package problem.board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;


public class BoardDAO {
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	SqlSession sqlSession;
	List<BoardDTO> list;
	int result;
	BoardDTO dto = new BoardDTO();
	public void insert(String title, String content, String writer) {
		sqlSession=sqlSessionFactory.openSession(true);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("content", content);
		map.put("writer", writer);
		result=sqlSession.insert("board.insert", map);
		if(result>0) {
			System.out.println("게시글 등록 성공");
		}
	}
	public void select() {
		sqlSession=sqlSessionFactory.openSession();
		list=sqlSession.selectList("selBoard");
		for (BoardDTO line : list) {
			System.out.println(line.toString());
		}
		
				
	}
	public void viewcnt(int bno) {
		sqlSession=sqlSessionFactory.openSession(true);
		result=sqlSession.update("viewcnt", bno);
		
	}
	public void search(int bno) {
		sqlSession = sqlSessionFactory.openSession();
		dto=sqlSession.selectOne("search", bno);
		viewcnt(bno);
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println(dto.getTitle()+"\t"+dto.getWriter()+"\t"+dto.getViewcnt());
		System.out.println(dto.getContent());
		
	}
	public void delete(int bno,String userid) {
		sqlSession=sqlSessionFactory.openSession(true);
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("userid", userid);
		result=sqlSession.delete("delete",map);
		if(result>0) {
			System.out.println("게시글 삭제 성공");
		}else {
			System.out.println("게시글 삭제 실패");
		}
	}
	public void update(int bno, String title, String content, String writer) {
		sqlSession=sqlSessionFactory.openSession(true);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("title", title);
		map.put("content", content);
		map.put("writer", writer);
		result=sqlSession.update("update", map);
		if(result>0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("실패");
		}
	}
	public void selectTitle(String title) {
		sqlSession=sqlSessionFactory.openSession(true);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "%"+title+"%");
		list=sqlSession.selectList("selectTitle", map);
		for (BoardDTO boardDTO : list) {
			System.out.println(boardDTO.toString());
		}
	}
	
	
	String session;
	
	public String login(String id, String pw) {
		try {			
			sqlSession = sqlSessionFactory.openSession();
			session = "no";
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("pw", pw);
			int num = sqlSession.selectOne("login.login", map);
			if (num>0) {
				session = "yes";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return session;

	}


}
