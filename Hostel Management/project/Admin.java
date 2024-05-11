package project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Admin extends Authentication{
    private String fullname;
    private String password;
    private static final String PATHFILE = "HostelData.xls";

    // Constructor for Admin Class
    public Admin(){
        this.fullname = super.getUserFullName();
        this.password = super.getUserPassword();
    }

    // Checks if the hostel owner is the same as the admin
    public boolean checkHostelOwner(String hostelName){
        boolean result = false;
        try{
            boolean hostelExists = checkHostelExits(hostelName);
            
            if (hostelExists){
                HSSFWorkbook wb = null;
                FileInputStream fis = new FileInputStream(PATHFILE);
                wb = new HSSFWorkbook(fis);
                HSSFSheet sheet = wb.getSheet(hostelName);
                Row row = sheet.getRow(0);
                
                if(row != null){
                    Cell cell = row.getCell(0);

                    if (cell != null){
                        String cellValue = cell.getStringCellValue();
                        if (cellValue.equals(this.fullname)){
                            result = true;
                        } else {
                            result = false;
                        }
                    } else {
                        System.out.println("No data found...");
                    }
                } else {
                    System.out.println("No data found...");
                }

                wb.close();

            }
        } catch (IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
            return result;
        }

        return result;
    }

    // Checks that the Hostel Exists
    public boolean checkHostelExits(String hostel_name){
        boolean result = false;
        try{
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream(PATHFILE);
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
        } catch (IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
            return result;
        }

        return result;
    }


    public void removeHostel(String hostelName){

        try{
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream(PATHFILE);
            wb = new HSSFWorkbook(fis);

            if (checkHostelOwner(hostelName)){
                // deletes the hostel sheet from the excel file
                HSSFSheet hostelSheet = wb.getSheet(hostelName);
                int index = wb.getSheetIndex(hostelSheet);
                System.out.print("testing...");

                wb.removeSheetAt(index);
                wb.write();
            } else {
                System.out.println("\nYou do not have the permission to delete this hostel...\n");
            }

            wb.close();
            fis.close();
        } catch (IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
        } catch (IllegalStateException ise) {
            System.out.println("You cannot write to this file...");
        }
    }

    // Main menu for the Admin class per the Main
    // interface requirement
    public int mainMenu(){
        while(true){
            Scanner keyboard = new Scanner(System.in);
            System.out.println("1. Add hostel");
            System.out.println("2. Delete hostel");
            System.out.println("3. Display hostel info");
            System.out.println("4. Exit Portal");
            System.out.print("> ");

            int choice = keyboard.nextInt();

            switch(choice){
                case 1:
                    Home newHostel = new Home();
                    newHostel.createHostel(fullname);
                    break;
                case 2:
                    System.out.println("Please enter the hostel's name");
                    System.out.print("> ");
                    keyboard.nextLine();
                    String hostelName = keyboard.nextLine();
                    removeHostel(hostelName);
                    break;
                case 3:
                    Student s = new Student();
                    //s.displayHostelData(PATHFILE);
                    break;
                case 4:
                    System.out.println("\nThank you for using Ashtel Hostel Services.");
                    System.exit(0);
            }
        }
    }

    public static int newHostelNumRooms(Scanner keyboard, int i, int totalRooms){

        boolean confirmation = false;
        int packageRooms = Integer.MAX_VALUE;
        int roomTotal = 0;

        while (!confirmation) {
            roomTotal = Integer.MAX_VALUE;
            while (roomTotal == Integer.MAX_VALUE){

                System.out.println("Number of rooms for " + (i + 1) + " in a room");
                System.out.print("> ");
                String textRoomTotal = keyboard.nextLine();

                try {
                    packageRooms = Integer.parseInt(textRoomTotal);
                } catch(NumberFormatException NFE) {
                    System.out.println("Invalid Integer. Try again.");
                    System.out.println();
                }

                if (roomTotal > 0){
                    confirmation = true;
                } else {
                    System.out.println("You cannot have a negative number of rooms. Please try again...");
                    confirmation = false;
                    System.out.println();
                }

                if (roomTotal > totalRooms){
                    System.out.println("This number exceeds the total number of rooms left. Please try again...");
                    confirmation = false;
                    System.out.println();
                } else {
                    confirmation = true;
                }
            }
        }
        return roomTotal;
    }
}
