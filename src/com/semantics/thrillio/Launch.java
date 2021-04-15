package com.semantics.thrillio;

import com.semantics.thrillio.entities.Bookmark;
import com.semantics.thrillio.entities.User;
import com.semantics.thrillio.managers.BookmarkManager;
import com.semantics.thrillio.managers.UserManager;

public class Launch {

    private static User[] users;
    private static Bookmark[][] bookmarks;

    public static void main(String[] args) {

        loadData();
        start();
    }

    private static void start() {

//        System.out.println("\n2. Bookmarking...");
        for(User user : users){

            View.browse(user, bookmarks);
        }
    }

    private static void loadData() {

        System.out.println("1. Loading Data...");
        DataStore.loadData();

        users = UserManager.getInstance().getUsers();
        bookmarks = BookmarkManager.getInstance().getBookmarks();

//        System.out.println("Printing Data...");
//        printUserData();
//        printBookmarkData();
    }

    private static void printBookmarkData() {

        for(Bookmark[] bookmarkList : bookmarks){
            for (Bookmark bookmark : bookmarkList){

                System.out.println(bookmark);
            }
        }
    }

    private static void printUserData() {

        for (User user : users){

            System.out.println(user);
        }
    }


}
