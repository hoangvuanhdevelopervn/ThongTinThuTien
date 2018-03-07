package hvasoftware.com.thongtinthutien.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Thanh on 03/07/2018.
 */

public class Utils {
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
