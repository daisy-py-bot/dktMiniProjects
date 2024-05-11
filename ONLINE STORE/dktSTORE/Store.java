package dktSTORE;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Seller Accounts
 */
public class Store {

  
    protected static final int USERNAME_COL_NUM = 0;
    protected static final int PASSWORD_COL_NUM = 1;
    private static final int PERSONAL_DETAILS_HEADER_ROW_NUM = 0;
    static private final int DEPARTMENT_HEADER_ROW_NUM = 0;
    static final int  DEPARTMENT_START_COL_NUM = 0;
    private static final int PERSONAL_DETAILS_ROW_NUM= 1;
    private static final int PERSONAL_DETAILS_START_COL_NUM= 0;

    // header names
    public static final String[] FIRST_SHEET_HEADERS = {"USERNAME", "PASSWORD", "OWNER'S NAME", "EMAIL", "STORE NAME", "ADDRESS", "OUR MOTTO", "DATE CREATED", "DATE LAST VISITED"};
    public static final String[] DEPARTMENT_HEADERS = {"ITEM NAME", "UNIT PRICE(GH₵)", "UNITS LEFT",  "QTY SOLD", "SUPPLY", "CUR. SALES", "EX. SALES" };
    public static final String[] USER_HEADERS = {"USERNAME", "PASSWORD", "FULL NAME", "EMAIL", "DATE CREATED", "DATE LAST VISITED"};

    // department header column numbers
    static final int ITEM_NAME_INDEX_NUM = 1;
    static final int ITEM_NAME_COL_NUM = 0;
    static final int PRICE_COL_NUM = 1;
    static final int UNITS_LEFT_COL_NUM = 2;
    static final int QTY_SOLD_COL_NUM = 3;
    static final int SUPPLY_COL_NUM= 4;
    static final int CUR_SALES_COL_NUM = 5;
    static final int EX_SALES_COL_NUM = 6;

    final int BUYER_MAX_COL_NUM = 4;

    private static final String USER_ACCOUNTS = "USER ACCOUNTS";
    public static final String FIRST_SHEET_NAME = "Store Details";

    // create a new excel file for the new store
    private static String storeFile ="dkt.xls";
    private static String usersFile;
    static String lineBreak = "------------------------------------------------------------------------------------";
   
    
/**
 * creates a new  online store
 * store has the details of the owner (full name of the owner, store name, address for the headerquaters)
 * store has the initial departments that the owner adds
 * each department has four headers (item name, unit price, quantity supplied, quantity bought, stock level, current sales, expected sales)
 * @return true if the new store has been successfully created
 */
    private static boolean initializeStore(String[] ownerPersonalDetails, ArrayList<String> departments, String storeFile, String usersFile){
         
        // creates new file and the first sheet with the owner's details
        System.out.println("creating the first sheet with the owner's details");
        ExcelFileMethods.createNewSheet(storeFile, FIRST_SHEET_NAME);

        // set headers in the sheet
        ExcelFileMethods.setHeaders(storeFile, FIRST_SHEET_NAME, FIRST_SHEET_HEADERS, PERSONAL_DETAILS_HEADER_ROW_NUM, PERSONAL_DETAILS_START_COL_NUM);

        // set the owner's details
        // get the last row number in the first sheet
        int rowNum = ExcelFileMethods.getLastRowNum(storeFile, FIRST_SHEET_NAME);

        // write the details of the store manager in the next row
        ExcelFileMethods.setRow(storeFile, FIRST_SHEET_NAME, ownerPersonalDetails, rowNum + 1, USERNAME_COL_NUM);
        

        // check if the user has added departments
        if(!departments.isEmpty()){
            // there is at least one department: create departments
            for(int i = 0; i < departments.size(); i++){
                // create a sheet for each department
                ExcelFileMethods.addSheet(storeFile, departments.get(i));
                
                // set the headers for each department
                ExcelFileMethods.setHeaders(storeFile, departments.get(i), DEPARTMENT_HEADERS, DEPARTMENT_HEADER_ROW_NUM,  DEPARTMENT_START_COL_NUM);

                
            }
        }

        // Create a database for users: create the first sheet in a new file
        ExcelFileMethods.createNewSheet(usersFile, USER_ACCOUNTS);

        // Set headers for the users file
        ExcelFileMethods.setHeaders(usersFile, USER_ACCOUNTS, USER_HEADERS, PERSONAL_DETAILS_HEADER_ROW_NUM, PERSONAL_DETAILS_START_COL_NUM);

        // return true if store has been created
        return true;     

    }

