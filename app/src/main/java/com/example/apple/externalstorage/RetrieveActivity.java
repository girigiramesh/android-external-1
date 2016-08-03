package com.example.apple.externalstorage;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RetrieveActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText load_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        load_text = (EditText) findViewById(R.id.load_text);
        findViewById(R.id.btn_load_internal_cache).setOnClickListener(this);
        findViewById(R.id.btn__load_external_cache).setOnClickListener(this);
        findViewById(R.id.btn_load_external_private).setOnClickListener(this);
        findViewById(R.id.btn_load_external_public).setOnClickListener(this);
        findViewById(R.id.btn_previous_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load_internal_cache:
                File folder = getCacheDir();
                File myfile = new File(folder,"mytext.txt");
                String data = readdata(myfile);
                if(data!=null){
                    load_text.setText(data);
                }else{
                    load_text.setText("no data found");
                }
                break;
            case R.id.btn__load_external_cache:
                folder = getExternalCacheDir();
                myfile = new File(folder,"mytext1.txt");
                data = readdata(myfile);
                if(data!=null){
                    load_text.setText(data);
                }else{
                    load_text.setText("no data found");
                }
                break;
            case R.id.btn_load_external_private:
                folder = getExternalFilesDir("ExternalStorage");
                myfile = new File(folder,"mytext2.txt");
                data = readdata(myfile);
                if(data!=null){
                    load_text.setText(data);
                }else{
                    load_text.setText("no data found");
                }
                break;
            case R.id.btn_load_external_public:
               folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                myfile = new File(folder,"mytext3.txt");
                data = readdata(myfile);
                if(data!=null){
                    load_text.setText(data);
                }else{
                    load_text.setText("no data found");
                }
                break;
            case R.id.btn_previous_activity:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private String readdata(File myfile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(myfile);
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            while ((read = fis.read()) != -1) {
                stringBuffer.append((char) read);
            }
               return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
        finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}

