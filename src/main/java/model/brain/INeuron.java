package model.brain;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public interface INeuron {

    double N = 0.1d;

    void addInSynapse(Synapse synapse);
    void addOutSynapse(Synapse synapse);
    double getValue();
    void setValue(double value);
    void correctWeight(double error, double oValue);

    default double proiz(double value){
        return exp(-value) / pow(exp(-value) + 1, 2);
    }

    default double proiz2(double value){
        return value * (1 - value);
    }
}
