package groupe.two.diiage.reserveme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.adapters.LocationAdapter;
import groupe.two.diiage.reserveme.models.Booking;
import groupe.two.diiage.reserveme.models.Location;
import groupe.two.diiage.reserveme.models.LocationObject;
import groupe.two.diiage.reserveme.models.Participant;
import groupe.two.diiage.reserveme.models.User;
import groupe.two.diiage.reserveme.mutables.ParticipantDAO;

public class SearchActivity extends AppCompatActivity {

    public final User currentUser = new User(1, "Nathan", "GAGNIARRE", LocalDateTime.of(1999, Month.SEPTEMBER.getValue(), 29,9,0,0), "natgag", LocalDateTime.now(), true, "fuioqispfp^po");

    public final ArrayList<Participant> participants = new ArrayList<Participant>() {{
        add(new Participant(1, "Geoffrey LIOTTE"));
        add(new Participant(2, "Damien MILLOT"));
        add(new Participant(3, "Loïc POPULIER"));
        add(new Participant(4, "Charlélie BOURDREUX"));
        add(new Participant(5, "Jordan PRUEDE"));
        add(new Participant(6, "Martin BALME"));
        add(new Participant(7, "Kim DOLE"));
        add(new Participant(8, "Valentin JACQUES"));
        add(new Participant(9, "Michel GIRARD"));
        add(new Participant(10, "Valentin VIROT"));
        add(new Participant(11, "Raphaël DELCROIX"));
        add(new Participant(12, "Clément HUGON"));
        add(new Participant(13, "Anne GUINOT"));
    }};

    public ArrayList<Booking> bookings;

    public final ArrayList<LocationObject> locationObjects = new ArrayList<LocationObject>() {{
        add(new LocationObject(1, "Projecteur", 1));
        add(new LocationObject(2, "Télévision", 2));
        add(new LocationObject(3, "Table", 1));
        add(new LocationObject(4, "Chaise", 6));
        add(new LocationObject(5, "Moquette", 1));
        add(new LocationObject(6, "Fenêtres", 3));
        add(new LocationObject(7, "Porte-manteau", 2));
        add(new LocationObject(8, "Poubelle", 2));
        add(new LocationObject(9, "Télécommande", 1));
        add(new LocationObject(10, "Stylo", 6));
    }};

    public ArrayList<Location> locations;

    public final ArrayList<ParticipantDAO> participantsDAO = new ArrayList<ParticipantDAO>() {{
        add(new ParticipantDAO(participants.get(0), false));
        add(new ParticipantDAO(participants.get(1), false));
        add(new ParticipantDAO(participants.get(2), false));
        add(new ParticipantDAO(participants.get(3), false));
        add(new ParticipantDAO(participants.get(4), false));
        add(new ParticipantDAO(participants.get(5), false));
        add(new ParticipantDAO(participants.get(6), false));
        add(new ParticipantDAO(participants.get(7), false));
        add(new ParticipantDAO(participants.get(8), false));
        add(new ParticipantDAO(participants.get(9), false));
        add(new ParticipantDAO(participants.get(10), false));
        add(new ParticipantDAO(participants.get(11), false));
        add(new ParticipantDAO(participants.get(12), false));
    }};


