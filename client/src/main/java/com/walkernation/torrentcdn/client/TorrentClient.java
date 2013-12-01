package com.walkernation.torrentcdn.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class TorrentClient extends Client {

	public TorrentClient(InetAddress address, SharedTorrent torrentInfo)
			throws UnknownHostException, IOException {
		super(address, torrentInfo);

		// You can optionally set download/upload rate limits
		// in kB/second. Setting a limit to 0.0 disables rate
		// limits.
		this.setMaxDownloadRate(50.0);
		this.setMaxUploadRate(50.0);
	}

	public void setSeedTimeAfterCompletion(int seconds) {
		this.share(seconds);
	}

	public void blockingWaitForCompletion() {
		this.waitForCompletion();
	}

}
