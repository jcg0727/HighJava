package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						"JCG92", "java");
			
			System.out.println("계좌번호 정보 추가하기");
			System.out.print("계좌번호 : ");
			String bankNo = scan.next();
			
			System.out.print("은 행 명 : ");
			String bankName = scan.next();
			
			System.out.print("예금주명 : ");
			String bankUser = scan.next();
			
			/*
			//statement 객체를 이용해서 데이터 추가하기
			String sql = "INSERT INTO BANKINFO (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
						  + "VALUES('" + bankNo + "','" + bankName + "', '" + bankUser + "', sysdate)";
			 stmt = conn.createStatement();
			 
			 // SQL문 실행하기
			 // 1) select문일 경우 : executeQuery() 메서드 사용
			 //			==> 반환값 : ResultSet객체 
			 // 2) select문이 아닐 경우 : exectueUpdate() 메서드 사용
			 //			==> 반환값 : 작업에 성공한 레코드 수
					
			 int cnt = stmt.executeUpdate(sql);
			*/
			
			
			// PreparedStatement 객체를 이용하여 처리하기

			// 쿼리문을 작성할 때 쿼리문에 데이터가 들어갈 자라에 물음표(?)를 넣어서 작성한다.
			String sql = "INSERT INTO BANKINFO (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
					  + "VALUES(?, ?, ?, sysdate)";
			
			//PreparedStatement 객체 생성
			//    ==> 사용할 쿼리문을 매개변수에 넘겨준다.
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문의 물음표(?) 자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표변호,데이터값);
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUser);
			
			// 데이터 셋팅이 완료되면 쿼리문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			
			 System.out.println("반환값 : " + cnt);
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
}
