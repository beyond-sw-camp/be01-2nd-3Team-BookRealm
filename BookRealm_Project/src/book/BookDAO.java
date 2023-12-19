package book;

import java.sql.*;

import ConnUtil.ConnectionSingletonHelper;

public class BookDAO {
	
	// 1.driver 연결 
	static Statement stmt = null;
    static ResultSet rs = null;
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    
    // 2. 계정 연결 
    public BookDAO() {
        conn = ConnectionSingletonHelper.getConnection("mariadb");
    }
	
	// 전체 출력 
    public ResultSet selectAll() throws SQLException {
    	
    	try {
    	//3. SQL 사용
    	stmt = conn.createStatement();
    	rs = stmt.executeQuery("SELECT * FROM BOOK");
    	
    	ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();  // db 테이블의 필드 갯수

		while( rs.next() ) {
			for (int i = 1; i <= count; i++) {  // 각 타입별로 출력하기  // db에서는 필드를 셀 때, 1부터 시작하기 때문에 i의 시작값이 1이다 
				
				switch (rsmd.getColumnType(i)) {
					case Types.NUMERIC :
					case Types.INTEGER :
						System.out.println(rsmd.getColumnName(i) + " : " + rs.getInt(i) + "  ");
						break;
					case Types.FLOAT :
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getFloat(i) + "  ");
						break;
					case Types.DOUBLE :
						System.out.println(rsmd.getColumnName(i) + " : " + (int)rs.getDouble(i) + "  ");
						break;
					case Types.CHAR :
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getString(i) + "  ");
						break;
					case Types.DATE :
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getDate(i) + "  ");
						break;
					default:
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getString(i) + "  ");
						break;
				} // switch end
			} // for end 
		} // while end 
    			
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		//4. 닫기 (자원 반환)
    		rs.close();
    		stmt.close();
    		conn.close();
    	}
		return rs;
	}
    
	// 선택 출력  
    public ResultSet selectBybookId(int bookId) throws SQLException {
    	
    	try {
    	//3. SQL 사용
    	pstmt = conn.prepareStatement("SELECT * FROM BOOK" + " WHERE bookId = ?");
    	pstmt.setInt(1, bookId);
    	rs = pstmt.executeQuery();
    	
    	ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		while( rs.next() ) {
			for (int i = 1; i <= count; i++) {  // 각 타입별로 출력하기
				
				switch (rsmd.getColumnType(i)) {
					case Types.NUMERIC :
					case Types.INTEGER :
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getInt(i) + "  ");
						break;
					case Types.FLOAT :
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getFloat(i) + "  ");
						break;
					case Types.DOUBLE :
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getDouble(i) + "  ");
						break;
					case Types.CHAR :
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getString(i) + "  ");
						break;
					case Types.DATE :
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getDate(i) + "  ");
						break;
					default:
						System.out.println(rsmd.getColumnName(i) + " : " +rs.getString(i) + "  ");
						break;
				} // switch end
			} // end for
			System.out.println();
		} // end while
    			
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		//4. 닫기 (자원 반환)
    		rs.close();
    		stmt.close();
    		conn.close();
    	}
    	return rs;
	}
    
    // 추가   
    public ResultSet insertBook(BookVO vo) throws SQLException {
    	
    	try {
    	//3. SQL 사용
    		pstmt = conn.prepareStatement("UPDATE BOOK SET VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? ) WHERE bookId = ?");
            pstmt.setInt(1, vo.getBookId());
            pstmt.setString(2, vo.getCategory());
            pstmt.setString(3, vo.getWriter());
            pstmt.setString(4, vo.getTitle());
            pstmt.setInt(5, vo.getPrice());
            pstmt.setInt(6, vo.getStock());
            pstmt.setInt(7, vo.getSales());
            pstmt.setDate(8, vo.getPublishDate());
            pstmt.setString(9, vo.getPublisher());
            int result = pstmt.executeUpdate();
		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		//4. 닫기 (자원 반환)
    		rs.close();
    		stmt.close();
    		conn.close();
    	}
    	return rs;
	}
    
    // 수정 
    public ResultSet updateBook(BookVO vo) throws SQLException {
    	
    	try {
    	//3. SQL 사용
    		pstmt = conn.prepareStatement("INSERT INTO BOOK VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            pstmt.setInt(1, vo.getBookId());
            pstmt.setString(2, vo.getCategory());
            pstmt.setString(3, vo.getWriter());
            pstmt.setString(4, vo.getTitle());
            pstmt.setInt(5, vo.getPrice());
            pstmt.setInt(6, vo.getStock());
            pstmt.setInt(7, vo.getSales());
            pstmt.setDate(8, vo.getPublishDate());
            pstmt.setString(9, vo.getPublisher());
            int result = pstmt.executeUpdate();
		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		//4. 닫기 (자원 반환)
    		rs.close();
    		stmt.close();
    		conn.close();
    	}
    	return rs;
	}
    
    // 삭제 
    public ResultSet deleteBook(int bookId) throws SQLException {
    	
    	try {
    	//3. SQL 사용
    		pstmt = conn.prepareStatement("DELETE FROM BOOK WHERE bookId = ?");
    		pstmt.setInt(1, bookId);
    		int result = pstmt.executeUpdate();
		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		//4. 닫기 (자원 반환)
    		rs.close();
    		stmt.close();
    		conn.close();
    	}
    	return rs;
	}
	
}
