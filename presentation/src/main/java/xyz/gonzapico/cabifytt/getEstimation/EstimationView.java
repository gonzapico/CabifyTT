package xyz.gonzapico.cabifytt.getEstimation;

import java.util.List;
import xyz.gonzapico.cabifytt.getEstimation.model.EstimateVechicle;

/**
 * Created by gfernandez on 8/11/16.
 */

public interface EstimationView {

  void showLoading();

  void hideLoading();

  void renderResults(List<EstimateVechicle> listOfVehicles);

  void showError(String errorMessage);
}
