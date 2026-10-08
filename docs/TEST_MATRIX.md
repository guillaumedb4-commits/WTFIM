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
- [x] Foundation test-item model/texture were runtime-validated before retirement. Current Copper builds intentionally no longer contain `test_item` assets.

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
- [x] `wtfim:test_item` removal condition is satisfied by the DEV-validated real Copper registration/resource smoke coverage; the temporary item is retired in the Copper cleanup boundary.

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
- [x] Source declares Overgeared `1.21.1-1.6.19+` as required/BOTH and orders WTFIM AFTER Overgeared. Runtime metadata/version-range validation remains part of the next DEV test.
- [x] `wtfim:test_item` is intentionally retired after real Copper registration/resource/persistence/EMI smoke coverage replaced the Foundation probe item. Retirement build/runtime validation passed in WTFIM-DEV on 2026-10-08: build successful, ID absent, EMI entry absent, existing world loaded, and Copper Pickaxe/Chestplate remained healthy.

#### Assets / language

- [x] All ten canonical items have models. (WTFIM-DEV runtime validation, 2026-10-06.)
- [x] All required item/armor textures resolve. (WTFIM-DEV runtime validation, 2026-10-06.)
- [x] All ten canonical items have English translations. (WTFIM-DEV runtime validation, 2026-10-06.)
- [x] No broken/missing Copper translation keys observed in WTFIM-DEV.
- [x] No global vanilla Copper resource overrides are required.

#### Tool / armor behavior

- [x] Tool durability = 350. (Operator runtime validation, 2026-10-07.)
- [ ] Tool mining speed = 6.0 baseline before quality.
- [ ] Tool enchantability = 13.
- [x] Sword = 5 damage / 1.6 speed baseline. (Operator runtime validation, 2026-10-07.)
- [x] Axe = 9 damage / 0.8 speed baseline. (Operator runtime validation, 2026-10-07.)
- [x] Pickaxe = 3 damage / 1.2 speed baseline. (Operator runtime validation, 2026-10-07.)
- [x] Shovel = 3.5 damage / 1.0 speed baseline. (Operator runtime validation, 2026-10-07.)
- [x] Hoe = 1 damage / 2.0 speed baseline. (Operator runtime validation, 2026-10-07.)
- [x] Copper Shears durability = 300. (Operator runtime validation, 2026-10-07.)
- [x] Copper Shears perform shearing behavior. Vanilla comparison was acceptable to the operator; no speed change is required.
- [x] Copper repair uses Copper Ingots for a standard tool, Copper armor, and Copper Shears. (Operator anvil validation, 2026-10-07.)
- [ ] Helmet = armor 2 / durability 200. **Durability 200 runtime-validated; individual armor value not separately measured.**
- [ ] Chestplate = armor 4 / durability 200. **Durability 200 runtime-validated; individual armor value not separately measured.**
- [ ] Leggings = armor 3 / durability 200. **Durability 200 runtime-validated; individual armor value not separately measured.**
- [ ] Boots = armor 1 / durability 200. **Durability 200 runtime-validated; individual armor value not separately measured.**
- [ ] Armor enchantability = 8.
- [ ] Armor toughness = 0.
- [ ] Armor knockback resistance = 0.
- [ ] No unintended fire resistance/special behavior.

#### Mining progression

- [x] Copper cannot correctly harvest `#minecraft:needs_iron_tool`. (Diamond Ore runtime validation, 2026-10-07.)
- [x] Copper cannot correctly harvest `#minecraft:needs_diamond_tool`. (Obsidian runtime validation, 2026-10-07.)
- [x] Copper does not correctly harvest an actual `#overgeared:needs_steel_tool` member (`minecraft:obsidian`). **The exact Overgeared tag contains Obsidian/Crying Obsidian, which also overlap the vanilla higher-tier gate, so independent runtime causality is not isolatable.**
- [x] Iron-stage behavior is preserved: Iron Ore is correctly harvested by the canonical Copper Pickaxe while Diamond/Obsidian remain gated. (Operator runtime validation, 2026-10-07.)
- [x] Mining behavior remains correct across Poor/Well/Expert/Perfect/Master qualities; no quality bypasses the Copper harvest gate. (Operator runtime validation, 2026-10-07.)

#### Overgeared manufacturing

