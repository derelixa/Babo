package alexia.tsp.babo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.*;

public class RequestDownloadTask extends AsyncTask<String, Void, ArrayList> {

    private final WeakReference<TextView> textViewWordWeakReference;
    private final WeakReference<TextView> textViewTradWeakReference;

    public RequestDownloadTask(TextView tvWord, TextView tvTrad) {
        textViewWordWeakReference = new WeakReference<>(tvWord);
        textViewTradWeakReference = new WeakReference<>(tvTrad);
    }

    protected void onPreExecute() {
        TextView tvWord = textViewWordWeakReference.get();
        TextView tvTrad = textViewTradWeakReference.get();

    }

    @Override
    protected ArrayList doInBackground(String... params) {
        String rWord = params[0];
        Log.i("alexia", rWord);
        List vocab = new ArrayList();
        List test = new ArrayList();
        test.add(0, "error");
        test.add(1, "error");
        String kWord;
        String enWord;

        try {
            String lien = "https://krdict.korean.go.kr/api/search?certkey_no=2121&key=1256AA71A62E288E936958B03CDB3DD7&type_search=search&method=WORD_INFO&part=word&sort=dict&translated=y&trans_lang=1";
            Log.i("alexia", lien + "&q=" + rWord);
            URL url = new URL(lien + "&q=" + rWord);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream stream = connection.getInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(stream);
            doc.getDocumentElement().normalize();


            Element root = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("item");
            kWord = doc.getElementsByTagName("word").item(0).getTextContent();
            enWord = doc.getElementsByTagName("trans_word").item(0).getTextContent();


            vocab.add(0,kWord);
            vocab.add(1,enWord);

            stream.close();

            Log.i("alexia",(String) vocab.get(0));


           // for (int temp = 0; temp <nList.getLength(); temp++) {
             //   Node nNode = nList.item(temp);

                //if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  //      Element eElement = (Element) nNode;
                    //    item.add(eElement.getElementsByTagName("word").item(0).getTextContent());
                   // }
                //}

            Log.i("item", "out");
            return (ArrayList) vocab;


        } catch (ProtocolException e) {
            e.printStackTrace();
            return (ArrayList) test;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return (ArrayList) test;
        } catch (IOException e) {
            e.printStackTrace();
            return (ArrayList) test;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return (ArrayList) test;
        } catch (SAXException e) {
            e.printStackTrace();
            return (ArrayList) test;
        }
    }




    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }


    protected void onPostExecute(ArrayList vocab) {
        TextView tvWord = textViewWordWeakReference.get();
        tvWord.setText((String) vocab.get(0));
        TextView tvTrad = textViewTradWeakReference.get();
        tvTrad.setText((String) vocab.get(1));
        vocab.clear();
    }
}

