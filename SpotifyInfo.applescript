DATA=$(osascript -e 'tell application "System Events"
	set myList to (name of every process)
end tell

if myList contains "Spotify" then
	tell application "Spotify"
		if player state is stopped then
			set output to "Stopped"
		else
			set trackname to name of current track
			set artistname to artist of current track
			set albumname to album of current track
		
				set output to trackname & " | " & artistname 
			
		end if
	end tell
else
	set output to "Spotify is not running"
end if')


echo $DATA | awk -F new_line '{print $1}'
echo $DATA | awk -F new_line '{print $2}'