    public static boolean createNewStore(String fullname, String username, String password, String email, String storeName, String motto, String address, ArrayList<String> departments){
        // create name for store owner's file
        String storeFile = username + ".xls";
        String usersFile = "UsersDatabase.xls";
        // get date account and store created
        LocalDateTime dateAndTime = LocalDateTime.now();

        // set the format for the date and time
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateStoreCreated = dateAndTime.format(formattedDate);

        // create an array of the personal details of the shop owner
        String[] storeOwnerPersonalDetails = {username, password, fullname, email, storeName, motto, address, dateStoreCreated, dateStoreCreated};

        // created the store and initialize it
        if(initializeStore(storeOwnerPersonalDetails, departments, storeFile, usersFile)){
            // return true if the store has been created
            Store.storeFile = storeFile;
            Store.usersFile = usersFile;

            return true; 
        }

        // return false if the store is not created
        return false;
    }

    public static boolean loginStoreManagerAccount(String username, String givenPassword){
        // store file for the owner is saved with name username.xls
        // to login, the file should exist, or should be open
        String filename = username + ".xls";

            // verification stage 1: check the existence of the owner's file
            if(ExcelFileMethods.doesExcelFileExists(filename)){
                System.out.println("\nThe file exist...username is correct.");
                // verfication stage 2: check the correctness of the password
                // compare the given password with the password saved in the database
                if(ExcelFileMethods.hasCellValue(filename, FIRST_SHEET_NAME, givenPassword, PERSONAL_DETAILS_ROW_NUM, PASSWORD_COL_NUM)){
                    // log in successful, verification stage complete: set the pathfiles
                    System.out.println("Welcome back! Pick up from where you left from...");
                    storeFile = filename;
                    usersFile = username + "UsersDatabase.xls";
                    return true;
                }
            }
            // Invalid password or username: verification process not complete
            return false;
                   
    }

    static private boolean changePassword(String oldPassword, String newPassword){
        return true;
    }
    
    static private void forgetPassword(){

    }
    


    public static void addNewDepartment(String departmentName){
        // add a sheet for each department
        ExcelFileMethods.addSheet(storeFile, departmentName);
                
        // set the headers for each department
        ExcelFileMethods.setHeaders(storeFile, departmentName, DEPARTMENT_HEADERS, DEPARTMENT_HEADER_ROW_NUM, DEPARTMENT_START_COL_NUM);

        


    }

    public static void addNewItemsToDepartment(String departmentName, String itemName, double quantity, double unitPrice){
        //     public static final String[] DEPARTMENT_HEADERS = {"ITEM NAME", "UNIT PRICE", 
        //"UNITS LEFT",  "QTY SOLD", "SUPPLY", "CUR. SALES", "EX. SALES" };

        double expectedSales = (unitPrice * quantity);
        double currentSales = (quantity * unitPrice);
        double unitsLeft = quantity;
        double qtySold = 0;

        // get the last row num in the department
        int lastRowNum = ExcelFileMethods.getLastRowNum(storeFile, departmentName) + 1;

        // add item name to first column and last row + 1 (so as not to overwrite the last row)
        ExcelFileMethods.setValueInCell(storeFile, departmentName, lastRowNum, ITEM_NAME_COL_NUM, itemName);

        // add the details of the item to an array
        double[] itemAddition = {unitPrice, unitsLeft, qtySold, quantity, currentSales, expectedSales};

        // append the details to the file
        ExcelFileMethods.setRow(storeFile, departmentName, itemAddition, lastRowNum, PRICE_COL_NUM);

    }



    public static void displayDepartmentNames(){
        // get the names of all the departments the store has
        ArrayList<String> departmentLists =  ExcelFileMethods.getSheetNames(storeFile, 0);

        // display the names of the department
        for(int i = 1; i < departmentLists.size(); i++){
            System.out.println(i + ". " + departmentLists.get(i));
        }
    }

