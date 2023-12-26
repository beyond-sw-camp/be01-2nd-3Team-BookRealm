package user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {

    Scanner sc = new Scanner(System.in);

    private UserDAO userDAO = new UserDAO();

    // 회원 전체 조회
    public void showUserAll() throws SQLException {
        ArrayList<UserVO> list = userDAO.selectAll();
        for(UserVO vo : list){
            System.out.println("아이디 : " + vo.getUserId());
            System.out.println("이름 : " + vo.getUsername());
            System.out.println("전화번호 : " + vo.getPhone());
            System.out.println("주소 : " + vo.getAddress());
            System.out.println("가입수단(k: 카카오 / a: 애플 / g: 구글...) : " + vo.getSuType());
            System.out.println("관리자 여부 : " + vo.getAdminyn());
            System.out.println("----------------------------------------------------");
        }
        System.out.println("----------------------------------------------------");
    }

    // 특정 아이디 검색
    public void showUserById() throws SQLException {
        System.out.print("조회할 회원의 아이디를 입력하세요 : ");      String id = sc.next();
        UserVO vo = userDAO.selectById(id);
        if(vo != null){
            System.out.println("아이디 : " + vo.getUserId());
            System.out.println("이름 : " + vo.getUsername());
            System.out.println("전화번호 : " + vo.getPhone());
            System.out.println("주소 : " + vo.getAddress());
            System.out.println("가입수단(k: 카카오 / a: 애플 / g: 구글...) : " + vo.getSuType());
            System.out.println("관리자 여부 : " + vo.getAdminyn());
            System.out.println("----------------------------------------------------");
        }
        else{
            System.out.println("존재하지 않는 회원입니다.");
        }
        System.out.println("----------------------------------------------------");
    }


}
