package model.brain;

public interface INeuron {

    double N = 0.9d;

    void addInSynapse(Synapse synapse);
    void addOutSynapse(Synapse synapse);
    double getValue();
    void setValue(double value);
    void correctWeight(double error);

}
