package garyapps.spiesvsagents;

import android.app.Activity;
import android.content.Context;
import android.support.v4.util.Pair;
import android.util.DisplayMetrics;

public abstract class UtilityFunctions {

    /**
     * Returns a pair with the current width/height of the screen
     * @param context : The context (Activity)
     * @return The dimensions -- (Width,Height)
     */
    public static Pair<Integer, Integer> getScreenDimensions(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new Pair<>(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
}
