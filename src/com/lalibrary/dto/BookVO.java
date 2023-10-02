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
public class BookVO {
	private String book_id;
	private String library_id;	
	private String borrow_id;	
	private int book_type;
	private String book_name;
	private int price;
	private String buy_date;	
	private String borrow_status;
	private int borrow_count;
	
	
//	도서 주문
	public BookVO(String library_id, int book_type, String book_name, int price) {
		super();
		this.library_id = library_id;
		this.book_type = book_type;
		this.book_name = book_name;
		this.price = price;
	}
}
