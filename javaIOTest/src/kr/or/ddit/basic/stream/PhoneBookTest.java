package kr.or.ddit.basic.stream;

 

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.io.Serializable;

import java.util.HashMap;

import java.util.Iterator;

import java.util.Map;

import java.util.Scanner;

import java.util.Set;

 

/*

	문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고,

		 Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.

		 

		 이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력 기능이 있다.

		 (데이터는 Map에 저장하여 관리하는데  key값으로는 '이름'을 사용하고

		  value값으로는 'Phone클래스의 인스턴스'로 한다.)

		 

		HashMap<String, Phone> 변수명 = new ....

	

	- 추가 사항)

		1) '6. 전화번호 저장'메뉴를 추가하고 저장하는 기능을 구현한다.

			(저장 파일명 : 'phoneData.dat'로 한다.)

		2) 이 프로그램이 시작될 때 저장된 파일이 있으면 그 파일의 데이터을 읽어와서

		   Map에 저장한다.

		3) 프로그램을 종료할 때 Map의 데이터가 변경되거나, 추가 또는 삭제되면 

		     변경된 데이터를 저장한 후 종료되도록 한다.

		   (변경된 부분이 하나도 없으면 그냥 종료한다.)

		

	

	실행예시)

	  -------------------

		1. 전화번호 등록

		2. 전화번호 수정

		3. 전화번호 삭제

		4. 전화번호 검색

		5. 전화번호 전체 출력

		6. 전화번호 저장

		0. 프로그램 종료

	  ==================

	  번호입력 >> 1

	  

	 새롭게 등록할 전화번호 정보를 입력하세요.

	 이 름 >> 홍길동

	 전화번호 >> 010-1234-5678

	 주 소 >> 대전시

	 

	 '홍길동' 전화번호 등록 완료!!

 

	  -------------------

		1. 전화번호 등록

		2. 전화번호 수정

		3. 전화번호 삭제

		4. 전화번호 검색

		5. 전화번호 전체 출력

		6. 전화번호 저장

		0. 프로그램 종료

	  ==================

	  번호입력 >> 1	 

	 

	 새롭게 등록할 전화번호 정보를 입력하세요.

	 이 름 >> 홍길동

	 

	'홍길동'은 이미 등록된 사람입니다.

	

	  -------------------

		1. 전화번호 등록

		2. 전화번호 수정

		3. 전화번호 삭제

		4. 전화번호 검색

		5. 전화번호 전체 출력

		6. 전화번호 저장

		0. 프로그램 종료

	  ==================

	  번호입력 >> 5

	  

	---------------------------------------

	 번호     이 름         전화번호               주소  	 	  

	---------------------------------------

	 1       홍길동       010-1234-567  대전시

	 ~~~

	---------------------------------------

	출력 완료...

	

	  -------------------

		1. 전화번호 등록

		2. 전화번호 수정

		3. 전화번호 삭제

		4. 전화번호 검색

		5. 전화번호 전체 출력

		6. 전화번호 저장

		0. 프로그램 종료

	  ==================

	  번호입력 >> 0

	  	

	 프로그램을 종료합니다.

	 

*/

public class PhoneBookTest {

	private Map<String, Phone> phoneBookMap;

	private Scanner scan;

	private String fileName = "d:/d_other/phoneData.xml";

	private boolean dataChange;  // 데이터의 변경 여부를 나타내는 변수 (변경되면 true, 그렇지 않으면 false)

	

	// 생성자

	public PhoneBookTest() {

		scan = new Scanner(System.in);

		//phoneBookMap = new HashMap<>();

		phoneBookMap = load();

		if(phoneBookMap==null) {

			phoneBookMap = new HashMap<>();

		}

	}

	

	public static void main(String[] args) {

		new PhoneBookTest().phoneStart();

	}

	

	// 프로그램이 시작되는 메서드

