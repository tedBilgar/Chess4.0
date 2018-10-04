package com.company.board;

import com.company.figures.ChessFigure;
import com.company.types.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chess {
    private List<ChessFigure> chessFigures;
    private Side whoTurns;

    public Chess() {
        chessFigures = new ArrayList<>();
        whoTurns = Side.WHITE;
    }

    public List<ChessFigure> getChessFigures() {
        return chessFigures;
    }

    public void setChessFigures(List<ChessFigure> chessFigures) {
        this.chessFigures = chessFigures;
    }

    public Side getWhoTurns() {
        return whoTurns;
    }

    public void setWhoTurns(Side whoTurns) {
        this.whoTurns = whoTurns;
    }

    public void findRandomFigureAndMove(){
        //TODO remember values of figures
        ChessFigure chessFigure;
   /*     do {
            Random random = new Random();
            chessFigure = getChessFigures().get(random.nextInt(getChessFigures().size() - 1));
        }while(chessFigure.getSide() != this.whoTurns && !chessFigure.move());
*/
        while(true){
            Random random = new Random();
            chessFigure = getChessFigures().get(random.nextInt(getChessFigures().size() - 1));
            if(chessFigure.getSide() == this.whoTurns && chessFigure.move()) break;
        }
    }

    public boolean isEnd(){
        //TODO adding any situations with the end of the game
        boolean sameSide = true;

        if (chessFigures.isEmpty() || chessFigures.size() == 1 ) return true;

        Side side = chessFigures.get(0).getSide();
        for (ChessFigure chessFigure:
             chessFigures) {
            if (side != chessFigure.getSide()) {
                return false;
            }
        }
        return true;
    }

    public ChessFigure getChessFigureByCoord(Location location){
        //TODO null return
        for (ChessFigure chessFigure:
             chessFigures) {
            if (chessFigure.getX_coord() == location.getX_coord() && chessFigure.getY_coord() == location.getY_coord()) return chessFigure;
        }
        return null;
    }
}
