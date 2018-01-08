package test.file.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.file.dao.FileDao;
import test.file.dto.FileDto;

public class FileListAction extends Action{
	//한 페이지에 나타낼 로우의 갯수
	private static final int PAGE_ROW_COUNT = 3;
	//하단 디스플레이 페이지 갯수
	private static final int PAGE_DISPLAY_COUNT = 5;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//검색과 관련된 파라미터를 읽어와 본다.
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
				
		//CafeDto 객체를 생성해서
		FileDto dto=new FileDto();
		if(keyword != null){ //검색어가 전달된 경우
			if(condition.equals("titlewriterfile")){ //제목+내용 검색
				dto.setTitle(keyword);
				dto.setWriter(keyword);
				dto.setOrgFileName(keyword);
			}else if(condition.equals("title")){//제목 검색
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){//작성자 검색
				dto.setWriter(keyword);
			}else if(condition.equals("orgFileName")) {//파일명 검색
				dto.setOrgFileName(keyword);
			}
			// list.jsp 뷰페이지에서 필요한 내용을 request 에 담는다. (검색 후 검색 폼에 검색한 키워드를 남기기 위해서)
			request.setAttribute("condition", condition);
			request.setAttribute("keyword", keyword);
		}
		
		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어온다.
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		//전체 row 의 갯수를 DB 에서 얻어온다.
		int totalRow = FileDao.getInstance().getCount(dto);
		//전체 페이지의 갯수 구하기
		int totalPageCount = (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//시작 페이지 번호
		int startPageNum = 1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}				
				
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
				
		//파일 목록을 얻어온다.
		List<FileDto> list = FileDao.getInstance().getList(dto);
		// request 에 담는다.
		request.setAttribute("list", list);
		// 현재 페이지 번호
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		// 전체 페이지의 갯수
		request.setAttribute("totalPageCount", totalPageCount);
		
		// view 페이지로 forward 이동해서 파일 목록 출력
		return new ActionForward("/views/file/list.jsp");
	}

}
