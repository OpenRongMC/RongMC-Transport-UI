# RongMC 交通界面

简体中文 · [English](./README.en.md)

给 MTR 和共享界面的交通扩展增加 Oriel 外观选项。经典界面和 Oriel 界面都通过原有编辑流程工作，选择会记录在客户端配置。

## 下载与安装

- 版本 · [v0.1.0+26.3](https://github.com/OpenRongMC/RongMC-Transport-UI/releases/tag/v0.1.0%2B26.3) (已发布)
- 下载文件 · `rongmc-transport-ui-1.0.0-port.1.26.3.jar`
- 运行环境 · Minecraft Java 26.3, Fabric, Java 25
- Fabric Loader · `>=0.20.1`
- Fabric 模组 ID · `rongmc_transport_ui`
- JAR 声明的依赖 · [`rongmc_mtr`](https://github.com/OpenRongMC/Minecraft-Transit-Railway) `4.0.5-port.1+26.3`

把发行 JAR 放入对应实例的 `mods/` 目录。只装在客户端，配套同系列 MTR。需要 Oriel 外观时加装 Oriel。缺少 Oriel 时可继续使用经典模式。

## 如何验证

从 MTR 设置的「界面」入口或 Mod Menu 切换模式。回到 MTR 设置检查是否保存，并试用一个 YTE 页面和原来可输入、保存的控件。

## 开发

构建入口见 [`build.sh`](./build.sh)。运行前检查脚本需要的工作区目录与依赖。

## 文档与反馈

- [原 README（旧版指南、贡献者与署名）](https://github.com/OpenRongMC/RongMC-Transport-UI/blob/3d8716d4273593d0cc897f0e45e749c0f7a9f001/README.md)
- [上游项目与文档](https://github.com/Rinorsi/Oriel-UI)
- [云竹交通扩展（YTE）](https://github.com/OpenRongMC/Yunzhu-Transit)
- [报告 RongMC 移植问题](https://github.com/OpenRongMC/RongMC-Transport-UI/issues) · 请附游戏版本、两端 JAR 文件名、复现步骤和相关日志。

## 许可与署名

请参阅本仓库的 [LICENSE](./LICENSE) 及原项目的许可、署名和第三方资源说明。
