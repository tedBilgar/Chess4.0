package com.company.figures;

import com.company.board.Chess;
import com.company.board.Location;
import com.company.types.Side;

import java.util.Optional;
import java.util.Random;

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
        System.out.println("here");
        System.out.println(locationsToMove);
        for (int i = 0; i<locationsToMove.size() -1; i++) {
            Location location = locationsToMove.get(i);

            if (location.getX_coord() <1 || location.getX_coord() > 8 || location.getY_coord() < 1 || location.getY_coord() > 8) {
                locationsToMove.remove(location);
                continue;
            }

            if ( (location.getX_coord() == x_coord - (sideCoeff) * 1 && location.getY_coord() == y_coord + (sideCoeff) * 1
                    || location.getX_coord() == x_coord + (sideCoeff) * 1 && location.getY_coord() == y_coord + (sideCoeff) * 1)) {
                Optional<ChessFigure> chessFigure = Optional.ofNullable(chess.getChessFigureByCoord(location));
                if (chessFigure.isPresent() && this.side == chessFigure.get().getSide() || !chessFigure.isPresent()){
                    locationsToMove.remove(location);
                    continue;
                }
            }

            if ( location.getX_coord() == x_coord && (location.getY_coord() == y_coord + (sideCoeff)*1 || location.getY_coord() == y_coord + (sideCoeff)*2)
                && chess.getChessFigureByCoord(location)!=null) {
                locationsToMove.remove(location);
                continue;
            }
        }
        System.out.println("after weed " + locationsToMove);

    }

    @Override
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
                if(chessFigure.get().getX_coord() ==  (this.x_coord - (sideCoeff) * 1)||chessFigure.get().getX_coord() ==  (this.x_coord + (sideCoeff) * 1)
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
    }

    @Override
    public boolean move() {
        System.out.print("Pawn was " + x_coord + " " + y_coord);
        findAllPath();
        weedOut();
        if (!kill()){
            if (locationsToMove.isEmpty()) return false;
            Random random =new Random();
            Location location = locationsToMove.get(random.nextInt(locationsToMove.size()-1));
            x_coord = location.getX_coord();
            y_coord = location.getY_coord();
        }
        System.out.println(" / NOW " + x_coord + " " + y_coord);
        return true;
    }
}
