package min.spring.mvc.dao;


import min.spring.mvc.vo.PdsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

    @Repository("pdao")
    public class PdsDAO {

    private JdbcTemplate jdbcTemplate;

    @Value("#{jdbc['insertPdsSQL']}") private String insertPdsSQL;
    @Value("#{jdbc['selectPdsSQL']}") private String selectPdsSQL;
    @Value("#{jdbc['selectOnePdsSQL']}") private String selectOnePdsSQL;
    @Value("#{jdbc['updateViewSQL']}")private String updateViewSQL;
    @Value("#{jdbc['updateDownSQL']}")private String updateDownSQL;

    @Autowired
    public PdsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 게시판 데이터를 board테이블에 저장
    public boolean insertPds( PdsVO p ) {
        // sql 정의
        // String sql = "insert into brd (title,userid,contents) values (?,?,?)";

        // 매개변수 정의
        Object[] params = new Object[] {
             p.getTitle(), p.getUserid(), p.getContents(),
                p.getFname(),p.getFsize(),p.getFdown(),p.getFtype()
        };

        // 매개변수 타입 정의 - 생략 ^^;

        // 샐행
        boolean isInsert = false;
        if (jdbcTemplate.update(insertPdsSQL, params) > 0)
            isInsert = true;

        return isInsert;
    }

    // 게시판데이터 중에서 글번호/제목/작성자/작성일/추천/조회만
    // 골라서 동적배열에 담아 반환함
    // 스프링에서는 RowMapper라는 클래스를 이용해서
    // select문의 결과를 처리할 수 있음
    public List<PdsVO> selectPds() {
        // String sql = "select bno,title,userid,regdate,thumbup,views from brd order by bno desc";

        RowMapper<PdsVO> mapper = new PdsRowMapper();
        // query 메서드를 통해 결과값을 가져올 때
        // 추출된 결과들에 따라 반복적으로 적용할 매핑 클래스

        // Mapper 클래스는 콜백함수처럼 작동되는데,
        // 결과집합에서 결과행이 존재할 때 마다
        // Mapper 클래스의 mapRow 메서드가 호출됨

        // 개발자가 작성하는 RowMapper 클래스는
        // RowMapper 인터페이스를 구현해서 생성해야함

        return jdbcTemplate.query(selectPdsSQL, mapper);
    }

    // 글번호로 선택한 게시물에 대해 모든 컬럼을 조회해서
    // BoardVO 객체에 저장하고 반환함
    public PdsVO selectOnePds(String pno) {
        // String sql = "select * from brd where bno = ?";

        Object[] params = new Object[] { pno };

        RowMapper<PdsVO> mapper = new PdsOneMapper();
        // ArrayList<BoardVO> bdlist = (ArrayList<BoardVO>)
        //         jdbcTemplate.query(sql, mapper, params);


        // return bdlist.get(0);
        PdsVO pvo = jdbcTemplate.queryForObject(selectOnePdsSQL,mapper,params);

        return pvo;
    }

    // 글번호로 본문글에 대한 조회수 증가
        public void updateViewsPds(String pno) {
            Object[] params = new Object[] {pno};

            jdbcTemplate.update(updateViewSQL, params);
        }



    // 글번호로 첨부파일에 대한 다운수 증가
         public void updateDownPds(String pno) {
             Object[] params = new Object[] {pno};

             jdbcTemplate.update(updateDownSQL, params);
        }
    // selectBoard의 RowMapper 내부 클래스
    private class PdsRowMapper implements RowMapper<PdsVO> {

        @Override
        public PdsVO mapRow(ResultSet rs, int num) throws SQLException {

            PdsVO pvo = new PdsVO(
                 rs.getString("pno"),
                 rs.getString("title"),
                 rs.getString("userid"),
                 rs.getString("regdate"),
                 rs.getString("thumbup"),
                    null,null,null,null,null,null
            );

            return pvo;
        }
    }

    // selectOneBoard의 RowMapper 내부 클래스
    private class PdsOneMapper implements RowMapper<PdsVO> {
        @Override
        public PdsVO mapRow(ResultSet rs, int num) throws SQLException {

            PdsVO pvo = new PdsVO(
                    rs.getString("pno"),
                    rs.getString("title"),
                    rs.getString("userid"),
                    rs.getString("regdate"),
                    rs.getString("thumbup"),
                    rs.getString("views"),
                    rs.getString("contents"),
                    rs.getString("fname"),
                    rs.getString("fsize"),
                    rs.getString("fdown"),
                    rs.getString("ftype")
            );

            return pvo;
        }
    }
}
