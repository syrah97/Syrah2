package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.DTO;
import Domain.MemberDTO;
import Service.LoginService;

public class LoginController implements SubController {
	private LoginService service = LoginService.getInstance();

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	LoginService ser = new LoginService();

	@Override
	public Object excute(int SN, DTO dto) {

		switch (SN) {
		case 1:
			// LoginService.Login()
			MemberDTO d = (MemberDTO) dto;

//			String mId = d.getmId();
//			String mPw = d.getmPw();

			ser.Login("testUser", "1234");

			break;
		case 2:
			// Logout Error
			ser.Logout(pstmt, rs, conn);

			break;
//		case 3:
//			System.out.println("회원 수정 서비스");
//			break;
//		case 4:
//			System.out.println("회원 삭제 서비스");
//			break;
		default:
			System.out.println("잘못된 서비스 요청입니다.");

		}
		return null;
	}

	public static void main(String[] args) {

		LoginController lc = new LoginController();

		lc.excute(1, null);
		lc.excute(2, null);

	}

}
