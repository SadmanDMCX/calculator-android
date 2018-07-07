package calculator.app.dmcx.calculator.Fragment.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import calculator.app.dmcx.calculator.Activity.MainActivity;
import calculator.app.dmcx.calculator.Activity.Variables.Vars;
import calculator.app.dmcx.calculator.R;

public class Calculator extends Fragment {

    public static final String Tag = "Calculator Tag";

    public static Calculator instance;

    private EditText numberET;
    private ImageButton historyIB;
    private ImageButton optionsIB;
    private NestedScrollView nestedScrollViewBottomSheet;

    private BottomSheetBehavior bottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        instance = this;

        // Initialization
        numberET = view.findViewById(R.id.numberET);
        historyIB = view.findViewById(R.id.historyIB);
        optionsIB = view.findViewById(R.id.optionIB);
        nestedScrollViewBottomSheet = view.findViewById(R.id.nestedScrollViewBottomSheet);

        bottomSheetBehavior = BottomSheetBehavior.from(nestedScrollViewBottomSheet);

        // Props
        numberET.setShowSoftInputOnFocus(false);

        historyIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vars.appFragmentManager
                    .setFragmentAnimation(R.anim.enter_from_right, R.anim.exit_to_left)
                    .replaceFragment(MainActivity.instance, new History(), History.Tag);
            }
        });

        optionsIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    return;
                }

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        return view;
    }

    public static boolean returnCallback() {
        if (instance.bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            instance.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return true;
        }

        return false;
    }
}
