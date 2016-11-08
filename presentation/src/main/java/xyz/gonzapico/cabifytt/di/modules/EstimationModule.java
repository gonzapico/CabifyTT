package xyz.gonzapico.cabifytt.di.modules;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import xyz.gonzapico.cabifytt.di.PerActivity;
import xyz.gonzapico.domain.interactor.BaseUseCase;
import xyz.gonzapico.domain.interactor.GetEstimation;

/**
 * Created by gfernandez on 31/10/16.
 */
@Module public class EstimationModule {

  public EstimationModule() {

  }

  @Provides @PerActivity @Named("estimation") BaseUseCase provideGetEstimationUseCase(GetEstimation getEstimation) {
    return getEstimation;
  }
}
