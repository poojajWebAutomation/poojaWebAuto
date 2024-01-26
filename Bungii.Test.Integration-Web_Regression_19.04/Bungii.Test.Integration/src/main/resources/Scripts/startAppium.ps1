$webdriverport=8100
#all devices from jenkins + extra1 
$devicelist =  $args[0]+',extra1'
$jsonFile= $args[1]
$arr = $devicelist -split ','
foreach ($device in $arr)
{
 $systemPortQuery=$device+'.systemPort'
 $systemPortQuery
 # get appium port number
 $portNumber =.\jsonExtractor.bat $jsonFile connection.$device
 $webdriverport=.\jsonExtractor.bat $jsonFile $systemPortQuery
 $logFile ="C:/Logs/AppiumServerLog_"+$device+$(get-date -f yyyy-MM-dd_hhmmss)+'.log'
 # add webdriver agent port to command
 $command ='--webdriveragent-port '+ $webdriverport+ ' -p '+$portNumber+ ' --log ' +$logFile +' --relaxed-security'
 # Add 10 for next webdriver port
 $webdriverport+=10
 $command
 start appium $command
 Start-Sleep -s 10

}
