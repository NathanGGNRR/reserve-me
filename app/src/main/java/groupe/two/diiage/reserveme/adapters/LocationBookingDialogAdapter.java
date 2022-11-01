package groupe.two.diiage.reserveme.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.activities.BookingActivity;
import groupe.two.diiage.reserveme.activities.LocationActivity;
import groupe.two.diiage.reserveme.models.LocationBooking;
import groupe.two.diiage.reserveme.models.User;

public class LocationBookingDialogAdapter extends RecyclerView.Adapter<LocationBookingDialogAdapter.ViewHolder> {

    private final List<LocationBooking> locationBookings;
    private final LayoutInflater mInflater;
    private final User currentUser;
    private final Context context;

    public LocationBookingDialogAdapter(Context context, List<LocationBooking> locationBookings, User currentUser) {
        this.mInflater = LayoutInflater.from(context);
        this.locationBookings = locationBookings;
        this.currentUser = currentUser;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_location_booking_dialog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocationBooking locationBooking = locationBookings.get(position);
        holder.locationBookingStartDate.setText(locationBooking.startAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        holder.locationBookingEndDate.setText(locationBooking.endAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        holder.navigateToBooking.setOnClickListener(v -> {
            Integer bookingId = locationBooking.id;
            Intent intent = new Intent(this.context, BookingActivity.class);
            intent.putExtra(BookingActivity.PARAM_BOOKING, bookingId);
            this.context.startActivity(intent);
        });
        if (locationBooking.bookedBy == this.currentUser.id) {
            holder.deleteBooking.setVisibility(View.VISIBLE);
            holder.deleteBooking.setOnClickListener(v -> {
                Integer bookingId = locationBooking.id;
                // REMOVE FROM LIST OF BOOKING OF THIS LOCATION
                // CALL API TO REMOVE BOOKINGS
            });
        }
    }

    @Override
    public int getItemCount() {
        return locationBookings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView locationBookingStartDate;
        private final TextView locationBookingEndDate;
        private final ImageButton deleteBooking;
        private final ImageButton navigateToBooking;

        ViewHolder(View itemView) {
            super(itemView);
            this.locationBookingStartDate = itemView.findViewById(R.id.locationStartDate);
            this.locationBookingEndDate = itemView.findViewById(R.id.locationEndDate);
            this.deleteBooking = itemView.findViewById(R.id.deleteBooking);
            this.navigateToBooking = itemView.findViewById(R.id.navigateToBooking);
        }
    }

}