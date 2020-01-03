package problem.login;

import java.sql.Date;

public class DTO {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private Date regdate;
	public DTO() {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "DTO [id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", regdate=" + regdate + "]";
	}
	public DTO(String id, String pw, String name, String phone, Date regdate) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.regdate = regdate;
	}
}
