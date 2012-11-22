package com.alan.shenw.jterminal.command;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Map;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class LSCommand extends Command {
	
	public LSCommand(Map<String, String> properties, String[] args) throws ParseException {
		super(properties, args);
	}

	@Override
	public String call() throws Exception {
		String curdir = properties.get("curdir");
		File file = new File(curdir);
		File[] list = file.listFiles(getFileFilter());
		return buildResult(list);
	}

	private String buildResult(File[] list) {
		
		// TODO Auto-generated method stub
		return null;
	}

	private FileFilter getFileFilter() {
		final boolean showHidden = cmd.hasOption('a');
		boolean onlyFile = cmd.hasOption('f');
		boolean onlyDirectory = cmd.hasOption('d');
		final boolean showFile = onlyFile || (onlyFile && onlyDirectory);
		final boolean showDirectory = onlyDirectory || (onlyFile && onlyDirectory);
		
		return new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file.isHidden() && showHidden) {
					if (file.isFile() && showFile) {
						return true;
					}
					if (file.isDirectory() && showDirectory) {
						return true;
					}
				}
				return false;
			}
		};
	}

	@Override
	Options getOptions() {
		Options opts = new Options();
		opts.addOption(new Option("h", "help", false, "help description"));
		opts.addOption(new Option("l", false, "detail list description"));
		opts.addOption(new Option("a", false, "all files description"));
		opts.addOption(new Option("d", false, "directory description"));
		opts.addOption(new Option("f", false, "file description"));
		return opts;
	}
}
