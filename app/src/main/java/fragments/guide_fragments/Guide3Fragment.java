package fragments.guide_fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jb.caesarfeng.vmovie.GuideActivity;
import com.jb.caesarfeng.vmovie.MainActivity;
import com.jb.caesarfeng.vmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Guide3Fragment extends Fragment {

    private ImageView startImg;
    private View mView;

    public Guide3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_guide3, container, false);
        startImg = (ImageView) mView.findViewById(R.id.go);

        startImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), MainActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        return mView;
    }

}
