package Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO extends DTO {

	private static MemberDAO instance;

	public static MemberDAO getInstance() {
		if (instance == null)
			;
		instance = new MemberDAO();
		return instance;
	}

	Connection conn = null; // DB를 연결하는 객체
	PreparedStatement pstmt = null; // SQL 쿼리를 전송하는 객체
	ResultSet rs = null; // SQL 쿼리 결과를 수신하는 객체

	public int Insert(MemberDTO dto) {
		int result = 0;
		String sql = "insert into tbl_member values(?, ?, ?, ?, ?)";
		try {
			// DBConnection 클래스의 getConnection 메소드를 호출한다. (DB 연결용)
			conn = DBConnection.getConnection();
			/*
			 * SQL 쿼리를 전송하는(pstmt) 객체를 DB에 연결 후(conn) SQL 구문을 실행시킨다. (prepareStatement) 변수
			 * 값을 prepareStatement에 넣을 때는 'sql O "sql" X', 문자열로 넣으면 파라미터 에러가 발생한다.
			 */
			pstmt = conn.prepareStatement(sql); // O
//			pstmt = conn.prepareStatement("sql"); // X
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getmPw());
			pstmt.setString(3, dto.getmEmail());
			pstmt.setInt(4, dto.getmBirth());
			pstmt.setInt(5, dto.getmRole());
			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("tbl_member Connected.");
				System.out.println("Insert Success!");
			} else {
				System.out.println("tbl_member Connected.");
				System.out.println("Insert Fail.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 관리
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public MemberDTO selectOne(String member) {
		String sql = "select * from tbl_member where mId=?";
		conn = DBConnection.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member);
			rs = pstmt.executeQuery();

			MemberDTO dto = new MemberDTO();

			// 반환할 값이 있다면(member ID 값이 들어있다면) if문이 수행된다.
			if (rs.next()) {
//				System.out.println("Success!"); // 결과 확인 후 주석 처리

				dto.setmId(rs.getString("mId"));
				// PW는 개인 정보이므로 생략한다.
				dto.setmEmail(rs.getString("mEmail"));
				dto.setmBirth(rs.getInt("mBirth"));
				dto.setmRole(rs.getInt("mRole"));
				// 인자 값으로 받아온 member의 ID와 함께 정보를 출력한다.
				System.out.println("<회원 정보 출력>");
				System.out.println(rs.getString("mId") + " 회원님의 정보를 출력합니다.");
				System.out.println("회원 ID: " + rs.getString("mId") + "\n회원 이메일: " + rs.getString("mEmail") + "\n회원 생일: "
						+ rs.getInt("mBirth") + "\n회원 권한: " + rs.getInt("mRole"));
				if (rs.getInt("mRole") == 1) {
					System.out.println("일반 회원입니다.");
				} else {
					System.out.println("관리자입니다.");
				}
			} else {
				conn.close();
				System.out.println("<회원 정보 출력>");
				System.out.println("해당 회원이 존재하지 않습니다.");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 관리
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// MemberDTO 타입 ArrayList 메소드를 생성한다.
	public ArrayList<MemberDTO> selectAll() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		MemberDTO dto = new MemberDTO(); // null!
		String sql = "select * from tbl_member";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select * from tbl_member");
			rs = pstmt.executeQuery();

			// 만약에 rs에 값이 들어갔다면(0이 아니라면)
			if (rs != null) {
//				System.out.println("tbl_member Connected!");

				// 차례로 ArrayList에 값을 넣는다.
				while (rs.next()) {
					dto = new MemberDTO();
					dto.setmId(rs.getString("mId"));
					dto.setmPw(rs.getString("mPw"));
					dto.setmEmail(rs.getString("mEmail"));
					dto.setmBirth(rs.getInt("mBirth"));
					dto.setmRole(rs.getInt("mRole"));
					list.add(dto);
				}
				for (MemberDTO m : list) {
					System.out.print("회원 ID: " + m.getmId() + " 회원 이메일: " + m.getmEmail() + " 회원 생일: " + m.getmBirth()
							+ " 회원 권한: " + m.getmRole());
					if (m.getmRole() == 1) {
						System.out.print(" 일반 회원입니다.\n");
					} else if (m.getmRole() >= 2) {
						System.out.print(" 관리자입니다.\n");
					}
				}
			}

		} catch (Exception e) {
			// printStackTrace: 예외 발생 시 호출 스택에 있던 에러 문구를 출력한다.
			e.printStackTrace();
		}
		// 마지막에 무조건 실행된다.
		finally {
			// 자원 관리
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public static void main(String[] args) {
		MemberDAO md = new MemberDAO();

		// Member Insert Test OK
//		md.Insert(new MemberDTO("testUser", "1234", "eMail@naver.com", 990101, 1));
//		md.Insert(new MemberDTO("testManager", "1234", "eMail2@naver.com", 900101, 3));
//		md.Insert(new MemberDTO("testUser2", "1234", "eMail2@naver.com", 910101, 3));

		// Member Select Test OK
		md.selectAll();

		/*
		 * <설명> pstmt.setString(1, member); mId 속성명 중, testUser에 해당하는 회원의 정보를 출력한다.
		 * selectOne 메소드를 호출 시 괄호() 안의 문자열은 member의 인자로 member 매개변수로 주소 값을 전달한다.
		 */
		// Member Select Test OK
//		md.selectOne("testUser"); // 해당 회원이 존재할 경우
//		System.out.println(); // 구분선
		md.selectOne("false"); // 해당 회원이 존재하지 않을 경우
//		md.selectOne("testManager"); // Test
	}
}
