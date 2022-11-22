package Domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TheaterDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private static TheaterDAO instance;

	public static TheaterDAO getInstance() {
		if (instance == null)
			;
		instance = new TheaterDAO();
		return instance;
	}

	// 상영관 추가하기
	public int Insert(TheaterDTO dto) {
		int result = 0;
		String sql = "insert into tbl_theater values(?,?,?,?)";
		try {
			// DBConnection 클래스의 getConnection 메소드를 호출한다. (DB 연결용)
			conn = DBConnection.getConnection();
			// prepareStatement: SQL 구문을 실행시키는 객체
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getTheaterNum());
			pstmt.setInt(2, dto.getTheaterSeat());
			pstmt.setString(3, dto.getTheaterName());
			pstmt.setInt(4, dto.getCinemaNum());
			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("상영관 추가 성공");
			} else {
				System.out.println("상영관 추가 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 관리
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
		return result;
	}

	// 상영관 조회하기
	public ArrayList<TheaterDTO> selectAll() {
		ArrayList<TheaterDTO> list = new ArrayList<TheaterDTO>();
		TheaterDTO dto = new TheaterDTO(); // null!
		try {
			// DBConnection 클래스의 getConnection 메소드를 호출한다. (DB 연결용)
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select * from theaterSeat_view");
			rs = pstmt.executeQuery();

			// 만약에 rs에 값이 들어갔다면(0이 아니라면)
			if (rs != null) {
//				System.out.println("tbl_theater Connected!");

				// 차례로 ArrayList에 값을 넣는다.
				while (rs.next()) {
					dto = new TheaterDTO();
					dto.setCinemaName(rs.getString("cinemaName"));
					dto.setTheaterName(rs.getString("theaterName"));
					dto.setTheaterSeat(rs.getInt("theaterSeat"));
//					dto.setMovieName(rs.getString("movieName"));
//					dto.setCinemaNum(rs.getInt("cinemaNum"));
//					dto.setTheaterNum(rs.getInt("theaterNum"));
					list.add(dto);
				}
				System.out.println("<상영관 전체 목록>");
				for (TheaterDTO t : list) {
					System.out.println("영화관 이름: " + t.getCinemaName() + " 상영관 이름: " + t.getTheaterName());
				}
			}

		} catch (Exception e) {
			// printStackTrace: 예외 발생 시 호출 스택에 있던 에러 문구를 출력한다.
			e.printStackTrace();
		}
		// 마지막에 무조건 실행된다.
		finally {
			// 자원 관리
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
		return list;

	}

	// 상영관 조회하기
//	public ArrayList<TheaterDTO> selectAll() {
//		ArrayList<TheaterDTO> list = new ArrayList<TheaterDTO>();
//		TheaterDTO dto = new TheaterDTO(); // null!
//		try {
//			// DBConnection 클래스의 getConnection 메소드를 호출한다. (DB 연결용)
//			conn = DBConnection.getConnection();
//			pstmt = conn.prepareStatement("select * from tbl_theater");
//			rs = pstmt.executeQuery();
//			
//			// 만약에 rs에 값이 들어갔다면(0이 아니라면)
//			if (rs != null) {
//				System.out.println("tbl_theater Connected!");
//				
//				// 차례로 ArrayList에 값을 넣는다.
//				while (rs.next()) {
//					dto = new TheaterDTO();
//					dto.setTheaterNum(rs.getInt("theaterNum"));
//					dto.setTheaterSeat(rs.getInt("theaterSeat"));
//					dto.setTheaterName(rs.getString("theaterName"));
//					dto.setCinemaNum(rs.getInt("cinemaNum"));
//					list.add(dto);
//				}
//				System.out.println("<상영관 전체 목록>");
//				System.out.println("상영관 이름: " + dto.getTheaterName());
//			}
//			
//		} catch (Exception e) {
//			// printStackTrace: 예외 발생 시 호출 스택에 있던 에러 문구를 출력한다.
//			e.printStackTrace();
//		}
//		// 마지막에 무조건 실행된다.
//		finally {
//			// 자원 관리
//			try {
//				rs.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			try {
//				pstmt.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return list;
//		
//	}

	public static void main(String[] args) {

		TheaterDAO td = TheaterDAO.getInstance();

		td.selectAll();
//		td.Insert(new TheaterDTO(1, 1, "1관",1));
//		td.Insert(new TheaterDTO(1, 2, "1관",1));
//		td.Insert(new TheaterDTO(1, 3, "1관",1));
//		td.Insert(new TheaterDTO(1, 4, "1관",1));
//		td.Insert(new TheaterDTO(1, 5, "1관",1));

	}

}