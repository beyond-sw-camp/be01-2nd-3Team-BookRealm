package BookSearchProgram;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnUtil.CloseHelper;
import ConnUtil.ConnectionSingletonHelper;

public class BSP_DAO {

	static Statement stmt = null;
	static ResultSet rs = null;
	static Connection conn = null;
	static PreparedStatement pstmt = null;

	public BSP_DAO() {
		conn = ConnectionSingletonHelper.getConnection("mariadb");
	}

	// 도서 베스트셀러(5개) 조회
	public List<BSP_VO> bestseller() throws SQLException {
		ArrayList<BSP_VO> list = new ArrayList<>();
		BSP_VO vo = null;

		try {
			// 작업 객체 생성
			stmt = conn.createStatement();

			// 쿼리문 준비 - select
			String sql = "SELECT BOOKID, CATEGORY, TITLE, WRITER FROM BOOK ORDER BY SALES DESC LIMIT 5";

			// 실행 및 리턴
			rs = stmt.executeQuery(sql);

			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount(); // db 테이블의 필드 갯수

			while (rs.next()) {

				vo = new BSP_VO();

				vo.setBookId(rs.getInt("bookId"));
				vo.setCategory(rs.getString("category"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));

				list.add(vo);

			} // while end

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 닫기 (자원 반환)
//			CloseHelper.close(rs);
//			CloseHelper.close(stmt);
//			CloseHelper.close(conn);
		}
		return list;
	}

	// 카테고리로 도서 조회
	public List<BSP_VO> selectBycategory(String category) throws SQLException {
		ArrayList<BSP_VO> list = new ArrayList<>();
		BSP_VO vo = null;

		// 쿼리문 준비 - select
		String sql = "SELECT * FROM BOOK WHERE category = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount(); // db 테이블의 필드 갯수

			while (rs.next()) {
				
				vo = new BSP_VO();

				vo.setBookId(rs.getInt("bookId"));
				vo.setCategory(rs.getString("category"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				
				list.add(vo);

			} // while end

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 닫기 (자원 반환)
//			CloseHelper.close(rs);
//			CloseHelper.close(stmt);
//			CloseHelper.close(conn);
		}
		return list;
	}

	// 제목으로 도서 조회
	public List<BSP_VO> selectBytitle(String title) throws SQLException {
		ArrayList<BSP_VO> list = new ArrayList<>();
		BSP_VO vo = null;

		// 쿼리문 준비 - select
		String sql = "SELECT * FROM BOOK WHERE title = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount(); // db 테이블의 필드 갯수

			while (rs.next()) {

				vo = new BSP_VO();

				vo.setBookId(rs.getInt("bookId"));
				vo.setCategory(rs.getString("category"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				
				list.add(vo);

			} // while end

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 닫기 (자원 반환)
//			CloseHelper.close(rs);
//			CloseHelper.close(stmt);
//			CloseHelper.close(conn);
		}
		return list;
	}

	// 작가로 도서 조회
	public List<BSP_VO> selectBywriter(String writer) throws SQLException {
		ArrayList<BSP_VO> list = new ArrayList<>();
		BSP_VO vo = null;

		// 쿼리문 준비 - select
		String sql = "SELECT * FROM BOOK WHERE writer = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount(); // db 테이블의 필드 갯수

			while (rs.next()) {

				vo = new BSP_VO();

				vo.setBookId(rs.getInt("bookId"));
				vo.setCategory(rs.getString("category"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				
				list.add(vo);

			} // while end

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 닫기 (자원 반환)
//			CloseHelper.close(rs);
//			CloseHelper.close(stmt);
//			CloseHelper.close(conn);
		}
		return list;
	}
	
	// 도서 상세보기
	public List<BSP_VO> viewDetails(int bookId) throws SQLException {
		ArrayList<BSP_VO> list = new ArrayList<>();
		BSP_VO vo = null;

		// 쿼리문 준비 - select
		String sql = "SELECT * FROM BOOK WHERE bookId = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount(); // db 테이블의 필드 갯수

			while (rs.next()) {

				vo = new BSP_VO();

				vo.setBookId(rs.getInt("bookId"));
				vo.setCategory(rs.getString("category"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setPrice(rs.getInt("price"));
				vo.setStock(rs.getInt("stock"));
				vo.setSales(rs.getInt("sales"));
				vo.setPublishDate(rs.getDate("publishDate"));
				vo.setPublisher(rs.getString("publisher"));
				
				list.add(vo);

			} // while end

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 닫기 (자원 반환)
//			CloseHelper.close(rs);
//			CloseHelper.close(stmt);
//			CloseHelper.close(conn);
		}
		return list;
	}

}
