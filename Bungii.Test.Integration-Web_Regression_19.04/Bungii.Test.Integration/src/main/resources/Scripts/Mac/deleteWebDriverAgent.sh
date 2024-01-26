#!/bin/sh
#list of devices to consider , $1 is argument from command line

#ios-deploy --id $1 --uninstall_only --bundle_id  com.facebook.WebDriverAgentRunner.xctrunner
wait for 18 sec
Sleep 18s
ios-deploy --id $1 --uninstall_only --bundle_id  com.apple.test.WebDriverAgentRunner-Runner
Sleep 18s
done