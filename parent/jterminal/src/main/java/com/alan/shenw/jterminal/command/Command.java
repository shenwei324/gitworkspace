package com.alan.shenw.jterminal.command;

import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

public abstract class Command implements Callable<String> {
	
	protected Logger logger = Logger.getLogger("JTerminal");
	protected Map<String, String> properties;
	protected String[] args;
	protected CommandLine cmd;
	
	public Command(Map<String, String> properties, String... args) throws ParseException {
		this.properties = properties;
		this.args = args;
		CommandLineParser cmdParser = new PosixParser();
		cmd = cmdParser.parse(getOptions(), args);
	}
	
	abstract Options getOptions();
}
