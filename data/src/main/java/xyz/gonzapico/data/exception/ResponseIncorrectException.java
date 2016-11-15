package xyz.gonzapico.data.exception;

/**
 * Created by gfernandez on 10/11/16.
 */

public class ResponseIncorrectException extends Exception {

  public ResponseIncorrectException() {
    super();
  }

  public ResponseIncorrectException(final String message) {
    super(message);
  }

  public ResponseIncorrectException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ResponseIncorrectException(final Throwable cause) {
    super(cause);
  }
}
