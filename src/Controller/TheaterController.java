package Controller;

import Domain.DTO;
import Service.TheaterService;

public class TheaterController implements SubController {

	TheaterService service = new TheaterService();

	@Override
	public Object excute(int SN, DTO dto) {
		Object obj = null;

		// 상영관 조회, 상영관 영화조회, 상영관 좌석조회
		switch (SN) {
		case 1: // 좌석 조회
			System.out.println("상영관 조회 서비스 요청");
			break;
		case 2: // 좌석 선택
			System.out.println("상영관 영화 조회 서비스 요청");
			break;
		case 3: //
			System.out.println("상영관 좌석 조회 서비스 요청");
			break;
		default:
			System.out.println("잘못된 서비스 요청입니다.");
		}

		return obj;

	}

}