    public static void displayItemsInDepartment(String departmentName){
        // get the last the row
        int lastRowNum = ExcelFileMethods.getLastRowNum(storeFile, departmentName);

        // get the arraylist with the specified department
        ArrayList<ArrayList<Object>> allItemsInDepartment = ExcelFileMethods.getAllRowsInSheet(storeFile, departmentName, ITEM_NAME_INDEX_NUM, lastRowNum, PRICE_COL_NUM, EX_SALES_COL_NUM);

        // get list of item names
        ArrayList<Object> itemNames =  getListOfItemNamesInDepartment(departmentName);

        // display the name of the department
        System.out.printf("\n%45s Department", departmentName);

        // print a line break
        System.out.println("\n" + lineBreak);

        // display the headings in a department
        System.out.printf("%25s %15s %15s %15s %15s %15s %15s", DEPARTMENT_HEADERS[0], DEPARTMENT_HEADERS[1], DEPARTMENT_HEADERS[2], DEPARTMENT_HEADERS[3], DEPARTMENT_HEADERS[4], DEPARTMENT_HEADERS[5], DEPARTMENT_HEADERS[6]);

        // print a line break
        System.out.println("\n" + lineBreak);

        // iterate through the rows (items)
        for(int i = 0; i < allItemsInDepartment.size(); i++){
            // display the name of the item: type cast from object to string data type
            System.out.printf("%25s ", itemNames.get(i));

            // iterate through each column (details for each item)
            for(int k = 0; k < EX_SALES_COL_NUM; k++){
                // display the items for the row (price, quantity etc): type cast the values from object to double data type
                System.out.printf("%15.2f ", (double) allItemsInDepartment.get(i).get(k));
            }
            // print a new line
            System.out.println();

        }

    }

    public static void displayItemNamesInDepartment(String departmentName){
        // get the names of all the departments the store has
        // list of items only
        ArrayList<Object> itemsList =  getListOfItemNamesInDepartment(departmentName);

        // print the name of the department
        System.out.println("\n                     " + departmentName + " Department");
        // display the names of the department: start from index 1 to skip the headers
        for(int i = 0; i < itemsList.size(); i++){
            System.out.println((i + 1) + ". " + itemsList.get(i));
        }
    }

    public static void displayAllDepartmentsInStore(){
        // get all the names of the departments
        ArrayList<String> departmentNames = getAllDepartments();

        // loop through all items
        for(int i = 0; i < departmentNames.size(); i++){
            displayItemsInDepartment(departmentNames.get(i));
        }


    }

    public static void displayItemsInDepartmentToBuyer(String departmentName){
        int lastRowNum = ExcelFileMethods.getLastRowNum(storeFile, departmentName);

        // get the arraylist with the specified department
        ArrayList<ArrayList<Object>> allItemsInDepartment = ExcelFileMethods.getAllRowsInSheet(storeFile, departmentName, ITEM_NAME_INDEX_NUM, lastRowNum, PRICE_COL_NUM, UNITS_LEFT_COL_NUM);

        // get list of item names
        ArrayList<Object> itemNames =  getListOfItemNamesInDepartment(departmentName);

        // print name of the department
        System.out.println("                     " + departmentName + "  Department");

        // add a linebreak
        System.out.println(lineBreak + "\n");

        // display the headings
        System.out.printf("%25s %18s %18s ", DEPARTMENT_HEADERS[0], DEPARTMENT_HEADERS[1], DEPARTMENT_HEADERS[2]);

        // print a new line
        System.out.println();

        // iterate through the rows
        for(int i = 0; i < allItemsInDepartment.size(); i++){
            // display the name of the item: type cast from object to string data type
            System.out.printf("%25s %18.2f %18.0f", itemNames.get(i), allItemsInDepartment.get(i).get(PRICE_COL_NUM - 1), allItemsInDepartment.get(i).get(UNITS_LEFT_COL_NUM - 1));

            // // iterate through each column
            // for(int k = 0; k <= UNITS_LEFT_COL_NUM; k++){
            //     // display the items for the row (price, quantity etc): type cast the values from object to double data type
            //     System.out.printf("%15.2f ", (double) allItemsInDepartment.get(i).get(k));
            // }
            // print new line
            System.out.println();

        }

    }

    /**
     * creates an arraylist of the departments in the store (in the file, departments start from the sheet index number 1)
     * @return an arraylist of the departments in the store
     */
    public static ArrayList<String> getAllDepartments(){
        ArrayList<String> departmentLists =  ExcelFileMethods.getSheetNames(storeFile, 1);
        return departmentLists;
    }

    public static ArrayList<Object> getListOfItemNamesInDepartment(String department){
        // get the number of items
        int numOfItems = ExcelFileMethods.getLastRowNum(storeFile, department);
        System.out.println(numOfItems);
        ArrayList<Object> itemNameList = ExcelFileMethods.getItemsInColumn(storeFile, department, ITEM_NAME_COL_NUM, ITEM_NAME_INDEX_NUM, numOfItems);
        return itemNameList;
    }
    
