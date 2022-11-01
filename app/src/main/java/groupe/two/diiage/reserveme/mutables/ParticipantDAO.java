package groupe.two.diiage.reserveme.mutables;

import groupe.two.diiage.reserveme.models.Participant;

public class ParticipantDAO {

    public Participant participant;
    public Boolean checked;

    public ParticipantDAO(Participant participant, Boolean checked) {
        this.participant = participant;
        this.checked = checked;
    }
}
