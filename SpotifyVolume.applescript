DATA=$(osascript -e 'tell application "System Events"
	set myList to (name of every process)
end tell

if myList contains "Spotify" then
	tell application "Spotify"
		if player state is stopped then
			set output to ""
		else
			set volumeLevel to sound volume
			

			if volumeLevel <= 10 and volumeLevel > 0
				set output to "_"
			else if volumeLevel <= 20 and volumeLevel > 10
				set output to "__"
			else if volumeLevel <= 30 and volumeLevel > 20
				set output to "___"
			else if volumeLevel <= 40 and volumeLevel > 30
				set output to "____"
			else if volumeLevel <= 50 and volumeLevel > 40
				set output to "_____"
			else if volumeLevel <= 60 and volumeLevel > 50
				set output to "______"
			else if volumeLevel <= 70 and volumeLevel > 60
				set output to "_______"
			else if volumeLevel <= 80 and volumeLevel > 70
				set output to "________"
			else if volumeLevel <= 90 and volumeLevel > 80
				set output to "_________"
			else if volumeLevel <= 100 and volumeLevel > 90
				set output to "___________"

			end if
			

		
			
		end if
	end tell
else
	set output to ""
end if')

echo $DATA | awk -F new_line '{print $1}'
echo $DATA | awk -F new_line '{print $2}'