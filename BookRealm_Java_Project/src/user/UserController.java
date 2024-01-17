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
        int op = 0;
        int result = 0;
        if(vo != null){
            System.out.println("[" + vo.getUserId() + ", " + vo.getUsername() + "] 유저 정보를 수정합니다.");
            System.out.println("1 : 관리자 권한 전환");
            System.out.println("2 : 비밀번호 초기화");
            System.out.println("9 : EXIT");
            while (true) {
                System.out.print(">>> ");
                op = sc.nextInt();
                switch (op) {
                    case 1 :
                        System.out.print("관리자 권한 수정(0: 일반 / 1: 관리자) : ");   int yn = sc.nextInt();
                        result = userDAO.updateByAdmin(op,yn,vo.getUserId());
                        if(result > 0)
                            System.out.println("회원 정보 수정이 완료 되었습니다.");
                        else
                            System.out.println("회원 정보 수정에 실패하였습니다.");
                        return;
                    case 2 :
                        System.out.print("사용자 비밀번호 변경 : ");   String passwd = sc.next();
                        result = userDAO.updateByAdmin(op,passwd,vo.getUserId());
                        if(result > 0)
                            System.out.println("회원 정보 수정이 완료 되었습니다.");
                        else
                            System.out.println("회원 정보 수정에 실패하였습니다.");
                        return;
                    case 9 :
                        return;
                    default:
                        System.out.println("선택할 수 없는 옵션입니다.");
                        break;
                }


            }
        }
        else{
            System.out.println("존재하지 않는 회원입니다.");
        }


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

    public void adminUserMenu() throws SQLException {
        int op = 0;

        while (true) {
            System.out.println("============= 회원 관리 모드 =============");
            System.out.println("1: 회원 전체 조회");
            System.out.println("2: 회원 조회");
            System.out.println("3: 회원 정보 수정");
            System.out.println("4: 회원 정보 삭제");
            System.out.println("9: EXIT");
            System.out.print(">>>  ");    op = sc.nextInt();

            switch (op) {
                case 1:
                    showUserAll();
                    break;
                case 2:
                    showUserById();
                    break;
                case 3:
                    editUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }

    }
}
