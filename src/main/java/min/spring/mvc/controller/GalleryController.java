package min.spring.mvc.controller;

import min.spring.mvc.service.GalleryService;
import min.spring.mvc.vo.GalleryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GalleryController {

    private GalleryService gsrv;

    @Autowired
    public GalleryController(GalleryService gsrv) {
        this.gsrv = gsrv;
    }

    // 목록보기
    @RequestMapping(value = "gallery/list")
    public ModelAndView list() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../gallery/list.jsp");

        // 뷰로 넘길 데이터를 modelandview 객체에 담음

        mv.addObject("glist", gsrv.showGallery());

        return mv;
    }



    // 새글쓰기
    @RequestMapping(value = "gallery/write")
    public ModelAndView write(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../gallery/write.jsp");



        return mv;


    }

    // 새글쓰기
    // 여러개의 이미지를 업로드하는 경우
    // 이미지 폼 이름은 모두 동일하게 설정
    // MultipartFile[] img1, MultipartFile[] img2,
    // MultipartFile[] img3 식으로 사용하는 것보다는
    // => MultipartFile[] img 식으로 사용하는 것이 더 편하기 때문임
    @PostMapping(value = "gallery/write")
    public String wirteok (GalleryVO gvo, MultipartFile[] img) {

        gsrv.newGallery(gvo, img);

        return "redirect:/gallery/list";

    }

    @RequestMapping(value = "gallery/view")
    public ModelAndView view(String gno) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../gallery/view.jsp");

        mv.addObject("g", gsrv.showOneGallery(gno));

        return mv;
    }
}

