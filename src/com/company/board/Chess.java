package com.company.board;

import com.company.figures.ChessFigure;

import java.util.List;

public class Chess {
    private List<ChessFigure> chessFigures;

    public List<ChessFigure> getChessFigures() {
        return chessFigures;
    }

    public void setChessFigures(List<ChessFigure> chessFigures) {
        this.chessFigures = chessFigures;
    }

    public void getRandomFigure(){
        //TODO
    }

    public ChessFigure getChessFigureByCoord(Location location){
        for (ChessFigure chessFigure:
             chessFigures) {
            if (chessFigure.getX_coord() == location.getX_coord() && chessFigure.getY_coord() == location.getY_coord()) return chessFigure;
        }
        return null;
    }
}
