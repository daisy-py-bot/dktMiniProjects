package project;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadWrite {
    public static void main (String[] args){

        // create a workbook instance

        Workbook wb1 = new HSSFWorkbook();

        // create a worksheet
        Sheet sheet1 = wb1.createSheet();

        // write data to the sheet
        Row row1 = sheet1.createRow(0); // create the first row
        Cell cell1 = row1.createCell(0); // create the first cell in the first row
        cell1.setCellValue("hostel owner");

        // save the workbook to a file
        String filePath = "hostel1.xls";

        try(FileOutputStream fos = new FileOutputStream(filePath)){
            wb1.write(fos);
            System.out.println("Excel file created successfully at: " + filePath);
            wb1.close();
            
        }
        catch(IOException e){
            System.out.println("Error creating Excel file: " + e.getMessage());
            e.printStackTrace();
        }


        
        

    }


}