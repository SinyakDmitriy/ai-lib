package model.brain;

import lombok.Getter;

import java.util.*;

import static java.lang.Math.exp;

@Getter
public class Neuron implements INeuron {

    private Set<Synapse> in = new HashSet<>();
    private Set<Synapse> out = new HashSet<>();

    public void addInSynapse(Synapse synapse){
        this.in.add(synapse);
    }

    public void addOutSynapse(Synapse synapse){
        this.out.add(synapse);
    }

    public double getValue(){
        double value = in.stream().mapToDouble(s -> s.getNeuronIn().getValue() * s.getWeight()).sum();
        return 1/(1 + exp(-value));
    }

    @Override
    public void setValue(double value) {}

    @Override
    public void correctWeight(double error, double oValue) {

        double iValue = getValue();
        double weight;

        double sumError = out.stream()
                .mapToDouble(i -> i.getWeight() * error)
                .sum();

        getIn().stream().forEach(i -> i.getNeuronIn().correctWeight(sumError, iValue));

        for (Synapse synapse : getIn()){
            weight = synapse.getWeight() + N * error * proiz2(iValue) * synapse.getNeuronIn().getValue();
            synapse.setWeight(weight);
        }
    }
}
