package problem.convenience;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class CDAO {
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();
	SqlSession sqlSession;
	int result;
	List<CDTO> list;
	Boolean flag;
	
	public boolean already(String pname){
		sqlSession = sqlSessionFactory.openSession();
		try {
			flag= false;
			result = sqlSession.selectOne("C.already",pname);
			if(result>0) {
				flag=true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public String selPname(int pno) {
		String pname;
		sqlSession = sqlSessionFactory.openSession();
		pname=sqlSession.selectOne("pname", pno);
		return pname;
	}
	public void update(int pno, int amt) {
		sqlSession = sqlSessionFactory.openSession(true);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("pno", pno);
		map.put("cnt", amt);
		result = sqlSession.update("sale", map);
		if (result > 0) {
			System.out.println("판매가 성공 하였습니다.");
			int price = sqlSession.selectOne("price", map);
			System.out.println("판매 금액은" + (price * amt) + "입니다.");
		}
	}
	public int priceC(int pno) {
		int price = 0;
		sqlSession = sqlSessionFactory.openSession(true);
		price = sqlSession.selectOne("price", pno);
		return price;
	}
	public int cnt(int pno) {
		int cnt;
		sqlSession = sqlSessionFactory.openSession();
		cnt=sqlSession.selectOne("amount", pno);
		return cnt;
	}
	public int no(int pno) {
		int number=0;
		CDTO dto = new CDTO();
		sqlSession = sqlSessionFactory.openSession();
		dto = sqlSession.selectOne("pno",pno);
		if(!(dto==null)){
			number = dto.getPno();
		} else if(dto==null) {
			number=0;
		}
		return number;
	}
	public void selectAll() {
		sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList("sellectAll");
		for (CDTO cdto : list) {
			System.out.println(cdto.toString());
			
		}
		
	}
	public void selectOne(int pno) {
		sqlSession = sqlSessionFactory.openSession();
		CDTO dto = new CDTO();
		dto = sqlSession.selectOne("sellectOne",pno);
		System.out.println(dto.toString());
			
		}
		
	public void insertS(SDTO sdto){
		sqlSession = sqlSessionFactory.openSession(true);
		result=sqlSession.insert("insertS", sdto);
		if(result>0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}


	public void updateProduct(String pname, int amt) {
		sqlSession=sqlSessionFactory.openSession(true);
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pname", pname);
			map.put("amt",amt);
			
			result=sqlSession.update("updateP", map);
			if(result>0) {
				System.out.println("입고된 제품의 수량이 업데이트 되었습니다.");
			}else {
				System.out.println("업데이트 실패하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void insert(String pname, String company, int price, int amt) {
		sqlSession=sqlSessionFactory.openSession(true);
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pname", pname);
			map.put("amt",amt);
			map.put("company",company);
			map.put("price",price);
			result=sqlSession.insert("insertP", map);
			if(result>0) {
				System.out.println("입고된 제품이 등록 되었습니다.");
			}else {
				System.out.println("등록 실패하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(int pno) {
		sqlSession=sqlSessionFactory.openSession(true);
		try {
			result=sqlSession.delete("C.deleteP",pno);
			if(result>0) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectSum() {
		sqlSession=sqlSessionFactory.openSession();
		try {
			result=sqlSession.selectOne("selectSum");
			if(result>0) {
				System.out.println("성공");
				System.out.println(result);
			}else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
