# RongMC Transport UI

为 Minecraft Transit Railway（及 Yunzhu 等）设置界面提供 **Oriel UI** 外观切换与统一入口。

## 功能

- 在 Mod Menu / MTR 设置路径上，可选使用 Oriel 风格设置页（检测到 `oriel` 时启用相关 mixin）。
- 统一打开 MTR / YTE 相关设置的入口与外观微调（过滤器按钮外观等）。
- 本地偏好保存在配置目录，记住是否走 Oriel 模式。

## 依赖

- `rongmc_mtr`（或提供 `mtr` 的等价构建）
- 可选：`oriel`（没有则回退经典设置页）

## 已知限制

- Oriel 未安装时只会走经典 UI，不算错误。
- 强依赖 MTR 屏幕类名；MTR 大改 UI 后需要同步 mixin。

## 许可

GPL-3.0（见 `LICENSE`）。Fabric id：`rongmc_transport_ui`。