	private void phoneStart() {

		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

		System.out.println("     전 화 번 호 관 리 프 로 그 램");

		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

		System.out.println();

		

		while(true) {

			int choice = displayMenu();

			switch(choice) {

				case 1 :	// 등록 

					insert(); break;

				case 2 : 	// 수정

					update(); break;

				case 3 : 	// 삭제

					delete(); break;

				case 4 : 	// 검색

					search(); break;

				case 5 : 	// 전체 출력

					phoneBookDisplayAll(); break;

				case 6 : 	// 저장

					save(); break;

				case 0 :	// 프로그램 종료

					if(dataChange==true) { // 데이터의 변경 여부를 확인한다.

						save();

					}

					System.out.println("프로그램을 종료합니다.");

					return;

				default : System.out.println("작업 번호를 잘못 입력했습니다.");

						System.out.println("다시 입력하세요.");

			}

		}

		

	}

	

	// 전화번호 정보가 저장된 파일을 읽어오는 메서드(읽어온 Map객체를 반환한다.)

	private Map<String, Phone> load(){

		// 읽어온 데이터가 저장될 변수 선언

		HashMap<String, Phone> pMap = null;

		

		File file = new File(fileName);

		if(!file.exists()) { // 파일이 없으면...

			return null;

		}

		

		// 저장된 파일이 있으면...

		ObjectInputStream ois = null;

		try {

			// 객체 입력용 스트림 객체 생성

			ois = new ObjectInputStream(

					new BufferedInputStream(

						new FileInputStream(file)

					)

				);

			// 객체를 읽어서 변수에 저장한다.

			pMap = (HashMap<String, Phone>) ois.readObject();

			

		} catch (IOException e) {

			return null;

		} catch (ClassNotFoundException e) {

			//e.printStackTrace();

			return null;

		} finally {

			if(ois!=null) try { ois.close(); }catch(IOException e) {}

		}

		return pMap;  // 읽어온 Map객체 반환

		

	}

	

	

	// 전화번호 정보를 파일로 저장하는 메서드

	private void save() {

		ObjectOutputStream oos = null;

		try {

			// 객체 출력용 스트림 객체 생성하기

			oos = new ObjectOutputStream(

					new BufferedOutputStream(

						new FileOutputStream(fileName)

					)

				);

			

			// Map객체를 파일로 저장한다.

			oos.writeObject(phoneBookMap);

			oos.flush();

			System.out.println("저장이 완료되었습니다.");

			dataChange = false;

			

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			// 스트림 객체 닫기

			if(oos!=null) try{ oos.close(); }catch(IOException e) {}

		}

	}

	

	

	// 전화번호 정보를 검색하는 메서드

	private void search() {

		System.out.println();

		System.out.println("검색할 전호번호 정보를 입력하세요.");

		System.out.print("이 름 >> ");

		String name = scan.next();

		

		if(!phoneBookMap.containsKey(name)) {

			System.out.println(name + "씨의 전화번호 정보가 없습니다.");

			return;

		}

		

		// 해당 자료가 있으면 Phone객체를 구해온다.

		Phone p = phoneBookMap.get(name);

		

		System.out.println(name + "씨의 전화번호 정보");

		System.out.println("------------------------");

		System.out.println(" 이     름 : " + p.getName());

		System.out.println(" 전화번호 : " + p.getTel());

		System.out.println(" 주     소 : " + p.getAddr());

		System.out.println("------------------------");

		System.out.println("검색 완료...");

		

	}

	

	

	// 전화번호 정보를 삭제하는 메서드

	private void delete() {

		System.out.println();

		System.out.println("삭제할 전화번호 정보를 입력하세요.");

		System.out.print("이 름 >> ");

		String name = scan.next();

		

		if(!phoneBookMap.containsKey(name)) {

			System.out.println(name + "씨의 전화번호 정보가 없습니다.");

			System.out.println("삭제 작업을 마칩니다.");

			return;

		}

		

		// 삭제 작업 수행

		phoneBookMap.remove(name);

		

		System.out.println(name + "씨의 전화번호 정보를 삭제했습니다.");

		dataChange = true;

		

	}

	

	

	// 전화번호 정보를 수정하는 메서드

