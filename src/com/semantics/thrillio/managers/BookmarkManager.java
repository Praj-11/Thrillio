package com.semantics.thrillio.managers;

import com.semantics.thrillio.constants.BookGenre;
import com.semantics.thrillio.constants.KidFriendlyStatus;
import com.semantics.thrillio.constants.MovieGenre;
import com.semantics.thrillio.dao.BookmarkDao;
import com.semantics.thrillio.entities.*;
import com.semantics.thrillio.util.HttpConnect;
import com.semantics.thrillio.util.IOUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public class BookmarkManager {

    private static final BookmarkManager instance = new BookmarkManager();
    private static final BookmarkDao dao = new BookmarkDao();

    private BookmarkManager(){}

    public static BookmarkManager getInstance(){
        return instance;
    }

    public Movie createMovie(long id, String title, int releaseYear, String[] cast,
                             String[] directors, MovieGenre genre, double imdbRating){

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
//        movie.setProfileUrl(profileUrl);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }

    public Book createBook(long id, String title, int publicationYear,
                           String publisher, String[] authors, BookGenre genre, double amazonRating){

        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
//        book.setProfileUrl(profileUrl);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);

        return book;
    }

    public WebLink createWeblink(long id, String title, String url, String host){

        WebLink webLink = new WebLink();
        webLink.setId(id);
        webLink.setTitle(title);
//        webLink.setProfileUrl(profileUrl);
        webLink.setUrl(url);
        webLink.setHost(host);

        return webLink;
    }

    public List<List<Bookmark>> getBookmarks(){
        return dao.getBookmarks();
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {

        UserBookmark userBookmark = new UserBookmark();
        userBookmark.setUser(user);
        userBookmark.setBookmark(bookmark);

        if (bookmark instanceof WebLink){
            try {

                String url = ((WebLink) bookmark).getUrl();
                if (!url.endsWith(".pdf")) {

                    String webpage = HttpConnect.download(((WebLink) bookmark).getUrl());
                    if (webpage != null){

                        IOUtil.write(webpage, bookmark.getId());
                    }
                }

            } catch (URISyntaxException | MalformedURLException e) {
                e.printStackTrace();
            }
        }

        dao.saveUserBookmark(userBookmark);
    }

    public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {

        bookmark.setKidFriendlyMarkedBy(user);
        bookmark.setKidFriendlyStatus(kidFriendlyStatus);

        dao.updateKidFriendlyStatus(bookmark);

        System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", Marked by: "
                + user.getEmail() + ", " + bookmark);
    }

    public void share(User user, Bookmark bookmark) {

        bookmark.setSharedBy(user);
        System.out.println("Data to be shared: ");

        if (bookmark instanceof Book){

            System.out.println(((Book) bookmark).getItemData());
        } else {
            System.out.println(((WebLink) bookmark).getItemData());
        }

        dao.sharedByInfo(bookmark);
    }

}
