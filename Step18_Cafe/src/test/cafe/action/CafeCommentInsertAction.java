package test.cafe.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.cafe.dao.CafeCommentDao;
import test.cafe.dto.CafeCommentDto;

import test.controller.Action;
import test.controller.ActionForward;
/*
 * 	덧글 저장하는 액션
 */
public class CafeCommentInsertAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String condition = request.getParameter("condition");
		String keyword = request.getParameter("keyword");
		
		//send redirect 할 때, 한글깨짐을 방지하기 위해 encode 를 해야 한다!!
		try {
			keyword = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
								
		//1. 파라미터로 전달되는 덧글 정보 읽어오기
		String writer=request.getParameter("writer");
		int ref_group=Integer.parseInt
				(request.getParameter("ref_group"));
		String target_id=request.getParameter("target_id");
		String content=request.getParameter("content");
		//덧글 내에서의 그룹번호를 읽어온다. 
		//null 이면 원글에 대한 덧글이고 아니면 덧글에 대한 덧글이다.
		String comment_group=request.getParameter("comment_group");
		//저장할 덧글 번호를 미리 읽어온다.(저장할 때, 덧글번호가 필요하기 때문에 번호를 미리 얻는다)
		int seq=CafeCommentDao.getInstance().getSequence();
		
		//2. 새 덧글 정보를 dto 에 담고
		CafeCommentDto dto=new CafeCommentDto();
		dto.setNum(seq);
		dto.setWriter(writer);
		dto.setTarget_id(target_id);
		dto.setContent(content);
		dto.setRef_group(ref_group);
		if(comment_group==null){//원글에 대한 덧글인 경우
			//덧글의 그룹번호를 덧글의 글번호와 같게 설정한다.
			dto.setComment_group(seq);
		}else{//덧글의 덧글인 경우 
			//파라미터로 넘어온 덧글의 그룹번호를 넣어준다.
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		//3. DB 에 저장하고
		CafeCommentDao.getInstance().insert(dto);
		//4. 글 자세히 보기로 다시 리다일렉트 시킨다. (리다일렉트 할때는 request에 담아서 보낼 수 없으므로, parameter로 보내야 한다.)
		return new ActionForward("/cafe/detail.do?num="+ref_group+"&condition="+condition+"&keyword="+keyword, true);
	}

}
