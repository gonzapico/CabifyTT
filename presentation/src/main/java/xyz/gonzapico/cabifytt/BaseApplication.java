package xyz.gonzapico.cabifytt;

import android.app.Application;
import android.os.StrictMode;
import xyz.gonzapico.cabifytt.di.components.ApplicationComponent;
import xyz.gonzapico.cabifytt.di.modules.ApplicationModule;

/**
 * Created by gfernandez on 8/11/16.
 */

public class BaseApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    this.initializeStrictMode();
    super.onCreate();
    this.initializeInjector();
  }

  private void initializeInjector() {
    this.applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }

  private void initializeStrictMode() {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
          .detectDiskWrites()
          .detectNetwork()
          .penaltyLog()
          .build());
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
          .detectLeakedClosableObjects()
          .penaltyLog()
          .penaltyDeath()
          .build());
    }
  }
}
