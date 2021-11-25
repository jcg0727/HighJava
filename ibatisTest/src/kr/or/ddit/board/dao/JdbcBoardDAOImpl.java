package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.util.SqlMapClientFactory;

public class JdbcBoardDAOImpl implements IJdbcBoardDAO {
   
   private static JdbcBoardDAOImpl dao;
   
   private SqlMapClient smc;
   
   private JdbcBoardDAOImpl() { 
	   smc = SqlMapClientFactory.getSqlMapClient();
   }
   
   public static JdbcBoardDAOImpl getInstance() {
      if(dao==null) dao = new JdbcBoardDAOImpl();
      return dao;
   }
   
 
   

   @Override
   public int insertBoard(JdbcBoardVO boardVo) {
      int cnt = 0;
      try {
         Object obj = smc.insert("board.insertBoard" , boardVo);
         if(obj==null) {
        	 cnt = 1;
         }
         
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      } 
      return cnt;
   }

   
   
   @Override
   public int deleteBoard(int boardNo) {
      int cnt = 0;
      try {
         cnt = smc.delete("board.deleteBoard" , boardNo);
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      }
      return cnt;
   }

   @Override
   public int updateBoard(JdbcBoardVO boardVo) {
      int cnt = 0;  
      try {
         cnt = smc.update("board.updateBoard" , boardVo);
         
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      }
      return cnt;
   }

   @Override
   public List<JdbcBoardVO> getALLBoardList() {
      List<JdbcBoardVO> boardList = new ArrayList<>();
      try {
         boardList = smc.queryForList("board.getALLBoard");
       
      } catch (SQLException e) {
         boardList = null;
         e.printStackTrace();
      } 
      return boardList;
   }

@Override
public JdbcBoardVO getBoard(int boardNo) {
	JdbcBoardVO jbvo = null;
	try {
		jbvo = (JdbcBoardVO) smc.queryForObject("board.getBoard", boardNo);
	} catch (SQLException e) {
		jbvo = null;
		e.printStackTrace();
	}
	return jbvo;
}

@Override
public List<JdbcBoardVO> getSearchBoardList(String title) {
	List<JdbcBoardVO> boardList = new ArrayList<>();
	try {
		boardList = smc.queryForList("board.getSearchBoard", title);
	} catch (SQLException e) {
		boardList = null;
		e.printStackTrace();
	}
	return boardList;
}

@Override
public int setCountIncrement(int boardNo) {
	int cnt = 0;
	try {
		cnt = smc.update("board.setCountBoard" , boardNo);
	} catch (SQLException e) {
		cnt = 0;
		e.printStackTrace();
	}
	return cnt;
}

   
}