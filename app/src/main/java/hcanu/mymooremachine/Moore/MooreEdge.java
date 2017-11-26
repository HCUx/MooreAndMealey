package hcanu.mymooremachine.Moore;

/**
 * Created by stava on 11.11.2017.
 */

public class MooreEdge {
    MooreStates TargetState;
    String edgeCondition;

    public MooreEdge(MooreStates targetState, String edgeCondition) {
        TargetState = targetState;
        this.edgeCondition = edgeCondition;
    }

    public MooreStates getTargetState() {
        return TargetState;
    }

    public void setTargetState(MooreStates targetState) {
        TargetState = targetState;
    }

    public String getEdgeCondition() {
        return edgeCondition;
    }

    public void setEdgeCondition(String edgeCondition) {
        this.edgeCondition = edgeCondition;
    }
}