Copper Shears source boundary:
- [x] `wtfim:copper_shears` has a dedicated `overgeared:forging` recipe.
- [x] Recipe uses exactly two `overgeared:heated_copper_ingot`.
- [x] Recipe uses Stone-tier forging, 3 hammering actions, no quenching, no polishing, and explicitly `has_quality: false`.
- [x] Recipe is categorized as Overgeared forging `MISC` and requires no blueprint.
- [x] Recipe uses the anti-diagonal Shears shape (`" #"`, `"# "`), avoiding the exact Overgeared Copper Hammer Head pattern (`"# "`, `" #"`).
- [x] Exact Overgeared `ForgingRecipe` bytecode confirms forging patterns are not mirrored during matching; the two diagonals are distinct.
- [x] No direct crafting fallback is added.
- [x] Runtime confirm Shears do not receive unintended forging quality. (Operator validation, 2026-10-07.)
- [x] Runtime confirm Copper Shears no longer collide with `overgeared:copper_hammer_head` without relying on Polymorph. (Operator validated both recipes forge independently, 2026-10-07.)

Static manufacturing redirect status:

- [x] Existing Overgeared recipe IDs are overridden rather than duplicated.
- [x] Five tool assembly recipes retain `overgeared:crafting_shapeless`, original forged-part inputs, and stick inputs; only result IDs change to `wtfim:`.
- [x] Four armor recipes retain `overgeared:forging`, original Copper Plate patterns, hammering counts, Stone tier, no quenching, and no polishing; only result IDs change to `wtfim:`.
- [x] Copper Shears manufacturing was intentionally absent from the standard redirect commit; it is now implemented as its own source boundary.
- [x] No Matcha-style shaped fallback recipe is added.

Runtime manufacturing checks:

Manufacturing validation passed in WTFIM-DEV on 2026-10-06 for the five standard tools and four armor pieces. The operator confirmed the redirected outputs and quality-bearing tool assembly work. The obsolete Overgeared finished Copper items remain registered and are still visible in Creative/EMI; that is **not** evidence of a duplicate recipe, and visibility cleanup remains a separate task.


- [x] Heating path is visible and functional in WTFIM-DEV.
- [x] Sword blade -> `wtfim:copper_sword` works.
- [x] Axe head -> `wtfim:copper_axe` works.
- [x] Pickaxe head -> `wtfim:copper_pickaxe` works.
- [x] Shovel head -> `wtfim:copper_shovel` works.
- [x] Hoe head -> `wtfim:copper_hoe` works.
- [x] Plate forging -> all four WTFIM armor pieces works.
- [x] Copper Shears forge from two heated Copper Ingots. (Operator runtime validation, 2026-10-07.)
- [ ] Copper Shears initial three-hammering design feels acceptable. **Source implemented; runtime validation pending.**
- [x] No Matcha-style direct shaped Copper equipment recipes observed at runtime.

#### Forging quality

- [x] Tool quality component survives Overgeared custom assembly onto the canonical WTFIM tool.
- [x] Creator component transfers where expected. The same `overgeared:creator` value persisted from `overgeared:copper_pickaxe_head` to final `wtfim:copper_pickaxe`; after proper polishing and cooling/quenching preparation, a Well head remained Well on the final tool. (Operator runtime validation, 2026-10-07.)
- [x] Quality attribute modifiers apply correctly to WTFIM tools. Poor and Expert Copper Pickaxes showed the expected quality-derived stat changes. (Operator runtime validation, 2026-10-07.)
- [x] Quality attribute modifiers apply correctly to WTFIM armor; quality and its modifiers persisted after save/reload while retaining `wtfim:copper_chestplate` identity. (Operator runtime validation, 2026-10-07.)
- [x] Durability/mining-speed quality modifiers behave correctly on canonical WTFIM Copper tools. Poor/Expert values matched the expected Overgeared quality behavior. (Operator runtime validation, 2026-10-07.)
- [x] Copper Shears do not receive unintended quality. (Operator runtime validation, 2026-10-07.)

#### Duplicate-route suppression

Trade-filter source status:

Runtime note: the first annotation/class-registration implementation did not suppress a fresh Toolsmith's `overgeared:copper_axe` offer. Registration was then made explicit through `NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, VillagerTradesEvent.class, ...)`.

Focused DEV runtime validation on 2026-10-07 passed with the corrected listener. Diagnostics confirmed wrapping for Armorer (28 listings), Toolsmith (42), and Weaponsmith (17), plus actual suppression of finished `overgeared:copper_sword`, `copper_axe`, `copper_pickaxe`, `copper_leggings`, and `copper_boots`. No finished Overgeared Copper offer leaked during the focused pass.

