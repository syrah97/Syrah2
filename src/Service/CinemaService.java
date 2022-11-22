package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.CinemaDAO;
import Domain.CinemaDTO;
import Domain.DBConnection;
import Domain.MemberDTO;

public class CinemaService {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public CinemaService() {
	}

	CinemaDAO dao = CinemaDAO.getInstance();

	private static CinemaService instance;

	public static CinemaService getInstance() {
		if (instance == null) {
			instance = new CinemaService();
			return instance;
		}
		return instance;
	}

	public void cinemaInsert(String mId, String mPw) {
		MemberDTO dto = new MemberDTO();
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
				System.out.println("<상영관 등록>");
				if (rs.getInt("mRole") == 1) {
					System.out.println("권한이 없습니다.");
				} else if (rs.getInt("mRole") >= 1) {
					System.out.println("상영관을 등록합니다.");
//					dao.Insert(new CinemaDTO());
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
	}

	public void cinemaShowInfoAll() {
		CinemaDAO dao = new CinemaDAO();
		dao.selectAll();
	}

	public static void main(String[] args) {
		CinemaService ser = new CinemaService();
//		ser.cinemaShowInfoAll();
//		ser.cinemaInsert("testUser", "1234"); // OK
//		ser.cinemaInsert("testManager", "1234"); // OK
//		ser.cinemaInsert("false", "1234"); // Error
	}
}
