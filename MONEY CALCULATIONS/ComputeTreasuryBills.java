
import java.text.NumberFormat;
import java.util.Scanner;

public class ComputeTreasuryBills {
    public static void main(String[] args){
        // Getting the user input
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("Welcome to Daisy Investment Fund Company!\nFill in the details below to calculate your total investment and total interest for your chosen maturity period.");
        System.out.println("-----------------------------------------");
        System.out.print("Principal: ");
        double principal = input.nextDouble();
        System.out.print("\nAnnual Rate: ");
        double rate = input.nextDouble();
        System.out.print("\nNumber of periods per year: ");
        double periodsPerYear = input.nextDouble();
        System.out.print("\nDeposits after the initial principle: ");
        double deposit = input.nextDouble();
        System.out.println("\nTotal periods for accruement: ");
        double totalPeriods = input.nextDouble();
        System.out.println("--------------------------------------");

        double totalInvestmentAccrued = computeInvestmentValue(principal, rate, periodsPerYear, deposit, totalPeriods);
        double totalAmountInvested = principal + (deposit * (totalPeriods - 1));    // calculating the amount invested by the user for the full period (deposit paid starting from the second period)
        double totalInterest = totalInvestmentAccrued - totalAmountInvested;        // calculating thr total interest gained over the full period
        
        // Formatting the currency
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String investmentAccrued = currency.format(totalInvestmentAccrued);
        String sumInterest = currency.format(totalInterest);

        // Displating the total investment anf total interest
        System.out.printf("Total value of investment: %26s\n", investmentAccrued);
        System.out.printf("Total interest earned over the period: %7s\n", sumInterest);

    }


    public static double computeInvestmentValue(double principle, double rate, double periodsPerYear, double deposit, double periods){
        double periodicInterest = (rate / periodsPerYear) / 100;  // calculatiing the interest gained per period as a decimal
        double investmentRolledOver = principle * (1 + periodicInterest);   // calculating the initial investment rolled over from the principal

        for(int i = 0; i < periods - 1; i++){
            investmentRolledOver = (investmentRolledOver + deposit) * (1 + periodicInterest);  // calculating the successive investment rolled over for the remaining (periods - 1)
        }

        return investmentRolledOver;
    }



}

/*
Test Case 1:

Welcome to Daisy Investment Fund Company!
Fill in the details below to calculate your total investment and total interest for your chosen maturity period.
-----------------------------------------
Principal: 500

Annual Rate: 28.8845

Number of periods per year: 4

Deposits after the initial principle: 100

Total periods for accruement: 
14
--------------------------------------
Total value of investment:             GH₵3,517.78
Total interest earned over the period: GH₵1,717.78


Test case 2:
Welcome to Daisy Investment Fund Company!
Fill in the details below to calculate your total investment and total interest for your chosen maturity period.
-----------------------------------------
Principal: 500

Annual Rate: 28.8845

Number of periods per year: 4

Deposits after the initial principle: 400

Total periods for accruement: 
14
--------------------------------------
Total value of investment:             GH₵10,089.90
Total interest earned over the period: GH₵4,389.90

 */