package Controller;

import Domain.CinemaDAO;
import Domain.CinemaDTO;
import Domain.DTO;
import Service.CinemaService;

public class CinemaController implements SubController {
	
	CinemaService service = new CinemaService();
	CinemaDAO dao = new CinemaDAO();

	@Override
	public Object excute(int SN, DTO dto) {
		Object obj=null;
		switch (SN) {
		case 1:
			CinemaDTO down = (CinemaDTO)dto;
			System.out.println("상영관 등록 성공여부");

			break;
		case 2:
			System.out.println("상영관 조회 서비스 요청");
			break;
		case 3:
			System.out.println("상영관 수정 서비스 요청");
			break;
		case 4:
			System.out.println("상영관 삭제 서비스 요청");
			break;
		case 5:
			obj = dao.selectAll();
			break;
		default:
			System.out.println("잘못된 서비스 요청입니다.");
		}
		return obj;
	}

}
