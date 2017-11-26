package hcanu.mymooremachine;

import android.widget.EditText;

/**
 * Created by stava on 14.11.2017.
 */

public class Control {
    //EditText[][] MooreEdgeC , MooreOutputC ,MealeyEdgeAndOutputC;
    //String[] QDatasArray,SDatasArray,TDatasArray;
    //String InputC;

    public static boolean MooreEdgeControl(EditText[][] mooreEdgeC , String[] qDatasArray,int sDataLength){

        boolean isRightInputs = true;
        for (int i=0;i< qDatasArray.length ;i++){
            for (int j=0;j<sDataLength;j++){

                for (String aQDatasArray : qDatasArray) {
                    if (!mooreEdgeC[i][j].getText().toString().equalsIgnoreCase(aQDatasArray))
                        isRightInputs = false;
                    else{
                        isRightInputs = true;
                        break;
                    }
                }

                if (!isRightInputs){
                    return false;
                }
            }
        }
        return isRightInputs;
    }

    public static boolean MooreOutputControl(EditText[] mooreOutputC , String[] tDatasArray){
        boolean isRightInputs = true;
        for (int i = 0;i<mooreOutputC.length;i++){
            for (int j = 0;j<tDatasArray.length;j++){
                if (!mooreOutputC[i].getText().toString().equalsIgnoreCase(tDatasArray[j])){
                    isRightInputs = false;
                }
                else {
                    isRightInputs = true;
                    break;
                }
            }
            if (!isRightInputs)
                return false;
        }
        return isRightInputs;
    }

    public static boolean MealeyEdgeAndOutputControl(EditText[][] mealeyEdgeAndOutputC , String[] qDatasArray,String[] tDatasArray,int sDataLength){

        boolean isRightQdata = true;
        for (int i=0;i< qDatasArray.length ;i++){
            for (int j=0;j<sDataLength*2;j=j+2){

                for (String aQDatasArray : qDatasArray) {
                    if (!mealeyEdgeAndOutputC[i][j].getText().toString().equalsIgnoreCase(aQDatasArray))
                        isRightQdata = false;
                    else{
                        isRightQdata = true;
                        break;
                    }
                }

                if (!isRightQdata){
                    return false;
                }
            }
        }

        boolean isRightTdata = true;
        for (int i=0;i< qDatasArray.length ;i++){
            for (int j=1;j<sDataLength*2;j=j+2){

                for (String atDatasArray : tDatasArray) {
                    if (!mealeyEdgeAndOutputC[i][j].getText().toString().equalsIgnoreCase(atDatasArray))
                        isRightTdata = false;
                    else{
                        isRightTdata = true;
                        break;
                    }
                }

                if (!isRightTdata){
                    return false;
                }
            }
        }
        return true;

    }


    public static boolean InputC(String input , String[] sDatasArray){
        boolean isRightInputs = true;
        for (int i=0;i<input.length();i++){
            for(int j=0;j<sDatasArray.length;j++){
                if (!input.substring(i,i+1).equalsIgnoreCase(sDatasArray[j]))
                    isRightInputs = false;
                else {
                    isRightInputs = true;
                    break;
                }
            }
            if (!isRightInputs)
                return false;
        }
        return isRightInputs;
    }
}
