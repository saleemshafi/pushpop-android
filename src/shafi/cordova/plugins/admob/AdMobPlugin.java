package shafi.cordova.plugins.admob;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;

import shafi.pushpop.App;
import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class AdMobPlugin extends Plugin {
	private static final String PLUGIN_NAME = "AdmobPlugin";
	private static final String ACTION_PREPARE = "prepare";
	private static final String ACTION_SHOWAD = "showAd";
	private static final String ACTION_HIDEAD = "hideAd";

    private AdView adView;

    @Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {
		Log.i(PLUGIN_NAME, "Plugin Called ("+action+")");
		this.ctx.runOnUiThread(new AdViewLoader(this, action));
		return new PluginResult(Status.OK);
	}
    
    public void prepareAd() {
        adView = new AdView((App)this.ctx, AdSize.SMART_BANNER, "a14fc2969f44aa5");
//        adView.setAdListener(null);
        ((App)this.ctx).getRootLayout().addView(adView);
    }
    
    public void showAd() {
    	if (adView == null) {
    		this.prepareAd();
    	}
        AdRequest adRequest = new AdRequest();
        adRequest.addTestDevice(AdRequest.TEST_EMULATOR);               // Emulator
        adView.loadAd(adRequest);
    }
    
    public void hideAd() {
    	if (adView != null) {
            ((App)this.ctx).getRootLayout().removeView(adView);
    		adView.destroy();
    	}
    	adView = null;
    }
    
    private static class AdViewLoader implements Runnable {
    	private AdMobPlugin plugin;
    	private String action;
		public AdViewLoader(AdMobPlugin plugin, String action) {
			this.plugin = plugin;
			this.action = action;
		}
		public void run() {
			if (ACTION_PREPARE.equals(action)) {
				this.plugin.prepareAd();
			} else if (ACTION_SHOWAD.equals(action)) {
				this.plugin.showAd();
			} else if (ACTION_HIDEAD.equals(action)) {
				this.plugin.hideAd();
			}
		}
    }
}
