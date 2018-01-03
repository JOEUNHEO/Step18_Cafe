package test.cafe.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.cafe.dao.CafeDao;
import test.cafe.dto.CafeDto;
import test.controller.Action;
import test.controller.ActionForward;

public class CafeInsertAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//파라미터로 전송되는 글제목, 글내용 읽어오기
		String title=request.getParameter("title");
		String content=request.getParameter("ir1");
		//세션에 저장된 id 정보 읽어오기
		String id=(String)request.getSession().getAttribute("id");
		//CafeDto 에 정보 담기
		CafeDto dto=new CafeDto();
		dto.setWriter(id);
		dto.setTitle(title);
		dto.setContent(content);
		//CafeDao 객체를 이용해서 DB 에 저장하기 
		boolean isSuccess = CafeDao.getInstance().insert(dto);
		
		if(isSuccess) {
			
		}else {
			
		}
		
		//글 목록 보기로 리다일렉트 이동
		return new ActionForward("/cafe/list.do", true);
	}

}
