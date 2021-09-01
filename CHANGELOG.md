# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog],
and this project adheres to [Semantic Versioning].

## [Unreleased]
- /


## [1.2.0] - 2021-09-01

### Added
- Russian translation [@kellixon] ([#2])
- German translation
- compatibility for [AE Additions]
- compatibility for [SamLams EC2 Fork]
- new prettier formatting style
- CI

### Changed
- to ForgeGradle 3
- information handling in gradle properties
- major code cleanup
- crafting recipes to use components instead of storages

### Fixed
- mod not running properly in dev environment

<!-- Links -->
[@kellixon]: https://github.com/kellixon
[#2]: https://github.com/RLNT/minecraft_extracpus/pull/2
[AE Additions]: https://www.curseforge.com/minecraft/mc-mods/ae-additions-extra-cells-2-fork
[SamLams EC2 Fork]: https://www.curseforge.com/minecraft/mc-mods/extra-cells-2-samlam140330s-fork


## [1.1.0] - 2020-09-21

### Notes
- although a lot of internal changes have been made in the mod, the new version is **still compatible** with your old world, nothing will be lost
- this update was only possible through the awesome dev [@Zokonius] of the original [Extra Crafting Storage] mod, thanks a lot!

### Changed
- block textures now connect properly within the mod
- blocks now also connect to original AE2 Crafting Storages
- blocks now emit light when in the dark while being powered
- remade some textures so they fit AE2 a bit more
- 4096k Crafting Storage is now pink for a visible difference to the 1k Crafting Storage
- general structure improvements and refactoring

<!-- Links -->
[@Zokonius]: https://github.com/Zokonius
[Extra Crafting Storage]: https://github.com/Zoko061602/ExtraCraftingStorage


## [1.0.0] - 2020-09-14

- initial release


<!-- Links -->
[keep a changelog]: https://keepachangelog.com/en/1.0.0/
[semantic versioning]: https://semver.org/spec/v2.0.0.html

<!-- Versions -->
[unreleased]: https://github.com/RLNT/minecraft_extracpus/compare/v1.2.0...HEAD
[1.2.0]: https://github.com/RLNT/minecraft_extracpus/compare/v1.1.0...v1.2.0
[1.1.0]: https://github.com/RLNT/minecraft_extracpus/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/RLNT/minecraft_extracpus/releases/tag/v1.0.0
