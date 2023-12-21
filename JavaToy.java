import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Toy {
    private int id;
    private String name;
    private int count;
    private double weight;

    public Toy(int id, String name, int count, double weight) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class ToyStore {
    private List<Toy> toys;

    public ToyStore() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void removeToy(Toy toy) {
        toys.remove(toy);
    }

    public Toy getPrizeToy() {
        Random random = new Random();
        double totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }
        double randomWeight = random.nextDouble() * totalWeight;
        double currentWeight = 0;
        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (currentWeight >= randomWeight) {
                toy.setWeight(0);
                toy.setCount(toy.getCount() - 1);
                return toy;
            }
        }
        return null;
    }

    public void printToys() {
        for (Toy toy : toys) {
            System.out.println(toy.getId() + " " + toy.getName() + " " + toy.getCount() + " " + toy.getWeight());
        }
    }

    public void writeToFile(Toy toy) {
        try {
            FileWriter writer = new FileWriter("toys.txt", true);
            writer.write(toy.getId() + " " + toy.getName() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}

public class JavaToy {
    public static void main(String[] args) {
        
        ToyStore store = new ToyStore();
        

        store.addToy(new Toy(1, "Car", 10, 10));
        store.addToy(new Toy(1, "Car", 10, 10));
        store.addToy(new Toy(2, "Doll", 20, 20));
        store.addToy(new Toy(3, "Ball", 30, 30));
        
        
        System.out.println("Initial toys:");
        store.printToys();

        store.getPrizeToy();
        System.out.println("Toys after getting a prize:");
        store.printToys();

                 
    }
}