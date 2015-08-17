package com.fly.firefly.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fly.firefly.FireFlyApplication;
import com.fly.firefly.R;
import com.fly.firefly.ui.activity.FragmentContainerActivity;
import com.fly.firefly.ui.module.FlightDetailModule;
import com.fly.firefly.ui.module.SearchFlightModule;
import com.fly.firefly.ui.presenter.BF_FlightDetailPresenter;
import com.fly.firefly.ui.presenter.BF_SearchFlightPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BF_FlightDetailFragment extends Fragment implements BF_FlightDetailPresenter.FlightDetailView {

    @Inject BF_FlightDetailPresenter presenter;
    @InjectView(R.id.btnPersonalDetail)Button btnPersonalDetail;

    private int fragmentContainerId;


    public static BF_FlightDetailFragment newInstance() {

        BF_FlightDetailFragment fragment = new BF_FlightDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FireFlyApplication.get(getActivity()).createScopedGraph(new FlightDetailModule(this)).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.flight_detail, container, false);
        ButterKnife.inject(this, view);


        btnPersonalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_activity_fragment_container, BF_PersonalDetailFragment.newInstance(), "PERSONAL_DETAIL");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentContainerId = ((FragmentContainerActivity) getActivity()).getFragmentContainerId();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }
}
