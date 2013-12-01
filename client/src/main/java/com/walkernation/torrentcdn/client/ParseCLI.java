package com.walkernation.torrentcdn.client;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class ParseCLI {

	String args[];

	private String port;
	private String directory;
	private String time;

	private boolean hasHelp;
	private boolean hasDirectory;
	private boolean hasPort;
	private boolean hasTime;

	private String helpOptionName = "help";
	private String DirectoryOptionName = "dir";
	private String PortOptionName = "port";
	private String TimeOptionName = "time";

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
		hasDirectory = cmd.hasOption(DirectoryOptionName);
		hasPort = cmd.hasOption(PortOptionName);
		hasTime = cmd.hasOption(TimeOptionName);

		if (hasHelp) {
			printHelpString();
			return false; // flag to start processing or not
		}

		if (hasDirectory && hasPort && hasTime) {
			this.directory = cmd.getOptionValue(DirectoryOptionName);
			this.port = cmd.getOptionValue(PortOptionName);
			this.time = cmd.getOptionValue(TimeOptionName);
			parseCompleted = true;
			return parseCompleted; // flag to start processing or not
		}

		return false;

	}

	public boolean getParseCompleted() {
		return parseCompleted;
	}

	public String getDirectory() {
		return directory;
	}

	public String getPort() {
		return port;
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

		rValue = "usage: TorrentCDN-Tracker.jar\n" + rValue;
		return rValue;
	}

	protected Options createOptionsList() {
		Options options = new Options();

		Option helpOption = new Option("h", helpOptionName, false,
				"print this message");

		Option directoryOption = new Option("d", DirectoryOptionName, true,
				"Directory of Torrents.");

		Option portOption = new Option("p", PortOptionName, true,
				"Port of Tracker.");
		Option timeOption = new Option("t", TimeOptionName, true,
				"Time for Tracker to stay up (in Seconds).");

		options.addOption(helpOption);
		options.addOption(directoryOption);
		options.addOption(portOption);
		options.addOption(timeOption);

		return options;
	}
}
