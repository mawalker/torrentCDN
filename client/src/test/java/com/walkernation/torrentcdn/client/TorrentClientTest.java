package com.walkernation.torrentcdn.client;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

import com.turn.ttorrent.client.SharedTorrent;

public class TorrentClientTest {

	String torrentPath = "./../torrents/testfile1.txt.torrent";
	String dirWithFile = "./../file-o/";
	String dirWithoutFile = "./../file-n/";

	@Test
	public void test() throws UnknownHostException, IOException {

		InetAddress address = InetAddress.getLocalHost();

		SharedTorrent torrent = null;

		torrent = SharedTorrent.fromFile(new File(torrentPath), new File(
				dirWithFile));

		TorrentClient tc = new TorrentClient(address, torrent);
		org.junit.Assert.assertNotNull(tc);
	}

}
