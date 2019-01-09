package model.brain;

import lombok.Getter;

@Getter
public class OutNeuron extends Neuron {

    @Override
    public void correctWeight(double tValue, double oValue) {
        double value = getValue();
        double weight;
        double error = tValue - value;
        getIn().stream().forEach(i -> i.getNeuronIn().correctWeight(error, value));

        for (Synapse synapse : getIn()){
                weight = synapse.getWeight() + N * error * proiz2(value) * synapse.getNeuronIn().getValue();
                synapse.setWeight(weight);
        }
    }
}
