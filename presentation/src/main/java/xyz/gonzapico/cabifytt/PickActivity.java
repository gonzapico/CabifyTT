package xyz.gonzapico.cabifytt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.cabifytt.di.HasComponent;
import xyz.gonzapico.cabifytt.di.components.DaggerEstimationComponent;
import xyz.gonzapico.cabifytt.di.components.EstimationComponent;
import xyz.gonzapico.cabifytt.getEstimation.GetEstimationPresenter;
import xyz.gonzapico.cabifytt.getEstimation.model.Location;
import xyz.gonzapico.cabifytt.getEstimation.model.RequestStops;
import xyz.gonzapico.cabifytt.getEstimation.model.Stop;

public class PickActivity extends BaseActivity implements HasComponent<EstimationComponent> {
  private final String TAG = "PickActivity";
  private final int PLACE_AUTOCOMPLETE_REQUEST_CODE_START = 1;
  private final int PLACE_AUTOCOMPLETE_REQUEST_CODE_END = 2;
  @BindView(R.id.etStart) EditText etStart;
  @BindView(R.id.etEnd) EditText etEnd;
  @Inject GetEstimationPresenter mGetEstimationPresenter;
  RequestStops requestStops = new RequestStops();
  List<Stop> stopList = new ArrayList<>();
  private boolean suggestedStartSearch = false;
  private boolean suggestedEndSearch = false;
  private EstimationComponent mEstimationComponent;

  @OnFocusChange(R.id.etStart) void suggestStartSearch() {
    if (!suggestedStartSearch) {
      try {
        Intent intent =
            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE_START);
      } catch (GooglePlayServicesRepairableException e) {
        // TODO: Handle the error.
      } catch (GooglePlayServicesNotAvailableException e) {
        // TODO: Handle the error.
      }
      suggestedStartSearch = true;
      suggestedEndSearch = false;
    }
  }

  @OnFocusChange(R.id.etEnd) void suggestEndSearch() {
    if (!suggestedEndSearch) {
      try {
        Intent intent =
            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE_END);
      } catch (GooglePlayServicesRepairableException e) {
        // TODO: Handle the error.
      } catch (GooglePlayServicesNotAvailableException e) {
        // TODO: Handle the error.
      }
      suggestedEndSearch = true;
      suggestedStartSearch = false;
    }
  }

  @OnClick(R.id.btSearch) void search() {

  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeInjector();

    this.getComponent().inject(this);
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
    if (resultCode == RESULT_OK) {
      Place place = PlaceAutocomplete.getPlace(this, data);
      SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      String currentDateFormatted = "";
      Date d = null;
      try {
        d = dataFormat.parse("2018-08-09 11:15");//catch exception
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        currentDateFormatted = dataFormat.format(d);
      }
      catch (Exception e){
        currentDateFormatted = dataFormat.format(new Date());
      }
      requestStops.setStartAt(currentDateFormatted);
      Log.i(TAG, "Place: " + place.getName());
      switch (requestCode) {
        case PLACE_AUTOCOMPLETE_REQUEST_CODE_START:
          Stop start = new Stop();
          List<Double> locationStartList = new ArrayList<>();
          locationStartList.add(place.getLatLng().latitude);
          locationStartList.add(place.getLatLng().longitude);
          start.setLocationList(locationStartList);
          start.setHitAt(currentDateFormatted);
          start.setName(place.getName().toString());
          start.setAddr(place.getAddress().toString());
          String[] partsOfAddress = place.getAddress().toString().split(",");
          try {
            start.setCity(partsOfAddress[partsOfAddress.length - 2].trim().split(" ")[1]);
          } catch (Exception e) {
            start.setCity(partsOfAddress[partsOfAddress.length - 2].trim().split(" ")[0]);
          }
          String num = "";
          try {
            int number = Integer.parseInt(partsOfAddress[1].trim());
            num = number + "";
          } catch (Exception e) {
            num = "S/N";
          }
          start.setNum(num);
          start.setCountry(partsOfAddress[partsOfAddress.length - 1].split(" ")[1]);

          stopList.add(start);

          etStart.setText(place.getName());
          /*
          if ((requestStops.getStops().get(0) != null) && (requestStops.getStops().get(1)
              != null)) {
            getEstimation(requestStops);
          }
          */
          break;
        case PLACE_AUTOCOMPLETE_REQUEST_CODE_END:
          Stop end = new Stop();
          List<Double> locationEndList = new ArrayList<>();
          locationEndList.add(place.getLatLng().latitude);
          locationEndList.add(place.getLatLng().longitude);
          end.setLocationList(locationEndList);
          end.setHitAt(currentDateFormatted);
          end.setName(place.getName().toString());
          end.setAddr(place.getAddress().toString());
          String[] partsOfAddressEnd = place.getAddress().toString().split(",");
          try {
            end.setCity(partsOfAddressEnd[partsOfAddressEnd.length - 2].trim().split(" ")[1]);
          } catch (Exception e) {
            end.setCity(partsOfAddressEnd[partsOfAddressEnd.length - 2].trim().split(" ")[0]);
          }
          String numEnd = "";
          try {
            int number = Integer.parseInt(partsOfAddressEnd[1].trim());
            numEnd = number + "";
          } catch (Exception e) {
            numEnd = "S/N";
          }
          end.setNum(numEnd);
          end.setCountry(partsOfAddressEnd[partsOfAddressEnd.length - 1].split(" ")[1]);

          stopList.add(end);
          etEnd.setText(place.getName());
          requestStops.setStops(stopList);
          if ((requestStops.getStops().get(0) != null) && (requestStops.getStops().get(1)
              != null)) {
            getEstimation(requestStops);
          }
          break;
      }
    } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
      Status status = PlaceAutocomplete.getStatus(this, data);
      // TODO: Handle the error.
      Log.i(TAG, status.getStatusMessage());
    } else if (resultCode == RESULT_CANCELED) {
      // The user canceled the operation.
    }
  }

  private void getEstimation(RequestStops stops) {
    mGetEstimationPresenter.getEstimation(stops);
  }

  @Override public EstimationComponent getComponent() {
    return mEstimationComponent;
  }
}
