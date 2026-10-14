<p align="center"><img src="icon.png" width="128"></p>

# Tuff Bright

fullbright that actually works. every block is fully lit, in caves and at night, and the darkness effect is gone. it is a lot stronger than the normal brightness slider.

client side fabric mod. settings through mod menu with cloth config (needs no fabric api):

- **fullbright on**: turn it off for the normal game lighting
- **brightness**: slider from 0% to 100%. 100% (default) lights everything like full daylight, lower values are a softer boost, 0% does nothing

## downloads

| jar | minecraft |
|---|---|
| `tuff-bright-mc1.21.11-1.0.1.jar` | 1.21.11 |
| `tuff-bright-mc26.1-26.3-1.0.0.jar` | 26.1 to 26.3 |

## pictures

same spot, same time, mod off and on:

| off | on |
|---|---|
| ![night off](screenshots/night-off.png) | ![night on](screenshots/night-on.png) |
| ![cave off](screenshots/cave-off.png) | ![cave on](screenshots/cave-on.png) |

50% in a cave and the settings screen:

![cave 50](screenshots/cave-50.png)

![settings](screenshots/settings.png)

## how it works

one mixin on the lightmap. on 1.21.11 it raises the brightness value that goes into the lightmap shader (and zeroes the darkness values), on 26.x it does the same on the lightmap render state. the brightness is far above the normal slider max, so even light level 0 ends up fully lit.

## tests

each folder has a client game test (`./gradlew runClientGameTest`): it checks the config, the slider values and takes screenshots in a closed stone room (mod off: black, 100%: fully lit, 50%: in between, 15%: still dark) and opens the cloth config screen. passes on 1.21.11, 26.1, 26.2 and 26.3.

license: MIT
## 1.0.1

on 1.21.11 the settings now apply right away, also with mods that cache the lightmap like BadOptimizations. before, the slider and the on/off switch could do nothing with those mods.
