package com.company.figures;

import com.company.board.Chess;
import com.company.board.Location;
import com.company.types.Side;

public class Rook extends ChessFigure {
    public Rook(int x_coord, int y_coord, Side side, Chess chess) {
        super(x_coord, y_coord, side, chess);
    }

    @Override
    public void findAllPath() {
        locationsToMove.clear();
        int x_coeff = 1;
        int y_coeff = 1;
        for(int i = 0;i < 4;i ++) {
            if (i == 0) {
                x_coeff = 1;
                y_coeff = 1;
            }
            if( i == 1) {
                x_coeff = 1;
                y_coeff = -1;
            }
            if ( i ==2 ){
                x_coeff = -1;
                y_coeff = -1;
            }
            if (i == 3){
                x_coeff = -1;
                y_coeff = 1;
            }
            for (int x = 1, y = 1; x_coord + (x_coeff) * x > 0 && x_coord + (x_coeff) * x < 9 && y_coord + (y_coeff) * y > 0 && y_coord + (y_coeff) * y < 9;x++,y++ ) {
                locationsToMove.add(new Location(x_coord + (x_coeff) * x, y_coord + (y_coeff) * y));
            }
        }

        System.out.println(locationsToMove);
    }

    @Override
    public void weedOut() {

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
