package com.walkernation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import junit.framework.TestCase;

import com.walkernation.torrentcdn.tracker.TorrentTracker;

public class TestTracker extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final static String torrentsFolder = "./../torrents/";

	public void testStartup() throws IOException, InterruptedException {
		if (torrentsFolder == null) {
			fail();
		}

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File torrentsFolder, String name) {
				return name.endsWith(".torrent");
			}
		};

		File[] files = new File(torrentsFolder).listFiles(filter);

		if (files == null) {
			fail("Torrents folder was invalid");
		}

		for (File file : files) {
			System.out.println("file: name: " + file.getName());
		}
		TorrentTracker tracker = new TorrentTracker(8090, torrentsFolder);
		tracker.startTracker();

		Thread.sleep(15 * 1000);

		tracker.stopTracker();
	}

}
