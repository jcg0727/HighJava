package kr.or.ddit.basic;
/*
  문제2) Lprod_id값을 2개 입력 받아서 두 값중 작은값부터 큰값 사이의
         자료들을 출력하시오.
 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcTest03 {
   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.println("첫번째 Lprod_id값 입력 : ");
      int num1 = scan.nextInt();
      System.out.println("두번째 Lprod_id값 입력 : ");
      int num2 = scan.nextInt();
      
      int max, min;
      if(num1 > num2) {
         max = num1;
         min = num2;
      } else {
         min = num1;
         max = num2;
      }

      // DB 작업에 필요한 변수 선언
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");

         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "LWG92", "java");
         String sql = "select * from lprod where lprod_id >= " + min + " and lprod_id <= " + max ;

         stmt = conn.createStatement();

         rs = stmt.executeQuery(sql);

         System.out.println("  == 결과 출력 ==");
         while (rs.next()) {
            System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
            System.out.println("Lprod_gu : " + rs.getString("lprod_gu"));
            System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
            System.out.println("--------------------------------");
         }
         System.out.println("출력 끝...");

      } catch (SQLException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } finally {
         // 5. 사용했던 자원 반납
         if (rs != null)
            try {
               rs.close();
            } catch (SQLException e) {
            }
         if (stmt != null)
            try {
               stmt.close();
            } catch (SQLException e) {
            }
         if (conn != null)
            try {
               conn.close();
            } catch (SQLException e) {
            }
      }

   }
}