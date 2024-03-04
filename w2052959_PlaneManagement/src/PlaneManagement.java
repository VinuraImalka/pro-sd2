import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {

    static char [][] seats = new char[4][14];


    public static void main(String[] args) {
        seats[1][12] = ' '; seats[1][13] = ' ';
        seats[2][12] = ' '; seats[2][13] = ' ';
        System.out.println("Welcome to the Plane Management application");
        menu();

    }
    public static void line_sep(){
        System.out.println("*".repeat(50));
    }

    public static boolean char_validation(char input){
        char[] valid_options = {'A','B','C','D'};
        boolean is_valid = false ;
        for (char option : valid_options){
            if (option == input){
                is_valid = true;
                break;
            }
        }
        return is_valid;
    }

    public static boolean num_validation(int input){
        int[] valid_options = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        boolean is_valid = false ;
        for (int option : valid_options){
            if (option == input){
                is_valid = true;
                break;
            }
        }
        return is_valid;
    }

    public static boolean seat_validation(char seat_letter,int seat_number){
        boolean is_valid = false;
        if((seat_letter == 'B'||seat_letter =='C')&&(seat_number==13 ||seat_number==14)){
            return is_valid;
        }
        else if((char_validation(seat_letter))&&(num_validation(seat_number))){
            is_valid = true;
        }
        return is_valid;
    }

    public static void menu() {
        String[] labels = {"Buy a seat","Cancel a seat","Find first available seat","Show seating plan",
                "Print tickets information and total sales","Search ticket","Quit"};
        line_sep();
        System.out.println("*"+" ".repeat(18)+"MENU OPTIONS"+" ".repeat(18)+"*");
        line_sep();
        for (int index = 0;index<labels.length;index++){
            if(index != (labels.length-1)){
                System.out.println("\t"+(index+1)+") "+labels[index]);
            }else {
                System.out.println("\t0) "+labels[index]);
            }
        }
        line_sep();
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Please select an option: ");
            int userChoice = input.nextInt();
            if (userChoice != 0){
                System.out.println(labels[userChoice-1]+" selected.");
            }else{
                System.out.println("Quit selected.");
                System.out.println("Quiting.");
                System.exit(0);
            }
            switch (userChoice){
                case 1:
                    seat(1,"buy");
                    break;
                case 2:
                    seat(2,"cancel");
                    break;
                case 3:
                    first_available_seat();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }

        }catch (InputMismatchException error){
            System.out.println("Please select a valid option!");
        }menu();

    }

    public static boolean conformation(){
        System.out.println("Please select y for yes ,n for no:");
        Scanner input = new Scanner(System.in);
        String user_input = input.nextLine();
        try{
            if(user_input.length() > 1){
                throw new RuntimeException();
            }else{
                boolean conformation = true;
                if (user_input.equals("n") || user_input.equals("y") ){
//                if (user_input.equals("n"))
                    if (user_input.charAt(0)=='n'){
                        conformation = false;
                    }
                }else{
                    throw new RuntimeException();
                }
                return conformation;
            }
        }catch (RuntimeException error){
            return (conformation());
        }
    }

    public static void seat(int function,String task){
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter the seat you want to "+task+"(A-D,1-14),(eg-:A3):");
            String user_input = input.nextLine().toUpperCase();
            char seat_letter = user_input.charAt(0);

            int seat_number = Integer.parseInt(user_input.substring(1));
            if(!(seat_validation(seat_letter,seat_number))){
                System.out.println("Please enter a valid seat!");
                throw new RuntimeException();
            }
            System.out.println("dsa"+seat_letter+seat_number);
            int seat_col = (seat_number-1);
            int seat_row = 0;
            switch (seat_letter) {
                case 'B' -> seat_row = 1;
                case 'C' -> seat_row = 2;
                case 'D' -> seat_row = 3;
            }
            if(function == 1 ){
                buy_seat(seat_row,seat_col,user_input);
            } else if (function == 2) {
                cancel_seat(seat_row,seat_col,user_input);
            }
        }catch (RuntimeException error){
            seat(function,task);
        }
    }

    public static void buy_seat(int seat_row,int seat_col,String user_input){
        try {
            if(seats[seat_row][seat_col]==0){
                System.out.println("seat "+user_input+" is available for purchase.");
            }else{
                System.out.println("seat "+user_input+" is not available for purchase.");
                System.out.println("Please select a valid available seat!");
                throw new RuntimeException();
            }

            System.out.println("would you like to purchase seat "+user_input+"?");
            if(conformation()){
                seats[seat_row][seat_col] = 'X';
                System.out.println("seat "+user_input+" is purchased successfully");
            }else{
                System.out.println("Stopping purchase.");
                throw new RuntimeException();
            }
        }catch(RuntimeException error){
            seat(1,"buy");
        }
    }

    public static void cancel_seat(int seat_row,int seat_col,String user_input){
        try {
            if(seats[seat_row][seat_col]=='X'){
                System.out.println("seat "+user_input+" is purchased.");
            }else{
                System.out.println("seat "+user_input+" is not purchased.");
                System.out.println("Please select a valid purchased seat!");
                throw new RuntimeException();
            }

            System.out.println("would you like to cancel the purchased seat "+user_input+"?");
            if(conformation()){
                seats[seat_row][seat_col] = 'O';
                System.out.println("purchase of seat "+user_input+" is canceled successfully!");
            }else{
                System.out.println("Stopping cancel.");
                throw new RuntimeException();
            }
        }catch(RuntimeException error){
            seat(2,"cancel");
        }
    }

    public static void first_available_seat(){
        for(int column_index = 0;column_index < seats.length; column_index++){
            for(int row_index =0; row_index < seats[column_index].length;row_index++){
                char column_letter = 'A';
                switch (column_index) {
                    case 'B' -> column_letter = 'B';
                    case 'C' -> column_letter = 'C';
                    case 'D' -> column_letter = 'D';
                }
                if (seats[column_index][row_index] == 0){
                    System.out.println("The first seat available for purchase is seat "+column_letter+""+(row_index+1));
                    menu();
                }
            }
        }
    }

    public static void show_seating_plan(){
        System.out.println("Seating plan");
        for(char[] column : seats){
            for(char row_item : column){
                if (row_item != 0){
                    System.out.print(row_item);
                }else{
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }
}