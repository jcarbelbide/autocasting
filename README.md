# Autocasting

## Overview
Autocasting is a plugin designed to help better manage your autocast spell. At its core, this plugin adds
an overlay which dynamically updates information about your current autocast spell, including the amount of times
you have runes to cast it. It will also alert when your autocast becomes unusable, due to your Magic dropping
below the required level, or running out of runes. These alerts can be configured to notify you in various ways,
such as desktop notifications, chat messages, or **bright red blinking alert box**.

### Standard Appearance
![Example 1](example1.png?raw=true "Standard appearance of the autocasting overlay")

### Alert when you run out of runes
![Example 2](example2.png?raw=true "An alert when you don't have enough runes to autocast")

### Another alert when you lose the magic level to autocast
![Example 3](example3.png?raw=true "Another alert when you don't have the magic level to autocast")

## Configuration
The following settings are configurable:

### Overlay
- **Show Overlay**: Globally remove the autocast overlay. Useful if you only want the other notifications.
- **Show Outside of Combat**: If unchecked, will hide the overlay after you're not attacking something for a few seconds.
- **Show Spell Name**: Display the name of the autocast spell in the overlay.
- **Show Spell Icon**: Include the spell's icon in the overlay.
- **Enable Override Text**: If checked, will override with a custom message when alerts trigger.
- **Low Magic Level Override Text**: Custom text when your magic level drops too much.
- **No Runes Override Text**: Custom text when you run out of runes
- **Overlay Alert Style**: Behavior of the alert. It can blink, be static _alert color_, or not change at all.
- **Overlay Alert Color**: Color Picker for the alert.
- **Overlay Flash Period**: Period of alert blinks, in hundredths of seconds.
- **Display Casts Remaining**: Show the number of times you can autocast, based on your current runes / equipped items.
- **Display Cast Limit**: Max amount of casts it will display; the idea is if you exceed this limit you have enough.

### Notifications
All notification types can be enabled or disabled independently, will print to the chat and optionally send a desktop notification.

- **Notify Out Of Combat**: If unchecked, won't send notifications unless you're in combat.
- **Stat Drain Notification**: Send a notification when your magic drops below the required level to cast.
- **Low Casts Notification**: Send a notification when the amount of remaining casts falls to or below the threshold below.
- **Low Cast Threshold**: Configurable limit for the above notification.
- **No Casts Notification**: Send a notification when you don't have the runes to cast your autocast.

## Support
The primary maintainers of this plugin are available through Discord:  

- _jcarbelbide_  
- _zachgs_  

## Contributing
If you would like to contribute to this plugin and want to understand the high level flow, please 
refer to the following diagram:

![Code Flow](flow_diagram.png?raw=true)
