package kr.or.ddit.test;

public class TestServiceImpl implements ITestService {
	private ITestDAO dao;
	
	private static TestServiceImpl service;
	
	private TestServiceImpl() {
		dao = TestDAOImpl.getInstance();
	}
	public static TestServiceImpl getInstance() {
		if(service == null) {
			service = new TestServiceImpl();
		}
		return service;
	}
	
	
	@Override
	public int insertTest(TestVO testVO) {
		return dao.insertTest(testVO);
				
	}

}
