package Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// DB Connection 클래스 생성 후, 첫번째 실험용 클래스 파일

public class MovieDAO extends DTO {

//  DB Connection DAO 생성 이후, 백업용으로 주석 처리
//	String id = "root";
//	String pw = "1234";
//	String url = "jdbc:mysql://localhost:3306/cinemaDB";

//	MovieDAO() {
//		// Connection 객체 연결
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection(url, id, pw);
//			System.out.println("movie Connected!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	// 싱글톤 생성
	private static MovieDAO instance;

	public static MovieDAO getInstance() {
		if (instance == null)
			;
		instance = new MovieDAO();
		return instance;
	}

	Connection conn; // DB를 연결하는 객체
	PreparedStatement pstmt; // SQL 쿼리를 전송하는 객체
	ResultSet rs; // SQL 쿼리 결과를 수신하는 객체

	// 영화 추가하기
	public int Insert(MovieDTO dto) {
		int result = 0;
		String sql = "insert into tbl_movie values(?,?,?,?,?,?)";
		try {
			// DBConnection 클래스의 getConnection 메소드를 호출한다. (DB 연결용)
			conn = DBConnection.getConnection();
			// prepareStatement: SQL 구문을 실행시키는 객체
			pstmt = conn.prepareStatement(sql);
			// 위의 코드가 대체하므로 밑에 코드는 주석 처리한다.
//			pstmt = conn.prepareStatement("insert into tbl_movie values(?,?,?,?,?,?)");
			pstmt.setInt(1, dto.getMovieCode());
			pstmt.setString(2, dto.getMovieName());
			pstmt.setString(3, dto.getMovieStartDate());
			pstmt.setString(4, dto.getMovieEndDate());
			pstmt.setString(5, dto.getMovieTime());
			pstmt.setInt(6, dto.getCinemaNum());
			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("영화 추가 성공");
			} else {
				System.out.println("영화 추가 실패");
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

	public MovieDTO selectOne(String movieName) {
		String sql = "select * from tbl_movie where movieName=?";
		conn = DBConnection.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieName);
			rs = pstmt.executeQuery();

			MovieDTO dto = new MovieDTO();

			// 반환할 값이 있다면(movieName 값이 들어있다면) if문이 수행된다.
			if (rs.next()) {
//				System.out.println("Success!"); // 결과 확인 후 주석 처리
				dto = new MovieDTO();
				dto.setMovieCode(rs.getInt("movieCode"));
				dto.setMovieName(rs.getString("movieName"));
				dto.setMovieStartDate(rs.getString("movieStartDate"));
				dto.setMovieEndDate(rs.getString("movieEndDate"));
				dto.setMovieTime(rs.getString("movieTime"));
				dto.setCinemaNum(rs.getInt("cinemaNum"));
				System.out.println("<상영 영화 조회>");
				System.out.println("해당 영화가 상영 중입니다.");
				System.out.println("영화 이름: " + rs.getString("movieName") + "\n영화 개봉일: " + rs.getString("movieStartDate")
						+ "\n상영 종영일: " + rs.getString("movieEndDate") + "\n영화 상영시간: " + rs.getString("movieTime"));

			} else {
				conn.close();
				System.out.println("<상영 영화 조회>");
				System.out.println("상영 영화가 존재하지 않습니다.");
				return null;
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
		return null;

	}

	// 상영 영화 정보 출력하기
	public ArrayList<MovieDTO> selectMovieAll() {
		ArrayList<MovieDTO> list = new ArrayList<MovieDTO>();
		MovieDTO dto = null;
//		MovieDTO dto = new MovieDTO(); // null!
		String sql = "select * from tbl_movie";
		try {
			// DBConnection 클래스의 getConnection 메소드를 호출한다. (DB 연결용)
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 만약에 rs에 값이 들어갔다면(0이 아니라면)
			if (rs != null) {
//				System.out.println("tbl_movie Connected!");

				// 차례로 ArrayList에 값을 넣는다.
				while (rs.next()) {
					dto = new MovieDTO();
					dto.setMovieCode(rs.getInt("movieCode"));
					dto.setMovieName(rs.getString("movieName"));
					dto.setMovieStartDate(rs.getString("movieStartDate"));
					dto.setMovieEndDate(rs.getString("movieEndDate"));
					dto.setMovieTime(rs.getString("movieTime"));
					dto.setCinemaNum(rs.getInt("cinemaNum"));
					list.add(dto);
				}
				System.out.println("<영화 전체 목록(상영 시간)>");
				System.out.print("영화 제목: " + dto.getMovieName() + " 영화 개봉일: " + dto.getMovieStartDate() + " 영화 종영일: "
						+ dto.getMovieEndDate() + " 영화 상영시간: ");
				for (MovieDTO m : list) {
					System.out.print(m.getMovieTime() + "  ");
				}
//
//				// ArrayList로 출력하니 너무 지저분해서 삭제했습니다.
//				System.out.println(list);
			}

		} catch (Exception e) {
			// printStackTrace: 예외 발생 시 호출 스택에 있던 에러 문구를 출력한다.
			e.printStackTrace();
		}
		// 마지막에 무조건 실행된다.
		finally {
			// 자원 관리
			try {rs.close();} catch (Exception e) {e.printStackTrace();}
			try {pstmt.close();} catch (Exception e) {e.printStackTrace();}
		}
		return list;

	}

	public static void main(String[] args) {

//		MovieDAO md = new MovieDAO();
		MovieDAO md = MovieDAO.getInstance();

		// Insert Test
		// 블랙 팬서 러닝 타임: 2시간 15분
//		md.Insert(new MovieDTO(1, "블랙 팬서", "2023년 11월 11일", "2023년 12월 11일", "09시 35분", 1));
//		md.Insert(new MovieDTO(2, "블랙 팬서", "2023년 11월 11일", "2023년 12월 11일", "12시 50분", 1));
//		md.Insert(new MovieDTO(3, "블랙 팬서", "2023년 11월 11일", "2023년 12월 11일", "15시 20분", 1));
//		md.Insert(new MovieDTO(4, "블랙 팬서", "2023년 11월 11일", "2023년 12월 11일", "17시 45분", 1));
//
//		md.Insert(new MovieDTO(2, "데시벨", "2022년 11월 16일", "2022년 12월 16일", "1시간 50분",1));
//		md.Insert(new MovieDTO(3, "동감", "2022년 11월 16일", "2022년 12월 16일", "1시간 54분",1));

		// Select Test
		md.selectMovieAll();

		System.out.println();

		// SelectOne test
		md.selectOne("블랙 팬서"); // true: 상영 영화가 존재할 경우
//		md.selectOne("데시벨"); 	// true: 상영 영화가 존재할 경우

//		System.out.println(""); 	// 구분선

		System.out.println();
//		md.selectOne("false"); // false: 상영 영화가 존재하지 않을 경우

//		md.selectOne("TestMovie"); 	// Test
	}
}
