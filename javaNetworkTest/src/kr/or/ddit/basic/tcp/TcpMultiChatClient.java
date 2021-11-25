package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {
	
	
	
	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();
	}
	
	
	private void clientStart() {
		Socket socket = null;
		
		try {
			socket = new Socket("192.168.45.27", 7777);
			System.out.println("서버에 연결되었습니다");
			System.out.println();
			
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	} //clientStart()메서드 끝...
	
	// -------------------------------------------------쓰레드 작성
	
	// 메시지 전송용 쓰레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		private String name;
		private Scanner scan;
		
		public ClientSender(Socket socket) {
			this.socket = socket;
			this.scan = new Scanner(System.in);
			
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				if(dos!=null) {
					// 처음 접속이 되면 자신의 대화명을 입력 받아 서버로 전송하고
					// 대화명의 중복여부를 feedBack으로 받아서 확인한다.
					System.out.print("대화명 : ");
					String name = scan.nextLine();
					while(true) {
						dos.writeUTF(name);  //대화명 전송
						
						//대화명의 중복 여부를 feedBack으로 받는다.
						String feedBack = dis.readUTF();
						if ("이름중복".equals(feedBack)) { // 대화명이 중복될 때...
							System.out.println(name + "은 대화명이 중복됩니다.");
							System.out.println("다른 대화명을 입력하세요.");
							System.out.print("대화명 : ");
							name = scan.nextLine();
						}else { // 중복되지 않을때
							this.name = name;
							System.out.println(name + " 이름으로 대화방에 입장했습니다.");
							break; // 반복문 탈출
						}	
					}//whlie 문 끝
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} //생성자 끝..
		
		@Override
		public void run() {
			try {
				while(dos !=null) {
					//키보드로 입력한 메시지를 서버로 전송한다.
					dos.writeUTF("[" + name + "]" + scan.nextLine());
				}	
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	} //전송용 쓰레드 끝...
	
		//메시지 수신용 쓰레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		
		//생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		} //생성자 긑...
		
		@Override
		public void run() {
			try {
				while(dis!=null) {
					//서버로부터 받은 메시지를 화면에 출력한다.
					System.out.println(dis.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
}
