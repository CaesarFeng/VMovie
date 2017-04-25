package fragments.guide_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jb.caesarfeng.vmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Guide1Fragment extends Fragment {


    public Guide1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide1, container, false);
    }

}
