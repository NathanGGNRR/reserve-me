package groupe.two.diiage.reserveme.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import groupe.two.diiage.reserveme.R;

public class LocationBookingDialogFragment extends Fragment {

    public LocationBookingDialogFragment() { }

    public static LocationBookingDialogFragment newInstance() {
        return new LocationBookingDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location_booking_dialog, container, false);
    }
}