package model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Synapse {
    private long id;
    private long neuronOutId;
    private long neuronInId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Synapse)) return false;
        Synapse synapse = (Synapse) o;
        return getId() == synapse.getId() &&
                getNeuronOutId() == synapse.getNeuronOutId() &&
                getNeuronInId() == synapse.getNeuronInId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getNeuronOutId(), getNeuronInId());
    }
}
