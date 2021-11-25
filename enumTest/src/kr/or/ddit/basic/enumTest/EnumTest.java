package kr.or.ddit.basic.enumTest;

/*
	enum(열거형) ==> 서로 관련 있는 상수들의 집합을 나타낸다.
				 ==> 클래스처럼 보이게 하는 상수
				 ==> 열거형은 클래스처럼 독립된 java파일에 단독으로 만들수 있고, 
				     하나의 java파일에 클래스와 같이 만들수 있고, 
				     클래스 안에 내부 클래스철머 만들수 있다. 
				     
    - 열거형의 속성 및 메서드	
      1) name() ==> 열거형 상수의 이름을 문자열로 반환한다.
      2) ordinal() ==> 열거형 상수가 정의된 순서값(index)을 반환한다. (0번부터 시작...)
      3) valueOf("열거형상수명") ==> 지정된 열거형에서 '열거형 상수명'과 일치하는 열거형 상수를 반환한다.
      4) 열거형이름.상수명 ==> valueOf()메서드와 같다.
    			     
    - 열거형 선언하기
       방법1)
       	   enum 열거형이름{상수명1, 상수명2, 상수명3, ....}
       	   
       방법2)
          enum 열거형이름{
          	상수명1(값들...),
          	상수명2(값들...),
          	상수명3(값들...),
            ...
            상수명n(값들...);
            
            //'값들'이 저장될 변수 선언 ...(private으로 한다.)
             private 자료형 이름 변수명;
             ..
             ...
             
             //열거형의 생성자를 만든다.
             //  ==> 열거형의 생성자는 '열거형상수'에 '값들'을 셋팅하는 역할을 수행한다.
             //  ==> 열거형 생성자는 묵시적으로 private이다.
             private 열거형 이름 (자료형 변수명, ...){ // 파라미터의 개수는 '값들'의 개수와 같다.
                 위에 선언된 변수들 초기화 하기;
                 ...
             }
             // 구성된 '값들'을 외부에서 불러올 수 있는 getter메서드를 만든다.
          }
*/
public class EnumTest {
	public enum Color{RED, GREEN, BLUE} // 방법1
	public enum Count{ONE, TWO, THREE}  // 방법1
	public enum Season{ //방법2
		//상수명(값들) 형식의 선언
		봄("3월부터 5월까지"),
		여름("6월부터 8월까지"),
		가을("9월부터 11월까지"),
		겨울("12월부터 2월까지");
		
		// 값이 저장될 변수 선언
		private String value;
		
		//생성자
		Season(String month){ //private Season(String Month){ 와 같다.
			value = month;
		}
		
		//getter메서드 만들기
		public String getValue() {
			return value;
		}
		
	}

	
	public static void main(String[] args) {
//		System.out.println("RED : " + ConstTest.RED);
//		System.out.println("THREE : " + ConstTest.THREE);
//		
//		if (ConstTest.RED == ConstTest.TWO) {
//			System.out.println("....");
//		}else {
//			System.out.println("****");
//		}
		
		// 열거형 사용하기
		// 열거형 변수를 선언하고 열거형 상수값으로 초기화하기
		Color mycol = Color.valueOf("GREEN");  //3번방법
		Count mycnt = Count.THREE;            //4번방법
		
		System.out.println("mycol : " + mycol.name());
		System.out.println("mycnt : " + mycnt.name());
		
		System.out.println("mycol ordinal : " + mycol.ordinal());
		System.out.println("mycnt ordinal : " + mycnt.ordinal());
		
		if (mycol == Color.GREEN) {
			System.out.println("같다");
		}
		System.out.println("===============================");
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("value : " + ss.getValue());
		
		//열거형명.values(); ==> 모든 상수들을 배열로 가져온다.
		for(Season se : Season.values()) {
			System.out.println(se.name() +  "==" + se + "==>" + se.getValue());
		}
		
	}
}
