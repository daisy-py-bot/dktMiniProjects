package project;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Hostel {
    private String hostelName;
    private int numRoomsTotal;
    private String ownerName;
    private Room[] rooms;

    public Hostel(String ownerName, String hostelName, int numRoomsTotal){
        this.ownerName = ownerName;
        this.hostelName = hostelName;
        this.numRoomsTotal = numRoomsTotal;
        this.rooms = new Room[numRoomsTotal];
        Arrays.fill(rooms, null);
    }

    // Prints out the details of the whole
    // hostel in a summarized format
    public void getHostelDetails(){
        System.out.println("Hostel Name: " + this.hostelName);
        System.out.println("Founder's Name: " + this.ownerName);
        System.out.println("Number of Rooms: " + this.numRoomsTotal);
    }

    public void displayHostelInfo(){
        
    }

    

    // public void createRooms(int numRooms, int maximumRoomOccupancy){
    //     Admin newAdmin = new Admin();
    //     newAdmin.addRoom(numRooms, maximumRoomOccupancy); 

    //     for (int i = 0; i < numRooms; i++) {
    //         if (rooms[i] == null) {
    //             rooms[i] = new Room(i, maximumRoomOccupancy);
                 

    //         }
    //     }
    // }

}
