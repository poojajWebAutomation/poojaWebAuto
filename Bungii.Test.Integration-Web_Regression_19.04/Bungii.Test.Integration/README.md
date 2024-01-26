Parallel Test
============
1. Edit pom.xml and update "<tags>" field with tag which you want execute in parallel
2. Start Appium server on different port and webdriver port 

Ex
	Server 1 : appium -p 4727 --webdriveragent-port 8110
	Server 2 : appium -p 4723 --webdriveragent-port 8100

3. Go to Bungii.Test.Integration folder and perform following action
	mvn clean
	mvn install -Dtest.Device="device1,device2" -Dcucumber.options="--glue com.bungii.web.stepdefinitions --glue com.bungii.hooks --tags '@web and @sanity'"
	
	where test.Device variable contains list of devices on which parallel test should be executed

	LIMITATION : For mobile device , Number of device = Number of Runner file generated


	Note: For android start appium server with "--relaxed-security" flag
	      Ex
	        appium -p 4723 --relaxed-security