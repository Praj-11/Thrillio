package com.semantics.thrillio;

import com.semantics.thrillio.constants.KidFriendlyStatus;
import com.semantics.thrillio.constants.UserType;
import com.semantics.thrillio.controllers.BookmarkController;
import com.semantics.thrillio.entities.Bookmark;
import com.semantics.thrillio.entities.User;
import com.semantics.thrillio.partner.Shareable;

import java.awt.*;
import java.util.List;

public class View {

    public static void browse(User user, List<List<Bookmark>> bookmarks){

        System.out.println("\n" + user.getEmail() + "is bookmarking");
        int bookmarkCount = 0;

        for(List<Bookmark> bookmarksList : bookmarks){
            for (Bookmark bookmark : bookmarksList){
//                Bookmarking!!
//                if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT){

                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if (isBookmarked){

                        bookmarkCount++;

                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);

                        System.out.println("New Item Bookmarked -- " + bookmark);
                    }
//                }



                if(user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEFEDITOR)){

                    //Mark as kid-friendly

                    if (bookmark.isKidFiendlyEligible() && bookmark.getKidFriendlyStatus().
                            equals(KidFriendlyStatus.UNKNOWN)){

                        KidFriendlyStatus kidFriendlyStatus = getKidFriendlyDecisionStatus(bookmark);
                        if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)){

                            BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
                        }
                    }

                    // Sharing!!

                    if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable){

                        boolean isShared = getShareDecision();
                        if (isShared){

                            BookmarkController.getInstance().share(user, bookmark);
                        }
                    }
                }
            }
        }
    }

    // ToDo: Below methods simulate user input. After IO, we take input via console.

    private static boolean getShareDecision() {

        return Math.random() < 0.5 ? true : false;
    }

    private static KidFriendlyStatus getKidFriendlyDecisionStatus(Bookmark bookmark) {

        double decision = Math.random();

        return decision < 0.4 ? KidFriendlyStatus.APPROVED :
                (decision >= 0.4 && decision < 0.8) ? KidFriendlyStatus.REJECTED :
                        KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {

        return Math.random() < 0.5 ? true : false;
    }

}
