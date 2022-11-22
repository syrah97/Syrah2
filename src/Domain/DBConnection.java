package Domain;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 문자열에 입력된 클래스를 찾는다.
			// == com.mysql.cj.jdbc.Driver 클래스를 찾는다.
			Class.forName("com.mysql.cj.jdbc.Driver");

			/*
			 * String id = "root";
			 * String pw = "1234";
			 * String url = "jdbc:mysql://localhost:3306/cinemaDB";
			 */
			
			// DriverManager.getConnection
			// 메소드를 이용하여 연결할 DB의 위치, SQL 계정, 비밀번호를 입력하고 연결할 통로를 만든다.
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemaDB", "root", "1234");
//			conn = DriverManager.getConnection(url, id, pw);

//			System.out.println("DB Connected Success!");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

//	public static void main(String[] args) {
//		// DB Connection Test
//		DBConnection dc = new DBConnection();
//	}
}
