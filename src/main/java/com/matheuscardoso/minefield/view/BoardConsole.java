package com.matheuscardoso.minefield.view;

import com.matheuscardoso.minefield.exceptions.ExitException;
import com.matheuscardoso.minefield.exceptions.ExplosionException;
import com.matheuscardoso.minefield.model.Board;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class BoardConsole {
    private Board board;
    private Scanner input = new Scanner(System.in);
    
    public BoardConsole(Board board) {
        this.board = board;
        executeGame();
    }

    private void executeGame() {
        try {
            boolean keepAlive = true;
            while (keepAlive) {
                gameCycle();
                System.out.println("Another play? (s/n): ");
                String answer = input.nextLine();
                if ("n".equalsIgnoreCase(answer)) {
                    keepAlive = false;
                } else {
                    board.restart();
                }
            }
        } catch (ExitException e) {
            System.out.println("See you!");
        } finally {
            input.close();
        }
    }

    private void gameCycle() {
        try {
            while(!board.goalAchieved()) {
                System.out.println(board);
                String typed = captureEnteredValue("Enter (x,y): ");
                Iterator<Integer> xy = Arrays.stream(typed.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();
                typed = captureEnteredValue("1- to Open or 2 - (Un)flag: ");
                if("1".equalsIgnoreCase(typed)) {
                    board.open(xy.next(), xy.next());
                } else if ("2".equals(typed)) {
                    board.toggleMarking(xy.next(), xy.next());
                }
            }
            System.out.println(board);
            System.out.println("You win!");
        } catch (ExplosionException e) {
            System.out.println(board);
            System.out.println("You lost!");
        }
    }

    private String captureEnteredValue(String text) {
        System.out.println(text);
        String typed = input.nextLine();
        if ("exit".equalsIgnoreCase(typed)) {
            throw new ExitException();
        }
        return typed;
    }
}
