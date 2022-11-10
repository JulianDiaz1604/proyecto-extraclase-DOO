package edu.uco.artdly.crosscutting.exception.usecase;

import edu.uco.artdly.crosscutting.enumeration.LayerException;
import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;

public class UsecaseCustomException extends ArtdlyCustomException {

    private static final long serialVersionUID = -5491429994161697127L;

	protected UsecaseCustomException(String userMessage, String technicalMessage, Throwable rootException) {
		super(userMessage, technicalMessage, rootException, LayerException.SERVICE);
	}

	public static final ArtdlyCustomException CreateUserException(final String userMessage) {
	    return new UsecaseCustomException(userMessage, userMessage, new Exception());
	    
	}
	
	public static final ArtdlyCustomException CreateTechnicalException(final String technicalMessage) {
	    return new UsecaseCustomException(null, technicalMessage, new Exception());
	    
	}
	public static final ArtdlyCustomException CreateTechnicalException(final String technicalMessage, final Exception rootException) {
	    return new UsecaseCustomException(null, technicalMessage,rootException);
	    
	}
	public static final ArtdlyCustomException Create(final String userMessage, final String technicalMessage) {
	    return new UsecaseCustomException(userMessage, technicalMessage, new Exception());
	    
	}
	public static final ArtdlyCustomException Create(final String userMessage, final String technicalMessage, final Exception rootException ) {
	    return new UsecaseCustomException(userMessage, technicalMessage, rootException);
	}

    
}
