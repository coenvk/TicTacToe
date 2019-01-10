package com.learncode.ttt.util;

import java.util.Scanner;

/**
 * Created by Coen on 11-7-2017.
 */
public class InputUtil {

    private static final String ERROR_MSG = "Invalid input!";
    private static Scanner input = new Scanner(System.in);

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine();
            Scanner inputLine = new Scanner(line);
            if (inputLine.hasNextInt()) {
                int result = inputLine.nextInt();
                return result;
            } else {
                System.out.println(ERROR_MSG);
            }
        }
    }

    public static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine();
            if (line.toLowerCase().equals("y") || line.toLowerCase().equals("yes")) {
                return true;
            } else if (line.toLowerCase().equals("n") || line.toLowerCase().equals("no")) {
                return false;
            } else {
                System.out.println(ERROR_MSG);
            }
        }
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        String line = input.nextLine();
        return line;
    }

}
