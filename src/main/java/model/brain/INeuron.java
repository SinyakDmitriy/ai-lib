package model.brain;

public interface INeuron {

    double N = 0.1d;
    double a = 0.7d;

    void addInSynapse(Synapse synapse);
    void addOutSynapse(Synapse synapse);
    double getValue();
    void setValue(double value);
    void correctWeight(double oDelta);
}
