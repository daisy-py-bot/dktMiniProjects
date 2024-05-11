package dktSTORE;

import java.io.File;
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

public class ExcelFileMethods {


    public static boolean doesExcelFileExists(String fileName){
        // create a file object
        File excelFile = new File(fileName);

        if(excelFile.exists()){
            // file exists, the store manager has an account
            // stage 1 verification process
            return true;
        }else{
            // there is no account with that username
            return false;
        }
    }

    /**
     * checks if a given string value exist in a cell given the row and column number
     * @param fileName
     * @param sheetName
     * @param givenCellValue
     * @param rowNum
     * @param columnIndex
     * @return
     */
    public static boolean hasCellValue(String fileName, String sheetName, String givenCellValue, int rowNum, int columnIndex){
        // checks if the given password already exists in the database
        // open the excel file
        boolean cellValueExist = false;
        try{
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream(fileName);
            wb = new HSSFWorkbook(fis);
    
            // retrieve the excel sheet
            HSSFSheet sheet = wb.getSheet(sheetName);

            // get the row with the given index
            Row r = sheet.getRow(rowNum);

            // get the cell in the given row and column number
            Cell c = r.getCell(columnIndex);

            // compare the cell string value to the given string value
            String stringValueInCell = c.getStringCellValue();

            fis.close();
            wb.close();

            // returns true if the two strings are the same
            cellValueExist = (stringValueInCell.equals(givenCellValue));
            
        }
        catch(IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
        }
        
       return cellValueExist; 
    
    }
    public static boolean rowHasCellValue(String fileName, String sheetName, String givenCellValue, int startingRowNum, int endingRowNum, int columnIndex){
        // iterate through all the rows
        for(int i = startingRowNum; i <= endingRowNum; i++){
            if (hasCellValue(fileName, sheetName, givenCellValue, i, columnIndex)){
                // the string value exist in that row
                return true;
            }
        }

        return false;
    }
 

    /**
     * creates a new excel sheet in a new file: file does not exist
     * @param fileName takes in the name of the excel file
     * @param sheetName takes in the name of the sheet to be newly created
     * @return void
     */
    public static void createNewSheet(String fileName, String sheetName){
        try{
            // open a file output stream: creates the file if it does not exist
            FileOutputStream fos = new FileOutputStream(fileName);

            // create a new workbook
            HSSFWorkbook wb = new HSSFWorkbook();

            // create a new sheet  and set it to the given name
            wb.createSheet(sheetName);


            // write to the file
            wb.write(fos);

            // close the streams
            fos.close();
            wb.close();

        }
        catch (IOException e){
            System.out.println("Error opening file: " + e.getMessage());
        }

    }

    public static void addSheet(String filename, String sheetName){
        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(filename);

            // open the workbook to retrieve the information
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // create new sheet
            HSSFSheet sheet = wb.createSheet(sheetName);

            // open an output stream
            FileOutputStream fos = new FileOutputStream(filename);

            // write to the file
            wb.write(fos);

            // close the input stream and the output stream
            fis.close();
            fos.close();
            wb.close();
            
        }
        catch (IOException e){
            System.out.println("Error opening file: " + e.getMessage());
        }

    }



    /**
     * sets the headers in an existing sheet of an existing file
     * @param filename
     * @param sheetName
     * @param headers
     * @param headerRowNum
     * @param startingColumnIndex
     */
    public static void setHeaders(String filename, String sheetName, String[] headers, int headerRowNum, int startingColumnIndex){

        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(filename);

            // create a workbook objects
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // retrieve existing sheet
            HSSFSheet sheet = wb.getSheet(sheetName);

            // create a new header row
            Row headerRow = sheet.createRow(headerRowNum);

            // set the header
            // set the headers for the given row
            for (int i = startingColumnIndex; i < headers.length; i++){
                // create a new cell for each column in the row
                Cell cell = headerRow.createCell(i);
                
                // set the value of the cell to the header string
                cell.setCellValue(headers[i]);
            
        }
        // close the input stream
        fis.close();

        // open a file output stream
        FileOutputStream fos = new FileOutputStream(filename);

        // write to the file
        wb.write(fos);

        // close the output streams
        fos.close();
        
        // close the workbook
        wb.close();

        }

        catch(IOException e){
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
  
    public static void setRow(String filename, String sheetName, String[] rowDetails, int rowNum, int startingColumnIndex){

        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(filename);

            // create a workbook objects
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // retrieve existing sheet
            HSSFSheet sheet = wb.getSheet(sheetName);

            // get the row at index rowNum
            Row r = sheet.getRow(rowNum);

            // check if the row exist
            if (r == null){
                // row does not exist: create a new row
                r = sheet.createRow(rowNum);
            }

            // set the header
            // set the headers for the given row
            for (int i = 0; i < rowDetails.length; i++){
                // create a new cell for each column in the row
                Cell cell = r.createCell(i + startingColumnIndex);
                
                // set the value of the cell to the header string
                cell.setCellValue(rowDetails[i]);
            
        }
        // open a file output stream
        FileOutputStream fos = new FileOutputStream(filename);

        // write to the file
        wb.write(fos);

        // close all file streams
        fos.close();
        fis.close();
        wb.close();

        }

        catch(IOException e){
            System.out.println("Error opening file: " + e.getMessage());
        }
    }

