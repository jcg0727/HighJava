package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardService {
	/**
	 * JdbvBoardVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 * @param boardVO DB에 insert할 자료가 저장된 JdbvBoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertBoard(JdbcBoardVO boardVO);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글을 삭제하는 메서드
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 하나의 JdbvBoardVO자료를 이용하여 DB에 update하는 메서드
	 * @param boardVO update할 게시글 정보가 저장된 JdbvBoardVo객체
	 * @return 작업성공 : 1 , 작업실패 : 0
	 */
	public int updateBoard(JdbcBoardVO boardVO);
	
	/**
	 * DB의 jdbv_board테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return JdbvBoardVo객체를 담고 있는 List 객체
	 */
	public List<JdbcBoardVO> getALLBoardList();
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 가져와 반환하는 메서드
	 * @param boardNo 가져올 게시글 번호
	 * @return 게시글번호에 맞는 자료가 있으면 해당 게시글정보를 담고 있는 JdbcBoardVO객체
	 * 			, 자료가 없으면 null 반환 
	 * 	
	 */
	public JdbcBoardVO getBoard(int boardNo);
	
	/**
	 * 게시글 제목을 이용하여 게시글을 검색하는 메서드
	 * @param title 검색할 게시글의 제목
	 * @return 검색된 결과를 담은 List 객체 
	 */
	public List<JdbcBoardVO> getSearchBoardList(String title);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글의 조회수를 증가시키느 메서드
	 * @param boardNo 조회수를 증가할 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int setCountIncrement(int boardNo);
}
