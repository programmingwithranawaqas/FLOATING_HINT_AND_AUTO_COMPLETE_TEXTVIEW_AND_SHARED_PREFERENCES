package com.wtechweb.v1_fh_actv_sf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView etFirstName;
    AutoCompleteTextView etLastName;
    TextView tvMessageNote;
    Button btnSubmit;

    public static final String MY_PREFS_FILENAME = "com.wtechweb.v1_fh_actv_sf.Username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvMessageNote = findViewById(R.id.tvWelcomeNote);

        SharedPreferences sPrefsReadData = getSharedPreferences(MY_PREFS_FILENAME, MODE_PRIVATE);
        String message = sPrefsReadData.getString("msg", "");
        if (message.equals(""))
        {
            tvMessageNote.setText("Welcome ");
        }
        else
        {
            tvMessageNote.setText("Welcome "+message);
            etFirstName.setVisibility(View.GONE);
            etLastName.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.GONE);
        }




        String []fnames = {"Waqas", "Rashid", "Waqar", "Wahid", "Wajid", "Raza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_design_autocomplete, fnames);
        etFirstName.setThreshold(1);
        etFirstName.setAdapter(adapter);

        String []lnames = {"Ali", "Murshid"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.custom_design_autocomplete, lnames);
        etLastName.setThreshold(1);
        etLastName.setAdapter(adapter1);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etFirstName.getText().toString().trim() +" "+ etLastName.getText().toString().trim();
                if(!name.isEmpty())
                {
                    tvMessageNote.setText("Welcome "+name);
                    SharedPreferences.Editor sPrefEditor = getSharedPreferences(MY_PREFS_FILENAME, MODE_PRIVATE).edit();
                    sPrefEditor.putString("msg",name);
                    sPrefEditor.commit();

                }
            }
        });
    }
}