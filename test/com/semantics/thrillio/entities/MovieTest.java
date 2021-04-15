package com.semantics.thrillio.entities;

import com.semantics.thrillio.constants.MovieGenre;
import com.semantics.thrillio.managers.BookmarkManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest extends Object {

    @Test
    public void isKidFiendlyEligible() {

        // Test 1

        Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941, new String[]{"Orson Welles", "Joseph Cotten"}, new String[]{"Orson Welles"}, MovieGenre.HORROR, 8.5);
        boolean isKidFriendlyEligible = movie.isKidFiendlyEligible();

        assertFalse("For Horror Genre - isKidFriendly should return false", isKidFriendlyEligible);

        // Test 2

        movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941, new String[]{"Orson Welles", "Joseph Cotten"}, new String[]{"Orson Welles"}, MovieGenre.THRILLER, 8.5);
        isKidFriendlyEligible = movie.isKidFiendlyEligible();

        assertFalse("For Thrillers Genre - isKidFriendly should return false", isKidFriendlyEligible);
    }
}
