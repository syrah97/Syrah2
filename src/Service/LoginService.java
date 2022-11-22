package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Domain.DBConnection;
import Domain.DTO;
import Domain.LoginDAO;
import Domain.MemberDTO;

public class LoginService extends DTO {

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	public LoginService() {
	}

	private LoginDAO dao = LoginDAO.getInstance();

	private static LoginService instance;

	public static LoginService getInstance() {
		if (instance == null)
			instance = new LoginService();
		return instance;
	}

	// 로그인
	public int Login(String mId, String mPw) {
		MemberDTO dto = new MemberDTO();
		LoginDAO dao = new LoginDAO();
		int role = 0;

		// 인자 값을 적어주지 않아도 매개변수명을 적어주면 호출된 메소드에서 인자 값을 해당 매개변수로 값을 넘겨준다.
//		dao.memberLogin(mId, mPw);
		// 1. 메소드 내에서 매개변수 값(== 인자 값) 입력하기
//		dao.memberRole("testUser", "1234");
//		dao.memberRole("testManager", "1234");
//		dao.memberRole("false", "1234");

		try {
			conn = DBConnection.getConnection();
			String sql = "select * from tbl_member where mId=? and mPw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt.setInt(2, dto.getmRole());
				role = rs.getInt("mRole");
				System.out.println("<로그인 성공>");
				if (rs.getInt("mRole") == 1) {
					System.out.println(mId + "님은 일반 회원으로 로그인되었습니다.");
				} else if (rs.getInt("mRole") >= 1) {
					System.out.println(mId + "님은 관리자 권한으로 로그인되었습니다.");
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

		return role;
	}

	public void Logout(PreparedStatement pstmt, ResultSet rs, Connection conn) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conn.close();
				System.out.println("<로그아웃 성공>");
				System.out.println("로그아웃이 완료되었습니다.");
			} else {
				System.out.println("<로그아웃 실패>");
				System.out.println("현재 로그인 상태가 아닙니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LoginService ser = new LoginService();
		// Login Test 완료
		// 2. 메인 메소드에서 메소드 호출 후 매개변수 값 입력하기
		ser.Login("testUser", "1234");
//		ser.Login("false", "1234");
//		ser.Login("testManager", "1234");

//		ser.Login(mId, mPw);
//		ser.Login(null, null);

		// Logout Test 완료
		try {
			ser.Logout(pstmt, rs, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
