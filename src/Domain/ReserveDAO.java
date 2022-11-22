package Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReserveDAO extends DTO {

	static String tmp_mId = null;
	static String tmp_movieName = null;
	static int tmp_movieTime = 0;
	static int tmp_price = 0;
	static int tmp_NumOfPeo = 0;
	static int seat_choice = 0;

	final int movieChoice = 1;
	final int movieTimeChoice = 2;
	final int movieSeatChoice = 3;
	final int movieTicketing = 4;
	final int movieTicketReserve = 5;

	final int menuNember = 0;

	private static ReserveDAO instance;

	public static ReserveDAO getInstance() {
		if (instance == null)
			;
		instance = new ReserveDAO();
		return instance;
	}

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	/*
	 * <movieChoice>
	 * Service에 넣어야 할 메소드
	 */

	public String movieChoice(String movieName) {
		ArrayList<MovieDTO> list = new ArrayList<MovieDTO>();
		MovieDTO dto = new MovieDTO();
		ReserveDAO dao = new ReserveDAO();

		String test = null;
		String sql = "select * from tbl_movie where movieName=?";

		System.out.println("<영화 예매 시스템>");

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setMovieName(rs.getString("movieName"));
				list.add(dto);
			}

			tmp_movieName = movieName;

			int num = menuNember;

			while (true) {
				switch (num) {
				case menuNember:
					if (tmp_movieName.equals(dto.getMovieName())) {
						System.out.println(tmp_movieName + "는(은) 상영중입니다.");
						System.out.println(tmp_movieName);
						return tmp_movieName;
//						return dao.movieTimeChoice(tmp_movieName);
					} else {
						System.out.println("선택하신 " + tmp_movieName + "은(는) 상영 중이 아닙니다.");
					}
					break;
				case movieTimeChoice:
					String sql2 = "select * from tbl_movie where movieName=?";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, movieName);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						dto.setMovieName(rs.getString("movieName"));
						list.add(dto);
					}

					tmp_movieName = movieName;
					
//					return dao.movieSeatChoice();
//				case movieSeatChoice:
//					return dao.movieTicketing();
//				case movieTicketing:
//					return dao.movieTicketReserve();
//				case movieTicketReserve:
//					break;
				default:
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
		return tmp_movieName;
	}

	/*
	 * <movieTimeChoice>
	 * Service에 넣어야 할 메소드
	 */
	
//	public ReserveDAO movieTimeChoice(String movieName) {
//		ArrayList<MovieDTO> list = new ArrayList<MovieDTO>();
//		MovieDTO dto = new MovieDTO();
//
//		String sql = "select * from tbl_movie where movieName=?";
//		conn = DBConnection.getConnection();
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, movieName);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				System.out.println("Success!"); // 결과 확인 후 주석 처리
//				dto = new MovieDTO();
//				dto.setMovieCode(rs.getInt("movieCode"));
//				dto.setMovieName(rs.getString("movieName"));
//				dto.setMovieStartDate(rs.getString("movieStartDate"));
//				dto.setMovieEndDate(rs.getString("movieEndDate"));
//				dto.setMovieTime(rs.getString("movieTime"));
//				dto.setCinemaNum(rs.getInt("cinemaNum"));
//				System.out.println("<상영 영화 조회>");
//				System.out.println("영화 상영시간: " + rs.getString("movieTime"));
//
//				for (MovieDTO m : list) {
//					System.out.print(m.getMovieTime() + "  ");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
////		tmp_movieTime = movieTime;
//
//		return null;
//
//	}
//
//	public ReserveDAO movieSeatChoice() {
//		/*
//		 * from tbl_cinema, tbl_movie, tbl_theater where
//		 * tbl_cinema.cinemaNum=tbl_movie.cinemaNum and
//		 * tbl_movie.cinemaNum=tbl_theater.cinemaNum; select * from theaterSeatOK_view;
//		 */
//
//		return null;
//	}
//
//	public ReserveDAO movieTicketing() {
//		return null;
//	}
//
//	public ReserveDAO movieTicketReserve() {
//		return null;
//	}

	// **********************************************************************

	public int ticketInsert(ReserveDTO dto) {
		ArrayList<ReserveDTO> list = new ArrayList<ReserveDTO>();
		int result = 0;
		String sql = "insert into tbl_reserve values(?,?,?,?,?,?)";

		try {
			
			// dao.ticketInsert(new ReserveDTO(seat_choice, tmp_movieName, tmp_mId, tmp_price, tmp_movieTime, tmp_NumOfPeo, seat_choice));

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getTicketNum());
			pstmt.setString(2, dto.getmId());
			pstmt.setString(3, dto.getTicketDate());
			pstmt.setInt(4, dto.getMovieCode());
			pstmt.setInt(5, dto.getTheaterNum());
			pstmt.setInt(6, dto.getCinemaNum());
			pstmt.setInt(7, dto.getTheaterSeat());
			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("티켓 예매가 완료되었습니다.\n");
				System.out.println("티켓 예매 내역");
				for (ReserveDTO r : list) {
					System.out.println("회원 ID: " + r.getmId() + " 영화 예매 날짜: " + r.getTicketDate() + " 티켓 번호: "
							+ r.getTicketNum() + " 영화 제목: " + r.getMovieName() + " 좌석 번호: " + r.getTicketNum());
				}
			} else {
				System.out.println("티켓 예매에 실패했습니다.");
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
		return result;
	}

	// **********************************************************************

