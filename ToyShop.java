import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ToyShop {

    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyShop() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void startRaffle() {

        Collections.shuffle(toys); // перемешиваем игрушки.

        int numPrizes = 3;         // Кол-во призов за розыгрыш.
        for (int i = 0; i < numPrizes; i++) {
            Toy prizeToy = selectPrizeToy(toys);
            if (prizeToy != null){
                prizeToys.add(prizeToy);
                decreaseToyQuantity(prizeToy.getId());
                System.out.println("Приз разыгран.");
            }
            else {
                System.out.println("Товары закончились. Пополните склад!");
                break;
            }
        }
    }

    private Toy selectPrizeToy(List<Toy> toys) {
        double totalWeight = 0.0;
        for (Toy toy : toys) {
            if (toy.getQuantity() > 0) totalWeight += toy.getWeight();
        }

        double randomWeight = Math.random() * totalWeight;
        double currentWeight = 0.0;

        for (Toy toy : toys) {
            if (toy.getQuantity() > 0){
                currentWeight += toy.getWeight();
                if (currentWeight >= randomWeight) {
                    return toy;
                }
            }
        }
        return null; // Если список toys пустой
    }

    private void decreaseToyQuantity(int toyId) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                int currentQuantity = toy.getQuantity() - 1;
                toy.setQuantity(currentQuantity);
                break;
            }
        }
    }

    public Toy getPrizeToy() {
        Toy prizeToy = prizeToys.get(0);
        prizeToys.remove(0);

        try {
            File file = new File("prize_toys.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(prizeToy.getName() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка.");
            e.printStackTrace();
        }
        return prizeToy;
    }

    public void loadToysFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double weight = Double.parseDouble(parts[3]);
                Toy toy = new Toy(id, name, quantity, weight);
                addToy(toy);
            }
//            scanner.close();
        } catch (IOException e) {
            System.out.println("Ошибка.");
            e.printStackTrace();
        }
    }

    public List<Toy> getPrizeToys() {
        return prizeToys;
    }
}
