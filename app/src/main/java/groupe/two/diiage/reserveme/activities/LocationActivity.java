package groupe.two.diiage.reserveme.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.adapters.AddBookingDialogAdapter;
import groupe.two.diiage.reserveme.adapters.LocationBookingDialogAdapter;
import groupe.two.diiage.reserveme.models.Location;
import groupe.two.diiage.reserveme.models.LocationBooking;
import groupe.two.diiage.reserveme.models.LocationObject;
import groupe.two.diiage.reserveme.adapters.LocationObjectAdapter;
import groupe.two.diiage.reserveme.models.Participant;
import groupe.two.diiage.reserveme.mutables.ParticipantDAO;
import groupe.two.diiage.reserveme.models.User;

public class LocationActivity extends AppCompatActivity {

    public final User currentUser = new User(1, "Nathan", "GAGNIARRE", LocalDateTime.of(1999, Month.SEPTEMBER.getValue(), 29,9,0,0), "natgag", LocalDateTime.now(), true, "fuioqispfp^po");

    public final ArrayList<LocationBooking> bookings = new ArrayList<LocationBooking>() {{
        add(new LocationBooking(1, LocalDateTime.of(2021, Month.APRIL.getValue(), 27, 13, 0, 0), LocalDateTime.of(2021, Month.APRIL.getValue(), 27, 17, 0, 0), 1, "Nathan GAGNIARRE"));
        add(new LocationBooking(2, LocalDateTime.of(2021, Month.APRIL.getValue(), 28, 0, 0, 0), LocalDateTime.of(2021, Month.APRIL.getValue(), 30, 0, 0, 0), 1, "Nathan GAGNIARRE"));
        add(new LocationBooking(3, LocalDateTime.of(2021, Month.MAY.getValue(), 1, 0, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 1, 23, 59, 59), 2, "Geoffrey LIOTTE"));
        add(new LocationBooking(4, LocalDateTime.of(2021, Month.MAY.getValue(), 2, 0, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 2, 23, 59, 59), 2, "Geoffrey LIOTTE"));
        add(new LocationBooking(5, LocalDateTime.of(2021, Month.MAY.getValue(), 5, 0, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 5, 23, 59, 59), 2, "Geoffrey LIOTTE"));
        add(new LocationBooking(6, LocalDateTime.of(2021, Month.MAY.getValue(), 8, 13, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 8, 17, 0, 0), 3, "Loïc POPULIER"));
        add(new LocationBooking(7, LocalDateTime.of(2021, Month.MAY.getValue(), 4, 13, 0, 0), LocalDateTime.of(2021, Month.MAY.getValue(), 10, 17, 0, 0), 1, "Nathan GAGNIARRE"));
    }};

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
    public Location location;

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

