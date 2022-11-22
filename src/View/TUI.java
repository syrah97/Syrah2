package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.FrontController;
import Domain.CinemaDTO;
import Domain.MovieDTO;

public class TUI {

		private FrontController controller = new FrontController();
		// 생성자
		Scanner sc = new Scanner(System.in);
		int n = 0;

		TUI() {
			mainview();
		}

		void mainview() {
			while (true) {
				System.out.println("-------------------M A I N --------------------");
				System.out.println("1 상영가능 영화 조회");
				System.out.println("2 영화관 조회");
				System.out.println("4 종료");
				System.out.println("-------------------M A I N --------------------");
				System.out.println("번호 : ");
				n = sc.nextInt();
				switch (n) {
				case 1:
					List<MovieDTO> mlist = (ArrayList<MovieDTO>)controller.MainController("/movie", 5, null);
					for(MovieDTO dto : mlist) {
						System.out.println();
					}
					break;
				case 2:		
					List<CinemaDTO> clist = (ArrayList<CinemaDTO>)controller.MainController("/cinema", 5, null);
					for(CinemaDTO dto : clist) {
						System.out.println();
					}
					break;
				case 3:
					break;
				case 4:
					System.out.println("종료합니다.");
					System.exit(-1);
					break;
				default:

				}
			}
		}

		

		

		public static void main(String[] args) {
			new TUI();
		}
}
