/* 
1. Get input from the user:
    a. costOfGoods
    b. amountPaid
2. (Method) Calculate the change
3. (Method) Calculate the number of Ghana cedis notes:
4. (Method) Calculate the number of Ghana cedis coins
5. (Method) Calculate the number of Ghana coins for 50, 20, 10, 5, 1 pesewus coins
6. Print the change, the number of notes, and coins the client should give


 * 
 */
package Ass2;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class EssentialsStore{
    public static final char CEDI = (char)'\u20B5';
    public static void main(String[] args){
        // getting cost of goods and the amount paid from the client
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter the total cost of goods purchased: %c", (char)'\u20B5');
        double costOfGoods = input.nextDouble();

        // getting the amount paid from the client
        System.out.printf("\nEnter the amount paid: %c", (char)'\u20B5');
        double amountPaid = input.nextDouble();

        double changeAmount = computeChangeBreakdown(costOfGoods, amountPaid);



    }
    // METHOD 1: for that prints the break down of the changes
    public static double computeChangeBreakdown(double goodsCost, double paidAmount){
        double change = paidAmount - goodsCost;
        

        // getting the amount of notes(the whole number part)
        int changeNotes = (int)(change / 1);
        System.out.println(changeNotes);

        // getting the amount of coins (the fractional part)
        int changeCoins = (int) ((change - changeNotes ) * 100) + 1;
        System.out.println(changeCoins);
        
        // formatting the currency
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String formattedChange = currency.format(change);
        String formattedCost = currency.format(goodsCost);
        String formattedPaidAmount = currency.format(paidAmount);

        // getting the time of purchase
        LocalTime timeOfPurchase = LocalTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        String time = timeOfPurchase.format(formatTime);

        // printing the receipt
        System.out.println("            Payment Receipt              ");
        System.out.println("              Time: " + time);
        System.out.println("-----------------------------------------");
        System.out.printf("Total cost:  %20s\n", formattedCost);
        System.out.printf("Amount paid: %20s\n", formattedPaidAmount);
        System.out.printf("Change:      %20s\n", formattedChange);
        System.out.println("-----------------------------------------");
        System.out.println("           Bills payable                 ");
        System.out.println("-----------------------------------------");
        calculateNotes(changeNotes);
        calculatePesewaCoins(changeCoins);
        System.out.println("------------------------------------------");
        System.out.println("        Thank you for your purchase!      ");

        return change;
    }



    // calculating the number of cedis notes
    public static void calculateNotes(int changeNotes){
        // initializing an array with all the Ghanian banknotes bills
        int[] banknotes = {200, 100, 50, 20, 10, 5};

        for(int i = 0; i < banknotes.length; i++){
            // getting the number of bank notes
            int numNotes = changeNotes / banknotes[i];
            // getting the remaining change
            changeNotes %= banknotes[i];

            if(numNotes != 0){
                System.out.printf("%9d %2c%d   cedis notes\n", numNotes, CEDI, banknotes[i]);
            }
            
        }
        // if there are remaining cedis, then call the method for calculating the cedis coins
        if(changeNotes != 0){
            calculateCedisCoins(changeNotes);
        }

    }



    // calculating the amount of cedis coins
    public static void calculateCedisCoins(int changeCedisCoins){
        // initializing an array with all the Ghanian cedis coins
        int[] bankCedisCoins = {2, 1};

        for (int i = 0; i < bankCedisCoins.length; i++){
            // get the number of cedis coins
            int numberOfCedisCoins = changeCedisCoins / bankCedisCoins[i];
            // get the remaining amount
            changeCedisCoins %= bankCedisCoins[i];

            if (numberOfCedisCoins != 0){
                System.out.printf("%9d %2c%d   cedis coins\n", numberOfCedisCoins, CEDI, bankCedisCoins[i]);
            }
        }

    }

    // calculate the number of pesewa coins
    public static void calculatePesewaCoins(int pesewaChange){
        // array with all Ghanian pesewa coins values
        int[] pesewaCoins = {50, 20, 10, 5, 1};
      
        for(int i = 0; i < pesewaCoins.length; i++){
            //calculating the number of denominations for each pesewa
            int numPesewa = pesewaChange / pesewaCoins[i];
            // calculating the remaining coins
            pesewaChange %= pesewaCoins[i];

            if(numPesewa != 0){
                System.out.printf("%9d %3dp   coins\n", numPesewa, pesewaCoins[i]);
            }
        }
    }
    
}