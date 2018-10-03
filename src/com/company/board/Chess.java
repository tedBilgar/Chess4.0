package com.company.board;

import com.company.figures.ChessFigure;

import java.util.List;
import java.util.Random;

public class Chess {
    private List<ChessFigure> chessFigures;
    private boolean isWhiteMove;

    public List<ChessFigure> getChessFigures() {
        return chessFigures;
    }

    public void setChessFigures(List<ChessFigure> chessFigures) {
        this.chessFigures = chessFigures;
    }

    public boolean isWhiteMove() {
        return isWhiteMove;
    }

    public void setWhitemove(boolean isWhiteMove) {
        this.isWhiteMove = isWhiteMove;
    }

    public void getRandomFigure(){
        //TODO
        ChessFigure chessFigure = null;
        do {
            Random random = new Random();
            ChessFigure chessFigure = getChessFigures().get(random.nextInt(getChessFigures().size()-1));
        }while(c)
    }

    public boolean isEnd(){
        if ()
    }

    public ChessFigure getChessFigureByCoord(Location location){
        for (ChessFigure chessFigure:
             chessFigures) {
            if (chessFigure.getX_coord() == location.getX_coord() && chessFigure.getY_coord() == location.getY_coord()) return chessFigure;
        }
        return null;
    }
}
