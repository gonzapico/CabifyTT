package xyz.gonzapico.data.entity;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import java.io.IOException;
import okio.BufferedSink;

/**
 * Created by gfernandez on 2/11/16.
 */

public class StopsBodyRequest extends RequestBody{
  @Override public MediaType contentType() {
    return null;
  }

  @Override public void writeTo(BufferedSink sink) throws IOException {

  }
}
