package shafi.cordova.plugins.admob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

import shafi.pushpop.App;
import android.util.Log;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class AdMobPlugin extends Plugin implements AdListener {
	private static final String PLUGIN_NAME = "AdmobPlugin";
	private static final String ACTION_PREPARE = "prepare";
	private static final String ACTION_SHOWAD = "showAd";
	private static final String ACTION_HIDEAD = "hideAd";
	private static final String ACTION_ONEVENT = "onEvent";

    private AdView adView;
    private Map<String, List<String>> handlers = new HashMap<String, List<String>>();

    @Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {
		Log.i(PLUGIN_NAME, "Plugin Called ("+action+")");
		if (ACTION_ONEVENT.equals(action)) {
			try {
			List<String> eventHandlers = this.handlers.get(data.getString(0));
			if (eventHandlers == null) {
				eventHandlers = new ArrayList<String>();
				this.handlers.put(data.getString(0), eventHandlers);
			}
			eventHandlers.add(data.getString(1));
			} catch (JSONException e) {
				return new PluginResult(Status.ERROR, e.getMessage());
			}
		} else {
			this.ctx.runOnUiThread(new AdViewLoader(this, action));
		}
		return new PluginResult(Status.OK);
	}
    
    public void prepareAd() {
        adView = new AdView((App)this.ctx, AdSize.SMART_BANNER, "a14fc2969f44aa5");
        adView.setAdListener(this);
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


    public void onEvent(String eventName) {
		List<String> eventHandlers = this.handlers.get(eventName);
		if (eventHandlers != null) {
			for (String handler : eventHandlers) {
				this.ctx.sendJavascript(handler);
			}
		}
    }
    
	public void onDismissScreen(Ad ad) {
		onEvent("dismissScreen");
	}

	public void onFailedToReceiveAd(Ad ad, ErrorCode error) {
		// TODO Auto-generated method stub
		
	}

	public void onLeaveApplication(Ad ad) {
		// TODO Auto-generated method stub
		
	}

	public void onPresentScreen(Ad ad) {
		onEvent("presentScreen");
	}

	public void onReceiveAd(Ad ad) {
		// TODO Auto-generated method stub
		
	}
}
