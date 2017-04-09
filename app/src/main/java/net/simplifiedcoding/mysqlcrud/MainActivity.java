package net.simplifiedcoding.mysqlcrud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText editTextName;
    private EditText editTextDesg;
    private EditText editTextSal;

    private Button buttonAdd;
    private Button buttonView;

    private  Button buttonTest;                                 // test Button TEST
    private  Button buttonViewALLJOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDesg = (EditText) findViewById(R.id.editTextDesg);
        editTextSal = (EditText) findViewById(R.id.editTextSalary);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        buttonTest =(Button) findViewById(R.id.buttonTest);     // test Button TEST
        buttonViewALLJOB =(Button) findViewById(R.id.buttonViewALLjob);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        buttonTest.setOnClickListener(this);                    // test Button TEST
        buttonViewALLJOB.setOnClickListener(this);
    }


    //Adding an employee
    private void addEmployee(){

        final String name = editTextName.getText().toString().trim();
        final String desg = editTextDesg.getText().toString().trim();
        final String sal = editTextSal.getText().toString().trim();


        // class AddEmployee extends AsyncTask<Void,Void,String>{
        class AddEmployee extends AsyncTask<Void,Integer,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {                         // 1 ระหว่างเตรียมข้อมูล
                super.onPreExecute();
                // loading = ProgressDialog.show(MainActivity.this,"กำลังเพิ่ม...","ใจเย็นๆ.นิ!!",false,false);
                loading = new ProgressDialog(MainActivity.this);
                loading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // loading.setTitle("Loading...");
                loading.setMessage("Loading Data...");
                loading.setCancelable(false);
                loading.setIndeterminate(false);
                loading.setMax(100);
                loading.setProgress(0);
                loading.show();
            }

            @Override
            protected String doInBackground(Void... v) {            // 2 กำลังส่งข้อมูล
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_DESG,desg);
                params.put(Config.KEY_EMP_SAL,sal);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {                // 4 เมื่อส่งข้อมูลเรียบร้อย "s" ได้มาจาก echo php ส่งกลับ
                super.onPostExecute(s);
                loading.dismiss();                                  // เมื่อ doInBackground โหลดข้อมูลเสร็จให้ปิด ProgressDialog
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate();
                loading.setProgress(values[0]);
            }


        }

        AddEmployee ae = new AddEmployee();
        ae.execute();                                               // 3 บันทึกลงฐานข้อมูล
        // Toast.makeText(MainActivity.this,"เรียบร้อย บริบูรณ์..",Toast.LENGTH_LONG).show();
    }

    private void bttTest(){     // เริ่ม
        // buttonTest
        final String name = editTextName.getText().toString().trim();
        final String desg = editTextDesg.getText().toString().trim();
        final String sal = editTextSal.getText().toString().trim();

        class  bttTest extends AsyncTask<Void,Void,String>{
            ProgressDialog loading2;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading2 = ProgressDialog.show(MainActivity.this,"กำลังกดปุ่ม TEST","มาแล้วดีใจด้วยปุ่ม Test",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading2.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_DESG,desg);
                params.put(Config.KEY_EMP_SAL,sal);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD100, params);
                return res;
            }
        }
        bttTest aa = new bttTest();
        aa.execute();
    }   // Method test จบ

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllEmployee.class));
        }

        // buttonTest
        if(v == buttonTest){
            bttTest();
        }

        //buttonViewALLJOB
        if(v == buttonViewALLJOB){
          //startActivity(new Intent(this,Vie));
        }
    }
}
