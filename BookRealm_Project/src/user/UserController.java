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
        sc.nextLine();  // 개행문자 제거
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

    // 관리자 회원 정보 수정
    public void editUser() throws SQLException {
        System.out.print("수정할 회원의 아이디를 입력하세요 : ");  String userId = sc.next();
        UserVO vo = userDAO.selectById(userId);
        if(vo != null){
            vo.setUserId(userId);
            System.out.print("이름 입력[" + vo.getUsername() + "] : ");                     vo.setUsername(sc.next());
            System.out.print("주소 입력[" + vo.getAddress() + "] : ");                      vo.setAddress(sc.nextLine());  sc.nextLine();
            System.out.print("전화번호 입력[" + vo.getPhone() + "] : ");                      vo.setPhone(sc.next());
            System.out.print("관리자 전환 여부[" + vo.getAdminyn() + "](1: 관리자) : ");       vo.setAdminyn(sc.nextInt());
        }
        else{
            System.out.println("존재하지 않는 회원입니다.");
            return;
        }

        if(userDAO.updateAll(vo) > 0)
            System.out.println("회원 정보 수정이 완료 되었습니다.");
        else
            System.out.println("회원 정보 수정에 실패하였습니다.");
    }

    public void deleteUser() throws SQLException {
        System.out.print("삭제할 회원의 아이디를 입력하세요 : ");      String userId = sc.next();
        sc.nextLine();  // 개행문자 제거

        UserVO vo = userDAO.selectById(userId);
        if(vo != null){
           if(userDAO.delete(userId) > 0)
               System.out.println("회원 정보가 삭제되었습니다.");
           else
               System.out.println("회원 정보 삭제에 실패하였습니다.");
        }
        else{
            System.out.println("존재하지 않는 회원입니다.");
        }
    }


}
