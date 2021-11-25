package kr.or.ddit.board.service;

import java.security.GeneralSecurityException;
import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDAO;
import kr.or.ddit.board.dao.JdbcBoardDAOImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService{
	private IJdbcBoardDAO dao;
	
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDAOImpl.getInstance();
	}
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) service = new JdbcBoardServiceImpl();
		return service;
	}
	
	
	@Override
	public int insertBoard(JdbcBoardVO boardVO) {
		return dao.insertBoard(boardVO);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVO) {
		return dao.updateBoard(boardVO);
	}

	@Override
	public List<JdbcBoardVO> getALLBoardList() {
		return dao.getALLBoardList();
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		//게시글 번호에 해당하는 데이터들을 가져오기 전에 조회수를 증가 시키고
		//조회수 증가가 성공되면 게시글 데이터들을 가져온다.
		int cnt = setCountIncrement(boardNo);
		if(cnt>0) { //조회수 증가 성공 후
			return dao.getBoard(boardNo);			
		}else { //조회수 증가 실패 후 
			return null;
		}
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		return dao.getSearchBoardList(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

	
	
	
}	
	/*private static JdbcBoardServiceImpl service;
	
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) {
			service = new JdbcBoardServiceImpl();
		}
		return service;
	}
	
	private IJdbcBoardDAO boardDao;
	
	private JdbcBoardServiceImpl() {
		boardDao = JdbcBoardDAOImpl.getInstance();
	}
	
	@Override
	public int insertBoard(JdbcBoardVO boardVO) {
		return boardDao.insertBoard(boardVO);
	}

	@Override
	public List<JdbcBoardVO> getALLBoardList() {
		return boardDao.getALLBoardList();
	}

	@Override
	public int searchBoard(String title) {
		return boardDao.searchBoard(title);
	}*/


