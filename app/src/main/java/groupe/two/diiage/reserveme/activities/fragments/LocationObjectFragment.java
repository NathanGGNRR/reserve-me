package groupe.two.diiage.reserveme.activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import groupe.two.diiage.reserveme.R;

public class LocationObjectFragment extends Fragment {

    public LocationObjectFragment() { }

    public static LocationObjectFragment newInstance() {
        return new LocationObjectFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location_object, container, false);
    }
}