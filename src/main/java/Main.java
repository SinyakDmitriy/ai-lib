import model.brain.Brain;

public class Main {
    public static void main(String[] args) {
        Brain brain = Brain.builder()
                .inputs(2)
                .layers(1)
                .outputs(1)
                .build();

        brain.init();
        brain.calculate();

        System.out.println(brain);
    }
}
