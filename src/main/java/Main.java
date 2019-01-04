import model.Brain;

public class Main {
    public static void main(String[] args) {
        Brain brain = new Brain(2, 1, 1);
        brain.calculate();
        System.out.println(brain);
    }
}
