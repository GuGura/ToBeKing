package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection getConnection() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/LectureEvaluation";
			String dbID = "root";
			String dbPwd = "root";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
