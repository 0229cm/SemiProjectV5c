package min.spring.mvc.service;


import min.spring.mvc.dao.GalleryDAO;
import min.spring.mvc.vo.GalleryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gsrv")
public class GalleryService {

    private GalleryDAO gdao;

    @Autowired
    public GalleryService(GalleryDAO gdao) {
        this.gdao = gdao;
    }

    // 새 갤러리 쓰기
    public void newGallery(GalleryVO gvo) {
        gdao.insertGallery(gvo);
    }
}
