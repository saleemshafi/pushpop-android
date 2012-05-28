function byebye() {
	PushPopUI.pauseTimer();
}

function welcomeBack() {
	PushPopUI.resumeTimer();
}


document.addEventListener("deviceready", function() {
	setTimeout(function() {
		window.plugins.admob.onPresentScreen("byebye()");
		window.plugins.admob.onDismissScreen("welcomeBack()");
		window.plugins.admob.showAd();
	}, 1000);
}, false);

var redrawForOrientation = null;

$(window).bind("orientationchange", function() {
	if (redrawForOrientation == null) {
		redrawForOrientation = setTimeout(function() {
			window.plugins.admob.hideAd();
			setTimeout(function() {
				window.plugins.admob.showAd();
				redrawForOrientation = null;
			}, 10);
		}, 10);
	}
});