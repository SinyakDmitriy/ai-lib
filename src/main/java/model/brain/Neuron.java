package model.brain;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

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
    public void correctWeight(double oDelta) {

        double iValue = getValue();
        double sumWOutDelta = getOut().stream().mapToDouble(i -> i.getWeight()*oDelta).sum();
        double delta = (1 - iValue) * iValue * sumWOutDelta;

        getOut().stream().forEach(i -> {
            double grad = iValue*oDelta;
            double deltaW1 = N * grad + a * i.getDWeight();
            i.setDWeight(deltaW1);
            i.setWeight(i.getWeight() + i.getDWeight());
        });

        getIn().stream().forEach(i -> i.getNeuronIn().correctWeight(delta));
    }
}
