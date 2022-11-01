package groupe.two.diiage.reserveme.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import groupe.two.diiage.reserveme.R;

public class AddBookingDialogFragment extends Fragment {

    public AddBookingDialogFragment() { }

    public static AddBookingDialogFragment newInstance() {
        return new AddBookingDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_booking_dialog, container, false);
    }
}