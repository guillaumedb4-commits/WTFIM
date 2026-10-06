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

- Nine `data/minecraft/recipe/diamond_*.json` resources are shapeless recipes whose ingredient and result are `minecraft:air`. Minecraft 1.21.1 rejects those recipe files.
- Overgeared's EMI plugin emits `overgeared:explanation/flint_knapping loaded with unregistered category: overgeared:flint_knapping`. EMI still completes its reload and indexes `wtfim:test_item`.
- A missing `overgeared:smithing` block tag warning and an optional JEI-class warning are also present.

These are recorded as **external baseline noise**, not as Foundation failures. Do not patch or suppress them merely because Copper work begins.

### Integration runtime

- [x] After DEV passes, boot WTFIM-INTEGRATION with the real modpack. (Runtime operator validation, 2026-10-06)
- [x] Reach title screen and load a test world.
- [x] Confirm `wtfim:test_item` still resolves correctly.
- [x] Confirm no new relevant registry/resource/Overgeared errors appear in `latest.log`; observed noise matches the known pre-WTFIM/Overgeared baseline already recorded above.

## Copper vertical slice — design/audit gate

### Documentation gate

- [x] Exact Matcha Backport 1.21.1 Copper inventory/stats/recipes/dependencies audited.
- [x] Exact Matcha v19 datapack Copper paths audited.
- [x] Exact Matcha resource-pack Copper identity/assets audited.
- [x] Exact Overgeared 1.21.1-1.6.19 Copper material/equipment/manufacturing audited.
- [x] Duplicate items, recipes, stats, mining gates, recycling, trades, and EMI risks identified.
- [x] Copper KEEP / CHANGE / DROP decisions accepted.
- [x] ADR-003 accepted.
- [x] Canonical ten-item WTFIM Copper family defined.
- [x] Overgeared manufacturing/quality responsibility defined.
- [x] Shakudo/Hepatizon and later systems remain outside Copper implementation scope.

## Copper registration implementation — static status

- [x] Source defines exactly ten canonical `wtfim:copper_*` equipment registrations.
- [x] Source defines the accepted 350-use / 6.0-speed / +2 / enchantability-13 Copper tool tier.
- [x] Source defines the accepted Copper armor material: 2/4/3/1, enchantability 8, zero toughness/knockback resistance.
- [x] Source defines Copper Shears at 300 durability with explicit Copper-Ingot repair.
- [x] Source defines `wtfim:incorrect_for_copper_tool` with the accepted Iron/Diamond/Overgeared-Steel boundaries.
- [x] No Copper manufacturing/acquisition recipes were added in the registration commit.
- [x] `wtfim:test_item` is intentionally retained until real Copper runtime/resource smoke coverage passes.

Registration validation completed in WTFIM-DEV on 2026-10-06. The build passed, all ten IDs resolved, armor equipped, Copper Shears sheared successfully, items persisted across save/reload, and the game did not crash. The uploaded log shows WTFIM initialized and only the expected missing Copper asset warnings for WTFIM at this stage.

The broader stat/mining/quality/manufacturing checks below remain intentionally unchecked.

## Copper presentation implementation — static status

- [x] Source contains models for all ten canonical Copper items.
- [x] Source contains the ten audited Matcha Copper item textures under the `wtfim` namespace.
- [x] Source contains Matcha Copper armor layer 1/2 textures under the `wtfim` namespace.
- [x] English names are defined for all ten canonical Copper items.
- [x] Vanilla tool/armor-slot/trimmable/enchantable tags mirror the audited Matcha core Copper tag membership using `wtfim:` IDs.
- [x] No global `minecraft:` model/texture override was introduced.

Presentation/runtime smoke validation passed in WTFIM-DEV on 2026-10-06: Copper names, item textures, equipped armor rendering, save/reload persistence, and EMI visibility were reported working. Tag-specific enchantment/trim behavior remains covered by later focused checks.

### Implementation/runtime checklist

#### Build / registry

- [x] `./gradlew build` succeeds with Copper registration. (Operator validation, 2026-10-06: `BUILD SUCCESSFUL`, 5 tasks executed.)
- [x] All ten canonical `wtfim:copper_*` IDs register at runtime. (`/give` verified for all ten in WTFIM-DEV, 2026-10-06.)
- [x] No unintended WTFIM Copper IDs observed during registration validation.
- [ ] Overgeared required dependency metadata is correct once manufacturing integration lands.
- [ ] `wtfim:test_item` removal/retention is intentional and documented at each boundary.

#### Assets / language

