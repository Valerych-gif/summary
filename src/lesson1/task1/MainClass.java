package lesson1.task1;

public class MainClass {
    public static void main(String[] args) {
        Person person = new  PersonBuilder()
                .addFirstName("Ivan")
                .addLastName("Ivanov")
                .addCountry("Russia")
                .addAge(35)
                .build();
        System.out.println(person);
    }
}
