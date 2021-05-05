package com.semantics.thrillio;

import com.semantics.thrillio.constants.BookGenre;
import com.semantics.thrillio.constants.Gender;
import com.semantics.thrillio.constants.MovieGenre;
import com.semantics.thrillio.constants.UserType;
import com.semantics.thrillio.entities.Bookmark;
import com.semantics.thrillio.entities.User;
import com.semantics.thrillio.entities.UserBookmark;
import com.semantics.thrillio.managers.BookmarkManager;
import com.semantics.thrillio.managers.UserManager;
import com.semantics.thrillio.util.IOUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static List<User> users = new ArrayList<>();
    private static List<List<Bookmark>> bookmarks = new ArrayList<>();
    private static List<UserBookmark> userBookmarks = new ArrayList<>();

    public static void loadData(){

//        loadUsers();
//        loadWebLinks();
//        loadBooks();
//        loadMovies();

        try {
            new com.mysql.jdbc.Driver();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jid_thrillio?useSSL=false", "devuser", "");
             Statement stmt = conn.createStatement();) {

            loadUsers(stmt);    // Student is expected to implement this method
            loadWebLinks(stmt); // Student is expected to implement this method
            loadMovies(stmt);
            loadBooks(stmt);

        }catch (SQLException e){

            e.printStackTrace();
        }
    }



    public static List<User> getUsers() {
        return users;
    }

    public static List<List<Bookmark>> getBookmarks() {
        return bookmarks;
    }

    private static void loadMovies(Statement stmt) throws SQLException {
        String query = "Select m.id, title, release_year, GROUP_CONCAT(DISTINCT a.name SEPARATOR ',') AS cast, GROUP_CONCAT(DISTINCT d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
                + " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
                + "where m.id = ma.movie_id and ma.actor_id = a.id and "
                + "m.id = md.movie_id and md.director_id = d.id group by m.id";
        ResultSet rs = stmt.executeQuery(query);

        List<Bookmark> bookmarkList = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int releaseYear = rs.getInt("release_year");
            String[] cast = rs.getString("cast").split(",");
            String[] directors = rs.getString("directors").split(",");
            int genre_id = rs.getInt("movie_genre_id");
            MovieGenre genre = MovieGenre.values()[genre_id];
            double imdbRating = rs.getDouble("imdb_rating");

            Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, releaseYear, cast, directors, genre, imdbRating);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }
    private static void loadBooks(Statement stmt) throws SQLException {
//        bookmarks[2][0] = BookmarkManager.getInstance().createBook(4000, "Walden",1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY,4.3);
//        bookmarks[2][1] = BookmarkManager.getInstance().createBook(4001, "Self-Reliance and Other Essays",1993, "Dover Publications", new String[]{"Ralph Waldo Emerson"}, BookGenre.PHILOSOPHY, 4.5);
//        bookmarks[2][2] = BookmarkManager.getInstance().createBook(4002, "Light From Many Lamps", 1988, "Touchstone", new String[]{"Lillian Eichler Watson"}, BookGenre.PHILOSOPHY, 5.0);
//        bookmarks[2][3] = BookmarkManager.getInstance().createBook(4003, "Head First Design Patterns", 2004,"O'Reilly Media", new String[]{"Eric Freeman", "Bert Bates", "Kathy Sierra", "Elisabeth Robson"}, BookGenre.TECHNICAL, 4.5);
//        bookmarks[2][4] = BookmarkManager.getInstance().createBook(4004, "Effective Java Programming Language Guide", 2007, "Prentice Hall", new String[]{"Joshua Bloch"}, BookGenre.TECHNICAL, 4.9);
        String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
                + " from Book b, Publisher p, Author a, Book_Author ba "
                + "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
        ResultSet rs = stmt.executeQuery(query);

        List<Bookmark> bookmarkList = new ArrayList<>();

        while (rs.next()){

            long id = rs.getLong("id");
            String title = rs.getString("title");
            int publicationYear = rs.getInt("publication_year");
            String publisher = rs.getString("name");
            String[] authors = rs.getString("authors").split(",");
            int genre_id = rs.getInt("book_genre_id");
            BookGenre genre = BookGenre.values()[genre_id];
            double amazonRating = rs.getDouble("amazon_rating");

            Date createdDate = rs.getDate("created_date");
            System.out.println("createdDate: " + createdDate);
            Timestamp timeStamp = rs.getTimestamp(8);
            System.out.println("timeStamp: " + timeStamp);
            System.out.println("localDateTime: " + timeStamp.toLocalDateTime());

            System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);

            Bookmark bookmark = BookmarkManager.getInstance().createBook(id, title, publicationYear, publisher, authors, genre, amazonRating/*, values[7]*/);
            bookmarkList.add(bookmark);
        }

        bookmarks.add(bookmarkList);

    }

    private static void loadWebLinks(Statement stmt) throws SQLException {

//        bookmarks[0][0] = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
//        bookmarks[0][1] = BookmarkManager.getInstance().createWeblink(2001, "How do I import a pre-existing Java project into Eclipse and get up and running?", "http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running", "http://www.stackoverflow.com");
//        bookmarks[0][2] = BookmarkManager.getInstance().createWeblink(2002, "Interface vs Abstract Class", "http://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com");
//        bookmarks[0][3] = BookmarkManager.getInstance().createWeblink(2003, "NIO tutorial by Greg Travis", "http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf", "http://cs.brown.edu");
//        bookmarks[0][4] = BookmarkManager.getInstance().createWeblink(2004,	"Virtual Hosting and Tomcat", "http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org");
        String query = "Select * from WebLink";
        ResultSet rs = stmt.executeQuery(query);

        List<Bookmark> bookmarkList = new ArrayList<>();

        while (rs.next()){

            long id = rs.getLong("id");
            String title = rs.getString("title");
            String url = rs.getString("url");
            String host = rs.getString("host");

            Bookmark bookmark = BookmarkManager.getInstance().createWeblink(id, title, url, host);
            bookmarkList.add(bookmark);
        }

        bookmarks.add(bookmarkList);
    }

    private static void loadUsers(Statement stmt) throws SQLException {

//        users[0] = UserManager.getInstance().createUser(1000, "user0@semanticsquare.com", "test", "John", "M", Gender.MALE, USER);
//        users[1] = UserManager.getInstance().createUser(1001,"user1@semanticsquare.com", "test", "Sam", "M", Gender.MALE,UserType.USER);
//        users[2] = UserManager.getInstance().createUser(1002, "user2@semanticsquare.com",	"test"	, "Anita", "M", Gender.FEMALE, UserType.EDITOR);
//        users[3] = UserManager.getInstance().createUser(1003, "user3@semanticsquare.com", "test", "Sara", "M", Gender.FEMALE, UserType.EDITOR);
//        users[4] = UserManager.getInstance().createUser(1004, "user4@semanticsquare.com", "test", "Dheeru", "M",	Gender.MALE, UserType.CHIEFEDITOR);

//        String[] data = new String[TOTAL_USER_COUNT];
        String query = "Select * from User";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {

            long id = rs.getLong("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int gender_id = rs.getInt("gender_id");
            Gender gender = Gender.values()[gender_id];
            int user_type_id = rs.getInt("user_type_id");
            UserType userType = UserType.values()[user_type_id];

            User user = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender, userType);

            users.add(user);
        }

    }

    private static int bookmarkIndex = 0;

    public static void add(UserBookmark userBookmark) {

        userBookmarks.add(userBookmark);
    }
}
