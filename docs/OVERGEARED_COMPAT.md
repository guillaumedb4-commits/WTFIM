# WTFIM — Overgeared Compatibility

## Foundation target

Primary compatibility target: **Overgeared 1.21.1-1.6.19**.

Foundation has no compile-time dependency on Overgeared and declares no hard runtime dependency. This is intentional: `0.1.0-alpha` contains no shared recipes, tags, registries, attributes, quality integration, or progression hooks.

The compatibility requirement for Foundation is therefore strict but simple:

- NeoForge loads WTFIM and Overgeared together.
- No registry collision occurs.
- No error attributable to WTFIM or a WTFIM↔Overgeared interaction appears in `latest.log`.
- The smoke-test item behaves normally with Overgeared installed.

## Responsibility boundary for later versions

Accepted direction:

- WTFIM / Matcha defines **what** materials, tiers, alloys, and progression exist.
- Overgeared defines **how** standard equipment is physically manufactured and forged.

Foundation must not pre-empt later integration decisions by adding tags, recipes, or compatibility shims before those slices are designed.

## Foundation runtime result — 2026-10-06

The clean DEV stack used Minecraft 1.21.1, NeoForge 21.1.251, WTFIM 0.1.0-alpha, Overgeared 1.21.1-1.6.19 and EMI 1.1.24. WTFIM initialized, its resources loaded, the test item survived save/reload, and EMI indexed it.

Known log errors are baseline Overgeared 1.6.19 behavior rather than WTFIM compatibility regressions:

- Overgeared ships nine `minecraft:diamond_*` placeholder recipe resources using `minecraft:air`; Minecraft rejects those recipe files.
- Overgeared's EMI plugin emits an unregistered `overgeared:flint_knapping` explanation-category error while EMI otherwise completes reload.

WTFIM 0.1 does not patch these external issues. They are retained as a baseline to compare against later WTFIM integration work.
