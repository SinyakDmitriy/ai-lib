package model.brain;

import lombok.Builder;
import lombok.Getter;
import model.coach.IBrainForCoach;

import java.util.*;

@Getter
@Builder
public class Brain implements IBrainForCoach {
    private Map<Long, Set<Neuron>> brainMap;
    private Set<Neuron> all;

    @Builder.Default private long inputs = 1;
    @Builder.Default private long outputs = 1;
    @Builder.Default private long layers = 1;

    public void init(){
        this.brainMap = genereteBrainMap();
        this.all = convertToBrainSet(brainMap);
        genereteSynapses();
    }

    private Map<Long, Set<Neuron>> genereteBrainMap(){
        Map<Long, Set<Neuron>> brainMap = new HashMap<>();

        brainMap.put(0L, getLayer(this.inputs));
        brainMap.put(layers + 1, getLayer(this.outputs));

        for (long i = 1; i <= layers; i++) {
            brainMap.put(i, getLayer(this.inputs + 1));
        }

        return brainMap;
    }

    private Set<Neuron> convertToBrainSet(Map<Long, Set<Neuron>> brainMap){
        Set<Neuron> all = new HashSet<>();

        for (Map.Entry<Long, Set<Neuron>> entry : brainMap.entrySet()){
            all.addAll(entry.getValue());
        }

        return all;
    }

    private Set<Neuron> getLayer(long neuronCount){
        Set<Neuron> layerSet = new HashSet<>();

        for (int i = 0; i < neuronCount; i++) {
            Neuron neuron = new Neuron();
            layerSet.add(neuron);
        }

        return layerSet;
    }

    private void genereteSynapses(){
        Iterator<Long> iterator = this.brainMap.keySet().iterator();
        Set<Neuron> current = this.brainMap.get(iterator.next());
        Set<Neuron> prev;

        while (iterator.hasNext()){
            prev = current;
            current = this.brainMap.get(iterator.next());

            connectNeurons(prev, current);
        }
    }

    private void connectNeurons(Set<Neuron> input, Set<Neuron> output){
        for (Neuron neuron : input){
            connectNeuron(neuron, output);
        }
    }

    private void connectNeuron(Neuron input, Set<Neuron> output){
        for (Neuron neuron : output){
            Synapse synapse = Synapse.builder()
                    .neuronIn(input)
                    .neuronOut(neuron)
                    .build();

            input.addOutSynapse(synapse);
            neuron.addInSynapse(synapse);
        }
    }

    public Set<Neuron> getInputLayer(){
        return brainMap.get(0L);
    }

    public Set<Neuron> getOutputLayer(){
        long size = this.brainMap.size();
        return this.brainMap.get(size - 1);
    }

    @Override
    public void setValueInputLayer(double value) {

    }
}