//	public MovieDTO ticketBuy(String movieName) {
//
//		ArrayList<MovieDTO> list = new ArrayList<MovieDTO>();
//		ArrayList<TheaterDTO> list2 = new ArrayList<TheaterDTO>();
//
//		MovieDTO dto = new MovieDTO();
//		TheaterDTO dto2 = new TheaterDTO();
//
//		int result = 0;
//		String sql = "select * from tbl_movie where movieName=?";
//
//		conn = DBConnection.getConnection();
//
//		try {
//
//			conn.setAutoCommit(false);
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, movieName);
//			rs = pstmt.executeQuery();
//
//			if (rs != null) {
//				if (rs.next()) {
//					System.out.println(rs.getString("movieName") + "는 상영중입니다.");
//					System.out.println("상영 시간을 고르세요.");
//
//					System.out.println();
//
//					while (rs.next()) {
//						dto.setMovieTime(rs.getString("movieTime"));
//						list.add(dto);
//					}
//
//					int i = 0;
//					// Error: 제일 첫 회차 시간이 안뜬다.
//					for (i = 1; i < list.size(); i++) {
//						for (MovieDTO m : list) {
//							System.out.println(i + ". 상영 시간: " + m.getMovieTime());
//							i++;
//						}
//					}
//
//					Scanner sc = new Scanner(System.in);
//					ArrayList<MovieDTO> tmp = new ArrayList<MovieDTO>();
//
//					System.out.println();
//					System.out.println("상영을 원하는 시간을 입력해주세요.");
//					System.out.println("EX: 1. 12시 50분 ~");
//
//					System.out.println("test version");
//					String movieSelect = sc.next();
//
//					switch (movieSelect) {
//					case "1":
//						String sql2 = "select * from theaterSeat_view";
//						pstmt = conn.prepareStatement(sql2);
//						rs.getInt("theaterSeat");
//						result = rs.getInt("theaterSeat");
//						rs = pstmt.executeQuery();
//						if (rs.next()) {
//							System.out.println("test");
//							pstmt.setInt(1, dto2.getTheaterSeat());
//						} else {
//
//							list2.add(dto2);
//							System.out.println("test");
//						}
//
//						break;
//					case "2":
//
//						break;
//					case "3":
//
//						break;
//
//					default:
//						break;
//					}
//
//				} else {
//					System.out.println("해당 영화가 존재하지 않습니다.");
//
//				}
//
//			}
//			// Update 완료 후 Insert문에서 문제가 생겼을 경우 result를 0으로 변경해 데이터를 reset한다.
//			result = 0;
//
//		} catch (
//
//		Exception e) {
//
//		} finally {
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
//		return null;
//	}
	
// * Select문
	
	public ReserveDTO select(String pId) {
		String sql = "select * from tbl_reserve where mId=?";
		conn = DBConnection.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pId);
			rs = pstmt.executeQuery();

			MemberDTO dto = new MemberDTO();

			// 반환할 값이 있다면(member ID 값이 들어있다면) if문이 수행된다.
			if (rs.next()) {
//				System.out.println("Success!"); // 결과 확인 후 주석 처리

//				pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, dto.getTicketNum());
//				pstmt.setString(2, dto.getmId());
//				pstmt.setString(3, dto.getTicketDate());
//				pstmt.setInt(4, dto.getMovieCode());
//				pstmt.setInt(5, dto.getTheaterNum());
//				pstmt.setInt(6, dto.getCinemaNum());
//				pstmt.setInt(7, dto.getTheaterSeat());
//				result = pstmt.executeUpdate();
				
				dto.setmId(rs.getString("mId"));
				// PW는 개인 정보이므로 생략한다.
				dto.setmEmail(rs.getString("mEmail"));
				dto.setmBirth(rs.getInt("mBirth"));
				dto.setmRole(rs.getInt("mRole"));
				// 인자 값으로 받아온 member의 ID와 함께 정보를 출력한다.
				System.out.println("<회원 정보 출력>");
				System.out.println(rs.getString("mId") + " 회원님의 정보를 출력합니다.");
				System.out.println("회원 ID: " + rs.getString("mId") + "\n회원 이메일: " + rs.getString("mEmail") + "\n회원 생일: "
						+ rs.getInt("mBirth") + "\n회원 권한: " + rs.getInt("mRole"));
				if (rs.getInt("mRole") == 1) {
					System.out.println("일반 회원입니다.");
				} else {
					System.out.println("관리자입니다.");
				}
			} else {
				conn.close();
				System.out.println("<회원 정보 출력>");
				System.out.println("해당 회원이 존재하지 않습니다.");
				return null;
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
		return null;
	}
	
	// ********************************************************************************

	public static void main(String[] args) {
		ReserveDAO dao = new ReserveDAO();
		
		System.out.println(tmp_movieName);
		
		/*
		 * 	pstmt.setInt(1, dto.getTicketNum());
			pstmt.setString(2, dto.getmId());
			pstmt.setString(3, dto.getTicketDate());
			pstmt.setInt(4, dto.getMovieCode());
			pstmt.setInt(5, dto.getTheaterNum());
			pstmt.setInt(6, dto.getCinemaNum());
			pstmt.setInt(7, dto.getTheaterSeat());
		 */
//		dao.ticketInsert(new ReserveDTO(1,"userTest", "2022-11-21", 1, 1 , 1 , 1 ));
		
//		public int memberRole(String mId, String mPw) {}

		dao.movieChoice("블랙 팬서");
//		dao.movieChoice("false");

//		dao.ticketBuy("블랙 팬서");
//		dao.ticketBuy("false");


	}

}
