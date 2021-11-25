package kr.or.ddit.Test0623;

// VO객체 ==> DB테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스이다.
//	- DB테이블의 '컬럼명'이 이 클래스의 '멤버변수'가 된다.
//	- DB테이블의 컬럼과 멤버변수를 매핑하는 역할을 수행한다.
public class MemberVO {
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;

	// VO클래스에서 별도의 생성자를 만들때는 기본 생성자도 반드시 같이 만들어준다.
	
	public String getMem_id() {
		return mem_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_addr == null) ? 0 : mem_addr.hashCode());
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		result = prime * result + ((mem_name == null) ? 0 : mem_name.hashCode());
		result = prime * result + ((mem_pass == null) ? 0 : mem_pass.hashCode());
		result = prime * result + ((mem_tel == null) ? 0 : mem_tel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (mem_addr == null) {
			if (other.mem_addr != null)
				return false;
		} else if (!mem_addr.equals(other.mem_addr))
			return false;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		if (mem_name == null) {
			if (other.mem_name != null)
				return false;
		} else if (!mem_name.equals(other.mem_name))
			return false;
		if (mem_pass == null) {
			if (other.mem_pass != null)
				return false;
		} else if (!mem_pass.equals(other.mem_pass))
			return false;
		if (mem_tel == null) {
			if (other.mem_tel != null)
				return false;
		} else if (!mem_tel.equals(other.mem_tel))
			return false;
		return true;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	
	
	
}
