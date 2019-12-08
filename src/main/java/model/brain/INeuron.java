package model.brain;

import static java.lang.Math.exp;

public interface INeuron {

    double N = 0.1d;
    double a = 0.7d;

    void addInSynapse(Synapse synapse);
    void addOutSynapse(Synapse synapse);
    double getValue();
    void setValue(double value);
    double updateValue();
    void correctWeight(double e);

    default double relu(double x){
        if(x > 1) return 1 + 0.01*(x - 1);
        if(x < 0) return 0.01*x;
        return x;
    }

    default double derivativeRelu(double x){
        if(x > 1) return 0.01d;
        if(x < 0) return 0.01d;
        return 1;
    }

    default double sigma(double x){
        return 1/(1 + exp(-x));
    }

    default double derivativeSigma(double x){
        return x * (x - 1);
    }
}
