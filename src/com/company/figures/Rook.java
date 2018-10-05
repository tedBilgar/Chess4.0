package com.company.figures;

import com.company.board.Chess;
import com.company.board.Location;
import com.company.types.Side;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Rook extends ChessFigure {
    private List<Location> firstListOfLocations;
    private List<Location> secondListOfLocations;
    private List<Location> thirdListOfLocations;
    private List<Location> foursListOfLocations;

    public Rook(int x_coord, int y_coord, Side side, Chess chess) {
        super(x_coord, y_coord, side, chess);
    }

    @Override
    public void findAllPath() {
        locationsToMove.clear();
        firstListOfLocations = new LinkedList<>();
        secondListOfLocations = new LinkedList<>();
        thirdListOfLocations = new LinkedList<>();
        foursListOfLocations = new LinkedList<>();

        List<Location> bufferList = null;
        int x_coeff = 1;
        int y_coeff = 1;
        for(int i = 0;i < 4;i ++) {
            if (i == 0) {
                x_coeff = 1;
                y_coeff = 1;
                bufferList = firstListOfLocations;
            }
            if( i == 1) {
                x_coeff = 1;
                y_coeff = -1;
                bufferList = secondListOfLocations;
            }
            if ( i ==2 ){
                x_coeff = -1;
                y_coeff = -1;
                bufferList = thirdListOfLocations;
            }
            if (i == 3){
                x_coeff = -1;
                y_coeff = 1;
                bufferList = foursListOfLocations;
            }
            for (int x = 1, y = 1; x_coord + (x_coeff) * x > 0 && x_coord + (x_coeff) * x < 9 && y_coord + (y_coeff) * y > 0 && y_coord + (y_coeff) * y < 9;x++,y++ ) {
                //locationsToMove.add(new Location(x_coord + (x_coeff) * x, y_coord + (y_coeff) * y));
                bufferList.add(new Location(x_coord + (x_coeff) * x, y_coord +(y_coeff) * y));
            }
        }
        System.out.println(firstListOfLocations);
        System.out.println(secondListOfLocations);
        System.out.println(thirdListOfLocations);
        System.out.println(foursListOfLocations);
    }

    @Override
    public void weedOut() {
        List<Location> locationsToDelete = new ArrayList<>();
        List<Location> bufferList = new ArrayList<>();

        for(int i = 0; i < 4; i ++){
            if (i == 0) bufferList = firstListOfLocations;
            if ( i == 1) bufferList = secondListOfLocations;
            if (i == 2) bufferList = thirdListOfLocations;
            if (i == 3) bufferList = firstListOfLocations;

            for (Location location:
                    bufferList) {
                if (chess.getChessFigureByCoord(location) != null) bufferList.removeAll(bufferList.subList(bufferList.indexOf(location),bufferList.size()));
            }
        }
    }

    @Override
    public boolean kill() {
        return false;
    }

    @Override
    public boolean move() {
        return false;
    }
}
