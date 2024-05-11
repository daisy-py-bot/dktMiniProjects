package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.Scanner;



public class Student implements TheMain{

    public static final String PATHFILE = "HostelData.xls";
    public static final int MaxOccIndex = 1;
    public static final int CurentOccIndex = 2;
    public static final int PackageTypeIndex = 3;
    public static final String Sheet = "sheet0";
	public int mainMenu() {
		//code to ask user if they want to pick or view the rooms
        Scanner userInput = new Scanner(System.in);
        //ask user to pick from a menu
        System.out.println("Welcome to the Hostel Management System");
        System.out.println("These are the hostels available on Campus:");
        //display the hostel data
                //ask them to pick a hostel
                ArrayList<String> nameOfHostels = new ArrayList<>();//getting an array of hostels
                try{
            
                    HSSFWorkbook wb1 = null;
                    FileInputStream inputStream = new FileInputStream(PATHFILE);
                    wb1 = new HSSFWorkbook(inputStream);
        
                    //getting the number of hostels
                    int numOfHostels = wb1.getNumberOfSheets();
                    //using a for loop to go through each sheet and gets its name
                    for(int i = 0; i < numOfHostels;i++ ){
                        nameOfHostels.add((String)wb1.getSheetName(i));
                    }
        
                    wb1.close();
                }
                catch(IOException e){
                    System.out.println("Error opening the file" + e.getMessage());
                }
                //displaying the hostel with their index
                int h = 0;
                for(h= 0;h<nameOfHostels.size();h++){
                    System.out.println(h + ". " + nameOfHostels.get(h));
                }
        System.out.println("Please enter a number to pick what you want to do today:");
        System.out.println("1. Display Hostel Rooms");
        System.out.println("2. Pick a room");
        System.out.println("3. Leave a room");
        System.out.println("4. Change rooms");
        System.out.println("Enter number:");
        int userChoice = userInput.nextInt();
        switch (userChoice) {
            case 1:
            //display the hostel data
                //ask them to pick a hostel
                System.out.println("Please enter the number that represents the hostel of your choice");
                int hostelChoice = userInput.nextInt();
                //display the hostel data
                displayHostelData(PATHFILE,nameOfHostels.get(hostelChoice));
                break;
            case 2:
                //ask them to pick a hostel
                System.out.println("Please enter the number that represents the hostel of your choice");
                int hostelName = userInput.nextInt();
                //ask user to pick a room to book 
                System.out.println("Please enter the room number you want to book:");
                int roomNumber = userInput.nextInt();
                boolean roomChosen = pickRoom(roomNumber,PATHFILE,nameOfHostels.get(hostelName));
                break;
            case 3:
                //ask them to pick a hostel
                System.out.println("Please enter the number that represents the hostel of your choice");
                int hostelNameToleave = userInput.nextInt();
                //ask user of the room they want to leave
                System.out.println("What is the room number that you would like to leave?");
                int roomNumberLeave = userInput.nextInt();
                boolean roomLeft = leaveRoom(roomNumberLeave,PATHFILE,nameOfHostels.get(hostelNameToleave));
                break;
            case 4:
                System.out.println("Please enter the number that represents the hostel you currently live in");
                int OriginalHostel = userInput.nextInt();
                //ask user of the room they want to leave
                System.out.println("What is the room number that you would like to leave?");
                int roomNoLeave = userInput.nextInt();
                boolean roomToLeave = leaveRoom(roomNoLeave,PATHFILE,nameOfHostels.get(OriginalHostel));
                //ask user the room they want to move to 
                //ask them to pick a hostel
                System.out.println("Please enter the number that represents the hostel of you want to move to: ");
                int NewhostelName = userInput.nextInt();
                //ask user to pick a room to book 
                System.out.println("Please enter the room number you want to book:");
                int roomNew = userInput.nextInt();
                boolean NewRoomMoved = pickRoom(roomNew,PATHFILE,nameOfHostels.get(NewhostelName));
                break;
                
        
            default:
                System.out.println("Invalid,program ends here");
                break;
        }
        return 0;
	}
    
