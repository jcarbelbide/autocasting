# Autocasting
This plugin aims to notify the client when the player's magic level falls below the level 
required for the autocast spell.

This provides the player a chance to restore their magic level before attempting to 
autocast again, which would normally mean that the autocasted spell would become 
deselected and must be reselected in the autocast menu. 

The plugin displays an overlay with the currently equipped autocast spell, and will flash 
red if the player's magic level falls below the required level for that spell. The overlay
will automatically hide when a non-casting weapon is equipped. 

![Overlay Example](Demo.PNG?raw=true "Overlay Example")

If you would like to contribute to this plugin and want to understand the high level flow, please 
refer to the following diagrams:

[Link to Event Flow Diagrams](https://drive.google.com/file/d/1R5bGnI_vM2OeVaXoTuoS-WRWvsrXPUJu/view?usp=sharing)
