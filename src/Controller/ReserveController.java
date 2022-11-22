//package Controller;
//
//import java.util.ArrayList;
//
//import Domain.DTO;
//import Domain.ReserveDAO;
//import Domain.ReserveDTO;
//
//public class ReserveController implements SubController{
//	ArrayList<String> tmp = new ArrayList<String>();
//	ReserveDAO dao = new ReserveDAO();
//	ReserveDTO dto = new ReserveDTO();
//
//	final int movieChoice = 1;
//	final int movieTimeChoice = 2;
//	final int movieSeatChoice = 3;
//	final int movieTicketing = 4;
//	final int movieTicketReserve = 5;
//
//	final int menuNember = 0;
//
//	int movieReserve() {
//		int num = menuNember;
//
//		System.out.println("<영화 예매 시스템>");
//
//		while (true) {
//			switch (num) {
//			case menuNember:
////				dao.movieChoice();
//				break;
//			case movieTimeChoice:
//				dao.movieTimeChoice("");
//				break;
//			case movieSeatChoice:
//				dao.movieSeatChoice();
//				break;
//			case movieTicketing:
//				dao.movieTicketing();
//				break;
//			case movieTicketReserve:
//				dao.movieTicketReserve();
//			}
//		}
//	}
//
//	// ****************************************************************
//	
//	@Override
//	public Object excute(int SN, DTO dto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
