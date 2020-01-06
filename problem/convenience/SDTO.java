package problem.convenience;

import java.sql.Date;

public class SDTO {
	private int sno;
	private int pno;
	private int tprice;
	private int cnt;
	private Date regdate;
	
	public SDTO() {}

	public SDTO(int sno, int pno, int tprice, int cnt, Date regdate) {
		super();
		this.sno = sno;
		this.pno = pno;
		this.tprice = tprice;
		this.cnt = cnt;
		this.regdate = regdate;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int getTprice() {
		return tprice;
	}

	public void setTprice(int tprice) {
		this.tprice = tprice;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "SDTO [sno=" + sno + ", pno=" + pno + ", tprice=" + tprice + ", cnt=" + cnt + ", regdate=" + regdate
				+ "]";
	}

	public SDTO(int pno, int tprice, int cnt) {
		super();
		this.pno = pno;
		this.tprice = tprice;
		this.cnt = cnt;
	}

}
