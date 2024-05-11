package project;

import java.util.Scanner;

public class ValidateInfor {
    public static void main(String[] args){
        // if minimum requirements exceed the minimum password length
        // Getting input from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Create your desired strong password by filling out the details below:");
        System.out.print("Minimun password length e.g. '8 or 10 or 15': ");
        int minPasswordLen = input.nextInt();
        System.out.print("\nMinimum number of uppercase letters: ");
        int minNumOfUpperCase = input.nextInt();
        System.out.print("\nMinimum number of digits: ");
        int minNumOfDigits = input.nextInt();      
        System.out.print("\nAllowed the special characters e.g '#@&': ");
        String specialChars = input.next();
        System.out.println("Minimum number of special characters: ");
        int minNumOfSpecialChar = input.nextInt();

        String password = generatePassword(minPasswordLen, minNumOfUpperCase, minNumOfDigits, minNumOfSpecialChar, specialChars);
        System.out.println("\nPassword: " + password);
        System.out.printf("Password length: %d\n", password.length());

        boolean passwordCheck = checkPassword(minPasswordLen, minNumOfUpperCase, minNumOfDigits, minNumOfSpecialChar, specialChars, password);
        System.out.println(passwordCheck);


    }


    // generate the password
    public static String generatePassword(int minPasswordLen, int minNumOfUpperCase, int minNumOfDigits, int minNumOfSpecialChar, String specialChars ){
        String password = "";
        int countUpperCase = 0;
        int countDigits = 0;
        int countSpecialChar = 0;

        // generate a random number in the range 0 to 5 to allow flexibility in the length of the password
        int passwordLength = minPasswordLen + ((int) (Math.random() * 6));

        while (password.length() < passwordLength){
            if (countUpperCase <= minNumOfUpperCase){
                password += randomUpperCase();   // add a random upper case letter
                countUpperCase++;
            }
            if (countDigits <= minNumOfDigits){
                password += randomInteger();
                countDigits++;           // keep track of the number of digits added to the password
            }
            if (countSpecialChar < minNumOfSpecialChar){
                password += randomSpecialChar(specialChars);
                countSpecialChar++;    // keep track of the number of special characters added to the password
            }
            password = password + randomLowerCase();    // add a random lower case letter
        }

        return password;
    }


    // generate random uppercase letters
    public static char randomUpperCase(){
        int randomChar = 'A' + (int)(Math.random() * ('Z' - 'A' + 1));
        return (char) randomChar;
    }

    // generate random lower case letter
    public static char randomLowerCase(){
        int randomChar = 'a' + (int)(Math.random() * ('z' - 'a' + 1));
        return (char) randomChar;

    }

    // generate random number between 0 and 9
    public static int randomInteger(){
        int randomInteger = (int) (0 + Math.random() * 10);
        return randomInteger;

    }

    // generate random special character
    public static char randomSpecialChar(String specialChar){
        int randomIndex = (int) ( Math.random() * specialChar.length());   // randomly choose an index value for a special character
        System.out.printf("\nrandomIndex for special character: %d", randomIndex);
        return specialChar.charAt(randomIndex);    // return the special character at the chosen index value
    }


    // check the password
    public static boolean checkPassword(int minPasswordLen, int minNumOfUpperCase, int minNumOfDigits, int minNumOfSpecialChar, String specialChars, String password){
        int countUpperCase = 0;
        int countDigits = 0;
        int countSpecialChar = 0;
        

        // check the length of the password 
        if (password.length() < minPasswordLen){
            System.out.println("Password too short");
            return false;
        }

        // checking the inner requirements of the password
        for (int i = 0; i < password.length(); i++){

            // checking if the character is an uppercase letter
            if ((int)password.charAt(i) >= (int)'A' && (int)password.charAt(i) <= (int)'Z'){
                countUpperCase++;
            }
            else if (checkInteger(password.charAt(i))){
                countDigits++;
            }
            else if (checkSpecialChar(specialChars, password.charAt(i))){
                countSpecialChar++;
            }
            else if ((int)password.charAt(i) >= (int)'a' && (int)password.charAt(i) <= (int)'z'){
                continue;

            }
            else{
                return false;
            }
        }

        if (countUpperCase < minNumOfUpperCase || countDigits < minNumOfDigits || countSpecialChar < minNumOfSpecialChar){
            System.out.println("Missing requirement.");
            return false;
        }
        return true;
    }


    // check for an integer:
    public static boolean checkInteger(char character){
        boolean answer = false;
 
        switch(character){
            case '0': answer = true; break;
            case '1': answer = true; break;
            case '2': answer = true; break;
            case '3': answer = true; break;
            case '4': answer = true; break;
            case '5': answer = true; break;
            case '6': answer = true; break;
            case '7': answer = true; break;
            case '8': answer = true; break;
            case '9': answer = true; break;
            default: answer = false;
        }

        return answer;
    }

    // Check for a special character
    public static boolean checkSpecialChar(String specialChars, char character){
        for (int i = 0; i < specialChars.length(); i++){
            if (specialChars.charAt(i) == character){
                return true;
            }

        }
        return false;
    }


    public static boolean validatePassword(String password){
        return checkPassword(8, 1, 1, 1, "!@#$%&*", password);
            
    }
}

    
