package model.brain;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class InputNeuron implements INeuron, IInputNeuron{

    private double value;
    private Set<Synapse> out = new HashSet<>();


    public InputNeuron(double value){
        this.value = value;
    }

    @Override
    public void addInSynapse(Synapse synapse) {

    }

    @Override
    public void addOutSynapse(Synapse synapse) {

    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }
}
