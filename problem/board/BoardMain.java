package problem.board;

import java.util.Scanner;

public class BoardMain {
	static String session = "no";
	static String userid = "";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int code = 0;
		while (true) {
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("게시판");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("1.게시글 작성");
			System.out.println("2.게시글 목록");
			System.out.println("3.게시글 조회");
			System.out.println("4.게시글 삭제");
			System.out.println("5.게시글 수정");
			System.out.println("6.게시글 제목포함 목록");
			System.out.println("7.로그인");
			System.out.println("8.게시글 나가기");
			while (true) {
				System.out.print("뭐할건데>");
				code = sc.nextInt();
				if (0 < code && code < 9) {
					break;
					
				} else {
					System.out.println("1~8만 눌러");
				}
			}

			BoardDAO dao = new BoardDAO();
			if (code == 1) {
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("게시글 작성");
				System.out.println(session+","+userid);
				if (session.equalsIgnoreCase("no")) {
					continue;
				} 
				System.out.print("제목>");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.print("내용>");
				String content = sc.nextLine();
				dao.insert(title, content, userid);

			} else if (code == 2) {
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("게시글 목록");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				dao.select();
			} else if (code == 3) {
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("게시글 조회");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.print("게시글 번호 입력>");
				int bno = sc.nextInt();
				dao.search(bno);
			} else if (code == 4) {
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("게시글 삭제");
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.print("게시글 번호 입력>");
				int bno = sc.nextInt();
				dao.delete(bno,userid);
			} else if (code == 5) {
				System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
				System.out.println("게시글 수정");
				System.out.print("수정할 게시글 번호>");
				int bno = sc.nextInt();
				 
				
				String writer = userid;
				System.out.print("제목>");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.print("내용>");
				String content = sc.nextLine();
				dao.update(bno, title, content, writer);
			} else if (code == 6) {
				System.out.print("제목명 조회");
				System.out.print("제목>");
				sc.nextLine();
				String title = sc.nextLine();
				dao.selectTitle(title);
			} else if (code == 7) {
				System.out.println("로그인시스템");
				System.out.print("아디>");
				sc.nextLine();
				String id = sc.nextLine();
				System.out.print("비번>");
				String pw = sc.nextLine();
				session = dao.login(id, pw);
				if (session.equalsIgnoreCase("yes")) {
					userid = id;
					System.out.println(id + "가 로그인" + session);
				} else {
					continue;
				}

				
			} else if (code == 8) {
				System.out.println("ㅂㅇ");
				sc.close();
				System.exit(0);
			}
		}

	}
}