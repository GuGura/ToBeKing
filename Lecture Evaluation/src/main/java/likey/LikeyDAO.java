package likey;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DatabaseUtil;

public class LikeyDAO {
	
	public int like(String userID, String evaluationID, String userIP) {
		String sql = "INSERT INTO LIKEY VALUES (?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, evaluationID);
			pstmt.setString(3, userIP);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn != null) conn.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2){e2.printStackTrace();}
		}
		return -1; //데이터 베이스 오류
	}
}
