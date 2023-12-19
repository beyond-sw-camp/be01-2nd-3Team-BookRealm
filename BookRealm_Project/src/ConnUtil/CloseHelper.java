package ConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CloseHelper {
    public static void close(Connection conn){
        if(conn !=null){
            try {
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public static void close(Statement stmt){
        if(stmt !=null){
            try {
                stmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public static void close(PreparedStatement pstmt){
        if(pstmt !=null){
            try {
                pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public static void close(ResultSet rs){
        if(rs !=null){
            try {
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        close(conn);
        close(pstmt);
        close(rs);
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        close(conn);
        close(stmt);
        close(rs);
    }
}
