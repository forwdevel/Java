public class SingleThread {
    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            System.out.println("-");
        }

        for (int i = 0; i < 300; i++) {
            System.out.println("|");
        }
    }
}