package com.alan.shenw.jterminal;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Dispatcher {

	private Logger logger = Logger.getLogger("JTerminal");
	private Options options = null;
	
	public void dispatch(String[] args) throws ParseException {
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(getOptions(), args);
 		if (cmd.hasOption("help") || cmd.getOptions().length == 0) {
			printHelpScreen();
			System.exit(0);
		}
		if (cmd.hasOption("debug")) {
			logger.setLevel(Level.DEBUG);
		}
	}
	
	private void printHelpScreen() {
		/*
		 * custom message
		 */
		StringBuilder builder = new StringBuilder();
		builder.append("jc [options] [args]");
		/*
		 * get help message from options
		 */
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(builder.toString(), getOptions());
	}

	private Options getOptions() {
		if (options == null) {
			Option helpOption = new Option("h", "help", false, "display help information and exit");
			Option debugOption = new Option("d", "debug", false, "print debug information to screen");
			/*
			 * attach the Options
			 */
			options = new Options();
			options.addOption(helpOption);
			options.addOption(debugOption);
		}
		return options;
	}
}
