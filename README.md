<div align="center">
<h1>Extra CPUs</h1>

A [Minecraft] mod to give you more Crafting Storages for [Applied Energistics 2][ae2].

[![Version][version_badge]][version_link]
[![Total Downloads][total_downloads_badge]][curseforge]
[![Workflow Status][workflow_status_badge]][workflow_status_link]
[![License][license_badge]][license]
[![Style][style_badge]][style_link]

[Discord] | [CurseForge]

</div>

## **📑 Overview**
This is a mod for [Minecraft] [Forge].<br>
It will add additional Crafting Storages which can be used with [Applied Energistics 2][ae2].

They can be used to save space because the built-in Crafting Storages have a maximum capacity of 64k.

The crafting recipes use Storage Components from Extra Cells.<br>
Since v1.2.0, this mod supports all known forks as well as the original Extra Cells mod. Just make sure to only use one of them.

<details>
    <summary>
        <strong>Preview</strong> (click to expand)
    </summary>

![preview](images/preview.png)
</details>


## **🔧 Installation**

1. Download the latest **mod jar** from the [releases] or from [CurseForge].
2. Install Minecraft [Forge].
3. Install the dependency mods [Applied Energistics 2][ae2] and one of the Extra Cells mods.
4. Drop the **jar file** into your mods folder.


## **🐛 Limitations**
Due to how [Applied Energistics 2][ae2] is made and built, there is a limitation you have to keep in mind:

- the Crafting Storage blocks of this mod will only work if at least one Crafting Storage block of the original mod [Applied Energistics 2][ae2] is in the same multiblock, otherwise they are just not being recognized by the ME system
- whenever you build a multiblock with the new Crafting Storages make sure that a minimum of 1 block of an Applied Energistics Crafting Storage is also included in the same multiblock


## **📕 History**
This is a port of the [Extra Crafting Storage] mod by [Zokonius].

He discontinued his version and since it's running under the [GPL 3 license][license], I decided to update it.

It is basically a rewrite and just borrows the idea.<br>
After the first version was released, he joined the development of Extra CPUs.


## **⏰ Changelog**
Everything related to versions and their release notes can be found in the [changelog].


## **🎓 License**
This project is licensed under the [GPL 3][license].


<!-- Badges -->
[version_badge]: https://img.shields.io/github/v/release/RLNT/minecraft_extracpus?style=flat-square
[version_link]: https://github.com/RLNT/minecraft_extracpus/releases/latest
[total_downloads_badge]: http://cf.way2muchnoise.eu/full_408089.svg?badge_style=flat
[workflow_status_badge]: https://img.shields.io/github/workflow/status/RLNT/minecraft_extracpus/CI?style=flat-square
[workflow_status_link]: https://github.com/RLNT/minecraft_extracpus/actions
[license_badge]: https://img.shields.io/github/license/RLNT/minecraft_extracpus?style=flat-square
[style_badge]: https://img.shields.io/badge/code_style-prettier-ff69b4.svg?style=flat-square
[style_link]: https://github.com/prettier/prettier

<!-- Links -->
[minecraft]: https://www.minecraft.net/
[ae2]: https://www.curseforge.com/minecraft/mc-mods/applied-energistics-2
[discord]: https://discordapp.com/invite/Q3qxws6
[curseforge]: https://www.curseforge.com/minecraft/mc-mods/extracpus
[forge]: http://files.minecraftforge.net/
[releases]: https://github.com/RLNT/minecraft_extracpus/releases
[extra crafting storage]: https://github.com/Zoko061602/ExtraCraftingStorage
[zokonius]: https://github.com/Zokonius
[changelog]: CHANGELOG.md
[license]: LICENSE
