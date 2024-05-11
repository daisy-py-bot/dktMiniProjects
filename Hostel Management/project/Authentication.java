package project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;




/**
 * This class allows the user to create an account if an account has not been created,
 * or verify an account, it there is an existing account already
 */
public class Authentication implements TheMain{

    // make the excel sheet a static variable that all objects in this class can access and modify
    // private static Workbook usersWorkbook = new HSSFWorkbook();
    // private static Sheet userDetails = usersWorkbook.createSheet("User Data");
    
    // data fields required
    private int totalNumOfAcc;  // increment for each account created
    private String role;
    private String username;
    private String userPassword;
    private String userFullName;
    private Date dateAccCreated;

    // constants
    private final String FILEPATH = "usersInformation.xls";
    private final int USERNAME_COL_NUM = 0;
    private final int PASSWORD_COL_NUM = 1;
    private final int FULL_NAME_COL_NUM = 2;
    private final int ROLE_COL_NUM = 3;
    private final int DATE_COL_NUM = 4;


    

//------------ MAIN MENU FOR ACCOUNT CREATION AN AUNTHENTICATION -----------
/**
 * this method invokes the full authentication process
 * @return returns 1 to proceed to student main portal and 2 to proceed to the admin main portal
 */
    public int mainMenu(){

        // check if the user has an account
        if (userHasAccount()){
            // verify the account

            // allow the user 3 attempts to enter a correct username and password
            int numAttempts = 3;

            while(numAttempts > 0){

                if(verifyUserAccount()){
                    // user account has been verified
                    
                    // print new line
                    System.out.println("-------------------------------");

                    // proceed to main page
                    System.out.println("Verification completed successfully");

                    // break out of the loop
                    break;
                }
                else if( numAttempts > 0){
                    // reduce the number of attempts
                    numAttempts--;

                    // tell user the number of attempts left
                    System.out.println("You have " + numAttempts + " attempts left.");
    
                }
                else{
                    // if the user does not enter a valid username and password in 3 attempts, log them out of the system for security reasons
                    System.out.println("Exceeded number of attempts.");
                    System.exit(0);

                }

            }
                 
        }

        // user has no account
        else {
            
            // create a new account
            if (createNewUserAccount()){
                // account created successfully
                // go to the main page
                System.out.println("\nAccount created successfully!.");
            }
            else{
                // tell user the account cannot be created
                System.out.println("\nFailed to create account.");

                // exit the programme
                System.exit(0);
            }    
        }

        
        // get the role of the user
        String userRole = getRoleOfUser();
        System.out.println(userRole);

        if(userRole.equals("Student")){
            // user has verified their account or they have created a new account
            // proceed to the student portal
            return 1;

        }
        else {
            // user has verified their account or they have created a new account
            // proceed to the admin menu
            return 2;
        }
    }
    

//------------- SET THE ROLE OF THE USER -----------------   
/**
 * Set the role of the user
 * The user can only be a student or an admin
 */
    public void setUserRole(){
        // create a scanner object
        Scanner input = new Scanner(System.in);
        // create a roleNum variable for the selected role
        int roleNum = 0;

        while(true){
            try{
                // ask user if they are an admin or a student
                System.out.println("\nWhat role do you identify with?");
                System.out.println("1. Student \n2. Admin");
                System.out.print("Enter: ");
                String roleResponse = input.next();
    
                roleNum = Integer.parseInt(roleResponse);
                // validate the input from the user
                // precondiction: the value of roleNum is either 1 or 2
                if(roleNum == 1){
                    // set role to student
                    this.role = "Student";

                    // break out the loop
                    break;
                }
                else if(roleNum == 2){
                    // set the role to admin
                    this.role = "Admin";

                    // break the loop
                    break;
                }
                else{
                    // user has not entered 1 or 2, so throw an exception
                    throw new Exception("Invalid Number. Type 1 or 2");
                }                
            }
            // handle the exceptions
            catch(Exception e){
                System.out.println("Input invalid");
                System.out.println("Please type 1 or 2");
            }
        }  
        // clear the buffer
        //input.nextLine();      
        
        
    }
    
    


//-----------------------CHECK IF USER HAS AN ACCOUNT---------------
/**
 * checks if the user has an account created
 * @return true if the user has an account
 */
    public boolean userHasAccount(){
        // create a Scanner object
        Scanner input = new Scanner(System.in);

        // create a variable to hold the status of the user
        boolean hasAccount = true;

        System.out.println("Welcome To Ashtel Hostel Services");
        System.out.println("--------------------------");
        System.out.println("Do you have an account? (y: Yes, n: No)");

        // prompt the user input unti they enter a valid input
        while(true){
            try{
                // ask the user if they have an account already
                System.out.print("> ");
                String response = input.nextLine();
                
                // check if the answer is yes, ignoring the cases
                if (response.equalsIgnoreCase("y")){
                    // user has an account
                    hasAccount = true;
                    break;
                }
                else if(response.equalsIgnoreCase("n")){
                    // user does not have an account
                    hasAccount = false;
                    break;
                }
                else{
                    // user has given an invalid input
                    throw new InputMismatchException("Invalid input. Type n for No or y Yes.");
                }
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
                input.nextLine(); // clear the information  in the buffer
            }
        }
        return hasAccount;


        
    }



//--------------------CREATE A NEW ACCOUNT----------------------------
/**
 * allows the user to create a new account
 * @return true if the account has been successfully created 
 */

