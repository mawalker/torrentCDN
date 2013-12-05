package com.walkernation.torrentcdn.tracker;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;

public class TorrentTracker extends Tracker {

	private final static String TORRENT_EXTENSION = ".torrent";

	// Tracker tracker;

	public TorrentTracker(int port, String torrentsFolder) throws IOException {
		super(new InetSocketAddress(port), torrentsFolder);
		// tracker = new Tracker(new InetSocketAddress(port));

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File torrentsFolder, String name) {
				return name.endsWith(TORRENT_EXTENSION);
			}
		};
		//
		// File[] files = new File(torrentsFolder).listFiles(filter);
		// for (File file : files) {
		// System.out.println("file: name: " + file.getName());
		// }
		for (File f : new File(torrentsFolder).listFiles(filter)) {
			this.announce(TrackedTorrent.load(f));
		}
	}

}