- [x] All ten canonical items have models. (WTFIM-DEV runtime validation, 2026-10-06.)
- [x] All required item/armor textures resolve. (WTFIM-DEV runtime validation, 2026-10-06.)
- [x] All ten canonical items have English translations. (WTFIM-DEV runtime validation, 2026-10-06.)
- [x] No broken/missing Copper translation keys observed in WTFIM-DEV.
- [x] No global vanilla Copper resource overrides are required.

#### Tool / armor behavior

- [ ] Tool durability = 350.
- [ ] Tool mining speed = 6.0 baseline before quality.
- [ ] Tool enchantability = 13.
- [ ] Sword = 5 damage / 1.6 speed baseline.
- [ ] Axe = 9 damage / 0.8 speed baseline.
- [ ] Pickaxe = 3 damage / 1.2 speed baseline.
- [ ] Shovel = 3.5 damage / 1.0 speed baseline.
- [ ] Hoe = 1 damage / 2.0 speed baseline.
- [ ] Copper Shears durability = 300.
- [x] Copper Shears perform shearing behavior. Vanilla comparison was acceptable to the operator; no speed change is required.
- [ ] Copper repair uses Copper Ingots.
- [ ] Helmet = armor 2 / durability 200.
- [ ] Chestplate = armor 4 / durability 200.
- [ ] Leggings = armor 3 / durability 200.
- [ ] Boots = armor 1 / durability 200.
- [ ] Armor enchantability = 8.
- [ ] Armor toughness = 0.
- [ ] Armor knockback resistance = 0.
- [ ] No unintended fire resistance/special behavior.

#### Mining progression

- [ ] Copper cannot correctly harvest `#minecraft:needs_iron_tool`.
- [ ] Copper cannot correctly harvest `#minecraft:needs_diamond_tool`.
- [ ] Copper cannot correctly harvest `#overgeared:needs_steel_tool`.
- [ ] Iron-stage blocks behave as intended in the Overgeared progression.
- [ ] Mining behavior remains correct across Poor/Well/Expert/Perfect/Master qualities.

#### Overgeared manufacturing

- [ ] Heating path is visible and functional.
- [ ] Sword blade -> `wtfim:copper_sword` works.
- [ ] Axe head -> `wtfim:copper_axe` works.
- [ ] Pickaxe head -> `wtfim:copper_pickaxe` works.
- [ ] Shovel head -> `wtfim:copper_shovel` works.
- [ ] Hoe head -> `wtfim:copper_hoe` works.
- [ ] Plate forging -> all four WTFIM armor pieces works.
- [ ] Copper Shears forge from two heated Copper Ingots.
- [ ] Copper Shears initial three-hammering design feels acceptable.
- [ ] No direct shaped Copper equipment recipes exist.

#### Forging quality

- [ ] Tool quality component transfers through Overgeared custom assembly.
- [ ] Creator component transfers where expected.
- [ ] Quality attribute modifiers apply correctly to WTFIM tools.
- [ ] Quality attribute modifiers apply correctly to WTFIM armor.
- [ ] Durability/mining-speed quality modifiers behave correctly.
- [ ] Copper Shears do not receive unintended quality.

#### Duplicate-route suppression

- [ ] No normal recipe produces finished `overgeared:copper_*` gear.
- [ ] Smithing-profession trades do not provide finished duplicate Overgeared Copper gear.
- [ ] Useful heads/blades/plates remain available only where intentionally retained.
- [ ] No other installed DEV datapack restores a duplicate finished Copper route.

#### Recycling / economy

- [ ] Canonical WTFIM Copper equipment recycles to one `overgeared:copper_nugget`.
- [ ] No one-Ingot Matcha-style Copper equipment recycling remains.
- [ ] Recycling cannot create a material duplication exploit.

#### EMI / discoverability

- [x] EMI indexes the canonical WTFIM Copper items in the presentation-validation build.
- [ ] EMI shows heating -> forging -> assembly/armor-forging progression.
- [ ] EMI shows canonical WTFIM outputs for final Copper equipment.
- [ ] Duplicate finished Overgeared Copper items are hidden where feasible.
- [ ] No Matcha-style direct Copper gear recipe appears.
- [ ] Copper progression advancement triggers as designed.

#### Persistence / logs / environments

- [x] Copper items survive save/reload. (Operator validation, 2026-10-06.)
- [ ] Forging quality survives save/reload.
- [x] No new Copper-registration crash/registry errors in `latest.log`. Expected missing WTFIM Copper model/armor-texture warnings are present because assets are the next unimplemented boundary; existing Overgeared recipe noise remains.
- [ ] Clean WTFIM-DEV passes.
- [ ] WTFIM-INTEGRATION real modpack passes.
- [ ] No Copper version tag is created before both environments pass.

## Failure triage rule

For crashes, inspect `latest.log` and/or the crash report and identify the first meaningful `Caused by:`. Record verified causes separately from inference; do not patch from the final surface error alone.
