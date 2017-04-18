package com.example.apple.sdv10;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView textWindows;
    Button btn1;
    String filename = "test";
    JSONArray jsonId = new JSONArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //android 5.0 x86_64 API21
        final SDCard sdCard = new SDCard(filename, MainActivity.this);
        listView = (ListView)findViewById(R.id.listview1);
        textWindows = (TextView)findViewById(R.id.textView1);
        btn1 = (Button) findViewById(R.id.button1);
        Context cntx = getApplicationContext();
        Contacts contacts = new Contacts(MainActivity.this,getContentResolver(),jsonId,listView);

        /*insert*/
        contacts.WritePhoneContact("YA","666",MainActivity.this);
        /*insert*/
        try {
            contacts.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sdCard.isSDWriteable()) {
                    sdCard.SDwrite(jsonId.toString());
                }
            }
        });
    }
}