/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inarow.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pkg4inarow.Model.GameGrid;
import pkg4inarow.Model.Player;
import pkg4inarow.view.View;

/**
 *
 * @author Jorge Robla
 * Controller Class which represents the brain of the system. Intermediary between view class and gamegrid class 
 * 
 */
public class Controller {
    
    // msg which are sent to the screen to be printed
    private final String msgWelcome = "**** Welcome to Game Simulator 4-in-a-row *****";
    private final String msghowToPlay = "How to play? " + System.lineSeparator()  + "A grid is shown on the screen (5x5 or 10x10). You play against the computer. The player/computer chooses a column to drop a piece/letter (C for computer and P for player) which will be inserted in the bottom most square in the column that is free. "
                                        + "Connect 4 pieces horizontally or vertically to win the match. The game can only be a draw if ALL the squares are filled and NO ONE has 4-in-a-row";
    private final String msgExample1 = "1) EXAMPLE - Assume a 5 x 5 grid, so :";
    private final String msgExample2 = "Start Grid ";
    private final String msgExample3 = "The start grid is always blank! Note how the columns are labelled across the top so that the player\n" +
                                       "knows what column to pick";
    private final String msgExample4 = "2) let’s say Player 1 chooses column 3";
    private final String msgExample5 = "3) Now let’s say the computer ALSO chooses column 3 – The bottom square is not free, so fill the next\n" +
                                       "one up";
    private final String msgExample6 = "4) To win the game, the Player (or the computer) must get “4 in a row”. This can be HORIZONTAL or\n" +
                                        "VERTICAL";
    private final String msgExample7 = "Sample win – Player";
    private final String msgExample8 = "The player has 4 in a row vertically in the column 1";    

    private final String msgExample9 = "---------------------------------------------------------------------------";    
    
    private final String msgEnterPlayerDetails = "Please, enter player details to start to play";
    private final String msgEnterFullname = "Please, enter your fullname (firstname surname separated by a space)";
    private final String msgEnterAge = "Please, enter your age";
    private final String msgEnterEmail = "Please, enter your email";
    private final String msgChooseGameQuestion = "Which game would you prefer to play?";
    private final String msgShortGame = "SHORT GAME - 5 X 5";
    private final int sizeShortGame = 5;
    private final String msgLongGame = "LONG GAME - 10 X 10";
    private final int sizeLongGame = 10;
    private final String msgLeaveGame = "Leave the game";
    private final String msgChooseColumn = "Select a valid column to fill the square/cell";
    private final String msgInvalidColumn = "Invalid column. This number does not exist";
    private final String msgGameHasFinished = "The game has finished";
    
    private final char playerLetter = 'P'; // letter which is put on the matrix
    private final char computerLetter = 'C'; // letter which is put on the matrix
    private final String msgGameDraw = "The game is a draw";
    private final String msgComputerPicksNumberX = "The Computer picks column X";
    private final String msgXWin = "X wins";
    private final String msgErrorAge = "The player must be aged over 12 and under 100";
    private final String msgPlayAgain = "Would you like to play again? (y/n)";
    
        
    private View view; // view 
    private Player player; // player human being
    private Player computer; // player computer
    private GameGrid gameGrid; // grid with its attributes
         

    public Controller(View view) {
        this.view = view;
    }
    
    public void executeProgram() {
        
        // print welcome message
        this.view.printMessage(msgWelcome);
        
        // print how to play
        this.view.printMessage(msghowToPlay);
        
        this.view.printMessage(System.lineSeparator());
        
        // more how to play and example showing it
        this.showExample();
        
        // user types personal details 
        this.view.printMessage(msgEnterPlayerDetails);
        
        // receives fullname
        String fullname = view.askForAnInput(msgEnterFullname, "^([a-zA-Z]+\\s)[a-zA-Z]+$");
        
        if (fullname == "") {
            this.view.printMessage(msgGameHasFinished);
            return;
        }    

        // receives age
        String age = view.askForAnInput(msgEnterAge + " - " + msgErrorAge, "^(?:|1[3-9]|[2-9][0-9])$");
        
        if (age == "") {
            this.view.printMessage(msgGameHasFinished);
            return;
        }

        // receives email
        String email = view.askForAnInput(msgEnterEmail, "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z0-9.-]+$");
        
        if (email == "") {
            this.view.printMessage(msgGameHasFinished);
            return;            
        }
        
        // create player
        this.player = new Player(fullname, Integer.parseInt(age), email);
        
        // create computer player
        this.computer = new Player("The Computer", 0, "");
        
        List<String> options = new ArrayList<>(); 
        options.add(msgShortGame);
        options.add(msgLongGame);
        
        // ask user what kind of game (s)he wants to play, or if (s)he wants to quit the game 
        int choice = view.chooseOption(options, msgChooseGameQuestion, msgLeaveGame);
        
        if (choice == 1) {// short game
            this.gameGrid = new GameGrid(sizeShortGame, sizeShortGame);  // create class grid to start to play - shortgame 5x5      
        } else if (choice == 2) { // long game
            this.gameGrid = new GameGrid(sizeLongGame, sizeLongGame); // create class grid to start to play - shortgame  10x10                     
        } else if (choice == 3) { // leave the game
            this.view.printMessage(msgGameHasFinished);
            return;            
        }
        
        boolean playAgain = false;
        // when a game is finished the user can choose if (s)he wants to continue playing
        while(true) {
            
            this.startGame(); // start to play
            playAgain = this.view.askQuestionYesOrNo(msgPlayAgain); // ask user Would you like to play again?
            System.out.println(System.lineSeparator());
            
            if (!playAgain) { // program finished
                
                this.view.printMessage(msgGameHasFinished);
                return;                
            
            }
            
            // programs ask user if s(he) wants to change the game or quit
            choice = view.chooseOption(options, msgChooseGameQuestion, msgLeaveGame);
            
            if (choice == 1) {// short game 5x5
                this.gameGrid = new GameGrid(sizeShortGame, sizeShortGame);            
            } else if (choice == 2) { // long game 10x10
                this.gameGrid = new GameGrid(sizeLongGame, sizeLongGame);                      
            } else if (choice == 3) { // quit
                this.view.printMessage(msgGameHasFinished);
                return;            
            } 
            
        }     
    }
    
