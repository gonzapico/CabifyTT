package xyz.gonzapico.cabifytt.di.components;

import dagger.Component;
import xyz.gonzapico.cabifytt.PickActivity;
import xyz.gonzapico.cabifytt.di.PerActivity;
import xyz.gonzapico.cabifytt.di.modules.ActivityModule;
import xyz.gonzapico.cabifytt.di.modules.EstimationModule;

/**
 * Created by gfernandez on 31/10/16.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class, EstimationModule.class
}) public interface EstimationComponent extends ActivityComponent {
  void inject(PickActivity pickActivity);
}