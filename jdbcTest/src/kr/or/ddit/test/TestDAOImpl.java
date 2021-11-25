package kr.or.ddit.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.util.DBUtil3;

public class TestDAOImpl implements ITestDAO{

	private static TestDAOImpl dao;
	
	private TestDAOImpl() {}
	public static TestDAOImpl getInstance() {
		if(dao==null) dao = new TestDAOImpl();
		return dao;
	}
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
	@Override
	public int insertTest(TestVO testVO) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "insert into test (test_num, test_id, test_nm) values(?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, testVO.getTestNum());
			pstmt.setString(2, testVO.getTestId());
			pstmt.setString(3, testVO.getTestNm());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
		}
		return cnt;
	}

}
