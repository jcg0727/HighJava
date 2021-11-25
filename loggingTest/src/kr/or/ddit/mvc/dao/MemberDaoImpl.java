package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{
	
	private static final Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	//1번
	private static MemberDaoImpl dao;
	//2번
	private MemberDaoImpl() {
	}
	//3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	
	

   @Override
   public int insertMember(MemberVO memVo) {
      int cnt = 0; // 반환값이 저장될 변수
      Connection conn = null;
      PreparedStatement pstmt = null;
      try {
         conn = DBUtil3.getConnection();
         String sql = "insert into mymember (mem_id, mem_pass, mem_name,"
               + "mem_tel, mem_addr) values(?, ?, ?, ?, ?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memVo.getMem_id());
         pstmt.setString(2, memVo.getMem_pass());
         pstmt.setString(3, memVo.getMem_name());
         pstmt.setString(4, memVo.getMem_tel());
         pstmt.setString(5, memVo.getMem_addr());
     
         logger.info("PreparedStatement	객체 생성");
         logger.info("실행 SQL : " + sql);
         logger.info("사용데이터 : [" + memVo.getMem_id() + ", " + memVo.getMem_pass() + ", " + memVo.getMem_name() + ", " + memVo.getMem_tel() + ", " + memVo.getMem_addr() + "]");
         
         cnt = pstmt.executeUpdate();
         
         logger.info("insert 작업 성공");
         
      } catch (SQLException e) {
    	  logger.info("insert 작업 실패!! : " + e);
         cnt = 0;
         e.printStackTrace();
      } finally {
         if(pstmt!=null) try { 
        	 pstmt.close(); 
        	 logger.info("PreparedStatement객체 반납 성공...");
         } catch(SQLException e) {}
         if(conn!=null) try { 
        	 conn.close(); 
        	 logger.info("Connection 객체 반납 성공...");
         } catch(SQLException e) {}
      }
      
      return cnt;
   }

   @Override
   public int deleteMember(String memId) {
      int cnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      try {
         conn = DBUtil3.getConnection();
         String sql = "delete from mymember where mem_id = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memId);
         
         logger.info("PreparedStatement 객체 생성");
         logger.info("실행 sql문 : " + sql);
         logger.info("사용데이터 : " + memId);
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
    	  logger.info("delete 작업 실패" + e);
         e.printStackTrace();
      } finally {
         if(pstmt!=null) try { 
        	 pstmt.close(); 
        	 logger.info("PreparedStatement 객체 반납");
         } catch(SQLException e) {}
         if(conn!=null) try { 
        	 conn.close(); 
        	 logger.info("Connection 객체 반납");
         } catch(SQLException e) {}
      }
      
      return cnt;
   }

   @Override
   public int updateMember(MemberVO memVo) {
      int cnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "update mymember set mem_pass=? ,"
                  + "mem_name=?, mem_tel=?, mem_addr=?"
                  + "where mem_id=? ";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memVo.getMem_pass());
         pstmt.setString(2, memVo.getMem_name());
         pstmt.setString(3, memVo.getMem_tel());
         pstmt.setString(4, memVo.getMem_addr());
         pstmt.setString(5, memVo.getMem_id());
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try { conn.close(); } catch(SQLException e) {}
      }
      
      return cnt;
   }

   @Override
   public List<MemberVO> getAllMemberList() {
      List<MemberVO> memList = new ArrayList<>();
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "select * from mymember";
         stmt = conn.createStatement();
         
         rs = stmt.executeQuery(sql);
         
         while(rs.next()) {
            MemberVO memVo = new MemberVO();
            String memId = rs.getString("mem_id");
             String memPass = rs.getString("mem_pass");
             String memName = rs.getString("mem_name");
             String memTel = rs.getString("mem_tel");
             String memAddr = rs.getString("mem_addr");
             memVo.setMem_id(memId);
             memVo.setMem_pass(memPass);
             memVo.setMem_name(memName);
             memVo.setMem_tel(memTel);
             memVo.setMem_addr(memAddr);
             
             memList.add(memVo);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally { 
         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
         if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
         if(conn!=null) try { conn.close(); } catch(SQLException e) {}
      }
      
      return memList;
      
      /*
      List<MemberVO> list = new ArrayList<MemberVO>();
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      try {
         conn = DBUtil3.getConnection();
         String sql = "select * from mymember";
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         
         while(rs.next()) {
            String memId = rs.getString("mem_id");
             String memPass = rs.getString("mem_pass");
             String memName = rs.getString("mem_name");
             String memTel = rs.getString("mem_tel");
             String memAddr = rs.getString("mem_addr");
             list.add(new MemberVO(memId, memPass, memName, memTel, memAddr));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally { 
         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
         if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
         if(conn!=null) try { conn.close(); } catch(SQLException e) {}
      }
      
      return list;
      */
   }

   @Override
   public int getMemberCount(String memId) {
      int cnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "select count(*) cnt from mymember where mem_id = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memId);
            
         rs = pstmt.executeQuery();
            
         if(rs.next()) {
             cnt = rs.getInt("cnt");
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally { 
         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try { conn.close(); } catch(SQLException e) {}
      }
      
      return cnt;
   }

   @Override
   public int memberUpdate2(Map<String, String> paramMap) {
      //  Key값 정보 ==> 회원ID(memId), 수정할컬럼명(field), 수정할데이터(data)
      int cnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "update mymember set " + paramMap.get("field") + " = ? "
                  + " where mem_id= ?";
            
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, paramMap.get("data"));
         pstmt.setString(2, paramMap.get("memId"));
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try { conn.close(); } catch(SQLException e) {}
      }
      
      return cnt;
   }
}

   /*
   @Override
   public int updateMember2(String memId, String updateField, String updateData) {
      int cnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "update mymember set " + updateField + " = ? "
                  + " where mem_id= ?";
            
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, updateData);
         pstmt.setString(2, memId);
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try { conn.close(); } catch(SQLException e) {}
      }
      
      return cnt;
   }
   */

