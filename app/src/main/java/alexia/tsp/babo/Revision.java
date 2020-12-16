package alexia.tsp.babo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Revision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision);

        ListView list_vocab = (ListView) findViewById(R.id.list_vocab);
        MyDatabase db = new MyDatabase(this);

        ArrayList<Vocab> VocabArray = db.readData();



        VocabListAdapter adapter = new VocabListAdapter(this, R.layout.list_view_layout, VocabArray);
        Log.i("revision", "oki");
        list_vocab.setAdapter(adapter);


    }
}