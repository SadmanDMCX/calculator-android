package calculator.app.dmcx.calculator.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import calculator.app.dmcx.calculator.Activity.Variables.Vars;
import calculator.app.dmcx.calculator.Fragment.AppFragmentManager;
import calculator.app.dmcx.calculator.Fragment.Fragment.Calculator;
import calculator.app.dmcx.calculator.Fragment.Fragment.History;
import calculator.app.dmcx.calculator.R;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;

    private void loadCalligraphy() {
        ViewPump.init(ViewPump.builder()
            .addInterceptor(new CalligraphyInterceptor(
                new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build())).build());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadCalligraphy();
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));

        instance = this;
        Vars.appFragmentManager = new AppFragmentManager();


         Vars.appFragmentManager.replaceFragment(instance, new Calculator(), Calculator.Tag);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(instance, Vars.currentFragment.getTag() + "", Toast.LENGTH_SHORT).show();

        if (Vars.currentFragment.getTag().equals(History.Tag)) {
            Vars.appFragmentManager
                .replaceFragment(MainActivity.instance, new Calculator(), Calculator.Tag);
        } else {
            boolean callback = Calculator.returnCallback();
            if (!callback) {
                super.onBackPressed();
            }
        }
    }
}
