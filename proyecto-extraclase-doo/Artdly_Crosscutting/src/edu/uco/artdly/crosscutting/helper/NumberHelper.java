package edu.uco.artdly.crosscutting.helper;
public class NumberHelper {

	private NumberHelper() {
		super();
	}
	public static final byte ZERO = 0;
	public static final <T> T getDefaultNumber(T value, T defaultValue) {
		return ObjectHelper.getDefaultIfNull(value, defaultValue);
	}
	public static final <T extends Number> Number getDefaultNumber(T value) {
		return getDefaultNumber(value, ZERO);
	}
	public static final <T extends Number> boolean isGreaterThan(T numberOne, T numberTwo){
		return getDefaultNumber(numberOne).doubleValue()>getDefaultNumber(numberTwo).doubleValue();
	
	}
	public static final <T extends Number> boolean isLessThan(T numberOne, T numberTwo){
		return getDefaultNumber(numberOne).doubleValue()<getDefaultNumber(numberTwo).doubleValue();
	}
	public static final <T extends Number> boolean isEqualThan(T numberOne, T numberTwo){
		return getDefaultNumber(numberOne).doubleValue() == getDefaultNumber(numberTwo).doubleValue();
	}
	public static final <T extends Number> boolean isGreaterOrEqualThan(T numberOne, T numberTwo){
		return getDefaultNumber(numberOne).doubleValue()>=getDefaultNumber(numberTwo).doubleValue();
	}
	public static final <T extends Number> boolean isLessOrEqualThan(T numberOne, T numberTwo){
		return getDefaultNumber(numberOne).doubleValue()<=getDefaultNumber(numberTwo).doubleValue();
	}
	public static final <T extends Number> boolean isDifferent(T numberOne, T numberTwo){
		return getDefaultNumber(numberOne).doubleValue()!=getDefaultNumber(numberTwo).doubleValue();
	}
	

}
