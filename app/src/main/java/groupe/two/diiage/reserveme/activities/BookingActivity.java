package groupe.two.diiage.reserveme.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.adapters.ParticipantListAdapter;
import groupe.two.diiage.reserveme.models.Booking;
import groupe.two.diiage.reserveme.repositories.BookingRepository;
import groupe.two.diiage.reserveme.services.BookingService;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BookingActivity extends AppCompatActivity {

    private Booking booking;

    public static final String PARAM_BOOKING = "PARAM_BOOKING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        setActionBar();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);

        Intent intent = getIntent();
        this.booking = (Booking) intent.getSerializableExtra(this.PARAM_BOOKING);

        //BookingRepository bookingRepository = new BookingRepository();
        //this.booking = bookingRepository.bookingDetail();

        configureRecyclerView();
        TextView txtLocationDisplay = findViewById(R.id.id_booking_details_location_display);
        txtLocationDisplay.setText(this.booking.locationDisplay);

        TextView txtBookedBy = findViewById(R.id.id_booking_details_booked_by);
        txtBookedBy.setText("Booked By : " + this.booking.bookedBy);

        TextView txtStartAt = findViewById(R.id.id_booking_details_start_at);
        txtStartAt.setText("From : " + shortDateFormat.format(R.id.id_booking_details_start_at));

        TextView txtEndAt = findViewById(R.id.id_booking_details_end_at);
        txtEndAt.setText("To : " + shortDateFormat.format(R.id.id_booking_details_end_at));

        TextView txtBookAt = findViewById(R.id.id_booking_details_book_at);
        txtBookAt.setText("At : " + shortDateFormat.format(R.id.id_booking_details_book_at));
        Button button = findViewById(R.id.unbook);

        if(true == true)
        {
            button.setEnabled(false);
        }
    }

    private void setActionBar() {
        Toolbar actionBarToolbar = findViewById(R.id.backToolbar);
        setSupportActionBar(actionBarToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Booking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBarToolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    private void configureRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.id_recycler_participants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ParticipantListAdapter participantListAdapter = new ParticipantListAdapter(this, this.booking.participants);
        recyclerView.setAdapter(participantListAdapter);
    }
}