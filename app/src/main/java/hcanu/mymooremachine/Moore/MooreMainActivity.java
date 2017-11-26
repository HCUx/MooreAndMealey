package hcanu.mymooremachine.Moore;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MooreMainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout qlayout,slayout,tlayout,listheadlayout,step2listlayout,step2btnlayout,endsteplinearlayout,outputLinear,statesLinear;

    Button step1to2,step2to3,backforstep1btn,step3to4,step4toEnd,finishbtn;
    EditText QEditText,SEditText,TEditText,EndInputET;
    String QDataFull,SDataFull,TDataFull,EndInput;
    ListView qlistview,slistview,tlistview;
    ArrayAdapter<String> qadapter,sadapter,tadapter;
    TextView outputStates,output;

    String[] QData,SData,TData;
    TextView[] tableRowTextViews,tableColumbTextViews;
    EditText[][] tableInputEditTexts;
    EditText[] editTextsForStep4;
    TableRow[] tableRows,tableRowsStep4;

    TableLayout tableLayout,tableLayoutStep4;

    ScrollView scrollView;
    ScrollView scrollViewstep4;

    MooreStates[] states;
    List<String> outputEndList,outputStateList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moore);
        initViewObject();
        visibilityForLayout(1);
        visibilityForLayout(4);
        visibilityForLayout(6);
        visibilityForLayout(8);
        visibilityForLayout(10);
        //moorestep1fill();
    }


    /*
    view nesnelerini tanıttığımız method
     */
    private void initViewObject() {
        qlayout = (LinearLayout) findViewById(R.id.qlayout);
        slayout = (LinearLayout) findViewById(R.id.slayout);
        tlayout = (LinearLayout) findViewById(R.id.tlayout);
        listheadlayout = (LinearLayout) findViewById(R.id.listheadlayout);
        step2listlayout = (LinearLayout) findViewById(R.id.step2listlayout);
        step2btnlayout = (LinearLayout) findViewById(R.id.step2btnlayout);
        endsteplinearlayout = (LinearLayout)findViewById(R.id.endsteplinearlayout);
        outputLinear = (LinearLayout) findViewById(R.id.outputLinearLayout);
        statesLinear = (LinearLayout) findViewById(R.id.outputStatesLinearLayout);

        outputStates = (TextView)findViewById(R.id.outputStatesTV);
        output = (TextView) findViewById(R.id.outputTV);

        QEditText = (EditText) findViewById(R.id.inputQEditText);
        SEditText = (EditText) findViewById(R.id.inputSEditText);
        TEditText = (EditText) findViewById(R.id.inputTEditText);
        EndInputET = (EditText) findViewById(R.id.endInputET);

        step1to2 = (Button) findViewById(R.id.step1to2);
        step2to3 = (Button) findViewById(R.id.step2to3);
        backforstep1btn = (Button) findViewById(R.id.backstep1);
        step3to4 = (Button) findViewById(R.id.step3to4);
        step4toEnd = (Button) findViewById(R.id.step4toEnd);
        finishbtn = (Button) findViewById(R.id.finishbtn);

        qlistview = (ListView) findViewById(R.id.qlistview);
        slistview = (ListView) findViewById(R.id.slistview);
        tlistview = (ListView) findViewById(R.id.tlistview);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableLayoutStep4 = (TableLayout)findViewById(R.id.tableLayoutstep4);

        scrollView = (ScrollView) findViewById(R.id.scrollvertical);
        scrollViewstep4 = (ScrollView) findViewById(R.id.scrollverticalstep4);

        step1to2.setOnClickListener(this);
        step2to3.setOnClickListener(this);
        backforstep1btn.setOnClickListener(this);
        step3to4.setOnClickListener(this);
        step4toEnd.setOnClickListener(this);
        finishbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.step1to2){
            if(getFirstInputData()){  //input dataları true dönerse ekrana girilen verileri göster.
                showFirstInputData();    //input datalarını göster
                InputMethodManager imm = (InputMethodManager)getSystemService( Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(TEditText.getWindowToken(), 0);
            }
        }else if (i == R.id.backstep1){
            backStepOne();
        }else if (i == R.id.step2to3){
            showInputForTransitionTable();
            createStep3Page();
            //moorestep3fill();
        }else if (i == R.id.step3to4){
            if (Control.MooreEdgeControl(tableInputEditTexts,QData,SData.length)) {
                createStep4Page();
                //moorestep4fill();
            }else
                Toast.makeText(getApplicationContext(),"Input Değerleri Hatalı \n Küme Dışı eleman Kullanmayın",Toast.LENGTH_LONG).show();
        }else if(i == R.id.step4toEnd){
            if (Control.MooreOutputControl(editTextsForStep4,TData)) {
                MooreCreateStateEdge c = new MooreCreateStateEdge(QData, SData, TData, tableInputEditTexts, editTextsForStep4);
                states = c.getMooreStatesArray();
                ShowEndStep();
            } else
                Toast.makeText(getApplicationContext(),"Input Değerleri Hatalı Küme Dışı eleman Kullanmayın",Toast.LENGTH_LONG).show();
        }
        else if (i == R.id.finishbtn){
            getEndData();
            if (Control.InputC(EndInput,SData))
                showOutput();
            else
                Toast.makeText(getApplicationContext(),"Input Değerleri Hatalı Küme Dışı eleman Kullanmayın",Toast.LENGTH_LONG).show();
        }
    }

    private void moorestep4fill() {
        //editTextsForStep4[0].setText("0"); editTextsForStep4[1].setText("0"); editTextsForStep4[2].setText("0"); editTextsForStep4[3].setText("1");
    }

    private void moorestep3fill() {
        /*tableInputEditTexts[0][0].setText("Q1"); tableInputEditTexts[0][1].setText("Q0");
        tableInputEditTexts[1][0].setText("Q2"); tableInputEditTexts[1][1].setText("Q0");
        tableInputEditTexts[2][0].setText("Q2"); tableInputEditTexts[2][1].setText("Q3");
        tableInputEditTexts[3][0].setText("Q1"); tableInputEditTexts[3][1].setText("Q0");*/
    }

    private void moorestep1fill() {
       /* QEditText.setText("Q0,Q1,Q2,Q3");
        SEditText.setText("A,B");
        TEditText.setText("0,1");*/
    }



    private void createStep3Page() {
        TableRow.LayoutParams layoutParamsTR = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams headerParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);

        tableRows = new TableRow[QData.length+1];

        tableInputEditTexts = new EditText[QData.length][SData.length];

        TextView oldstateTV = new TextView(MooreMainActivity.this);
        oldstateTV.setText("Old State      ");
        oldstateTV.setPadding(20,20,20,20);

        tableRowTextViews = new TextView[QData.length];
        tableColumbTextViews = new TextView[SData.length];

        tableRows[0] = new TableRow(MooreMainActivity.this);
        tableRows[0].setLayoutParams(layoutParamsTR);
        tableRows[0].addView(oldstateTV);


        for (int i = 0; i<SData.length;i++){
            tableColumbTextViews[i] = new TextView(MooreMainActivity.this);
            tableColumbTextViews[i].setText(SData[i]);
            tableColumbTextViews[i].setLayoutParams(headerParams);
            ((TableRow.LayoutParams)tableColumbTextViews[i].getLayoutParams()).gravity = Gravity.CENTER;
            tableColumbTextViews[i].setPadding(20,20,20,20);
            tableRows[0].addView(tableColumbTextViews[i]);
        }

        for (int i=0;i<QData.length;i++){

            tableRowTextViews[i] = new TextView(MooreMainActivity.this);
            tableRowTextViews[i].setText(QData[i]);
            tableRowTextViews[i].setLayoutParams(layoutParamsTR);
            tableRowTextViews[i].setPadding(20,20,20,20);

            tableRows[i+1] = new TableRow(MooreMainActivity.this);
            tableRows[i+1].setLayoutParams(layoutParamsTR);
            tableRows[i+1].addView(tableRowTextViews[i]);

            for (int k=0 ; k<SData.length ; k++){
                tableInputEditTexts[i][k] = new EditText(MooreMainActivity.this);
                tableInputEditTexts[i][k].setLayoutParams(layoutParamsTR);
                tableInputEditTexts[i][k].setPadding(20,20,20,20);
                tableInputEditTexts[i][k].setMinimumWidth(200);
                tableRows[i+1].addView(tableInputEditTexts[i][k]);
            }
        }

        for (int i = 0;i<QData.length+1;i++){
            tableLayout.addView(tableRows[i]);
        }
    }


    private void createStep4Page() {
        visibilityForLayout(6);
        visibilityForLayout(7);

        TableRow.LayoutParams layoutParamsTR = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        tableRowsStep4 = new TableRow[QData.length+1];

        editTextsForStep4 = new EditText[QData.length];

        tableRowTextViews = new TextView[QData.length];

        TextView view = new TextView(MooreMainActivity.this);
        view.setText("Outputs");
        view.setPadding(20,20,20,20);

        tableRowsStep4[0] = new TableRow(MooreMainActivity.this);
        tableRowsStep4[0].setLayoutParams(layoutParamsTR);
        //tableRowsStep4[0].setPadding(20,20,20,20);
        tableRowsStep4[0].addView(view);

        for (int i=0;i<QData.length;i++){

            tableRowTextViews[i] = new TextView(MooreMainActivity.this);
            tableRowTextViews[i].setText(QData[i]);
            tableRowTextViews[i].setLayoutParams(layoutParamsTR);
            tableRowTextViews[i].setPadding(20,20,20,20);

            tableRowsStep4[i+1] = new TableRow(MooreMainActivity.this);
            tableRowsStep4[i+1].setLayoutParams(layoutParamsTR);
            //tableRowsStep4[i+1].setPadding(20,20,20,20);
            tableRowsStep4[i+1].addView(tableRowTextViews[i]);

            editTextsForStep4[i] = new EditText(MooreMainActivity.this);
            editTextsForStep4[i].setLayoutParams(layoutParamsTR);
            editTextsForStep4[i].setPadding(20,20,20,20);
            editTextsForStep4[i].setMinimumWidth(200);
            tableRowsStep4[i+1].addView(editTextsForStep4[i]);

        }

        for (int i = 0 ; i < QData.length+1; i++){
            tableLayoutStep4.addView(tableRowsStep4[i]);
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
            Toast.makeText(MooreMainActivity.this,"Boş İnputları Doldurunuz",Toast.LENGTH_LONG).show();
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
                step3to4.setVisibility(View.VISIBLE);break;
            case 6:
                scrollView.setVisibility(View.GONE);
                step3to4.setVisibility(View.GONE);break;
            case 7:
                step4toEnd.setVisibility(View.VISIBLE);
                scrollViewstep4.setVisibility(View.VISIBLE);break;
            case 8:
                step4toEnd.setVisibility(View.GONE);
                scrollViewstep4.setVisibility(View.GONE);break;
            case 9:
                endsteplinearlayout.setVisibility(View.VISIBLE);
                outputLinear.setVisibility(View.VISIBLE);
                statesLinear.setVisibility(View.VISIBLE);
                finishbtn.setVisibility(View.VISIBLE);
                break;
            case 10:
                endsteplinearlayout.setVisibility(View.GONE);
                outputLinear.setVisibility(View.GONE);
                statesLinear.setVisibility(View.GONE);
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
        visibilityForLayout(8);
        visibilityForLayout(9);
    }

    /*
    son adımdaki inputu al
     */
    private void getEndData() {
        EndInput = EndInputET.getText().toString();
    }

    private void showOutput() {
        outputEndList = new ArrayList<String>();
        outputStateList = new ArrayList<String>();

        outputEndList.add(states[0].getOutput());
        outputStateList.add(states[0].getStateName());

        MooreStates tempMooreStates = states[0];
        for (int i = 1;i<EndInput.length()+1;i++){
            for (int k = 0;k < SData.length;k++){
                if (tempMooreStates.getMooreEdgeList().get(k).getEdgeCondition().equalsIgnoreCase(EndInput.substring(i-1,i))){
                    tempMooreStates = tempMooreStates.getMooreEdgeList().get(k).getTargetState();
                    outputStateList.add(tempMooreStates.getStateName());
                    outputEndList.add(tempMooreStates.getOutput());

                    break;
                }
            }
        }

        outputStates.setText("");
        for (String text : outputStateList){
            outputStates.setText(outputStates.getText()+text+" ");
        }
        output.setText("");
        for (String text : outputEndList){
            output.setText(output.getText()+text+" ");
        }
    }
}
