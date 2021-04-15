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

import java.util.Arrays;

import static com.semantics.thrillio.constants.UserType.USER;

public class DataStore {

    public static final int TOTAL_USER_COUNT = 5;
    public static final int BOOKMARK_TYPES_COUNT = 3;
    public static final int BOOKMARK_COUNT_PER_TYPE = 5;
    public static final int USER_BOOKMARK_LIMIT = 5;

    private static User[] users = new User[TOTAL_USER_COUNT];
    private static Bookmark[][] bookmarks = new Bookmark[BOOKMARK_TYPES_COUNT][BOOKMARK_COUNT_PER_TYPE];
    private static UserBookmark[] userBookmarks = new UserBookmark[TOTAL_USER_COUNT * USER_BOOKMARK_LIMIT];

    public static void loadData(){

        loadUsers();
        loadWebLinks();
        loadBooks();
        loadMovies();
    }

    public static User[] getUsers() {
        return users;
    }

    public static Bookmark[][] getBookmarks() {
        return bookmarks;
    }

    private static void loadMovies() {

//        bookmarks[1][0] = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941, new String[]{"Orson Welles", "Joseph Cotten"}, new String[]{"Orson Welles"}, MovieGenre.CLASSICS, 8.5);
//        bookmarks[1][1] = BookmarkManager.getInstance().createMovie(3001, "The Grapes of Wrath", 1940, new String[]{"Henry Fonda", "Jane Darwell"}, new String[]{"John Ford"}, MovieGenre.CLASSICS, 8.2);
//        bookmarks[1][2] = BookmarkManager.getInstance().createMovie(3002, "A Touch of Greatness", 2004, new String[]{"Albert Cullum"}, new String[]{"Leslie Sullivan"}, MovieGenre.DOCUMENTARIES, 7.3);
//        bookmarks[1][3] = BookmarkManager.getInstance().createMovie(3003, "The Big Bang Theory", 2007, new String[]{"Kaley Cuoco", "Jim Parsons"}, new String[]{"Chuck Lorre", "Bill Prady"}, MovieGenre.TV_SHOWS, 8.7);
//        bookmarks[1][4] = BookmarkManager.getInstance().createMovie(3004, "Ikiru", 1952, new String[]{"Takashi Shimura", "Minoru Chiaki"}, new String[]{"Akira Kurosawa"}, MovieGenre.FOREIGN_MOVIES, 8.4);

        String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
        IOUtil.read(data, "Movie");
        int colNum = 0;
        for (String row : data){

            String[] values = row.split("\t");
            String[] cast = values[3].split(",");
            String[] directors = values[4].split(",");
            bookmarks[1][colNum++] = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), cast, directors, values[5], Double.parseDouble(values[6]));
        }
    }

    private static void loadBooks() {

//        bookmarks[2][0] = BookmarkManager.getInstance().createBook(4000, "Walden",1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY,4.3);
//        bookmarks[2][1] = BookmarkManager.getInstance().createBook(4001, "Self-Reliance and Other Essays",1993, "Dover Publications", new String[]{"Ralph Waldo Emerson"}, BookGenre.PHILOSOPHY, 4.5);
//        bookmarks[2][2] = BookmarkManager.getInstance().createBook(4002, "Light From Many Lamps", 1988, "Touchstone", new String[]{"Lillian Eichler Watson"}, BookGenre.PHILOSOPHY, 5.0);
//        bookmarks[2][3] = BookmarkManager.getInstance().createBook(4003, "Head First Design Patterns", 2004,"O'Reilly Media", new String[]{"Eric Freeman", "Bert Bates", "Kathy Sierra", "Elisabeth Robson"}, BookGenre.TECHNICAL, 4.5);
//        bookmarks[2][4] = BookmarkManager.getInstance().createBook(4004, "Effective Java Programming Language Guide", 2007, "Prentice Hall", new String[]{"Joshua Bloch"}, BookGenre.TECHNICAL, 4.9);

        String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
        IOUtil.read(data, "Book");
        int colNum = 0;
        for (String row : data){
            String[] values = row.split("\t");
            String[] authors = values[4].split(",");
            bookmarks[2][colNum++] = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], authors, values[5], Double.parseDouble(values[6]));
        }

    }

    private static void loadWebLinks() {

//        bookmarks[0][0] = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.javaworld.com");
//        bookmarks[0][1] = BookmarkManager.getInstance().createWeblink(2001, "How do I import a pre-existing Java project into Eclipse and get up and running?", "http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running", "http://www.stackoverflow.com");
//        bookmarks[0][2] = BookmarkManager.getInstance().createWeblink(2002, "Interface vs Abstract Class", "http://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com");
//        bookmarks[0][3] = BookmarkManager.getInstance().createWeblink(2003, "NIO tutorial by Greg Travis", "http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf", "http://cs.brown.edu");
//        bookmarks[0][4] = BookmarkManager.getInstance().createWeblink(2004,	"Virtual Hosting and Tomcat", "http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org");

        String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
        IOUtil.read(data, "WebLink");
        int colNum = 0;
        for (String row : data){

            String[] values = row.split("\t");
//            System.out.println("STRING ARRAY = " + Arrays.toString(values));
            bookmarks[0][colNum++] = BookmarkManager.getInstance().createWeblink(Long.parseLong(values[0]), values[1], values[2], values[3]);
        }
    }

    private static void loadUsers() {

//        users[0] = UserManager.getInstance().createUser(1000, "user0@semanticsquare.com", "test", "John", "M", Gender.MALE, USER);
//        users[1] = UserManager.getInstance().createUser(1001,"user1@semanticsquare.com", "test", "Sam", "M", Gender.MALE,UserType.USER);
//        users[2] = UserManager.getInstance().createUser(1002, "user2@semanticsquare.com",	"test"	, "Anita", "M", Gender.FEMALE, UserType.EDITOR);
//        users[3] = UserManager.getInstance().createUser(1003, "user3@semanticsquare.com", "test", "Sara", "M", Gender.FEMALE, UserType.EDITOR);
//        users[4] = UserManager.getInstance().createUser(1004, "user4@semanticsquare.com", "test", "Dheeru", "M",	Gender.MALE, UserType.CHIEFEDITOR);

        String[] data = new String[TOTAL_USER_COUNT];
        IOUtil.read(data, "User");
        int rowNum = 0;
        for (String row : data) {

            String[] values = row.split("\t");

            int gender = Gender.MALE;
            if (values[5].equals("f")){

                gender = Gender.FEMALE;
            } else if (values[5].equals("t")){

                gender = Gender.TRANSGENDER;
            }

            users[rowNum++] = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]);
        }

    }

    private static int bookmarkIndex = 0;

    public static void add(UserBookmark userBookmark) {

        userBookmarks[bookmarkIndex] = userBookmark;
        bookmarkIndex++;
    }
}