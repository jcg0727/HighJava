package kr.or.ddit.Test0623;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DAO implements Daotest{

	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "where mem di vaef ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, x);
			
			cnt = pstmt.executeUpdate();
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
	
		return 0;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMemberCount(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
