# WTFIM

**What the Fuck Is Matcha?** is a clean Minecraft 1.21.1 / NeoForge rebuild inspired by Matcha Flavoured and designed to integrate deliberately with Overgeared.

Current version: **0.1.0-alpha — Foundation**.

Foundation contains one disposable registry smoke-test item, `wtfim:test_item`. It does not implement Copper, Steel, Shakudo, Hepatizon, Electrum, Adamant, progression, recipes, loot, mobs, trades, or legacy carrier migration.

## Build target

- Minecraft 1.21.1
- NeoForge 21.1.251+
- Java 21
- Mod ID: `wtfim`
- Primary compatibility target: Overgeared 1.21.1-1.6.19

## Build

```text
./gradlew build
```

Expected artifact:

```text
build/libs/wtfim-0.1.0-alpha.jar
```

Foundation validation passed on 2026-10-06. The local baseline is tagged `v0.1.0-alpha`; see `docs/TEST_MATRIX.md` for the recorded validation evidence.
