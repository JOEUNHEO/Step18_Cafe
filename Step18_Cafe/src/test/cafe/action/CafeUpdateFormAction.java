package test.cafe.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.cafe.dao.CafeDao;
import test.cafe.dto.CafeDto;
import test.controller.Action;
import test.controller.ActionForward;

public class CafeUpdateFormAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. 파라미터로 전달되는 수정할 글번호 읽어오기
		int num = Integer.parseInt(request.getParameter("num"));
		//2. 수정할 글의 정보 얻어오기
		CafeDto dto = new CafeDto();
		dto.setNum(num); //글번호를 Dto 에 담아서
		//CafeDao 에 전달한다.
		CafeDto resultDto = CafeDao.getInstance().getData(dto);
		//3. request 에 dto 를 담고
		request.setAttribute("dto", resultDto);
		//4. 수정할 글의 정보 출력해 주기
		return new ActionForward("/views/cafe/private/updateform.jsp");
	}

}
