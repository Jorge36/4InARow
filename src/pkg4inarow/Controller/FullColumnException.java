/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inarow.Controller;

/**
 * @author Jorge Robla
 * Exeception Class when column is full 
 */
public class FullColumnException extends Exception {

    /**
     * Creates a new instance of <code>CustomException</code> without detail
     * message.
     */
    private final static String msgColumnFull = "Column is full, please choose another one";

    public FullColumnException() {
        super(msgColumnFull);
    }

}