    //method to display data to the student based on the sheet:
    public static void displayHostelData(String filePath, String HostelName){

        try{
        HSSFWorkbook wb1 = null;
        FileInputStream inputStream = new FileInputStream(filePath);
        wb1 = new HSSFWorkbook(inputStream);

        HSSFSheet sheet = wb1.getSheet(HostelName);
        if(sheet == null){
            System.out.println("Hostel not found");
            return;
        }
        //Sheet sheet = wb1.getSheetAt(0);//accessing the first sheet
        //using a for loop to go through the sheet data to display data
        //getting the max number of rows and cells in the sheet
        int rows = sheet.getLastRowNum();
        //System.out.println("|Rooms|Room Capacity|Current Number of Occupants|Package Type|");
        for(int r = 0 ; r <= rows; r++) {
			Row row = sheet.getRow(r);
            int cols = sheet.getRow(r).getLastCellNum();
			
			
			for(int c = 1; c < cols;c++) {
				
				Cell cell =  row.getCell(c);
                System.out.println(cell);
				switch(cell.getCellType()) {
				case STRING:  
                    System.out.print("|" + cell.getStringCellValue()+"|\n");
                    break;
				case NUMERIC: 
                    System.out.print("|" +(int) cell.getNumericCellValue()+"|");
                    break;
				case BOOLEAN: 
                    System.out.print("|" + cell.getBooleanCellValue()+"|\n");
                    break;
                default:
                    break;
				
				
				}
				
			}
            System.out.println();
            wb1.close();
            inputStream.close();
        }
        }
            catch(IOException e){
                System.out.println("Error opening the file" + e.getMessage());
            }
			System.out.println();
		}
    

    public static boolean pickRoom(int roomNumber, String filePath, String HostelName)
    {
        HSSFWorkbook wb = null;
        try{

        // extract the file

        FileInputStream fis = new FileInputStream(filePath);
        wb = new HSSFWorkbook(fis);
        
        // retrieve the excel sheet
        HSSFSheet sheet = wb.getSheet(HostelName);
        if(sheet == null){
            System.out.println(HostelName + "hostel does not exist");
            wb.close();
            return false;
        }

        //use get row using the roomNumber chosen
        //check if the room is available and then book room
        int rows = sheet.getLastRowNum() + 1;

        if(roomNumber > rows ||  roomNumber < 0){
            System.out.println("Invalid room number. Pick another room.");
            wb.close();
            return false;
        }

        //index == roomNumber 
        Row row = sheet.getRow(roomNumber);

        //get the cell at index 2 which is the current number of occupants
        Cell CurrentValueOfOccupants = row.getCell(CurentOccIndex);
        //get the numeric value of the cell
        int NumOfOccupants = (int) CurrentValueOfOccupants.getNumericCellValue();
        //get the cell at index 1 which is the the maximum capacity of the room and compare that to current number of occupants
        Cell maxCapacityOfRoom = row.getCell(1);
        int maxCapacity = (int) maxCapacityOfRoom.getNumericCellValue();
        
            
            if(NumOfOccupants>= maxCapacity){
                System.out.println("The room " + roomNumber + "is currently full.");
                
                return false;
            }
            else
            {
                    FileOutputStream outputStream = new FileOutputStream(filePath);
                    //if the room is available allow them to book the room and update the current number of occupants

                
                    CurrentValueOfOccupants.setCellValue(NumOfOccupants +1 ); //update the number of occupants in the room
                    wb.write(outputStream);
                    System.out.println("You have successfully booked room " + roomNumber);
                    wb.close();
                    return true;//return true if the room is available and has been successfuully picked
            } 

                
            } catch (IOException e){
                System.out.println("Error opening file." + e.getMessage());
                return false;
            }
        }

        //method to leave a room
    public static boolean leaveRoom(int roomNumber, String filePath, String HostelName){
        
        try{
            HSSFWorkbook wb = null;
            //extracting the file
            FileInputStream file = new FileInputStream((filePath));
            wb = new HSSFWorkbook(file);
            //retrieving data from the excel sheet 
            HSSFSheet sheet = wb.getSheet(HostelName);
            if(sheet == null){
                System.out.println("Hostel not found");
                wb.close();
                return false;
            }
            //using the getRow using the roomNumber 
            int rows = sheet.getLastRowNum() + 1; //indexing starts at 0 

            if(roomNumber> rows || roomNumber < 0){
                System.out.println("Invalid room number.");
                wb.close();
                return false;
            }
            //index == roomNumber , our room numbers start from 1 onwards
            Row row = sheet.getRow(roomNumber);
            //getting the column at index 2 which the current number of occupants
            Cell CurrentValueOfOccupants = row.getCell(CurentOccIndex);
            //getting the numeric value of the cell
            int NumOfOccupants = (int) CurrentValueOfOccupants.getNumericCellValue();
            //check if the number of current occupants is equa1 to or greater than 0
            if(NumOfOccupants <= 0){
                System.out.println("There are no occupants to unbook this room.");
                return false;
            }
            else{
                //code to leave/unbook a room
                //update the number of occupants
                FileOutputStream outputStream = new FileOutputStream(filePath);
                //reduce the number of occupants
                CurrentValueOfOccupants.setCellValue(NumOfOccupants - 1);
                wb.write(outputStream);
                
                
                System.out.println("You have successfully left room " + roomNumber);
                wb.close();
                outputStream.close();
                return true;//return true if the room is left
                }
                

        }
        catch(IOException e){
            System.out.println("Error opening file." + e.getMessage());
        }
        return true;
    }
}