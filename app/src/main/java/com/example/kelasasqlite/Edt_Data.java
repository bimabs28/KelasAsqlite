package com.example.kelasasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kelasasqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class Edt_Data extends AppCompatActivity {
    private TextInputEditText tNama,tTelpon;
    private Button simpanBtn;
    String name, telpon, id;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt__data);

        tNama = (TextInputEditText)findViewById(R.id.edNama);
        tTelpon = (TextInputEditText)findViewById(R.id.edTelpon);
        simpanBtn = (Button)findViewById(R.id.buttonSave);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        telpon = getIntent().getStringExtra("telpon");

        setTitle("EditData");
        tNama.setText(name);
        tTelpon.setText(telpon);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tNama.getText().toString().equals("")||tTelpon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data Belum Lengkap!", Toast.LENGTH_SHORT).show();
                }else {
                    name = tNama.getText().toString();
                    telpon = tTelpon.getText().toString();
                    HashMap<String,String> qvalues = new HashMap<>();
                    qvalues.put("id",id);
                    qvalues.put("name",name);
                    qvalues.put("telpon",telpon);
                    controller.EditData(qvalues);
                    callHome();
                }
            }
        });

    }
    public void  callHome(){
        Intent intent = new Intent(Edt_Data.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}