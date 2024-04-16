/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inarow.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jorge Robla
 * Class view to print elements on the screen
 */
public class View {
    
    
    // Attribute global in to read from keyboard
    private final Scanner in = new Scanner(System.in);
    // Attribute global messages which are print to the user
    private final String msgInvalidValue = "You have entered an invalid input. Please, try again";

    
    
    // Close scanner
    public void CloseScanner() {
        
        in.close();
        
    }
    
    // method to ask user to type an input. The input is validated using regular expressions
    public String askForAnInput(String msgQuestion, String regex) {
        
        
        //String input;
        String input;
        int howMany = 1;
       
        System.out.println(msgQuestion);

        do {
                  
            // Scanner for user input
            input = in.nextLine();  
            
            // if the user type a valid value
            if (input.matches(regex))
                return input;
            else {
                // if the user fail to type the value 4 times, the metohod return with value null
                if (howMany > 3) 
                    return "";
                        
                System.out.println(msgInvalidValue);
                howMany++;
                     
            }
                             
        } while(true);
       
    }
    
    // print a message on the screen without beaking lines at 75
    public void printMessageWithoutBreakingLines(String message) {
       
       System.out.println(message);
       
   }

    // print a message on the screen beaking lines at 75
    public void printMessage(String message) {
       
       System.out.println(breakLines(message, 75));
       
   }
    
    // Program taken from https://stackoverflow.com/questions/7528045/large-string-split-into-lines-with-maximum-length-in-java
    
    private static final char NEWLINE = '\n';
    private static final String SPACE_SEPARATOR = " ";
    //if text has \n, \r or \t symbols it's better to split by \s+
    private static final String SPLIT_REGEXP= "\\s+";

    public static String breakLines(String input, int maxLineLength) {
        String[] tokens = input.split(SPLIT_REGEXP);
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        for (int i = 0; i < tokens.length; i++) {
            String word = tokens[i];

            if (lineLen + (SPACE_SEPARATOR + word).length() > maxLineLength) {
                if (i > 0) {
                    output.append(NEWLINE);
                }
                lineLen = 0;
            }
            if (i < tokens.length - 1 && (lineLen + (word + SPACE_SEPARATOR).length() + tokens[i + 1].length() <=
                    maxLineLength)) {
                word += SPACE_SEPARATOR;
            }
            output.append(word);
            lineLen += word.length();
        }
        return output.toString();
    }
 
    // Program taken from https://stackoverflow.com/questions/7528045/large-string-split-into-lines-with-maximum-length-in-java
 
    // Method which allow user to choose an option from the screen
    public int chooseOption(List<String> options, String msgQuestion, String goBack) {
        
       int choice;
       int howMany = 1;
       int position;
       
       System.out.println(msgQuestion);

       do {
           
            try {
                 // code to display all possible user choices
                 printOptions(options);
                 // go back to previous menu option
                 position = options.size() + 1;
                 System.out.println("[" + position + "] " + goBack + System.lineSeparator());
                 // Scanner for user input
                 choice = in.nextInt();
                 // if the user type a valid value of movie
                 in.nextLine();
                 if(choice>=1 && choice<=position) 
                    return choice; // return that movie
                 else {
                    // if the user fail to type the value 4 times, the metohod return with value null
                    if (howMany > 3) 
                        return -1;
                        
                    System.out.println(msgInvalidValue);
                    howMany++;
                     
                 }
                     
            } catch(InputMismatchException e) {
                
               // to clear an invalid input from the Scanner
               in.next();
                // if the user fail to typed the value 4 times, the metohod return with value null
               if (howMany > 3) 
                    return -1;
                        
               System.out.println(msgInvalidValue);
               howMany++;
            }
        
        } while(true);
       
    }
    
    // print options on the screen
    public void printOptions(List<String> options) {

        int position = 1;
        
        for (String option: options) {
            
            System.out.println("[" + position + "] " + option);
            position++;
            
        }
                               
    }
    
    // https://stackoverflow.com/questions/26184409/java-console-prompt-for-enter-input-before-moving-on
    // this method was taken from the previous link 
    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
   // method to ask the user a questions which can be answered Yes or NO
   public boolean askQuestionYesOrNo(String msgQuestion) {
       
       char answer;
       int howMany = 1;

       System.out.println(msgQuestion);

       do {
           
            try {
                // Scanner for user input
                //answer = in.next().charAt(0); 
                answer = in.next().charAt(0); 
                in.nextLine();
                switch (answer) {
                    case 'N':
                    case 'n':
                        return false;
                    case 'Y':
                    case 'y':
                        return true;
                    default:
                        if (howMany > 3)
                            return false;
                        System.out.println(msgInvalidValue);
                        howMany++;
                        break;
                }
                                     
            } catch(InputMismatchException e) {

               // if the user fail to typed the value 4 times, the metohod return with value 0
               if (howMany > 3) 
                    return false;
                        
               System.out.println(msgInvalidValue);
               howMany++;
            }
        
        } while(true);       
       
       
    }
    
    
}
