/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inarow.Model;

import pkg4inarow.Controller.FullColumnException;

/**
 * @author Jorge Robla
 * Class with the grid and its attributes
 */
public class GameGrid {
    
    // height of the grid
    private int height;
    // width of the grid
    private int width;
    // matrix to represent the grid. Starts in 0
    private char[][] grid;
    // cell occupied to check the matrix for full
    private int occupiedCells;
    // how many cells the matrix has
    private int quantityCells;
    // how many cell in row we need to win
    private int winQuantity;
    
    // initialization method
    public GameGrid(int height, int width) {
        this.height = height;
        this.width = width;
        this.quantityCells = this.width * this.height;
        this.occupiedCells = 0;
        this.grid = new char[height][width];
        this.winQuantity = 4;
        
    }
    
    // draw the grid, return an string
    public String drawNewGrid() {
        
        StringBuilder gridString = new StringBuilder("");
            
        // draw header, numbers on each columns from 1 to 5 or from 1 to 10
        for (int i = 0; i < this.width; i++) {
              
              
              gridString.append("  " + String.valueOf(i + 1) + "  ");

                
        }
        // we print a new line
        gridString.append(System.lineSeparator());
            
        // draw first line below header. 
        for (int i = 0; i < this.width; i++) {
                
            gridString.append(" --- ");
                
        }
        // we print a new line    
        gridString.append(System.lineSeparator());
            
        // draw values travelling through the matrix
        for (int i = 0; i < this.width; i++) {
                                
            for (int j = 0; j < this.height; j++) {
                    
                if (this.grid[i][j] == '\u0000') // if it is null character
                    gridString.append("| " + " " + "  "); // we print null
                else    
                    gridString.append("| " + this.grid[i][j] + "  "); // we print the value inside of the cell
                    
            }
            
            // close the matrix and print new line
            gridString.append("|" + System.lineSeparator());

            // draw line after the matrix, the last line
            for (int p = 0; p < this.width; p++) {
                
                gridString.append(" --- ");
                
            }
            // print new line
            gridString.append(System.lineSeparator());
            
        }
                        

        
        return gridString.toString();
        
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getOccupiedCells() {
        return this.occupiedCells;
    }

    public int getQuantityCells() {
        return this.quantityCells;
    }

    public int setElementOnTheGrid(char letter, int column) throws FullColumnException {
        
        // we travlled from the bottom to the top looking for a free cell
        for (int i = this.height - 1; i >= 0; i--) {
            
            if (this.grid[i][column - 1] == '\u0000') { // null space, we can drop the letter
                
                this.occupiedCells++; // one cell is occupied
                this.grid[i][column - 1] = letter; // we drop the letter
                return i + 1; // return i + 1, from outside the cliente see matrix from 1 to 5, inside is from 0 to 5
                             // the matrix is encapsulated
                
            }
            
        }
        
        throw new FullColumnException();
        
    }

    public char getElementOnTheGrid(char letter, int i, int j) {
        
        return this.grid[i][j];
        
    }
    
    // the grid is full, we can not insert more elements
    public boolean isIsGridFull() {
        return quantityCells == occupiedCells;
    }
    
    // checks if there are four letters in row
    public boolean checks4InARow(int row, int column, char letter) {
        
        boolean win = false; 
        // check first for horizontal
        win = checksHorizontal(row, column, letter);
        
        if (win)
            return win;
        // check for vertical
        win = checksVertical(row, column, letter);
        if(win)
            return win;
        
        win = checksDiagonal(row, column, letter);
        return win;
        
    }
    // check if there are four pieces in row vertically
    private boolean checksVertical(int row, int column, char letter) {
        
        int initialrow =  (row - 1) - 3; // we stand on the row which is three positions behind current row to start to travell from there
        int finalrow = (row - 1) + 3; // last position is the third cell after the formal parameter row 
        int count = 0; // we count how many letters are in row
        
        if (initialrow < 0)
            initialrow = 0;
        
        if (finalrow >= this.width)
            finalrow = this.width - 1;
        
        // we travelled from final row to initial row to check if there are four pieces in row
        for (int i = finalrow; i >= initialrow; i--) {
            
            if (grid[i][column - 1] == letter) 
                count++; 
            else count = 0;
            
            if (count == winQuantity) // quanntity is equal to 4
                return true;
            
        }
        
        return false;
        
    }

    private boolean checksHorizontal(int row, int column, char letter) {

        int initialcolumn =  (column - 1) - 3; // we stand on the column which is three positions behind current column to start to travell from there
        int finalcolumn = (column - 1) + 3; // last position is the third cell after the formal parameter column 
        int count = 0;
        
        if (initialcolumn < 0)
            initialcolumn = 0;
        
        if (finalcolumn >= this.height)
            finalcolumn = this.height - 1;
        
        // we travelled from initial column to final column to check if there are four pieces in row
        for (int j = initialcolumn; j <= finalcolumn; j++) {
            
            if (grid[row - 1][j] == letter)
                count++;
            else count = 0;
            
            if (count == winQuantity) // quantity is equal to 4
                return true;
            
        }
        
        return false;

    }
    
    // NOT IMPLEMENTED
    private boolean checksDiagonal(int row, int column, char letter) {
        
        // diagonal we should check the values of i and j 
        // to know if the values increased or decreased 
        // from left to right
        final int fourInARow = 4;
                
        int initialColumn = column - 1;
        int finalColumn = column - 1; 
        
        int initialRow = row - 1;
        int finalRow = row - 1; 
        int count = 0;
         int movementCount = 0;
        
        // move initialColumn and initialRow position to the left diagonally
        while (movementCount < fourInARow - 1) {
            
            // if initialColumn is less than 0, we are in the first column
            // leave the loop
            if (initialColumn - 1 < 0) { 
                break;
            }
            
            // if initialRow is greater than the width of the array, we are in the last row
            // leave the loop
            if (initialRow + 1 >= this.width) {
                break;
            }
                        
            movementCount++; // we move one cell more to the left
            initialRow++; // update row
            initialColumn--; // update column
                   
        }       
        movementCount = 0;

        // check diagonal from left to right 
        while (movementCount <= fourInARow) {
            
            if (initialColumn >= this.height) {
                break;
            }
            
            if (initialRow < 0) {
                break;
            }            
                        
            if (grid[initialRow][initialColumn] == letter)
                count++;
            else count = 0;            

            if (count == fourInARow)
                return true;
                        
            movementCount++;
            initialRow--;
            initialColumn++;
            
            if ((initialRow == finalRow) && (initialColumn == finalColumn)) {
                movementCount = 1;
            }             
            
        }
           
        // from right to left
        initialColumn = column - 1;
        finalColumn = column - 1; 
        
        initialRow = row - 1;
        finalRow = row - 1; 
        count = 0;
        movementCount = 0;
        
        // move initialColumn and initialRow position
        while (movementCount < fourInARow - 1) {
            
            if (initialColumn + 1 >= this.height) {
                break;
            }
            
            if (initialRow + 1 >= this.width) {
                break;
            }
                        
            movementCount++;
            initialRow++;
            initialColumn++;
                   
        }       
        movementCount = 0;

        // check diagonal from left to right before passing row and column
        while (movementCount <= fourInARow) {
            
            if (initialColumn < 0) {
                break;
            }
            
            if (initialRow < 0) {
                break;
            }            
                        
            if (grid[initialRow][initialColumn] == letter)
                count++;
            else count = 0;            

            if (count == fourInARow)
                return true;
                        
            movementCount++;
            initialRow--;
            initialColumn--;
            
            if ((initialRow == finalRow) && (initialColumn == finalColumn)) {
                movementCount = 1;
            }             
            
        }
        
        return false;
        
    }
    
}
