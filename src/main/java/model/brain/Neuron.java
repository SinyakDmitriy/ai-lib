package model.brain;

import lombok.Getter;

import java.util.*;

import static java.lang.Math.exp;

@Getter
public class Neuron implements INeuron{

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Neuron neuron = (Neuron) o;

        if (in != null ? !in.equals(neuron.in) : neuron.in != null) return false;
        return out != null ? out.equals(neuron.out) : neuron.out == null;
    }

    @Override
    public int hashCode() {
        int result = in != null ? in.hashCode() : 0;
        result = 31 * result + (out != null ? out.hashCode() : 0);
        return result;
    }
}
