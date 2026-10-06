# WTFIM — Test Matrix

## 0.1.0-alpha — Foundation

A compile is necessary but not sufficient. Do not create the `v0.1.0-alpha` tag until all runtime checks below pass.

### Static preflight performed in the Foundation build workspace

- [x] Java 21 source compiles in a signature-check harness using the NeoForge/Minecraft method descriptors exercised by the attached 1.21.1 Matcha backport.
- [x] `neoforge.mods.toml` parses as TOML.
- [x] `pack.mcmeta`, language JSON, and item-model JSON parse successfully.
- [x] `test_item.png` is a valid 16×16 PNG.
- [x] A manually assembled candidate JAR contains only WTFIM classes plus the expected resources; compile-time signature stubs are not packaged.

These checks are preflight only. They do **not** replace the Gradle build or NeoForge runtime tests below.

### Build / artifact

- [x] `./gradlew build` succeeds. (Runtime operator validation, 2026-10-06)
- [x] `build/libs/wtfim-0.1.0-alpha.jar` exists. (Confirmed by `Get-ChildItem .\build\libs\`)
- [ ] JAR contains `dev/wtfim/WTFIM.class`.
- [ ] JAR contains `dev/wtfim/registry/ModItems.class`.
- [ ] JAR contains `META-INF/neoforge.mods.toml`.
- [ ] JAR contains `assets/wtfim/lang/en_us.json`.
- [ ] JAR contains `assets/wtfim/models/item/test_item.json`.
- [ ] JAR contains `assets/wtfim/textures/item/test_item.png`.

### WTFIM-DEV runtime

- [x] NeoForge 1.21.1 reaches the title screen.
- [x] WTFIM appears in the loaded mods list.
- [x] No WTFIM registry errors occur.
- [x] `/give @s wtfim:test_item` works.
- [x] The item displays as `WTFIM Test Item`.
- [x] The item uses the WTFIM test model.
- [x] The item uses the WTFIM test texture.
- [x] Put the item in inventory, save/quit, reload the world, and confirm it survives.
- [x] EMI displays/indexes `wtfim:test_item` when EMI is installed.
- [x] Overgeared 1.21.1-1.6.19 loads alongside WTFIM.
- [x] No errors attributable to WTFIM or a WTFIM↔Overgeared interaction appear in `latest.log`. See known Overgeared baseline exceptions below.
- [x] Clean WTFIM-DEV instance passes Foundation scope, subject to the known Overgeared baseline exceptions below.

### Known Overgeared 1.6.19 baseline exceptions

The clean DEV log contains errors even without Matcha datapacks/resource packs or any other gameplay mods. Static inspection of the exact Overgeared 1.6.19 JAR confirms these originate in Overgeared itself, not WTFIM:

- Nine `data/minecraft/recipe/diamond_*.json` resources are shapeless recipes whose ingredient and result are `minecraft:air`. Minecraft 1.21.1 rejects these during recipe parsing.
- Overgeared's EMI integration emits `overgeared:explanation/flint_knapping loaded with unregistered category: overgeared:flint_knapping`. EMI still completes its reload and indexes `wtfim:test_item`.
- A missing `overgeared:smithing` block tag warning and an optional JEI-class warning are also present.

These are recorded as **external baseline noise**, not as Foundation failures. Foundation compatibility means WTFIM introduces no new registry/resource/EMI/Overgeared interaction error on top of that baseline. Do not patch or suppress Overgeared inside WTFIM 0.1.

### Integration runtime

- [x] After DEV passes, boot WTFIM-INTEGRATION with the real modpack. (Runtime operator validation, 2026-10-06)
- [x] Reach title screen and load a test world.
- [x] Confirm `wtfim:test_item` still resolves correctly.
- [x] Confirm no new relevant registry/resource/Overgeared errors appear in `latest.log`; observed noise matches the known pre-WTFIM/Overgeared baseline already recorded above.

## Failure triage rule

For crashes, inspect `latest.log` and/or the crash report and identify the first meaningful `Caused by:`. Record verified causes separately from inference; do not patch from the final surface error alone.
