package groupe.two.diiage.reserveme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.models.Booking;

public class BookingListAdapter extends RecyclerView.Adapter<BookingListViewHolder> {

    private List<Booking> bookingList;

    private Consumer<Booking> bookingConsumer;

    public BookingListAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
        this.bookingConsumer = p -> {};
    }

    public void setBookingConsumer(Consumer<Booking> personConsumer) { this.bookingConsumer = personConsumer; }

    @NonNull
    @Override
    public BookingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_row_layout, parent, false);
        return new BookingListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingListViewHolder holder, int position) {
        Booking booking = bookingList.get(position);

        holder.txtLocation.setText(booking.locationDisplay);
        holder.txtStartAt.setText(this.getStrDate(booking.startAt));
        holder.txtEndAt.setText(this.getStrDate(booking.endAt));
        holder.txtBookAt.setText(this.getStrDate(booking.bookAt));
        holder.txtParticipantNbr.setText(String.valueOf(booking.participants.size()));

        holder.itemView.setOnClickListener(v -> {
            bookingConsumer.accept(booking);
        });
    }
    @Override
    public int getItemCount() {
        return bookingList.size();
    }


    private String getStrDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }
}
