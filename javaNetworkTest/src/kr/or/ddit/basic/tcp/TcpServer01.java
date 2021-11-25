package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {
	public static void main(String[] args) throws IOException {
		//TCP 소켓 통신을 위해 ServerSocket객체를 생성한다.
		ServerSocket server = new ServerSocket(7777);
		System.out.println("접속을 기다립니다..");
		
		//accept()메서드
		//		==> 클라이언트에서 연결 요청이 올 때까지 계속 기다린다.
		//      ==> 클라이언트의 연결 요청이 오면 새로운 Socket객체를 생성해서 
		//			클라이언트의 Socket 과 연결한 후 반환한다.
		Socket socket = server.accept();
		
		// accept()메서드 이후의 내용은 연결이 완료되어야만 실행된다.
		// 이 메서드 이후에 서로 데이터를 주고 받는 명령을 기술하면 된다.
		System.out.println("클라이언트와 연결되었습니다...");
		System.out.println();
		
		System.out.println("접속한 상대방 컴퓨터의 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		
		System.out.println("상대방과 연결된 자신의 정보 ");
		System.out.println("IP주소 : " + socket.getLocalAddress());
		System.out.println("Port번호 : " + socket.getLocalPort());
		
		//클라이언트로 메시지 보내기
		//OutPutStream 객체를 구성해서 전송한다.
		// (Socket의 getOutPutStream()메서드를 이용한다.)
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		//클라이언트로 메시지 전송하기
		dos.writeUTF("환영합니다. 어서오세요...");
		System.out.println("메시지를 보냇습니다...");
				
		//스트림과 소켓 닫기
		dos.close();
		socket.close();
		server.close();
		
	}
}
