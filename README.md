# WTFIM

**What the Fuck Is Matcha?** is a clean Minecraft 1.21.1 / NeoForge rebuild inspired by Matcha Flavoured and designed to integrate deliberately with Overgeared.

Current version: **0.2.0-alpha**. Development branch `feature/copper` contains the validated Copper gameplay vertical slice and is in release closeout.

Foundation is validated and retained as the baseline. Copper is the first completed gameplay vertical slice: the canonical ten-item Copper equipment family, Overgeared manufacturing integration, quality/creator behavior, mining gates, recycling, duplicate-route suppression, EMI discoverability, progression advancement, persistence, and real-modpack integration have passed validation. Steel, Shakudo, Hepatizon, Electrum, Adamant, mobs, and legacy migration remain unimplemented.

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
build/libs/wtfim-0.2.0-alpha.jar
```

Foundation validation passed on 2026-10-06 and remains the `v0.1.0-alpha` baseline. Copper passed DEV, real-modpack integration, and final `0.2.0-alpha` release-candidate artifact validation on 2026-10-09. `v0.2.0-alpha` is the accepted Copper release tag and is the only remaining closeout action. See `docs/TEST_MATRIX.md` for recorded evidence.
