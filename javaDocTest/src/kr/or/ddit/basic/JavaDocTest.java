package kr.or.ddit.basic;
//Java Doc 파일 만들기 예제

/**
 * 
 * @author 홍길동
 * @version 1.0
 *
 *<p>
 *	파일명 : JavaDocTest.java<br>
 *  설  명 : JavaDoc문서 작성을 위한 연습용 Interface<br><br>
 *  
 *  수정이력 : <br>
 *  ---------------------<br>
 *  수정일자 : 2021-06-18<br>
 *  작성자  : 홍길동<br>
 *  수정내용 : 최초 생성<br>
 *  ---------------------<br>
 *  </p>
 */

public interface JavaDocTest {
	/**
	 * 메서드명 : methodTest
	 * 기    능 : 반환값이 없는 메서드
	 * @param a 첫번째 매개변수 (정수형)
	 * @param b 두번째 매개변수 (정수형)
	 */
	public void methodTest(int a, int b);
	
	
	/**
	 * 메서드명 : methodAdd<br>
	 * 기    능 : 정수형 데이터 2개를 인수로 받아서 합계를 반환하는 메서드
	 * @param x 합계 계산에 시용할 첫번째 정수형 데이터
	 * @param y 합계 계산에 사용할 두번째 정수형 데이터
	 * @return 두 정수의 합계가 반환된다.
	 */
	public int methodAdd(int x, int y);
	
	/**
	 * 메서드명 : methodSub<br>
	 * 기 능 : 반환값과 매개변수가 없는 메서드
	 */
	public void methodSub();
}
