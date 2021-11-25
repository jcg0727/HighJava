package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest2 {

	//iBatis를 이용하여 DB자료를 처리하는 순서 및 방법
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		
		try {
			//값을 불러오기 위한 환경셋팅
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/config/sqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			//-------------------------------------------------------------------------------------
			
					
			//LPROD_ID는 현재의 LPROD_ID 중 제일 큰 값보다 1크게 한다.
			int max = (int) smc.queryForObject("lprod.getMaxId");
			
			
			
			
			
			//입력받은 상품분류코드(lprod_gu)가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			String gu; // 상품분류 코드가 저장될 변수 선언
			int count = 0; // 입력한 상품분류 코드의 개수가 저장될 변수 선언		
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 : ");
				gu = scan.next();
				
				count= (int) smc.queryForObject("lprod.getCountLprodGu" , gu);
				

				if(count>0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(count>0);
				
			//-----------------------------------------------------------------------------------------------
			System.out.print("상품 분류명(LPROD_NM) 입력 : ");
			String nm = scan.next();
			
			LprodVO lpvo = new LprodVO();
			lpvo.setLprod_gu(gu);
			lpvo.setLprod_id(max);
			lpvo.setLprod_nm(nm);
			
			Object obj = smc.insert("lprod.insertLprod", lpvo);
		
			
			if(obj==null) {
				System.out.println("등록성공");
			}else {
				System.out.println("등록실패");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
