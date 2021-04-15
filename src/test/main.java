package test;

import com.semantics.thrillio.managers.BookmarkManager;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        ArrayList<String[]> data = fileReader("/home/praj/Desktop/Internship(Cogni)/FSE/Java/Thrillio/Book");
        int i = 0;

        for(String[] str : data){

            System.out.println(Arrays.toString(str));
        }

    }

    private static ArrayList<String[]> fileReader(String path){

        ArrayList<String[]> strings = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);

            while(sc.hasNextLine()) {

                String str = sc.nextLine();
                String[] split = str.split("\\s+");
                strings.add(split);
            }
            sc.close();

        }catch (Exception e){}

        return strings;
    }
}
