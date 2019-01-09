package model.brain;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class BiasNeuron implements INeuron{

    private double value;
    private Set<Synapse> out = new HashSet<>();

    public BiasNeuron(double value){
        this.value = value;
    }

    @Override
    public void addInSynapse(Synapse synapse) {}

    @Override
    public void addOutSynapse(Synapse synapse) {
        this.out.add(synapse);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public void correctWeight(double oDelta) {
        double iValue = getValue();

        getOut().stream().forEach(i -> {
            double grad = iValue*oDelta;
            double deltaW1 = N * grad + a * i.getDWeight();
            i.setDWeight(deltaW1);
            i.setWeight(i.getWeight() + i.getDWeight());
        });
    }
}
