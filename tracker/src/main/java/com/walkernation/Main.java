package com.walkernation;

import java.io.IOException;

/**
 * Hello world!
 * 
 */
public class Main {

	public static int port = 8080;

	public static String torrentsFolder = "./torrents/";

	public static void main(String[] args) throws InterruptedException {

		// TODO use Apache CLI
		if (args.length > 0) {
			torrentsFolder = args[0];
		}

		if (args.length > 1) {
			port = Integer.valueOf(args[1]);
		}

		TorrentTracker tracker = null;
		try {
			tracker = new TorrentTracker(port, torrentsFolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Tracker Created");
		if (tracker != null) {
			tracker.startTracker();
			System.out.println("Tracker Started");
		}
		Thread.sleep(4000);
		if (tracker != null) {
			tracker.stopTracker();
			System.out.println("Tracker Stopped");
		}

	}
}
