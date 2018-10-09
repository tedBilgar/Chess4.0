package com.company;

import com.company.board.Chess;
import com.company.figures.Pawn;
import com.company.figures.Rook;
import com.company.types.Side;

public class Main {


    public static void main(String[] args) {
        Chess chess = new Chess();
        for(int i = 0; i< 3;i++){
            chess.getChessFigures().add(new Rook(1+i,1, Side.WHITE,chess));
        }

        for(int i = 0; i< 3;i++){
            chess.getChessFigures().add(new Rook(1+i,4, Side.BLACK,chess));
        }

        System.out.println(chess.getChessFigures());

        while (!chess.isEnd()){
            System.out.println("Before the step " + chess.getChessFigures());
            chess.findRandomFigureAndMove();
            if (chess.getWhoTurns() == Side.WHITE) chess.setWhoTurns(Side.BLACK);
            else chess.setWhoTurns(Side.WHITE);
            System.out.println(chess.getChessFigures());
        }
    }
}
