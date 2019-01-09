package model.brain;

import lombok.Builder;
import lombok.Getter;

import java.util.*;

import static java.lang.Math.random;

@Getter
@Builder
public class Brain {
    private Map<Long, Set<INeuron>> brainMap;
    @Builder.Default private Set<Synapse> synapseSet = new HashSet<>();
    private Set<INeuron> all;

    @Builder.Default private long inputs = 1;
    @Builder.Default private long outputs = 1;
    @Builder.Default private long layers = 1;

    public void init(){
        this.brainMap = genereteBrainMap();
        this.all = convertToBrainSet(brainMap);
        genereteSynapses();
    }

    private Map<Long, Set<INeuron>> genereteBrainMap(){
        Map<Long, Set<INeuron>> brainMap = new HashMap<>();

        brainMap.put(0L, getInputLayer(this.inputs));
        brainMap.put(layers + 1, getOutputLayer(this.outputs));

        for (long i = 1; i <= layers; i++) {
            brainMap.put(i, getLayer(this.inputs));
        }

        return brainMap;
    }

    private Set<INeuron> getInputLayer(long neuronCount){
        Set<INeuron> layerSet = new HashSet<>();

        INeuron defaultNeuron = new BiasNeuron(1);
        layerSet.add(defaultNeuron);

        for (int i = 0; i < neuronCount; i++) {
            INeuron neuron = new InNeuron();
            layerSet.add(neuron);
        }

        return layerSet;
    }

    private Set<INeuron> getOutputLayer(long neuronCount){
        Set<INeuron> layerSet = new HashSet<>();

        for (int i = 0; i < neuronCount; i++) {
            INeuron neuron = new OutNeuron();
            layerSet.add(neuron);
        }

        return layerSet;
    }

    private Set<INeuron> getLayer(long neuronCount){
        Set<INeuron> layerSet = new HashSet<>();

        INeuron defaultNeuron = new BiasNeuron(1);
        layerSet.add(defaultNeuron);

        for (int i = 0; i < neuronCount; i++) {
            INeuron neuron = new Neuron();
            layerSet.add(neuron);
        }

        return layerSet;
    }

    private void genereteSynapses(){
        Iterator<Long> iterator = this.brainMap.keySet().iterator();
        Set<INeuron> current = this.brainMap.get(iterator.next());
        Set<INeuron> prev;

        while (iterator.hasNext()){
            prev = current;
            current = this.brainMap.get(iterator.next());

            connectNeurons(prev, current);
        }
    }

    private void connectNeurons(Set<INeuron> input, Set<INeuron> output){
        for (INeuron neuron : input){
            connectNeuron(neuron, output);
        }
    }

    private void connectNeuron(INeuron input, Set<INeuron> output){
        for (INeuron neuron : output){
            if(neuron instanceof BiasNeuron) continue;
            Synapse synapse = Synapse.builder()
                        .neuronIn(input)
                        .neuronOut(neuron)
                        .weight(0.0)
                        .build();

            this.synapseSet.add(synapse);
            input.addOutSynapse(synapse);
            neuron.addInSynapse(synapse);
        }
    }

    public Set<INeuron> getInputLayer(){
        return brainMap.get(0L);
    }

    public Set<INeuron> getOutputLayer(){
        long size = this.brainMap.size();
        return this.brainMap.get(size - 1);
    }

    private Set<INeuron> convertToBrainSet(Map<Long, Set<INeuron>> brainMap){
        Set<INeuron> all = new HashSet<>();

        for (Map.Entry<Long, Set<INeuron>> entry : brainMap.entrySet()){
            all.addAll(entry.getValue());
        }

        return all;
    }
}
