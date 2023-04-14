package main.java.com.example.demo.dao;

import com.example.demo.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private String jdbcURL = "jdbc:mysql://localhost/jdbc_demo";
    private String jdbcUsername = "root";
    private String jdbcPassword = ""; // your password

    private static final String SELECT_ALL_BOOKS = "SELECT * FROM Book";
    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM BOOK WHERE bookcode=?";
    private static final String INSERT_BOOKS_SQL = "INSERT INTO book VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOKS_SQL = "UPDATE book SET title=?,author=?,category=?,approved=? WHERE bookcode=?";
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM Book WHERE bookcode=?";

    public BookDAO(){};

    protected Connection getConnection(){
        Connection connection = null;
        try{
            // MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public List<Book> selectAllBooks(){
        List<Book> books = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int bookcode = rs.getInt("bookcode");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String category = rs.getString("category");
                int approved = rs.getInt("approved");
                books.add(new Book(bookcode, title, author, category, approved == 0 ? false : true));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return books;
    }

    public Book selectBook(int bookcode){
        Book book = new Book();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);
            preparedStatement.setInt(1, bookcode);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                book.setBookcode(rs.getInt("bookcode"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setApproved(rs.getInt("approved") != 0 ? true : false);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return book;
    }
    public void insertBook(Book book){
        try{
            System.out.println("Executed");
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKS_SQL);
            preparedStatement.setInt(1, book.getBookcode());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getCategory());
            preparedStatement.setInt(5, book.isApproved() ? 1 : 0);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        // return error
    }
    public void updateBook(Book book){
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKS_SQL);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getCategory());
            preparedStatement.setInt(4, book.isApproved() ? 1 : 0);
            preparedStatement.setInt(5, book.getBookcode());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        // return error
    }
    public void deleteBook(int bookcode){
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_BY_ID);
            preparedStatement.setInt(1, bookcode);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        // return error
    }
    public int getNextElementId(){
        String sql = "SELECT LAST_INSERT_ID()";
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                int lastId = rs.getInt(1);
                return lastId + 1;
            }
            else{
                throw new SQLException("No ID obtained.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
