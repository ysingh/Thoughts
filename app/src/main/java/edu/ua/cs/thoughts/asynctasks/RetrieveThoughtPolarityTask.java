package edu.ua.cs.thoughts.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.alchemyapi.api.AlchemyAPI;

import org.w3c.dom.Document;

import edu.ua.cs.thoughts.entities.Thought;
import edu.ua.cs.thoughts.interfaces.DataDownloadListener;

/**
 * Created by TaxMac on 12/3/14.
 */
public class RetrieveThoughtPolarityTask extends AsyncTask<Thought, Void, Document> {

    DataDownloadListener dataDownloadListener;

    private static final String TAG = "RetrieveThoughtPolarityTask";
    public String AlchemyAPI_Key = "911fc8f6e057c3e6dfd2fa485ddbcc378e5dad71";
    AlchemyAPI api = null;
    Document doc = null;

    public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
        this.dataDownloadListener = dataDownloadListener;
    }

    @Override
    protected Document doInBackground(Thought... thoughts) {
        try {
            api = AlchemyAPI.GetInstanceFromString(AlchemyAPI_Key);
        }
        catch(IllegalArgumentException ex) {
            Log.e(TAG, "Error loading AlchemyAPI. Check that you have a valid AlchemyAPI key set in the AlchemyAPI_Key variable.");
            return null;
        }

        try {
            doc = api.TextGetTextSentiment(thoughts[0].thoughtText);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(Document result) {


        if(result != null) {
            Log.i(TAG, result.toString());
            dataDownloadListener.dataDownloadedSuccessfully(result);
        }
        else
            dataDownloadListener.dataDownloadFailed();

    }

}
