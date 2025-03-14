package local.fileserver.api.common.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ApiException {
	@Getter
	private final String message;
	
	@Getter
	private final HttpStatus httpStatus;
	
	@Getter
	private final int statusCode;
}
