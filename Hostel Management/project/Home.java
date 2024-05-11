package project;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Home {
    public static final String PATHFILE = "HostelData.xls";

    public static int newHostelNumberOfRooms(Scanner keyboard, String hostelName){

        boolean confirmation = false;
        int numberOfRooms = Integer.MAX_VALUE;

        while (!confirmation) {
            numberOfRooms = Integer.MAX_VALUE;
            while (numberOfRooms == Integer.MAX_VALUE){

                System.out.println("How many rooms are in " + hostelName + " ?");
                System.out.print("> ");
                String textNumberOfRooms = keyboard.nextLine();

                try {
                    numberOfRooms = Integer.parseInt(textNumberOfRooms);
                } catch(NumberFormatException NFE) {
                    System.out.println("Invalid Integer. Try again.");
                    System.out.println();
                }

                if (numberOfRooms > 0){
                    confirmation = true;
                } else {
                    System.out.println("You cannot have a negative number of rooms. Please try again...");
                    System.out.println();
                }
            }
        }

        return numberOfRooms;
    }

    public static String newHostelName(Scanner keyboard, String ownerName){
        // 2. Hostel Name
        System.out.println("Welcome " + ownerName);
        System.out.println("What is the name of your hostel?");
        System.out.print("> ");
        String hostelName = keyboard.nextLine();
        System.out.println();
        return hostelName;
    }

    public int newHostelRoomMaximumStudents(Scanner keyboard){

        boolean confirmation = false;
        int roomMaximumStudents = Integer.MAX_VALUE;

        while (!confirmation) {
            roomMaximumStudents = Integer.MAX_VALUE;
            while (roomMaximumStudents == Integer.MAX_VALUE){

                System.out.println("Maximum number of students allowed (Ex: 4, 2, 1 etc...)");
                System.out.print("> ");
                String textRoomMaximumStudentCapacity = keyboard.nextLine();

                try {
                    roomMaximumStudents = Integer.parseInt(textRoomMaximumStudentCapacity);
                } catch(NumberFormatException NFE) {
                    System.out.println("Invalid Integer. Try again.");
                    System.out.println();
                }

                if (roomMaximumStudents > 0){
                    confirmation = true;
                } else {
                    System.out.println("You cannot have a negative number of students. Please try again...");
                    System.out.println();
                }

            }
        }

        return roomMaximumStudents;
    }

    public Hostel createHostel(String ownerName){
        // Creates a Scanner object for input
        Scanner keyboard = new Scanner(System.in);

        // 2. Hostel Name
        String hostelName = newHostelName(keyboard, ownerName);

        // 3. Number Of Rooms
        int numberOfRooms = newHostelNumberOfRooms(keyboard, hostelName);

        // 4. Creating the Object
        Hostel newHostel = new Hostel(ownerName, hostelName, numberOfRooms);

        //newHostel.createRooms(numberOfRooms, 4);

        // 5. Creating the rooms
        System.out.println(hostelName + " Successfully Created");
        System.out.println("---------------------------");
        newHostel.getHostelDetails();

        // create admin sheet
        createAdmin(hostelName, ownerName);



        // add the room
        addRoom(hostelName, numberOfRooms, 4);
        

        return newHostel;

    }

    public void addRoom(String hostelName, int roomNum, int maxOccupancy) {
        try{
            System.out.println(roomNum);
            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream(PATHFILE);
            wb = new HSSFWorkbook(fis);

            // retrieve the excel sheet
            HSSFSheet sheet = wb.getSheet(hostelName);

            // find the last row in the excel sheet
            int lastrow = sheet.getLastRowNum();

            
            

            FileOutputStream fos = new FileOutputStream(PATHFILE);
            int count = lastrow + 1;
            for(int i = 0; i < roomNum; i ++){
                
                // room no., max number, occupancy, currentStudents
                
                Row rowDetail = sheet.createRow(i + 1);
                Cell cell0 = rowDetail.createCell(0);
                cell0.setCellValue("Room " + (i + 1));

                // max number
                Cell cell1 = rowDetail.createCell(1);
                cell1.setCellValue(maxOccupancy);

                // occupancy status
                Cell cell2 = rowDetail.createCell(2);
                cell2.setCellValue("Taken");

                // current student in the hist
                Cell cell3 = rowDetail.createCell(3);
                cell3.setCellValue(0);

                
                

                

            }
            
            wb.write(fos);
            fos.close();

            wb.close();


        }catch (IOException e){
            System.out.println("Error opening excel file: " + e.getMessage());
        }

    }

    public boolean createAdmin(String hostelName, String adminName)  {
      
        try{
            HSSFSheet sheet = null;

            HSSFWorkbook wb = null;
            FileInputStream fis = new FileInputStream(PATHFILE);
            wb = new HSSFWorkbook(fis);
            fis.close();

            // check if the file exists
            HSSFSheet sheetExist = wb.getSheet(hostelName);
            if(sheetExist == null){
                // sheet does not exist
                // create a new sheet
                sheet = wb.createSheet(hostelName);
                System.out.println("Hostel created");
            }
            else{
                System.out.println("Hostel not created");
            }


            // find the last row in the excel sheet
            //int lastrow = sheet.getLastRowNum();

            // create a new row for appending data for the user
            Row newRow = sheet.createRow(0);

            // add the adminName
            Cell cell0 = newRow.createCell(0);
            cell0.setCellValue(adminName);

            // // add the password
            // Cell cell1 = newRow.createCell(1);
            // cell1.setCellValue(this.password);

            // add the hostel_name
            Cell cell2 = newRow.createCell(2);
            cell2.setCellValue(hostelName);

            // // then create a worksheet for that hostel, to add room etc
            //wb.createSheet(hostelName);

            // open an output stream
            FileOutputStream output = new FileOutputStream(PATHFILE);

            // write to the file
            wb.write(output);
            output.close();
            
            wb.close();
            fis.close();
            return true;

        }catch (IOException e){
            System.out.println("Error2222 opening excel file: " + e.getMessage());
            return false;
        }
    }   
}