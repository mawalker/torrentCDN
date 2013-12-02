package com.walkernation.torrentcdn.client;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class ParseCLI {

	String args[];

	private String torrent;
	private String directory;
	private String time;

	private boolean hasHelp;
	private boolean hasDirectory;
	private boolean hasTorrent;
	private boolean hasTime;

	private String helpOptionName = "help";
	private String torrentOptionName = "torrent";
	private String directoryOptionName = "directory";
	private String timeOptionName = "time";

	CommandLine cmd;

	private boolean parseCompleted;

	public boolean setArgs(String[] args) {
		this.args = args;

		return (args.length > 0);
	}

	public boolean parseCommandLine() {

		Options options = createOptionsList();
		CommandLineParser parser = new PosixParser();
		cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println("ERORR #2 Parsing of CLI failed  :"
					+ e.getMessage());
		}

		if (cmd == null) {
			return false;
		}

		hasHelp = cmd.hasOption(helpOptionName);
		hasDirectory = cmd.hasOption(directoryOptionName);
		hasTorrent = cmd.hasOption(torrentOptionName);
		hasTime = cmd.hasOption(timeOptionName);

		if (hasHelp) {
			printHelpString();
			return false; // flag to start processing or not
		}

		if (hasDirectory && hasTorrent && hasTime) {
			this.directory = cmd.getOptionValue(directoryOptionName);
			this.torrent = cmd.getOptionValue(torrentOptionName);
			this.time = cmd.getOptionValue(timeOptionName);
			parseCompleted = true;
			return parseCompleted; // flag to start processing or not
		} else{
			System.out.println("Missing some required option.");
			printHelpString();
		}

		return false;

	}

	public boolean getParseCompleted() {
		return parseCompleted;
	}

	public String getOutputDirectory() {
		return directory;
	}

	public String getTorrentPath() {
		return torrent;
	}

	public String getTime() {
		return time;
	}

	protected void printHelpString() {
		System.out.println(getHelpString());
	}

	public String getHelpString() {
		String rValue = null;
		ClientHelpFormatter formatter = new ClientHelpFormatter();

		rValue = formatter.renderHelpString(createOptionsList());

		rValue = "usage: TorrentCDN-Client.jar\n" + rValue;
		return rValue;
	}

	protected Options createOptionsList() {
		Options options = new Options();

		Option helpOption = new Option("h", helpOptionName, false,
				"print this message");

		Option directoryOption = new Option("d", directoryOptionName, true,
				"Output Directory(Required)");

		Option portOption = new Option("p", torrentOptionName, true,
				"Path to Torrent file.(Required)");
		Option timeOption = new Option("t", timeOptionName, true,
				"Time for Seed after completion (in Seconds).(Required)");

		options.addOption(helpOption);
		options.addOption(directoryOption);
		options.addOption(portOption);
		options.addOption(timeOption);

		return options;
	}
}
