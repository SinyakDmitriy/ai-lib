package model.coach;

import lombok.Builder;
import model.brain.Brain;
import model.brain.INeuron;
import model.brain.InNeuron;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.pow;
import static java.util.Objects.isNull;
import static java.util.concurrent.TimeUnit.SECONDS;

@Builder
public class Coach {

    private Brain brain;
    private double error;
    @Builder.Default private Map<Integer, List<Double>> errorMap = new HashMap<>();
    @Builder.Default private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private void initScheduler(){
        final Runnable beeper = new Runnable() {
            public void run() { System.out.println(error); }
        };
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 1, 2, SECONDS);
    }

    public void init(){

        initScheduler();

        Map<Integer, Double[]> iData = new HashMap<>();
        Double[] first = {0.1, 0.1, 0.1};
        Double[] second = {0.9, 0.1, 0.9};
        Double[] third = {0.9, 0.9, 0.1};
        Double[] fours = {0.1, 0.9, 0.9};

        iData.put(0, first);
        iData.put(1, second);
        iData.put(2, third);
        iData.put(3, fours);

        Set<INeuron> iLayer = this.brain.getInputLayer();
        Set<INeuron> oLayer = this.brain.getOutputLayer();

        INeuron fNeuron = null;
        INeuron sNeuron = null;

        for (INeuron neuron : iLayer){
            if(neuron instanceof InNeuron){
                if(fNeuron == null) fNeuron = neuron;
                else sNeuron = neuron;
            }
        }

        INeuron oNeuron = null;

        for (INeuron neuron : oLayer){
            oNeuron = neuron;
        }


        for (int i = 0; i < 3000; i++) {
            if(i == 865)
                System.out.println(0);
            for (Map.Entry<Integer, Double[]> entry : iData.entrySet()) {
                Double[] value = entry.getValue();
                fNeuron.setValue(value[0]);
                sNeuron.setValue(value[1]);
                double error = pow(value[2] - oNeuron.getValue(), 2) * 100;
                oNeuron.updateValue();
                oNeuron.correctWeight(value[2]);
            }
        }
        this.scheduler.shutdown();
        System.out.println(oNeuron);
    }

    private void saveError(int index, double error){
        List<Double> listError = errorMap.get(index);
        if (isNull(listError)){
            listError = new ArrayList<>();
            errorMap.put(index, listError);
        }

        listError.add(error);
    }
}
