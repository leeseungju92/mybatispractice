package problem.bank;

import java.sql.Date;

public class DTO {
	private int bno;
	private String bname;
	private String pw;
	private int money;
	private Date regdate;
	public DTO(int bno, int money) {
		super();
		this.bno = bno;
		this.money = money;
	}
	public int getBno() {
		return bno;
	}
	public DTO(int bno, String pw) {
		super();
		this.bno = bno;
		this.pw = pw;
	}
	public DTO(String bname, String pw) {
		super();
		this.bname = bname;
		this.pw = pw;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBname() {
		return bname;
	}
	public DTO(int bno, String bname, int money, Date regdate) {
		super();
		this.bno = bno;
		this.bname = bname;
		this.money = money;
		this.regdate = regdate;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public DTO(String bname, String pw, int money) {
		super();
		this.bname = bname;
		this.pw = pw;
		this.money = money;
	}
	public DTO(int bno, String bname, String pw, int money, Date regdate) {
		super();
		this.bno = bno;
		this.bname = bname;
		this.pw = pw;
		this.money = money;
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "DTO [bno=" + bno + ", bname=" + bname + ", pw=" + pw + ", money=" + money + ", regdate=" + regdate
				+ "]";
	}
	public DTO() {}

}
