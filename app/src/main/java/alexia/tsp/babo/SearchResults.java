package alexia.tsp.babo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {
    TextView word;
    TextView trad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        word = (TextView) findViewById(R.id.tvWord);
        trad = (TextView) findViewById(R.id.tvTrad);




        Button bSearch = (Button) findViewById(R.id.bSearch);
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etSearch = (EditText) findViewById(R.id.etSearch);
                String rword = etSearch.getText().toString();
                Log.i("SR", rword);
                getDLTask(word, trad).execute(rword);

            }
        });

    }

    private RequestDownloadTask getDLTask(TextView word, TextView trad) {
        return new RequestDownloadTask(word, trad);
    }

}



