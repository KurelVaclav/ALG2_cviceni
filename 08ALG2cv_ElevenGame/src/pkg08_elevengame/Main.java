package pkg08_elevengame;

import java.util.Scanner;

/**
 * main komunikuje s uživatelem, "uživatelské rozhraní - hloupé" vždy se ptá
 * Board, nkampsulacie - schovám logiku před uživatelem => komunikace pouze s
 * board - neví o Card a Deck
 *
 * @author Václav Kurel
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    //budeme dělat s rozhraním
    //public static BoardInterface board = new Board();

    public static void main(String[] args) {
        Board board = Board.newBoard();
        while (board.isAnotherPlayPossible()) { //ještě jsou karty v balíčku, na stole a zároveň existuje JQK, sum11
            showBoard(board);
            System.out.println("Vyber karty: ");
            //načtu si celý řádek a pošlu to logice :) , split rozdělí na pole Stringů, musím říct co je oddělovač
            String[] selectedCardsPositions = sc.nextLine().split(" +"); //split také regulární výraz - výčet oddělovačů
            //"[a-z] jakékoliv písmeno, [a-z]+ .. jedno nebo více, " +" jedno nebo více mezer je oddělovač
            while (!board.playAndReplace(selectedCardsPositions)) {
                System.out.println("Nevalidní tah!");
                selectedCardsPositions = sc.nextLine().split(" +");
            }
        }
        //kdy končí hra? - check jestli jsem vyhrál;
        if (board.hasWon()) {
            showBoard(board);
            System.out.println("Gratuluji, vyhrál jsi!");
        } else {
            showBoard(board);
            System.out.println("Nelze hrát, neexistuje další tah!");
        }
    }

    private static void displayCards(BoardInterface board) {
        for (int i = 0; i < board.numberOfCards(); i++) {
            System.out.format("%1d.  %10s  ", i + 1, board.getCardDescriptionAt(i));
            if ((i + 1) % 3 == 0) {
                System.out.println("");
            }
        }
    }

    private static void displayDeck(BoardInterface board) {
        System.out.println("V balíčku je " + board.getDeckSize() + " karet.");
    }

    private static void showBoard(BoardInterface board) {
        System.out.println("************ " + board.getName() + "**************");
        displayCards(board);
        displayDeck(board);
    }

}


//metody jsou zprávy které si mezi sebou posílají jednostlivé třídy 
