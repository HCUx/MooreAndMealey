package hcanu.mymooremachine.Mealey;

/**
 * Created by stava on 12.11.2017.
 */

public class MealeyEdge {
    MealeyStates TargetState;
    String edgeCondition;
    String output;

    public MealeyEdge(MealeyStates targetState, String edgeCondition,String output) {
        TargetState = targetState;
        this.edgeCondition = edgeCondition;
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public MealeyStates getTargetState() {
        return TargetState;
    }

    public void setTargetState(MealeyStates targetState) {
        TargetState = targetState;
    }

    public String getEdgeCondition() {
        return edgeCondition;
    }

    public void setEdgeCondition(String edgeCondition) {
        this.edgeCondition = edgeCondition;
    }
}
