package Controller;

import java.util.HashMap;
import java.util.Map;

import Domain.DTO;

public class FrontController {
	private Map<String, SubController> map = new HashMap();

	public FrontController() {
		init();
	}

	private void init() {
		map.put("/login", new LoginController());
//		map.put("/reserve", new ReserveController());
		map.put("/cinema", new CinemaController());
		map.put("/theater", new TheaterController());
		map.put("/movie", new MovieController());
	}

	public Object MainController(String menu, int SN, DTO dto) {
		/*
		 * map.put에 저장된 menu 중 하나를 고르면 해당 서비스를 실행한다.
		 * 링크타고 간다 생각하면 더 쉬울 것 같다. (RequestMapping?)
		 */
		SubController sub = map.get(menu);
		// 서비스 내 메뉴를 선택한다.
		Object obj = sub.excute(SN, dto);
		return obj;
	}

	public static void main(String[] args) {
		FrontController fc = new FrontController();
		// Test
		fc.MainController("/login", 1, null); // OK: Login
		fc.MainController("/login", 2, null); // Error: Logout
		fc.MainController("/login", 3, null); // OK: Login not service input

	}

}
