package Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO extends DTO {

	private static LoginDAO instance;

	public static LoginDAO getInstance() {
		if (instance == null)
			;
		instance = new LoginDAO();
		return instance;
	}

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	// ********************************************************************************

	// github open source code version: Test 완료
	public int memberLogin(String mId, String mPw) {
		MemberDTO dto = new MemberDTO();
		int result = 0;

		try {
			conn = DBConnection.getConnection();
			String sql = "select * from tbl_member where mId=? and mPw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt.setInt(2, dto.getmRole());
				result = rs.getInt("mRole");
				System.out.println("<로그인 성공>");
				if (rs.getInt("mRole") == 1) {
					System.out.println(mId + "님은 일반 회원으로 로그인되었습니다.");
				} else if (rs.getInt("mRole") >= 1) {
					System.out.println(mId + "님은 관리자 권한으로 로그인되었습니다.");
				}
			} else {
				System.out.println("<로그인 실패>");
				System.out.println("ID 혹은 PW를 다시 확인해주세요.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 일반 회원: 1, 관리자: 2, 3
		return result;
	}

	// jy version: Test 완료
	public MemberDTO memberRole(String mId, String mPw) {
		String sql = "select * from tbl_member where mId=? and mPw=?";
		conn = DBConnection.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();

			MemberDTO dto = new MemberDTO();

			if (rs.next()) {
				dto.setmRole(rs.getInt("mRole"));
				if (rs.getInt("mRole") == 1) {
					System.out.println("<로그인 성공>");
					System.out.println(mId + "님은 일반 회원으로 로그인되었습니다.");
				} else if (rs.getInt("mRole") >= 2) {
					System.out.println("<로그인 성공>");
					System.out.println(mId + "님은 관리자 권한으로 로그인되었습니다.");
				}
			} else {
				System.out.println("<로그인 실패>");
				System.out.println("ID 혹은 PW를 다시 확인해주세요.");

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
		return null;

	}

	// ********************************************************************************

	public void Logout(PreparedStatement stmt, ResultSet rs, Connection conn) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conn.close();
				System.out.println("로그아웃이 완료되었습니다.");
			} else {
				System.out.println("현재 로그인 상태가 아닙니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LoginDAO ls = new LoginDAO();
		// Login Test 완료
		ls.memberRole("testUser", "1234");
//		ls.memberRole("testManager", "1234");
//		ls.memberLogin("testUser", "1234");
//		ls.memberLogin("testManager", "1234");

//		ls.memberRole("false", "1234");
//		ls.memberLogin("false", "1234");

		// Logout Test
		ls.Logout(pstmt, rs, conn);
	}

}
