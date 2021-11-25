package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	/*
	 	Map==> key 값과 value값을 한 쌍으로 관리하는 객체 
	 	  - key 값 : 중복을 허용하지 않고 순서가 없다.
	 	  -	value값 : 중복을 허용한다.
	*/
	
	
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		
		// 자료 추가 : put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전시");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map => " + map);
		
		//자료 수정 : 데이터를 추가할 때 key값을 기존의 데이터와 같게 하면 나중에 추가한 값이 저장된다.
		map.put("name", "이순신");
		System.out.println("map => " + map);
		
		//자료 삭제 : remove(key값) => key값이 같은 자료를 찾아 삭제한다.
		//			  반환값 : 삭제된 자료의 value값이 반환된다.
		System.out.println("-----------------------------");
		String delTel = map.remove("tel");
		System.out.println("delTel : " + delTel);
		System.out.println("map => " + map);
		
		//자료 읽기 : get(key값) ==> key값과 짝이 되는 value값을 반환한다.
		System.out.println("이름 : " + map.get("name"));
		System.out.println();
		
		// key값이 존재하는지 여부를 나타내는 메서드 : containsKey(찾을key값)
		// ==> '찾을key값'이 있으면 true, 없으면 false
		System.out.println("tel 키값의 존재 여부 : " + map.containsKey("tel")); 
		System.out.println("addr 키값의 존재 여부 : " + map.containsKey("addr")); 
		System.out.println("---------------------------------------------------");
		
		//Map에 저장된 모든 key값을 읽어와 Map 데이터를 처리하는 방법
		
		//방법1 : keySet()메서드 이용하기
		//          ==> Map의 모든 Key값들을 읽어와 Set형으로 반환한다.
		Set<String> keyset = map.keySet();
		Iterator<String> it = keyset.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("===========================================");
		
		//방법2 : keySet을 향상된 for문으로 처리하기
		
		for (String key : map.keySet()) {
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("===========================================");
		
		//방법3 : value값만 일겅와 처리하기 ==> values()메서드를 이용한다.
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("===========================================");
		
		/*
		 방법 4 : Map에는 Entry라는 내부class가 만들어져 있다.
		 		 이 Entry  클래스는 key와 value 라는 멤버변수로 구성되어 있다.
		 		 Map에서는 이 Entry클래스를 Set 형식으로 저장하여 관리한다.
		 - Entry 객체 전체를 가져오는 메서드 : enterySet() 메서드
		    ==> 가져온 Entry객체들은 Set 형식으로 되어있다. 		 	
		*/
		Set<Map.Entry<String, String>> mapEntry = map.entrySet();
		Iterator<Map.Entry<String, String>> entryIt = mapEntry.iterator();
		
		while(entryIt.hasNext()) {
			//Entry객체 1개 구하기
			Map.Entry<String, String> entry = entryIt.next();
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}
		System.out.println("===========================================");
	}
}
