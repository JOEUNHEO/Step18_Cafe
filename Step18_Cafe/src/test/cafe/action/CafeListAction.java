package test.cafe.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.cafe.dao.CafeDao;
import test.cafe.dto.CafeDto;
import test.controller.Action;
import test.controller.ActionForward;

public class CafeListAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. 글 목록을 불러온다.
		List<CafeDto> list = CafeDao.getInstance().getList();
		//2. request 에 담는다.
		request.setAttribute("list", list);
		//3. forward 이동해서 글목록 출력하기
		return new ActionForward("/views/cafe/list.jsp");
	}

}
