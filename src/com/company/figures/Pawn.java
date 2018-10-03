package com.company.figures;

import com.company.board.Chess;
import com.company.board.Location;
import com.company.types.Side;

public class Pawn extends ChessFigure {

    public Pawn(int x_coord, int y_coord, Side side, Chess chess) {
        super(x_coord, y_coord, side, chess);

        if (this.side == Side.WHITE) sideCoeff = 1;
        else sideCoeff = -1;
        gameValue = 1;
    }

    @Override
    public void findAllPath() {
        locationsToMove.add(new Location(x_coord - (sideCoeff)*1,y_coord+(sideCoeff)*1));
        locationsToMove.add(new Location(x_coord,y_coord+(sideCoeff)*1));
        locationsToMove.add(new Location(x_coord,y_coord+(sideCoeff)*2));
        locationsToMove.add(new Location(x_coord+(sideCoeff)*1,y_coord+(sideCoeff)*1));
    }

    //TODO with null
    @Override
    public void weedOut() {

        for (Location location :
                    locationsToMove) {

            if (location.getX_coord() <1 && location.getX_coord() > 8 || location.getY_coord() < 1 && location.getY_coord() > 8)
                locationsToMove.remove(location);

            if ( (location.getX_coord() == x_coord - (sideCoeff) * 1 && location.getY_coord() == y_coord + (sideCoeff) * 1
                    || location.getX_coord() == x_coord + (sideCoeff) * 1 && location.getY_coord() == y_coord + (sideCoeff) * 1)
                    && chess.getChessFigureByCoord(location) == null)

                locationsToMove.remove(location);

            if ( location.getX_coord() == x_coord && location.getY_coord() == y_coord + (sideCoeff)*1
                && chess.getChessFigureByCoord(location)!=null)

                locationsToMove.remove(location);
        }

    }

    @Override
    public boolean kill() {
        int maxValue = 0;
        Location locationToKill = null;
        for (Location location:
             locationsToMove) {
            //TODO this null return
            int curValue = chess.getChessFigureByCoord(location).getGameValue();
            if (curValue > maxValue){
                maxValue = curValue;
                locationToKill = location;
            }
        }
        if (maxValue != 0) {
            ChessFigure chessFigureToKill = chess.getChessFigureByCoord(locationToKill);
            this.x_coord = chessFigureToKill.getX_coord();
            this.y_coord = chessFigureToKill.getY_coord();
            chess.getChessFigures().remove(chessFigureToKill);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean move() {
        return false;
    }
}
