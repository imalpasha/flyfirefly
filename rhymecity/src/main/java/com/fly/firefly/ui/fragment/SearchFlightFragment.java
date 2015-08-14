package com.fly.firefly.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fly.firefly.FireFlyApplication;
import com.fly.firefly.R;
import com.fly.firefly.ui.activity.FragmentContainerActivity;
import com.fly.firefly.ui.activity.RegisterActivity;
import com.fly.firefly.ui.module.SearchFlightModule;
import com.fly.firefly.ui.presenter.LoginPresenter;
import com.fly.firefly.ui.presenter.SearchFlightPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchFlightFragment extends Fragment implements SearchFlightPresenter.SearchFlightView {

    @Inject
    SearchFlightPresenter presenter;

    @InjectView(R.id.btnReturn) LinearLayout btnReturn;
    @InjectView(R.id.btnOneWay) LinearLayout btnOneWay;
    @InjectView(R.id.returnDateBlock) LinearLayout returnDateBlock;



    private int fragmentContainerId;

    public static SearchFlightFragment newInstance() {

        SearchFlightFragment fragment = new SearchFlightFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

        // new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FireFlyApplication.get(getActivity()).createScopedGraph(new SearchFlightModule(this)).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_flight, container, false);
        ButterKnife.inject(this, view);

        /*Initial*/
        btnOneWay.setBackgroundColor(getResources().getColor(R.color.grey));

        /*Return Button Clicked*/
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDateBlock.setVisibility(View.VISIBLE);
                btnReturn.setBackgroundColor(getResources().getColor(R.color.white));
                btnOneWay.setBackgroundColor(getResources().getColor(R.color.grey));
            }
        });

        /*One Way Button Clicked*/
        btnOneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDateBlock.setVisibility(View.GONE);
                btnReturn.setBackgroundColor(getResources().getColor(R.color.grey));
                btnOneWay.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        return view;
    }

    /*Public-Inner Func*/
    public void goRegisterPage()
    {
        Intent loginPage = new Intent(getActivity(), RegisterActivity.class);
        getActivity().startActivity(loginPage);
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
