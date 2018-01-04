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
	/*
	 * 	인자로 전달된 CafeDto 에는 startRowNum 과 endRowNum 이
	 * 	들어 있다. 이 값을 이용해서 SELECT 한다.
	 */
	public List<CafeDto> getList(CafeDto dto){
		SqlSession session = null;
		List<CafeDto> list = null;
		
		try {
			session = factory.openSession(true);
			/*
			 * 	Mapper 의 namespace :cafe
			 * 	sql id : getList
			 * 	parameterType: CafeDto
			 * 	resultType : CafeDto 
			 */
			list = session.selectList("cafe.getList", dto);
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
	public CafeDto getData(CafeDto dto) {
		SqlSession session = null;
		CafeDto resultDto = null;
		
		try {
			session = factory.openSession(true);
			/*
			 * 	Mapper namespace : cafe
			 * 	sql id : getData
			 * 	parameterType : CafeDto
			 * 	resultType : CafeDto 
			 */
			resultDto = session.selectOne("cafe.getData", dto);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return resultDto;
	}
	
	//글정보를 삭제하는 메소드
	public boolean delete(int num) {
		SqlSession session = null;
		boolean isSuccess = false;
		
		try {
			session = factory.openSession(true);
			int flag = session.delete("cafe.delete", num);
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
	//글정보를 수정하는 메소드
	public boolean update(CafeDto dto) {
		SqlSession session = null;
		boolean isSuccess = false;
		
		try {
			session = factory.openSession(true);
			int flag = session.update("cafe.update", dto);
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
	
	//전체 글의 갯수를 리턴하는 메소드
	public int getCount(CafeDto dto) {
		SqlSession session = null;
		int count = 0;
		
		try {
			session = factory.openSession(true);
			/*
			 * 	검색 조건에 맞는 row 의 갯수만 셀 수 있도록
			 * 	parameter 를 CafeDto 에 전달한다.
			 * 	parameterType : CafeDto
			 */
			count = session.selectOne("cafe.getCount", dto);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return count;
	}
}
