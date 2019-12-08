package model.brain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Neuron implements INeuron {

    private Set<Synapse> in = new HashSet<>();
    private Set<Synapse> out = new HashSet<>();

    @Setter
    private double error;
    private double value;

    public void addInSynapse(Synapse synapse){
        this.in.add(synapse);
    }

    public void addOutSynapse(Synapse synapse){
        this.out.add(synapse);
    }

    public double getValue(){
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double updateValue() {
        double uValue = getIn().stream().mapToDouble(i -> i.getNeuronIn().updateValue()*i.getWeight()).sum();
        uValue = relu(uValue);
        setValue(uValue);
        return uValue;
    }


    @Override
    public void correctWeight(double e) {

        setError(getOut().stream().mapToDouble(i -> i.getWeight()*e).sum());

        getIn().stream().forEach(i -> {
            double v = i.getNeuronIn().getValue();
            double w = i.getWeight();
            i.setWeight(w + N * getError() * derivativeRelu(v) * v);
        });

        getIn().stream().forEach(i -> i.getNeuronIn().correctWeight(getError()));
    }
}
