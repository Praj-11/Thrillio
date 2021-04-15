package com.semantics.thrillio.entities;

import com.semantics.thrillio.managers.BookmarkManager;

import static org.junit.Assert.*;

public class WebLinkTest extends Object {

    @org.junit.Test
    public void isKidFiendlyEligible() {

        //Test 1: porn in url -- false

        WebLink webLink = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html", "http://www.javaworld.com");
        boolean isKidFriendlyEligible = webLink.isKidFiendlyEligible();

        assertFalse("For porn in url - isKidFirendlyEligible() must return false", isKidFriendlyEligible);

        //Test 2: porn in title -- false

        webLink = BookmarkManager.getInstance().createWeblink(2000, "Taming Porn, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming--part-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFiendlyEligible();

        assertFalse("For porn in title - isKidFirendlyEligible() must return false", isKidFriendlyEligible);

        //Test 3: adult in host -- false

        webLink = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming--part-2.html", "http://www.adult.com");
        isKidFriendlyEligible = webLink.isKidFiendlyEligible();

        assertFalse("For adult in host - isKidFirendlyEligible() must return false", isKidFriendlyEligible);

        //Test 4: adult in url, but not in host part -- true

        webLink = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html", "http://www.tiger.com");
        isKidFriendlyEligible = webLink.isKidFiendlyEligible();

        assertTrue("For adult in url but not is host - isKidFirendlyEligible() must return true", isKidFriendlyEligible);

        //Test 5: adult in title only -- true

        webLink = BookmarkManager.getInstance().createWeblink(2000, "Taming adult, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.tiger.com");
        isKidFriendlyEligible = webLink.isKidFiendlyEligible();

        assertTrue("For adult in title only - isKidFirendlyEligible() must return true", isKidFriendlyEligible);

    }
}
