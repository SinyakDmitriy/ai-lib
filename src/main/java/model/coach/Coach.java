package model.coach;

import lombok.Builder;
import model.brain.Brain;
import model.brain.INeuron;
import model.brain.InNeuron;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Builder
public class Coach {

    private Brain brain;
    private TrainingSet trainingSet;

    public void init(){
        Map<Integer, Integer[]> iData = new HashMap<>();
        Integer[] first = {0, 0, 0};
        Integer[] second = {1, 0, 1};
        Integer[] third = {0, 1, 1};
        Integer[] fours = {1, 1, 0};

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

        for (int i = 0; i < 3000000; i++) {
            double gError = 0;
            for (Map.Entry<Integer, Integer[]> entry : iData.entrySet()) {
                Integer[] value = entry.getValue();
                fNeuron.setValue(value[0]);
                sNeuron.setValue(value[1]);
                oNeuron.correctWeight(value[2]);
                double pError = Math.pow(value[2] - oNeuron.getValue(), 2);
                gError += pError;
            }
            System.out.println((gError / brain.getAll().size()) * 100);
        }
        System.out.println(oNeuron);
    }
}
