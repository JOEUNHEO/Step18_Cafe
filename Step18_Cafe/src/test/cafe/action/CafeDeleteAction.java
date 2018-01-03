package test.cafe.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.cafe.dao.CafeDao;
import test.controller.Action;
import test.controller.ActionForward;

public class CafeDeleteAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		CafeDao.getInstance().delete(num);
		
		return new ActionForward("/cafe/list.do", true);
	}

}
