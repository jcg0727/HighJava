package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

//JDBC드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class이다.

// dbinfo.properties파일의 내용을 읽어서 설정하는 방법

// 방법2) ResourceBundle 객체 이용하기
public class DBUtil3 {
	static final Logger logger = Logger.getLogger(DBUtil3.class); // 로그 기록을 남기기 위해서 가장 먼저 해야 함
	
	
	static ResourceBundle bundle; //ResourceBundle 객체 변수 선언
	


	static {	//static 초기화 블럭
			bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
			
			logger.info("properties파일을 이용한 ResourceBundle객체 생성");
			
			
			try {
				Class.forName(bundle.getString("driver"));
				logger.info("DB 드라이버 로딩 성공!!");
			} catch (ClassNotFoundException e) {
				//System.out.println("드라이버 로딩 실패~~~~");
				logger.error("드라이버 로딩 실패~~ : " + e);
				e.printStackTrace();
			}
		

	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			//return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "JCG92" , "java");
			conn =  DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			logger.info("DB 시스템에 연결 성공");
			return conn;
		} catch (SQLException e) {
			//System.out.println("오라클 DB 연결 실패~~~");
			logger.error("DB 시스템에 견결 실패~~ : " + e);
			return null;
		}
	}
	
	
}
