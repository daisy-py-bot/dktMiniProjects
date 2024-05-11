package dktSTORE;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class CreateStore {
    protected static final int USERNAME_COL_NUM = 0;
    protected static final int PASSWORD_COL_NUM = 1;
    private static final int PERSONAL_DETAILS_HEADER_ROW_NUM = 0;
    private static final int PERSONAL_DETAILS_ROW_NUM= 1;
    private static final int PERSONAL_DETAILS_START_COL_NUM= 0;
    public static final String[] FIRST_SHEET_HEADERS = {"USERNAME", "PASSWORD", "OWNER'S NAME", "EMAIL", "STORE NAME", "ADDRESS", "OUR MOTTO", "DATE CREATED", "DATE LAST VISITED"};
    static private final int DEPARTMENT_HEADER_ROW_NUM = 0;
    public static  final String[] DEPARTMENT_HEADERS = {"ITEM NAME", "UNIT PRICE", "UNITS LEFT",  "QTY SOLD", "SUPPLY", "CUR. SALES", "EX. SALES" };
    public static final String[] USER_HEADERS = {"USERNAME", "PASSWORD", "FULL NAME", "EMAIL", "DATE CREATED", "DATE LAST VISITED"};


    private static final String USER_ACCOUNTS = "USER ACCOUNTS";
    public static final String FIRST_SHEET_NAME = "Store Details";
    // create a new excel file for the new store
    protected static String storeFile;
    protected static String usersFile;
    
   
    
/**
 * creates a new  online store
 * store has the details of the owner (full name of the owner, store name, address for the headerquaters)
 * store has the initial departments that the owner adds
 * each department has four headers (item name, unit price, quantity supplied, quantity bought, stock level, current sales, expected sales)
 * @return true if the new store has been successfully created
 */
    protected static boolean initializeStore(String[] ownerPersonalDetails, ArrayList<String> departments, String storeFile, String usersFile){
         
        // creates new file and the first sheet with the owner's details
        ExcelFileMethods.createNewSheet(storeFile, FIRST_SHEET_NAME);

        // set headers in the sheet
        ExcelFileMethods.setHeaders(storeFile, FIRST_SHEET_NAME, FIRST_SHEET_HEADERS, PERSONAL_DETAILS_HEADER_ROW_NUM, PERSONAL_DETAILS_START_COL_NUM);


        // check if the user has added departments
        if(!departments.isEmpty()){
            // there is at least one department: create departments
            for(int i = 0; i < departments.size(); i++){
                // create a sheet for each department
                ExcelFileMethods.createNewSheet(storeFile, departments.get(i));
                
                // set the headers for each department
                ExcelFileMethods.setHeaders(storeFile, departments.get(i), ownerPersonalDetails, PERSONAL_DETAILS_HEADER_ROW_NUM, PERSONAL_DETAILS_START_COL_NUM);

                // add the personal details
                ExcelFileMethods.setRow(storeFile, departments.get(i), ownerPersonalDetails, PERSONAL_DETAILS_ROW_NUM, PERSONAL_DETAILS_START_COL_NUM);
                    
            }
        }

        // Create a database for users
        ExcelFileMethods.createNewSheet(usersFile, USER_ACCOUNTS);

        // Set headers for the users file
        ExcelFileMethods.setHeaders(usersFile, USER_ACCOUNTS, USER_HEADERS, PERSONAL_DETAILS_HEADER_ROW_NUM, PERSONAL_DETAILS_START_COL_NUM);

        // return true if store has been created
        return true;     

    }


    public String getownerFullName(){

        return "Daisy";

    }



    private String getOwnerUsername(){

        return "daisy-py";
    }



    private String getOwnerPassword(){

        return "123DKT@home";
    }
    


    public String getStoreName(){

        return "DKT Holdings";
    }


    public String getStoreMotto(){

        return "You ask, We Deliver";
    }


    public String getHeadquatersAddress(){

        return "1 University Avenue, Berekuso, Accra, Ghana";
    }



   

    
}
