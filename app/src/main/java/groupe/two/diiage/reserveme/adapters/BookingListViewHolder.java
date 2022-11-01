package groupe.two.diiage.reserveme.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import groupe.two.diiage.reserveme.R;

public class BookingListViewHolder extends RecyclerView.ViewHolder {

    public View itemView;

    public TextView txtLocation;
    public TextView txtStartAt;
    public TextView txtEndAt;
    public TextView txtBookAt;
    public TextView txtParticipantNbr;

    public BookingListViewHolder(@NonNull View itemView) {
        super(itemView);

        this.itemView = itemView;

        this.txtLocation       = itemView.findViewById(R.id.locationBox);
        this.txtStartAt        = itemView.findViewById(R.id.startAtBox);
        this.txtEndAt          = itemView.findViewById(R.id.endAtBox);
        this.txtBookAt         = itemView.findViewById(R.id.bookAtBox);
        this.txtParticipantNbr = itemView.findViewById(R.id.participantNbrBox);
    }
}

