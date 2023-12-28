package com.github.jbescos.media;

import com.github.jbescos.media.client.MediaCenterTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class MediaCenterSuite extends GWTTestSuite {
    public static Test suite() {
        TestSuite suite = new TestSuite("Tests for MediaCenter");
        suite.addTestSuite(MediaCenterTest.class);
        return suite;
    }
}
