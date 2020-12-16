package alexia.tsp.babo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class VocabListAdapter extends ArrayAdapter<Vocab> {
    private Context context;

    public VocabListAdapter (@NonNull Context context, int ressource, @NonNull ArrayList<Vocab> vocabs) {
        super(context, 0, vocabs);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Vocab vocab = getItem(position);
        String kword = vocab.kVoc;
        String enword = vocab.enVoc;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_view_layout, parent, false);

            TextView tvKword = (TextView) convertView.findViewById(R.id.ListViewKWord);
            TextView tvEnword = (TextView) convertView.findViewById(R.id.ListViewEnWord);

            tvKword.setText(kword);
            tvEnword.setText(enword);

        }
        return convertView;
    }

    public void notifyDataSetChanged () {

    }
}
