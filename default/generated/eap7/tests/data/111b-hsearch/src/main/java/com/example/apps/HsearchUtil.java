package com.example.apps;



import org.hibernate.search.backend.configuration.impl.IndexWriterSetting;
import org.hibernate.search.store.spi.DirectoryHelper;

import static org.hibernate.search.backend.configuration.impl.IndexWriterSetting.MAX_THREAD_STATES;
import static org.hibernate.search.backend.configuration.impl.IndexWriterSetting.TERM_INDEX_INTERVAL;


public class HsearchUtil {

    public void main(String[] args) throws InterruptedException {
        IndexWriterSetting s1 = MAX_THREAD_STATES;
        IndexWriterSetting s2 = TERM_INDEX_INTERVAL;
        System.out.println(s1.toString() + s2);

        DirectoryHelper.getVerifiedIndexPath(null, null, false);
        DirectoryHelper.getVerifiedIndexDir(null, null, false);
    }
}