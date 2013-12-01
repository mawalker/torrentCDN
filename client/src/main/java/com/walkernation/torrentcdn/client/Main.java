package com.walkernation.torrentcdn.client;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Observable;
import java.util.Observer;

import com.turn.ttorrent.client.SharedTorrent;

/**
 * Hello world!
 * 
 */
public class Main {
	public static void main(String[] args) {

		TorrentClient tC = null;

		String torrentPath = "./../torrents/testfile1.txt.torrent";
		String pathToOutputDirectory = "./../file-o/";

		SharedTorrent torrent = null;
		try {
			torrent = SharedTorrent.fromFile(new File(torrentPath), new File(
					pathToOutputDirectory));
		} catch (IOException e1) {
			System.err.println("Error with setup" + e1.getMessage());
		}

		try {
			if (torrent != null) {
				tC = new TorrentClient(InetAddress.getLocalHost(), torrent);
				tC.addObserver(new Observer() {

					@Override
					public void update(Observable o, Object arg) {
						System.out.println("TorrentCDN Client: "
								+ arg.toString());
					}
				});
			}

		} catch (IOException ex) {
			System.err.println("Error with setup" + ex.getMessage());
		}

		if (tC != null) {
			tC.setSeedTimeAfterCompletion(15);
			// tC.download();
			tC.blockingWaitForCompletion();
		}
	}
}
