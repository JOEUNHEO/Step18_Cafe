package test.file.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.file.dto.FileDto;
import test.mybatis.SqlMapConfig;

public class FileDao {
	private static FileDao dao;
	private static SqlSessionFactory factory;
	
	private FileDao() {}
	
	public static FileDao getInstance() {
		if(dao == null) {
			dao = new FileDao();
			factory=SqlMapConfig.getSqlSession();
		}
		
		return dao;
	}
	
	public boolean insert(FileDto dto) {
		SqlSession session = null;
		boolean isSuccess = false;
		
		try {
			session = factory.openSession(true);
			int flag = session.insert("file.insert", dto); 
					
			if (flag > 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return isSuccess;
	}
	
	public List<FileDto> getList(FileDto dto) {
		SqlSession session = null;
		List<FileDto> list = null;

		try {
			session = factory.openSession(true);
			list = session.selectList("file.getList", dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		//글 목록을 리턴해준다.
		return list;
	}
	
	public boolean delete(int num) {
		SqlSession session = null;
		boolean isSuccess = false;
		
		try {
			session = factory.openSession(true);
			int flag = session.delete("file.delete", num);
			
			if (flag > 0) {
				isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return isSuccess;
	}
	
	public FileDto getData(int num) {
		SqlSession session = null;
		FileDto dto = null;

		try {
			session = factory.openSession(true);
			/*
			 * 	parameterType : int
			 * 	resultType : FileDto
			 */
			dto = session.selectOne("file.getData", num);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	
		return dto;
	}
	
	public int getCount(FileDto dto) {
		SqlSession session = null;
		int count = 0;
		
		try {
			session = factory.openSession(true);
			/*
			 * 	검색 조건에 맞는 row 의 갯수만 셀 수 있도록
			 * 	parameter 를 FileDto 에 전달한다.
			 * 	parameterType : FileDto
			 */
			count = session.selectOne("file.getCount", dto);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return count;
	}
}
