# WTFIM — Design Spec

## Project target

WTFIM is a clean NeoForge mod for Minecraft 1.21.1.

Foundation baseline:

- Minecraft: `1.21.1`
- NeoForge compile/minimum target: `21.1.251`
- Java: `21`
- Mod ID: `wtfim`
- Package root: `dev.wtfim`
- Primary compatibility target: Overgeared `1.21.1-1.6.19`
- Foundation version: `0.1.0-alpha`

## 0.1.0-alpha scope

Foundation exists only to prove the clean project architecture.

It contains:

- Gradle / ModDevGradle project configuration.
- One NeoForge `@Mod` entrypoint.
- Deferred-register item registration.
- One disposable item: `wtfim:test_item`.
- English translation, generated item model, and standalone texture.
- NeoForge metadata and resource-pack metadata.
- Basic initialization logging.
- Permanent project documentation.

It deliberately does **not** contain Matcha gameplay systems, progression, recipes, loot, trades, mobs, carrier migration, vanilla replacement logic, or the old Matcha datapack/resource pack.

## Architectural rule

Features are introduced as vertical slices only after Foundation passes the DEV and integration checks in `TEST_MATRIX.md`.

The old Matcha backport, datapack, and resource pack remain behavioral/design references. They are not the WTFIM architecture.
