package test.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.cafe.dto.CafeDto;
import test.mybatis.SqlMapConfig;

public class CafeDao {
	private static CafeDao dao;
	private static SqlSessionFactory factory;
	private CafeDao(){}
	public static CafeDao getInstance(){
		if(dao==null){
			dao=new CafeDao();
			factory=SqlMapConfig.getSqlSession();
		}
		return dao;
	}
	
	//글목록을 리턴해주는 메소드
	public List<CafeDto> getList(){
		SqlSession session = null;
		List<CafeDto> list = null;
		
		try {
			session = factory.openSession(true);
			/*
			 * 	Mapper 의 namespace :cafe
			 * 	sql id : getList
			 * 	resultType : CafeDto 
			 */
			list = session.selectList("cafe.getList");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return list;
	}
	//글정보를 저장하는 메소드
	public boolean insert(CafeDto dto) {
		SqlSession session = null;
		boolean isSuccess = false;
		
		try {
			session = factory.openSession(true);
			int flag = session.insert("cafe.insert", dto);
			if(flag > 0) {
				isSuccess = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return isSuccess;
	}
	
	//조회수를 1 올리는 메소드
	public void increaseViewCount(int num) {
		SqlSession session = null;
				
		try {
			session = factory.openSession(true);
			session.update("cafe.increaseViewCount", num);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	//글정보를 리턴해주는 메소드
	public CafeDto getData(int num) {
		SqlSession session = null;
		CafeDto dto = null;
		
		try {
			session = factory.openSession(true);
			/*
			 * 	Mapper namespace : cafe
			 * 	sql id : getData
			 * 	parameterType : int
			 * 	resultType : CafeDto 
			 */
			dto = session.selectOne("cafe.getData", num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return dto;
	}
}
