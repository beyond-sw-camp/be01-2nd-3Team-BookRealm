package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {

    Scanner sc= new Scanner(System.in);
    UserDAO dao = new UserDAO();
    UserVO user;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void mainView() throws SQLException, IOException {
        int result;
        do {
            result = nView();
        } while(result == 1);

        while(true) {
            result = userView();
            switch (result) {
                case 1:
                    do {
                        int i = info();
                        if(i == 1) userMod();
                        else break;
                    } while (true);
                    break;
                case 3:
                    int n = userDelete();
                    if(n==1) return;
                    break;
                case 4:
                    return;

            }
        }


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
            case 2:
                join();
                return 0;
        }
        return -1;
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
        if(result > 0) System.out.println("회원 등록에 성공하였습니다");
        else System.out.println("회원 등록을 실패하였습니다.");
    }

    private int userView() {
        System.out.println("==========" + user.getUsername() + "님 환영 합니다==========");
        System.out.println("1. 마 이 페 이 지");
        System.out.println("2. 리 뷰 작 성");
        System.out.println("3. 회 원 탈 퇴");
        return sc.nextInt();
    }

    private int info() {
        System.out.println("==========" + user.getUsername() + "님의 정보==========");
        System.out.println("아이디 : " + user.getUserId());
        System.out.println("이름 : " + user.getUsername());
        System.out.println("주소 : " + user.getAddress());
        System.out.println("휴대폰 번호 : " + user.getPhone() );
        System.out.println();
        System.out.println();
        System.out.println("정보 수정을 원하시면 1, 다시 돌아가려면 2를 눌러주세요");

        return 1;
    }

    private void userMod() throws SQLException, IOException {
        System.out.println("==========정보 수정==========");
        System.out.println("1. 이름 수정");
        System.out.println("2. 비밀번호 수정");
        System.out.println("3. 주소 수정");
        System.out.println("4. 핸드폰 번호 수정");

        int n = sc.nextInt();
        String s;
        int result;

        switch (n) {
            case 1:
                System.out.println("변경하실 이름을 입력해주세요");
                s = sc.next();
                result = dao.update("username",s,user.getUserId());
                if(result > 0) System.out.println("성공적으로 변경되었습니다.");
                break;
            case 2:
                while(true) {
                    System.out.println("현재 비밀 번호를 입력하세요");
                    s = sc.next();
                    if (user.getPasswd().equals(s)) {
                        System.out.println("바꾸실 비밀 번호를 입력하세요");
                        s=sc.next();
                        result = dao.update("passwd",s, user.getUserId());
                        if(result > 0) System.out.println("성공적으로 변경되었습니다.");
                        break;
                    } else {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                    }
                }
                break;
            case 3:
                System.out.println("변경하실 주소를 입력하세요");
                s = br.readLine();
                result = dao.update("address",s, user.getUserId());
                if(result > 0) System.out.println("성공적으로 변경되었습니다.");
                break;
            case 4:
                System.out.println("변경하실 핸드폰 번호를 입력하세요");
                s = sc.next();
                result = dao.update("address",s, user.getUserId());
                if(result > 0) System.out.println("성공적으로 변경되었습니다.");
                break;
            default:
                System.out.println("잘못된 번호를 선택하셨습니다.");
        }
    }

    public int userDelete() throws SQLException {
        System.out.println("정말로 탈퇴하시겠습니까?");
        System.out.println("1 : 예 2: 아니오");
        int n = sc.nextInt();
        if(n == 1) {
            int num = dao.delete(user.getUserId());
            if(num > 0) System.out.println("성공적으로 탈퇴하셨습니다.");
            return 1;
        } else return 0;
    }
}
