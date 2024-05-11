package dktSTORE;

import java.util.ArrayList;
import java.util.Scanner;

public class SellerMainMenu {

    static final int MAX_TRIES = 3;
    static String lineBreak = "------------------------------------------------------------------------------------";
    


        // ********* 2 STORE MANAGER LOGS INTO ACCOUNT OR CREATE A NEW ACCOUNT************

    static public void storeManagerDashboard(){
        // 1. Verify account
        if(accountVerification()){
            // account created or verified successully: 2. Proceed to main menu
            sellerMenu();
        }     

    }

    static public boolean accountVerification(){
        Scanner input = new Scanner(System.in);
        // Ask the shop owner if they have an online store or if they want to create one
        System.out.println(lineBreak);
        System.out.println("1. Login to Store \n2. Create a new store \n3. Exit");
        System.out.println(lineBreak);
        System.out.print("Reply: ");

        // initialize the response
        int response = 3;
        
        // set the limit on the number of times the user can enter incorrect input
        int allowedTrials = MAX_TRIES;

        // allows the user to make 3 attempts
        while(allowedTrials > 0){
            try{
                // read the input from the user
                response = input.nextInt();
                if(response != 1 && response != 2 && response != 3){
                    // throw an exception if the user does not enter 1, 2, or 3
                    throw new Exception("\nInvalid input. ");
                }
                // else the input is valid: break out of the loop
                else{
                    break;
                }
                
            }
            catch(Exception e){
                System.out.println(e.getMessage() + "Type 1 to log in to your account, 2 to create a new store and 3 if you want to exit");

            }
            // decrease the number of trials left
            allowedTrials--;

        }

        switch(response){
            // user is a store manager
            case 1:
            System.out.println("Verifying account......");
                // verify the account of manager
                verifyStoreManagerAccount();
                break;
            case 2:
            System.out.println("\n....Let's help you set up your online store.");
                // direct the store manager to their main menu
                createNewAccount();
                break;
                // exit the program
            case 3:
            System.out.println("Exiting the program....");
                System.exit(0);

        }
        return true;
    }


    static public void sellerMenu(){
        Scanner input = new Scanner(System.in);
        int response = 0;
        // print a linebreak
        System.out.println(lineBreak);
        System.out.println(" \n                             SELLER MAIN MENU");
        System.out.println(lineBreak);

        System.out.println("1. Go to Store \n" + 
                        "2. Add new department to the store \n" + 
                        "3. Add new items to department \n" + 
                        "4. Restock items \n" + 
                        "5. Change price of item\n" + 
                        "6. Log out");
        System.out.println(lineBreak);
        System.out.print("Reply: ");
        response = input.nextInt();

        switch(response){
            case 1:
                // allow seller to view store
                viewStore();
                break;

            case 2:
                // allow the seller to add a new department
                appendNewDepartmentToStore();
                break;
                

            case 3:
                // allow seller to add a new item to a chosen department
                appendNewItemsToDepartment();
                break;


            case 4:


            case 5:


            case 6:
                System.exit(0);


                
        }

    }

    static public void viewStore(){
        Scanner input = new Scanner(System.in);
        // print line break
        System.out.println(lineBreak);

        System.out.println("\nChoose department");
        // display the departments
        Store.displayDepartmentNames();
        System.out.println("99. View all departments");
        System.out.println(lineBreak);
        System.out.print("Reply: ");

        int response = input.nextInt();

        if(response == 99){
            // display all the departments
            Store.displayAllDepartmentsInStore();
        }
        else{
            // display the specified department
            Store.displayItemsInDepartment(Store.getAllDepartments().get(response-1));
        }

        // return to the seller main menu
        sellerMenu();


    }

    static public void appendNewDepartmentToStore(){
        Scanner input = new Scanner(System.in);

        // print line break
        System.out.println(lineBreak);

        // ask the name of the department
        System.out.print("\nEnter name of new department: ");
        String departmentName = input.nextLine();

        // add new department to the store
        Store.addNewDepartment(departmentName);

        // tell user the department has been successfully added
        System.out.println("\n" + departmentName + " Department added successfully!");
        // go back to main menu
        sellerMenu();

    }
    
    static public void appendNewItemsToDepartment(){
        Scanner input = new Scanner(System.in);

        // get the name of the department (response - 1: to get the index number of the shee name)
        String departmentName = chooseDepartment();

        // ask user for the name of the item
        System.out.print("\nEnter full name of item: ");
        String itemName = input.nextLine();

        // ask for the unit price of the item
        System.out.print("\nEnter unit price of item: GH₵ ");
        double unitPrice = input.nextDouble();

        // ask for the quantity of the item
        System.out.print("\nEnter quantity (number of units of the new item): ");
        double quantity = input.nextDouble();

        // add new item to the department
        Store.addNewItemsToDepartment(departmentName, itemName, quantity, unitPrice);

        // tell user the item has been added successfully
        System.out.println(itemName + " has been successfully added to the " + departmentName + " Department!");
        // return to main menu
        sellerMenu();

    }

