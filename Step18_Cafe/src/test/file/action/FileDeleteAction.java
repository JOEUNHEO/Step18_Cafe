package test.file.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.file.dao.FileDao;
import test.file.dto.FileDto;

public class FileDeleteAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//삭제할 파일의 번호를 읽어와서
		int num = Integer.parseInt(request.getParameter("num"));
		//1. 삭제할 파일의 저장된 파일명을 읽어온다.
		FileDto dto = FileDao.getInstance().getData(num);
		String saveFileName = dto.getSaveFileName();
		//2. DB 에서 파일 정보 삭제
		boolean isSuccess = FileDao.getInstance().delete(num);
		//3. 파일 시스템에서 삭제
		//삭제할 파일의 절대 경로 구성하기
		ServletContext application = request.getServletContext();
		String path = application.getRealPath("/upload")+
			File.separator+saveFileName;
		//File 객체생성해서 삭제하기
		new File(path).delete();
		
		request.setAttribute("isSuccess", isSuccess);
		String cPath = request.getContextPath();
		request.setAttribute("url", cPath+"/file/list.do");
		
		//파일 목록 보기로 forward 이동(request 에 담은 내용을 jsp 에서 부르기 위해서는 리다일렉트로 보낼 수 없음)
		return new ActionForward("/views/file/private/alert2.jsp");
	}

}
