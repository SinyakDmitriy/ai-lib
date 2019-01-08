package model.brain;

import lombok.Getter;

@Getter
public class OutNeuron extends Neuron {

    @Override
    public void correctWeight(double tValue) {
        double delta;
        double value = getValue();
        double weight;
        double error = tValue - value;
        getIn().stream().forEach(i -> i.getNeuronIn().correctWeight(error));

        for (Synapse synapse : getIn()){
                delta = value * (1 - value);
                weight = synapse.getWeight() + N * error * delta * value;
                synapse.setWeight(weight);
        }
    }
}
