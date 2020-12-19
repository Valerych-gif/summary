package lesson2;

public class MainApp {

    private static ListDemo<String> arrayList;

    public static void main(String[] args) {
        System.out.println("=============ArrayList=============");
        arrayList = new ArrayListDemo<>();
        arrayDemo();

        System.out.println("\n=============LinkedList=============");
        arrayList = new LinkedListDemo<>();
        arrayDemo();
    }

    private static void arrayDemo() {
        System.out.println("Is list empty: " + arrayList.isEmpty());

        for (int i = 0; i < 25; i++) {
            arrayList.add("Item " + i);
        }

        System.out.println("List size after adding 25 entities: " + arrayList.size());
        System.out.println("Getting entity by index 15: " + arrayList.get(15));
        System.out.println("Removing entity by index 15.");
        arrayList.remove("Item 15");
        System.out.println("Getting entity by index 15: " + arrayList.get(15));
        System.out.println("List size: " + arrayList.size());
        System.out.println("Getting entity by index 24: " + arrayList.get(24));
        System.out.println("Setting entity by index 15: " + arrayList.set(15, "New Item"));
        System.out.println("Getting entity by index 15: " + arrayList.get(15));
        System.out.println("Setting entity by index 24 (more then list size): " + arrayList.set(24, "Wrong index"));
    }
}
