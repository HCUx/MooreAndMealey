package hcanu.mymooremachine.Moore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stava on 11.11.2017.
 */

public class MooreStates {
    List<MooreEdge> mooreEdgeList;
    String stateName;
    String output;

    public MooreStates(String stateName, String output) {
        this.stateName = stateName;
        this.output = output;
        mooreEdgeList = new ArrayList<MooreEdge>();
    }

    public MooreStates EdgePick(String value){
        MooreStates mooreStates = null;

        for(int i = 0; i < mooreEdgeList.size(); i++){
            if(value.equalsIgnoreCase(mooreEdgeList.get(i).getEdgeCondition())){
                mooreStates = mooreEdgeList.get(i).getTargetState();
                break;
            }
        }

        return mooreStates;
    }

    public List<MooreEdge> getMooreEdgeList() {
        return mooreEdgeList;
    }

    public void setMooreEdgeList(MooreEdge mooreEdge) {
        mooreEdgeList.add(mooreEdge);
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
