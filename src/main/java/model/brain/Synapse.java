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
    @Builder.Default private double weight = 0.5d;
}