    public void startGame() {
                
        String pattern = "^[0-9]+$"; // pattern digits
        
        int column = 0;
        int row = 0;
        boolean got4InRow = false; // variable which kept if somebody got 4 in row
        
        char turn = playerLetter; // turn to play, player starts
        
        while(true) { 
            
            if (this.gameGrid.isIsGridFull()) { // if the grid is full, the game is a draw
                
                this.view.printMessage(msgGameDraw);
                
                this.view.printMessageWithoutBreakingLines(this.gameGrid.drawNewGrid());
                
                return;
            
            }    
        
            this.view.printMessageWithoutBreakingLines(this.gameGrid.drawNewGrid()); // draw the grid on the screen
            
            if (turn == playerLetter) { // player moves
            
                column = Integer.parseInt(view.askForAnInput(msgChooseColumn, pattern)); // player chooses column

                if (column < 1 || column > this.gameGrid.getWidth()) { // if column is less than 1 or greater than width error!!
                    this.view.printMessage(msgInvalidColumn);
                    this.view.promptEnterKey();
                    continue;
                }
            
                try {
                    row = this.gameGrid.setElementOnTheGrid(turn, column); // set element on the grid and return row
                } catch (FullColumnException ex) {
                    this.view.printMessage(ex.getMessage());
                    this.view.printMessage(System.lineSeparator());
                    continue;
                }
                
            } else if (turn == computerLetter) { // computer moves
                
                while (true) {
                
                    column = chooseRandomNumberForCompuer(); // choose number randomly
                    try {
                        row = this.gameGrid.setElementOnTheGrid(turn, column); // set element on the grid and return row
                        this.view.printMessage(msgComputerPicksNumberX.replace("X", String.valueOf(column))); // show on the screen what column whas chosen by the computer
                        this.view.printMessage(System.lineSeparator());
                        break;
                    } catch (FullColumnException ex) {}
                
                }
                
            }
            
            got4InRow = this.gameGrid.checks4InARow(row, column, turn); // check if there is 4 in a row
            
            if (got4InRow) { // true
                // print grid
                this.view.printMessageWithoutBreakingLines(this.gameGrid.drawNewGrid());
                // print who wins
                if (turn == playerLetter) {
                    
                    this.view.printMessage(msgXWin.replace("X", player.getFirstName()));
                    
                } else if (turn == computerLetter) {
                
                    this.view.printMessage(msgXWin.replace("X", computer.getFirstName() + " " + computer.getLastName()));
                            
                }
                
                return;
                    
            }
            // change player
            if (turn == playerLetter)
                turn = computerLetter;
            else turn = playerLetter;
                
        }
           
    }
    // method to choose a random from a range of values 0..X
    private int chooseRandomNumberForCompuer() {

        Random r = new Random();
        int someNumber = r.nextInt(this.gameGrid.getWidth());
        return someNumber + 1;
        //this will generate a random no from 0-this.gameGrid.getWidth() and store it in “someNumber”
        
    }
    // show example how to play this game, drawing a grid on the screen
    private void showExample() {

        this.view.printMessage(msgExample1);
        
        this.view.printMessage(msgExample2);
        
        GameGrid gameGridAux = new GameGrid(5, 5);
        
        this.view.printMessage(System.lineSeparator());
        
        this.view.printMessageWithoutBreakingLines(gameGridAux.drawNewGrid());
        
        this.view.printMessage(msgExample3);

        this.view.promptEnterKey();
        
        this.view.printMessage(msgExample4);
        
        this.view.printMessage(System.lineSeparator());

                
        try {
            gameGridAux.setElementOnTheGrid(playerLetter, 3);
        } catch (FullColumnException ex) {
        }
        
        this.view.printMessageWithoutBreakingLines(gameGridAux.drawNewGrid());
        
        this.view.promptEnterKey();
        
        this.view.printMessage(msgExample5);

        this.view.printMessage(System.lineSeparator());

         try {
            gameGridAux.setElementOnTheGrid(computerLetter, 3);
        } catch (FullColumnException ex) {
        }
        
        this.view.printMessageWithoutBreakingLines(gameGridAux.drawNewGrid());
        
        this.view.promptEnterKey();

        this.view.printMessage(msgExample6);
        
        this.view.printMessage(msgExample7);
        
        this.view.printMessage(System.lineSeparator());

        try {
            
            gameGridAux.setElementOnTheGrid(playerLetter, 1);
            gameGridAux.setElementOnTheGrid(playerLetter, 1);
            gameGridAux.setElementOnTheGrid(playerLetter, 1);
            gameGridAux.setElementOnTheGrid(playerLetter, 1);
            
        } catch (FullColumnException ex) {
        }

        this.view.printMessageWithoutBreakingLines(gameGridAux.drawNewGrid());

        this.view.printMessage(msgExample8);
        
        this.view.printMessage(System.lineSeparator());

        this.view.printMessage(msgExample9);
                
        this.view.printMessage(System.lineSeparator());

        
    }
    
    
    
    
}
