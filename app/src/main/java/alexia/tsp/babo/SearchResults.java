package alexia.tsp.babo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {
    TextView word;
    TextView trad;
    ArrayList voc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        word = (TextView) findViewById(R.id.tvWord);
        trad = (TextView) findViewById(R.id.tvTrad);
        MyDatabase db = new MyDatabase(this);


        Button bSearch = (Button) findViewById(R.id.bSearch);
        bSearch.setBackgroundColor(Color.parseColor("#E6007E"));
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etSearch = (EditText) findViewById(R.id.etSearch);
                if (etSearch.getText().toString().matches("")) {
                    Toast.makeText(SearchResults.this, "Please enter a word !", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    String rword = etSearch.getText().toString();
                    Log.i("SR", rword);
                    getDLTask(word, trad).execute(rword);
                }

            }
        });

        Button bS = (Button) findViewById(R.id.bSend);
        bS.setBackgroundColor(Color.parseColor("#E6007E"));
        bS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (word.getText().toString().matches("Researched word")) {
                    Toast.makeText(SearchResults.this, "Please enter a word !", Toast.LENGTH_LONG).show();
                    return;
                }
                //else if (db.CheckIsDataAlreadyInDBorNot(word.getText().toString()) == false ) {
                  //  Toast.makeText(SearchResults.this, "Already in !", Toast.LENGTH_LONG).show();
               // }
                else {
                    Vocab rS = new Vocab(word.getText().toString(), trad.getText().toString());
                   // Cherche à tester si le mot est déjà dans la base de données pour ne pas le rajouter --> cause une erreur
                    // -> AS ne me permet pas de voir les erreurs
                    //String select = "SELECT * DATABASE_TABLE_NAME WHERE KEY_WORD = '"+word.getText().toString() + "'";
                    //if(db.getData(select).getCount()>0){
                    //    Toast.makeText(getApplicationContext(), "Already Exist!", Toast.LENGTH_SHORT).show();}
                    else {
                        db.insertData(rS);
                        Toast.makeText(SearchResults.this, "ADDED !", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    private RequestDownloadTask getDLTask(TextView word, TextView trad) {
        return new RequestDownloadTask(word, trad);
    }

}


