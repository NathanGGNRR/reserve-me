package groupe.two.diiage.reserveme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.activities.LocationActivity;
import groupe.two.diiage.reserveme.activities.LoginActivity;
import groupe.two.diiage.reserveme.models.Participant;
import groupe.two.diiage.reserveme.mutables.ParticipantDAO;

public class AddBookingDialogAdapter extends RecyclerView.Adapter<AddBookingDialogAdapter.ViewHolder> {

    private final List<ParticipantDAO> participants;
    private final LayoutInflater mInflater;
    private final Context context;

    public AddBookingDialogAdapter(Context context, List<ParticipantDAO> participants) {
        this.mInflater = LayoutInflater.from(context);
        this.participants = participants;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_add_booking_dialog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ParticipantDAO participantDAO = participants.get(position);
        holder.participantName.setText(participantDAO.participant.userDisplay);
        holder.participantCheck.setOnCheckedChangeListener(null);
        holder.participantCheck.setChecked(participantDAO.checked);
        holder.participantCheck.setOnCheckedChangeListener(
        (buttonView, isChecked) -> {
            participantDAO.checked = isChecked;
            ((LocationActivity)context).refreshParticipantCount(isChecked ? 1 : -1);
        });
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView participantName;
        private final CheckBox participantCheck;

        ViewHolder(View itemView) {
            super(itemView);
            this.participantName = itemView.findViewById(R.id.participantName);
            this.participantCheck = itemView.findViewById(R.id.participantCheck);
        }
    }

}