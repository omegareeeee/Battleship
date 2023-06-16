/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ships;
import java.util.ArrayList;

/**
 *
 * @author tsith
 */
public abstract class Ship {
    private int size;
    private String ship;

    private ArrayList<Cordinate> cordinates;
    public Ship(int shipSize, String shipName){
        cordinates = new ArrayList<>();
        size = shipSize;
        ship = shipName;
    }

    public void addCordinates(Cordinate cordinate){
        cordinates.add(cordinate);
    }

    public ArrayList<Cordinate> getCordinates(){
        return cordinates;
    }
    
    public String getShip(){
        return ship;
    }
    
    public int getSize(){
        return size;
    }
}