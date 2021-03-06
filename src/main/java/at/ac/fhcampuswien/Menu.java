package at.ac.fhcampuswien;

import java.util.Scanner;

public class Menu{

    private AppController controller = AppController.getInstance();

    private static final String INVALID_INPUT_MESSAGE = "Invalid Input!";
    private static final String EXIT_MESSAGE = "Bye Bye!";

    private static final String end = "q";


    public Menu() {
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input = new String();

        while (true) {
            printMenu();
            input = scanner.next();
            handleInput(input);
            if (input.equals(end))
                break;
        }
    }

    private void handleInput(String input) {
        if (input.equals("a")) {
            getTopHeadlinesAustria(controller);
        } else if (input.equals("b")) {
            getAllNewsBitcoin(controller);
        } else if (input.equals("y")) {
            getArticleCount(controller);
        } else if (input.equals("q")) {
            printExitMessage();
        } else {
            printInvalidInputMessage();
        }
    }

    private void getArticleCount(AppController ctrl) {
        try {
            System.out.println(ctrl.getArticleCount());
        }catch(NewsAPIException e){
            System.out.println(e.getMessage());
        }
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
        System.out.println(ctrl.getTopHeadlinesAustria());
    }

    private void getAllNewsBitcoin(AppController ctrl) {
        System.out.println(ctrl.getAllNewsBitcoin());
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
