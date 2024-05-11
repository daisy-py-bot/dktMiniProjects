package dktSTORE;

import java.util.ArrayList;
import java.util.Scanner;

public class BuyerMainMenu {
    /*
     * 1. Login to account or create an account
     * 2. Go to store -> Display departments
     * 3. Add item to cart
     * 4. View cart
     * 5. Checkout 
     */
    

    private static String username;
    private static String password;

    static final int MAX_TRIES = 3;
    static String lineBreak = "------------------------------------------------------------------------------------";
    

    static public void buyerDashboard(){
        // 1. Verify account
        if(accountLogin()){
            // account created or verified successully: 2. Proceed to main menu
            buyerMenu1();
        }     

    }

    


    static public void buyerMenu1(){
        Scanner input = new Scanner(System.in);
        int response = 0;
        // print a linebreak
        System.out.println(lineBreak);
        System.out.println(lineBreak);
        System.out.println("                        Main Menu");

        System.out.println("1. Go to Store \n " + 
                        "2. View cart \n " + 
                        "3. Log out");

        System.out.print("\nReply: ");
        response = input.nextInt();

        switch(response){
            case 1:
                // view the items in a chosen department
                viewStore();
                
                break;

            case 2:
                // allow the buyer to view their cart
                viewCart();
                break;

            case 3:
                System.exit(0);


                
        }

    }

    public static void buyerMenu2(String departmentName){
        // buyer is view a department
        Scanner input = new Scanner(System.in);
        int response = 0;
        // print a linebreak
        System.out.println(lineBreak);
        System.out.println(lineBreak);

        System.out.println("1. Add item to cart \n" + 
                        "2. View cart \n" + 
                        "3. Change department \n" + 
                        "4. Log out");

        System.out.print("Reply: ");
        response = input.nextInt();

        switch(response){
            case 1:
                addToCart(departmentName);
                break;

            case 2:
                // allow the seller to add a new department
                viewCart();
                break;

            case 3:
            // go back to choosing department in store to view
                viewStore();

            case 4:
                System.exit(0);
        }
    }

    static public String chooseDepartment(){
        Scanner input = new Scanner(System.in);
        // print line break
        System.out.println(lineBreak);

        System.out.println("Choose department\n");
        // display the departments
        Buyer.displayDepartments();

        System.out.print("\n Reply: ");
        
        int response = input.nextInt();
        System.out.println("Chosen department: " + Buyer.getDepartmentList().get(response - 1));

        return Buyer.getDepartmentList().get(response - 1);
    }

    public static String chooseItemToAddToCart(String department){
        Scanner input = new Scanner(System.in);

        // display the item names
        System.out.println("...These are the items we have in this department..." + department);
        Buyer.displayItemNamesInDepartment(department);

        // print line break
        System.out.println(lineBreak);

        System.out.print("\n Reply: ");
        
        int response = input.nextInt();

        return (String) (Buyer.getListOfItemNamesInDepartment(department).get(response - 1));
    }

    static public void viewStore(){
        // choose department
        String department = chooseDepartment();
        // view the items in the department (prices and quantity left)
        Buyer.viewDepartmentInStore(department);

        // ask if buyer wants to 1. add item to cart 2. change department 3. log out
        buyerMenu2(department);
        
    }

    public static void viewCart(){
        Buyer.viewCart(username);

        // show menu 3
        buyerMenu3();

    }

    public static boolean addToCart(String department){
        Scanner input = new Scanner(System.in);
        // print line break
        System.out.println(lineBreak);

        

        System.out.println("Now you need to select items");
        // select item to add to cart
        String itemName = chooseItemToAddToCart(department);

        System.out.print("Number of items: ");
        double quantity = input.nextDouble();

        if(Buyer.addNewItemToCart(username, department, itemName, quantity)){
            System.out.println(" \n" + itemName + " has been successfully added to you cart.");

            // go back to the menu 2
            buyerMenu2(department);
            return true;
        }
        else{
            System.out.println("Could not add " + itemName + " to the cart.");
            
            // go back to the menu2
            buyerMenu2(department);
            return false;
        }

        

    }
    

