package min.spring.mvc.service;

import min.spring.mvc.dao.PdsDAO;
import min.spring.mvc.vo.PdsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service("psrv")
public class PdsService {

    private PdsDAO pdao;

    @Autowired
    public PdsService(PdsDAO pdao) {
        this.pdao = pdao;
    }

    public String newPds(PdsVO p,
                         Map<String, String> frmdata) {
        String result = "데이터 입력 실패!";

        procFormdata(p, frmdata);
        p.setFdown("0");

        if (pdao.insertPds(p))
            result = "데이터 입력 성공!!";

        System.out.println(result);
        // result 변수 값을 WAS 콘솔에 로그형태로 출력

        return result;
    }

    public ArrayList<PdsVO> showPds() {
        return (ArrayList<PdsVO>)pdao.selectPds();
    }

    public PdsVO showOnePds(String pno) {
        pdao.updateViewsPds(pno); // 조회수 증가
        return pdao.selectOnePds(pno);
    }

    // 첨부파일 다운수 처리
    public void modifyDown(String pno) {
        pdao.updateDownPds(pno);

    }

    // multipart 폼 데이터 처리
    private void procFormdata(PdsVO p, Map<String, String > frmdata) {

        for(String key:frmdata.keySet()) {
            String val = frmdata.get(key);
            switch (key) {
                case "title": p.setTitle(val); break;
                case "userid": p.setUserid(val); break;
                case "contents": p.setContents(val); break;

                case "file1": p.setFname(val); break;
                case "file1size": p.setFsize(val); break;
                case "file1type": p.setFtype(val); break;
            }
        }
    }

}
