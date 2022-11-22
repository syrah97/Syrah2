package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.DBConnection;
import Domain.MemberDTO;
import Domain.MovieDAO;
import Domain.MovieDTO;

public class MovieService {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public MovieService() {
	}

	private MovieDAO dao = MovieDAO.getInstance();

	MovieDAO mDAO = MovieDAO.getInstance();
	private MovieService instance;

	public MovieService getInstance() {
		if (instance == null)
			instance = new MovieService();
		return instance;
	}

	// int movieCode, String movieName, String MovieStartDate, String movieEndDate,
	// String movieTime, int cinemaNum
	public MovieDAO movieInsert(String mId, String mPw) {
		MemberDTO dto = new MemberDTO();
		MovieDAO movie = new MovieDAO();
		System.out.println("권한을 확인합니다.");
		System.out.println("ID와 PW를 입력하세요.");

		try {
			conn = DBConnection.getConnection();
			String sql = "select * from tbl_member where mId=? and mPw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int role = 0;

				pstmt.setInt(2, dto.getmRole());
				role = rs.getInt("mRole");
				System.out.println("\n<영화 등록>");
				if (rs.getInt("mRole") == 1) {
					System.out.println("권한이 없습니다.");
				} else if (rs.getInt("mRole") >= 1) {
					System.out.println("영화를 등록합니다.");
//					movie.Insert(new MovieDTO());
				}
//					else { // Error
//					System.out.println("ID나 PW가 일치하지 않습니다.");
//				}
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

	public void movieShowInfoAll() {
		MovieDAO dao = new MovieDAO();
		dao.selectMovieAll();
	}

	public void movieShowInfoSelect(String movieName) {
		MovieDAO dao = new MovieDAO();
		dao.selectOne("블랙 팬서");
	}

	public static void main(String[] args) {
		MovieService ser = new MovieService();

		// 시간상 Insert Test 생략
		// showInfo Test 완료
//		ser.movieShowInfoAll();
//		System.out.println();
//		ser.movieShowInfoSelect("블랙 팬서");

		ser.movieInsert("testUser", "1234");
//		ser.movieInsert("testManager", "1234");
	}
}
