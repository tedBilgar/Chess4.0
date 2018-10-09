package com.company.figures;

import com.company.board.Chess;
import com.company.board.Location;
import com.company.types.Side;

import java.util.*;

public abstract class ChessFigure {
    protected int x_coord;
    protected int y_coord;
    protected Side side;
    protected int sideCoeff;
    protected int gameValue;

    protected Chess chess;
    protected List<Location> locationsToMove;
    protected List<List<Location>> listOfListsOfLocations;

    public ChessFigure(int x_coord, int y_coord, Side side, Chess chess) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.side = side;
        this.chess = chess;
        locationsToMove = new LinkedList<>();
    }

    public int getX_coord() {
        return x_coord;
    }

    public void setX_coord(int x_coord) {
        this.x_coord = x_coord;
    }

    public int getY_coord() {
        return y_coord;
    }

    public void setY_coord(int y_coord) {
        this.y_coord = y_coord;
    }

    public Side getSide() {
        return side;
    }

    public int getGameValue() {
        return gameValue;
    }


    public abstract void findAllPath();

    //is Done
    public void weedOut(){
        List<Location> bufferList;
        List<Location> listForDelete = new ArrayList<>();
        int startIndex = 0;

        for (List<Location> listOfLocation:
                listOfListsOfLocations) {
            bufferList = listOfLocation;
            for (Location location:
                    bufferList) {
                if (chess.getChessFigureByCoord(location) != null){
                    if (chess.getChessFigureByCoord(location).getSide() == this.side) {
                        //bufferList.removeAll(bufferList.subList(bufferList.indexOf(location), bufferList.size()));
                        startIndex = bufferList.indexOf(location);
                        break;
                    }
                    else {
                        //bufferList.removeAll(bufferList.subList(bufferList.indexOf(location) + 1,bufferList.size()));
                        startIndex = bufferList.indexOf(location) + 1;
                        break;
                    }
                }
            }
            if (startIndex != 0)
            bufferList.removeAll(bufferList.subList(startIndex,bufferList.size()));
            locationsToMove.addAll(bufferList);
        }

        System.out.println("weed " + locationsToMove);
    }

    //is Done
    public boolean findLocationToKill(){
        int maxValue = 0;
        Location locationToKill = null;
        if (locationsToMove.isEmpty()) return false;

        for (Location location:
                locationsToMove) {
            //TODO this null return
            Optional<ChessFigure> chessFigure = Optional.ofNullable(chess.getChessFigureByCoord(location));

            if (chessFigure.isPresent() && chessFigure.get().getSide() != this.side){
                int curValue = chessFigure.get().getGameValue();
                if (curValue > maxValue) {
                    maxValue = curValue;
                    locationToKill = location;
                }
            }
        }
        if (maxValue != 0) {
            kill(locationToKill);
            return true;
        }else {
            return false;
        }
    }

    //is Done
    public void kill(Location locationToKill){
        ChessFigure chessFigureToKill = chess.getChessFigureByCoord(locationToKill);
        this.x_coord = chessFigureToKill.getX_coord();
        this.y_coord = chessFigureToKill.getY_coord();
        chess.getChessFigures().remove(chessFigureToKill);
        System.out.println("afterkill " + x_coord + " " + y_coord);
    }

    //is Done
    public boolean move() {
        findAllPath();
        weedOut();
        if (!findLocationToKill()){
            if (locationsToMove.isEmpty()) return false;
            Random random = new Random();
            Location location = locationsToMove.get(random.nextInt(locationsToMove.size()));
            x_coord = location.getX_coord();
            y_coord = location.getY_coord();
        }
        System.out.println("move is Done + " +x_coord +" " + y_coord);
        return true;
    }

    @Override
    public String toString() {
        return "ChessFigure{" +
                "x_coord=" + x_coord +
                ", y_coord=" + y_coord +
                ", side=" + side +
                '}';
    }
}
