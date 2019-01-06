package model.brain;

public interface INeuron {

    void addInSynapse(Synapse synapse);
    void addOutSynapse(Synapse synapse);
    double getValue();

}
