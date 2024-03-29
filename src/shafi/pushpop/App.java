package shafi.pushpop;

import org.apache.cordova.DroidGap;

import android.os.Bundle;
import android.view.ViewGroup;

public class App extends DroidGap {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setIntegerProperty("splashscreen", R.drawable.splash_small);
        super.setStringProperty("loadingDialog", "PushPop,Loading...");
        super.setIntegerProperty("backgroundColor", 0xff121212);
        this.root.setBackgroundResource(R.drawable.splash_small);
        super.loadUrl("file:///android_asset/www/src/index.html");
    }
    
    public ViewGroup getRootLayout() {
    	return this.root;
    }
}