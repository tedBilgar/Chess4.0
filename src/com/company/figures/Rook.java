package com.company.figures;

import com.company.board.Chess;
import com.company.board.Location;
import com.company.types.Side;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Rook extends ChessFigure {

    public Rook(int x_coord, int y_coord, Side side, Chess chess) {
        super(x_coord, y_coord, side, chess);
        gameValue = 5;
        listOfListsOfLocations = new LinkedList<>();
    }

    @Override
    public void findAllPath() {
        locationsToMove.clear();

        for (int i = 0;i<4;i++) {
            listOfListsOfLocations.add(new LinkedList<Location>());
        }

        List<Location> bufferList = null;
        int x_coeff = 1;
        int y_coeff = 1;
        for(int i = 0;i < 4;i ++) {
            if (i == 0) {
                x_coeff = 1;
                y_coeff = 1;
                bufferList = listOfListsOfLocations.get(i);
            }
            if( i == 1) {
                x_coeff = 1;
                y_coeff = -1;
                bufferList = listOfListsOfLocations.get(i);
            }
            if ( i ==2 ){
                x_coeff = -1;
                y_coeff = -1;
                bufferList = listOfListsOfLocations.get(i);
            }
            if (i == 3){
                x_coeff = -1;
                y_coeff = 1;
                bufferList = listOfListsOfLocations.get(i);
            }
            for (int x = 1, y = 1; x_coord + (x_coeff) * x > 0 && x_coord + (x_coeff) * x < 9 && y_coord + (y_coeff) * y > 0 && y_coord + (y_coeff) * y < 9;x++,y++ ) {
                bufferList.add(new Location(x_coord + (x_coeff) * x, y_coord +(y_coeff) * y));
            }
        }

    }


    @Override
    public boolean move() {
        return false;
    }
}
