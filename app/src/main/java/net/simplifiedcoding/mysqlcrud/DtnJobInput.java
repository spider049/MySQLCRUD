package net.simplifiedcoding.mysqlcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DtnJobInput extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave,btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtn_job_input);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnHome = (Button) findViewById(R.id.btnHomeback);

        btnSave.setOnClickListener(this);
        btnHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnHome){
            startActivity(new Intent(this,MainActivity.class));
        }

        if(v == btnSave){
            Toast.makeText(DtnJobInput.this,"บันทุกปิดงานแล้วนะ",Toast.LENGTH_LONG).show();
        }
    }
}
