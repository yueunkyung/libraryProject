package com.lalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryVO {

	//조회할 목적 ! 방이 필요해서 그만큼 변수 생성
	private String book_id;	//1
	private String library_id;	//2
	private String borrow_id;	//3
	private int book_type;	//4
	private String book_name;	//5
	private int price;	//6
	private String buy_date;	//7
	private String borrow_status;	//8
	private int borrow_count;	//9
	public LibraryVO(String book_id, String library_id, int book_type, String book_name, int price) {
		super();
		this.book_id = book_id;
		this.library_id = library_id;
		this.book_type = book_type;
		this.book_name = book_name;
		this.price = price;
	}
	public LibraryVO(String library_id, int book_type, String book_name, int price) {
		super();
		this.library_id = library_id;
		this.book_type = book_type;
		this.book_name = book_name;
		this.price = price;
	}
	
	
}
