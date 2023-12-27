package BookSearchProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BSP_BookSearchByPage {

	private static Scanner sc = new Scanner(System.in);
	private static BSP_DAO dao = new BSP_DAO();

	public static int BookSearchByMenu() {

		StringBuffer sb = new StringBuffer();
		int select = 0;

		sb.append("\n");
		sb.append("<< 카테고리/제목/작가별 검색 페이지 >>\n");
		sb.append("\n");
		sb.append("1. 카테고리별 검색\n");
		sb.append("2. 제목별 검색\n");
		sb.append("3. 작가별 검색\n");
		sb.append("4. 이전 페이지로 이동\n");
		System.out.println(sb);
		System.out.print("번호 선택 : ");
		select = sc.nextInt();

		return select;
	}

	public static void choice_2() throws SQLException {

		int no = 0;

		do {
			no = BookSearchByMenu();
			switch (no) {
			case 1:
				System.out.println("카테고리별 검색을 선택하셨습니다.");
				System.out.println();
				selectBycategory();
				System.out.println();
				viewDetails();
				break;
			case 2:
				System.out.println("제목별 검색을 선택하셨습니다.");
				System.out.println();
				selectBytitle();
				System.out.println();
				viewDetails();
				break;
			case 3:
				System.out.println("작가별 검색을 선택하셨습니다.");
				System.out.println();
				selectBywriter();
				System.out.println();
				viewDetails();
				break;
			case 4:
				System.out.println("이전 페이지로 이동합니다.");
				return;
			default:
				System.out.println("없는 번호입니다. 다시 입력해주세요.");
				break;
			}
		} while (no != 4);
	}

	private static void selectBycategory() throws SQLException {
		System.out.print("카테고리 입력 : ");
		sc.nextLine();
		String c = sc.nextLine();
		System.out.println("============================");

		ArrayList<BSP_VO> list = (ArrayList<BSP_VO>) dao.selectBycategory(c);

		for (BSP_VO vo : list) {
			System.out.println("도서코드 : " + vo.getBookId());
			System.out.println("카테고리 : " + vo.getCategory());
			System.out.println("제목 : " + vo.getTitle());
			System.out.println("작가 : " + vo.getWriter());
			System.out.println("============================");
		}

	}

	private static void selectBytitle() throws SQLException {
		System.out.print("제목 입력 : ");
		sc.nextLine();
		String t = sc.nextLine();
		System.out.println("============================");

		ArrayList<BSP_VO> list = (ArrayList<BSP_VO>) dao.selectBytitle(t);

		for (BSP_VO vo : list) {
			System.out.println("도서코드 : " + vo.getBookId());
			System.out.println("카테고리 : " + vo.getCategory());
			System.out.println("제목 : " + vo.getTitle());
			System.out.println("작가 : " + vo.getWriter());
			System.out.println("============================");
		}

	}

	private static void selectBywriter() throws SQLException {
		System.out.print("작가 입력 : ");
		sc.nextLine();
		String w = sc.nextLine();
		System.out.println("============================");

		ArrayList<BSP_VO> list = (ArrayList<BSP_VO>) dao.selectBywriter(w);

		for (BSP_VO vo : list) {
			System.out.println("도서코드 : " + vo.getBookId());
			System.out.println("카테고리 : " + vo.getCategory());
			System.out.println("제목 : " + vo.getTitle());
			System.out.println("작가 : " + vo.getWriter());
			System.out.println("============================");
		}

	}

	private static void viewDetails() throws SQLException {
		System.out.print("상세히 보고 싶은 도서의 도서코드 입력 : ");
		int bookId = sc.nextInt();
		System.out.println("============================");

		ArrayList<BSP_VO> list = (ArrayList<BSP_VO>) dao.viewDetails(bookId);

		for (BSP_VO vo : list) {
			System.out.println("도서코드 : " + vo.getBookId());
			System.out.println("카테고리 : " + vo.getCategory());
			System.out.println("제목 : " + vo.getTitle());
			System.out.println("작가 : " + vo.getWriter());
			System.out.println("출판사 : " + vo.getPublisher());
			System.out.println("출판일 : " + vo.getPublishDate());
			System.out.println("가격 : " + vo.getPrice());
			System.out.println("판매랑 : " + vo.getSales());
			System.out.println("재고 : " + vo.getStock());
			System.out.println("============================");
		}

	}

}
