package groupe.two.diiage.reserveme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.models.LocationObject;

public class LocationObjectAdapter extends RecyclerView.Adapter<LocationObjectAdapter.ViewHolder> {

    private final List<LocationObject> locationObjects;
    private final LayoutInflater mInflater;

    public LocationObjectAdapter(Context context, List<LocationObject> locationObjects) {
        this.mInflater = LayoutInflater.from(context);
        this.locationObjects = locationObjects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_location_object, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocationObject locationObject = locationObjects.get(position);
        holder.locationObjectName.setText(locationObject.locationObjectDisplay);
        holder.locationObjectQuantity.setText(String.valueOf(locationObject.quantity));
    }

    @Override
    public int getItemCount() {
        return locationObjects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView locationObjectName;
        private final TextView locationObjectQuantity;

        ViewHolder(View itemView) {
            super(itemView);
            this.locationObjectName = itemView.findViewById(R.id.locationObjectName);
            this.locationObjectQuantity = itemView.findViewById(R.id.locationObjectQuantity);
        }
    }

}