package com.semantics.thrillio.entities;

import com.semantics.thrillio.constants.BookGenre;
import com.semantics.thrillio.constants.MovieGenre;
import com.semantics.thrillio.managers.BookmarkManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest extends Object {

    @Test
    public void isKidFiendlyEligible() {

        // Test 1

        Book book = BookmarkManager.getInstance().createBook(4000, "Walden",1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY,4.3);
        boolean isKidFriendlyEligible = book.isKidFiendlyEligible();

        assertFalse("For Philosophy Genre - isKidFriendly should return false", isKidFriendlyEligible);

        // Test 2

        book = BookmarkManager.getInstance().createBook(4000, "Walden",1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.SELF_HELP,4.3);
        isKidFriendlyEligible = book.isKidFiendlyEligible();

        assertFalse("For Self Help Genre - isKidFriendly should return false", isKidFriendlyEligible);

    }
}
