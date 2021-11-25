package collectionTest;

import java.util.HashMap;
import java.util.Scanner;

public class hotel {

	private HashMap<Integer, Room> hotelMap;
	private Scanner scan;
	
	
	public hotel() {
		hotelMap = new HashMap<>();
		
		scan = new Scanner(System.in);
		
		
		for(int i=2; i<=4; i++) {
			String roomType = null;
			switch(i) {
			case 2:
				roomType = "싱글룸";
				break;
			
			case 3:
				roomType = "더블룸";
				break;
				
			case 4:
				roomType = "스위트룸";
				break;
			}
			
			for(int j=1; j<=9; j++) {
				int roomNumber = i * 100 + j;
				
				Room room = new Room(roomNumber, roomType);
				
				hotelMap.put(roomNumber, room);
			}
			
		}
	} //생성자
	
	
	public static void main(String[] args) {
		new hotel().hotelStart();
	}
	
	private void hotelStart() {
		System.out.println();
		System.out.println("==============");
		System.out.println("어서오세요");
		System.out.println("==============");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1:
				checkIn();
				break;
				
			case 2:
				checkOut();
				break;
			case 4:
				System.out.println("종료");
				break;
			default :
				System.out.println("잘못입력");
			}
		}
		
	}//Start
	
	private void checkOut() {
		System.out.println();
		System.out.print("방번호 입력 >>");
		int num = scan.nextInt();
		
		if(!hotelMap.containsKey(num)) {
			System.out.println("객실 존재 X");
		}else if(hotelMap.get(num).getGuestName() == null) {
			System.out.println("체크인x");
		}else {
			
		}
		
		hotelMap.remove(num);
		System.out.println("체크아웃 완료");
		
	}
	
	
	
	
	private void checkIn() {
		System.out.println();
		System.out.println("체크인");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("==================================");
		System.out.print("방 번호 입력>>");
		int num = scan.nextInt();
		
		if(!hotelMap.containsKey(num)) {
			System.out.println("객실 존재하지 않습니다");
			return;
		}else if(hotelMap.get(num).getGuestName()!=null) {
			System.out.println("이미 예약됨");
		}else {
			System.out.print("누구 \n 이름>>");
			String name = scan.next();
			
			hotelMap.get(num).setGuestName(name);
			System.out.println("완료");
		}
	}
	
	
	
	private int displayMenu() {
		System.out.println("-----------------------------------------------------------");

		System.out.println("어떤 업무를 하시겠습니까?");

		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");

		System.out.println("-----------------------------------------------------------");

		System.out.print("선택>> ");

		int num = scan.nextInt();

		return num;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} //hotel




class Room{
	private int roomNumber;
	
	private String roomType;
	
	private String guestName;

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

} //room
