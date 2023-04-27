import java.util.List;
import java.util.Scanner;


public class View {
    private ToyShop toyShop;
    Scanner input = new Scanner(System.in);

    public View(ToyShop toyShop) {
        this.toyShop = toyShop;
    }

    public void showMenu() {
        System.out.println("\nГлавное Меню:");
        System.out.println("1. Сделать розыгрыш.");
        System.out.println("2. Выдать приз.");
        System.out.println("3. Показать оставшиеся разыгранные призы.");
        System.out.println("4. Показать остатки товаров в магазине.");
        System.out.println("0. Выход");
    }

    public int getChoice() {
        System.out.print("\nВыберите действие:\n");
        String in = input.next();
        if (isNumeric(in)) return Integer.parseInt(in);
        else return 10;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void start() {
        List<Toy> currentPrizeToys = toyShop.getPrizeToys();
        List<Toy> currentToys = toyShop.getToys();
        int choice = -1;
        while (choice != 0) {
            showMenu();
            choice = getChoice();
            switch (choice) {
                case 1:
                    toyShop.startRaffle();
                    break;
                case 2:
                    if (currentPrizeToys.size() == 0){
                        System.out.println("==============================");
                        System.out.println("Призов нет. Сделайте розыгрыш.");
                        System.out.println("==============================");
                        break;
                    }
                    Toy prizeToy = toyShop.getPrizeToy();
                    System.out.println("====== Выдаём приз ======");
                    System.out.println(prizeToy.getName());
                    System.out.println("=========================");
                    break;
                case 3:
                    if (currentPrizeToys.size() == 0){
                        System.out.println("==============================");
                        System.out.println("Призов нет. Сделайте розыгрыш.");
                        System.out.println("==============================");
                        break;
                    }
                    System.out.println("========= Призы =========");
                    for (Toy toy : currentPrizeToys){
                        System.out.println(toy.getName());
                    }
                    System.out.println("=========================");
                    break;
                case 4:
                    if (currentToys.size() == 0){
                        System.out.println("==============================");
                        System.out.println("Товара не осталось совсем!!!");
                        System.out.println("==============================");
                    }
                    else {
                        System.out.println("====== Остатки товаров ======");
                        for (Toy toy : currentToys){
                            System.out.println(toy.getName() + " " + toy.getQuantity());
                        }
                        System.out.println("=============================");
                    }
                    break;
                case 0:
                    showMessage("Пока!");
                    break;
                default:
                    showMessage("Неправильный выбор.");
                    break;
            }
        }
    }
}
