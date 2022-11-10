package edu.uco.artdly.crosscutting.exception.crosscutting;

import edu.uco.artdly.crosscutting.enumeration.LayerException;
import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;

public class CrosscuttingCustomException extends ArtdlyCustomException {


	private static final long serialVersionUID = -4008582570891805620L;

	protected CrosscuttingCustomException(String userMessage, String technicalMessage, Throwable rootException) {
		super(userMessage, technicalMessage, rootException, LayerException.CROSSCUTTING);

	}
    public static final ArtdlyCustomException CreateUserException(final String userMessage) {
        return new CrosscuttingCustomException(userMessage, userMessage, new Exception());
        
    }
    
    public static final ArtdlyCustomException CreateTechnicalException(final String technicalMessage) {
        return new CrosscuttingCustomException(null, technicalMessage, new Exception());
        
    }
    public static final ArtdlyCustomException CreateTechnicalException(final String technicalMessage, final Exception rootException ) {
        return new CrosscuttingCustomException(null, technicalMessage,rootException);
        
    }
    public static final ArtdlyCustomException Create(final String userMessage, final String technicalMessage) {
        return new CrosscuttingCustomException(userMessage, technicalMessage, new Exception());
        
    }
    public static final ArtdlyCustomException Create(final String userMessage, final String technicalMessage, final Exception rootException ) {
        return new CrosscuttingCustomException(userMessage, technicalMessage, rootException);
    }

}
