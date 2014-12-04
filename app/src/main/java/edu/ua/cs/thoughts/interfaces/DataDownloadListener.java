package edu.ua.cs.thoughts.interfaces;

/**
 * Created by TaxMac on 12/3/14.
 */
public interface DataDownloadListener {
    void dataDownloadedSuccessfully(Object data);
    void dataDownloadFailed();
}
