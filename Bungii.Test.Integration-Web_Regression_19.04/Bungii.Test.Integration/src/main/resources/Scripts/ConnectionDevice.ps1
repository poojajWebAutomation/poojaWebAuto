$devicelist =  $args[0]+',extra1'
$hostName= $args[1]
$jsonFile= $args[2]
set ADB_SERVER_SOCKET=tcp:$hostName:5554
set ANDROID_ADB_SERVER_PORT=5554
adb devices
adb tcpip 5037
#adb connect 192.168.55.43:5037
adb devices
$arr = $devicelist -split ','
foreach ($device in $arr)
{
$command =$device+'.udid'
 # get appium port number
 $udid =.\jsonExtractor.bat $jsonFile $command
 $udid
 adb connect $udid
 Start-Sleep -s 1
}
adb devices