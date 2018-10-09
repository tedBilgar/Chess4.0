package com.company.figures;

import com.company.board.Chess;
import com.company.board.Location;
import com.company.types.Side;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessFigure {

    public Pawn(int x_coord, int y_coord, Side side, Chess chess) {
        super(x_coord, y_coord, side, chess);

        if (this.side == Side.WHITE) sideCoeff = 1;
        else sideCoeff = -1;
        gameValue = 1;
    }

    @Override
    public void findAllPath() {
        locationsToMove.clear();
        if (this.side == Side.WHITE && this.y_coord == 1 || this.side == Side.BLACK && this.y_coord ==4)
            locationsToMove.add(new Location(x_coord,y_coord+(sideCoeff)*2));
        locationsToMove.add(new Location(x_coord - (sideCoeff)*1,y_coord+(sideCoeff)*1));
        locationsToMove.add(new Location(x_coord,y_coord+(sideCoeff)*1));
        locationsToMove.add(new Location(x_coord+(sideCoeff)*1,y_coord+(sideCoeff)*1));
    }

    //TODO with null
    @Override
    public void weedOut() {
        System.out.print("here");
        System.out.println(locationsToMove);
        List<Location> locationsToDelete = new ArrayList<>();
        for (Location location:
             locationsToMove) {

            //TODO go out the frame
            if (location.getX_coord() < 1 || location.getX_coord() > 8 || location.getY_coord() < 1 || location.getY_coord() > 8) {
                locationsToDelete.add(location);
                continue;
            }


            if ( (location.getX_coord() == x_coord - (sideCoeff) * 1 || location.getX_coord() == x_coord + (sideCoeff) * 1)
                    && location.getY_coord() == y_coord + (sideCoeff) * 1) {
                if (chess.getChessFigureByCoord(location) == null) {
                    locationsToDelete.add(location);
                    continue;
                } else if (chess.getChessFigureByCoord(location).getSide() == this.side){
                    locationsToDelete.add(location);
                    continue;
                }

            }

            if (location.getX_coord() == x_coord && location.getY_coord() == y_coord + (sideCoeff) * 1
                    && chess.getChessFigureByCoord(location) != null) {
                locationsToDelete.add(location);
                for (Location addLocation:
                     locationsToMove) {
                    if (addLocation.getX_coord() == x_coord && addLocation.getY_coord() == y_coord + (sideCoeff) * 2) {
                        locationsToDelete.add(addLocation);
                        break;
                    }
                }
                continue;
            }
            if (location.getX_coord() == x_coord && location.getY_coord() == y_coord + (sideCoeff)*2
                    && chess.getChessFigureByCoord(location) !=null && !locationsToDelete.contains(location))
                locationsToDelete.add(location);
        }

        locationsToMove.removeAll(locationsToDelete);
        System.out.println("after weed " + locationsToMove);

    }

/*    @Override
    public boolean kill() {
        int maxValue = 0;
        Location locationToKill = null;
        if (locationsToMove.isEmpty()) return false;

        for (Location location:
             locationsToMove) {
            //TODO this null return
            Optional<ChessFigure> chessFigure = Optional.ofNullable(chess.getChessFigureByCoord(location));
            if (chessFigure.isPresent()) {
                int curValue = chessFigure.get().getGameValue();
                if( (chessFigure.get().getX_coord() ==  (this.x_coord - (sideCoeff) * 1)||chessFigure.get().getX_coord() ==  (this.x_coord + (sideCoeff) * 1))
                        && chessFigure.get().getY_coord() == (this.y_coord + (sideCoeff) *1)) {
                    if (curValue > maxValue) {
                        maxValue = curValue;
                        locationToKill = location;
                    }
                }
            }
        }
        if (maxValue != 0) {
            ChessFigure chessFigureToKill = chess.getChessFigureByCoord(locationToKill);
            this.x_coord = chessFigureToKill.getX_coord();
            this.y_coord = chessFigureToKill.getY_coord();
            chess.getChessFigures().remove(chessFigureToKill);
            System.out.println("KILL");
            return true;
        }else {
            return false;
        }
    }*/

}