    public boolean createNewUserAccount(){
        Scanner input = new Scanner(System.in);

        // account and details added to the Excel file for admin

        // ask user for their name
        System.out.print("\nFull Name: ");
        this.userFullName = input.nextLine();


        // ask user for a unique username
        this.username = createUserName();

        // ask user for a valid password
        this.userPassword = createUserPassword();

        // ask user to select their role
        setUserRole();

        // record date when account is created
        dateAccCreated = new Date();

        // account created successfully
        return (addAccountToFile());
    }


 //------------------ GET NUMBER OF ACCOUNTS ------------------------

    public int getNumOfAccounts(){
        int numAcc = 0;
        try{
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream("hostel1.xls");
            wb = new HSSFWorkbook(fis);
    
            // retrieve the excel sheet
            HSSFSheet sheet = wb.getSheet("sheet0");
            
            numAcc = sheet.getPhysicalNumberOfRows();

            wb.close();
            
    }
        catch(IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
        }
        
        return numAcc;
}




// -------------------- ADD USER ACCOUNT -------------------------


    /**
     * adds an account that has been created to an external file
     * @return true if the account has been added successfully
     */
    public boolean addAccountToFile(){
        try{

            
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream(FILEPATH);
            wb = new HSSFWorkbook(fis);
    
            // retrieve the excel sheet
            HSSFSheet sheet = wb.getSheet("sheet0");

            // find the last row in the excel sheet
            int lastrow = sheet.getLastRowNum();

            // create a new row for appending data for the user
            Row newRow = sheet.createRow(lastrow + 1);

            // create new cells in the new row
            // add the username
            Cell cell0 = newRow.createCell(0);
            cell0.setCellValue(this.username);

            // add the password
            Cell cell1 = newRow.createCell(PASSWORD_COL_NUM);
            cell1.setCellValue(this.userPassword);

            // add the full name
            Cell cell2 = newRow.createCell(FULL_NAME_COL_NUM);
            cell2.setCellValue(this.userFullName);

            // add the role
            Cell cell4 = newRow.createCell(ROLE_COL_NUM);
            cell4.setCellValue(this.role);

            // add the date
            Cell cell5 = newRow.createCell(DATE_COL_NUM);
            cell5.setCellValue(dateAccCreated);


            // save the changes in the excel file
            FileOutputStream fos = new FileOutputStream(FILEPATH);
            wb.write(fos);

            // close the streams
            wb.close();
            fis.close();
            fos.close();

            
            
        }
        catch(IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
            return false;
        }
        
        return true;
    }



// ------------------- CREATE THE USERNAME -------------------------


