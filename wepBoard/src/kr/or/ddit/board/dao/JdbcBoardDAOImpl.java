package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil3;

public class JdbcBoardDAOImpl implements IJdbcBoardDAO {
   
   private static JdbcBoardDAOImpl dao;
   
   private JdbcBoardDAOImpl() { }
   
   public static JdbcBoardDAOImpl getInstance() {
      if(dao==null) dao = new JdbcBoardDAOImpl();
      return dao;
   }
   
   // DB작업에 필요한 객체변수들 선언
   private Connection conn;
   private Statement stmt;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   // 사용했던 자원을 반납하는 메서드
   private void disConnect() {
      if(rs!=null) try { rs.close(); }catch(SQLException e) {}
      if(stmt!=null) try { stmt.close(); }catch(SQLException e) {}
      if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
      if(conn!=null) try { conn.close(); }catch(SQLException e) {}
   }
   

   @Override
   public int insertBoard(JdbcBoardVO boardVo) {
      int cnt = 0;
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "insert into jdbc_board (board_no, board_title, "
               + "board_writer, board_date, board_cnt, board_content) "
               + "values( board_seq.nextVal, ?, ?, sysdate, 0, ? )";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, boardVo.getBoard_title());
         pstmt.setString(2, boardVo.getBoard_writer());
         pstmt.setString(3, boardVo.getBoard_content());
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      } finally {
         disConnect();
      }
      
      return cnt;
   }

   @Override
   public int deleteBoard(int boardNo) {
      int cnt = 0;
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "delete from jdbc_board where board_no = ?";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, boardNo);
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      } finally {
         disConnect();
      }
      
      return cnt;
   }

   @Override
   public int updateBoard(JdbcBoardVO boardVo) {
      int cnt = 0;
      
      try {
         conn = DBUtil3.getConnection();
         String sql = "update jdbc_board set "
               + "board_title = ? ,"
               + "board_content = ?, "
               + "board_date = sysdate "
               + "where board_no = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, boardVo.getBoard_title());
         pstmt.setString(2, boardVo.getBoard_content());
         pstmt.setInt(1, boardVo.getBoard_no());
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      } finally {
         disConnect();
      }
      
      return cnt;
   }

   @Override
   public List<JdbcBoardVO> getALLBoardList() {
      List<JdbcBoardVO> boardList = new ArrayList<>();
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "select board_no, board_title, board_writer,"
               + "to_char(board_date, 'YYYY-MM-DD') board_date, "
               + "board_cnt, board_content "
               + "from jdbc_board "
               + "order by board_no desc ";
               
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         
         // 레코드 하나하나를 VO객체에 매핑하고 매핑된 VO객체를 List에 추가한다.
         while(rs.next()) {
            JdbcBoardVO boardVo = new JdbcBoardVO();
            boardVo.setBoard_no(rs.getInt("board_no"));
            boardVo.setBoard_title(rs.getString("board_title"));
            boardVo.setBoard_writer(rs.getString("board_writer"));
            boardVo.setBoard_date(rs.getString("board_date"));
            boardVo.setBoard_cnt(rs.getInt("board_cnt"));
            boardVo.setBoard_content(rs.getString("board_content"));
            
            boardList.add(boardVo);
            
         }
         
      } catch (SQLException e) {
         boardList = null;
         e.printStackTrace();
      } finally { 
         disConnect();
      }
      
      return boardList;
   }

   @Override
   public JdbcBoardVO getBoard(int boardNo) {
      JdbcBoardVO boardVo = null;
      try {
         conn = DBUtil3.getConnection();
         String sql =  "select board_no, board_title, board_writer,"
               + "to_char(board_date, 'YYYY-MM-DD') board_date, "
               + "board_cnt, board_content "
               + "from jdbc_board "
               + "where board_no = ? ";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, boardNo);
         
         rs = pstmt.executeQuery();
         if(rs.next()) {
            boardVo = new JdbcBoardVO();
            
            boardVo.setBoard_no(rs.getInt("board_no"));
            boardVo.setBoard_title(rs.getString("board_title"));
            boardVo.setBoard_writer(rs.getString("board_writer"));
            boardVo.setBoard_date(rs.getString("board_date"));
            boardVo.setBoard_cnt(rs.getInt("board_cnt"));
            boardVo.setBoard_content(rs.getString("board_content"));
         }
         
      } catch (SQLException e) {
         boardVo = null;
         e.printStackTrace();
      } finally {
         disConnect();
      }
      
      return boardVo;
   }

   @Override
   public List<JdbcBoardVO> getSearchBoardList(String title) {
      List<JdbcBoardVO> boardList = new ArrayList<>();
      if(title==null) {  // 검색할 제목이 없을 때 처리하기
         title = "";
      }
      
      try {
         conn = DBUtil3.getConnection();
         
         String sql = "select board_no, board_title, board_writer,"
               + "to_char(board_date, 'YYYY-MM-DD') board_date, "
               + "board_cnt, board_content "
               + "from jdbc_board "
               + "where board_title like '%' || ? || '%' " // ?가 작은 따옴표 안에 안들어가게 해야함
               + "order by board_no desc ";
               
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, title);
         
         rs = pstmt.executeQuery();
         
         // 레코드 하나하나를 VO객체에 매핑하고 매핑된 VO객체를 List에 추가한다.
         while(rs.next()) {
            JdbcBoardVO boardVo = new JdbcBoardVO();
            boardVo.setBoard_no(rs.getInt("board_no"));
            boardVo.setBoard_title(rs.getString("board_title"));
            boardVo.setBoard_writer(rs.getString("board_writer"));
            boardVo.setBoard_date(rs.getString("board_date"));
            boardVo.setBoard_cnt(rs.getInt("board_cnt"));
            boardVo.setBoard_content(rs.getString("board_content"));
            
            boardList.add(boardVo);
            
         }
         
      } catch (SQLException e) {
         boardList = null;
         e.printStackTrace();
      } finally { 
         disConnect();
      }
      
      return boardList;

   }

   @Override
   public int setCountIncrement(int boardNo) {
      int cnt = 0;
      try {
         conn = DBUtil3.getConnection();
         String sql = "update jdbc_board set "
               + " board_cnt = board_cnt + 1 "
               + " where board_no = ? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, boardNo);
         
         cnt = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         cnt = 0;
         e.printStackTrace();
      } finally {
         disConnect();
      }
      return cnt;
   }

}
	/*	private static JdbcBoardDAOImpl dao;
		
		private JdbcBoardDAOImpl() {
			
		}
		
		public static JdbcBoardDAOImpl getInstance() {
			if(dao==null) {
				dao = new JdbcBoardDAOImpl();
			}
			return dao;
		}

	@Override
	public int insertBoard(JdbcBoardVO boardVO) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board values (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, 0, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVO.getBoard_title());
			pstmt.setString(2, boardVO.getBoard_writer());
			pstmt.setString(3, boardVO.getBoard_content());
	
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getALLBoardList() {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				JdbcBoardVO boardVO = new JdbcBoardVO();
				
				int boNo = rs.getInt("board_no");
				String boTitle = rs.getString("board_title");
				String boWriter = rs.getString("board_writer");
				String boDate = rs.getString("board_date");
				int boCnt = rs.getInt("board_cnt");
				String boContent = rs.getString("board_content");
				
				boardVO.setBoard_no(boNo);
				boardVO.setBoard_title(boTitle);
				boardVO.setBoard_writer(boWriter);
				boardVO.setBoard_date(boDate);
				boardVO.setBoard_cnt(boCnt);
				boardVO.setBoard_content(boContent);
				
				boardList.add(boardVO);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { 
	         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
	         if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
	         if(conn!=null) try { conn.close(); } catch(SQLException e) {}
	      }
		return boardList;
	}


	@Override
	public int searchBoard(String title) {
	}*/

