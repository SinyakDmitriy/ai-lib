package model.brain;

import lombok.Getter;

@Getter
public class OutNeuron extends Neuron {

    @Override
    public void correctWeight(double e) {
        double value = getValue();
        setError(e - value);

        getIn().stream().forEach(i -> i.getNeuronIn().correctWeight(getError()));

        getIn().stream().forEach(i -> {
            double v = i.getNeuronIn().getValue();
            double w = i.getWeight();
            i.setWeight(w + N * getError() * derivativeRelu(v) * v);
        });
    }
}
