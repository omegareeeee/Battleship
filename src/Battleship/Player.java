
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Battleship;

/**
 *
 * @author tsith
 */

import Ships.*;

import java.util.ArrayList;
public class Player {
    private ArrayList<Ship> ships;

    private Board board;
    public Player(){
        ships = new ArrayList<>();
        ships.add(new AircraftCarrier());
        ships.add(new Battleship());
        ships.add(new Submarine());
        ships.add(new Cruiser());
        ships.add(new Destroyer());
    }

    public Board getBoard(){
        return board;
    }
    public ArrayList<Ship> getShips(){
        return ships;
    }
    
}