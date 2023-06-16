
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Battleship;

/**
 *
 * @author tsith
 */

import Ships.Cordinate;
import Ships.Ship;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Board {
    private String[][] board;
    private Player player;
    private String[][] hitOrMissboard;

    
    public Board(int rowSize, int columnSize, Player player){
        this.player = player;
        hitOrMissboard = new String[rowSize][columnSize];
        board = new String[rowSize][columnSize];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "~";
                hitOrMissboard[i][j] = "~";
            }
        }
    }
    
    public void place(){
        System.out.println(toString());
        Scanner scan = new Scanner(System.in);
        ArrayList<Ship> ships = player.getShips();
        int x = 0;
        int y = 0;
        int x2 = 0;
        int y2 = 0;
        
        for(int i = 0; i < ships.size(); i++ ){
            System.out.println("Enter the coordinates of the " + ships.get(i).getShip() + " ("+ ships.get(i).getSize() +" cells)\n" );
           while(true){
               boolean valid = false;
               //ask until it's in the correct format
               do{
                try{
                    System.out.print(">");
                    String cordinate = scan.nextLine();
                    //Takes user input and converts it to X,Y cordinates

                    String[] splited = cordinate.split(" ");

                    String cordinate1 = splited[0];

                        // start of cell
                    x = Integer.valueOf(cordinate1.substring(1))-1;
                    Character letter = Character.toUpperCase(cordinate1.charAt(0));
                    y = (letter - 'A' + 1)-1;
                    
                        //end of cell
                    String cordinate2 = splited[1];

                    x2 = Integer.valueOf(cordinate2.substring(1))-1;
                    letter = Character.toUpperCase(cordinate2.charAt(0));
                    y2 = (letter - 'A' + 1)-1;
                    valid = true;
                }catch(Exception e){
                    System.out.println("Error! Make sure values are between (A-J)(1-10)");
                }
               }while(!valid);
               
                if(!spaceAvailable(x,y,x2, y2)){
                    System.out.println("\nError! Space is already taken\n");
                    continue;
                    
                }

                if(!correctSize(x,y,x2, y2, ships.get(i)) ){
                    System.out.println("\nError! not correct amount of space\n");
                    continue;
                }
                //if(tooCLose)
               //then ask again
                break;
           }
            
            //if the cordinate touch any other ships then it's invalid
            if(spaceAvailable(x,y,x2, y2)){
                
               // If the letters are the same the just add the x value
               if(y == y2){
                   int row = y;
                   for(int column = x; column <= x2; column++ ){
                       board[row][column] = "O";
                       player.getShips().get(i).addCordinates(new Cordinate(y, x));
                   }
                   
               }
                // If the Numbers are the same the just add the y value
               if(x == x2){
                   int column = x;
                   for(int row = y; row <= y2; row++ ){
                       board[row][column] = "O";
                       player.getShips().get(i).addCordinates(new Cordinate(y, x));
                   }
               }
            }       
            
            System.out.println("\n"+toString());
        }
    }

    public boolean correctSize(int x1, int y1, int x2, int y2, Ship ship) {
        int size = 0;
        if(y1 == y2){
            int row = y1;
            for(int column = x1; column <= x2; column++ ){
                size++;
            }         
        }
              
        if(x1 == x2){
            int column = x1;
            for(int row = y1; row <= y2; row++ ){
                    size++;
            }
        }
        
        return size == ship.getSize();
    }
    public boolean spaceAvailable(int x1, int y1, int x2, int y2){
        boolean available = true;
        if(y1 == y2){
            int row = y1;
            for(int column = x1; column <= x2; column++ ){
                if(board[row][column].equals("O")){
                    available = false;
                }
            }         
        }
              
        if(x1 == x2){
            int column = x1;
            for(int row = y1; row <= y2; row++ ){
                if(board[row][column].equals("O")){
                    available = false;
                }
            }
        }
        
        return available;
    }
    
    public void attack(String[][] enemyBoard){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the coordinates to attack");
        int x = 0;
        int y = 0;
        boolean inputValid = false;
        do {
            try {
                System.out.print(">");
                String cordinate = scan.nextLine();
                x = Integer.valueOf(cordinate.substring(1)) - 1;
                y = (cordinate.charAt(0) - 'A' + 1) - 1;
                if(enemyBoard[y][x].equals("X")|enemyBoard[y][x].equals("M")){
                    System.out.println("Already has been attacked, Try Again!");
                    continue;
                }
                if (enemyBoard[y][x].equals("O")) {
                    System.out.println("YOU HIT!!");
                    hitOrMissboard[y][x] = "X";
                    enemyBoard[y][x] = "X";
                } else {
                    System.out.println("YOU MISS!");
                    hitOrMissboard[y][x] = "M";
                    enemyBoard[y][x] = "X";
                }
                inputValid = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error! Make sure values are between (A-J)(1-10)");
            } catch(NumberFormatException e){
                System.out.println("Error! Make sure values are between (A-J)(1-10)");
            }
        }while(!inputValid);
    }
    public void test(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = "X";
            }
        }
    }
    public void aiAttack(String[][] board){
        Random ran = new Random();
        int x = ran.nextInt(10);
        int y = ran.nextInt(10);

        if(board[y][x].equals("O")){
            System.out.println("AI: HIT!!");
            board[y][x] = "X";
        } else {
            System.out.println("AI: MISS!");
            board[y][x] = "M";
        }
    }
    public void makeAiBoard(){
        Random ran = new Random();
        int choice = ran.nextInt(3)+1;
        if(choice == 1){
            //Air craft
            int col = 0;
            for(int row = 0; row <= 4; row++ ){
                //   Y      X
                board[row][col] = "O";
                player.getShips().get(0).addCordinates(new Cordinate(row, col));
            }
            //Battle ship
            int row = 0;
            for(int column = 6; column <= 9; column++ ){
                board[row][column] = "O";
                player.getShips().get(1).addCordinates(new Cordinate(row, column));
            }
            //Submarine
            row = 5;
            int startColumn = 2;
            for (int column = startColumn; column < startColumn + 3; column++) {
                board[row][column] = "O";
                player.getShips().get(2).addCordinates(new Cordinate(row, column));
            }

        // Cruiser
            row = 7;
            startColumn = 3;
            for (int column = startColumn; column < startColumn + 3; column++) {
                board[row][column] = "O";
                player.getShips().get(3).addCordinates(new Cordinate(row, column));
            }

            // Destroyer
            row = 9;
            startColumn = 5;
            for (int column = startColumn; column < startColumn + 2; column++) {
                board[row][column] = "O";
                player.getShips().get(4).addCordinates(new Cordinate(row, column));
            }


        }
        if(choice == 2){
            // Aircraft Carrier
            //Air craft
            int col = 0;
            for(int row = 0; row <= 4; row++ ){
                //   Y      X
                board[row][col] = "O";
                player.getShips().get(0).addCordinates(new Cordinate(row, col));
            }
            //Battle ship
            int row = 0;
            for(int column = 6; column <= 9; column++ ){
                board[row][column] = "O";
                player.getShips().get(1).addCordinates(new Cordinate(row, column));
            }
            //Submarine
            row = 5;
            int startColumn = 2;
            for (int column = startColumn; column < startColumn + 3; column++) {
                board[row][column] = "O";
                player.getShips().get(2).addCordinates(new Cordinate(row, column));
            }

            // Cruiser
            row = 7;
            startColumn = 3;
            for (int column = startColumn; column < startColumn + 3; column++) {
                board[row][column] = "O";
                player.getShips().get(3).addCordinates(new Cordinate(row, column));
            }

            // Destroyer
            row = 9;
            startColumn = 5;
            for (int column = startColumn; column < startColumn + 2; column++) {
                board[row][column] = "O";
                player.getShips().get(4).addCordinates(new Cordinate(row, column));
            }
        }

        if(choice == 3){
//Air craft
            int col = 0;
            for(int row = 0; row <= 4; row++ ){
                //   Y      X
                board[row][col] = "O";
                player.getShips().get(0).addCordinates(new Cordinate(row, col));
            }
            //Battle ship
            int row = 0;
            for(int column = 6; column <= 9; column++ ){
                board[row][column] = "O";
                player.getShips().get(1).addCordinates(new Cordinate(row, column));
            }
            //Submarine
            row = 5;
            int startColumn = 2;
            for (int column = startColumn; column < startColumn + 3; column++) {
                board[row][column] = "O";
                player.getShips().get(2).addCordinates(new Cordinate(row, column));
            }

            // Cruiser
            row = 7;
            startColumn = 3;
            for (int column = startColumn; column < startColumn + 3; column++) {
                board[row][column] = "O";
                player.getShips().get(3).addCordinates(new Cordinate(row, column));
            }

            // Destroyer
            row = 9;
            startColumn = 5;
            for (int column = startColumn; column < startColumn + 2; column++) {
                board[row][column] = "O";
                player.getShips().get(4).addCordinates(new Cordinate(row, column));
            }
        }

    }
    public String[][] getBoard(){
        return board;
    }
    public String printBoard(String[][] board){
        String str = "  ";

        for (int i = 1; i <= board.length; i++) {
            str += i + " ";
        }
        str += "\n";

        for (int i = 0; i < board.length; i++) {
            char label = (char) ('A' + i);
            str += label + " ";
            for (int j = 0; j < board[i].length; j++) {
                str += board[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }

    public String[][] getHitOrMiss(){
        return hitOrMissboard;
    }
    public String toString(){
        String str = "  ";
        
        for (int i = 1; i <= board.length; i++) {
            str += i + " ";
        }
        str += "\n";
        
        for (int i = 0; i < board.length; i++) {
            char label = (char) ('A' + i);
            str += label + " ";
            for (int j = 0; j < board[i].length; j++) {
                str += board[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
}