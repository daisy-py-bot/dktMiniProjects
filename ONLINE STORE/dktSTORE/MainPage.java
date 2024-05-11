package dktSTORE;

import java.util.ArrayList;
import java.util.Scanner;

public class MainPage {
    final int MAX_TRIES = 3;
    String lineBreak = "------------------------------------------------------------------------------------";
    

    public void welcomeMenu(){
        Scanner input = new Scanner(System.in);

        // greet the user
        System.out.println("\n---------------------------WELCOME to DKT ONLINE STORE PLATFORM! ENJOY YOUR SHOPPING WITH US.-----------------------");

        System.out.println("\nSelect your role");
        System.out.println(lineBreak);
        System.out.println("1. Consumer \n2. Store manager\n3. Exit");
        System.out.println(lineBreak);
        System.out.print("Reply: ");

        // initialize the response variable
        int response = 3;

        // give the user a limited number of trials
        int allowedTrials = MAX_TRIES;

        // allows the user to make 3 attempts
        while(allowedTrials > 0){
            try{
                // read the response from the user
                response = input.nextInt();

                // check if the response takes any of the choices 1, 2, or 3
                if(response != 1 && response != 2 && response != 3){
                    // response is not 1, 2, or 3, so throw an exception because the user has entered an invalid input
                    throw new Exception("Invalid input. ");
                }
                // else, the response is valid, so break from the loop
                else{
                    if(response != 3){
                        // user is continueing to the login page
                        System.out.println("\nWait whilst you are being directed to the login page...");
                    }
                    

                    // break out of the loop
                    break;
                }

                
            }
            catch(Exception e){
                System.out.println(e.getMessage() + "Type 1 if you are a consumer, 2 if you are a shop manager and 3 if you want to exit");
                System.out.println("Reply: ");

            }
            // decrease the number of trials left
            allowedTrials--;

        }

        // Direct the user to their dashboard based on their chosen role
        switch(response){
            // user is a buyer
            case 1:
                // direct the buyer to the consumer main menu
                System.out.println("\n                     BUYER DASHBOARD");
                BuyerMainMenu.buyerDashboard();
                break;
            case 2:
                // direct the store manager to their main menu
                System.out.println("\n                    STORE MANAGER DASHBOARD");
                SellerMainMenu.storeManagerDashboard();
                break;
                // exit the program
            case 3:
                // end the program
                System.exit(0);


        }

    }


}
