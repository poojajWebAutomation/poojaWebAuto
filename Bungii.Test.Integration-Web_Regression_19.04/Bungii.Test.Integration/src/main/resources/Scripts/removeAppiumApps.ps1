# remove appium apps that get installed , replace UDID with udid of device which you want to connect
# this is required if Appium server is detecting device but giving proxy error
adb -s TA93401OLE uninstall io.appium.uiautomator2.server
adb -s TA93401OLE uninstall io.appium.uiautomator2.server.test
adb -s TA93401OLE uninstall io.appium.unlock
adb -s TA93401OLE uninstall io.appium.settings