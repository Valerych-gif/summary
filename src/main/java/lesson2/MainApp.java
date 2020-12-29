package lesson2;

public class MainApp {

    private static ListDemo<String> list;

    public static void main(String[] args) {
        System.out.println("=============ArrayList=============");
        list = new ArrayListDemo<>();
        arrayDemo();

        System.out.println("\n=============LinkedList=============");
        list = new LinkedListDemo<>();
        arrayDemo();
    }

    private static void arrayDemo() {
        System.out.println("Is list empty: " + list.isEmpty());

        for (int i = 0; i < 25; i++) {
            list.add("Item " + i);
        }

        System.out.println("List size after adding 25 entities: " + list.size());
        System.out.println("Getting entity by index 15: " + list.get(15));
        System.out.println("Removing entity by index 15.");
        list.remove("Item 15");
        System.out.println("Getting entity by index 15: " + list.get(15));
        System.out.println("List size: " + list.size());
        System.out.println("Getting entity by index 24: " + list.get(24));
        System.out.println("Setting entity by index 15: " + list.set(15, "New Item"));
        System.out.println("Getting entity by index 15: " + list.get(15));
        System.out.println("Setting entity by index 24 (more then list size): " + list.set(24, "Wrong index"));
        System.out.println("Does list contain value 'Item 20': " + list.contains("Item 20"));
        System.out.println("Does list contain value 'Item 15': " + list.contains("Item 15"));
    }
}
