package com.semantics.thrillio.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class IODemo {

    private static void readFromStandardInput() {

        System.out.println("\nInside readFromStandardInpur ...");
        String data;

        System.out.print("Enter \"start\" to continue (Using BufferedReader)");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"))) {

            while ((data = in.readLine()) != null && !data.equals("start")){

                System.out.print("\nDid not enter \"start\". Try again");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Correct!!");
    }
}
