package local.fileserver.api.common.exceptions;

public class ConflictException extends ApiRequestException {
	public ConflictException() {
		super("Resource already exists");
	}
	
	public ConflictException(String message) {
		super(message);
	}
}