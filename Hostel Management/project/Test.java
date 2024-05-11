package project;

public class Test{
    public static void main(String[] args){
        // Authentication au = new Authentication();
        // au.mainMenu();

        TheMain interface0 = new Authentication();
        int r = interface0.mainMenu();

        TheMain interface1 = new Student();
        TheMain interface2 = new Admin();

        if (r == 1){
            interface1.mainMenu();
            
        }
        else if(r == 2){
            interface2.mainMenu();
        }
    }

}