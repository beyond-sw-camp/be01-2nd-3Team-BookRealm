package BookSearchProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BSP_BookSearchPage {

	private static Scanner sc = new Scanner(System.in);
	private static BSP_DAO dao = new BSP_DAO();

	public static int BookSearchMenu() {

		StringBuffer sb = new StringBuffer();
		int select = 0;

		sb.append("\n");
		sb.append("<< 도서 검색 페이지 >>\n");
		sb.append("\n");
		sb.append("1. 카테고리/제목/작가별 검색\n");
		sb.append("2. 베스트셀러 보기\n");
		sb.append("3. 이전 페이지로 이동\n");
		System.out.println(sb);
		System.out.print("번호 선택 : ");
		select = sc.nextInt();

		return select;
	}

	public static void choice_1() throws SQLException {

		int no = 0;

		do {
			no = BookSearchMenu();
			switch (no) {
			case 1:
				System.out.println("카테고리/제목/작가별 검색을 선택하셨습니다.");
				BSP_BookSearchByPage.choice_2();
				System.out.println();
				break;
			case 2:
				System.out.println("베스트셀러 보기를 선택하셨습니다.");
				System.out.println();
				System.out.println("<< 베스트셀러 페이지 >>");
				System.out.println("============================");
				bestseller();
				System.out.println();
				break;
			case 3:
				System.out.println("이전 페이지로 이동합니다.");
				return;
			default:
				System.out.println("없는 번호입니다. 다시 입력해주세요.");
			}
		} while (no != 3);
	}

	private static void bestseller() throws SQLException {
		ArrayList<BSP_VO> list = (ArrayList<BSP_VO>) dao.bestseller();

		for (BSP_VO vo : list) {
			System.out.println("도서코드 : " + vo.getBookId());
			System.out.println("카테고리 : " + vo.getCategory());
			System.out.println("제목 : " + vo.getTitle());
			System.out.println("작가 : " + vo.getWriter());
			System.out.println("============================");
		}

	}

}
