package edu.uco.artdly.crosscutting.helper;

public class ObjectHelper {
	
	private ObjectHelper() {
		super();
	}
	
	public static final <T> boolean inNUll(T value) {
		return value == null;
	}
	
	public static final <T> T getDefaultIfNull(T value, T defaultValue) {
		return (inNUll(value))?defaultValue : value;
	}

}
