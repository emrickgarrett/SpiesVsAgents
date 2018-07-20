package garyapps.spiesvsagents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import emrickgj.saa.SingleActivity;
import garyapps.spiesvsagents.ViewControllers.HomeViewController;

public class MainActivity extends SingleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, R.id.main_linear_layout);

        navigateToViewController(new HomeViewController(this));
    }
}
