package Controller;

import Domain.DTO;
import Domain.MovieDAO;
import Domain.MovieDTO;
import Service.MovieService;

public class MovieController implements SubController {
	
	MovieService service = new MovieService();
	MovieDAO dao = new MovieDAO();

	@Override
	public Object excute(int SN, DTO dto) {
		Object obj=null;
		switch(SN)
		{
		case 1 : //도서등록
//			System.out.println("도서등록 서비스 요청");
			MovieDTO down = (MovieDTO)dto;
					
			System.out.println("[System]상영가능영화 등록 성공 여부 : ");
		case 2: //도서조회
			System.out.println("상영가능영화조회 서비스 요청");
			break;
		case 3: //도서수정
			System.out.println("상영가능영화수정 서비스 요청");
			break;
		case 4: //도서삭제
			System.out.println("상영가능영화삭제 서비스 요청");
			break;
		case 5:	//도서 전체 조회
				obj = dao.selectMovieAll();
			break;
		default:
			System.out.println("잘못된 서비스 요청입니다.");
		}
		
		return obj;
	}
}
