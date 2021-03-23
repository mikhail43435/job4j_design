package ru.job4j.design.isp.menumanager;

import ru.job4j.design.isp.menumanager.items.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Реализовать структуру для поддержания меню.
 * Каждый элемент меню имеет имя. Все меню должно выводиться на экран.
 * Каждый пункт меню может быть как одиночным элементом,
 * так и иметь дочерние подпункты.
 * Все меню должно выводиться на экран. В виде дерева.
 * Предусмотреть возможность определять действие,
 * когда пользователь выбирает конкретный пункт меню.
 */

public class MenuManager {

    public static final String SEPARATOR = "---";
    private final List<MenuItem> menuList = new ArrayList<>();
    private int printLevel;

    public MenuManager(MenuItem... args) {
        generateMenuList(Arrays.asList(args));
        menuList.add(new Item000());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        int answerInt;
        while (true) {
            displayMenu();
            try {
                System.out.println("Enter number...");
                answer = scanner.nextLine();
                answerInt = Integer.parseInt(answer);
            } catch (Exception e) {
                System.out.println("Invalid input: " + answer);
                continue;
            }
            if (answerInt == menuList.size()) {
                System.out.println("Menu manager is stopped");
                return;
            }
            if ((answerInt > 0) && (answerInt <= menuList.size())) {
                menuList.get(answerInt - 1).execute();
            } else {
                System.out.println("Invalid input: " + answerInt);
            }

        }
    }

    public void displayMenu() {
        System.out.println("======");
        System.out.println("Printing menu...");
        for (int i = 0; i < menuList.size(); i++) {
            MenuItem item = menuList.get(i);
            System.out.println("enter "
                    + (i + 1)
                    + " for "
                    + SEPARATOR.repeat(item.getLevel())
                    + "> "
                    + item.getName());
        }
    }

    private void generateMenuList(List<MenuItem> list) {
        for (MenuItem item : list) {
            item.setLevel(printLevel);
            menuList.add(item);
            if (!item.childList.isEmpty()) {
                printLevel++;
                generateMenuList(item.getchildList());
            }
        }
        printLevel--;
    }

    public static void main(String[] args) {
        MenuManager manager =
                new MenuManager(
                        new Item001(),
                        new Item006(),
                        new Item007(),
                        new Item008()
                );
        manager.run();
    }
}