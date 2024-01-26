#!/usr/bin/osascript

tell application "Xcode"
launch
activate
end tell
delay 15
tell application "System Events"
tell process "Xcode"
activate
click menu item "Devices and Simulators" of menu 1 of menu bar item "Window" of menu bar 1
#set value of text field of window 1 to "CCI-iPhone6P-1"
#return every UI element of front window
#return name of every UI element of front window
# tell window 1
# click button 1
# end tell
end tell
end tell
