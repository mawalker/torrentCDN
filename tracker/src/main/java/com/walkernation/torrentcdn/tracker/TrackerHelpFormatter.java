package com.walkernation.torrentcdn.tracker;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class TrackerHelpFormatter extends HelpFormatter {
	public String renderHelpString(Options options) {
		StringBuffer sb = new StringBuffer();
		this.renderOptions(sb, 80, options, 0, 10);
		return sb.toString();
	}
}