- [x] LOWEST-priority `VillagerTradesEvent` handler is registered explicitly with `NeoForge.EVENT_BUS.addListener(...)`.
- [x] Handler targets Weaponsmith, Toolsmith, and Armorer only.
- [x] Trade factories are wrapped after Overgeared rather than inspected through reflection.
- [x] Only the nine finished `overgeared:copper_*` result IDs are suppressed.
- [x] Useful Copper heads/blades/plates are not ID-blocked.
- [x] Finished duplicate trades are removed rather than redirected to WTFIM finished gear.
- [x] Existing persisted villager offers are intentionally not migrated in this boundary.
- [x] Fresh Weaponsmith does not generate `overgeared:copper_sword`. (DEV runtime validation, 2026-10-07; suppression logged repeatedly.)
- [x] Fresh Toolsmith does not generate finished Overgeared Copper tools. **First runtime attempt failed; corrected explicit listener registration passed focused DEV validation on 2026-10-07.**
- [x] Fresh Armorer does not generate finished Overgeared Copper armor. (DEV runtime validation, 2026-10-07.)
- [x] Useful Copper head/blade trades still generate. (Runtime observed Copper Axe Head / Copper Shovel Head offers.)

- [x] Standard Copper manufacturing recipes no longer produce finished `overgeared:copper_*` gear.
- [x] Smithing-profession trades do not provide finished duplicate Overgeared Copper gear. **DEV runtime-validated across Weaponsmith, Toolsmith, and Armorer on 2026-10-07.**
- [ ] Useful heads/blades/plates remain available only where intentionally retained.
- [ ] No other installed DEV datapack restores a duplicate finished Copper route.

#### Recycling / economy

Source status:

- [x] Nine standard Overgeared Copper smelting recipe IDs are overridden to accept the canonical `wtfim:copper_*` item and still return one `overgeared:copper_nugget`.
- [x] Nine standard Overgeared Copper blasting recipe IDs are overridden the same way.
- [x] `wtfim:copper_shears` has matching smelting and blasting recycling recipes because Overgeared has no native Copper Shears.
- [x] Overgeared's 0.1 XP and 200-tick smelting / 100-tick blasting values are preserved.
- [x] Matcha's one-full-Copper-Ingot recycling recipe is not reproduced.

Runtime checks:

- [x] Canonical WTFIM Copper equipment recycles to one `overgeared:copper_nugget` in a furnace. (Operator runtime validation, 2026-10-07.)
- [x] Canonical WTFIM Copper equipment recycles to one `overgeared:copper_nugget` in a blast furnace. (Operator runtime validation, 2026-10-07.)
- [x] Copper Shears recycle correctly through both furnace types. (Operator runtime validation, 2026-10-07.)
- [x] No one-Ingot Matcha-style Copper equipment recycling remains. (Operator runtime validation, 2026-10-07.)
- [ ] Recycling cannot create a material duplication exploit.

#### EMI / discoverability

- [x] EMI indexes the canonical WTFIM Copper items in the presentation-validation build.
- [x] EMI shows the Overgeared Copper manufacturing chain with canonical WTFIM final outputs.
- [x] EMI shows canonical WTFIM outputs for final Copper equipment.
- [ ] Duplicate finished Overgeared Copper items are hidden where feasible.
- [x] No Matcha-style direct Copper gear recipe appears.
- [ ] Copper progression advancement triggers as designed.

#### Persistence / logs / environments

- [x] Copper items survive save/reload. (Operator validation, 2026-10-06.)
- [x] Forging quality and its applied effects survive save/reload on canonical WTFIM Copper tools; item identity remains `wtfim:copper_pickaxe`. (Operator runtime validation, 2026-10-07.)
- [x] No new WTFIM/Copper manufacturing errors in `latest.log`; WTFIM + Overgeared + EMI load and EMI completes reload. Known Overgeared diamond recipe noise and stale EMI persistent-data errors remain external/baseline cleanup items.
- [ ] Clean WTFIM-DEV full Copper slice passes. Standard manufacturing, Copper Shears behavior, villager-trade suppression, Copper recycling, and Copper mining-gate boundaries passed; cleanup and remaining behavior checks remain.
- [ ] WTFIM-INTEGRATION real modpack passes.
- [ ] No Copper version tag is created before both environments pass.

## Failure triage rule

For crashes, inspect `latest.log` and/or the crash report and identify the first meaningful `Caused by:`. Record verified causes separately from inference; do not patch from the final surface error alone.
