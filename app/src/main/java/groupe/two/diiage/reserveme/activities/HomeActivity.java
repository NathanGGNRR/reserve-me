package groupe.two.diiage.reserveme.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.adapters.BookingListAdapter;
import groupe.two.diiage.reserveme.models.Booking;
import groupe.two.diiage.reserveme.models.Participant;
import groupe.two.diiage.reserveme.repositories.BookingRepository;

public class HomeActivity extends AppCompatActivity {

    private EditText searchBox;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BookingRepository bRepos = new BookingRepository();

        RecyclerView bookingListView = findViewById(R.id.bookingListView);
        bookingListView.setLayoutManager(new LinearLayoutManager(this));
        bookingListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        this.searchBox = findViewById(R.id.searchBox);
        this.searchBtn = findViewById(R.id.searchButton);

        this.searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchBtn.setEnabled(s.toString().trim().length() > 0);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) { }
        });

        BookingListAdapter bookingListAdapter = new BookingListAdapter(bRepos.bookingList(""));
        bookingListAdapter.setBookingConsumer(this::navigateBookDetails);
        bookingListView.setAdapter(bookingListAdapter);
    }

    private void navigateBookDetails(Booking book) {
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra(BookingActivity.PARAM_BOOKING, book);
        startActivity(intent);
    }

    public void navigateToSearch(View view) {
        if (this.searchBox.getText() != null && !this.searchBox.getText().toString().equals("")) {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(SearchActivity.PARAM_LOCATION_NAME, this.searchBox.getText().toString());
            startActivity(intent);
        }
    }
}