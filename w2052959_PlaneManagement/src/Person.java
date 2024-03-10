public class Person {
    private static String name;
    private static String surname;
    private static String email;

    Person(String name,String surname,String email){
        Person.name = name;
        Person.surname = surname;
        Person.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        Person.name = name;
    }

    public void setSurname(String surname) {
        Person.surname = surname;
    }

    public void setEmail(String email) {
        Person.email = email;
    }

    public static void printInformation(){
        System.out.println("Name : "+ name);
        System.out.println("Surname : "+ surname);
        System.out.println("Email : "+ email);
    }
}
