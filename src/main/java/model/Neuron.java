package model;

import lombok.Getter;

import java.util.*;

@Getter
public class Neuron {

    public static final int MAX_K = 1;
    public static final int MIN_K = -1;

    private long id;
    private Set<Synapse> in = new HashSet<>();
    private Set<Synapse> out = new HashSet<>();
    private Map<Long, Double> inkMap = new HashMap<>();
    private Map<Long, Double> invMap = new HashMap<>();

    public Neuron() {}

    public Neuron(long id) {
        this.id = id;
    }

    public void initK(){
        for (Synapse synapse : in) {
            inkMap.put(synapse.getId(), 0d);
        }
    }

    public void addInSynapse(Synapse synapse){
        this.in.add(synapse);
    }

    public void addOutSynapse(Synapse synapse){
        this.out.add(synapse);
    }

    public void calculateValue(long synapseId, double inputValue){
        double k = inkMap.get(synapseId);
        invMap.put(synapseId, k * inputValue);
    }

    public double getValue(){
        return invMap.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Neuron)) return false;
        Neuron neuron = (Neuron) o;
        return id == neuron.id &&
                Objects.equals(in, neuron.in) &&
                Objects.equals(out, neuron.out) &&
                Objects.equals(inkMap, neuron.inkMap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, in, out, inkMap);
    }
}
