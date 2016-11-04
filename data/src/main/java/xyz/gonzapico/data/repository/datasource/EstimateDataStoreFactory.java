package xyz.gonzapico.data.repository.datasource;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link EstimateDataStore}.
 */
@Singleton public class EstimateDataStoreFactory {

  private final Context context;

  @Inject public EstimateDataStoreFactory(Context context) {
    if (context == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
  }

  /**
   * Create {@link EstimateDataStore} to retrieve data from the Cloud.
   */
  public EstimateDataStore createCloudDataStore() {

    return new CloudEstimateDataStore(context);
  }
}
