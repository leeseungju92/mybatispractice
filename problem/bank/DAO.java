package problem.bank;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class DAO {
	// mybatis 세팅값 호출
	// Session을 생성하는 공장을 만드는 과정
	SqlSessionFactory sqlSessionFactory = SqlMapConfig.getSqlSession();

	// mapper에 접근하기 위한 sqlsession
	SqlSession sqlSession;

	List<DTO> list2;

	// 계좌전체조회
	public void selectBank() {
		System.out.println("zzsd");
		sqlSession = sqlSessionFactory.openSession();
		try {
			list2 = sqlSession.selectList("selBank");
			for (DTO line : list2) {
				System.out.println(line.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<DTO> list = new ArrayList<DTO>();
	DTO dto;

	public void selectAccount(int bno, String pw) {
		// TODO Auto-generated method stub
		sqlSession = sqlSessionFactory.openSession();

		try {
			dto = new DTO(bno, pw);

			dto = sqlSession.selectOne("selectAccount", dto);
			if (dto == null) {
				System.out.println("존재하지 않는 계좌번호이거나 암호입니다.");
				return;
			} else
				System.out.println(bno + "계좌의 잔고가" + dto.getMoney() + "원 있습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		
		
//	public void insert(String bname, String pw) {
//		try {
//			conn = DBManager.getConnection();
//			String sql = "INSERT INTO tbl_bank(bno,bname,pw) " + "VALUES(seq_bno.NEXTVAL,?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, bname);
//			pstmt.setString(2, pw);
//			int result = pstmt.executeUpdate();
//			if (result == 0) {
//				System.out.println("오류");
//
//			} else {
//				System.out.println("성공");
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public void search(int bno, String pw) {
//		try {
//			conn = DBManager.getConnection();
//			String sql = "SELECT * FROM tbl_bank " + "WHERE bno = ? and pw = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bno);
//			pstmt.setString(2, pw);
//			rs = pstmt.executeQuery();
//			list.clear();
//			while (rs.next()) {
//				bno = rs.getInt("bno");
//				int money = rs.getInt("money");
//				String bname = rs.getString("bname");
//				Date regdate = rs.getDate("regdate");
//				dto = new DTO(bno, bname, money, regdate);
//			}
//			System.out.println(dto.getBname() + "님의" + dto.getRegdate() + "에 개설하신" + dto.getBno() + "계좌에"
//					+ dto.getMoney() + "원이 예금되어있습니다.");
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//	}
//
//	public void update(int bno, int money) {
//		try {
//			conn = DBManager.getConnection();
//			String sql = "UPDATE tbl_bank " + "SET money = money+?" + "WHERE bno = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bno);
//			pstmt.setInt(2, money);
//			int result = pstmt.executeUpdate();
//			if (result == 0) {
//				System.out.println("오류");
//			} else {
//				System.out.println("성공");
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public int confirm(int bno, String pw) {
//		// TODO Auto-generated method stub
//		int conf = 0;
//		conn = DBManager.getConnection();
//		String sql = "SELECT * FROM tbl_bank " + "WHERE bno = ? AND pw = ?";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				conf = 1;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return conf;
//	}

	}

	public void insertBank(String bname, String pw) {
		// TODO Auto-generated method stub
		sqlSession=sqlSessionFactory.openSession(true);
		try {
			dto = new DTO(bname,pw);
			int result = sqlSession.insert("insertBank",dto);
			
			if(result >0) {
				System.out.println(bname+"님 계좌개설 성공");
			} else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}
	
	
	public void updateBank(int bno, int money) {
		sqlSession=sqlSessionFactory.openSession(true);
		HashMap<String, Integer> map =new HashMap<>();
		map.put("bno", bno);
		map.put("money", money);
		try {
			int result = sqlSession.update("updateBank",map);
			if(result >0) {
				System.out.println("입/출금 성공");
				System.out.println(balanceMoney(bno));
			} else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}

	public void deletBank(int bno, String pw) {
		sqlSession=sqlSessionFactory.openSession(true);
//		"bno", bno
//		"pw", pw
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("pw", pw);
		try {
			
			int result = sqlSession.delete("deleteBank",map);
			if(result >0) {
				System.out.println("철회 성공");
			} else {
				System.out.println("실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	public int confirm(int bno, String pw) {
		int conf = 0;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * " + "FROM tbl_bank " + "WHERE bno = ? and pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				conf = 1;
			} else {
				conf = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conf;

	}
	public void updateI(DTO dto) {
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE tbl_bank " + "SET money = money + ? " + "WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getMoney());
			pstmt.setInt(2, dto.getBno());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("입/출금성공");
			} else {
				System.out.println("입/출금실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	public int confM(int bno) {
		int confmoney = 0;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * " + "FROM tbl_bank " + "WHERE bno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			confmoney = rs.getInt("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} return confmoney;

	}
	public int balanceMoney(int bno) {
		sqlSession = sqlSessionFactory.openSession();
		int money=0;
		try {
			money =sqlSession.selectOne("balanceMoney",bno);
			System.out.println(">>>>>>>>>>>>>"+money);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return money;
	}

	public boolean checkUser(int bno, String pw) {
		boolean flag = false;// TODO Auto-generated method stub
		sqlSession = sqlSessionFactory.openSession();
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("pw", pw);
		try {
			int num=sqlSession.selectOne("checkUser",map);
			if(num==1) {
				flag=true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return flag;
	}
	
}