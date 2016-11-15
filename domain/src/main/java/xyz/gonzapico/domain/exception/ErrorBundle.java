package xyz.gonzapico.domain.exception;

/**
 * Created by gfernandez on 7/11/16.
 *
 * Interface to represent a wrapper around an {@link Exception} to manage errors.
 */
public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
