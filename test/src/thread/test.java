package thread;

import javax.swing.JOptionPane;

public class test {
public static void main(String[] args) {
	
	DataInput th1 = new DataInput();
	CountDown th2 = new CountDown();
	
	th1.start();
	th2.start();
}


}




class DataInput extends Thread{
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
			String str = JOptionPane.showInputDialog("아무거나 입력하세요");
			inputCheck = true;
			System.out.println("입력값 : " + str);
	}
}



class CountDown extends Thread{
	@Override
	public void run() {
		for (int i = 10; i >= 1 ; i--) {
			if (DataInput.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("프로그램 종료");
		System.exit(0);
	}
	
}