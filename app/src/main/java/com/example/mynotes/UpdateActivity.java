package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import io.realm.Realm;
import io.realm.RealmResults;

public class UpdateActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    Button updateBtn;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title = findViewById(R.id.titleView);
        description = findViewById(R.id.MultiLineView);
        updateBtn = findViewById(R.id.updateNote);

        //receiving data from other activity
        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        description.setText(i.getStringExtra("description"));
        id = i.getIntExtra("id", 0);

        //update btn
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s_title=title.getText().toString();
                String s_description=description.getText().toString();

                if(!TextUtils.isEmpty(s_title) && !TextUtils.isEmpty(s_description))
                {
                     Realm realm = Realm.getDefaultInstance();

                             RealmResults<Note> results = realm.where(Note.class).equalTo("id", id).findAll();

                             for(Note n:results) {
                                 realm.beginTransaction();
                                 n.setTitle(s_title);
                                 n.setDescription(s_description);
                                 realm.commitTransaction();
                             }
                    Intent mi = new Intent(UpdateActivity.this,MainActivity.class);
                    mi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mi);
                }
                else{
                    Toast.makeText(UpdateActivity.this,"Both Fields Required",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}