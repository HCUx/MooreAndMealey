package hcanu.mymooremachine.Moore;

import android.widget.EditText;

/**
 * Created by stava on 11.11.2017.
 */

public class MooreCreateStateEdge {
    String[] QData,TData,SData;
    MooreStates[] mooreStatesArray;
    EditText[][] editTexts;
    EditText[] texts;

    public MooreCreateStateEdge(String[] QData, String[] SData, String[] TData, EditText[][] editTexts, EditText[] texts) {
        this.QData = QData;
        this.TData = TData;
        this.SData = SData;
        this.editTexts = editTexts;
        this.texts = texts;

        createStatesAndEdges();
    }

    private void createStatesAndEdges() {
        mooreStatesArray = new MooreStates[QData.length];

        for (int i = 0 ; i < QData.length ; i++){
            mooreStatesArray[i] = new MooreStates(QData[i],texts[i].getText().toString());
        }

        for(int i=0;i<QData.length;i++){
            for (int k=0;k<SData.length;k++){
                MooreStates tempState = null;
                tempState = findState(editTexts[i][k].getText().toString());
                MooreEdge mooreEdge = new MooreEdge(tempState,SData[k]);
                mooreStatesArray[i].setMooreEdgeList(mooreEdge);
            }
        }
    }

    private MooreStates findState(String stateName) {
        MooreStates state = null;
        for (int i = 0; i< mooreStatesArray.length; i++){
            if(stateName.equalsIgnoreCase(mooreStatesArray[i].getStateName())){
                state = mooreStatesArray[i];
                break;
            }
        }
        return state;
    }

    public MooreStates[] getMooreStatesArray() {
        return mooreStatesArray;
    }
}
