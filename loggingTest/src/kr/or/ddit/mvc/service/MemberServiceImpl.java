package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private static MemberServiceImpl service;	
	
	public static MemberServiceImpl getInstance() {
		if(service==null) service = new MemberServiceImpl();
		return service;
	}
	
	private IMemberDao memDao;
	
	private MemberServiceImpl() {
		//memDao = new MemberDaoImpl();
		memDao = MemberDaoImpl.getInstance();
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		return memDao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return memDao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		return memDao.getMemberCount(memId);
	}

	@Override
	public int memberUpdate2(Map<String, String> paramMap) {
		return memDao.memberUpdate2(paramMap);
	}

}
