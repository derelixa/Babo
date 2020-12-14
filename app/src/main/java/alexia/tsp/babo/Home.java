package alexia.tsp.babo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button bSearch = (Button) findViewById(R.id.bSearchA);
        EditText etSearch = (EditText) findViewById(R.id.etSearch);
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callSearch; callSearch = new Intent(getApplicationContext(), SearchResults.class);
                //callSearch.putExtra("word", etSearch.getText().toString());
                startActivity(callSearch);
            }
        });

        Button bRevise = (Button) findViewById(R.id.bReviseA);
        bRevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callRevision; callRevision = new Intent(getApplicationContext(), Revision.class);
                startActivity(callRevision);
            }
        });
    }
}