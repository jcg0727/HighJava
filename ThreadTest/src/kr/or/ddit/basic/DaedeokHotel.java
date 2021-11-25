package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DaedeokHotel {
	private HashMap<Integer, Room> hotelMap;
	private Scanner scan;
	
	//생성자
	public DaedeokHotel() {
		hotelMap = new HashMap<>();
		scan = new Scanner(System.in);
		
		//객실 초기화
		for (int i = 2; i <=4; i++) {
			String roomType = null;
			switch(i) {
			case 2 : 
				roomType = "싱글룸";
				break;
			case 3 : 
				roomType = "더블룸";
				break;
			case 4 : 
				roomType = "스위트룸";
				break;
			}
			for (int j = 1; j<=9; j++) {
				int roomNumber = i*100+j;
				Room room = new Room(roomNumber, roomType);
				hotelMap.put(roomNumber, room);
			}
		}
	}
	
	
	public static void main(String[] args) {
		new DaedeokHotel().hotelStart();
	}

	private void hotelStart() {
		System.out.println();	
		System.out.println("************************************");
		System.out.println("호텔문을 열었습니다. 어서오십시오.");
		System.out.println("************************************");
		System.out.println();
		
		while (true) {
			int choice = displayMenu();
			switch(choice) {
			case 1:  //체크인
				checkIn();
				break;
			case 2:  //체크아웃
				CheckOut();
				break;
			case 3:  //객실 상태
				showRoom();
				break;
			case 4:  //업무종료
				System.out.println("************************************");
				System.out.println("호텔문을 닫았습니다.");
				System.out.println("************************************");
				return;
			default:
				System.out.println("잘못입력했습니다.");
			}
		}
		
}
	//객실 상태를 출력하는 메서드
	private void showRoom() {
		System.out.println();
		
		//방번호를 순서대로 나오게 하기 위해서 방번호를 list 에 넣은 후 정렬한다.
		ArrayList<Integer> roomNumList = new ArrayList<>(hotelMap.keySet());
		Collections.sort(roomNumList); //방번호의 오름차순 정렬
		System.out.println("-------------------------------");
		System.out.println("현재 객실 상태");
		System.out.println("-------------------------------");
		System.out.println("방번호    방종류    투숙객이름");
		System.out.println("-------------------------------");
		
		//List 에서 방번호를 하나씩 꺼내와서 Map의 키값중 해당 방번호에  해당하는
		// Room객체를 구해서 출력한다.
		for (int roomNum : roomNumList) {
			Room r = hotelMap.get(roomNum);
			System.out.print(roomNum + "\t" + r.getRoomType() + "\t" );
			String name = " -";
			if(r.getGuestName()!=null) {
				name = r.getGuestName();
			}
			System.out.println(name);
			System.out.println();
	}
	}	
	
	//체크 아웃을 하는 메서드
	private void CheckOut() {
		System.out.println("----------------------------------");
		System.out.println("체크아웃 작업");
		System.out.println("----------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >>");
		int num = scan.nextInt();
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
		}else if (hotelMap.get(num).getGuestName()==null) {
			System.out.println(num + "호 객실에는 체크인한 손님이 없습니다.");
		}else {
			//체크아웃작업은 해당 객실의 손님이름을 null로 변경하면 된다.
			
			//현재 손님 이름 구하기
			String name = hotelMap.get(num).getGuestName();
			hotelMap.get(num).setGuestName(null); //손님 이름을 null로 변경
			
			System.out.println(num + "호 객실의" + name + "님이 체크아웃을 완료"); 
		}
	}
	
	
	
	
	//체크인하는 메서드
	private void checkIn() {
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("체크인 작업");
		System.out.println("------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("------------------------------");
		System.out.print("방 번호 입력 >>");
	
		 int num = scan.nextInt();
		 
		 //입력한 방 번호가 Map의 키값에 없으면 잘못 입력한 방번호이다.
		 if (!hotelMap.containsKey(num)) {
			 System.out.println(num + "호 객실은 존재하지 않습니다.");
		 }else if(hotelMap.get(num).getGuestName()!=null) { //해당객실에 다른손님이 있는지
			 System.out.println(num + "호 객실에는 이미 손님이 있습니다.");
		 }else {
			 //체크인 작업 수행하기
			 System.out.println("누구를 체크인 하시겠습니까?");
			 System.out.println("이름입력>> ");
			 String name = scan.next();
			 
			 //입력받은 투숙객 이름을 해당 객실의 투숙객 명단에 넣는다.
			 hotelMap.get(num).setGuestName(name);
			 System.out.println(name + "씨가" + num + "호 객실에 체크인 되었습니다.");
		 }
	}
	
	
	//메뉴를 출력하고 선택한 작업 번호를 반환하는 메서드
	private int displayMenu(){
		System.out.println("-------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실상태 4. 업무종료");
		System.out.println("-------------------------------------------");
		System.out.print("선택>> ");
		int num = scan.nextInt();
		return num;
	}
	
	
}



class Room{
	private int roomNumber;  //방번호
	private String roomType; //방종류
	private String guestName; //손님이름
	
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