public class MainTestClass {
    public static void main (String[] args){
        // ------TESTING THE DAILY SCHEDULE-------------
        System.out.println("----------------TESTING THE DAILY SCHEDULE--------------");
       // create a DailyApptSchedule object
       DailyApptSchedule dayAppt = new DailyApptSchedule();
       System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
       dayAppt.displayAppointments();

       // create appointments object
       Appointment appt1 = new Appointment("Daisy", "FI Office", "Review Classes and Objects");
       Appointment appt2 = new Appointment("Kudzai", "Dr Korsah's Office", "Discuss the project");

       // ----add first appointment to the daily schedule -----
       System.out.println("ADDING THE FIRST APPOINTMENT");
       // -----WHAT KIND OF INPUT CAN I GIVE FOR THE TIME SLOT??----
       if (dayAppt.addAppointment(6, appt1)){
        System.out.println("Appointment successfully booked");
       }else{
        System.out.println("Failed to book appoiintment");
       }
       // display the daily appointment schedule
       System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
       dayAppt.displayAppointments();

       // -----create a second appointment on a time slot that is alredy booked----
       System.out.println("\nBOOKING A TIME SLOT THAT IS NOT FREE");
       if (dayAppt.addAppointment(6, appt2)){
        System.out.println("Appointment successfully booked");
       }else{
        System.out.println("Failed to book appointment");
       }
       // display the daily appointment schedule
       System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
       dayAppt.displayAppointments();


       // test the other overloaded method for adding an appointment
       System.out.println("\nBOOKING A SECOND APPOINTMENT USING THE OVERLOADED METHOD FOR ADDING AN APPOINTMENT");
       if (dayAppt.addAppointment(1, "Ama", "FI Office", "Learn Arrays")){
        System.out.println("Appointment successfully booked");
       }else{
        System.out.println("Failed to book appointment");
       }
       // display the daily appointment schedule
       System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
       dayAppt.displayAppointments();

       // booking an appointment at any free slots
       System.out.println("\nBOOKING ANY TIME SLOT THAT IS FREE");
       // get the inddex for the time slot
       int n = dayAppt.addAppointment(appt2);
       if (n != -1){
        System.out.println("Appointment successfully booked at " + dayAppt.TIMES[n]);
       }else{
        System.out.println("Failed to book appointment");
       }
       // display the daily appointment schedule
       System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
       dayAppt.displayAppointments();


       // booking an appointment at any free slots using the overloaded method
       System.out.println("\nBOOKING ANY TIME SLOT THAT IS FREE USING THE OVERLOADED METHOD");
       // get the inddex for the time slot
       int n1 = dayAppt.addAppointment("Chris", "Lobby", "Discuss personal matter");
       if (n1 != -1){
        System.out.println("Appointment successfully booked at " + dayAppt.TIMES[n1]);
       }else{
        System.out.println("Failed to book appointment");
       }
       // display the daily appointment schedule
       System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
       dayAppt.displayAppointments();


       // remove an appointment given the time slot
       System.out.println("REMOVE AN APPOINTMENT GIVEN THE TIME SLOT IT WAS ORIGINALLY BOOKED");
       if (dayAppt.removeAppointment(0)){
        System.out.println("Appointment at " + dayAppt.TIMES[0] + " has been cancelled");
       }
       else{
        System.out.println("No appointment was booked at that time.");
       }
       System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
       dayAppt.displayAppointments();


       // remove an appointment given the name of the student
       System.out.println("\nREMOVE AN APPOINTMENT GIVEN THE NAME OF STUDENT WHO ORIGINALLY BOOKED");
       if (dayAppt.removeAppointment(2)){
        System.out.println("Appointment at " + dayAppt.TIMES[2] + " has been cancelled");
       }
       else{
        System.out.println("No appointment was booked at that time.");
       }
       System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
       dayAppt.displayAppointments();


       // reschedule an appointment given the name of the student and the new time slot
       System.out.println("\nRESCHEDULE AN APPOINMENT GIVEN THE NAME OF THE STUDENT AND THE NEW TIME SLOT");
       if(dayAppt.rescheduleAppt("Daisy", 5)){
        System.out.println("Apointment successully rescheduled to " + dayAppt.TIMES[5]);
       }else{
        System.out.println("Rescheduling failed. Proposed time already booked");
       }
       // display the list of appointment
        System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
        dayAppt.displayAppointments();


    // reschedule an appointment given the old time slot and the new time slot
    System.out.println("\nRESCHEDULE AN APPOINMENT GIVEN THE OLD TIME SLOT AND THE NEW TIME SLOT");
    if(dayAppt.rescheduleAppt(1, 8)){
     System.out.println("Apointment successully rescheduled to " + dayAppt.TIMES[8]);
    }else{
     System.out.println("Rescheduling failed. Proposed time already booked");
    }
    // display the list of appointment
    System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
    dayAppt.displayAppointments();



    // book another appointment for any available slots
    System.out.println("\nBOOKING ANY TIME SLOT THAT IS FREE USING THE OVERLOADED METHOD");
    // get the inddex for the time slot
    int n2 = dayAppt.addAppointment("Chris", "Lobby", "Discuss personal matter");
    if (n2 != -1){
     System.out.println("Appointment successfully booked at " + dayAppt.TIMES[n2]);
    }else{
     System.out.println("Failed to book appointment");
    }
    // display the daily appointment schedule
    System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
    dayAppt.displayAppointments();



    // reschedule the appointment for Chris
    // reschedule an appointment given the name of the student and the new time slot
    System.out.println("\nRESCHEDULE AN APPOINMENT GIVEN THE NAME OF THE STUDENT AND THE NEW TIME SLOT");
    if(dayAppt.rescheduleAppt("Chris", 4)){
     System.out.println("Apointment successully rescheduled to " + dayAppt.TIMES[4]);
    }else{
     System.out.println("Rescheduling failed. Proposed time already booked");
    }
    // display the list of appointment
     System.out.println("\nLIST OF A APPOINTMENTS WITHIN A DAY");
     dayAppt.displayAppointments();




     // --------------TESTING THE WEEKLY SCHEDULE---------------------
    


       
       


    }
}
