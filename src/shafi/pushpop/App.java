package shafi.pushpop;

import org.apache.cordova.DroidGap;

import android.os.Bundle;
import android.view.ViewGroup;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class App extends DroidGap {
    private AdView adView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/src/index.html");
        adView = new AdView(this, AdSize.SMART_BANNER, "a14d91b10f12454");

        // Lookup your LinearLayout assuming it’s been given
        // the attribute android:id="@+id/mainLayout"
        //LinearLayout layout = (LinearLayout)findViewById(R.layout.main);
        // Add the adView to it
        //layout.addView(adView);

        this.appView.addView(adView);

        // Initiate a generic request to load it with an ad
        AdRequest adRequest = new AdRequest();
        adRequest.addTestDevice(AdRequest.TEST_EMULATOR);               // Emulator
        adView.loadAd(adRequest);
    }

    @Override
    public void onDestroy() {
		if (adView != null) {
		    adView.destroy();
		}
      super.onDestroy();
    }
}