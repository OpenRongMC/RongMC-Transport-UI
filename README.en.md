# RongMC Transport UI

[简体中文](./README.md) · English

Adds optional Oriel styling to MTR and compatible shared addon screens. Classic and Oriel modes use the same underlying editors, and the setting is saved locally.

## Download and installation

- Version · [v0.1.0+26.3](https://github.com/OpenRongMC/RongMC-Transport-UI/releases/tag/v0.1.0%2B26.3) (Release)
- Release asset · `rongmc-transport-ui-1.0.0-port.1.26.3.jar`
- Runtime · Minecraft Java 26.3, Fabric, Java 25
- Fabric Loader · `>=0.20.1`
- Fabric mod ID · `rongmc_transport_ui`
- Dependencies declared by the JAR · [`rongmc_mtr`](https://github.com/OpenRongMC/Minecraft-Transit-Railway) `4.0.5-port.1+26.3`

Place the release JAR in the instance’s `mods/` directory. Install it on clients with the matching MTR series. Add Oriel for the new appearance; Classic remains available when Oriel is absent.

## How to try it

Switch mode from MTR Settings → Interface or from Mod Menu. Return to MTR settings to confirm it persisted, then test input and saving on a compatible YTE screen.

## Development

See [`build.sh`](./build.sh) for the build entry point. Check its workspace paths and dependencies before running it.

## Documentation and support

- [Previous README (historical guide, contributors and credits)](https://github.com/OpenRongMC/RongMC-Transport-UI/blob/3d8716d4273593d0cc897f0e45e749c0f7a9f001/README.md)
- [Upstream project and documentation](https://github.com/Rinorsi/Oriel-UI)
- [Yunzhu Transit Extension (YTE)](https://github.com/OpenRongMC/Yunzhu-Transit)
- [Report a RongMC port issue](https://github.com/OpenRongMC/RongMC-Transport-UI/issues) · Include the game version, client and server JAR names, reproduction steps and relevant logs.

## License and credits

See this repository’s [LICENSE](./LICENSE) and the original project’s license, credits and third-party asset notices.
