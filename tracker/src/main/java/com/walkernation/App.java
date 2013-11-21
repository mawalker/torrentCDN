package com.walkernation;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.turn.ttorrent.tracker.Tracker;

/**
 * Hello world!
 * 
 */
public class App {

	public final static int port = 8080;

	public final static String torrentsFolder = "";

	public static void main(String[] args) throws InterruptedException {

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
