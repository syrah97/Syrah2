package Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CinemaDAO {

	Connection conn = null; // DB를 연결하는 객체
	PreparedStatement pstmt = null; // SQL 쿼리를 전송하는 객체
	ResultSet rs = null; // SQL 쿼리 결과를 수신하는 객체

	private static CinemaDAO instance;

	public static CinemaDAO getInstance() {
		if (instance == null)
			;
		instance = new CinemaDAO();
		return instance;
	}

	public int Insert(CinemaDTO dto) {
		int result = 0;
		String sql = "insert into tbl_cinema values(?,?)";
		try {
			// DBConnection 클래스의 getConnection 메소드를 호출한다. (DB 연결용)
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getCinemaNum());
			pstmt.setString(2, dto.getCinemaName());
			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("영화관 등록 완료");
			} else {
				System.out.println("영화관 등록 실패");
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

	// 영화관 목록 출력하기
	public ArrayList<CinemaDTO> selectAll() {
		ArrayList<CinemaDTO> list = new ArrayList<CinemaDTO>();
		CinemaDTO dto = new CinemaDTO();
		String sql = "select * from tbl_cinema";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 만약에 rs에 값이 들어갔다면(0이 아니라면)
			if (rs != null) {
//				System.out.println("tbl_movie Connected!");

				// 차례로 ArrayList에 값을 넣는다.
				while (rs.next()) {
					dto = new CinemaDTO();
					dto.setCinemaNum(rs.getInt("cinemaNum"));
					dto.setCinemaName(rs.getString("cinemaName"));
					list.add(dto);
				}
				System.out.println("<영화관 전체 목록>");
				for (CinemaDTO m : list) {
					System.out.println("영화관 이름: " + m.getCinemaName());
				}
//				System.out.println(list);
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

	public static void main(String[] args) {
		CinemaDAO cd = CinemaDAO.getInstance();
//		cd.Insert(new CinemaDTO(1, "동성로점"));
//		cd.Insert(new CinemaDTO(2, "죽전점"));
//		cd.Insert(new CinemaDTO(3, "반월당점"));
//		cd.Insert(new CinemaDTO(4, "율하점"));

		cd.selectAll();
	}
}
