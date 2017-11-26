package hcanu.mymooremachine.Mealey;

import android.widget.EditText;

/**
 * Created by stava on 12.11.2017.
 */

public class MealeyCreateStateEdge {
    String[] QData,TData,SData;
    MealeyStates[] mealeyStatesArray;
    EditText[][] editTexts;

    public MealeyCreateStateEdge(String[] QData, String[] SData, String[] TData, EditText[][] editTexts) {
        this.QData = QData;
        this.TData = TData;
        this.SData = SData;
        this.editTexts = editTexts;

        createStatesAndEdges();
    }

    private void createStatesAndEdges() {
        mealeyStatesArray = new MealeyStates[QData.length];

        for (int i = 0 ; i < QData.length ; i++){
            mealeyStatesArray[i] = new MealeyStates(QData[i]);
        }

        for(int i=0;i<QData.length;i++){
            for (int k=0;k<SData.length ;k++){
                MealeyStates tempState = null;
                tempState = findState(editTexts[i][k*2].getText().toString());
                MealeyEdge mealeyEdge = new MealeyEdge(tempState,SData[k],editTexts[i][(k*2)+1].getText().toString());
                mealeyStatesArray[i].setMealeyEdgeList(mealeyEdge);
            }
        }
    }

    private MealeyStates findState(String stateName) {
        MealeyStates state = null;
        for (int i = 0; i< mealeyStatesArray.length; i++){
            if(stateName.equalsIgnoreCase(mealeyStatesArray[i].getStateName())){
                state = mealeyStatesArray[i];
                break;
            }
        }
        return state;
    }

    public MealeyStates[] getMealeyStatesArray() {
        return mealeyStatesArray;
    }
}
