package Controller;

import Domain.DTO;
import Domain.MemberDTO;
import Service.MemberService;

public class MemberController implements SubController {

	private MemberService ms = new MemberService();
	private MemberDTO dto = new MemberDTO();
	private MemberDTO d = (MemberDTO) dto;

	@Override
	public Object excute(int SN, DTO dto) {
		switch (SN) {
		case 1:
			// 회원 등록
			ms.memberInsert();
			try {
				if (ms.rs != null) {
					System.out.println("회원 등록이 완료되었습니다.");
				} else if (ms.rs == null) {
					System.out.println("회원 등록에 실패했습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:
			// 회원 전체 조회 (권한)
			ms.memberShowInfoAll("testUser", "1234");

			break;
		case 3:
			// 해당 회원 조회 (권한)

			break;
//		case 4:
//			System.out.println("회원 삭제 서비스");
//			break;
		default:
			System.out.println("잘못된 서비스 요청입니다.");
		}
		return null;
	}

	public static void main(String[] args) {
		MemberController mc = new MemberController();
		mc.excute(1, null);
		mc.excute(2, null);
	}

}
