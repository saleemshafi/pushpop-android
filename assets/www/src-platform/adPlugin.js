/**
 * 
 * @return Object literal singleton instance of DirectoryListing
 */
var AdMob = function() {
};

/**
 * @param directory
 *            The directory for which we want the listing
 * @param successCallback
 *            The callback which will be called when directory listing is
 *            successful
 * @param failureCallback
 *            The callback which will be called when directory listing encouters
 *            an error
 */
AdMob.prototype.prepare = function(successCallback, failureCallback) {
	return PhoneGap.exec(successCallback, // Success callback from the plugin
		failureCallback, // Error callback from the plugin
		'AdMobPlugin', // Tell PhoneGap to run "DirectoryListingPlugin" Plugin
		'prepare', // Tell plugin, which action we want to perform
		[]); // Passing list of args to the plugin
};

AdMob.prototype.showAd = function(successCallback, failureCallback) {
	return PhoneGap.exec(successCallback, // Success callback from the plugin
		failureCallback, // Error callback from the plugin
		'AdMobPlugin', // Tell PhoneGap to run "DirectoryListingPlugin" Plugin
		'showAd', // Tell plugin, which action we want to perform
		[]); // Passing list of args to the plugin
};

AdMob.prototype.hideAd = function(successCallback, failureCallback) {
	return PhoneGap.exec(successCallback, // Success callback from the plugin
		failureCallback, // Error callback from the plugin
		'AdMobPlugin', // Tell PhoneGap to run "DirectoryListingPlugin" Plugin
		'hideAd', // Tell plugin, which action we want to perform
		[]); // Passing list of args to the plugin
};

cordova.addConstructor(function() {
	console.log("----loading the admob plugin----");
	cordova.addPlugin("admob", new AdMob());
});

