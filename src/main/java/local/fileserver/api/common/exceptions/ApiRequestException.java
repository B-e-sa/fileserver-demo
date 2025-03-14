package local.fileserver.api.common.exceptions;

public class ApiRequestException extends RuntimeException {
	public ApiRequestException(String message) {
		super(message);
	}
}
