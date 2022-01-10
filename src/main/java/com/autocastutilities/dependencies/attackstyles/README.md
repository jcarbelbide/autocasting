# Dependencies

This plugin depends on the following classes from the Attack Styles plugin:
1. AttackStyle
2. WeaponType

The enums from these files from the Attack Styles plugin are private, so are normally inaccessible. The files have been
copied over to this folder and made public so that it can be used by AutocastUtilities. 
The only change to these files are that the enums are made public. 

If in the future, a new weapon type is released that is capable of autocasting and the WeaponType
enum is updated, the updated WeaponType.java from the Attack Styles plugin should be copied
over to this folder, and the enum should be made public.
