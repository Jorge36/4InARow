/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4inarow.Model;

/**
 * @author Jorge Robla
 * Player Class with first name, last name, age and email 
 */
public class Player {
    
    private String firstName;
    private String lastName;
    private int age;
    private String email;

    public Player(String fullName, int age, String email) {
        
        String[] parts = fullName.split(" ");
        
        this.firstName = parts[0];
        this.lastName = parts[1];
        this.age = age;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
       
}
