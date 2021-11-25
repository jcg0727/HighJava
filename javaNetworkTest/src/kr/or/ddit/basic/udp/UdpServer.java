package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
  UDP방식 : 비연결 지향, 신뢰성이 없다, 데이터가 순서대로 도착한다는 보장이 없다.
  			그렇지만 TCP방식보다 속도가 빠르다.
  			
  - TCP 방식은 스트림을 이용해서 송수신하지만
    UDP방식은 데이터그램을 이용해서 송수신힌다.	
    
  - DatagramSocket 객체와 DatagramPacket 객체를 이용해서 통신한다.	
  	*DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다. (우체부)
  	*DatagramPacket : 주고 받는 데이터와 관련된 작업을 수행한다. (편지,소포)
  	  		==> 송신을 위한 생성자와 수신을 위한 생성자를 따로 제공한다.
*/

public class UdpServer {

	public static void main(String[] args) {
		try {
			//통신할 포트번호를 지정하여 소캣객체를 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			//수신용 패킷변수와 송신용 패킷변수 선언
			DatagramPacket inpacket, outpacket ;
			
			System.out.println("서버 실행중...\n");
			
			while(true) {
				//데이터가 저장될 byte형 배열 선언
				byte[] bMsg = new byte[512];
				
				//수신용 패킷객체 생성
				//  ==> 데이터가 저장될 byte형 배열, 배열의 길이를 생성자에 넣어서 생성한다.
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				
				
				// 데이터를 수신한다. ==>receive()메서드 이용
				// 이 메서드는 데이터가 올 때까지 기다린다.
				// 수신된 데이터의 패킷정보는 지정한 패킷변수에 저장된다.
				socket.receive(inpacket);
				
				//수신받은 패킷에서 상대방의 주소, 포트번호 등의 정보를 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대방의 IP 정보 : " + address);
				System.out.println("상대방의 port정보 : " + port);
				
				//상대방이 보낸 메시지 출력하기
				//inpacket.getLength() ==> 실제 읽어온 데이터의 길이를 반환
				//inpacket.getData(), 또는 수신용 패킷을 생성할 때 지정한 byte형 배열
				//		==> 실제 읽어온 데이터가 반환된다.
				
				//수신받은 데이터 (byte형 배열)를 문자열로 변환하기
				//String msg = new String(bMsg, 0, inpacket.getLength());
				String msg = new String(inpacket.getData(), 0, inpacket.getLength());
				
				 if ("/end".equals(msg)) {
		               break;
		            }

				System.out.println("상대방이 보낸 메시지 : " + msg);
				System.out.println("----------------------------------------------------");
				
				//---------------------------------------------------------------------------
				
				//상대방에게 메시지 보내기(수신받은 메시지를 그대로 송신하기)
				
				//송신할 메시지를 byte배열로 변환한다.
				byte[] sendMsg = msg.getBytes();
				
				//송신용 패킷객체 생성하기
				//  	==>생성자에 다음과 같은 정보를 지정해서 생성한다.
				// 		==>전송할 데이터가 저장된 byte 형 배열, 전송할 자료의 길이(배열의길이),
				//			상대방의 주소정보, 상대방의 포트번호
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				//송신하기 ==>send()메서드를 사용한다.
				socket.send(outpacket);
				System.out.println("송신완료...\n");
				
				
			}//while문 끝

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
