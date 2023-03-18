package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {
	
	
	/***
	 * 아이디와 비번을 받아서 로그인을 시도해주는 함수이고
	 * 그 결과값을 정수로 반환한다.
	 * @param userID
	 * @param userPassword
	 * @return
	 */
	public int login(String userID, String userPassword) {
		String sql = "SELECT userPassword FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) { 
					return 1; // 로그인 성공
				}else {
					return 0; // 비밀번호 틀림
				}
			}
			return -1;  // 아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn != null) conn.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(rs != null) rs.close();} catch (Exception e2){e2.printStackTrace();}
		}
		return -2; //데이터 베이스 오류
	}
	/***
	 * 사용자의 정보를 입력받아서 회원가입을 수행해주는 함수이고 
	 * 그 결과를 정수로 리턴해준다.
	 * @param user
	 * @return
	 */
	public int join(UserDTO user) {
		String sql = "INSERT INTO USER VALUES (?,?,?,?,false)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn != null) conn.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2){e2.printStackTrace();}
		}
		return -1; //데이터 베이스 오류
	}
	/***
	 * 사용자의 이메일인증이 되었는 지를 확인해주는 함수이고 
	 * 결과는 boolean값으로 반환한다.
	 * @param userID
	 * @return
	 */
	public Boolean getUserEmailChecked(String userID) {
		String sql = "SELECT userEmailChecked FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn != null) conn.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(rs != null) rs.close();} catch (Exception e2){e2.printStackTrace();}
		}
		return false; //데이터 베이스 오류
	}
	/***
	 * 사용자의 ID값을 받아서 그 사용자의 이메일을 반환해주는 함수이고
	 * 결과값은 문자열
	 * @param userID
	 * @return
	 */
	public String getUserEmail(String userID) {
		String sql = "SELECT userEmail FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn != null) conn.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(rs != null) rs.close();} catch (Exception e2){e2.printStackTrace();}
		}
		return null; //데이터 베이스 오류
	}
	
	/***
	 * 특정한 사용자의 이메일 인증을 수행해주는 함수
	 * @param userID
	 * @return
	 */
	public Boolean setUserEmailChecked(String userID) {
		String sql = "UPDATE USER SET userEmailChecked = true WHERE userID =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {if(conn != null) conn.close();} catch (Exception e2){e2.printStackTrace();}
			try {if(pstmt != null) pstmt.close();} catch (Exception e2){e2.printStackTrace();}
		}
		return false; //데이터 베이스 오류
	}
}
