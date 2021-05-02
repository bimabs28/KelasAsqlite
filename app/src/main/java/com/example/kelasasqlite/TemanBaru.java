package com.example.kelasasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kelasasqlite.database.DBController;
import com.example.kelasasqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class TemanBaru extends AppCompatActivity {
    private TextInputEditText tNama,tTelpon;
    private Button simpanBtn;
    String nm,tlp;
    DBController controller = new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_baru);

        tNama = (TextInputEditText)findViewById(R.id.tietNama);
        tTelpon = (TextInputEditText)findViewById(R.id.tietTelpon);
        simpanBtn = (Button)findViewById(R.id.buttonSave);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tNama.getText().toString().equals("")||tTelpon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data belum komplit!", Toast.LENGTH_SHORT).show();
                }else{
                    nm = tNama.getText().toString();
                    tlp = tTelpon.getText().toString();
                    HashMap<String,String> QueryValues = new HashMap<>();
                    QueryValues.put("nama",nm);
                    QueryValues.put("telpon",tlp);
                    controller.insertData(QueryValues);
                    callHome();
                }
            }
        });
    }
    public void callHome(){
        Intent intent = new Intent(TemanBaru.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}