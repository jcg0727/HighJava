package kr.or.ddit.basic;

 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

 

public class DaedeokHotel {

	private HashMap<Integer, Room> hotelMap;

	private Scanner scan;
	static int check = 0;
	

	// 생성자

	public DaedeokHotel() {

		hotelMap = new HashMap<>();

		scan = new Scanner(System.in);

		

		// 객실 초기화

		for(int i=2; i<=4; i++) {

			String roomType = null;

			switch(i) {

				case 2 : roomType = "싱글룸"; break;

				case 3 : roomType = "더블룸"; break;

				case 4 : roomType = "스위트룸"; break;

			}

			

			 for(int j=1; j<=9; j++) {

				 int roomNumber = i * 100 + j;

				 Room room = new Room(roomNumber, roomType);

				 hotelMap.put(roomNumber, room);

			 }

			

		}

	}

	

	

	public static void main(String[] args) {

		new DaedeokHotel().hotelStart();

	}

	

	// 시작 메서드

	private void hotelStart() {

		System.out.println();

		System.out.println("**************************************");

		System.out.println("       호텔문을 열었습니다. 어서오세요.");

		System.out.println("**************************************");
		callHotel();
		System.out.println();

		

		while(true) {

			int choice = displayMenu();

			switch(choice) {

				case 1 : // 체크인

					checkIn(); break;

				case 2 : // 체크아웃

					checkOut(); break;

				case 3 : // 객실 상태

					showRoom();	break;
					
				case 4:	
					saveHotel();
					break;

				case 0 : // 업무 종료
					autosave();
					System.out.println("****************************");

					System.out.println("       호텔문을 닫았습니다.");

					System.out.println("****************************");

					return;

				default : 

					System.out.println("잘못 입력했습니다.");

			}

		}

		
		
		

	}
	
	
	//불러오기
	private void callHotel() {
		try {
		    FileInputStream fin = new FileInputStream("D:/d_other/hotel.dat");
		    BufferedInputStream bin = new BufferedInputStream(fin);
		    ObjectInputStream ois = new ObjectInputStream(bin);
		    
		    Object obj;
		    
		    try {
				System.out.println("객체 읽기 작업 시작");
				
				while((obj = ois.readObject()) !=null) {
					Room room = (Room)obj;
					hotelMap.put(room.getRoomNumber(), new Room(room.getRoomNumber(), room.getRoomType()));
				}
			} catch (IOException e) {
				System.out.println("불러오기 완료");
			} catch(ClassNotFoundException e) {
		    
			} finally {
				ois.close();
			}
		    
		    
		}catch(IOException e) {
			
		}
	}
	
	
	//자동저장 메서드
	private void autosave() {
		try {
			FileOutputStream fout = new FileOutputStream("D:/d_other/hotel.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oos = new ObjectOutputStream(bout);
			
			//쓰기작업
			System.out.println("호텔객실정보 저장하기 시작");
			
			Set<Integer> hotelkey = hotelMap.keySet();
			if(hotelkey.size()==0) {
				System.out.println("객실 정보가 없습니다.");
			}else {
				int cnt = 0;
				Iterator<Integer> hotelIt = hotelkey.iterator();
				while(hotelIt.hasNext()) {
					cnt++;
					Integer next = hotelIt.next();
					Room r = hotelMap.get(next);
					oos.writeObject(r);
					
					
				}
			}
			System.out.println("저장 끝");
			
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//객실 정보를 저장하는 메서드
	private void saveHotel() {
		try {
			FileOutputStream fout = new FileOutputStream("D:/d_other/hotel.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oos = new ObjectOutputStream(bout);
			
			//쓰기작업
			System.out.println("호텔객실정보 저장하기 시작");
			
//			Set<Integer> hotelkey = hotelMap.keySet();
//			if(hotelkey.size()==0) {
//				System.out.println("객실 정보가 없습니다.");
//			}else {
//				int cnt = 0;
//				Iterator<Integer> hotelIt = hotelkey.iterator();
//				while(hotelIt.hasNext()) {
//					cnt++;
//					Integer next = hotelIt.next();
//					Room r = hotelMap.get(next);
//					oos.writeObject(r);
//					
//					
//				}
//			}
			System.out.println("저장 끝");
			
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

	// 체크 아웃을 진행하는 메서드

	private void checkOut() {

		System.out.println();

		System.out.println("----------------------------------------------");

		System.out.println("   체크아웃 작업");

		System.out.println("----------------------------------------------");

		System.out.println("체크아웃 할 방 번호를 입력하세요.");

		System.out.print("방번호 입력 >> ");

		int num = scan.nextInt();

		

		// 입력한 방번호가 Map의 키값에 없으면 잘못 입력한 방번호이다.

		if(!hotelMap.containsKey(num)) {

			System.out.println(num + "호 객실은 존재하지 않습니다.");

		}else if(hotelMap.get(num).getGuestName()==null) { // 해당 객실에 손님이 없는 경우

			System.out.println(num + "호 객실에는 체크인 한 손님이 없습니다.");

		}else {

			// 체크 아웃 작업은 해당 객실의 손님 이름을 null로 변경하면 된다.

			

			// 현재 손님 이름 구하기

			String name = hotelMap.get(num).getGuestName();

			

			hotelMap.get(num).setGuestName(null); // 손님 이름을 null로 변경하기

			

			System.out.println(num + "호 객실의 " + name + "님이 체크아웃을 완료했습니다.");

		}

		

	}

	

	

	// 객실 상태를 출력하는 메서드

	private void showRoom() {

		System.out.println();

	

		// 방번호를 순서대로나오게 하기 위해서 방번호를 List에 넣은 후 정렬한다.

		ArrayList<Integer> roomNumList = new ArrayList<>(hotelMap.keySet());

		Collections.sort(roomNumList); // 방번호의 오름차순 정렬

		

		System.out.println("----------------------------------------------");

		System.out.println("                   현재 객실 상태");

		System.out.println("----------------------------------------------");

		System.out.println("방 번호	 방 종류	 투숙객 이름");

		System.out.println("----------------------------------------------");

		

		// List에서 방번호를 하나씩 꺼내와서 Map의 키값중 해당 방번호에 해당하는

		// Room객체를 구해서 출력한다.

		for(int roomNum : roomNumList) {

			Room r = hotelMap.get(roomNum);

			System.out.print(roomNum + "\t" + r.getRoomType() + "\t");

			String name = " -";

			if(r.getGuestName()!=null) {

				name = r.getGuestName();

			}

			System.out.println(name);

		}

		System.out.println("----------------------------------------------");

		System.out.println();

		

			

	}

	

	// 체크인하는 메서드

	private void checkIn() {

		System.out.println();

		System.out.println("----------------------------------------------");

		System.out.println("   체크인 작업");

		System.out.println("----------------------------------------------");

		System.out.println(" * 201~209 : 싱글룸");

		System.out.println(" * 301~309 : 더블룸");

		System.out.println(" * 401~409 : 스위트룸");

		System.out.println("----------------------------------------------");

		System.out.print("방 번호 입력 >> ");

		int num = scan.nextInt();

		

		// 입력한 방번호가 Map의 키값에 없으면 잘못 입력한 방번호이다.

		if(!hotelMap.containsKey(num)) {

			System.out.println(num + "호 객실은 존재하지 않습니다.");

		}else if(hotelMap.get(num).getGuestName()!=null) { // 해당 객실에 다른 손님이 있는지 검사

			System.out.println(num + "호 객실에는 이미 손님이 있습니다.");

		}else {

			// 체크인 작업 수행하기

			System.out.println("누구를 체크인 하시겠습니까?");

			System.out.print("이름 입력 >> ");

			String name = scan.next();

			

			//입력 받은 투숙객 이름을 해당 객실의 투숙객 명단에 넣는다.

			hotelMap.get(num).setGuestName(name);

			System.out.println(name + "씨가 " + num + "호 객실에 체크인 되었습니다.");

		}

		

	}

	

	// 메뉴를 출력하고 선택한 작업 번호를 반환하는 메서드

	private int displayMenu() {

		System.out.println("-----------------------------------------------------------");

		System.out.println("어떤 업무를 하시겠습니까?");

		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 자동저장   0.업무종료");

		System.out.println("-----------------------------------------------------------");

		System.out.print("선택>> ");

		int num = scan.nextInt();

		return num;

	}

	

	

	

 

}

 

class Room implements Serializable{

	private int roomNumber;  	// 방번호

	private String roomType;	// 방종류

	private String guestName;	// 손님이름

	

	// 생성자

	public Room(int roomNumber, String roomType) {

		super();

		this.roomNumber = roomNumber;

		this.roomType = roomType;

	}

 

	public int getRoomNumber() {

		return roomNumber;

	}

 

	public void setRoomNumber(int roomNumber) {

		this.roomNumber = roomNumber;

	}

 

	public String getRoomType() {

		return roomType;

	}

 

	public void setRoomType(String roomType) {

		this.roomType = roomType;

	}

 

	public String getGuestName() {

		return guestName;

	}

 

	public void setGuestName(String guestName) {

		this.guestName = guestName;

	}

	

	

	

	

	

}