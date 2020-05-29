package min.spring.mvc.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUpDownUtil {

    private String uploadPath = "c:/java/pdsupload/";
    // 파일 업로드시 저장된 경로 지정

    // 업로드 처리 메서드
    public Map<String, String> procUpload(
            HttpServletRequest req) {

        Map<String, String> frmdata = new HashMap<>();

        String fname = "";

        // 요청한 폼 데이터가 multipart 인지 확인
        RequestContext rctx = new ServletRequestContext(req);
        boolean isMultipart = FileUpload.isMultipartContent(rctx);

        try {
            if (isMultipart) { // 클라이언트의 요청이 multipart 라면
                DiskFileItemFactory df = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(df);

                // 클라이언트의 요청정보를 리스트에 저장
                List items = upload.parseRequest(req);
                Iterator<FileItem> params = items.iterator();

                // 리스트에 저장된 요청정보를 하나씩 꺼내서
                // 폼 데이터의 유형에 따라 각각 처리

                while (params.hasNext()) {
                    try {
                        FileItem item = (FileItem) params.next();

                        if (item.isFormField()) { // 텍스트 데이터라면
                            String name = item.getFieldName();
                            String val = item.getString("utf-8");
                            frmdata.put(name, val);
                        } else { // 파일 데이터라면
                            String ufname = item.getName(); // 파일 경로 추출

                            // 첨부파일이 없는 경우
                            if (ufname.equals("")|| ufname == null)
                                continue;

                            // ex) ufname => c:/java/jobs.txt
                            fname = ufname.substring(
                                    ufname.lastIndexOf("\\") + 1 ); // 파일명 추출

                            // ex) fname => jobs.txt
                            // 겹치지 않는 파일명을 위해 유니크한 임의의 값 생성1
                            // UUID uuid = UUID.randomUUID();

                            // 겹치지 않는 파일명을 위해 유니크한 임의의 값 생성2
                            String fmt = "yyyyMMddHHmmss";
                            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
                            String uuid = sdf.format(new Date());

                            String fnames[] = fname.split("[.]");
                            // fname = fnames[0] + uuid.toString() + "." + fnames[1];
                            fname = fnames[0] + uuid + "." + fnames[1];

                            // ex) fname => jobs123456789.txt
                            // ex) f => c:/java/pdsupload/jobs123456789.txt
                            File f = new File(uploadPath + "/" + fname);
                            item.write(f);  // 지정한 경로에 파일 저장

                            String name = item.getFieldName();
                            frmdata.put(name, fname);

                            // 파일 기타정보 처리
                            long fsize = item.getSize() / 1024;
                            String ftype = fnames[1];

                            frmdata.put(name + "size", fsize + "");
                            frmdata.put(name+"type",ftype);


                            // 파일명 처리 결과 확인
                            System.out.println(ufname + "/" +fname);
                            System.out.println(fsize + "/" +ftype);

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } // while
            } // if
        } catch (Exception ex) {
            ex.printStackTrace();
        } // try

        return frmdata;
    }

    // 다운로드 처리 메서드
}