package model.brain;

import lombok.Getter;

import java.util.*;

@Getter
public class ZeroNeuron implements INeuron{

    private double value;
    private Set<Synapse> out = new HashSet<>();


    public ZeroNeuron(double value){
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
    public void correctWeight(double error) {}
}
