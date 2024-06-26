package br.com.chessGameProject.aplication;

import br.com.chessGameProject.chess.ChessMatch;
import br.com.chessGameProject.chess.ChessPiece;
import br.com.chessGameProject.chess.ChessPosition;
import br.com.chessGameProject.chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static ChessPosition readChessPosition(Scanner in) {
        try {
            String s = in.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading Chessposition. Valid values are a1 to h8.");
        }

    }

    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j],false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] posibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j],posibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printPiece(ChessPiece piece, boolean backGound) {
        if (backGound){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
    public static void  printMatch(ChessMatch chessMatch,List<ChessPiece> captured){
        printBoard(chessMatch.getPicies());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println("Turn : "+chessMatch.getTurn());
        if(!chessMatch.isCheckmate()){
            System.out.println("Waiting player:"+chessMatch.getCurrentPlColor());
            System.out.println(chessMatch.isCheck()?"CHECK!":"");
        }else {
            System.out.println("CHECKMATE!");
            System.out.println("Winner:"+chessMatch.getCurrentPlColor());
        }

    }

    public static void printCapturedPieces(List<ChessPiece> captured){
        List<ChessPiece> whith = captured.stream().filter(x->x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x->x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("Captured picies:");
        System.out.print("WHITE:");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(whith.toArray()));
        System.out.println(ANSI_RESET);
        System.out.print("BLACK:");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.println(ANSI_RESET);
    }
}
