/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inarow;

import pkg4inarow.Controller.Controller;
import pkg4inarow.Model.GameGrid;
import pkg4inarow.view.View;

/**
 *
 * @author Jorge Robla
 * Class with main method. start point for the program
 * 
 */
public class Game {

       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // create a view
        View view = new View();
        // create a controller, passing the view as parameter
        Controller controller = new Controller(view);
        // execute the program
        controller.executeProgram(); 
        
    }
    
}
