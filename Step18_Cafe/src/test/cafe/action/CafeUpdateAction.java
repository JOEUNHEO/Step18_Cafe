package test.cafe.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.cafe.dao.CafeDao;
import test.cafe.dto.CafeDto;
import test.controller.Action;
import test.controller.ActionForward;

public class CafeUpdateAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. 폼 전송된 수정할 글의 정보 얻어오기
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("ir1");
		
		//2. DB 에 수정 반영
		CafeDto dto = new CafeDto();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		
		boolean isSuccess = CafeDao.getInstance().update(dto);
		//3. 글목록 보기로 redirect 이동
		return new ActionForward("/cafe/list.do", true);
	}

}
