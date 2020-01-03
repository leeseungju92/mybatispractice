package mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
	private static SqlSessionFactory sqlSessionFactory;
	
	//정적 블록
	//클래스 로딩시 1회만 실행되는 코드
	static {
		String resource = "mybatis/Configuration.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			
			if(sqlSessionFactory==null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static SqlSessionFactory getSqlSession() {
		return sqlSessionFactory;
	}
	
}
