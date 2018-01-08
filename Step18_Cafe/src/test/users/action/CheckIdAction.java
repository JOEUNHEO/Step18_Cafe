package test.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.users.dao.UsersDao;

public class CheckIdAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. login_form 에 입력한 id 값을 가져온다.
		String id = request.getParameter("id");
		//2. DB 에 같은 id 가 있는지 확인한다.
		boolean isValid = UsersDao.getInstance().checkId(id);
		
		request.setAttribute("isValid", isValid);
				
		//3. forward 한다.
		return new ActionForward("/views/users/checkid.jsp");
	}

}
