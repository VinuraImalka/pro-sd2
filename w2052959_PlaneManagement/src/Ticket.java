public class Ticket {
    private static int row;
    private static String seat;
    private static double price;
    private static Person person;

    Ticket(int row, String seat,double price,Person person){
        Ticket.row = row;
        Ticket.seat = seat;
        Ticket.price = price;
        Ticket.person = person;
    }

    public static int getRow() {
        return row;
    }

    public static String getSeat() {
        return seat;
    }

    public static double getPrice() {
        return price;
    }

    public static Person getPerson() {
        return person;
    }

    public static void setRow(int row) {
        Ticket.row = row;
    }

    public static void setSeat(String seat) {
        Ticket.seat = seat;
    }

    public static void setPrice(double price) {
        Ticket.price = price;
    }

    public static void setPerson(Person person) {
        Ticket.person = person;
    }

    public static void printInformation(){
        System.out.println("Row : "+row);
        System.out.println("Seat : "+ seat);
        System.out.println("Price : "+ price);
        Person.printInformation();
    }
}
