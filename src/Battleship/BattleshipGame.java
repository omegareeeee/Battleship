
package Battleship;

import Ships.Cordinate;
import Ships.Ship;

import java.sql.SQLOutput;

public class BattleshipGame {
    private Player ai;
    private Player player;
    private Board playerBoard;
    private Board aiBoard;
    public BattleshipGame(){
        player = new Player();
        ai = new Player();

        playerBoard = new Board(10,10, player);
        aiBoard = new Board(10,10, ai);

        aiBoard.makeAiBoard();
    }
    public void test(){

    }
    public void startGame(){
        playerBoard.place();
        System.out.println(playerBoard.printBoard(playerBoard.getHitOrMiss()));
        while (true){

            playerBoard.attack(aiBoard.getBoard());

            // prints hit or miss board
            System.out.println(playerBoard.printBoard(playerBoard.getHitOrMiss()));
            aiBoard.aiAttack(playerBoard.getBoard());
            // prints player board
            System.out.println(playerBoard.printBoard(playerBoard.getBoard()));

            if(hasWinner(player, ai)){
                break;
            }
        }
    }

    public boolean hasWinner(Player player, Player ai) {
        boolean aiHasShip= false;
        boolean playerHasShip = false;
        // checks if players ships are still there
        for (Ship ship: player.getShips()) {
            for(Cordinate cordinate: ship.getCordinates()){
                if(playerBoard.getBoard()[cordinate.getyVal()][cordinate.getxVal()].equals("O")){
                    playerHasShip = true;
                }
            }
        }
        // checks if ai ships are still there
        for (Ship ship: ai.getShips()) {
            //System.out.println(ship+"\n");
            for(Cordinate cordinate: ship.getCordinates()){
              //  System.out.println("Cordinaates: " + "("+cordinate.getxVal() +","+ cordinate.getyVal()+ ")");
                if(aiBoard.getBoard()[cordinate.getyVal()][cordinate.getxVal()].equals("O")){
                //    System.out.println("HAS 0");
                    aiHasShip = true;
                }

            }
        }
        if(!playerHasShip){
            System.out.println("You Lost");
            return true;
        }

        if(!aiHasShip){
            System.out.println("You Won!");
            return true;
        }
        return  false;
    }
}