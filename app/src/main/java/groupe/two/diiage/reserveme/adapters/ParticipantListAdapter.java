package groupe.two.diiage.reserveme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.models.Participant;

public class ParticipantListAdapter extends RecyclerView.Adapter<ParticipantListAdapter.ViewHolder> {

    private List<Participant> participants;
    private LayoutInflater mInflater;
    public ParticipantListAdapter(Context context, List<Participant> participants) {
        this.mInflater = LayoutInflater.from(context);
        this.participants = participants;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.participants_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Participant participant = participants.get(position);
        holder.participantName.setText(participant.userDisplay);
    }
    @Override
    public int getItemCount() {
        return participants.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView participantName;
        ViewHolder(View itemView) {
            super(itemView);
            this.participantName = itemView.findViewById(R.id.id_recycler_participants);
        }
    }
}
