package project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AdminX {
    private String fullname;
    private String password;
    private String hostel_name;
    private static final String PATHFILE = "www.xls";



    public AdminX(String fullname, String password, String hostel_name){
        this.fullname = fullname;
        this.password = password;
        this.hostel_name = hostel_name;
    }
    public static void main(String[] args) {

        AdminX ad = new AdminX("Denis", "Denis123456", "Dufie");

        ad.createAdmin();
    }

    public boolean createAdmin()  {
      
        try{
            HSSFSheet sheet = null;

            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream(PATHFILE);
            wb = new HSSFWorkbook(fis);
            fis.close();

            // check if the file exists
            HSSFSheet sheetExist = wb.getSheet(hostel_name);
            if(sheetExist == null){
                // sheet does not exist
                // create a new sheet
                sheet = wb.createSheet(hostel_name);
                System.out.println("Sheet created");
            }
            else{
                System.out.println("sheet not created");
            }


            // find the last row in the excel sheet
            int lastrow = sheet.getLastRowNum();

            // create a new row for appending data for the user
            Row newRow = sheet.createRow(lastrow + 1);

            // add the adminName
            Cell cell0 = newRow.createCell(0);
            cell0.setCellValue(this.fullname);

            // add the password
            Cell cell1 = newRow.createCell(1);
            cell1.setCellValue(this.password);

            // add the hostel_name
            Cell cell2 = newRow.createCell(2);
            cell2.setCellValue(this.hostel_name);

            // // then create a worksheet for that hostel, to add room etc
            // wb.createSheet(this.hostel_name);

            // open an output stream
            FileOutputStream output = new FileOutputStream(PATHFILE);

            // write to the file
            wb.write(output);
            output.close();
            
            wb.close();
        
            
            

            return true;

        }catch (IOException e){
            System.out.println("Error2222 opening excel file: " + e.getMessage());
            return false;
        }

    }
/* 
    public boolean checkHostelExits(String hostel_name){
        boolean result = false;
        try{
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream("database.xls");
            wb = new HSSFWorkbook(fis);

            if(wb.getNumberOfSheets() != 0){
                for(int i = 0; i < wb.getNumberOfSheets(); i ++){
                    if(wb.getSheetName(i).equals(hostel_name)){
                        // which mean hostel already exist
                        result = true;
                        break;
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
            return result;
        }

        return result;
    }

/* 
    public void removeHostel(){

        try{
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream("database.xls");
            wb = new HSSFWorkbook(fis);

            // deletes the hostel sheet from the excel file
            Sheet hostelSheet = wb.getSheet(this.hostel_name);
            int index = wb.getSheetIndex(hostelSheet);

            wb.removeSheetAt(index);

            // delete the hostel name from the admins sheet
            HSSFSheet adminSheet = wb.getSheet("admins");

            // get the number of rows
            int numRows = adminSheet.getPhysicalNumberOfRows();

            // iterate through the rows using indexing
            for(int rowNum = 0; rowNum < numRows; rowNum++){
                // get the row
                Row row = adminSheet.getRow(rowNum);

                // check if the row is not null
                if(row != null){
                    // get the cell value
                    Cell cell = row.getCell(2);

                    // check if the cell value is null
                    if(cell != null){
                        // get the cell value
                        String cellValue = cell.getStringCellValue();

                        // compare the usernames
                        if (cellValue.equals(this.hostel_name)){
                            // username already exists
                            cell.setBlank();
                        }
                    }

                }
            }

            wb.write();
            fis.close();

        }catch (IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
        }


    }

    public void addRoom(int roomNum) {
        try{
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream("database.xls");
            wb = new HSSFWorkbook(fis);

            // retrieve the excel sheet
            HSSFSheet sheet = wb.getSheet(this.hostel_name);

            // find the last row in the excel sheet
            int lastrow = sheet.getLastRowNum();

            // create a new row for appending data for the user
            Row newRow = sheet.createRow(lastrow + 1);

            for(int i = 0; i < roomNum; i ++){
                int count = lastrow + i;
                Cell cell0 = newRow.createCell(count);
                cell0.setCellValue("Room " + count);

                Cell cell1 = newRow.createCell(count + 1);
                cell1.setCellValue("Taken");
            }


        }catch (IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
        }

    }

    public void removeRoom(int roomNum){

    }

  */  

}
