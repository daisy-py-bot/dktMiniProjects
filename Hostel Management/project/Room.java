package project;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Room {
    private int roomNumber;
    private int maximumRoomOccupancy;
    private Student[] studentsInRoom;

    // Creates the basics for a specific room
    public Room(int roomNumber, int maximumRoomOccupancy){
        this.roomNumber = roomNumber;
        this.maximumRoomOccupancy = maximumRoomOccupancy;
        this.studentsInRoom = new Student[maximumRoomOccupancy];
        Arrays.fill(studentsInRoom, null);
    }


}
