// ----------rescheduling the appointment to any other time slot

/**
 * A class to represent the appointment schedule for a given day.
 * Each day is assumed to have hour-long timeslots starting at 8am and
 * with the final timeslot ending at 4pm
 **/
public class DailyApptSchedule {

  private Appointment[] apptsForTheDay;

  public static final int NUM_SLOTS = 9;
  public static final String[] TIMES = {"8am", "9am", "10am", "11am", 
                                        "12noon", "1pm", "2pm", "3pm", "4pm"};

  // No-arg onstructor creates an empty schedule for a day
  public DailyApptSchedule() {
    // TO-DO
    // create an new empty daily appointment schedule
    apptsForTheDay = new Appointment[NUM_SLOTS];

    //set the all time slots in the array to be null
    // apptsForTheDay[0] = null;
    // apptsForTheDay[1] = null;
    // apptsForTheDay[2] = null;
    // apptsForTheDay[3] = null;
    // apptsForTheDay[4] = null;
    // apptsForTheDay[5] = null;
    // apptsForTheDay[6] = null;
    // apptsForTheDay[7] = null;
    // apptsForTheDay[8] = null;

  }

  /**
   * A method to display the list of appointments for the day
   * In the format:
   *    time: appointment_info
   *    time: appointment_info ... etc
   * Any empty slots (null Appointments in the array) should be listed as Free
   */
  public void displayAppointments() {
    // TO-DO
    // loop through the whole appointmentSchedule array
    for (int i = 0; i < NUM_SLOTS; i++){
      // display free if time slot in the apptsForTheDay Schedule references null
      // display the details of that appoinment if appointment is not free
      String s = TIMES[i] + ": " + (apptsForTheDay[i] == null ? "Free" : apptsForTheDay[i].toString()) ;

      // --------HOW DO I ADD APPOINTMENTS TO THE APPOINTMENT ARRAY????
      // --------CHANGE THE TIME SLOT ONCE ITS BOOKED

      // display the details of the appointment
      System.out.println(s);
    }
  }

  /**
   * A method to add the given appointment to the schedule in the specified timeslot
   * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
   * @param appt represents the appointment object
   * @return true if it was successful and false if not successful
   */
  public boolean addAppointment(int whichSlot, Appointment appt) {
    // TO-DO
    // check if the time slot given is free or booked in the daily appointment schedule
    if (apptsForTheDay[whichSlot] == null){
      // create an appointment for the given time slot
      apptsForTheDay[whichSlot] = appt;

      // the appointment is successful
      return true;

    }
    else{
      // the time slot is already booked and is not free
      // the appointment is not successful
      return false;
    }

  }

  /**
   * A method to add an appointment for the given person, venue and purpose to the schedule in the specified timeslot.
   * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
   * @param n represents the name of the student
   * @param v represents the location of the appointment
   * @param p represents the reason for the meeting
   * @return true if it was successful and false if not successful (i.e. if the given slot is invalid or taken)
   */
  public boolean addAppointment(int whichSlot, String n, String v, String p) {
    // TO-DO
    // check if the time slot is free (if there is no booking during that time)
    if(apptsForTheDay[whichSlot] == null){
      // create the appointment in the daily appointment array schedule
      apptsForTheDay[whichSlot] = new Appointment(n, v, p);

      // appointment scheduling is successful
      return true;
    }
    else{
      // time slot already booked, no appointment created
      return false;
    }

  }


  /**
   * A method to add the given appointment to the schedule in any free timeslot.
   * @param appt represents the appointment object
   * @return the index of the chosen timeslot or -1 if no free timeslot exists.
   */
  public int addAppointment(Appointment appt) {
    // TO-DO
    // search for an empty time slot
    for (int i = 0; i < NUM_SLOTS; i++){
      // check if the time slot is not booked
      if (apptsForTheDay[i] == null){
        // schedule the appointment
        apptsForTheDay[i] = appt;
        
        // return the index of the timeslot
        return i;

      }
    }
    // if no time slot is free, return -1
    return -1;
  }

  /**
   * A method to add an appointment for the given person, venue and purpose
   * @param n represents the name of the student
   * @param v represents the location of the appointment
   * @param p represents the reason for the meeting
   * @return the index of the chosen timeslot or -1 if no free timeslot exists.
   */
  public int addAppointment(String n, String v, String p) {
    // TO-DO
    // search for an empty time slot
    for (int i = 0; i < NUM_SLOTS; i++){
      // check if the time slot is not booked
      if (apptsForTheDay[i] == null){
        // schedule a new appointment
        apptsForTheDay[i] = new Appointment(n, v, p);
        
        // return the index of the timeslot
        return i;
      }
    }
    // if no time slot is free, return -1
    return -1;
  }
  

  /**
   * Remove the appointment in the given slot
   * @param slot the index of the timeslot
   * @return true if operation was successful and false if not
   */
  public boolean removeAppointment(int slot){
    // TO-DO
    // check if the given time slot is booked
    if (apptsForTheDay[slot] != null){
      // remove the appointment by setting the value on the slot to null
      apptsForTheDay[slot] = null;

      // appointment cancellation successful
      return true;
    }
    else{
      // there is no appointment booked
      return false;
    }
  }

  /**
   * Remove the appointment corresponding to the given student name
   * @param n represents the name of the student
   * @return true if operation was successful and false if not
   */
  public boolean removeAppointment(String n){
      // TO-DO
      // loop through the array of appointments to see if the student has booked the appointment
      for(int i = 0; i < NUM_SLOTS; i++){
        // check if the student is on the appointment list
        if(apptsForTheDay[i] != null && apptsForTheDay[i].getStudentName() == n){
          // cancel the appointment by setting the appointment aboject to null
          apptsForTheDay[i] = null;

          // appointment cancellation successful
          return true;
        }
      }
      // else there is no student with that name of the list of daily appointments
      return false;
  }

  // rescheduling an appointment given the name of the student and the new time
  // takes in student name (n) and new time slot for the appointment
  public boolean rescheduleAppt(String n, int newTime){
    // search for the appointment
    // loop through the list of daily appointments
    for (int i = 0; i < NUM_SLOTS; i++){
      // check for a slot with an appointment where apptsForTheDay is not referencing null
      // check for the name of the student
      if(apptsForTheDay[i] != null && apptsForTheDay[i].getStudentName().equals(n)){
        // store the appointment in temp variable
        // assuming the venue is not changing
        Appointment temp = apptsForTheDay[i];

        // set the old appointment to null/ remove it
        if (removeAppointment(n)){
          // when the appointment has been successfully removed
          // add the new appointment
          // return true once the appointment has been successfully rescheduled
          return (addAppointment(newTime, temp));
        }
      }
    }
    // return false if the appointment has not been successfully rescheduled
    return false;
  }

  // rescheduling an appointment given the old time slot index and the new time slot index
  // assuming that the venue is not changing
  public boolean rescheduleAppt(int oldTimeSlot, int newTimeSlot){
    // store the old appointment in a temporary variable
    Appointment temp = apptsForTheDay[oldTimeSlot];
    // set the current appointment to null or remove it
    if (removeAppointment(oldTimeSlot)){
      // set the new apppointment
      return (addAppointment(newTimeSlot, temp));
  
  }
    // return false if the appointment has not been rescheduled
    return false;
  }



   
   
}





