package model.brain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class BiasNeuron implements INeuron{

    private String num;
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
    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double updateValue() {
        return value;
    }

    @Override
    public void correctWeight(double e) { }

    @Override
    public String toString() {
        return "BiasNeuron{" +
                "num='" + num + '\'' +
                ", value=" + value +
                '}';
    }
}
