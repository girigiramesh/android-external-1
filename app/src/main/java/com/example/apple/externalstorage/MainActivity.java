package com.example.apple.externalstorage;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private EditText edit_text;
    private Button btn_internal_cache,btn_external_cache,btn_external_private,btn_external_public,btn_next_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_text = (EditText) findViewById(R.id.edit_text);
        findViewById(R.id.btn_internal_cache).setOnClickListener(this);
        findViewById(R.id.btn_external_cache).setOnClickListener(this);
        findViewById(R.id.btn_external_private).setOnClickListener(this);
        findViewById(R.id.btn_external_public).setOnClickListener(this);
        findViewById(R.id.btn_next_activity).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_internal_cache:
                String data = edit_text.getText().toString();
                File folder = getCacheDir();
                File myfile = new File(folder,"mytext.txt");
                writedata(myfile,data);
                break;

            case R.id.btn_external_cache:
                data = edit_text.getText().toString();
                folder = getExternalCacheDir();
                myfile = new File(folder, "mytext1.txt");
                writedata(myfile,data);
                break;

            case R.id.btn_external_private:
                data = edit_text.getText().toString();
                folder = getExternalFilesDir("ExternalStorage");
                myfile = new File(folder,"mytext2.txt");
                writedata(myfile,data);
                break;

            case R.id.btn_external_public:
                data = edit_text.getText().toString();
                folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                myfile = new File(folder,"mytext3.txt");
                writedata(myfile,data);
                break;

            case R.id.btn_next_activity:
                Intent intent = new Intent(this,RetrieveActivity.class);
                startActivity(intent);
                break;

        }
    }
    private  void writedata(File myfile,String data){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myfile);
            fos.write(data.getBytes());
            message(data + "was written sucessfully" + myfile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void message(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

}
