package com.semantics.thrillio;

import com.semantics.thrillio.entities.Bookmark;
import com.semantics.thrillio.entities.User;
import com.semantics.thrillio.managers.BookmarkManager;
import com.semantics.thrillio.managers.UserManager;

import java.util.List;

public class Launch {

    private static List<User> users;
    private static List<List<Bookmark>> bookmarks;

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

        for(List<Bookmark> bookmarkList : bookmarks){
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
