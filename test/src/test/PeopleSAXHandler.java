package test;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class PeopleSAXHandler extends DefaultHandler { // DefaultHandler를 상속받는 클래스 작성

	// 파싱한 사람 객체를 넣을 리스트
	private List<Person> personList;
	
	//파싱한 사람 객체
	private Person person;
	
	//character 메소드에서 저장할 문자열 변수
	private String str;
	
	//파싱한 사람 객체를 넣을 리스트를 ArrayList로 초기화
	public PeopleSAXHandler() {
		 personList = new ArrayList<>();
	}
	
	//시작 태그를 만났을 때 발생하는 이벤트
	public void startElement(String ur, String localName, String name, Attributes att) { //Attributes는 요소의 모든 속성에 접근하게 해준다
		if(name.equals("person")) {
			person = new Person();
			personList.add(person);
		}
	}
	
	//닫힌 태그를 만났을 때 발생하는 이벤트
	public void endElement(String url, String localName, String name) {
		if(name.equals("name")) {
			person.setName(str);
		}else if(name.equals("hp")) {
			person.setHp(str);
		}else if(name.equals("addr")) {
			person.setAddr(str);
		}
	}
	
	// 태그와 태그 사이의 text를 처리하기 위한 이벤트
	// XML의 태그가 아닌 데이터를 알려주는 메서드
	// 데이터 내용은 ch의 start 인덱스에서 length만큼의 문자에 해당
	public void characters(char[] ch, int start, int length) {
		str = new String(ch,start,length);
	}
	
	 public List<Person> getPersonList(){
			return personList;
	}
	 
	public void setPersonList(List<Person> personList) {
		this.personList=personList;
	}

}
