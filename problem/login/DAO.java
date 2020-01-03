package problem.login;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class DAO {

	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	SqlSession sqlSession;
	DTO dto = new DTO();
	List<DTO> list;
	String session;
	public String login(String id, String pw) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			session = "no";
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("pw", pw);
			list = sqlSession.selectList("login", map);
			if (list.size() == 1) {
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
