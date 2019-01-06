package model.coach;

import lombok.Builder;
import model.brain.Brain;

@Builder
public class Coach {

    private Brain brain;
    private TrainingSet trainingSet;

    public Coach(Brain brain) {
        this.brain = brain;
    }

    public void init(){

    }
}
