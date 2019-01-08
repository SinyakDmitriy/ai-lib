import model.brain.Brain;
import model.brain.INeuron;
import model.coach.Coach;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Brain brain = Brain.builder()
                .inputs(2)
                .layers(1)
                .outputs(1)
                .build();

        brain.init();
        Set<INeuron> oLayer = brain.getOutputLayer();
        for (INeuron neuron : oLayer){
            System.out.println(neuron.getValue());
        }

        Coach coach = Coach.builder()
                .brain(brain)
                .build();

        coach.init();
        System.out.println(brain);
    }
}
