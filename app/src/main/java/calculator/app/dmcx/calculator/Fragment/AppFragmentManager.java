package calculator.app.dmcx.calculator.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import calculator.app.dmcx.calculator.Activity.Variables.Vars;
import calculator.app.dmcx.calculator.R;

public class AppFragmentManager {

    private int animEnter;
    private int animExit;
    private FragmentTransaction transaction;

    public AppFragmentManager() {
        animEnter = android.R.animator.fade_in;
        animExit = android.R.animator.fade_out;
    }

    private void reloadAnimations() {
        animEnter = android.R.animator.fade_in;
        animExit = android.R.animator.fade_out;
    }

    public AppFragmentManager setFragmentAnimation(int enter, int exit) {
        animEnter = enter;
        animExit = exit;

        return this;
    }

    public void replaceFragment(AppCompatActivity appCompatActivity, Fragment fragment, String tag) {
        appCompatActivity
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(animEnter, animExit)
                .replace(R.id.fragment_container, fragment, tag)
                .commit();

        reloadAnimations();
        Vars.currentFragment = fragment;
    }

}
