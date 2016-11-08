package xyz.gonzapico.cabifytt.di.modules;

import com.elconfidencial.app.di.PerActivity;
import com.elconfidencial.domain.app.interactor.BaseUseCase;
import com.elconfidencial.domain.app.interactor.GetNew;
import com.elconfidencial.domain.app.interactor.GetNews;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by gfernandez on 31/10/16.
 */
@Module public class EstimationModule {

  public EstimationModule() {

  }

  @Provides @PerActivity @Named("new") BaseUseCase provideGetNewUseCase(GetNew getNew) {
    return getNew;
  }

  @Provides @PerActivity @Named("news") BaseUseCase provideGetNewsUseCase(GetNews getNews) {
    return getNews;
  }
}
