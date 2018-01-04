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
		
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		//BoardDto 객체를 생성해서
		CafeDto dto=new CafeDto();
		if(keyword != null){ //검색어가 전달된 경우
			if(condition.equals("titlecontent")){ //제목+내용 검색
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){//제목 검색
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){//작성자 검색
				dto.setWriter(keyword);
			}
			// list.jsp 뷰페이지에서 필요한 내용을 request 에 담는다.
			request.setAttribute("condition", condition);
			request.setAttribute("keyword", keyword);
		}
		
		//1. 파라미터로 전달되는 글번호를 읽어온다.
		int num = Integer.parseInt(request.getParameter("num"));
		dto.setNum(num); //글번호도 dto 에 담는다.
		//2. 글의 조회수를 1 올린다.
		CafeDao.getInstance().increaseViewCount(num);
		//3. 글번호에 해당되는 글정보를 얻어온다.
		CafeDto resultDto = CafeDao.getInstance().getData(dto);
		//4. 글정보를 request 에 담는다.
		request.setAttribute("dto", resultDto);
		
		String id = (String)request.getSession().getAttribute("id");
		boolean isLogin=false;
		if(id != null){
			isLogin=true;
		}
		//로그인 했는지 여부도 request 에 담는다.
		request.setAttribute("isLogin", isLogin);
		
		//5. 뷰페이지로 forward 이동해서 글정보를 응답한다.
		return new ActionForward("/views/cafe/detail.jsp");

	}

}
