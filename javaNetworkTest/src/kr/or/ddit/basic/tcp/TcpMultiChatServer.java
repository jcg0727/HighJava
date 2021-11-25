package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// 서버에 접속한 Socket정보를 저장할 Map 객체 변수 선언
	//    ==> key 값 : 접속한 사람 이름, value값 : 접속한 Socket 객체
	private Map<String, Socket> clientMap;
	
	
	
	
	//생성자
	public TcpMultiChatServer() {
		//clientMap을 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	
	
	
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}
	
	
	
	
	private void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다...");
			
			while(true) {
				socket = server.accept();  //클라이언트의 접속을 기다린다.
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속했습니다.");
			
				//서버용 쓰레드 객체를 생성한 후 실행시킨다.
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(server !=null) try {server.close();}catch(IOException e) {}
		}
				
	}
	
	
	
	
	//clientMap에 저장된 전체 사용자에게 메시지를 전송하는 메서드
		private void sendToAll(String msg) {
			//clientMap의 데이터 개수만큼 반복
			for (String name : clientMap.keySet()) {
				try {
					DataOutputStream dos = new DataOutputStream(
							clientMap.get(name).getOutputStream()
							);
					dos.writeUTF(msg);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		} //sendToAl()메서드 끝...
	
		//서버에서 클라이언트로 메시지를 전송하는 Thread를 만든다.(Inner Class로 작성)
		class ServerReceiver extends Thread{
			private Socket socket;
			private DataInputStream dis;
			private DataOutputStream dos;
			
			//생성자
			public ServerReceiver(Socket socket) {
				this.socket = socket;
				try {
					//수신용
					dis = new DataInputStream(socket.getInputStream());
					
					//송신용
					dos = new DataOutputStream(socket.getOutputStream());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}	 // 생성자 끝..
			
			@Override
			public void run() {
				String name = "";
				try {
					while(true) {
						// 클라이언트가 접속 후 최초로 보낸 데이터는 사용자의 이름이다.
						// 이 이름이 중복되는지 여부를 검사해서 그 결과 feedBack으로 
						// 클라이언트에게 보내준다.
						name = dis.readUTF();
						if(clientMap.containsKey(name)) { // 이름이 중복 되면..
							dos.writeUTF("이름중복");
						}else { //이름이 중복되지 않으면...
							dos.writeUTF("OK");
							break;
						}
					}//while문 끝...

					//전체 사용자에게 새로운 참여자의 입장에 대한 메시지를 전송한다.
					sendToAll("[" + name + "]님이 대화방에 입장했습니다.");
					
					//사용자 이름과 클라이언트의 Socket 객체를 Map에 저장한다.
					clientMap.put(name, socket);
					
					System.out.println("현재 서버 접속자 수 : " + clientMap.size() + "명");
					
					//한 클라이언트가 보낸 메시지를 받아서 전체 클라이언트에게 보낸다.
					while(dis != null) {
						sendToAll(dis.readUTF());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					//이 finally 영역시 실행된다는 것은 해당 클라이언트가 접속을 종료했다는 의미이다.
					sendToAll("[" + name + "]님이 대화방을 나갔습니다");
					
					//사용자 목록에서 삭제
					clientMap.remove(name);
					
					System.out.println("현재 서버 접속자 수 : " + clientMap.size() + "명");
				}
			}
		}
}



