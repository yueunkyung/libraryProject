package com.lalibrary.controller;

import java.util.List;
import java.util.Scanner;

import com.lalibrary.dto.LibraryVO;
import com.lalibrary.model.LibraryService;
import com.lalibrary.view.LibraryView;

public class LIbraryController {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		LibraryService service = new LibraryService();
		boolean isRun = true;
		while(isRun) {
			menu();
			int job = Integer.parseInt(sc.nextLine());
			switch (job) {
			case 1: {
				System.out.print("bookTitle>>");
//				sc.nextLine();
				String title = sc.nextLine();
				List<LibraryVO> booklist = service.selectByBookTitle(title);
				if(booklist.isEmpty()) { 
					isRun = orderBook(sc, service);					
				} else {
					LibraryView.print(booklist);
				}
				break;
			}				
			case 2: {
				List<LibraryVO> bestlist = service.selectPopularBook();
				LibraryView.print(bestlist);
				break;
			}
			case 7:
				isRun = false;
				break;
			default:
				break;
			}
		}
		System.out.println("프로그램 종료");
		sc.close();
	}
	
	public static void menu() {
		System.out.println("1.통합도서검색");
		System.out.println("2.베스트 도서 TOP10 조회");
		System.out.println("3.사물함 조회");
		System.out.println("4.열람실 좌석 조회");
		System.out.println("5.분실물 보관센터");
		System.out.println("6.마이페이지");
		System.out.println("7.종료");
		System.out.print("작업선택>>");
	}

	public static boolean orderBook(Scanner sc, LibraryService service) {
		boolean isrun = true;
		System.out.println("찾으시는 책이 없나요? 희망 도서를 신청하시겠습니까?");
		System.out.println("1.책 주문 2.종료");
		System.out.print("작업선택>>");
		int orderbook = sc.nextInt();
		switch (orderbook) {
			case 1:{
				System.out.println("도서 상세 정보를 작성하세요.");
				System.out.print("도서관ID>>");
				String libraryId = sc.next();
				System.out.print("책Type>>");
				int bookType = sc.nextInt();
				sc.nextLine();
				System.out.print("책이름>>");
				String bookName = sc.nextLine();
				System.out.print("가격>>");
				int price = sc.nextInt();
				sc.nextLine();
	
				LibraryVO writeBookInfo = new LibraryVO(libraryId, bookType, bookName, price);
				int count = service.selectOrderBook(writeBookInfo);
				if(count>0) {
					LibraryView.print("주문하신 책은 오늘 날짜 기준 3일 후에 입고됩니다.");
				}
				else {
					LibraryView.print("도서 정보가 잘못되었습니다.");
					isrun = false;
				}
				break;
			}
			case 2: isrun = false; break;
		}
		return isrun; //return이 true일때 종료
	}
}