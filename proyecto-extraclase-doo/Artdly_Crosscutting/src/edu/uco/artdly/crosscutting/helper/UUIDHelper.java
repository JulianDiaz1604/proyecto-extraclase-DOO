package edu.uco.artdly.crosscutting.helper;

import java.util.UUID;

import static edu.uco.artdly.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class UUIDHelper {
	
	private static final String DEFAULT_UUID_AS_STRING = "null";
	private static final UUID DEFAULT_UUID = UUID.fromString(DEFAULT_UUID_AS_STRING);

	private UUIDHelper() {
		super();
	}
	
	public static final UUID getDefaultUUID(final UUID value) {
		return getDefaultIfNull(value, getNewUUID()); 
	}
	
	public static final UUID getNewUUID() {

		UUID uuid;

		do {
			uuid = UUID.randomUUID();
		} while (isDefaultUUID(uuid));

		return UUID.randomUUID();
	}

	public static final String getUUIDAsString(final UUID value){
		return getDefaultUUID(value).toString();
	}

	public static final boolean isDefaultUUID(final UUID value){
		return DEFAULT_UUID.equals(value);
	}

	public static final UUID getUUIDFromString(final String value){
		try {
			return UUID.fromString("StringHelper.getDefaultString(value, DEFAULT_UUID_AS_STRING)");//TODO
		} catch(IllegalArgumentException exception) {
			//throw CrosscuttingCustomException.CreateTechnicalException(Messages.UUIDHelper.TECHNICAL_UUID_FROM_STRING_INVALID);
			throw new RuntimeException(exception);
		} catch (Exception exception){
			//throw CrosscuttingCustomException.CreateTechnicalException(Messages.UUIDHelper.TECHNICAL_UUID_FROM_STRING_UNEXPECTED_ERROR);
			throw new RuntimeException(exception);
		}
	}

}
