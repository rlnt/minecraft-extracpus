# **Extra CPUs**

> A [Minecraft] mod to give you more Crafting Storages for [Applied Energistics 2][ae2].


[![forthebadge](https://forthebadge.com/images/badges/60-percent-of-the-time-works-every-time.svg)][curseforge]
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)][curseforge]

- [Discord]
- [CurseForge project page][curseforge]

---

<br>

<!-- Table of Contents -->
<details>
    <summary>
        <strong>Table of Contents (click to expand)</strong>
    </summary>

- [**Extra CPUs**](#extra-cpus)
  - [**📑 Overview**](#-overview)
  - [**🐛 Limitations**](#-limitations)
  - [**🔧 Installation**](#-installation)
  - [**📕 History**](#-history)
  - [**⏰ Changelog**](#-changelog)
  - [**🎓 License**](#-license)
</details>


## **📑 Overview**
This is a mod for [Minecraft] [Forge]. It will add additional Crafting Storages which can be used with [Applied Energistics 2][ae2].

They can be used to save space because the built-in Crafting Storages have a maximum capacity of 64k.

The crafting recipes use the cells from [Extra Cells].

[Applied Energistics 2][ae2] and [Extra Cells] are dependencies for this mod.

<details>
    <summary>
        <strong>Preview (click to expand)</strong>
    </summary>

![preview](images/preview.png)
</details>


## **🐛 Limitations**
Due to how [Applied Energistics 2][ae2] is made and built, there is a limitation you have to keep in mind:

- the Crafting Storage blocks of this mod will only work if at least one Crafting Storage block of the original mod [Applied Energistics 2][ae2] is in the same multiblock, otherwise they are just not being recognized by the ME system
- so whenever you build a multiblock with the new Crafting Storages make sure that a minimum of 1 block of an Applied Energistics Crafting Storage is also included in the same multiblock


## **🔧 Installation**

1. Download the latest **mod jar** from the [releases] or from [curseforge].
2. Install Minecraft [Forge].
3. Install the dependency mods [Applied Energistics 2][ae2] and [Extra Cells].
4. Drop the **jar file** into your mods folder.


## **📕 History**
This is a port of the [Extra Crafting Storage mod][original mod] by [Zoko061602][author].

He discontinued his mod and since it's running under the [GPL 3 license](./LICENSE), I decided to update it.

I am using a different approach for the new version and also remade the textures so it's more like an overhauled version.

*Update:* Meanwhile, he joined the development of this mod so it's like a second edition now.


## **⏰ Changelog**
Everything related to versions and their release notes can be found in the [changelog].


## **🎓 License**
This project is licensed under the [GPL 3].


<!-- Links -->
[Minecraft]: https://www.minecraft.net/
[ae2]: https://www.curseforge.com/minecraft/mc-mods/applied-energistics-2
[Discord]: https://discordapp.com/invite/Q3qxws6
[curseforge]: https://www.curseforge.com/minecraft/mc-mods/extracpus
[Extra Cells]: https://www.curseforge.com/minecraft/mc-mods/extracells2
[releases]: https://github.com/RLNT/sinus-staff-list/releases
[Forge]: http://files.minecraftforge.net/
[original mod]: https://github.com/Zoko061602/ExtraCraftingStorage
[author]: https://github.com/Zoko061602
[changelog]: CHANGELOG.md
[GPL 3]: LICENSE.md
[pull request]: https://github.com/RLNT/minecraft-extracpus/pulls
