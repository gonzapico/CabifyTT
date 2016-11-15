package xyz.gonzapico.cabifytt.getEstimation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.cabifytt.R;
import xyz.gonzapico.cabifytt.di.PerActivity;
import xyz.gonzapico.cabifytt.getEstimation.model.EstimateVechicle;

/**
 * Created by gfernandez on 14/11/16.
 */

@PerActivity public class VehicleTypeResultAdapter extends RecyclerView.Adapter<VehicleViewHolder> {
  private final LayoutInflater layoutInflater;
  private List<EstimateVechicle> elementsCollection = new ArrayList<>();
  private Context mContext;

  @Inject
  public VehicleTypeResultAdapter(Context context, List<EstimateVechicle> collectionOfRelatedNews) {
    this.mContext = context;
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.elementsCollection.addAll(collectionOfRelatedNews);
  }

  @Override public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_vehicle_type_result, parent, false);
    return new VehicleViewHolder(view);
  }

  @Override public void onBindViewHolder(VehicleViewHolder holder, int position) {
    final EstimateVechicle element = this.elementsCollection.get(position);
    holder.tvDescription.setText(element.getVehicleType().getDescription());
    holder.tvName.setText(element.getVehicleType().getName());
    holder.tvPrice.setText(element.getPriceFormatted());
  }

  @Override public int getItemCount() {
    return (this.elementsCollection != null) ? this.elementsCollection.size() : 0;
  }
}
