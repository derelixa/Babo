package alexia.tsp.babo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.*;

public class RequestDownloadTask extends AsyncTask<String, Void, ArrayList> {

    private final SearchResults activity;
    private final String url;

    public RequestDownloadTask(SearchResults activity, String URL) {
        this.activity = activity;
        this.url = URL;
        Log.i("alexia", "start");
    }

    @Override
    protected ArrayList doInBackground(String... string) {
        List item = new ArrayList();

        try {
            URL url = new URL(this.url);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = factory.newPullParser();

            myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myParser.setInput(stream, null);
            myParser.nextTag();

            Log.i("alexia", "do in bcgd");
            item = readFeed(myParser);



            return (ArrayList) item;


        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List item = new ArrayList();

        parser.require(XmlPullParser.START_TAG, null, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the item tag
            if (name.equals("item")) {
                readTitle(parser);
            } else {
                skip(parser);
            }
        }
        return item;
    }


    private String readTitle (XmlPullParser myParser) throws IOException, XmlPullParserException {
        myParser.require(XmlPullParser.START_TAG, null, "word");
        String word = readText(myParser);
        myParser.require(XmlPullParser.END_TAG, null, "word");
        return word;
    }

    private String readText (XmlPullParser myParser) throws IOException, XmlPullParserException {
        String result = "";
        if (myParser.next() == XmlPullParser.TEXT) {
            result = myParser.getText();
            myParser.nextTag();
        }
        return result;
    }

    // Skip tags I don't care about
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    protected void onPostExecute(ArrayList item){
        activity.callBackData(item);
    }
}