/**
 * 
 * @param filename
 * @param sheetName
 * @param rowDetails
 * @param rowNum
 * @param startingColumnIndex
 */
    public static void setRow(String filename, String sheetName, double[] rowDetails, int rowNum, int startingColumnIndex){

        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(filename);

            // create a workbook objects
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // retrieve existing sheet
            HSSFSheet sheet = wb.getSheet(sheetName);

            // get the row at index rowNum
            Row r = sheet.getRow(rowNum);

            // check if the row exist
            if (r == null){
                // row does not exist: create a new row
                r = sheet.createRow(rowNum);
            }

            for (int i = 0; i < rowDetails.length; i++){
                // create a new cell for each column in the row
                Cell cell = r.createCell(i + startingColumnIndex);
                
                // set the value of the cell to the header string
                cell.setCellValue(rowDetails[i]);
            
        }
        // open a file output stream
        FileOutputStream fos = new FileOutputStream(filename);

        // write to the file
        wb.write(fos);

        // close all file streams
        fos.close();
        fis.close();
        wb.close();

        }

        catch(IOException e){
            System.out.println("Error opening file: " + e.getMessage());
        }
    }


    // get the index of a row given a string value in the cell
    /**
     * gets the row index number of the for a given string value
     * @param filename
     * @param sheetName
     * @param cellStringValue
     * @param cellValueColNum
     * @return
     */
    public static int getRowIndexOfCell(String filename, String sheetName, String cellStringValue, int cellValueColNum){
        // checks if the given password already exists in the database
        // open the excel file
        int indexValue = -1;

        try{
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream(filename);
            wb = new HSSFWorkbook(fis);
    
            // retrieve the excel sheet
            HSSFSheet sheet = wb.getSheet(sheetName);
    
            // get the number of rows in the excel sheet
            int numRows = sheet.getLastRowNum();
    
            // iterate through the rows using indexing
            for(int rowNum = 1; rowNum <= numRows; rowNum++){
                // get the row
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(cellValueColNum);
                String cellValue = cell.getStringCellValue();

                if (cellValue.equals(cellStringValue)){
                    // if the cell values match, set the indexValue to the rowNum
                    indexValue = rowNum;

                    // break out of the loop
                    break;
            
                }
            }
            fis.close();
            wb.close();
            return indexValue;
            
        }
        catch(IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
        }
        // return -1 if the cell value does not exist
       return -1; 
    
    }


    // get value given the index of the row
    /**
     * get the cell string value given the column and row numbers
     * @param fileName
     * @param sheetName
     * @param rowNum
     * @param colNum
     * @return string value in a cell
     */
    public static String getCellStringValue(String fileName, String sheetName, int rowNum, int colNum){
        String cellValue = "";
    
        try{
            
            FileInputStream fis = new FileInputStream(fileName);
            HSSFWorkbook wb = new HSSFWorkbook(fis);
    
            // retrieve the excel sheet
            HSSFSheet sheet = wb.getSheet(sheetName);
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


    /**
     * gets the double value in a cell given a row and column number
     * @param fileName
     * @param sheetName
     * @param rowNum
     * @param colNum
     * @return double value in a cell
     */
    public static double getCellNumericValue(String fileName, String sheetName, int rowNum, int colNum){
        double cellValue = 0;
    
        try{
            
            FileInputStream fis = new FileInputStream(fileName);
            HSSFWorkbook wb = new HSSFWorkbook(fis);
    
            // retrieve the excel sheet
            HSSFSheet sheet = wb.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            
                // check if the row is not null
                if(row != null){
                    // get the cell value
                    Cell cell = row.getCell(colNum);
    
                    // check if the cell value is null
                    if(cell != null){
                        // get the cell value
                        cellValue = cell.getNumericCellValue();
    
                    }}
                    wb.close();
    
        }
        catch(IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
        }
    
        return cellValue;

    }

    // get value given the name of an item in a certain row
    /**
     * gets the double value of a cell given the string that ius in the same row
     * @param fileName
     * @param sheetName
     * @param cellString
     * @param colNum
     * @return
     */
    public static double getCellNumericValue(String fileName, String sheetName, String cellString, int colNum){
        // get the index number of the row the cell string is in
        int rowINdex = getRowIndexOfCell(fileName, sheetName, cellString, colNum);

        // get the double value in a cell
        return getCellNumericValue(fileName, sheetName, rowINdex, colNum);


    }

    public static double getCellNumericValue(String fileName, String sheetName, String cellString, int colOfString, int colNumOfCell){
        // get the index number of the row the cell string is in
        int rowINdex = getRowIndexOfCell(fileName, sheetName, cellString, colOfString);

        // get the double value in a cell
        return getCellNumericValue(fileName, sheetName, rowINdex, colNumOfCell);


    }
    // get all the sheet names
    public static ArrayList<String> getSheetNames(String fileName, int startingIndex){
        // create list of departments
        ArrayList<String> departsmentsList = new ArrayList<>();

        // open the workbook
        try{
            // open a file input stream 
            FileInputStream fis = new FileInputStream(fileName);

            // create a new workbook
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // get the number of sheets (excluding the first sheet with store owner information)
            int numSheets = wb.getNumberOfSheets();

            // get the names of the sheets (departments)
            for(int i = startingIndex; i < numSheets; i++){
                // get the name of the department at index i
                departsmentsList.add(wb.getSheetName(i));
            }

            // close the workbook
            wb.close();
        }

        catch(IOException e){
            System.out.println("Error opening file: " + e.getMessage());
        }

        return departsmentsList;

    
    }


    // get the items in a row and return them in an array list
    public static  ArrayList<Object> getItemsInRow(String fileName, String sheetName, int rowIndexValue, int startingColumnNum, int endingColNum){
        // create a 2D array list of mixed data type
        ArrayList<Object> itemsInRow = new ArrayList<>();

        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(fileName);

            // create a new workbook
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // retrieve the department
            HSSFSheet sheet = wb.getSheet(sheetName);
            

            // get the row with item
            Row r = sheet.getRow(rowIndexValue);

            // loop through the cells in each row
            for(int colNum = startingColumnNum; colNum <= endingColNum; colNum++){
                // get the value at each cell 
                Cell cellValue = r.getCell(colNum);
                double valueInCell = cellValue.getNumericCellValue();

                // add value in cell to the list
                //departmentItemsList.get(rowNum).add(valueInCell);
                itemsInRow.add(valueInCell);

            }
            
            // close all streams
            fis.close();
            wb.close();

        }
        catch(IOException e){
            System.out.println("Error opening the file: " + e.getMessage());
        }

        // return the list with all details for the department
        return itemsInRow;


    }


    public static  ArrayList<Object> getItemsInColumn(String fileName, String sheetName, int colIndexValue, int startingRowNum, int endingRowNum){
        // create a 2D array list of mixed data type
        ArrayList<Object> itemsInColumn= new ArrayList<>();

        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(fileName);

            // create a new workbook
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // retrieve the department
            HSSFSheet sheet = wb.getSheet(sheetName);

            // loop through the cells in each row
            for(int rowNum = startingRowNum; rowNum <= endingRowNum; rowNum++){
                // get the row with item
                Row r = sheet.getRow(rowNum);

                // get the value at each cell 
                Cell cellValue = r.getCell(colIndexValue);
                String valueInCell = cellValue.getStringCellValue();

                // add value in cell to the list
                //departmentItemsList.get(rowNum).add(valueInCell);
                itemsInColumn.add(valueInCell);

            }
            
            // close all streams
            fis.close();
            wb.close();

        }
        catch(IOException e){
            System.out.println("Error opening the file: " + e.getMessage());
        }

        // return the list with all details for the department
        return itemsInColumn;


    }

  
    public static ArrayList<ArrayList<Object>> getAllRowsInSheet(String fileName, String sheetName, int startingRowNum, int endingRowNum, int startingColumnNum, int endingColNum){
        ArrayList<ArrayList<Object>> itemsInSheet = new ArrayList<>();

        for (int i = startingRowNum; i <= endingRowNum; i++){
            // append the rows
            itemsInSheet.add(getItemsInRow(fileName, sheetName, i, startingColumnNum, endingColNum));
        }

        return itemsInSheet;
    }

    public static void setValueInCell(String fileName, String sheetName, int rowIndex, int colNumOfCell, double newValueOfCell){
        // access the file and read from it
        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(fileName);

            // create a new workbook
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // retrieve sheet
            HSSFSheet sheet = wb.getSheet(sheetName);

            // get the row with the name of the item
            Row r = sheet.getRow(rowIndex);

            // check if the row exist
            if (r == null){
                // row does not exist: create a new row
                r = sheet.createRow(rowIndex);
            }

            // get the cell and reset its value
            Cell givenCell = r.createCell(colNumOfCell);
            givenCell.setCellValue(newValueOfCell);


            // open a file output stream 
            FileOutputStream fos = new FileOutputStream(fileName);

            // write to the file
            wb.write(fos);

            fos.close();
            fis.close();
            wb.close();

   
        }
        catch(IOException e){

            System.out.println("Error opening the file: " + e.getMessage());
        }

    }

    // sets a double value in a cell given the cell value of an item
    /**
     * sets a new double value in a cell that in the same row as the string value given
     * @param fileName
     * @param sheetName
     * @param cellStringValue
     * @param colNumOfCell
     * @param newValueOfCell
     */
    public static void setValueInCell(String fileName, String sheetName, String cellStringValue, int colNumOfCell, double newValueOfCell){
        // get the index row num of cell string value
        int rowNum = getRowIndexOfCell(fileName, sheetName,cellStringValue, colNumOfCell);

        // set the value of the cell in the given row and column
        setValueInCell(fileName, sheetName, rowNum, colNumOfCell, newValueOfCell);

    }

    // sets a string value in a cell given the cell value of an item
    public static void setValueInCell(String fileName, String sheetName, String cellStringValue, int colNumOfCell, String newValueOfCell){
        // get the index row num of cell string value
        int rowNum = getRowIndexOfCell(fileName, sheetName,cellStringValue, colNumOfCell);

        // set the value of the cell in the given row and column
        setValueInCell(fileName, sheetName, rowNum, colNumOfCell, newValueOfCell);

    }
    
    public static void setValueInCell(String fileName, String sheetName, int rowIndex, int colNumOfCell, String newValueOfCell){
        // access the file and read from it
        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(fileName);

            // create a new workbook
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // retrieve sheet
            HSSFSheet sheet = wb.getSheet(sheetName);
            
            // get the row with the name of the item
            Row r = sheet.getRow(rowIndex);

            // check if row exist
            if(r == null){
                // row does not exist: create a new row
                r = sheet.createRow(rowIndex);
            }
            

            // get the cell and reset its value
            Cell givenCell = r.createCell(colNumOfCell);
            givenCell.setCellValue(newValueOfCell);


            // open a file output stream 
            FileOutputStream fos = new FileOutputStream(fileName);

            // write to the file
            wb.write(fos);

            fos.close();
            fis.close();
            wb.close();

   
        }
        catch(IOException e){

            System.out.println("Error opening the file: " + e.getMessage());
        }

    }


    

    public static int getLastRowNum(String fileName, String sheetName){
        int lastRowNum = 1;

        try{
            // open a file input stream
            FileInputStream fis = new FileInputStream(fileName);

            // create a new workbook
            HSSFWorkbook wb = new HSSFWorkbook(fis);

            // retrieve the department
            HSSFSheet sheet = wb.getSheet(sheetName);
            
            // if(sheet != null){
            //     // get the last row index
            //     lastRowNum = sheet.getLastRowNum();
            // }
            // else{
            //     System.out.println(sheetName + "does not exist");
            // }
            // get the last row index
            lastRowNum = sheet.getLastRowNum();
 
            // close all streams
            fis.close();
            wb.close();

        }

        catch(IOException e){
            System.out.println("Error opening the file: " + e.getMessage());
        }

        // return the list with all details for the department
        return lastRowNum;


    }



}
