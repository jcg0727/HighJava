package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 송신용, 수신용 패킷 객체변수 선언
		DatagramPacket inpacket, outpacket;

		// 수신받은 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];
		
		try {
			//소켓 객체 생성
			DatagramSocket socket = new DatagramSocket();
			
			//접속할 곳의 주소 정보 구하기 (지금은 서버의 주소 정보 구하기)
			InetAddress address = InetAddress.getByName("127.0.0.1");
			
			while (true) {
				// 전송할 메시지 읻ㅂ력
				System.out.print("보낼 메시지 입력 : ");
				String msg = scan.nextLine();
				
				//전송할 패킷객체 생성
				outpacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, 8888);
				
				//전송
				socket.send(outpacket);
				
				if ("/end".equals(msg)) { // 메시지 전송 중지 확인하기
					break;
				}
				//---------------------------------------------------------
				//서버에서온 데이터를 받아서 출력하가ㅣ
				//수신용 패킷객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				//수신하기
				socket.receive(inpacket);
				
				System.out.println("서버의 응답 메시지 " + new String(bMsg, 0, inpacket.getLength()));
				System.out.println();
				
			}
			System.out.println("통신 끝..");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