    private TextView searchLocationName;
    private TextView searchNumberLimit;
    private TextView searchStartDate;
    private TextView searchStartDateTime;
    private TextView searchEndDate;
    private TextView searchEndDateTime;
    public Calendar startDate;
    public Calendar endDate;
    public static final String PARAM_LOCATION_NAME = "PARAM_LOCATION_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setActionBar();
        setSearchComponent();
        Intent intent = getIntent();
        if (intent.getSerializableExtra(PARAM_LOCATION_NAME) != null) {
            this.searchLocationName.setText((String) intent.getSerializableExtra(PARAM_LOCATION_NAME));
            this.search(null);
        } else {
            this.configureRecyclerView(null);
        }
    }

    private void setActionBar() {
        Toolbar actionBarToolbar = findViewById(R.id.backToolbar);
        setSupportActionBar(actionBarToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBarToolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    private void setSearchComponent() {
        this.bookings = new ArrayList<Booking>() {{
            add(new Booking(1, LocalDateTime.of(2021, Month.APRIL.getValue(), 27, 13, 0, 0), LocalDateTime.of(2021, Month.APRIL.getValue(), 27, 17, 0, 0), LocalDateTime.now(), 1, "Salle 206", "Nathan GAGNIARRE",  new ArrayList<Participant>(participants.subList(0, 3))));
            add(new Booking(2, LocalDateTime.of(2021, Month.APRIL.getValue(), 28, 0, 0, 0), LocalDateTime.of(2021, Month.APRIL.getValue(), 30, 0, 0, 0), LocalDateTime.now(), 1, "Salle 206", "Nathan GAGNIARRE", new ArrayList<Participant>(participants.subList(2, 5))));
            add(new Booking(3, LocalDateTime.of(2021, Month.MAY.getValue(), 1, 0, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 1, 23, 59, 59), LocalDateTime.now(), 2, "Salle 207", "Geoffrey LIOTTE", new ArrayList<Participant>(participants.subList(4, 7))));
            add(new Booking(4, LocalDateTime.of(2021, Month.MAY.getValue(), 2, 0, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 2, 23, 59, 59), LocalDateTime.now(), 2, "Salle 207", "Geoffrey LIOTTE", new ArrayList<Participant>(participants.subList(6, 9))));
            add(new Booking(5, LocalDateTime.of(2021, Month.MAY.getValue(), 5, 0, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 5, 23, 59, 59), LocalDateTime.now(), 3, "Salle 208", "Geoffrey LIOTTE", new ArrayList<Participant>(participants.subList(8, 11))));
            add(new Booking(6, LocalDateTime.of(2021, Month.MAY.getValue(), 8, 13, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 8, 17, 0, 0), LocalDateTime.now(), 3, "Salle 208", "Loïc POPULIER", new ArrayList<Participant>(participants.subList(10, 13))));
            add(new Booking(7, LocalDateTime.of(2021, Month.MAY.getValue(), 4, 13, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 10, 17, 0, 0), LocalDateTime.now(), 4, "Salle 209", "Nathan GAGNIARRE", new ArrayList<Participant>(participants.subList(11, 13))));
            add(new Booking(8, LocalDateTime.of(2021, Month.MAY.getValue(), 15, 13, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 18, 17, 0, 0), LocalDateTime.now() , 4, "Salle 209" , "Nathan GAGNIARRE", new ArrayList<Participant>(participants.subList(0, 13))));
        }};
        this.locations = new ArrayList<Location>() {{
            add(new Location(1, "Salle 206", 6, "Le Lorem Ipsum est simplement du faux text, page de texte, comme Aldus PageMaker", "Étage 2 bâtiment C", null, LocalDate.now(), true, "Salle de réunion", new ArrayList<Booking>(bookings.subList(0, 2)), "Nathan GAGNIARRE", new ArrayList<LocationObject>(locationObjects.subList(0, 3))));
            add(new Location(2, "Salle 207", 8, "Le Lorem Ipsum est simplement du faux text, page de texte, comme Aldus PageMaker", "Étage 2 bâtiment C", null, LocalDate.now(), true, "Salle de classe", new ArrayList<Booking>(bookings.subList(2, 4)), "Nathan GAGNIARRE",  new ArrayList<LocationObject>(locationObjects.subList(3, 6))));
            add(new Location(3, "Salle 208", 3, "Le Lorem Ipsum est simplement du faux text, page de texte, comme Aldus PageMaker", "Étage 3 bâtiment C", null, LocalDate.now(), true, "Salle de repos", new ArrayList<Booking>(bookings.subList(4, 6)), "Geoffrey LIOTE", new ArrayList<LocationObject>(locationObjects.subList(6, 9))));
            add(new Location(4, "Salle 209", 4, "Le Lorem Ipsum est simplement du faux text, page de texte, comme Aldus PageMaker", "Étage 1 bâtiment A", null, LocalDate.now(), true, "Salle de réunion", new ArrayList<Booking>(bookings.subList(6, 8)),"Martin BALME", new ArrayList<LocationObject>(locationObjects.subList(9, 10))));
        }};
        this.searchLocationName = this.findViewById(R.id.textName);
        this.searchNumberLimit = this.findViewById(R.id.textNumberLimit);
        this.searchStartDate = this.findViewById(R.id.textStartDate);
        this.searchStartDateTime = this.findViewById(R.id.textStartDateTime);
        this.searchEndDate = this.findViewById(R.id.textEndDate);
        this.searchEndDateTime = this.findViewById(R.id.textEndDateTime);
        this.createDatePicker("Select start date", this.searchStartDate, this.searchStartDateTime, this.findViewById(R.id.datePickerStartDate), true);
        this.createDatePicker("Select end date", this.searchEndDate, this.searchEndDateTime,  this.findViewById(R.id.datePickerEndDate), false);
    }

    private void configureRecyclerView(List<Location> locations){
        RecyclerView recyclerView = findViewById(R.id.searchResults);
        TextView emptyRecyclerView = findViewById(R.id.emptySearchResults);
        if (locations == null || locations.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
            emptyRecyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyRecyclerView.setVisibility(View.INVISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            LocationAdapter locationAdapter = new LocationAdapter(this, locations);
            recyclerView.setAdapter(locationAdapter);
        }
    }

    public void search(View view) {
        if (this.startDate != null && this.endDate == null) {
            this.showError("End date can't be empty if search period", false, null);
        } else if (this.startDate == null && this.endDate != null) {
            this.showError("Start date can't be empty if search period", true, null);
        } else {
            List<Location> locationResults = this.locations.stream().filter(location -> {
                boolean corresponding = true;
                if (corresponding && this.searchLocationName.getText() != null && !this.searchLocationName.getText().toString().equals("")) {
                    corresponding = location.name.trim().toLowerCase().equals(this.searchLocationName.getText().toString().trim().toLowerCase()) || location.name.trim().toLowerCase().contains(this.searchLocationName.getText().toString().trim().toLowerCase());
                }
                if (corresponding && this.startDate != null && this.endDate != null) {
                    corresponding = this.isAvailable(location, this.startDate, this.endDate);
                }
                if (corresponding && this.searchNumberLimit.getText() != null && !this.searchNumberLimit.getText().toString().equals("")) {
                    corresponding = location.numberLimit == Integer.parseInt(this.searchNumberLimit.getText().toString());
                }
                return corresponding;
            }).collect(Collectors.toList());
            this.configureRecyclerView(locationResults);
        }
    }

    private Boolean isAvailable(Location location, Calendar startDate, Calendar endDate) {
        return location.bookings.stream().noneMatch(booking -> {
            LocalDateTime startDateTime = LocalDateTime.of(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH) + 1, startDate.get(Calendar.DAY_OF_MONTH), startDate.get(Calendar.HOUR_OF_DAY), startDate.get(Calendar.MINUTE));
            LocalDateTime endDateTime = LocalDateTime.of(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH) + 1, endDate.get(Calendar.DAY_OF_MONTH), endDate.get(Calendar.HOUR_OF_DAY), endDate.get(Calendar.MINUTE));
            return (startDateTime.compareTo(booking.startAt) >= 0 && startDateTime.compareTo(booking.endAt) <= 0) || (endDateTime.compareTo(booking.startAt) >= 0 && endDateTime.compareTo(booking.endAt) <= 0);
        });
    }

    private void createDatePicker(String title, TextView textDate, TextView textTime, ImageButton button, Boolean isStart) {
        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText(title);
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        button.setOnClickListener(v -> {
            if (!materialDatePicker.isVisible()) {
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                button.setClickable(false);
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(
                date -> {
                    LocalDateTime selectedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) date), TimeZone.getDefault().toZoneId());
                    if (isStart) {
                        if (this.endDate == null) {
                            this.startDate = Calendar.getInstance();
                            startDate.set(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth(), 0, 0);
                            this.setDateAndConfigureTime(textDate, textTime, isStart, button);
                        } else {
                            Calendar selectStartDate = Calendar.getInstance();
                            Calendar finalEndDate = Calendar.getInstance();
                            selectStartDate.set(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth(), 0, 0);
                            finalEndDate.set(this.endDate.get(Calendar.YEAR), this.endDate.get(Calendar.MONTH), this.endDate.get(Calendar.DAY_OF_MONTH), 0, 0);
                            finalEndDate.add(Calendar.DATE, 1);
                            if (selectStartDate.before(finalEndDate)) {
                                this.startDate = selectStartDate;
                                this.setDateAndConfigureTime(textDate, textTime, isStart, button);
                            } else {
                                materialDatePicker.dismiss();
                                this.showError("Start date must be before end date", isStart, button);
                            }
                        }
                    } else {
                        if (this.startDate == null) {
                            this.endDate = Calendar.getInstance();
                            endDate.set(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth(), 0, 0);
                            this.setDateAndConfigureTime(textDate, textTime, isStart, button);
                        } else {
                            Calendar selectEndDate = Calendar.getInstance();
                            Calendar finalStartDate = Calendar.getInstance();
                            selectEndDate.set(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth(), 0, 0);
                            finalStartDate.set(this.startDate.get(Calendar.YEAR), this.startDate.get(Calendar.MONTH), this.startDate.get(Calendar.DAY_OF_MONTH), 0, 0);
                            finalStartDate.add(Calendar.DATE, -1);
                            if (selectEndDate.after(finalStartDate)) {
                                this.endDate = selectEndDate;
                                this.setDateAndConfigureTime(textDate, textTime, isStart, button);
                            } else {
                                materialDatePicker.dismiss();
                                this.showError("End date must be after start date", isStart, button);
                            }
                        }
                    }
                }
        );
    }


    private void setDateAndConfigureTime(TextView textDate, TextView textTime, Boolean isStart, ImageButton button) {
        Calendar selectedDate = isStart ? this.startDate : this.endDate;
        textDate.setText(formatCalendarDate(selectedDate));

        MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("Select time start")
                .build();

        materialTimePicker.show(getSupportFragmentManager(), "MATERIAL_TIME_PICKER");

        materialTimePicker.addOnPositiveButtonClickListener(
                time -> {
                    Integer minutes = materialTimePicker.getHour() * 60 + materialTimePicker.getMinute();
                    if (isStart) {
                        if(this.endDate == null) {
                            this.setTime(materialTimePicker.getHour(), materialTimePicker.getMinute(), textTime, true, button);
                        } else {
                            String startDateDisplay = String.format("%s/%s/%s", this.startDate.get(Calendar.DAY_OF_MONTH), this.startDate.get(Calendar.MONTH), this.startDate.get(Calendar.YEAR));
                            String endDateDisplay = String.format("%s/%s/%s", this.endDate.get(Calendar.DAY_OF_MONTH), this.endDate.get(Calendar.MONTH), this.endDate.get(Calendar.YEAR));
                            if (startDateDisplay.equals(endDateDisplay)) {
                                Integer minutesEndDate = this.endDate.get(Calendar.HOUR) * 60 + this.endDate.get(Calendar.MINUTE);
                                if (minutesEndDate < minutes) {
                                    materialTimePicker.dismiss();
                                    this.showError("Start time must be before end time", isStart, button);
                                } else {
                                    this.setTime(materialTimePicker.getHour(), materialTimePicker.getMinute(), textTime, true, button);
                                }
                            } else {
                                this.setTime(materialTimePicker.getHour(), materialTimePicker.getMinute(), textTime, true, button);
                            }
                        }
                    } else {
                        if(this.startDate == null) {
                            this.setTime(materialTimePicker.getHour(), materialTimePicker.getMinute(), textTime, false, button);
                        } else {
                            String startDateDisplay = String.format("%s/%s/%s", this.startDate.get(Calendar.DAY_OF_MONTH), this.startDate.get(Calendar.MONTH), this.startDate.get(Calendar.YEAR));
                            String endDateDisplay = String.format("%s/%s/%s", this.endDate.get(Calendar.DAY_OF_MONTH), this.endDate.get(Calendar.MONTH), this.endDate.get(Calendar.YEAR));
                            if (startDateDisplay.equals(endDateDisplay)) {
                                Integer minutesStartDate = this.startDate.get(Calendar.HOUR) * 60 + this.startDate.get(Calendar.MINUTE);
                                if (minutesStartDate > minutes) {
                                    materialTimePicker.dismiss();
                                    this.showError("End time must be after start time", isStart, button);
                                } else {
                                    this.setTime(materialTimePicker.getHour(), materialTimePicker.getMinute(), textTime, false, button);
                                }
                            } else {
                                this.setTime(materialTimePicker.getHour(), materialTimePicker.getMinute(), textTime, false, button);
                            }
                        }
                    }
                }
        );
    }

    private String formatCalendarDate(Calendar date) {
        return String.format("%s/%s/%s", date.get(Calendar.DAY_OF_MONTH) < 10 ? String.format("0%s", date.get(Calendar.DAY_OF_MONTH)) : date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.MONTH) < 10 ? String.format("0%s", date.get(Calendar.MONTH) + 1) : date.get(Calendar.MONTH), date.get(Calendar.YEAR));
    }

    private void setTime(Integer hours, Integer minutes, TextView textTime, Boolean isStart, ImageButton button) {
        String timeDisplay = String.format("%s:%s", hours, minutes);
        Calendar selectedDate = isStart ? this.startDate : this.endDate;
        selectedDate.set(Calendar.HOUR, hours);
        selectedDate.set(Calendar.MINUTE, minutes);
        textTime.setText(timeDisplay);
        button.setClickable(true);
    }


    private void showError(String message, Boolean isStart, ImageButton button) {
        TextView errorText = isStart ? this.findViewById(R.id.errorStartDate) : this.findViewById(R.id.errorEndDate);
        errorText.setText(message);
        errorText.setVisibility(View.VISIBLE);
        if (button != null) {
            button.setClickable(true);
        }
    }

    public void deleteName(View view) {
        this.searchLocationName.setText("");
    }

    public void deleteNumberLimit(View view) {
        this.searchNumberLimit.setText("");
    }

    public void deleteDate(View view) {
        this.searchStartDate.setText("");
        this.searchStartDateTime.setText("");
        this.searchEndDate.setText("");
        this.searchEndDateTime.setText("");
        this.startDate = null;
        this.endDate = null;
    }


}