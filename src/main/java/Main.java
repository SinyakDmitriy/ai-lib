import model.LineChartSimple;
import model.brain.Brain;
import model.brain.INeuron;
import model.coach.Coach;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Map<Long, Double> buff = new ConcurrentHashMap<>();

        Thread thread = new Thread(() -> {
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

            coach.init(buff);
            System.out.println(1);
        });

        thread.start();

//        LineChartSimple.launch(buff, args);
    }
}
