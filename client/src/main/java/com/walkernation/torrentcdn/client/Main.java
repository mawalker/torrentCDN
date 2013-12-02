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

	String torrentPath = "./../torrents/testfile1.txt.torrent";
	String pathToOutputDirectory = "./../file-o/";
	int time = 0;
	
	public Main(String[] args) {

		ParseCLI cli = new ParseCLI();

		cli.setArgs(args);
		

		boolean parseCompleted = cli.parseCommandLine();
		if (parseCompleted) {

			torrentPath = cli.getTorrentPath();
			pathToOutputDirectory = cli.getOutputDirectory();
			time = Integer.valueOf(cli.getTime());

			System.out.println("dir:" + pathToOutputDirectory);
			System.out.println("torrent:" + torrentPath);
			System.out.println("time:" + time);

		} else {
			return;
		}

		TorrentClient tC = null;
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
			tC.setSeedTimeAfterCompletion(time);
			// tC.download();
			tC.blockingWaitForCompletion();
		}
	}

	public static void main(String[] args) {
		Main main = new Main(args);
	}

}
