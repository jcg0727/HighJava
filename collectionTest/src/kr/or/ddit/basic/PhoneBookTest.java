package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/*- 추가사항 )
 * 
 * 1) 6.전화번호 저장 메뉴를 추가하고 저장하는 기능을구현한다.
 *    저장 파일명 : phoneData.dat 으로한다.
 * 2)프로그램이 시작될대 저장된 파일이 있으면 그 파일의 데이터를 읽어와서 Map에 저장한다.
 * 
 * 3)프로그램을 종료할 때 Map의 데이터가 변경되거나, 추가 또는 삭제되면
 * 변경된 데이터를 저장한 후 종료되도록 한다.
 * (변경된 부분이 하나도 없으면 그냥 종료한다.)
 * 생성자에서 저장해보자;;;
 * 
 * 6번,종료할때 저장
 * 
 *
 * -------------------
   1. 전화번호 등록
   2. 전화번호 수정
   3. 전화번호 삭제
   4. 전화번호 검색
   5. 전화번호 전체 출력
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
   0. 프로그램 종료
  ==================
  번호입력 >> 0
     
 프로그램을 종료합니다.
 
 * 
 */
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PhoneBookTest {
   private Map<String, Phone> phoneBookMap;
   private Scanner scan;
   static int check = 0;
   
   
   // 생성자
   public PhoneBookTest() {
      scan = new Scanner(System.in);
      phoneBookMap = new HashMap<>();
   }

   public static void main(String[] args) {
      new PhoneBookTest().phoneStart();
   }

   // 프로그램이 시작되는 메서드
   private void phoneStart() {
      System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
      System.out.println(" 전  화  번  호  관  리  프  로  그  램");
      System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
      callPhone();

      while (true) {
         int choice = displayMenu();
         switch (choice) {
         case 1: // 등록
            insert();
            break;
         case 2: // 수정
            update();
            break;
         case 3: // 삭제
            delete();
            break;
         case 4: // 검색
            search();
            break;
         case 5: // 전체출력
            phoneBookDisplayAll();
            break;
         case 6: // 저장하는 프로그램
            savePhone();
            break;
         case 7: // xml-dom 으로 외부에 저장
            forSavexmldom();
            break;
         case 8: // xml-dom 불러와 읽어오기
            showXML();
            break;
         case 0: // 프로그램 종료
           autosave();
            System.out.println("프로그램을 종료합니다.");
            check = 0;
            return;
         default:
            System.out.println("작업 번호를 잘못 입력했습니다.");
            System.out.println("다시 입력하세요.");
         }
      }

   }
   //전화번호 정보를 검색하는 메서드
   private void search() {
      System.out.println();
      System.out.println("검색할 전화번호 정보를 입력하세요");
      System.out.print("이 름 >> ");
      String name = scan.next();
      
      if(!phoneBookMap.containsKey(name)) {
         System.out.println(name + "씨의 전화번호 정보가 없습니다.");
         return;
      }   
      //해당 자료가 있으면Phone객체를 구해온다.
      Phone p = phoneBookMap.get(name);
      
      System.out.println(name + "씨의 전화번호 정보");
      System.out.println("--------------------");
      System.out.println(" 이  름 : " + p.getName());
      System.out.println(" 전화번호 : " + p.getTel());
      System.out.println(" 주  소 : " + p.getAddr());
      System.out.println("--------------------");
      System.out.println("검색 완료...");
      
      
      }
      
   
   //전화번호 정보를 삭제하는 메서드
   private void delete() {
      System.out.println();
      System.out.println("삭제할 전화번호 정보를 입력하세요.");
      System.out.print("이름 >> ");
      String name = scan.next();
      
      if(!phoneBookMap.containsKey(name)) {
         System.out.println(name + "씨의 전화번호 정보가 없습니다.");
         System.out.println("삭제 작업을 마칩니다.");
         return;
      }
      // 삭제 작업 수행
      phoneBookMap.remove(name);
      
      System.out.println(name + "씨의 전화번호 정보를 삭제했습니다");
      check++;
      
      
      
   }
   
   //전화번호 정보를 수정하는 메서드
   private void update() {
      System.out.println();
      System.out.println("수정할 전화번호 정보를 입력하세요.");
      
      System.out.println("이 름 >> ");
      String name = scan.next();
      
      // 거꾸로 생가갛며 데이터가 없으면 안되는 것
      // 수정할 전화번호 정보가 있는지 여부를 검사한다.
      if(!phoneBookMap.containsKey(name)) { //수정할 전화번호가 존재하지 않으면
         System.out.println(name + "씨의 전화번호 정보가 없습니다.");
         System.out.println("수정 작업을 마칩니다...");
         return;
      }
      System.out.print("새로운 전화번호 >> ");
      String newTel = scan.next();

      System.out.print("새로운 즈소 >> ");
      String newAddr = scan.next();
      
      // 수장방법 1 //수정작업 진행 ==> 같은 키값에 새로운 전화번호 정보를 저장한다.
//      phoneBookMap.put(name, new Phone(name, newTel, newAddr));
      // 수정방법 2
      Phone p = phoneBookMap.get(name); //객체를 사용하면 참조값(번지)를 가지고 있다고 생각혀라
      p.setTel(newTel);
      p.setAddr(newAddr);
      
      check++;
      
      
      System.out.println(name + "씨의 전화번호 정보 수정 완료!!");
      
   }
   

   // 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
   private int displayMenu() {
      System.out.println();
      System.out.println("----------------------");
      System.out.println("1.전화번호 등록");
      System.out.println("2.전화번호 수정");
      System.out.println("3.전화번호 삭제");
      System.out.println("4.전화번호 검색");
      System.out.println("5.전화번호 전체출력");
      System.out.println("6.전화번호 저장");
      System.out.println("7.전화번호 xml-dom으로 저장");
      System.out.println("8.저장된 xmldom파일 콘솔에 출력");
      System.out.println("0.프로그램 종료");
      System.out.print("선택할 번호를 입력하세요 > ");
      int num = scan.nextInt();
      return num;
   }

   // 새로운 전화번호 정보를 등록하는 메서드
   private void insert() {
      System.out.println();
      System.out.println("새롭게 등록할 전화번호를 입력하세요.");
      System.out.print("이 름 >> ");
      String name = scan.next();

      // 이미 등록된 사람인지 여부 검사
      if (phoneBookMap.containsKey(name) == true) { // 있으면 true, 없으면 false
         System.out.println(name + "씨는 이미 등록된 사람입니다.");
         return;
      }
      // 입력받는 작업
      System.out.print("전화번호 >> ");
      String tel = scan.next();

      System.out.print("주소 >> ");
      String addr = scan.next();

//      //Phone객체(인스턴스) 생성
//      Phone p = new Phone(name, tel, addr);
//      
//      //Map에 추가하기
//      phoneBookMap.put(name, p);

      // 위에 2개를 이렇게 해도 됨
      phoneBookMap.put(name, new Phone(name, tel, addr));

      System.out.println("'" + name + "'" + " 전화번호 등록완료!");
      check++;
   }
   //전화번호를 저장하는 메소드
   private void savePhone() {
      try {
      FileOutputStream fout = new FileOutputStream("d:/d_other/phoneData.dat");
      BufferedOutputStream bout = new BufferedOutputStream(fout);
      ObjectOutputStream oos = new ObjectOutputStream(bout);
      
      //쓰기작업
      System.out.println("전화번호 저장하기 시작...");
      
      
      Set<String> phonekey = phoneBookMap.keySet();
      if(phonekey.size() == 0) {
         System.out.println("전화번호정보가 하나도 없습니다.");
      }else {
         int cnt = 0;
         Iterator<String> phoneIt = phonekey.iterator();
         while(phoneIt.hasNext()) {
            cnt++;
            String name = phoneIt.next();
            oos.close();
            Phone p = phoneBookMap.get(name);
            oos.writeObject(p);
         }
      }
      
      System.out.println("저장 작업 끝....");
      
      oos.close();
      } catch (IOException e) {
      e.printStackTrace();
      }
      
   }
   
   //전화번호 종료시 변동사항 있으면 자동으로 저장하는 메소드
   private void autosave() {
      if(check == 0) {
         System.out.println("변동사항이 없습니다.");
      }else {
         System.out.println("변동사항이 있습니다. 자동으로 저장합니다.");
         try {
            FileOutputStream fout = new FileOutputStream("d:/d_other/phoneData.dat");
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            ObjectOutputStream oos = new ObjectOutputStream(bout);
            
            //쓰기작업
            System.out.println("전화번호 저장하기 시작...");
            
            
            Set<String> phonekey = phoneBookMap.keySet();
            if(phonekey.size() == 0) {
               System.out.println("전화번호정보가 하나도 없습니다.");
            }else {
               int cnt = 0;
               Iterator<String> phoneIt = phonekey.iterator();
               while(phoneIt.hasNext()) {
                  cnt++;
                  String name = phoneIt.next();
                  Phone p = phoneBookMap.get(name);
                  oos.writeObject(p);
               }
            }
            
            System.out.println("저장 작업 끝....");
            
            } catch (IOException e) {
            e.printStackTrace();
            }
      }
      
   }
   
   private void callPhone() {
   try {
      FileInputStream fin = new FileInputStream("d:/d_other/phoneData.dat");
      BufferedInputStream bin = new BufferedInputStream(fin);
      ObjectInputStream ois = new ObjectInputStream(bin);
      
      Object obj;
      
      try {
         System.out.println("객체 읽기 작업 시작...");
         
         while((obj = ois.readObject()) != null) {
            Phone phone = (Phone)obj;
            phoneBookMap.put(phone.getName(), new Phone(phone.getName(),phone.getTel(),phone.getAddr()));
         }
         
      }catch(IOException e) {
         System.out.println("불러오기 완료");
      }catch(ClassNotFoundException e) {
         
      }finally {
         ois.close();
      }
      
   } catch (IOException e) {
   
   }
      
   }
   
   // 전체 전화번호 정보를 출력하는 메서드
   private void phoneBookDisplayAll() {
      System.out.println();
      
      // 키값들만 구한다.
      Set<String> phoneKey = phoneBookMap.keySet();
      
      System.out.println("---------------------------------");
      System.out.println(" 번호      이름      전화번호     주소");
      System.out.println("---------------------------------");
      if(phoneKey.size() == 0) {
         System.out.println(" 등록된 전화번호 정보가 하나도 없습니다. ");
      }else {
         int cnt = 0; //번호가 저장될 변수
         Iterator<String> phoneIt = phoneKey.iterator();
         while(phoneIt.hasNext()) {
            cnt++; //번호 증가
            String name = phoneIt.next(); // 키값 (즉, 이름) 구하기
            Phone p = phoneBookMap.get(name); // 키값을 이용하여 Phone객체 구하기
            System.out.println(cnt + "\t" + p.getName() + "\t" + p.getTel() + "\t" +p.getAddr());
         }
      }
      System.out.println("---------------------------------");
      System.out.println("출력 끝...");
   }
   
   // forsavexmldom 메소드 이용하여 phonebook의 데이터를 넣는 메소드 필요없음!!
   /*
    * private void createXMLDOM() {
    * Set<String> phoneKey = phoneBookMap.keySet(); if(phoneKey.size() == 0) {
    * System.out.println("등록된 전화번호가 하나도 없습니다."); }else { Iterator<String>phoneIt =
    * phoneKey.iterator(); while(phoneIt.hasNext()) { String name = phoneIt.next();
    * // 키값 구하기 Phone p = phoneBookMap.get(name); forSavexmldom(p.getName(),
    * p.getTel(), p.getAddr()); }
    * System.out.println("전화번호를 xmlDom파일로 저장 완료했습니다..."); } }
    */
   
   //xml-dom으로 저장하는 메소드
   private void forSavexmldom() { 
      
      try {
         //저장을 하기위한 객체 생성
         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
         Document doc = docBuilder.newDocument();
         
         //루트 엘리먼트
         Element rootElement = doc.createElement("phonebook");
         doc.appendChild(rootElement);
         
         Set<String> phoneKey = phoneBookMap.keySet();
         if(phoneKey.size() == 0) {
            System.out.println("등록된 전화번호가 하나도 없습니다.");
         }else {
            Iterator<String>phoneIt = phoneKey.iterator();
            for (int i = 0; i < phoneBookMap.size(); i++) { 
               String names = phoneIt.next(); // 키값 구하기
               Phone p = phoneBookMap.get(names);
               String name1 = p.getName();
               String tel1 = p.getTel();
               String addr1 = p.getAddr();
               //phonelist 엘리먼트
               Element phonelist = doc.createElement("phonelist");
               rootElement.appendChild(phonelist);
               
               //속성값 정의 -> 패스
               //name 엘리먼트
               Element name = doc.createElement("name");
               name.appendChild(doc.createTextNode(name1));
               phonelist.appendChild(name);
               
               //tel 엘리먼트
               Element tel = doc.createElement("tel");
               tel.appendChild(doc.createTextNode(tel1));
               phonelist.appendChild(tel);
               
               //addr 엘리먼트
               Element addr = doc.createElement("addr");
               addr.appendChild(doc.createTextNode(addr1));
               phonelist.appendChild(addr);
            }
         }
         
         /*
          * for (int i = 0; i < phoneBookMap.size(); i++) { //phonelist 엘리먼트 Element
          * phonelist = doc.createElement("phonelist");
          * rootElement.appendChild(phonelist);
          * 
          * //속성값 정의 -> 패스 //name 엘리먼트 Element name = doc.createElement("name");
          * name.appendChild(doc.createTextNode(name1)); phonelist.appendChild(name);
          * 
          * //tel 엘리먼트 Element tel = doc.createElement("tel");
          * tel.appendChild(doc.createTextNode(tel1)); phonelist.appendChild(tel);
          * 
          * //addr 엘리먼트 Element addr = doc.createElement("addr");
          * addr.appendChild(doc.createTextNode(addr1)); phonelist.appendChild(addr); }
          */
         
         //xml파일로 쓰기
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         File file = new File("d:/새폴더");
         if(!file.exists()) {
            file.mkdirs();//폴더가없으면 만든다.
         }
         StreamResult result = new StreamResult(new FileOutputStream(new File(file + "/phonebookxmldom.xml")));
         
         transformer.transform(source, result);
         System.out.println("전화번호를 xmlDom파일로 저장 완료했습니다...");

         
   } catch (ParserConfigurationException e) {
      e.printStackTrace();
   } catch (TransformerConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   } catch (TransformerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
   } // xml-dom으로 저장하는 메소드 종료
   
   //xml-dom파싱으로 콘솔에 출력하는 메소드
   
   private void showXML() {
      try {
      File fxmlFile = new File("d:/새폴더/phonebookxmldom.xml");
      
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fxmlFile);
      
      doc.getDocumentElement().normalize();
      
      //실행확인
      //System.out.println("RootElement : " + doc.getDocumentElement().getNodeName());
      //루트 바로 밑 노드 확인
      //System.out.println(doc.getDocumentElement().getFirstChild().getNodeName());
      
      //System.out.println(doc.getDocumentElement().getFirstChild().getFirstChild().getNodeName());
      //System.out.println(doc.getDocumentElement().getFirstChild().getFirstChild().getNextSibling().getNodeName());
      //System.out.println(doc.getDocumentElement().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNodeName());
      
      
      //NodeList nList = doc.getElementsByTagName(doc.getDocumentElement().getFirstChild().getNodeName());
      NodeList nList = doc.getElementsByTagName("phonelist");
      for(int temp = 0; temp < nList.getLength(); temp++) {
         Node nNode = nList.item(temp);
         
         if(nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) nNode;
            System.out.println(doc.getDocumentElement().getFirstChild().getFirstChild().getNodeName() + " : " +element.getElementsByTagName(doc.getDocumentElement().getFirstChild().getFirstChild().getNodeName()).item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(doc.getDocumentElement().getFirstChild().getFirstChild().getNextSibling().getNodeName() + " : " +element.getElementsByTagName(doc.getDocumentElement().getFirstChild().getFirstChild().getNextSibling().getNodeName()).item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(doc.getDocumentElement().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNodeName() + " : " +element.getElementsByTagName(doc.getDocumentElement().getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNodeName()).item(0).getChildNodes().item(0).getNodeValue());
           }
      }
      
   } catch (ParserConfigurationException e) {
      e.printStackTrace();
   } catch (SAXException e) { //DOM타입으로 읽는데 왜 SAXException 오류가나죠? ㅠㅠㅠ
      // TODO Auto-generated catch block
      e.printStackTrace();
   } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
   }
   
}
/*
 * 전화번호 정보를 저장하기 위한 VO 클래스
 */


class Phone implements Serializable {
   private String name; // 이름
   private String tel; // 전화번호
   private String addr; // 주소
   
   // 생성자
   public Phone(String name, String tel, String addr) {
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
   
   @Override
   public String toString() {
      return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
   }
   
}


//선생님한테 질문할 껏
//DOM타입으로 읽는데 왜 SAXException 오류가나죠? ㅠㅠㅠ 553줄 하나씩읽으니 dom파서 방식이 맞는건가요??
//Iterator.hasnext 말고 for (int i = 0; i < map.size() ; i++} 로 해도되나요?
//함수의 매개변수가 함수안 변수의 변수명과 같을땐어떻게하나요 this를 못쓰는대;;
