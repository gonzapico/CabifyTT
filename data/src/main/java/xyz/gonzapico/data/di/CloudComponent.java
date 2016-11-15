package xyz.gonzapico.data.di;

import dagger.Component;
import javax.inject.Singleton;
import xyz.gonzapico.data.repository.datasource.CloudEstimateDataStore;

/**
 * Created by gfernandez on 28/07/16.
 */
@Singleton @Component(modules = CloudModule.class) public interface CloudComponent {
  void inject(CloudEstimateDataStore cloudEstimateDataStore);
}
