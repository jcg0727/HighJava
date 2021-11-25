package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		//InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		//www.naver.com의 IP정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");

		System.out.println("Host Name : " + naverIp.getHostName());
		System.out.println("Host Address : " + naverIp.getHostAddress());
		System.out.println("toStirng : " + naverIp.toString());
		
		System.out.println("---------------------------------------------------------");
		
		// 자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("Host Name : " + localIp.getHostName());
		System.out.println("Host Address : " + localIp.getHostAddress());
		System.out.println("toString : " + localIp.toString());
		System.out.println("---------------------------------------------------------");
	
		
		//IP주소가 여러개인 Host의 정보 가져오기
		InetAddress[] ips = InetAddress.getAllByName("www.naver.com");
		for (InetAddress ip : ips) {
			System.out.println(ip.toString());
		}
	
	
	
	
	
	
	
	
	
	}

}
