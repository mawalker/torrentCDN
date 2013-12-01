package com.walkernation.torrentcdn.tracker;

import java.io.IOException;

/**
 * Hello world!
 * 
 */
public class Main {

	public static int port = 8080;
	public static int time = 15;

	public static String torrentsFolder = "./torrents/";

	public static void main(String[] args) throws InterruptedException {

		ParseCLI cli = new ParseCLI();
		cli.setArgs(args);
		boolean parseCompleted = cli.parseCommandLine();
		if (parseCompleted) {

			torrentsFolder = cli.getDirectory();
			port = Integer.valueOf(cli.getPort());
			time = Integer.valueOf(cli.getTime());

			System.out.println("dir:" + torrentsFolder);
			System.out.println("port:" + port);
			System.out.println("time:" + time);

		}

		if (!parseCompleted) {
			System.out
					.println("Sorry. Arguments not recognized please try -h or -help, to continue.");
			return;
		}
		TorrentTracker tracker = null;
		try {
			tracker = new TorrentTracker(port, torrentsFolder);
		} catch (IOException ex) {
			System.err
					.println("Error with reading torrents Directory or Network Communication"
							+ ex);
		}

		if (tracker != null) {
			System.out.println("Tracker Created");
			tracker.startTracker();
			System.out.println("Tracker Started");
		}

		// let the Tracker run for as long as it is needed
		Thread.sleep(time * 1000);

		// when no longer needed report stopped.
		if (tracker != null) {
			tracker.stopTracker();
			System.out.println("Tracker Stopped");
		}
	}
}
