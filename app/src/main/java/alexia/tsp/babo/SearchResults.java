package alexia.tsp.babo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

    private static final String url = "https://krdict.korean.go.kr/api/search?certkey_no=2121&key=1256AA71A62E288E936958B03CDB3DD7&type_search=search&method=WORD_INFO&part=word&sort=dict&translated=y&trans_lang=1&go=exam&q=%EC%B0%BD%EB%AC%B8";
    TextView word;
    TextView trad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        word = (TextView)findViewById(R.id.tvWord);
        trad = (TextView)findViewById(R.id.tvTrad);

        Bundle extras = getIntent().getExtras();
        //String researchword = new String(extras.getString("word"));
        new RequestDownloadTask(this,url).execute();
    }


    public void callBackData(ArrayList item) {
        word.setText((CharSequence) item.get(0));
        trad.setText((CharSequence) item.get(1));
        }


    }