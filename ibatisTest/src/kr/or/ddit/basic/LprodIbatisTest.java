package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest {

	//iBatis를 이용하여 DB자료를 처리하는 순서 및 방법
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		try {
			//1. iBatis의 환경설정파일(sqlMapConfig.xml)을 읽어와 실행한다.
			//1-1. 문자 인코딩 케릭터셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			//1-2. 환경 설정 파일을 읽어온다.
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			
			//1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경 설정을 완성한 후
			// 		작성된 SQL문을 호출해서 처리할 객체를 생성한다.
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			
			//------------------------------------------------------------------------------------
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			
			/*// 2-1. insert 연습
			System.out.println("insert 작업 시작...");
			System.out.print("lprod_id 입력 : ");
			int lprodId = scan.nextInt();
			
			System.out.print("lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			System.out.print("lprod_nm 입력 : ");
			String lprodNm = scan.next();
			
			//1) 저장할 데이터를 VO객체에 저장한다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(lprodId);
			lvo.setLprod_gu(lprodGu);
			lvo.setLprod_nm(lprodNm);
			
			//2) SqlMapClient 객체변수를 이용해서 처리할 쿼리문을 호출하여 실행한다.
			// 형식) smc.insert("namespace 속성값.id속성값", 파라미터클래스);
			//		반환값 : insert 성공 : null, 실패 : 오류객체
			Object obj = smc.insert("Lprod.insertLprod", lvo);
			if(obj==null) {
				System.out.println("insert작업 성공");
			}else {
				System.out.println("insert작업 실패");
			}*/
			
			
			
			/*// 2-2 update 연습
			System.out.println("update 시작 ...");
			
			System.out.print("수정할 lprod_gu 입력 : ");
			String lprodGu2 = scan.next();
			
			System.out.print("새로운 lprod_id 입력 : ");
			int lprodId2 = scan.nextInt();
			
			System.out.print("새로운 lprod_nm 입력 : ");
			String lprodNm2 = scan.next();
			
			//1) 저장할 데이터를 VO객체에 저장한다.
			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_id(lprodId2);
			lvo2.setLprod_gu(lprodGu2);
			lvo2.setLprod_nm(lprodNm2);
			
			//2) 형식)smc.update("namespace속성값.id속성값", 파라미터클래스)
			//		반환값 : 작업에 성공한 레코드 수
			int cnt = smc.update("lprod.updateLprod", lvo2);
			if(cnt>0) {
				System.out.println("update 성공!!");
			}else {
				System.out.println("update 실패!!");
			}*/
			
			
			
			/*//2-3. delete 연습
			System.out.println("delete 시작 ...");		
			System.out.print("삭제할 lprod_gu 입력 : ");
			String lprodGu3 = scan.next();
			
			// 형식) smc.delete("namespace속성값.id속성값", 파라미터클래스);
			//		반환값 : 작업에 성공한 레코드 수 
			int cnt2 = smc.delete("lprod.deleteLprod" , lprodGu3);
			if(cnt2>0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}*/
			
			/*//2-4. select 연습
			System.out.println("select 연습 시작...");
			// 1)응답의 결과가 여러개의 레코드 일 경우
			System.out.println("1) 응답 결과가 여러개의 레코드일 경우");
			//형식) smc.queryForList("namespace속성값.id속성값",파라미터클래스);
			//    ==>queryForList()메서드는 여러개의 레코드 각각을 VO에 담은 후
			//		이 VO데이터를 List에 추가해 주는 작업을 자동으로 수행한다.
			List<LprodVO> lprodList = smc.queryForList("lprod.getAllLprod");
			for (LprodVO lpvo : lprodList) {
				System.out.println("ID : " + lpvo.getLprod_id());
				System.out.println("GU : " + lpvo.getLprod_gu());
				System.out.println("NM : " + lpvo.getLprod_nm());
				System.out.println("---------------------------");
			}
			System.out.println("출력 끝..");*/
			
			
			
			System.out.println("2) 응답 결과가 1개의 레코드일 경우");
			// 응답 결과가 1개의 레코드일 경우에 queryForObject()메서드를 사용한다.
			// 형식) smc.queryForObject("namespace속성값.id속성값", 파라미터클래스);
			// 		 반환값 : 쿼리결과가 여러개 일 경우 ==> 오류 Exception
			//				  1개일 경우 ==> 해당 객체 반환(정상)
			//				   없을 경우 ==> null값 반환
			System.out.print("검색할 Lprod_gu 입력 : ");
			String lprodGu4 = scan.next();
			
			LprodVO lpvo2 = (LprodVO) smc.queryForObject("lprod.getLprod", lprodGu4);
			if (lpvo2==null) {
				System.err.println("검색한 데이터가 하나도 없습니다.");
				return;
			}
			System.out.println("ID : " + lpvo2.getLprod_id());
			System.out.println("GU : " + lpvo2.getLprod_gu());
			System.out.println("NM : " + lpvo2.getLprod_nm());
			System.out.println("---------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}//main

}
