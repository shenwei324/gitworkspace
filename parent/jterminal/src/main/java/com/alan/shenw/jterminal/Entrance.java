package com.alan.shenw.jterminal;


public class Entrance {

	public static void main(String[] args) {
		try {
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.dispatch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
