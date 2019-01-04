package model;

import lombok.Getter;

import java.util.*;

@Getter
public class Brain {
    private Map<Long, Set<Neuron>> brainMap;
    private Set<Neuron> all;

    private long inputs;
    private long outputs;
    private long layers;

    private long currentId;

    public Brain(long inputs, long outputs, long layers) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.layers = layers;

        this.brainMap = genereteBrainMap();
        this.all = convertToBrainSet(brainMap);
        genereteSynapses();
        genereteK();
    }

    /**
     *  generete unique id
     * */
    private long genereteId(){
        return currentId++;
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
            Neuron neuron = new Neuron(genereteId());
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
                    .id(genereteId())
                    .neuronInId(input.getId())
                    .neuronOutId(neuron.getId())
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

    public double calculate(){
        for (Map.Entry<Long, Set<Neuron>> entry : brainMap.entrySet()){
            setValue(entry.getValue());
        }

        Set<Neuron> outputLayer = getOutputLayer();
        for (Neuron neuron : outputLayer) {
            System.out.println(neuron.getValue());
            return neuron.getValue();
        }

        return 1000;
    }

    private void setValue(Set<Neuron> layer){
        for (Neuron neuron : layer){
            double value = neuron.getValue();
            calculateNextLayerNeuron(value, neuron.getOut());
        }
    }

    private void calculateNextLayerNeuron(double value, Set<Synapse> synapses){
        for (Synapse synapse : synapses){
            Neuron neuron = findById(synapse.getNeuronOutId());
            neuron.calculateValue(synapse.getId(), value);
        }
    }

    private Neuron findById(long id){
        for (Neuron neuron : all){
            if(neuron.getId() == id) return neuron;
        }
        return null;
    }

    private void genereteK(){
        for (Neuron neuron : all){
            neuron.initK();
        }
    }
}
