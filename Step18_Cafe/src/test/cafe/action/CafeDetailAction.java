package test.cafe.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.cafe.dao.CafeDao;
import test.cafe.dto.CafeDto;
import test.controller.Action;
import test.controller.ActionForward;

public class CafeDetailAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. 파라미터로 전달되는 글번호를 읽어온다.
		int num = Integer.parseInt(request.getParameter("num"));
		//2. 글의 조회수를 1 올린다.
		CafeDao.getInstance().increaseViewCount(num);
		//3. 글번호에 해당되는 글정보를 얻어온다.
		CafeDto dto = CafeDao.getInstance().getData(num);
		//4. 글정보를 request 에 담는다.
		request.setAttribute("dto", dto);
		//5. view 페이지로 forward 이동해서 글 내용을 출력한다.
		return new ActionForward("/views/cafe/detail.jsp");
	}

}
