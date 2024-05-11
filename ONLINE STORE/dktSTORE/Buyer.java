package dktSTORE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Buyer {
    public static final String userFile = "UsersDatabase.xls";
    private static final String FIRST_SHEET = "USER ACCOUNTS";
    public static final String[] USERS_ACCOUNT_HEADERS = {"USERNAME", "PASSWORD", "FULL NAME", "EMAIL", "DATE CREATED", "DATE LAST VISITED"};
    public static final String[] CART_HEADERS = {"DEPARTMENT", "ITEM", "UNIT PRICE", "QUANTITY", "TOTAL COST"};
    protected static final int USERNAME_COL_NUM = 0;
    protected static final int PASSWORD_COL_NUM = 1;

    public static final int DEPARTMENT_COL_NUM = 0;
    public static final int ITEM_COL_NUM = 1;
    public static final int PRICE_COL_NUM = 2;
    public static final int QUANTITY_COL_NUM = 3;
    public static final int TOTAL_COST_COL_NUM = 4;

    public static final int ITEM_START_ROW_NUM = 1;
    public static final int ACC_START_ROW_NUM = 1;
    static String lineBreak = "------------------------------------------------------------------------------------";


    static public boolean createAccount(String fullname, String username, String password, String email){
        // create a sheet with the name set to the ussername of buyer: username should be unique
        // create account assuming details are verified and validated
        // set date when account is created
        LocalDateTime dateAndTime = LocalDateTime.now();

        // set the format for the date and time
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateStoreCreated = dateAndTime.format(formattedDate);

        // create an array with user details
        String[] accDetails = {username, password, fullname, email, dateStoreCreated, dateStoreCreated};

        // get the last row num
        int lastRowNum = ExcelFileMethods.getLastRowNum(userFile, FIRST_SHEET);

        // add the acount
        ExcelFileMethods.setRow(userFile, FIRST_SHEET, accDetails, lastRowNum + 1, USERNAME_COL_NUM);

        // create a new account for the buyer
        ExcelFileMethods.addSheet(userFile, username);

        // set the headers
        ExcelFileMethods.setHeaders(userFile, username, CART_HEADERS, 0, USERNAME_COL_NUM);
        return true;
    }
    
    static public boolean usernameExist(String givenUsername){
        System.out.println("Does username exist");
        // get the number of rows
        int numRows = ExcelFileMethods.getLastRowNum(userFile, FIRST_SHEET);
        // returns true if the username exists
        return ExcelFileMethods.rowHasCellValue(userFile, FIRST_SHEET, givenUsername, ACC_START_ROW_NUM, numRows, USERNAME_COL_NUM);
    }

    static public boolean loginAccount(String givenUsername, String givenPassword){
        // verify if the account exists
        if(usernameExist(givenUsername)){
            System.out.println("Username exists");
            // get the row num for username
            int rowNum = ExcelFileMethods.getRowIndexOfCell(userFile, FIRST_SHEET, givenUsername, USERNAME_COL_NUM);

            System.out.println("row number with the username: " + rowNum);
            // check if the password is correct
            if(ExcelFileMethods.hasCellValue(userFile, FIRST_SHEET, givenPassword, rowNum, PASSWORD_COL_NUM)){
                System.out.println("Password exist");
                // correct username and password: login successful
                return true;
            }
        }
        // username or password incorrect
        return false;
    }



    static public void displayDepartments(){
        // display the names of the different departments available
        Store.displayDepartmentNames();

    }

    static public void viewDepartmentInStore(String departmentName){
        // display itens, price, units left to the buyer
        Store.displayItemsInDepartmentToBuyer(departmentName);
        
    }

    static public ArrayList<String> getDepartmentList(){
        return Store.getAllDepartments();
    }

    public static ArrayList<Object> getListOfItemNamesInDepartment(String department){
        ArrayList<Object> itemsList = Store.getListOfItemNamesInDepartment(department);
        return itemsList;
    }
    
    public static void displayItemNamesInDepartment(String departmentName){
        System.out.println("display items: Buyer class");
        Store.displayItemNamesInDepartment(departmentName);
    }

    static public boolean addNewItemToCart(String username, String departmentOfItem, String nameOfItem, double numOfItems){
        // get the last row number
        System.out.println("details to add to cart: " +  username + "  " + departmentOfItem + "  " + nameOfItem + "  " + numOfItems);
        
        int rowNum = ExcelFileMethods.getLastRowNum(userFile, username) + 1;
        System.out.println("row number for adding items to cart: " + rowNum);

        System.out.println(" Getting the Price of " + nameOfItem);
        double price = getCurrentPrice(departmentOfItem, nameOfItem);
        System.out.println("Price: " + price);

        // calculate total cost
        double totalCost = price * numOfItems;

        // add name of the department with the item
        ExcelFileMethods.setValueInCell(userFile, username, rowNum, DEPARTMENT_COL_NUM, departmentOfItem);

        // add the name of the item
        ExcelFileMethods.setValueInCell(userFile, username, rowNum, ITEM_COL_NUM, nameOfItem);

        // add the price of the item
        ExcelFileMethods.setValueInCell(userFile, username, rowNum, PRICE_COL_NUM, price);

        // add the quantity of the item
        ExcelFileMethods.setValueInCell(userFile, username, rowNum, QUANTITY_COL_NUM, numOfItems);

        // add the total cost for that item
        ExcelFileMethods.setValueInCell(userFile, username, rowNum, TOTAL_COST_COL_NUM, totalCost);

        return true;
    }

    public static ArrayList<Object> getItemNamesInCart(String department){
        // get the number of items
        int numOfItems = ExcelFileMethods.getLastRowNum(userFile, department);
        System.out.println(numOfItems);
        ArrayList<Object> itemNameList = ExcelFileMethods.getItemsInColumn(userFile, department, ITEM_COL_NUM, ITEM_START_ROW_NUM, numOfItems);
        return itemNameList;
    }

    public static ArrayList<Object> getDepartmentNamesInCart(String department){
        // get the number of items
        int numOfItems = ExcelFileMethods.getLastRowNum(userFile, department);
        System.out.println(numOfItems);
        ArrayList<Object> itemNameList = ExcelFileMethods.getItemsInColumn(userFile, department, DEPARTMENT_COL_NUM, ITEM_START_ROW_NUM, numOfItems);
        return itemNameList;
    }

    static public void viewCart(String username){
        // get the num of items in the cart
        int numItems = ExcelFileMethods.getLastRowNum(userFile, username);
        // get all items in buyer's cart
        ArrayList<ArrayList<Object>> itemsInCart = ExcelFileMethods.getAllRowsInSheet(userFile, username, ITEM_START_ROW_NUM, numItems, PRICE_COL_NUM, TOTAL_COST_COL_NUM);

        // get list of departments in the cart
        ArrayList<Object> departmentList =  getDepartmentNamesInCart(username);

        ArrayList<Object> itemList =  getItemNamesInCart(username);




        // display the cart
        System.out.println(lineBreak);
        System.out.println("\n                            My Cart");
        System.out.println(lineBreak);

        // display the headings
        System.out.printf("%15s %25s %12s %12s %12s ", CART_HEADERS[DEPARTMENT_COL_NUM],CART_HEADERS[ITEM_COL_NUM], CART_HEADERS[PRICE_COL_NUM], CART_HEADERS[QUANTITY_COL_NUM], CART_HEADERS[TOTAL_COST_COL_NUM]);

        // print a linebreak
        System.out.println("\n" + lineBreak);

        // iterate through the rows
        for(int i = 0; i < itemsInCart.size(); i++){
            // display the items
            System.out.printf("%15s %25s %12.2f %12.0f %12.2f ", departmentList.get(i), itemList.get(i), (double) itemsInCart.get(i).get(0), itemsInCart.get(i).get(1), itemsInCart.get(i).get(2));

            // print a new line
            System.out.println();
        }
        System.out.println();

    }

    static public boolean removeItemFromCart(String itemName){
        return true;
    }

    public boolean changeQuantityOfItemInCart(String username, String itemName, double newQuantity){
        // get the row index of the item
        int rowNum = ExcelFileMethods.getRowIndexOfCell(userFile, username, itemName, ITEM_COL_NUM);
        // set the new quantity
        ExcelFileMethods.setValueInCell(userFile, username, rowNum, QUANTITY_COL_NUM, newQuantity);
        return true;
    }

    public ArrayList<ArrayList<Object>> getAllItemsInCart(String username){
        // get number of items in cart
        int numOfItems = ExcelFileMethods.getLastRowNum(userFile, username);

        // get list of items in cart
        ArrayList<ArrayList<Object>> itemsInCart = ExcelFileMethods.getAllRowsInSheet(userFile, username, ITEM_START_ROW_NUM, numOfItems, DEPARTMENT_COL_NUM, TOTAL_COST_COL_NUM);
        return itemsInCart;
    }
    
    public static String checkout(String username){
        // get number of items in cart
        int numOfItems = ExcelFileMethods.getLastRowNum(userFile, username);
        
        String receipt = String.format("%12s %25s %12s %12s %12s \n", CART_HEADERS[DEPARTMENT_COL_NUM], CART_HEADERS[ITEM_COL_NUM], CART_HEADERS[PRICE_COL_NUM], CART_HEADERS[QUANTITY_COL_NUM], CART_HEADERS[TOTAL_COST_COL_NUM]);
        double totalExpenditure = 0;

        System.out.println(lineBreak);
        // loop through each item in the cart
        for(int i = ITEM_START_ROW_NUM; i <= numOfItems; i++){
            // get the department name
            String departmentOfItem = ExcelFileMethods.getCellStringValue(userFile, username, i, DEPARTMENT_COL_NUM);

            
            String itemName = ExcelFileMethods.getCellStringValue(userFile, username, i, ITEM_COL_NUM);

            double quantity = ExcelFileMethods.getCellNumericValue(userFile, username, i, QUANTITY_COL_NUM);

            double price = getCurrentPrice(departmentOfItem, itemName);

            double totalCost = price * quantity;

            // increase total expenditure
            totalExpenditure += totalCost;

            
            // buy item
            Store.sellItems(departmentOfItem, itemName, numOfItems);

            // generate receipt
            String itemBought = String.format("%12s %25s %12.2f %12.0f %12.2f \n", departmentOfItem, itemName, price , quantity, totalCost);
            receipt += itemBought;
        }
        // add the total cost to the receipt
        receipt += String.format("%10s GH₵ %10.2f", CART_HEADERS[TOTAL_COST_COL_NUM], totalExpenditure);
        return receipt;

    }

    public static double getCurrentUnitsLeft(String departmentName, String nameOfItem){
        return Store.getCurrentUnitsLeft(departmentName, nameOfItem);
    }

    public static double getCurrentPrice(String departmentName, String nameOfItem){
        return Store.getCurrentPrice(departmentName, nameOfItem);    }


    
}
