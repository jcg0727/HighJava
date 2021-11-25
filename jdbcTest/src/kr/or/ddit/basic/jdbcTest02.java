package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
   문제1 : 사용자로부터 Lprod_id 값을 입력받아 입력한 값보다 Lprod_id 값이 큰 자료를 출력하시오.
   
   문제2 : Lprod_id값을 2개 입력 받아서 두 값중 작은값부터 큰값 사이의 자료들을 출력하시오
    		(새로운클래스에서 작업)
 */
public class jdbcTest02 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Lprod_id값 입력 : ");
		int num = scanner.nextInt();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JCG92", "java");
			
			String sql = "select * from lprod where lprod_id >" +  num;
			
			stmt = conn.createStatement();
		
			rs = stmt.executeQuery(sql);
			System.out.println("==결과출력==");
			while(rs.next()) {	
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("---------------------------------------");
			} 
			System.out.println("출력 끝...");
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			//5. 사용했던 자원 반납
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
			
	}
}
