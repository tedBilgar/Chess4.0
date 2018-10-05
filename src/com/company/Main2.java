package com.company;

import com.company.board.Chess;
import com.company.figures.ChessFigure;
import com.company.figures.Rook;
import com.company.types.Side;

public class Main2 {
    public static void main(String[] args) {
        Chess chess = new Chess();
        ChessFigure chessFigure  = new Rook(3,1, Side.WHITE,chess);
        chessFigure.findAllPath();
    }
}
