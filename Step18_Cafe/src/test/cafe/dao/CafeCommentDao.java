package test.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.cafe.dto.CafeCommentDto;
import test.mybatis.SqlMapConfig;

public class CafeCommentDao {
	private static CafeCommentDao dao;
	private static SqlSessionFactory factory;
	
	private CafeCommentDao(){}
	
	//자신의 참조값을 리턴해주는 static 맴버 메소드 
	public static CafeCommentDao getInstance(){
		if(dao==null){
			dao=new CafeCommentDao();
			factory=SqlMapConfig.getSqlSession();
		}
		return dao;
	}
	
	// ref_group 번호를 이용해서 덧글 목록을 리턴하는 메소드
	public List<CafeCommentDto> getList(int ref_group){
		SqlSession session = null;
		List<CafeCommentDto> list = null;
		
		try {
			session = factory.openSession(true);
			/*
			 * 	Mapper namespace : cafeComment
			 * 	sql id : getList
			 * 	parameterType : int
			 * 	resultType : CafeCommentDto
			 */
			list = session.selectList("cafeComment.getList", ref_group);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return list;
	}
	
	//새 덧글을 저장하는 메소드
	public void insert(CafeCommentDto dto){
		SqlSession session = null;
		try{
			session = factory.openSession(true);
			session.insert("cafeComment.insert", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	//새 덧글의 글번호(sequence) 값을 얻어내서 리턴해주는 메소드
	public int getSequence(){
		SqlSession session= null;
		int num=0;
		try{
			session = factory.openSession(true);
			num=session.selectOne("cafeComment.getSequence");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return num;
	}
}
