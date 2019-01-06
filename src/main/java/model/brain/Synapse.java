package model.brain;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Synapse {
    private Neuron neuronOut;
    private Neuron neuronIn;
    private double weight;
}
