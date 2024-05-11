// javac -cp "lib/*:." project/*.java
// java -cp "lib/*:." project.Test   
// https://www.youtube.com/watch?v=84WHFaiS8nk

/*
 *      To be done:
 * 1. validate the username of the store manager: it should follow the format of the sheet name in an excel file
 * 2. Validate the password for store manage and the buyer
 * 3. Handle invalid input from the user**
 * 4. Check if department exists and tell them it already exists
 * 5. Validate name of the department
 * 6 When you login, update the date
 */

package dktSTORE;



public class Test {
    public static void main(String[] args){
        MainPage transaction = new MainPage();
        transaction.welcomeMenu();
        

    }
}