    /**
     * creates the username for the user
     * username should not already exist
     * @return String value of the username
     */
    public String createUserName(){
        Scanner input = new Scanner(System.in);

        // prompt user to give a valid username that is unique to them and does not already exist in the excel file
        while(true){
            System.out.print("\nValid User Name: " );

            String newUsername = input.nextLine();

            // check if the username already exists in the system
            if (!doesUsernameExist(newUsername)){
                System.out.println("Username is valid!\n");
                // username is unique
                return newUsername;
            
            }
            else{
                // password is not unique
                System.out.println("\n! Username already used.");
            }
        }
    }




// ------------------- CREATE USER PASSWORD ----------------------

    /**
     * create the password of the user
     * validates the password of user
     * @return String value of the password
     */
    public String createUserPassword(){
        Scanner input = new Scanner(System.in);
    

        // create a new variable for the suggested value
        String suggestedPassword = "";
        
        // create a new variable for the status of the password
        boolean passwordIsNotUnique = true;

        // prompt user to give a valid username that is unique to them and does not already exist in the excel file
        while(passwordIsNotUnique){
            System.out.println("Enter a valid password. (min length = 8 characters, at least 1 digit, 1 uppercase, and 1 special character (!@#)): " );
            System.out.print("> ");

            // WILL NEED TO VALIDATE THIS

            suggestedPassword = input.nextLine();

            // check is the password is valid
            
            while(!ValidateInfor.validatePassword(suggestedPassword)){
                // prompt the user for another password
                System.out.print("Enter a valid password: ");
                suggestedPassword = input.nextLine();
                
            }

            // check if the suggested password already exists in the system
            if (doesPasswordExist(suggestedPassword)){
                passwordIsNotUnique = true;
                // password is not unique   
            }else{
                // password is unique
                passwordIsNotUnique = false;
            }
        }
        // return a valid password
        return suggestedPassword;
    }



//------------------ CHECK IF USERNAME EXISTS -------------------

/**
 * 
 * @param username is the username for the user, either admin or student
 * @return true if the username already exists
 */
public boolean doesUsernameExist(String username){
    //checks if the given username exists already
    if (hasCellValue(username, 0)){
        // username exists
        return true;
    }else{
        // username does not exist
        return false;
    }

}
public boolean doesPasswordExist(String suggestedPassword){
    if (hasCellValue(suggestedPassword, 1)){
        // passwords are similar
        //PUT CONSTANT VALUES FOR COLUMN NUMBERS
        return true;
    }
    else{
        // passwords are not similar
        return false;
    }
}


// --------------------------GET ROLE OF THE STUDENT--------------------------------------
public String getRoleOfUser(){
    // get the role number
    int numRow = getRowNum(username, USERNAME_COL_NUM);

    // get the role of the student
    String roleOfUser = getCellValue(numRow, ROLE_COL_NUM);

    return roleOfUser;
}
public String getUserFullName(){
    int numRow = getRowNum(username, USERNAME_COL_NUM);

    String nameOfStudent = getCellValue(numRow, FULL_NAME_COL_NUM);

    return nameOfStudent;

}
public String getUserPassword(){
    int numRow = getRowNum(username, USERNAME_COL_NUM);

    String password = getCellValue(numRow, PASSWORD_COL_NUM);

    return password;

}

// --------------------- GET THE ROLE NUMBER -------------------

private int getRowNum(String nameOfUser, int columnIndex){
    // checks if the given password already exists in the database
    // open the excel file
    try{
        HSSFWorkbook wb = null;
        FileInputStream fis = new FileInputStream(FILEPATH);
        wb = new HSSFWorkbook(fis);

        // retrieve the excel sheet
        HSSFSheet sheet = wb.getSheet("sheet0");

        // get the number of rows in the excel sheet
        int numRows = sheet.getPhysicalNumberOfRows();

        // iterate through the rows using indexing
        for(int rowNum = 0; rowNum < numRows; rowNum++){
            // get the row
            Row row = sheet.getRow(rowNum);
        
            // check if the row is not null
            if(row != null){
                // get the cell value
                Cell cell = row.getCell(columnIndex);

                // check if the cell value is null
                if(cell != null){
                    // get the cell value
                    String cellValue = cell.getStringCellValue();

                    // compare the usernames
                    if (cellValue.equals(nameOfUser)){
                        // get the row number for that username
                        wb.close();
                        return rowNum;
                        
                    }
                }     

            }  
        }
        
    }
    catch(IOException e){
        System.out.println("Error opening excel file: " + e.getMessage());
    }
   return -1; 

}




// -------------------- GET THE CELL VALUE --------------------------
public String getCellValue(int rowNum, int colNum){
    String cellValue = "";

    try{
        HSSFWorkbook wb = null;
        FileInputStream fis = new FileInputStream(FILEPATH);
        wb = new HSSFWorkbook(fis);

        // retrieve the excel sheet
        HSSFSheet sheet = wb.getSheet("sheet0");
        Row row = sheet.getRow(rowNum);
        
            // check if the row is not null
            if(row != null){
                // get the cell value
                Cell cell = row.getCell(colNum);

                // check if the cell value is null
                if(cell != null){
                    // get the cell value
                    cellValue = cell.getStringCellValue();

                }}
                wb.close();

    }
    catch(IOException e){
        System.out.println("Error opening excel file: " + e.getMessage());
    }

    return cellValue;
}




//-------------- CHECK THE VALUE OF A CELL -------------------


private boolean hasCellValue(String givenCellValue, int columnIndex){
    // checks if the given password already exists in the database
    // open the excel file
    try{
        HSSFWorkbook wb = null;
        FileInputStream fis = new FileInputStream(FILEPATH);
        wb = new HSSFWorkbook(fis);

        // retrieve the excel sheet
        HSSFSheet sheet = wb.getSheet("sheet0");

        // get the number of rows in the excel sheet
        int numRows = sheet.getPhysicalNumberOfRows();

        // iterate through the rows using indexing
        for(int rowNum = 0; rowNum < numRows; rowNum++){
            // get the row
            Row row = sheet.getRow(rowNum);
        
            // check if the row is not null
            if(row != null){
                // get the cell value
                Cell cell = row.getCell(columnIndex);

                // check if the cell value is null
                if(cell != null){
                    // get the cell value
                    String cellValue = cell.getStringCellValue();

                    // compare the usernames
                    if (cellValue.equals(givenCellValue)){
                        // username already exists
                        return true;
                    }
                }     

            }  
        }
        
    }
    catch(IOException e){
        System.out.println("Error opening excel file: " + e.getMessage());
    }
   return false; 

}



// ------------ VERIFY THE ACCOUNT OF THE USER ----------------
    

public boolean verifyUserAccount(){
    Scanner input = new Scanner(System.in);
    // verify an existing account by matching the username & password given to the ones in the excel file

    // prompt the user for their username and password
    System.out.println("Enter your username: ");
    String givenUsername = input.nextLine();

    // check if the username exists
    if (!doesUsernameExist(givenUsername)){
        System.out.println("Username does not exist");
        return false;
    }

    System.out.println("Enter your password: ");
    String givenPassword = input.nextLine();

    // check if the password matches the username
    if (!doesPasswordExist(givenPassword)){
        System.out.println("Username and password do not match");
        return false;
    }

    // set the username and the password before verifying
    this.username = givenUsername;
    this.userPassword = givenPassword;


    // return true if both the username and the password exist
    return doesPasswordExist(givenPassword) && doesUsernameExist(givenUsername);


}}