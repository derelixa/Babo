package alexia.tsp.babo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button bSearchA = (Button) findViewById(R.id.bSearchA);
        bSearchA.setBackgroundColor(Color.parseColor("#E6007E"));
        bSearchA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callSearch; callSearch = new Intent(getApplicationContext(), SearchResults.class);
                startActivity(callSearch);
            }
        });

        Button bRevise = (Button) findViewById(R.id.bReviseA);
        bRevise.setBackgroundColor(Color.parseColor("#E6007E"));
        bRevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callRevision; callRevision = new Intent(getApplicationContext(), Revision.class);
                startActivity(callRevision);
            }
        });
    }
}