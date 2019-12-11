package model.brain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
@Builder
public class Synapse {
    private INeuron neuronOut;
    private INeuron neuronIn;
    private double weight;

    @Override
    public String toString() {
        return "Synapse{" +
                "weight=" + weight +
                '}';
    }
}