	private void update() {

		System.out.println();

		System.out.println("수정할 전화번호 정보를 입력하세요.");

		

		System.out.print("이 름 >> ");

		String name = scan.next();

		

		// 수정할 전화번호 정보가 있는지 여부를 검사한다.

		if(!phoneBookMap.containsKey(name)) {

			System.out.println(name + "씨의 전화번호 정보가 없습니다.");

			System.out.println("수정 작업을 마칩니다...");

			return;

		}

		

		System.out.print("새로운 전화번호 >> ");

		String newTel = scan.next();

		

		scan.nextLine();  // 입력버퍼 비우기

		System.out.print("새로운 주소 >> ");

		String newAddr = scan.nextLine();

		

		// 수정작업 진행 ==> 같은 키값에 새로운 전화번호 정보를 저장한다.

		// 방법1

		//phoneBookMap.put(name, new Phone(name, newTel, newAddr));

		

		// 방법2

		Phone p = phoneBookMap.get(name);

		p.setTel(newTel);

		p.setAddr(newAddr);

		

		System.out.println(name + "씨의 전화번호 정보 수정 완료!!");

		dataChange = true;

		

	}

	

	

	// 메뉴를 출력하고 작업 번호를 입력 받아 반환하는 메서드

	private int displayMenu() {

		System.out.println();

		System.out.println("-------------------");

		System.out.println(" 1. 전화번호 등록      " );

		System.out.println(" 2. 전화번호 수정      " );

		System.out.println(" 3. 전화번호 삭제      " );

		System.out.println(" 4. 전화번호 검색      " );

		System.out.println(" 5. 전화번호 전체 출력   " );

		System.out.println(" 6. 전화번호 저장   " );

		System.out.println(" 0. 프로그램 종료      " );

	    System.out.println("================" );

	    System.out.print("번호입력 >> ");

	    int num = scan.nextInt();

	    return num;

	}

	

	// 새로운 전화번호 정보를 등록하는 메서드

	private void insert() {

		System.out.println();

		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");

		System.out.print("이 름 >> ");

		String name = scan.next();

		

		// 이미 등록된 사람인지 여부 검사

		if( phoneBookMap.containsKey(name) ) {

			System.out.println(name + "씨는 이미 등록된 사람입니다.");

			return;

		}

		

		System.out.print("전화번호 >> ");

		String tel = scan.next();

		

		scan.nextLine();  // 입력버퍼 비우기

		System.out.print("주 소 >> ");

		String addr = scan.nextLine();

		

		/*

		// Phone객체(인스턴스) 생성

		Phone p = new Phone(name, tel, addr);

		

		// Map에 추가하기

		phoneBookMap.put(name, p);

		*/

		

		phoneBookMap.put(name, new Phone(name, tel, addr));

		

		System.out.println("'" + name + "' 전화번호 등록 완료!!");

		dataChange = true;

		

	}

	

	// 전체 전화번호 정보를 출력하는 메서드

	private void phoneBookDisplayAll() {

		System.out.println();

		

		// 키값들을 구한다.

		Set<String> phoneKey = phoneBookMap.keySet();

		

		System.out.println("---------------------------------------");

		System.out.println(" 번호        이  름           전화번호          주소");

		System.out.println("---------------------------------------");

		if(phoneKey.size() == 0) {

			System.out.println(" 등록된 전화번호 정보가 하나도 없습니다.");

		}else {

			int cnt = 0;  // 번호가 저장될 변수

			Iterator<String> phoneIt = phoneKey.iterator();

			while(phoneIt.hasNext()) {

				cnt++;   // 번호 증가

				String name = phoneIt.next();  // 키값 (즉, 이름) 구하기

				Phone p = phoneBookMap.get(name); // 키값을 이용하여 Phone객체 구하기

				System.out.println(cnt + "\t" + p.getName() + "\t" + 

							p.getTel() + "\t" + p.getAddr());

			}

		}

		System.out.println("---------------------------------------");

		System.out.println("출력 끝...");

		

	}

	

	

 

}

 

 

 

 

class Phone implements Serializable{

	private static final long serialVersionUID = 3325054451851774513L;

	

	private String name;

	private String tel;

	private String addr;

	

	// 생성자

	public Phone(String name, String tel, String addr) {

		super();

		this.name = name;

		this.tel = tel;

		this.addr = addr;

	}

	

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getTel() {

		return tel;

	}

	public void setTel(String tel) {

		this.tel = tel;

	}

	public String getAddr() {

		return addr;

	}

	public void setAddr(String addr) {

		this.addr = addr;

	}

	

	

}