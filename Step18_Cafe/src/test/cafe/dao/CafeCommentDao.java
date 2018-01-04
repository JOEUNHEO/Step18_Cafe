package test.cafe.dao;

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
	//새 덧글을 저장하는 메소드
	public void insert(CafeCommentDto dto){
		SqlSession session=factory.openSession(true);
		try{
			session.insert("cafeComment.insert", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	//새 덧글의 글번호(sequence) 값을 얻어내서 리턴해주는 메소드
	public int getSequence(){
		SqlSession session=factory.openSession();
		int num=0;
		try{
			num=session.selectOne("cafeComment.getSequence");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return num;
	}	
}
