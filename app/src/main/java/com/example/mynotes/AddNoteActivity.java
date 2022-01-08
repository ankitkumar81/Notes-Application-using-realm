package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // title and description
        EditText titleInput=findViewById(R.id.titleview);
        EditText descriptionInput=findViewById(R.id.MultiLineview);


        //initiating realm
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        //Done button
        Button done = findViewById(R.id.button2);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();

                    Note n = new Note();
                    realm.beginTransaction();
                    n.setId(getNextKey());
                    n.setTitle(title);
                    n.setDescription(description);
                    realm.copyToRealm(n);
                    realm.commitTransaction();
                    realm.close();

                    Intent an = new Intent(AddNoteActivity.this, MainActivity.class);
                    an.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(an);
                }

            public int getNextKey() {
                try {
                    return realm.where(Note.class).max("id").intValue() + 1;
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    return 0;
                }
            }
        });

    }
}