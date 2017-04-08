package net.simplifiedcoding.mysqlcrud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllEmployee extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME);
                //String desg1 = jo.getString(Config.TAG_DESG); // เพิ่ม

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_ID,id);
                employees.put(Config.TAG_NAME,name);
                //employees.put(Config.TAG_DESG,desg1); // เพิ่ม
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllEmployee.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_NAME},
                new int[]{R.id.id, R.id.name});  // เพิ่ม

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        // class GetJSON extends AsyncTask<Void,Void,String>{
        class GetJSON extends AsyncTask<Void,Integer,String>{
            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                loading.setProgress(values[0]);
            }

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // loading = ProgressDialog.show(ViewAllEmployee.this,"กำลังดึงข้อมูล","สักครู่...",false,false);
                loading = new ProgressDialog(ViewAllEmployee.this);
                loading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //loading.setTitle("Loading...");
                loading.setMessage("Loading Data...");
                loading.setCancelable(false);
                loading.setIndeterminate(false);
                loading.setMax(100);
                loading.setProgress(0);
                loading.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewEmployee.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.EMP_ID,empId);
        startActivity(intent);
    }
}
