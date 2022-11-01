package groupe.two.diiage.reserveme.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import groupe.two.diiage.reserveme.models.Booking;
import groupe.two.diiage.reserveme.models.Participant;

public class BookingRepository {
    public Booking bookingDetail()
    {

        ArrayList<Participant> participants = new ArrayList<Participant>();
        Participant participant = new Participant(1, "Allo");
        Participant participant2 = new Participant(2, "Tu m'entend");
        participants.add(participant);
        participants.add(participant2);
        LocalDateTime date = LocalDateTime.now();
        Booking booking = new Booking(1, date, date, date, 1, "Dooo", "Kooooo", participants);
        return booking;
    }

    public List<Booking> bookingList(String filter)
    {
        List<Booking> bookingArray = new ArrayList<>();
        List<Participant> participantArray = new ArrayList<>();

        participantArray.add(new Participant(0, "Salut"));
        participantArray.add(new Participant(0, "Salut2"));
        bookingArray.add(new Booking(0, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 0, "Oui1", "Non1", (ArrayList<Participant>) participantArray));
        bookingArray.add(new Booking(0, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 0, "Oui23", "Non1", (ArrayList<Participant>) participantArray));
        bookingArray.add(new Booking(0, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 0, "Oui456789", "Oui2", (ArrayList<Participant>) participantArray));

        return bookingArray;
    }
}
