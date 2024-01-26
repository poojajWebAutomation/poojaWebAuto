$hostName =  $args[0] +""
$taskName = 'ADB_SERVER'
echo 'Host Name is' +$hostName + 'task name is '+ $taskName
schtasks /run /s $hostName /tn $taskName