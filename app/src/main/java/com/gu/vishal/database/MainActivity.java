package com.gu.vishal.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnIns, btnUp, btnDel, btnFind, btnList;
    EditText editId, editName, editPost;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIns = (Button) findViewById(R.id.btnIns);
        btnUp =  (Button) findViewById(R.id.btnUp);
        btnDel =  (Button) findViewById(R.id.btnDel);
        btnFind =  (Button) findViewById(R.id.btnFind);
        btnList =  (Button) findViewById(R.id.btnList);

        editId = (EditText) findViewById(R.id.author_id);
        editName  = (EditText) findViewById(R.id.author_name);
        editPost  = (EditText) findViewById(R.id.author_post);
        result = (TextView) findViewById(R.id.textView2);


        final SqlDbHandler db = new SqlDbHandler(this);

        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addBlog(new SqlBlog(editName.getText().toString(),editPost.getText().toString()));

                editName.setText("");
                editPost.setText("");
                result.setText("");

                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<SqlBlog> entries = db.getAllPosts();
                String log = "";

                for (SqlBlog entry : entries){
                    log += "Id:: "+entry.get_id() + ",\tAuthor::" +entry.get_author() + ",\tPost::"+entry.get_post()+"\n";
                }
                result.setText(log);
            }

            });



    }
}
