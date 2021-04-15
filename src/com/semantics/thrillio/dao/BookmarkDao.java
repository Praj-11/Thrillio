package com.semantics.thrillio.dao;

import com.semantics.thrillio.DataStore;
import com.semantics.thrillio.entities.Bookmark;
import com.semantics.thrillio.entities.UserBookmark;

public class BookmarkDao {

    public Bookmark[][] getBookmarks(){

        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {

        DataStore.add(userBookmark);
    }
}
