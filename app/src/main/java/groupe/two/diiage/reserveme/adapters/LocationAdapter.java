package groupe.two.diiage.reserveme.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.activities.LocationActivity;
import groupe.two.diiage.reserveme.models.Booking;
import groupe.two.diiage.reserveme.models.Location;
import groupe.two.diiage.reserveme.models.LocationBooking;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private final List<Location> locations;
    private final LayoutInflater mInflater;
    private final Context context;

    public LocationAdapter(Context context, List<Location> locations) {
        this.mInflater = LayoutInflater.from(context);
        this.locations = locations;
        this.context = context;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.locationName.setText(location.name);
        holder.locationNumberLimit.setText(String.valueOf(location.numberLimit));
        this.setIsAvailableNow(holder.locationIsAvailableNow, location);
        holder.parentLocationFragment.setOnClickListener(v -> {
            Intent intent = new Intent(this.context, LocationActivity.class);
            intent.putExtra(LocationActivity.PARAM_LOCATION, location.id);
            this.context.startActivity(intent);
        });
    }

    private void setIsAvailableNow(TextView locationIsAvailableNow, Location location) {
        List<Booking> bookings = this.getLocationBookingByDay(location, Calendar.getInstance());
        if (!bookings.isEmpty()) {
            locationIsAvailableNow.setText(R.string.not_available_now);
            locationIsAvailableNow.setTextColor(context.getColor(R.color.error));
        } else {
            locationIsAvailableNow.setText(R.string.available_now);
            locationIsAvailableNow.setTextColor(context.getColor(R.color.success));
        }
    }

    private List<Booking> getLocationBookingByDay(Location location, Calendar now) {
        LocalDateTime dateTime = LocalDateTime.of(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1, now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE));
        return location.bookings.stream().filter(booking -> booking.startAt.compareTo(dateTime) <= 0 && booking.endAt.compareTo(dateTime) >= 0).collect(Collectors.toList());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView locationName;
        private final TextView locationNumberLimit;
        private final TextView locationIsAvailableNow;
        private final ConstraintLayout parentLocationFragment;

        ViewHolder(View itemView) {
            super(itemView);
            this.locationName = itemView.findViewById(R.id.locationName);
            this.locationNumberLimit = itemView.findViewById(R.id.locationNumberLimit);
            this.locationIsAvailableNow = itemView.findViewById(R.id.locationIsAvailableNow);
            this.parentLocationFragment = itemView.findViewById(R.id.parentLocationFragment);
        }
    }

}