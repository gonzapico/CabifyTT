package xyz.gonzapico.cabifytt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.cabifytt.di.HasComponent;
import xyz.gonzapico.cabifytt.di.components.DaggerEstimationComponent;
import xyz.gonzapico.cabifytt.di.components.EstimationComponent;
import xyz.gonzapico.cabifytt.getEstimation.EstimationView;
import xyz.gonzapico.cabifytt.getEstimation.GetEstimationPresenter;
import xyz.gonzapico.cabifytt.getEstimation.Utils;
import xyz.gonzapico.cabifytt.getEstimation.adapter.VehicleTypeResultAdapter;
import xyz.gonzapico.cabifytt.getEstimation.model.EstimateVechicle;

public class PickActivity extends BaseActivity
    implements HasComponent<EstimationComponent>, EstimationView {
  private final String TAG = "PickActivity";
  @BindView(R.id.etStart) EditText etStart;
  @BindView(R.id.etEnd) EditText etEnd;
  @Inject GetEstimationPresenter mGetEstimationPresenter;

  @BindView(R.id.llGlobalPick) LinearLayout llGlobalPick;
  @BindView(R.id.pbLoading) ProgressBar pbLoading;
  @BindView(R.id.rvEstimateVehicles) RecyclerView rvEstimateVehicles;
  @BindView(R.id.llSort) LinearLayout llSort;

  private EstimationComponent mEstimationComponent;

  @OnClick(R.id.btSearch) void estimate() {
    mGetEstimationPresenter.getEstimation();
  }

  @OnClick(R.id.btSortExpensive) void sortAsc() {
    mGetEstimationPresenter.orderByPrice(true);
  }

  @OnClick(R.id.btSortCheap) void sortDesc() {
    mGetEstimationPresenter.orderByPrice(false);
  }

  @OnFocusChange(R.id.etStart) void startSearch() {
    mGetEstimationPresenter.suggestSearchPlace(mGetEstimationPresenter.suggestedStartSearch, Utils.PLACE_AUTOCOMPLETE_REQUEST_CODE_START,
        this);
  }

  @OnTextChanged(R.id.etStart) void suggestStartSearch() {
    mGetEstimationPresenter.suggestSearchPlace(mGetEstimationPresenter.suggestedStartSearch, Utils.PLACE_AUTOCOMPLETE_REQUEST_CODE_START,
        this);
  }

  @OnTextChanged(R.id.etEnd) void endSearch() {
    mGetEstimationPresenter.suggestSearchPlace(mGetEstimationPresenter.suggestedEndSearch, Utils.PLACE_AUTOCOMPLETE_REQUEST_CODE_END,
        this);
  }

  @OnFocusChange(R.id.etEnd) void suggestEndSearch() {
    mGetEstimationPresenter.suggestSearchPlace(mGetEstimationPresenter.suggestedEndSearch, Utils.PLACE_AUTOCOMPLETE_REQUEST_CODE_END,
        this);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeInjector();

    this.getComponent().inject(this);
    mGetEstimationPresenter.setEstimationView(this);
    rvEstimateVehicles.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
  }

  private void initializeInjector() {
    this.mEstimationComponent = DaggerEstimationComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_pick;
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    mGetEstimationPresenter.onActivityResult(requestCode, resultCode, data);
  }

  @Override public EstimationComponent getComponent() {
    return mEstimationComponent;
  }

  @Override public void showLoading() {
    pbLoading.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {

    pbLoading.setVisibility(View.GONE);
  }

  @Override public void renderResults(List<EstimateVechicle> listOfVehicles) {
    VehicleTypeResultAdapter adapterEstimateVehicles =
        new VehicleTypeResultAdapter(this, listOfVehicles);
    rvEstimateVehicles.setAdapter(adapterEstimateVehicles);
    adapterEstimateVehicles.notifyDataSetChanged();
  }

  @Override public void showError(String errorMessage) {
    Snackbar.make(llGlobalPick, errorMessage, Snackbar.LENGTH_LONG).show();
  }

  @Override public void hideResults() {
    llSort.setVisibility(View.GONE);
    rvEstimateVehicles.setVisibility(View.GONE);
  }

  @Override public void showResults() {
    llSort.setVisibility(View.VISIBLE);
    rvEstimateVehicles.setVisibility(View.VISIBLE);
  }

  @Override public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  @Override public void updateOrigin(String text) {

    etStart.setText(text);
  }

  @Override public void updateDestiny(String text) {
    etEnd.setText(text);
  }
}