    public static void buyerMenu3(){
        // buyer is view a department
        Scanner input = new Scanner(System.in);
        int response = 0;
        // print a linebreak
        System.out.println(lineBreak);
        System.out.println(lineBreak);

        System.out.println("1. Checkout items in cart \n " + 
                        "2. Remove item from cart \n " + 
                        "3. Go back to main menu \n " + 
                        "4. Log out");

        System.out.print("Reply: ");
        response = input.nextInt();

        switch(response){
            case 1:
                checkout();
                // return to main menu
                buyerMenu1();
                break;

            case 2:
                // allow the buyer to remove item from cart: still viewing the cart
                removeItemFromCart();
                // view the cart
                viewCart();
                break;

            case 3:
            // go back to choosing department in store to view
                buyerMenu1();

            case 4:
                System.exit(0);
        }

    }

    public static void checkout(){
        // buying items in the cart
        System.out.println("                                        RECEIPT");
        System.out.println(lineBreak);
        String receipt = Buyer.checkout(username);

        // display receipt
        System.out.println(receipt);
        System.out.println("                                Thank you for shopping with us!");

        

    }

    public static void removeItemFromCart(){
        System.out.println("Select item to remove from cart");

    }



    // Verify accounts
    static public boolean accountLogin(){
        Scanner input = new Scanner(System.in);
        // Ask the shop owner if they have an online store or if they want to create one
        System.out.println(lineBreak);
        System.out.println("1. Login to account \n 2. Create a new account \n   3. Exit");

        System.out.print("Reply: ");

        // create and initialize the response variable
        int response = 3;
        
        int allowedTrials = MAX_TRIES;
        // allows the user to make 3 attempts
        while(allowedTrials > 0){
            try{
                // get the response from the user
                response = input.nextInt();
                if(response != 1 && response != 2 && response != 3){
                    // throw an InputMismatchException
                    throw new Exception();
                }
                else{
                    // break out of the loop
                    break;
                }
                
            }
            catch(Exception e){
                System.out.println(e.getMessage() + " Type 1 to log in to your account, 2 to create a new store and 3 if you want to exit");
                System.out.println("Reply: ");

            }
            // decrease the number of trials left
            allowedTrials--;

        }

        switch(response){
            // user is a store manager
            case 1:
                // verify the account of manager
                if (verifyAccount()){
                    // tell user account verification is complete
                    System.out.println("\n         Account verification complete!");
                }
                break;

            case 2:
                // direct the store manager to their main menu
                if(createNewAccount()){
                    System.out.println("\n          Account has been successfully created!");
                }
                break;
                // exit the program
            case 3:
                System.exit(0);

        }
        return true;
    }

    static public boolean verifyAccount(){
        Scanner input = new Scanner(System.in);

        String username = "";
        String password = "";

        // ask for the username
        int numOfTries = 3;
        while(numOfTries > 0){
            // print a line break
            System.out.println(lineBreak);

            // ask for a valid useername
            System.out.print("Enter your username: ");
            username = input.nextLine();
            System.out.println(lineBreak);

            // ask for a password
            System.out.print("Enter your password: ");
            password = input.nextLine();
            System.out.println(lineBreak);

            if(Buyer.loginAccount(username, password)){
                // login successful
                // set the password and username datafields
                BuyerMainMenu.username = username;
                BuyerMainMenu.password = password;

                return true;
            }
            else{
                numOfTries--;
                // try again: tell user their password or username is wrong
                System.out.println("Password and username do not match. Enter your correct username and password.\n" + numOfTries + " attempts left.");
                
            }
        }
        // else if the number of tries has been exceeded, return false: verification not complete
        return false;
    }

    static public boolean createNewAccount(){
        // public boolean createNewStore(String fullname, String username, String password, String email, 
        //String storeName, String motto, String address, ArrayList<String> departments)

        Scanner input = new Scanner(System.in);

        // print a line break   **** validate this information (passwords, username, email)
        System.out.println(lineBreak);
        System.out.println("   Let's help you set up your account...");


        System.out.print("\nEnter your full name: ");
        String fullname = input.nextLine();

        System.out.print("\nEnter a valid username: ");
        String username = input.nextLine();

        System.out.print("\nEnter a valid password: ");
        String password = input.nextLine();

        System.out.print("\nEnter your email address: ");
        String email = input.nextLine();

       
        if(Buyer.createAccount(fullname, username, password, email)){
            // account has been successfully created
            // set the password and username
            BuyerMainMenu.username = username;
            BuyerMainMenu.password = password;
            return true;
        }
        // return false if store failed to create
        return false;
    }

  
}

    

    

