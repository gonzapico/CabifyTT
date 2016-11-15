package xyz.gonzapico.cabifytt.getEstimation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.gonzapico.cabifytt.R;

/**
 * Created by gfernandez on 14/11/16.
 */

public class VehicleViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.tvDescription) TextView tvDescription;
  @BindView(R.id.tvName) TextView tvName;
  @BindView(R.id.tvPrice) TextView tvPrice;

  public VehicleViewHolder(final View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
