package com.walkernation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;

public class TorrentTracker {

	int port;
	Tracker tracker;

	public TorrentTracker(int port, String torrentsFolder) throws IOException {
		this.port = port;
		try {
			tracker = new Tracker(new InetSocketAddress(6969));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".torrent");
			}
		};

		for (File f : new File(torrentsFolder).listFiles(filter)) {
			tracker.announce(TrackedTorrent.load(f));
		}
	}

	public void startTracker() {
		tracker.start();
	}

	public void stopTracker() {
		tracker.stop();
	}

}
