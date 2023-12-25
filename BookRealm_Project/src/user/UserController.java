package user;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController {

    Scanner sc= new Scanner(System.in);
    UserDAO dao = new UserDAO();
    UserVO user;

    public void mainView() throws SQLException {
        int result;
        do {
            result = nView();
        } while(result == 1);




    }

    public int nView() throws SQLException {
        int result;
        int n;

        System.out.println("==========일반 회원==========");
        System.out.println("1. 로 그 인");
        System.out.println("2. 회 원 가 입");
        result =  sc.nextInt();

        switch (result) {
            case 1:
                login();
                return 1;
                break;
            case 2:
                join()
        }


    }
    public void login() throws SQLException {
        String id, passwd;
        int result;
        do {
            System.out.println("==========일반 회원 로그인==========");
            System.out.println("아이디를 입력하세요");
            id = sc.next();
            System.out.println("비밀번호를 입력하세요");
            passwd = sc.next();

            result = dao.login(id, passwd);
            if (result == 1) {
                user = dao.selectById(id);
            } else if (result == 0) {
                System.out.println("비밀번호가 일치하지 않습니다.");
            } else if (result == -1) {
                System.out.println("일치하는 아이디가 존재하지 않습니다.");
            }
        } while(result == 1);
    }

    public void join() throws SQLException {
        UserVO user;
        String id, name, passwd, address, phone;
        int result;
        System.out.println("==========회원가입==========");

        while (true){
            System.out.println("아이디를 입력하세요");
            id = sc.next();
            if(dao.selectById(id) != null) {
                System.out.println("이미 사용중인 아이디 입니다");
            } else break;
        }

        System.out.println("비밀번호를 입력하세요");
        passwd = sc.next();
        System.out.println("이름을 입력하세요");
        name = sc.next();
        System.out.println("주소를 입력하세요");
        address = sc.next();
        System.out.println("핸드폰 번호를 입력하세요");
        phone = sc.next();

        user = new UserVO(id, name, passwd, address, phone, "일반", 0);
        result = dao.join(user);

    }
}
