package problem.convenience;

import java.sql.Date;
import java.util.Scanner;

public class CMain {
	// 내부저장소
	// 관리자 계정 ID와 PW 선언
	String id = "admin";
	String pw = "1234";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CDAO cDao = new CDAO();
		CMain mn = new CMain();
		Boolean flag =false;
		String userid = "";
		String userpw = "";

		System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
		System.out.println("▨▧▨▧▨▧▨▧▨▧ Convinience System Ver1.0▨▧▨▧▨▧▨▧▨▧");
		System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");

		do {
			System.out.println("▨▧[Msg] Please Login To Use.");
			System.out.print("▨▧ID>>");
			userid = sc.nextLine();
			System.out.print("▨▧PW>>");
			userpw = sc.nextLine();
		} while (mn.login(userid, userpw));
//		 로그인 체크

		while (true) {
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧ 1. sale   merchandise▧▨▧▨▧▨▧▨▧▨▧▧");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧ 2. update merchandise▧▨▧▨▧▨▧▨▧▨▧▨");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧ 3. delete merchandise▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧ 4. view   merchandise▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧ 5. search merchandise▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧ 6. amount of sales   ▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧ 7. system exit       ▨▧▨▧▨▧▨▧▨▧▨▧");
			System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
			int code;
			while (true) {
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧ select the number▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.print("▨▧number>>");
				code = sc.nextInt();
				if (0 < code && code < 8)
					break;
				else {
					System.out.println("▨▧[Msg] Incorrect number");
					System.out.println("▨▧[Msg] Please type the number by 1 to 7");
					continue;
				}
			}

			if (code == 1) {
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧1. sale merchandise▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				int pno = 0;
				int amt = 0;
				cDao.selectAll();
				while (true) {
					System.out.print("▨▧no of merchandise(1001~)>>");
					pno = sc.nextInt();
					System.out.println(cDao.no(pno));
					if (cDao.no(pno)==0) {
						System.out.println("유효한 상품번호가 아닙니다.");
						continue;
					} else {
						break;
					}
				}
				while (true) {
					System.out.print("▨▧amount of merchandise>>");
					amt = sc.nextInt();
					if (cDao.cnt(pno) > amt) {
						cDao.update(pno, amt);
						int tprice=cDao.priceC(pno);
						int cnt = amt;
						SDTO sdto = new SDTO(pno,tprice,cnt);
						cDao.insertS(sdto);
						break;
					}else {
					System.out.println("재고가 부족합니다.");
					}
				} 
				
			} else if (code == 2) {
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨2. update merchandise▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.print("▨▧입고 아이템>>");
				sc.nextLine();
				String pname =sc.nextLine();
				if(cDao.already(pname)) {
//					기등록 UPDATE >
					System.out.println("▨▧입고 수량>>");
					int amt =sc.nextInt();
					cDao.updateProduct(pname, amt);
				}else {
//					미등록 INSERT >
					System.out.println("▨▧입고 수량>>");
					int amt =sc.nextInt();
					System.out.println("▨▧제품 가격>>");
					int price =sc.nextInt();
					System.out.println("▨▧제조사>>");
					sc.nextLine();
					String company = sc.nextLine();
					
					cDao.insert(pname,company,price,amt);
				}

			} else if (code == 3) {
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨3. delete merchandise▨▧▨▧▨▧▨▧▨▧▨▧");
				System.out.println("▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧▨▧");
				cDao.selectAll();
				int pno;
				while (true) {
					System.out.print("▨▧no of merchandise(1001~)>>");
					pno = sc.nextInt();
					if (cDao.no(pno)==0) {
						System.out.println("유효한 상품번호가 아닙니다.");
						continue;
					} else {
						cDao.delete(pno);
						break;
					}
				}
			} else if (code == 4) {
				cDao.selectAll();
				
			} else if (code == 5) {
				cDao.selectAll();
				System.out.print("▨▧no of merchandise(1001~)>>");
				int pno=sc.nextInt();
				cDao.selectOne(pno);
			} else if (code == 6) {
				System.out.println();
				cDao.selectSum();
			} else if (code == 7) {
				System.out.println("▨▧[Msg] System Exit");
				System.exit(0);
			}
		}
	}

	public boolean login(String userid, String userpw) {
		Boolean flag = true; // 로그인 유무 판별 flag 값 <true:실패,false:성공>
		if (userid.equals(id) && userpw.equals(pw)) {
			flag = false;
			System.out.println("▨▧[Msg] Login accessed by admin");
		} else {
			System.out.println("▨▧[Msg] Login denied");
		}
		return flag;
	}
}
