package edu.uco.artdly.crosscutting.exception;

import edu.uco.artdly.crosscutting.enumeration.LayerException;
import edu.uco.artdly.crosscutting.helper.ObjectHelper;
public class ArtdlyCustomException extends RuntimeException {

	
	private static final long serialVersionUID = -1776237992981511029L;

	private String userMessage;
	private  LayerException layer;
	    
	            
	    protected ArtdlyCustomException(final String userMessage, final String technicalMessage, final Throwable rootException, final LayerException layer) {
	        super(technicalMessage, ObjectHelper.getDefaultIfNull(rootException, new Exception()));
	        setUserMessage(userMessage);
	        setLayer(layer);
	    }


	    public String getUserMessage() {
	        return userMessage;
	    }


	    public void setUserMessage(final String userMessage) {
	        this.userMessage = applytrim(userMessage);
	    }


	    public LayerException getLayer() {
	        return layer;
	    }


	    public void setLayer(LayerException layer) {
	        this.layer = getDefaultIfNull(layer, LayerException.APLICATION);
	    }
	    
	    public final boolean isTechinalException() {
	        return isnull(getUserMessage());
	    }

}
