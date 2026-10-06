# WTFIM

**What the Fuck Is Matcha?** is a clean Minecraft 1.21.1 / NeoForge rebuild inspired by Matcha Flavoured and designed to integrate deliberately with Overgeared.

Current version: **0.1.0-alpha**. Development branch `feature/copper` is building the first gameplay vertical slice.

Foundation is validated and retained as the baseline. On `feature/copper`, the canonical ten-item Copper equipment family is registered and registration-validated; its self-contained models, textures, language, and standard item tags are now implemented. Copper manufacturing/progression integration is still incomplete. Steel, Shakudo, Hepatizon, Electrum, Adamant, mobs, and legacy migration remain unimplemented.

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
