package com.lalibrary.model;

import java.util.List;

import com.lalibrary.dto.LibraryVO;

public class LibraryService {
	LibraryDAO dao = new LibraryDAO();
	
	public List<LibraryVO> selectByBookTitle(String title){
		return dao.selectByBookTitle(title);
	}
	public int selectOrderBook(LibraryVO book) {		
		return dao.selectOrderBook(book);
	}
	public List<LibraryVO> selectPopularBook(){
		return dao.selectPopularBook();
	}
}
