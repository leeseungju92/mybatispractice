package problem.login;

import java.util.Scanner;

public class LoginMain {
	static String session;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("로그인시스템");
		System.out.print("아디>");
		String id=sc.nextLine();
		System.out.print("비번>");
		String pw=sc.nextLine();
		DAO dao = new DAO();
		session=dao.login(id,pw);
		System.out.println(session);
		sc.close();
	}
}
