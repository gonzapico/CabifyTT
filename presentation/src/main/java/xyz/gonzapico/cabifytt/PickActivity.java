package xyz.gonzapico.cabifytt;

import android.os.Bundle;

public class PickActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pick);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_pick;
  }
}
