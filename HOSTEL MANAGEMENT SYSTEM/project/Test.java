package project;
// javac -cp "lib/*:." project/*.java
// java -cp "lib/*:." project.Test  

/*
 * 1. add comments in javadoc syntax
 * 2. organize the methods
 * 3. improve the output...add more text to guide the user
 * 4. implement a method to validate the user password and their username
 * 5. implement a method to delete an account
 * 6. implement a method for a user to reset their passoword
 */
public class Test {
    public static void main(String[] args){
        Authentication dkt = new Authentication();
        String cellValue = dkt.getCellValue(1, 1);

        System.out.println(cellValue);
        dkt.mainMenu();
    }
    
}