    public static void restockItem(String department, String itemName, double addedQuantity){

    }

    public static boolean sellItems(String departmentName, String nameOfItem, double numOfItemsBought){
        // 1. reduce quantity (units left) of the item 2. increase sales 3. increase quantity bought
        reduceUnitsLeft(departmentName, nameOfItem, numOfItemsBought);
        incrementSales(departmentName, nameOfItem, numOfItemsBought);
        incrementQuantitySold(departmentName, nameOfItem, numOfItemsBought);
        return true;
    }

    static protected void changeUnitPrice(String departmentName, String nameOfItem, double newPrice){
        // set the new price
        ExcelFileMethods.setValueInCell(storeFile, departmentName, nameOfItem, PRICE_COL_NUM, newPrice);

    }



    static public void reduceUnitsLeft(String departmentName, String nameOfItem, double numItemsSold){
        // get the current quantity
        double currentUnitsLeft = getCurrentUnitsLeft(departmentName, nameOfItem);

        // **** ASSUMES THE UNITS LEFT IS GREATER THAN OR EQUAL TO QUANTITY BEING SOLD ***
        // Need to ensure buyer doesnt order more than what is left
        double newUnitsLeft = currentUnitsLeft - numItemsSold;

        // get the row number of the item in the item column
        int rowNum = ExcelFileMethods.getRowIndexOfCell(storeFile, departmentName, nameOfItem, ITEM_NAME_COL_NUM);

        // set the new value of the units left
        ExcelFileMethods.setValueInCell(storeFile, departmentName, rowNum, UNITS_LEFT_COL_NUM, newUnitsLeft);
    }

    static public void incrementQuantitySold(String departmentName, String nameOfItem, double numItemsSold){
        // get the current quantity that has been sold
        double currentQuantitySold = getCurrentQuantityBought(departmentName, nameOfItem);

        // calculate the new quanity that has been sold
        double newQuantitySold = currentQuantitySold + numItemsSold;

        // get the row number of the item in the item column
        int rowNum = ExcelFileMethods.getRowIndexOfCell(storeFile, departmentName, nameOfItem, ITEM_NAME_COL_NUM);

        // set the new value of the quantity that has been sold
        ExcelFileMethods.setValueInCell(storeFile, departmentName, rowNum, QTY_SOLD_COL_NUM, newQuantitySold);
    }

    static public void incrementSales(String departmentName, String nameOfItem, double numItemsSold){
        // get the current sales
        double currentSales = getCurrentSales(departmentName, nameOfItem);

        // get the value of the new sales 
        double newSales = currentSales + (numItemsSold * getCurrentPrice(departmentName, nameOfItem));

        // get the row number of the item in the item column
        int rowNum = ExcelFileMethods.getRowIndexOfCell(storeFile, departmentName, nameOfItem, ITEM_NAME_COL_NUM);

        // set the new value of the sale
        ExcelFileMethods.setValueInCell(storeFile, departmentName, rowNum, CUR_SALES_COL_NUM, newSales);
    }


    

    static public double getCurrentUnitsLeft(String departmentName, String nameOfItem){
        double unitsLeft =  ExcelFileMethods.getCellNumericValue(storeFile, departmentName, nameOfItem, ITEM_NAME_COL_NUM, UNITS_LEFT_COL_NUM);
        return unitsLeft;
    }

    static public double getCurrentSales(String departmentName, String nameOfItem){
        return ExcelFileMethods.getCellNumericValue(storeFile, departmentName, nameOfItem, ITEM_NAME_COL_NUM, CUR_SALES_COL_NUM);
    }

    static public double getCurrentQuantityBought(String departmentName, String nameOfItem){
        return ExcelFileMethods.getCellNumericValue(storeFile, departmentName, nameOfItem, ITEM_NAME_COL_NUM, QTY_SOLD_COL_NUM);
    }

    static public double getCurrentPrice(String departmentName, String nameOfItem){
        // get the row index value of the items
        int rowNum = ExcelFileMethods.getRowIndexOfCell(storeFile, departmentName, nameOfItem, ITEM_NAME_COL_NUM);

        // get the price in the column and row number
        double price = ExcelFileMethods.getCellNumericValue(storeFile, departmentName, rowNum, PRICE_COL_NUM);
        //double price =  ExcelFileMethods.getCellNumericValue(storeFile, departmentName, nameOfItem, PRICE_COL_NUM);
        return price;
    }



    
}
