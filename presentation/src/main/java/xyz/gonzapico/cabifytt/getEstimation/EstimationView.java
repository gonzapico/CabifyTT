package xyz.gonzapico.cabifytt.getEstimation;

import java.util.List;
import xyz.gonzapico.data.entity.EstimateVehicle;

/**
 * Created by gfernandez on 8/11/16.
 */

public interface EstimationView {

  void showLoading();

  void hideLoading();

  void renderResults(List<EstimateVehicle> listOfVehicles);
}
