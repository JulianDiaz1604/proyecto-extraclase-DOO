package edu.uco.artdly.crosscutting.exception.data;

import edu.uco.artdly.crosscutting.enumeration.LayerException;
import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;

public class DataCustomException extends ArtdlyCustomException {
	private static final long serialVersionUID = -5491429994161697127L;

	protected DataCustomException(String userMessage, String technicalMessage, Throwable rootException) {
		super(userMessage, technicalMessage, rootException, LayerException.DATA);
	}

	public static final ArtdlyCustomException CreateUserException(final String userMessage) {
	    return new DataCustomException(userMessage, userMessage, new Exception());
	    
	}
	
	public static final ArtdlyCustomException CreateTechnicalException(final String technicalMessage) {
	    return new DataCustomException(null, technicalMessage, new Exception());
	    
	}
	public static final ArtdlyCustomException CreateTechnicalException(final String technicalMessage, final Exception rootException ) {
	    return new DataCustomException(null, technicalMessage,rootException);
	    
	}
	public static final ArtdlyCustomException Create(final String userMessage, final String technicalMessage) {
	    return new DataCustomException(userMessage, technicalMessage, new Exception());
	    
	}
	public static final ArtdlyCustomException Create(final String userMessage, final String technicalMessage, final Exception rootException ) {
	    return new DataCustomException(userMessage, technicalMessage, rootException);
	}




}