    public static String chooseDepartment(){
        Scanner input = new Scanner(System.in);
        // print a line break
        System.out.println(lineBreak);

        // Choose department to append new item
        System.out.println("\nSelect the department \n");
        Store.displayDepartmentNames();

        // print a line break
        System.out.println(lineBreak);

        // get response from the user
        System.out.print("Reply: ");
        int response = input.nextInt();

        // get rid of the white space
        input.nextLine();

        // get the name of the department (response - 1: to get the index number of the shee name)
        String departmentName = Store.getAllDepartments().get(response - 1);

        return departmentName;
    }


    public static String chooseItemFromDepartment(String departmentName){
        Scanner input = new Scanner(System.in);
        // print a line break
        System.out.println(lineBreak);

        // Choose name of the item
        System.out.println("\nSelect the name of the item \n");

        // display the names of the items for the chosen department
        Store.displayItemNamesInDepartment(departmentName);;

        // print a line break
        System.out.println(lineBreak);

        // get response from the user
        System.out.print("Reply: ");
        int response = input.nextInt();

        // get rid of the white space
        input.nextLine();

        // get the name of the department (response - 1: to get the index number of the item name)
        // type cast from object to String data type
        String itemName = (String) Store.getListOfItemNamesInDepartment(departmentName).get(response - 1);

        return itemName;
    }

    static public boolean verifyStoreManagerAccount(){
        Scanner input = new Scanner(System.in);

        String managerUsername = "";
        String managerPassword = "";

        // ask for the username
        int numOfTries = 3;
        while(numOfTries > 0){
            // print a line break
            System.out.println(lineBreak);

            // ask for a valid useername
            System.out.print("Enter your username: ");
            managerUsername = input.nextLine();
            System.out.println(lineBreak);

            // ask for a password
            System.out.print("Enter your password: ");
            managerPassword = input.nextLine();
            System.out.println(lineBreak);

            if(Store.loginStoreManagerAccount(managerUsername, managerPassword)){
                // login successful
                return true;
            }
            else{
                // try again: tell user their password or username is wrong
                System.out.println("Password and username do not match. Enter your correct username and password.");
                numOfTries--;
            }
        }
        // else if the number of tries has been exceeded, return false: verification not complete
        return false;
    }


    public static void changeUnitPriceOfItem(){
        // name of item, department name, new price of item

        // choose department
        String departmentName = chooseDepartment();

        // choose item
        String itemName = chooseItemFromDepartment(departmentName);

        // ch
    }

    public static void restockItemInDepartment(){
        // select department, select item, add the new quantity to be added
    }

    static public boolean createNewAccount(){
        // public boolean createNewStore(String fullname, String username, String password, String email, 
        //String storeName, String motto, String address, ArrayList<String> departments)

        Scanner input = new Scanner(System.in);

        // print a line break   **** validate this information (passwords, username, email)
        System.out.println(lineBreak);
        System.out.println("\nComplete the datafields below to create your store.");

        System.out.println("\n-------------------------- 1. PERSONAL INFORMATION ----------------------------");

        System.out.print("\nEnter your full name: ");
        String fullname = input.nextLine();

        System.out.print("\nEnter a valid username: ");
        String username = input.nextLine();

        System.out.print("\nEnter a valid password: ");
        String password = input.nextLine();

        System.out.print("\nEnter your email address: ");
        String email = input.nextLine();

        System.out.println("\n----------------------------2. STORE INFROMATION -------------------------------");
        System.out.print("\nEnter the name of your online store: ");
        String storeName = input.nextLine();

        System.out.print("\nEnter your motto: ");
        String motto = input.nextLine();

        System.out.print("\nEnter your address: ");
        String address = input.nextLine();

        System.out.println("\n----------------------------3. STORE DEPARTMENTS -------------------------------");
        ArrayList<String> departments = getDepartmentsList();

        if(Store.createNewStore(fullname, username, password, email, storeName, motto, address, departments)){
            // account has been successfully created
            return true;
        }
        // return false if store failed to create
        return false;
    }

    static public ArrayList<String> getDepartmentsList(){
        Scanner input = new Scanner(System.in);

        System.out.println("Set up at least 1 department (e.g. Food, Education, Electronics)");

        // create an arraylist of department names
        ArrayList<String> departmentsList = new ArrayList<>();

        int response = 1;
        do{
            // add departments to the store
            System.out.print("\nEnter name of department: ");

            // get the name of the department
            String departmentName = input.nextLine();

            // print a linebreak
            System.out.println(lineBreak);
            // add department to the list
            departmentsList.add(departmentName);

            // ask user if they want to add  more departments
            System.out.println("\n1. Add another department. \n 2. Save and Exit");
            System.out.println(lineBreak);
            System.out.print("Reply: ");
            
            // read the response from the user
            response = input.nextInt();
            // clear the white space
            input.nextLine();

        }
        while(response == 1);

        System.out.println("DONE! Departments ready for stocking.");

        // return a list of the departments
        return departmentsList;
    }



  
}

    