    public TextView numberSelectedPerson;
    public Calendar startDate;
    public Calendar endDate;
    public static final String PARAM_LOCATION = "PARAM_LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setActionBar();
        this.locations =  new ArrayList<Location>() {{
            add(new Location(1, "Salle 206", 6, "Le Lorem Ipsum est simplement du faux text, page de texte, comme Aldus PageMaker", "Étage 2 bâtiment C", null, LocalDate.now(), true, "Salle de réunion", "Nathan GAGNIARRE", new ArrayList<LocationBooking>(bookings.subList(0, 2)), new ArrayList<LocationObject>(locationObjects.subList(0, 3))));
            add(new Location(2, "Salle 207", 8, "Le Lorem Ipsum est simplement du faux text, page de texte, comme Aldus PageMaker", "Étage 2 bâtiment C", null, LocalDate.now(), true, "Salle de classe", "Nathan GAGNIARRE",new ArrayList<LocationBooking>(bookings.subList(2, 4)), new ArrayList<LocationObject>(locationObjects.subList(3, 6))));
            add(new Location(3, "Salle 208", 3, "Le Lorem Ipsum est simplement du faux text, page de texte, comme Aldus PageMaker", "Étage 3 bâtiment C", null, LocalDate.now(), true, "Salle de repos", "Geoffrey LIOTE",new ArrayList<LocationBooking>(bookings.subList(4, 6)), new ArrayList<LocationObject>(locationObjects.subList(6, 9))));
            add(new Location(4, "Salle 209", 4, "Le Lorem Ipsum est simplement du faux text, page de texte, comme Aldus PageMaker", "Étage 1 bâtiment A", null, LocalDate.now(), true, "Salle de réunion", "Martin BALME",new ArrayList<LocationBooking>(bookings.subList(6, 7)), new ArrayList<LocationObject>(locationObjects.subList(9, 10))));
        }};
        Intent intent = getIntent();
        if (intent.getSerializableExtra(PARAM_LOCATION) != null) {
            this.location = this.locations.stream().filter(location -> location.id.toString().equals(intent.getSerializableExtra(PARAM_LOCATION).toString())).findFirst().orElse(null);
        } else {
            this.location = new Location(
                1,
                "Salle 206",
                6,
                "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker",
                "Étage 2 bâtiment C",
                null,
                LocalDate.now(),
                true,
                "Salle de réunion",
                "Nathan GAGNIARRE",
                this.bookings,
                this.locationObjects
            );
        }
        this.configure();
    }

    private void configure() {
        this.configureRecyclerView();
        this.configureCalendarView();
        this.configureAvailableNow();
        this.configureComponent();
    }

    private void configureComponent() {
        TextView titleLocation = this.findViewById(R.id.titleLocation);
        TextView descriptionLocation = this.findViewById(R.id.descriptionLocation);
        TextView complementInformationLocation = this.findViewById(R.id.complementInformationLocation);
        TextView numberLimit = this.findViewById(R.id.numberLimit);
        TextView ownedBy = this.findViewById(R.id.ownedBy);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        titleLocation.setText(String.format("%s - %s (%s)", location.name, location.locationTypeDisplay, location.createdAt.format(formatter)));
        descriptionLocation.setText(location.description);
        complementInformationLocation.setText(location.complementInformation);
        numberLimit.setText(String.format("Limited by %s participant%s", location.numberLimit, location.numberLimit > 1 ? "s" : ""));
        ownedBy.setText(String.format("Owned by %s", location.ownerDisplay));
    }

    private void configureRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.locationObjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LocationObjectAdapter locationObjectAdapter = new LocationObjectAdapter(this, this.location.locationObjects);
        recyclerView.setAdapter(locationObjectAdapter);
    }

    private void configureCalendarView(){
        CalendarView calendarView = findViewById(R.id.bookingsCalendarView);
        calendarView.setOnDayClickListener(this::onDayClicked);
        List<EventDay> events = new ArrayList<>();
        for (LocationBooking booking : this.location.locationBookings) {
            Calendar startDate = Calendar.getInstance();
            startDate.set(booking.startAt.getYear(), booking.startAt.getMonthValue() - 1, booking.startAt.getDayOfMonth(), 0, 0);
            Calendar endDate = Calendar.getInstance();
            endDate.set(booking.endAt.getYear(), booking.endAt.getMonthValue() - 1, booking.endAt.getDayOfMonth(), 0, 0);
            // To add event when start and end date is equal, add one day
            endDate.add(Calendar.DATE, 1);
            String startDateDisplay = String.format("%s/%s/%s", startDate.get(Calendar.DAY_OF_MONTH), startDate.get(Calendar.MONTH), startDate.get(Calendar.YEAR));
            String endDateDisplay = String.format("%s/%s/%s", endDate.get(Calendar.DAY_OF_MONTH), endDate.get(Calendar.MONTH), endDate.get(Calendar.YEAR));
            while (!startDateDisplay.equals(endDateDisplay)) {
                Calendar finalStartDate = Calendar.getInstance();
                finalStartDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH), 0, 0);
                this.addEventDay(events, finalStartDate, startDateDisplay);
                startDate.add(Calendar.DATE, 1);
                startDateDisplay = String.format("%s/%s/%s", startDate.get(Calendar.DAY_OF_MONTH), startDate.get(Calendar.MONTH), startDate.get(Calendar.YEAR));
            }
        }
        calendarView.setEvents(events);
    }

    private void configureAvailableNow(){
        List<LocationBooking> locationBookings = this.getLocationBookingByDateAndHour(Calendar.getInstance());
        TextView availableNow = this.findViewById(R.id.availableIcon);
        if (!locationBookings.isEmpty()) {
            availableNow.setText(getString(R.string.not_available_now));
            availableNow.setTextColor(getColor((R.color.error)));
        } else {
            availableNow.setText(getString(R.string.available_now));
            availableNow.setTextColor(getColor((R.color.success)));
        }
    }

    private void addEventDay(List<EventDay> events, Calendar date, String startDateDisplay) {
        try {
            EventDay alreadyExists = events.stream().filter(event -> {
                String eventDateDisplay = String.format("%s/%s/%s", event.getCalendar().get(Calendar.DAY_OF_MONTH), event.getCalendar().get(Calendar.MONTH), event.getCalendar().get(Calendar.YEAR));
                return eventDateDisplay.equals(startDateDisplay);
            }).findFirst().get();
            int indexEventDay = events.indexOf(alreadyExists);
            events.set(indexEventDay, new EventDay(date, R.drawable.ic_ellipsis));
        } catch (NoSuchElementException | NullPointerException e){
            events.add(new EventDay(date, R.drawable.ic_black_circle));
        }
    }

    private void onDayClicked(EventDay eventDay) {
        Calendar dateClicked = eventDay.getCalendar();
        String dateClickedDisplay = formatCalendarDate(dateClicked);
        List<LocationBooking> locationBookings = this.getLocationBookingByDate(dateClicked);
        this.showLocationBookingDialog(locationBookings, dateClickedDisplay, dateClicked);
    }

    private List<LocationBooking> getLocationBookingByDate(Calendar dateClicked) {
        LocalDate date = LocalDate.of(dateClicked.get(Calendar.YEAR), dateClicked.get(Calendar.MONTH) + 1, dateClicked.get(Calendar.DAY_OF_MONTH));
        return this.location.locationBookings.stream().filter(booking -> {
            LocalDate startDate = LocalDate.of(booking.startAt.getYear(), booking.startAt.getMonth(), booking.startAt.getDayOfMonth());
            LocalDate endDate = LocalDate.of(booking.endAt.getYear(), booking.endAt.getMonth(), booking.endAt.getDayOfMonth());
            return startDate.compareTo(date) <= 0 && endDate.compareTo(date) >= 0;
        }).collect(Collectors.toList());
    }

    private List<LocationBooking> getLocationBookingByDateAndHour(Calendar dateClicked) {
        LocalDateTime dateTime = LocalDateTime.of(dateClicked.get(Calendar.YEAR), dateClicked.get(Calendar.MONTH) + 1, dateClicked.get(Calendar.DAY_OF_MONTH), dateClicked.get(Calendar.HOUR_OF_DAY), dateClicked.get(Calendar.MINUTE));
        return this.location.locationBookings.stream().filter(booking -> booking.startAt.compareTo(dateTime) <= 0 && booking.endAt.compareTo(dateTime) >= 0).collect(Collectors.toList());
    }

    private void setActionBar() {
        Toolbar actionBarToolbar = findViewById(R.id.backToolbar);
        setSupportActionBar(actionBarToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBarToolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    private void showLocationBookingDialog(List<LocationBooking> locationBookings, String clickedDate, Calendar dateClicked) {
        final Dialog dialog = this.createDialog(R.layout.location_booking_dialog);
        TextView dialogTitle = dialog.findViewById(R.id.titleDialogLocationBooking);
        RecyclerView recyclerView = dialog.findViewById(R.id.locationBookingsDialog);
        ImageButton addButton = dialog.findViewById(R.id.addBooking);

        String title = getString(R.string.location_booking_dialog_title);
        if (clickedDate != null) {
            title = String.format("%s for the %s", getString(R.string.location_booking_dialog_title), clickedDate);
        }

        // SET TITLE
        dialogTitle.setText(title);

        if (locationBookings.isEmpty()) {
            ConstraintLayout parentHeaderLocationBookingDialogFragment = dialog.findViewById(R.id.parentHeaderLocationBookingDialogFragment);
            TextView emptyLocationBooking = dialog.findViewById(R.id.emptyLocationBooking);

            parentHeaderLocationBookingDialogFragment.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            emptyLocationBooking.setVisibility(View.VISIBLE);
        } else {
            // SET LIST
            recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
            LocationBookingDialogAdapter locationBookingDialogAdapter = new LocationBookingDialogAdapter(dialog.getContext(), locationBookings, this.currentUser);
            recyclerView.setAdapter(locationBookingDialogAdapter);
        }

        addButton.setOnClickListener(v -> {
            showAddBookingDialog(dateClicked);
        });

        dialog.show();
    }

    private Dialog createDialog(int layout){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(layout);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        return dialog;
    }

    public void onAddBookingClick(View view){
        this.showAddBookingDialog(null);
    }

    private void showAddBookingDialog(Calendar dateClicked) {
        final Dialog dialog = this.createDialog(R.layout.add_booking_dialog);
        RecyclerView recyclerViewParticipant = dialog.findViewById(R.id.bookingParticipants);
        RecyclerView recyclerViewExpand = dialog.findViewById(R.id.expandListBookings);
        ConstraintLayout expandBookings = dialog.findViewById(R.id.expandBookings);
        ConstraintLayout expandBookingsList = dialog.findViewById(R.id.expandBookingsList);
        ImageView arrowExpand = dialog.findViewById(R.id.arrowExpand);
        Button cancelButton = dialog.findViewById(R.id.cancelButton);
        Button addButton = dialog.findViewById(R.id.addButton);
        TextView textStartDate = dialog.findViewById(R.id.textStartDate);
        TextView textStartDateTime = dialog.findViewById(R.id.textStartDateTime);
        TextView textEndDate = dialog.findViewById(R.id.textEndDate);
        TextView textEndDateTime = dialog.findViewById(R.id.textEndDateTime);

        this.numberSelectedPerson = dialog.findViewById(R.id.numberSelectedPerson);

        this.createDatePicker(dialog,"Select start date", textStartDate, textStartDateTime, dialog.findViewById(R.id.datePickerStartDate), true);
        this.createDatePicker(dialog, "Select end date", textEndDate, textEndDateTime, dialog.findViewById(R.id.datePickerEndDate), false);

        if (dateClicked != null) {
            this.startDate = dateClicked;
            textStartDate.setText(formatCalendarDate(this.startDate));
            textStartDateTime.setText(R.string.midnight);
        }

        if (!this.location.locationBookings.isEmpty()) {
            expandBookings.setOnClickListener(v -> clickExpandBookings(v, expandBookingsList, arrowExpand));
            recyclerViewExpand.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
            LocationBookingDialogAdapter locationBookingDialogAdapterExpand = new LocationBookingDialogAdapter(dialog.getContext(), this.location.locationBookings, this.currentUser);
            recyclerViewExpand.setAdapter(locationBookingDialogAdapterExpand);
        }
        recyclerViewParticipant.setLayoutManager(new LinearLayoutManager(this));
        AddBookingDialogAdapter addBookingDialogAdapterParticipant = new AddBookingDialogAdapter(this, this.participantsDAO);
        recyclerViewParticipant.setAdapter(addBookingDialogAdapterParticipant);

        cancelButton.setOnClickListener(v -> cancelDialog(v, dialog));
        addButton.setOnClickListener(v -> addBooking(v, dialog));

        dialog.show();
    }

    private void clickExpandBookings (View view, ConstraintLayout expandBookingsList, ImageView arrowExpand) {
        TransitionManager.beginDelayedTransition(expandBookingsList, new AutoTransition());
        expandBookingsList.setVisibility(expandBookingsList.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        arrowExpand.setImageDrawable(expandBookingsList.getVisibility() == View.GONE ? getDrawable(R.drawable.ic_expand_more) : getDrawable(R.drawable.ic_expand_less));
    }

    private void cancelDialog(View view, Dialog dialog){
        dialog.dismiss();
    }

    private void addBooking(View view, Dialog dialog){
        if (this.startDate == null || this.endDate == null) {
            if (this.startDate == null) {
                this.showError(dialog, "Must have start date", true, dialog.findViewById(R.id.datePickerStartDate));
            }
            if (this.endDate == null) {
                this.showError(dialog, "Must have end date", false, dialog.findViewById(R.id.datePickerEndDate));
            }
        } else {
            // CALL API
            LocalDateTime startLocalDateTime = LocalDateTime.of(this.startDate.get(Calendar.YEAR), this.startDate.get(Calendar.MONTH) + 1, this.startDate.get(Calendar.DAY_OF_MONTH), this.startDate.get(Calendar.HOUR_OF_DAY), this.startDate.get(Calendar.MINUTE));
            LocalDateTime endLocalDateTime = LocalDateTime.of(this.endDate.get(Calendar.YEAR), this.endDate.get(Calendar.MONTH) + 1, this.endDate.get(Calendar.DAY_OF_MONTH), this.endDate.get(Calendar.HOUR_OF_DAY), this.endDate.get(Calendar.MINUTE));
            LocationBooking newLocationBooking = new LocationBooking(8, startLocalDateTime, endLocalDateTime, 1, "Nathan GAGNIARRE");
            this.location.locationBookings.add(newLocationBooking);
            this.configure();
            dialog.dismiss();
        }
    }

    private void createDatePicker(Dialog dialog, String title, TextView textDate, TextView textTime, ImageButton button, Boolean isStart) {
        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText(title);
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        button.setOnClickListener(v -> {
            if (!materialDatePicker.isVisible()) {
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                button.setClickable(false);
                hideError(dialog);
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(
                date -> {
                    LocalDateTime selectedDate = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) date), TimeZone.getDefault().toZoneId());
                    if (isStart) {
                        if (this.endDate == null) {
                            this.startDate = Calendar.getInstance();
                            startDate.set(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth(), 0, 0);
                            this.setDateAndConfigureTime(dialog, textDate, textTime, isStart, button);
                        } else {
                            Calendar selectStartDate = Calendar.getInstance();
                            Calendar finalEndDate = Calendar.getInstance();
                            selectStartDate.set(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth(), 0, 0);
                            finalEndDate.set(this.endDate.get(Calendar.YEAR), this.endDate.get(Calendar.MONTH), this.endDate.get(Calendar.DAY_OF_MONTH), 0, 0);
                            finalEndDate.add(Calendar.DATE, 1);
                            if (selectStartDate.before(finalEndDate)) {
                                this.startDate = selectStartDate;
                                this.setDateAndConfigureTime(dialog, textDate, textTime, isStart, button);
                            } else {
                                materialDatePicker.dismiss();
                                this.showError(dialog, "Start date must be before end date", isStart, button);
                            }
                        }
                    } else {
                        if (this.startDate == null) {
                            this.endDate = Calendar.getInstance();
                            endDate.set(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth(), 0, 0);
                            this.setDateAndConfigureTime(dialog, textDate, textTime, isStart, button);
                        } else {
                            Calendar selectEndDate = Calendar.getInstance();
                            Calendar finalStartDate = Calendar.getInstance();
                            selectEndDate.set(selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth(), 0, 0);
                            finalStartDate.set(this.startDate.get(Calendar.YEAR), this.startDate.get(Calendar.MONTH), this.startDate.get(Calendar.DAY_OF_MONTH), 0, 0);
                            finalStartDate.add(Calendar.DATE, -1);
                            if (selectEndDate.after(finalStartDate)) {
                                this.endDate = selectEndDate;
                                this.setDateAndConfigureTime(dialog, textDate, textTime, isStart, button);
                            } else {
                                materialDatePicker.dismiss();
                                this.showError(dialog,"End date must be after start date", isStart, button);
                            }
                        }
                    }
                }
        );
    }

    public void displayAllBookings(View view) {
        this.showLocationBookingDialog(this.location.locationBookings, null, null);
    }

    public void refreshParticipantCount(Integer amount) {
        Integer count = Integer.parseInt(this.numberSelectedPerson.getText().toString());
        this.numberSelectedPerson.setText(String.valueOf(count + amount));
        if (count + amount > this.location.numberLimit) {
            this.numberSelectedPerson.setTextColor(getColor(R.color.error));
        } else {
            this.numberSelectedPerson.setTextColor(getColor(R.color.secondary));
        }
    }

    private void setDateAndConfigureTime(Dialog dialog, TextView textDate, TextView textTime, Boolean isStart, ImageButton button) {
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
                                this.showError(dialog,"Start time must be before end time", isStart, button);
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
                                this.showError(dialog, "End time must be after start time", isStart, button);
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

    private void showError(Dialog dialog, String message, Boolean isStart, ImageButton button) {
        TextView errorText = isStart ? dialog.findViewById(R.id.errorStartDate) : dialog.findViewById(R.id.errorEndDate);
        errorText.setText(message);
        errorText.setVisibility(View.VISIBLE);
        button.setClickable(true);
    }

    private void hideError(Dialog dialog) {
        TextView errorStartDateText = dialog.findViewById(R.id.errorStartDate);
        TextView errorEndDateText = dialog.findViewById(R.id.errorEndDate);
        errorStartDateText.setVisibility(View.INVISIBLE);
        errorStartDateText.setText("");
        errorEndDateText.setVisibility(View.INVISIBLE);
        errorEndDateText.setText("");
    }
}