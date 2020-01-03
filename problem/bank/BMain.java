package problem.bank;

import java.util.Scanner;

public class BMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DAO dao = new DAO();
		int code = 0;
		while (true) {
			System.out.println("은행");
			System.out.println("1.계좌개설");
			System.out.println("2.계좌철회");
			System.out.println("3.입금");
			System.out.println("4.전체계좌 조회");
			System.out.println("5.계좌조회");
			while (true) {
				System.out.print("번호입력>");
				code = sc.nextInt();
				if (1 > code && code > 5) {
					continue;
				} else {
					break;
				}
			}
			if (code == 1) {
				System.out.print("예금주명>");
				sc.nextLine();
				String bname = sc.nextLine();
				System.out.print("비밀번호>");
				String pw = sc.nextLine();
				dao.insertBank(bname, pw);

			} else if (code == 2) {
				System.out.println("출금");
				System.out.print("계좌번호 입력");
				int bno = sc.nextInt();
				System.out.print("비밀번호 입력");
				sc.nextLine();
				String pw = sc.nextLine();
				
				if (!(dao.checkUser(bno,pw))){
					System.out.println("계좌번호 또는 비밀번호 오류");
					continue;
				}
				System.out.print("출금액 입력");

				int confmoney=dao.balanceMoney(bno);

				int money = sc.nextInt();
				if (confmoney >= money) {
					
					dao.updateBank(bno, -money);;
				} else {
					System.out.println("잔고가 부족합니다.");
					continue;
				}

			} 
			else if (code == 6) {
				System.out.print("계좌번호>");
				int bno = sc.nextInt();
				System.out.println("비밀번호>");
				sc.nextLine();
				String pw = sc.nextLine();
				dao.deletBank(bno, pw);
			}
			else if (code == 3) {

				System.out.print("계좌번호>");
				int bno = sc.nextInt(); 
				System.out.print("얼마?");
				int money = sc.nextInt();
				dao.updateBank(bno, money);
			} else if (code == 4) {
				System.out.println("전체계좌조회");
				dao.selectBank();
			} else if (code == 5) {
				System.out.println("계좌번호와 암호를 입력");
				System.out.print("계좌>");
				int bno = sc.nextInt();
				System.out.print("비번>");
				sc.nextLine();
				String pw = sc.nextLine();
				dao.selectAccount(bno, pw);
			}

//			if (code == 1) {
//				System.out.print("예금주명>");
//				sc.nextLine();
//				String bname = sc.nextLine();
//				System.out.print("비밀번호>");
//				String pw = sc.nextLine();
//				dao.insert(bname, pw);
//
//			} else if (code == 2) {
//				System.out.print("계좌번호>");
//				int bno = sc.nextInt();
//				System.out.println("비밀번호>");
//				sc.nextLine();
//				String pw = sc.nextLine();
//				dao.search(bno, pw);
//			} else if (code == 3) {
//
//				System.out.print("계좌번호>");
//				int bno = sc.nextInt();
//				System.out.print("얼마?");
//				int money = sc.nextInt();
//				dao.update(bno, money);
//
//			} else if (code == 4) {
//				while (true) {
//					System.out.print("계좌번호>");
//					int bno = sc.nextInt();
//					System.out.println("비밀번호>");
//					sc.nextLine();
//					String pw = sc.nextLine();
//					int conf = dao.confirm(bno, pw);
//					if (conf == 1) {
//						break;
//					}
//				}
//				
//			} else if (code == 5) {
//				System.out.print("계좌번호>");
//				int bno = sc.nextInt();
//				System.out.println("비밀번호>");
//				sc.nextLine();
//				String pw = sc.nextLine();
//				dao.search(bno, pw);
//			}
		}

	}
}
