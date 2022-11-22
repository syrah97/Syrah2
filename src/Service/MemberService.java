package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.DBConnection;
import Domain.MemberDAO;
import Domain.MemberDTO;

public class MemberService {

	Connection conn = null;
	PreparedStatement pstmt = null;
	public ResultSet rs = null;

	public MemberService() {
	}

	private MemberDAO dao = MemberDAO.getInstance();
	private LoginService ser = LoginService.getInstance();

	private static MemberService instance;

	public static MemberService getInstance() {
		if (instance == null) {
			instance = new MemberService();
			return instance;
		}
		return instance;
	}

	public void memberInsert() {
		System.out.println("회원 정보를 등록합니다.");
//		dao.Insert(new MemberDTO(null, null, null, 0, 0));

	}

	public void memberShowInfoAll(String mId, String mPw) {
		MemberDTO dto = new MemberDTO();
		System.out.println("권한을 확인합니다.");
		System.out.println("ID와 PW를 입력하세요.");

		try {
			conn = DBConnection.getConnection();
			String sql = "select * from tbl_member where mId=? and mPw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int role = 0;

				pstmt.setInt(2, dto.getmRole());
				role = rs.getInt("mRole");
				System.out.println("\n<회원 전체 정보>");
				if (rs.getInt("mRole") == 1) {
					System.out.println("권한이 없습니다.");
				} else if (rs.getInt("mRole") >= 1) {
					dao.selectAll();
				} else {
					System.out.println("ID나 PW가 일치하지 않습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	}

	// 권한 여부 확인 후 해당 회원의 ID와 PW가 맞을 경우 정보를 출력한다.
	// 시간상 PASS
//	public void memberShowInfoSelect() {
//		dao.selectOne("testUser");
//	}

	public static void main(String[] args) {
		MemberService ser = new MemberService();
//		ser.memberShowInfoAll("testUser", "1234");
		ser.memberShowInfoAll("testManager", "1234");
//		ser.memberShowInfoSelect();
	}
}