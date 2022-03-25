package at.ac.fhcampuswien;

import java.util.Scanner;

public class Menu {

    private AppController controller = new AppController();

    private static final String INVALID_INPUT_MESSAGE = "Invalid Input!";
    private static final String EXIT_MESSAGE = "Bye Bye!";

    private static final String end = "q";


    public Menu() {
    }

    public void start() {
        printMenu();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while (!input.equals(end)) {
            handleInput(input);
        }

    }

    private void handleInput(String input) {
        if (input.equals("a")) {
            controller.getTopHeadlinesAustria();
        } else if (input.equals("b")) {
            controller.getAllNewsBitcoin();
        } else if (input.equals("y")) {
            controller.getArticleCount();
        } else if (input.equals("q")) {
            printExitMessage();
        } else {
            printInvalidInputMessage();
        }
    }

    private void getArticleCount(AppController ctrl) {
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
    }

    private void getAllNewsBitcoin(AppController ctrl) {
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    private static void printMenu() {
        System.out.println("*****************************" + "\n" +
                "*    Welcome to NewsApp     *" + "\n" +
                "*****************************" + "\n" +
                "*****************************" + "\n" +
                "Enter what you wanna do:" + "\n" +
                "a: Get top headlines austria" + "\n" +
                "b: Get all news about bitcoin" + "\n" +
                "y: Count articles" + "\n" +
                "q: Quit program");

    }

}
