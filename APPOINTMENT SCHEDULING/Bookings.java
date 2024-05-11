import java.util.Scanner;

public class Bookings {
    public static void main(String[] args){
        // STAGE 1: ASK THE USER WHAT THEY WOULD LIKE TO DO

        Scanner input = new Scanner(System.in);
        WeeklyApptSchedule  weekSchedule = new WeeklyApptSchedule();

        // display the weeklly schedule first
        
        // Greet user and ask the operation they would like to perform
        System.out.println("Good day, how many I help you today? Choose any of the options below typing in the corresponding number.");
        System.out.println("1. Book an appointment.\n2. Cancel an appointment \n3. Reschedule an appointment \n4. Display the list of appointments. \n5. Exit");

        // get the input from the keyboard
        String answer = input.next();

        // remove an string in the buffer
        input.nextLine();



        // STAGE 2: USER BOOKS AN APPOINTMENT



        // STAGE 3: USER CANCELS AN APPOINTMENT


        // STAGE 4: USER RESCHEDULES AND APPOINTMENT


        // STAGE 5: USER REQUEST FOR LIST OF APPONTMENTS TO BE DISPLAYED


        // STAGE 3: USER EXITS THE APPLICATION

        
    }

    // method that sets a new appointment
    // returns the time that the booking has been set
    public static String setAppointment(){
        Scanner input  = new Scanner(System.in);
        // ask user for their full name
        System.out.println("Enter your full name: ");
        String firstName = input.nextLine();

        // ask user for location
        System.out.println("Enter the venue: ");
        String location = input.nextLine();

        // ask user for purpose of the appointment
        System.out.println("Enter the purpose: ");
        String purpose = input.nextLine();

        // create an appointment object
        Appointment appt = new Appointment(firstName, location, purpose);

        // create a daily appointment object
        DailyApptSchedule dailyAppt = new DailyApptSchedule();

        // choose a day of the week to set appointment
        WeeklyApptSchedule.Day day = dayOfWeek();

        // create an weekly appointment object
        WeeklyApptSchedule weekSche = new WeeklyApptSchedule();

        // get the time of the day
        int timeIndex = timeOfDay();

        input.close();

        // add an appointment to the weekly schedule
        if (weekSche.addAppointment(appt, day, timeIndex)){
            return "Appointment scheduled successfully";
        }
        else{
            return "Failed to schedule appointment";
        }


    }



    // METHOD FOR CREATING AN APPOINTMENT

    
    // method that return the Day given a corresponding ordinal value
    public static WeeklyApptSchedule.Day dayOfWeek(){
        Scanner input = new Scanner(System.in);

        // ask user choose the day of the week
        System.out.println("Choose the day of the week by typing 0, 1, 2 e.t.c");

        // loop through all the days of the week
        for (int i = 0; i < WeeklyApptSchedule.NUM_DAYS; i++){
            // display the all the weekdays
            System.out.println(i + ". " + WeeklyApptSchedule.DAYS[i]);
        }

        System.out.println("Enter: ");

        // get the response in string format representing the day
        String dayNum = input.nextLine();

        // get the number representing the day of the week
        Integer num = Integer.parseInt(dayNum);

        // create a variable of type day
        WeeklyApptSchedule.Day day = WeeklyApptSchedule.Day.MONDAY;
        switch(num){
            case 0:
                day = WeeklyApptSchedule.Day.MONDAY;
                break;
            case 1:
                day = WeeklyApptSchedule.Day.TUESDAY;
                break;
            case 2:
                day = WeeklyApptSchedule.Day.WEDNESDAY;
                break;
            case 3:
                day = WeeklyApptSchedule.Day.THURSDAY;
                break;
            default:
                day = WeeklyApptSchedule.Day.FRIDAY;

        }
        input.close();
        return day;
    }

    // method that returns the time of the day
    public static int timeOfDay(){
        Scanner input = new Scanner(System.in);
        // display all the times of the day
        System.out.println("Select the time of the day by typing 0, 1, 2 etc");
        for (int i = 0; i < DailyApptSchedule.NUM_SLOTS; i++){
            System.out.println(i + ". " + DailyApptSchedule.TIMES[i]);
        }
        System.out.println("Enter time slot: ");
        String timeSlot = input.nextLine();
        
        // convert the time slot to an interger
        Integer n = Integer.parseInt(timeSlot);

        // return the index value of the time of the day
        return n;
    }
}
