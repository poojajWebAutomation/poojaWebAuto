#!/bin/sh
#list of devices to consider , $1 is argument from command line

idevicediagnostics -u $1 restart
Sleep 180s
ios-deploy --noninteractive -u $1 --debug --bundle com.bungii.customer
done