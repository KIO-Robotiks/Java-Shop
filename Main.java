
public class Main {
    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();
        View view = new View(toyShop);
        toyShop.loadToysFromFile("toys.txt");

        view.start();
    }
}
