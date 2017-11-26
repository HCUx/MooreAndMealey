package hcanu.mymooremachine.Mealey;

import java.util.ArrayList;
import java.util.List;

import hcanu.mymooremachine.Moore.MooreEdge;
import hcanu.mymooremachine.Moore.MooreStates;

/**
 * Created by stava on 12.11.2017.
 */

public class MealeyStates {
    List<MealeyEdge> mealeyEdgeList;
    String stateName;

    public MealeyStates(String stateName) {
        this.stateName = stateName;
        mealeyEdgeList = new ArrayList<MealeyEdge>();
    }

    public MealeyStates EdgePick(String value){
        MealeyStates mealeyStates = null;

        for(int i = 0; i < mealeyEdgeList.size(); i++){
            if(value.equalsIgnoreCase(mealeyEdgeList.get(i).getEdgeCondition())){
                mealeyStates = mealeyEdgeList.get(i).getTargetState();
                break;
            }
        }

        return mealeyStates;
    }

    public List<MealeyEdge> getMealeyEdgeList() {
        return mealeyEdgeList;
    }

    public void setMealeyEdgeList(MealeyEdge mealeyEdge) {
        mealeyEdgeList.add(mealeyEdge);
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
