package min.spring.mvc.service;

import min.spring.mvc.dao.BDReplyDAO;
import min.spring.mvc.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("brsrv")
public class BDReplyService {

    private BDReplyDAO brdao;

    @Autowired
    public BDReplyService(BDReplyDAO brdao) {
        this.brdao = brdao;
    }

    // 댓글쓰기
    public void makeReply(ReplyVO rvo) {
        brdao.insertReply(rvo);
    }


    // 댓글과 대댓글 가져오기
    public ArrayList<ReplyVO> showReply(String bno) {
        return (ArrayList<ReplyVO>)brdao.selectReply(bno);
    }

    // 대댓글 쓰기
    public void makecomment(ReplyVO rvo) {
        brdao.insertComment(rvo);
    }
}
