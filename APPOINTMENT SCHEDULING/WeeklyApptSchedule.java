
public class WeeklyApptSchedule{
   
   private DailyApptSchedule[] apptsForTheWeek;
   
   public static final int NUM_DAYS = 5;
   public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", 
     "Thursday", "Friday"};
   public enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};
   
   // No-arg constructor creates an array of DailyApptSchedule 
   // for the week
   public WeeklyApptSchedule()
   {
      // TO-DO
      // creates an array of empty daily schedules
      apptsForTheWeek[0] = new DailyApptSchedule();
      apptsForTheWeek[1] = new DailyApptSchedule();
      apptsForTheWeek[2] = new DailyApptSchedule();
      apptsForTheWeek[3] = new DailyApptSchedule();
      apptsForTheWeek[4] = new DailyApptSchedule();

   }
   
   // display weekly appointments
   // Display the set of appointsments for the week by showing
   // the schedule for each day.
   public void displayWeeklyAppointments() {
      // TO-DO
      // loop through the array of weekly appointments
      for (int i = 0; i < NUM_DAYS; i++){
         // display the day of the week
         System.out.println("----------" + DAYS[i] + "----------");
         // display the appointments for that day
         apptsForTheWeek[i].displayAppointments();
      }
   }
   
   // Add an appointment for a given timeslot on a given day.
   // Note: You may find it helpful to invoke day.ordinal()
   // ordinal() is a method automatically defined for enumerations 
   // it gives an "index" of a particular value in the enumeration.
   public boolean addAppointment(Appointment appt, Day day, int timeSlot){
     // TO-DO
     // create a daily appointment
     // get the ordinal value for the day
     int ordinalValue = day.ordinal();
     // set appointment for the given day of the week
     // if the appointment is set successfully, return true
     return (apptsForTheWeek[ordinalValue].addAppointment(timeSlot, appt));

   }
   
   
   // Cancels (removes) the appointment in a given timeslot on
   // a given day
   public void cancelAppointment(Day day, int timeSlot) {
      // TO-DO 
      // get the ordinal value for the given day of the week
      int ordinalValue = day.ordinal();

      // remove the appointment
      apptsForTheWeek[ordinalValue].removeAppointment(timeSlot);
   }

   // reschedule an appointment given the old day and the old time, and the new day and the new time
   public boolean rescheduleAppt(Appointment appt, Day oldDay, int oldTime, Day newDay, int newTime){
      // store the appointment in a temporary variable
      Appointment temp = appt;

      // cancel the current appointment 
      cancelAppointment(oldDay, oldTime);

      // reschedule the appointment
      // return true if the appointment has been rescheduled successfully
      return (addAppointment(temp, newDay, newTime));


   }

}