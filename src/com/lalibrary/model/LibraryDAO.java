package com.lalibrary.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.lalibrary.dto.LibraryVO;
import com.lalibrary.util.DBUtil;

public class LibraryDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	//1.통합도서검색-도서 이름으로 검색하기
	public List<LibraryVO> selectByBookTitle(String title){
		List<LibraryVO> booklist = new ArrayList<>();
		String sql = "SELECT * FROM BOOKS WHERE BOOK_NAME LIKE '%" + title + "%'";
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				LibraryVO book = makeBookList(rs);//reset에서 읽어서 VO만들기
				booklist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		return booklist;
	};

	public int selectOrderBook(LibraryVO bookInfo) {
//		String sql = "insert into books"
//					+ " values('"+bookInfo.getBook_id()+"','"+bookInfo.getLibrary_id()+"', null,'"+bookInfo.getBook_type()+"','"+
//					bookInfo.getBook_name()+"','"+bookInfo.getPrice()+"', TO_DATE(sysdate+3), 'N', 0)";
//		int count = 0;
//		conn = DBUtil.getConnection();
//		// ? : binding 변수 (Statement는 지원안함)
//		//Statement를 상속받은 하위 PreparedStatement
//		try {
//			st = conn.createStatement();
//			count = st.executeUpdate(sql); //DML은 executeUpdate()로 실행
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBUtil.dbDisConnection(conn, pst, rs);
//		}	
//		
//		return count;
		
//		##procedure
		int count = 0;
		String sql = "{call add_new_book(?, ?, ?, ?)}";
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, bookInfo.getLibrary_id());
			pst.setInt(2, bookInfo.getBook_type());
			pst.setString(3, bookInfo.getBook_name());
			pst.setInt(4, bookInfo.getPrice());

			count = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, pst, rs);
		}
				
		return count;		
	}
	
	
	public List<LibraryVO> selectPopularBook(){
		List<LibraryVO> bestlist = new ArrayList<>();
		String sql = "select *"
				+ " from ( select * from books"
				+ "       order by BORROW_COUNT desc nulls last)"
				+ " where rownum<=10";
		conn = DBUtil.getConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				LibraryVO bestbook = makeBookList(rs);//reset에서 읽어서 VO만들기
				bestlist.add(bestbook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnection(conn, st, rs);
		}
		
		return bestlist;
	}

	private LibraryVO makeBookList(ResultSet rs) throws SQLException {
		LibraryVO libr = new LibraryVO();
		libr.setBook_id( rs.getString(1));
		libr.setLibrary_id(rs.getString(2));
		libr.setBorrow_id(rs.getString(3));
		libr.setBook_type(rs.getInt(4));
		libr.setBook_name(rs.getString(5));
		libr.setPrice(rs.getInt(6));
		libr.setBuy_date(rs.getString(7));
		libr.setBorrow_status (rs.getString(8));
		libr.setBorrow_count (rs.getInt(9));
		
		return libr;
	}
}

	
