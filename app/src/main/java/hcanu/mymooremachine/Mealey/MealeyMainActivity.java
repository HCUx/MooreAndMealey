package hcanu.mymooremachine.Mealey;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcanu.mymooremachine.Control;
import hcanu.mymooremachine.R;

/**
 * Created by stava on 12.11.2017.
 */

public class MealeyMainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout qlayout,slayout,tlayout,listheadlayout,step2listlayout,step2btnlayout,endsteplinearlayout,outputStatesLinear,outputLinear;
    Button step1to2,step2to3,backforstep1btn,step4toEnd,finishbtn;
    EditText QEditText,SEditText,TEditText,EndInputET;
    String QDataFull,SDataFull,TDataFull,EndInput;
    ListView qlistview,slistview,tlistview;
    ArrayAdapter<String> qadapter,sadapter,tadapter;

    TextView outputTV,outputStatesTv;

    String[] QData,SData,TData;
    TextView[] tableRowTextViews,tableColumbTextViews;
    EditText[][] tableInputEditTexts;
    TableRow[] tableRows;

    TableLayout tableLayout,tableLayoutStep4;

    ScrollView scrollView;

    MealeyStates[] states;
    List<String> outputEndList,outStateList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealey);
        initViewObject();
        visibilityForLayout(1);
        visibilityForLayout(4);
        visibilityForLayout(6);
        visibilityForLayout(8);
        //mealeystep1fill();
    }


    /*
    view nesnelerini tanıttığımız method
     */
    private void initViewObject() {
        qlayout = (LinearLayout) findViewById(R.id.mealeyqlayout);
        slayout = (LinearLayout) findViewById(R.id.mealeyslayout);
        tlayout = (LinearLayout) findViewById(R.id.mealeytlayout);
        listheadlayout = (LinearLayout) findViewById(R.id.mealeylistheadlayout);
        step2listlayout = (LinearLayout) findViewById(R.id.mealeystep2listlayout);
        step2btnlayout = (LinearLayout) findViewById(R.id.mealeystep2btnlayout);
        endsteplinearlayout = (LinearLayout)findViewById(R.id.mealeyendsteplinearlayout);
        outputLinear = (LinearLayout) findViewById(R.id.outputLinearMealey);
        outputStatesLinear = (LinearLayout) findViewById(R.id.outputStatesLinearMealey);

        outputTV = (TextView) findViewById(R.id.outputMealeyTV);
        outputStatesTv = (TextView) findViewById(R.id.outputStatesMealeyTV);

        QEditText = (EditText) findViewById(R.id.mealeyinputQEditText);
        SEditText = (EditText) findViewById(R.id.mealeyinputSEditText);
        TEditText = (EditText) findViewById(R.id.mealeyinputTEditText);
        EndInputET = (EditText) findViewById(R.id.mealeyendInputET);

        step1to2 = (Button) findViewById(R.id.mealeystep1to2);
        step2to3 = (Button) findViewById(R.id.mealeystep2to3);
        backforstep1btn = (Button) findViewById(R.id.mealeybackstep1);
        step4toEnd = (Button) findViewById(R.id.mealeystep4toEnd);
        finishbtn = (Button) findViewById(R.id.mealeyfinishbtn);

        qlistview = (ListView) findViewById(R.id.mealeyqlistview);
        slistview = (ListView) findViewById(R.id.mealeyslistview);
        tlistview = (ListView) findViewById(R.id.mealeytlistview);

        tableLayout = (TableLayout) findViewById(R.id.mealeytableLayout);

        scrollView = (ScrollView) findViewById(R.id.mealeyscrollvertical);

        step1to2.setOnClickListener(this);
        step2to3.setOnClickListener(this);
        backforstep1btn.setOnClickListener(this);
        step4toEnd.setOnClickListener(this);
        finishbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.mealeystep1to2){
            if(getFirstInputData()){  //input dataları true dönerse ekrana girilen verileri göster.
                showFirstInputData();    //input datalarını göster
                InputMethodManager imm = (InputMethodManager)getSystemService( Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(TEditText.getWindowToken(), 0);
            }
        }else if (i == R.id.mealeybackstep1){
            backStepOne();
        }else if (i == R.id.mealeystep2to3){
            showInputForTransitionTable();
            createStep3Page();
            //mealeystep3fill();
        }else if(i == R.id.mealeystep4toEnd){
            if(Control.MealeyEdgeAndOutputControl(tableInputEditTexts,QData,TData,SData.length)){
                MealeyCreateStateEdge c = new MealeyCreateStateEdge(QData,SData,TData,tableInputEditTexts);
                states = c.getMealeyStatesArray();
                ShowEndStep();
            }else
                Toast.makeText(getApplicationContext(),"Input Değerleri Hatalı Küme Dışı eleman Kullanmayın",Toast.LENGTH_LONG).show();

        }
        else if (i == R.id.mealeyfinishbtn){
            getEndData();
            if (Control.InputC(EndInput,SData))
                showOutput();
            else
                Toast.makeText(getApplicationContext(),"Input Değerleri Hatalı Küme Dışı eleman Kullanmayın",Toast.LENGTH_LONG).show();
        }
    }

    private void mealeystep3fill() {
       /* tableInputEditTexts[0][0].setText("Q2"); tableInputEditTexts[0][1].setText("0"); tableInputEditTexts[0][2].setText("Q2"); tableInputEditTexts[0][3].setText("1");
        tableInputEditTexts[1][0].setText("Q2"); tableInputEditTexts[1][1].setText("1"); tableInputEditTexts[1][2].setText("Q0"); tableInputEditTexts[1][3].setText("1");
        tableInputEditTexts[2][0].setText("Q3"); tableInputEditTexts[2][1].setText("0"); tableInputEditTexts[2][2].setText("Q1"); tableInputEditTexts[2][3].setText("1");
        tableInputEditTexts[3][0].setText("Q3"); tableInputEditTexts[3][1].setText("1"); tableInputEditTexts[3][2].setText("Q1"); tableInputEditTexts[3][3].setText("1");
*/
    }

    private void mealeystep1fill() {
      /*  QEditText.setText("Q0,Q1,Q2,Q3");
        SEditText.setText("A,B");
        TEditText.setText("0,1");*/
    }


    private void createStep3Page() {
        TableRow.LayoutParams tbrowlaparams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams textviewlayoutparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams headerParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
        headerParams.span = 2;

        tableRows = new TableRow[QData.length+2];
        tableInputEditTexts = new EditText[QData.length][SData.length*2];

        TextView[] textViews = new TextView[2];
        textViews[0] = new TextView(getApplicationContext());
        textViews[1] = new TextView(getApplicationContext());

        textViews[0].setText("Old State");
        textViews[0].setTextColor(getResources().getColor(R.color.colorAccent));
        textViews[1].setText("Mealey");
        textViews[1].setTextColor(getResources().getColor(R.color.colorAccent));

        textViews[0].setLayoutParams(textviewlayoutparams);
        textViews[1].setLayoutParams(textviewlayoutparams);

        textViews[0].setPadding(20,20,20,20);
        textViews[1].setPadding(20,20,20,20);

        tableRowTextViews = new TextView[QData.length];
        tableColumbTextViews = new TextView[SData.length];

        tableRows[0] = new TableRow(getApplicationContext());
        tableRows[0].setLayoutParams(tbrowlaparams);
        tableRows[0].addView(textViews[1]);

        for (int i = 0; i<SData.length;i++){
            tableColumbTextViews[i] = new TextView(getApplicationContext());
            tableColumbTextViews[i].setText("After "+SData[i]);
            tableColumbTextViews[i].setLayoutParams(headerParams);
            tableColumbTextViews[i].setTextColor(getResources().getColor(R.color.colorAccent));
            ((TableRow.LayoutParams)tableColumbTextViews[i].getLayoutParams()).gravity = Gravity.CENTER;
            tableColumbTextViews[i].setPadding(20,20,20,20);
            tableRows[0].addView(tableColumbTextViews[i]);
        }

        tableRows[1] = new TableRow(getApplicationContext());
        tableRows[1].setLayoutParams(tbrowlaparams);
        tableRows[1].addView(textViews[0]);

        TextView[] headerTV = new TextView[SData.length*2];

        for(int i=0;i<(SData.length*2);i++){
            headerTV[i] = new TextView(getApplicationContext());
            headerTV[i].setLayoutParams(textviewlayoutparams);
            headerTV[i].setPadding(20,20,20,20);
            if(i%2 == 0){
                headerTV[i].setText("New State");
            }else {
                headerTV[i].setText("Output");
            }
            headerTV[i].setTextColor(getResources().getColor(R.color.colorAccent));
            tableRows[1].addView(headerTV[i]);
        }

        for (int i=0;i<QData.length;i++){

            tableRowTextViews[i] = new TextView(getApplicationContext());
            tableRowTextViews[i].setText(QData[i]);
            tableRowTextViews[i].setLayoutParams(textviewlayoutparams);
            tableRowTextViews[i].setPadding(20,20,20,20);
            tableRowTextViews[i].setTextColor(getResources().getColor(R.color.colorAccent));

            tableRows[i+2] = new TableRow(getApplicationContext());
            tableRows[i+2].setLayoutParams(tbrowlaparams);
            tableRows[i+2].addView(tableRowTextViews[i]);

            for (int k=0 ; k<(SData.length*2); k++){
                tableInputEditTexts[i][k] = new EditText(getApplicationContext());
                tableInputEditTexts[i][k].setLayoutParams(textviewlayoutparams);
                tableInputEditTexts[i][k].setMinimumWidth(200);
                tableInputEditTexts[i][k].setPadding(20,20,20,20);
                tableInputEditTexts[i][k].setTextColor(getResources().getColor(R.color.whitecolor));
                tableRows[i+2].addView(tableInputEditTexts[i][k]);
            }
        }

        for (int i = 0;i<(QData.length+2);i++){
            tableLayout.addView(tableRows[i]);
        }
    }

    /*
    Girilen verileri virgül ile ayırmak ve string dizilerine atmak için yazılmış method
    Input boş gelirse false dönderir.
     */
    private boolean getFirstInputData() {
        QDataFull = QEditText.getText().toString();
        SDataFull = SEditText.getText().toString();
        TDataFull = TEditText.getText().toString();

        QDataFull =  QDataFull.replaceAll(" ","");
        SDataFull =  SDataFull.replaceAll(" ","");
        TDataFull =  TDataFull.replaceAll(" ","");

        if(!QDataFull.isEmpty() && !SDataFull.isEmpty() && !TDataFull.isEmpty()){
            QData = QDataFull.split(",");
            SData = SDataFull.split(",");
            TData = TDataFull.split(",");
            return true;
        }else {
            Toast.makeText(getApplicationContext(),"Boş İnputları Doldurunuz",Toast.LENGTH_LONG).show();
            return false;
        }
    }


    /*
    ana ekranda alınan ilk verileri doğrulama amaçlı ekran
     */
    private void showFirstInputData() {
        visibilityForLayout(2);
        visibilityForLayout(3);
        qadapter = new ArrayAdapter<String>(getBaseContext(),R.layout.listmodel1,R.id.checkTextView,QData);
        sadapter = new ArrayAdapter<String>(getBaseContext(),R.layout.listmodel1,R.id.checkTextView,SData);
        tadapter = new ArrayAdapter<String>(getBaseContext(),R.layout.listmodel1,R.id.checkTextView,TData);

        qlistview.setAdapter(qadapter);
        slistview.setAdapter(sadapter);
        tlistview.setAdapter(tadapter);
    }

    /*
    ikinci aşamadan birinci aşmaya geçerken verileri temizleyip gerekli layoutları görünür hale getiriyoruz
     */
    private void backStepOne() {
        mainDataFree();
        visibilityForLayout(4);
        visibilityForLayout(1);
    }

    /*
    dataları boşaltmak istersek
     */
    private void mainDataFree() {
        QData = null; SData = null; TData = null;
        QDataFull = null; SDataFull = null; TDataFull = null;
    }

    /*
    parametreye göre bazı layoutları topluca görünür veya gizli yapan method
     */
    private void visibilityForLayout(int i) {
        switch (i){
            case 1:
                qlayout.setVisibility(View.VISIBLE);
                slayout.setVisibility(View.VISIBLE);
                tlayout.setVisibility(View.VISIBLE);
                step1to2.setVisibility(View.VISIBLE);break;
            case 2:
                qlayout.setVisibility(View.GONE);
                slayout.setVisibility(View.GONE);
                tlayout.setVisibility(View.GONE);
                step1to2.setVisibility(View.GONE);break;
            case 3:
                listheadlayout.setVisibility(View.VISIBLE);
                step2listlayout.setVisibility(View.VISIBLE);
                step2btnlayout.setVisibility(View.VISIBLE);break;
            case 4:
                listheadlayout.setVisibility(View.GONE);
                step2listlayout.setVisibility(View.GONE);
                step2btnlayout.setVisibility(View.GONE);break;
            case 5:
                scrollView.setVisibility(View.VISIBLE);
                step4toEnd.setVisibility(View.VISIBLE);break;
            case 6:
                scrollView.setVisibility(View.GONE);
                step4toEnd.setVisibility(View.GONE);break;
            case 7:
                endsteplinearlayout.setVisibility(View.VISIBLE);
                outputLinear.setVisibility(View.VISIBLE);
                outputStatesLinear.setVisibility(View.VISIBLE);
                finishbtn.setVisibility(View.VISIBLE);
                break;
            case 8:
                endsteplinearlayout.setVisibility(View.GONE);
                outputLinear.setVisibility(View.GONE);
                outputStatesLinear.setVisibility(View.GONE);
                finishbtn.setVisibility(View.GONE);
                break;
        }
    }

    /*
    table visibility methodu
     */
    private void showInputForTransitionTable() {
        visibilityForLayout(4);
        visibilityForLayout(5);
    }

    /*
    son adımdaki view nesnelerini aç
     */
    private void ShowEndStep() {
        visibilityForLayout(6);
        visibilityForLayout(7);
    }

    /*
    son adımdaki inputu al
     */
    private void getEndData() {
        EndInput = EndInputET.getText().toString();
    }

    private void showOutput() {

        outputEndList = new ArrayList<String>();
        outStateList = new ArrayList<String>();

        MealeyStates tempMealeyStates = states[0];

        for (int i = 0;i<EndInput.length();i++){

            for (int k = 0;k < SData.length;k++){
                String a = EndInput.substring(i,i+1);
                if (tempMealeyStates.getMealeyEdgeList().get(k).getEdgeCondition().equalsIgnoreCase(EndInput.substring(i,i+1))){
                    outputEndList.add(tempMealeyStates.getMealeyEdgeList().get(k).getOutput());
                    outStateList.add(tempMealeyStates.getStateName());
                    tempMealeyStates = tempMealeyStates.getMealeyEdgeList().get(k).getTargetState();
                    break;
                }

            }

        }

        outputStatesTv.setText("");
        for (String text : outStateList){
            outputStatesTv.setText(outputStatesTv.getText()+text+" ");
        }

        outputTV.setText("");
        for (String text : outputEndList){
            outputTV.setText(outputTV.getText()+text+" ");
        }
    }
}
