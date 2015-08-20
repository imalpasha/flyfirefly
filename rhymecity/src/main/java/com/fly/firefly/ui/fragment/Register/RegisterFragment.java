package com.fly.firefly.ui.fragment.Register;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fly.firefly.FireFlyApplication;
import com.fly.firefly.R;
import com.fly.firefly.ui.activity.FragmentContainerActivity;
import com.fly.firefly.ui.module.RegisterModule;
import com.fly.firefly.ui.presenter.RegisterPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterFragment extends Fragment implements RegisterPresenter.RegisterView {

    @Inject
    RegisterPresenter presenter;

    @InjectView(R.id.registerIndicator1) LinearLayout indicator1;
    @InjectView(R.id.registerIndicator2) LinearLayout indicator2;
    @InjectView(R.id.registerIndicator3) LinearLayout indicator3;

    @InjectView(R.id.registerBasicInfoBlock) LinearLayout registerPersonalInfoBlock;
    @InjectView(R.id.registerAddressInfoBlock) LinearLayout registerAddressBlock;
    @InjectView(R.id.registerContactInfoBlock) LinearLayout registerContactBlock;

    @InjectView(R.id.registerContinueButton) Button registerContinueButton;
    @InjectView(R.id.imageViewRegisterIndicator) ImageView imageRegisterIndicator;

    private int currentPage;


    //private ProgressBar progressIndicator;
    private int fragmentContainerId;

    public static RegisterFragment newInstance() {

        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

        // new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FireFlyApplication.get(getActivity()).createScopedGraph(new RegisterModule(this)).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register, container, false);
        ButterKnife.inject(this, view);

        /*Initial indicator*/
        imageRegisterIndicator.setBackgroundResource(R.drawable.register_account_focus);
        currentPage = 1;

        /*Switch register info block*/
        indicator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            showHiddenBlock(1);

            }
        });

        indicator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            showHiddenBlock(2);

            }
        });

        indicator3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            showHiddenBlock(3);

            }
        });

        registerContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHiddenBlock(currentPage+1);
            }
        });

        return view;
    }

    /*Change Field Block*/
    public void showHiddenBlock(int page)
    {
        if(page == 1){
            imageRegisterIndicator.setBackgroundResource(R.drawable.register_account_focus);
            resetRegisterFieldBlockVisibility();
            registerPersonalInfoBlock.setVisibility(View.VISIBLE);
            currentPage = 1;
        }
        else if(page == 2){
            imageRegisterIndicator.setBackgroundResource(R.drawable.register_address_focus);
            resetRegisterFieldBlockVisibility();
            registerAddressBlock.setVisibility(View.VISIBLE);
            currentPage = 2;
        }
        else if(page == 3){
            imageRegisterIndicator.setBackgroundResource(R.drawable.register_contact_focus);
            resetRegisterFieldBlockVisibility();
            registerContactBlock.setVisibility(View.VISIBLE);
            currentPage = 3;
        }
        else if(page == 4){
            Log.e("READY FOR VALIDATION","TRUE");
        }
        else {
            getActivity().finish();
        }

    }

    /*Set all block visibility to - GONE*/
    public void resetRegisterFieldBlockVisibility(){
        registerPersonalInfoBlock.setVisibility(View.GONE);
        registerAddressBlock.setVisibility(View.GONE);
        registerContactBlock.setVisibility(View.GONE);
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

    public void registerBackFunction()
    {
            showHiddenBlock(currentPage-1);

    }

}
