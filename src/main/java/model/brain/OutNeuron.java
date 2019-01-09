package model.brain;

import lombok.Getter;

@Getter
public class OutNeuron extends Neuron {

    @Override
    public void correctWeight(double eValue) {
        double value = getValue();
        double delta = ((eValue - value) * ((1 - value) * value));

        getIn().stream().forEach(i -> i.getNeuronIn().correctWeight(delta));
    }
}
