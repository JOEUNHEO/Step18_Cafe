package test.file.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.file.dao.FileDao;
import test.file.dto.FileDto;

public class FileDownloadAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//파라미터로 전달되는 다운로드 시켜줄 파일의 번호를 읽어온다.
		int num = Integer.parseInt(request.getParameter("num"));
		//다운로드 시켜줄 파일의 정보를 DB 에서 읽어온다.
		FileDto dto = FileDao.getInstance().getData(num);
		
		// FileDto 를 request 에 담고
		request.setAttribute("dto", dto);
		
		// 파일을 다운로드 시켜주는 view 페이지로 forward 이동해서
		// 파일 다운로드 시켜주기
		return new ActionForward("/views/file/download.jsp");
		
	}

}
