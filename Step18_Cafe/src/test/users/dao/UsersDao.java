package test.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.mybatis.SqlMapConfig;
import test.users.dto.UsersDto;

public class UsersDao {
	private static UsersDao dao;
	private static SqlSessionFactory factory;
	private UsersDao(){}
	public static UsersDao getInstance(){
		if(dao==null){
			dao=new UsersDao();
			factory=SqlMapConfig.getSqlSession();
		}
		return dao;
	}
	
	//회원정보를 삭제하는 메소드
	public boolean delete(String id) {
		SqlSession session = null;
		boolean isSuccess = false;
		
		try {
			//auto commit 되는 SqlSession 객체 얻어오기
			session = factory.openSession(true);
			/*
			 * 	Mapper 의 namespace : users
			 * 	Mapper 안에 sql 문의 id : delete
			 * 	parameterType : String
			 */
			int flag = session.delete("users.delete", id);
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
	
	//회원정보를 수정하는 메소드
	public boolean update(UsersDto dto) {
		SqlSession session = null;
		boolean isSuccess = false;
		
		try {
			session = factory.openSession(true);
			int flag = session.update("users.update", dto);
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
	
	//회원 한명의 정보를 리턴해 주는 메소드
	public UsersDto getData(String id) {
		SqlSession session = null;
		UsersDto dto = null;
		
		try {
			session = factory.openSession(true);
			/*
			 * 	Mapper 의 namespace : users
			 * 	Mapper 안에 sql id : getData
			 * 	parameterType : String
			 * 	resultType : UsersDto
			 */
			dto = session.selectOne("users.getData", id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return dto;
	}
	
	//아이디 비밀번호가 유효한지 여부를 리턴하는 메소드
	public boolean isValid(UsersDto dto) {
		SqlSession session = null;
		String id = null;
		boolean isValid = false;
				
		try {
			session = factory.openSession(true);
			/*
			 * 	parameterType : UsersDto
			 * 	resultType : String
			 */
			id = session.selectOne("users.isValid", dto);
			// id 가 null 이면 아이디 혹은 비밀번호가 틀린 것이다.
			if(id != null) {
				isValid = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return isValid;
	}
	
	//회원정보를 저장하는 메소드
	public boolean insert(UsersDto dto) {
		SqlSession session = null;
		boolean isSuccess = false;
		
		try {
			session = factory.openSession(true);
			int flag = session.insert("users.insert", dto);
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
	
	//회원 id가 존재하는 지 확인하는 메소드
	public boolean checkId(String id) {
		SqlSession session = null;
		String checkid = null;
		boolean isValid = false;
		
		try {
			session = factory.openSession(true);
			checkid = session.selectOne("users.checkId", id);
			if(checkid != null) {
				isValid = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return isValid;
	}
}
