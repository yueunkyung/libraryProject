package com.lalibrary.view;

import java.util.List;

import com.lalibrary.dto.LibraryVO;

public class LibraryView {
	public static void print(String message) {
		System.out.println("===============================");
		System.out.println(message);
		System.out.println("===============================");
	}

	public static void print(LibraryVO book) {
		System.out.println("===============================");
		System.out.println(book);
		System.out.println("===============================");
	}

	public static void print(List<LibraryVO> booklist) {
		System.out.println("===============================");
		booklist.stream().forEach(book -> {
			System.out.println(book);
		});
		System.out.println("===============================");
	}
}
