package com.fly.firefly.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;

import com.fly.firefly.MainFragmentActivity;
import com.fly.firefly.R;
import com.fly.firefly.ui.fragment.RegisterFragment;

import butterknife.ButterKnife;

//import android.view.WindowManager;

public class RegisterActivity extends MainFragmentActivity implements FragmentContainerActivity {

    //implements ToolbarActivity, ProgressIndicatorActivity, FragmentContainerActivity {
    //@InjectView(R.id.main_activity_toolbar) Toolbar toolbar;
    //@InjectView(R.id.main_activity_progress_indicator) ProgressBar progressIndicator;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, RegisterFragment.newInstance(),"xx").commit();

       /* Fragment fragmentA = new FragmentA();
        getFragmentManager().beginTransaction()
                .replace(R.id.MainFrameLayout,fragmentA,"YOUR_TARGET_FRAGMENT_TAG")
                .addToBackStack("YOUR_SOURCE_FRAGMENT_TAG").commit();*/
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.main_activity_fragment_container;
    }

    @Override
    public void onBackPressed(){

        final FragmentManager manager = getFragmentManager();
        RegisterFragment fragment = (RegisterFragment) manager.findFragmentByTag("xx");
        fragment.registerBackFunction();


    }
}
