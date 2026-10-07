# WTFIM — Design Decisions

## ADR-001 — Clean WTFIM Foundation

**Status:** Accepted

### Decision

WTFIM is rebuilt as a clean NeoForge mod rather than extending the old Unified JAR or importing the old Matcha datapack/resource-pack architecture wholesale.

Foundation uses a conventional NeoForge structure with a single mod entrypoint, DeferredRegister-based registries, self-contained assets, explicit metadata, and permanent documentation.

### Reason

The previous combined architecture changed Java registrations, carrier migration, datapack replacements, assets, progression, and compatibility behavior at the same time. That made failures difficult to isolate and made the project harder to reason about.

### Consequences

- `0.1.0-alpha` contains no gameplay progression.
- `wtfim:test_item` is temporary and exists only to validate registry/resource/save behavior.
- Matcha systems are rebuilt one vertical slice at a time after Foundation passes validation.
- Old carrier IDs are not introduced as a default implementation strategy.
- No stable Foundation tag is created until runtime checks pass.

## Accepted project direction carried into later versions

These decisions are recorded here as context only; Foundation does not implement them:

- Matcha Copper will be the canonical Copper equipment family.
- Matcha Steel will be the canonical Steel equipment family.
- Iron, Gold, and Diamond manufacturing authority belongs to Overgeared.
- Shakudo and Hepatizon will be real WTFIM alloys branching from Copper.
- Electrum will be a real WTFIM alloy branching from Diamond.
- Vanilla Netherite IDs become Adamant.
- Overgeared's Diamond Upgrade Smithing Template is used for Steel -> Diamond.
- Matcha defines progression; Overgeared defines craftsmanship.

## ADR-002 — Foundation validation baseline

**Status:** Accepted — 2026-10-06

### Decision

WTFIM `0.1.0-alpha` Foundation is considered validated after passing the Gradle build, clean DEV runtime, save/reload, EMI indexing, Overgeared coexistence, and the real modpack integration boot/test.

### Evidence boundary

Known Overgeared 1.6.19 recipe/EMI log errors are treated as external baseline noise because they reproduce independently of WTFIM and are documented in `TEST_MATRIX.md` / `OVERGEARED_COMPAT.md`. WTFIM Foundation does not patch them.

### Consequence

`v0.1.0-alpha` is the baseline tag. Gameplay work starts after this point in vertical slices, beginning with Copper; Foundation-only behavior should remain reproducible from the tag.

## ADR-003 — Canonical Copper Equipment and Overgeared Manufacturing

**Status:** Accepted — 2026-10-06

### Context

The exact Matcha 1.21.1 backport and Overgeared 1.21.1-1.6.19 both define full Copper equipment families, but with separate registry IDs, stats, recipes, mining gates, and player-facing progression.

Leaving both final families active would create duplicate Copper progression and recipe-viewer confusion. Reproducing Matcha's ordinary shaped Copper recipes would also bypass Overgeared's intended craftsmanship.

The source audit additionally confirmed that Overgeared's custom final-tool assembly copies forging-quality and creator data from forged parts, and its forging recipes can produce non-`overgeared` outputs. This gives WTFIM a clean integration seam without reimplementing Overgeared's craftsmanship system.

### Decision

WTFIM registers the canonical ten-item Copper equipment family:

- `wtfim:copper_sword`
- `wtfim:copper_axe`
- `wtfim:copper_pickaxe`
- `wtfim:copper_shovel`
- `wtfim:copper_hoe`
- `wtfim:copper_shears`
- `wtfim:copper_helmet`
- `wtfim:copper_chestplate`
- `wtfim:copper_leggings`
- `wtfim:copper_boots`

WTFIM preserves the audited Matcha core base stats and visual identity for those items, except that Copper harvest gating follows Overgeared's progression boundary rather than Matcha's more permissive incorrect-block tag.

Overgeared owns the Copper manufacturing path:

```text
Copper acquisition
-> heating
-> blueprints + forged heads/blades/plates
-> assembly / armor forging
-> canonical wtfim:copper_* equipment
```

The five standard tools remain Overgeared custom-assembly outputs so forging-quality and creator data can transfer from forged parts. The four armor pieces remain Overgeared forging outputs using Copper Plates and existing material costs.

Copper Shears are a WTFIM addition because Overgeared has no Copper Shears. They will be forged from two heated Copper Ingots, initially targeting three hammering actions, and will not use forging quality.

Matcha's direct shaped Copper equipment recipes are not reproduced.

Canonical Copper equipment recycling follows Overgeared's economy and returns one `overgeared:copper_nugget`, not Matcha's one-Ingot return.

Overgeared's nine finished `overgeared:copper_*` items remain registered for compatibility but are excluded from normal WTFIM survival progression. Compatibility must redirect final recipes, prevent ordinary smithing-profession trade leakage, and hide the obsolete final family from EMI where feasible.

Starting with the Copper implementation version, Overgeared 1.21.1-1.6.19+ is a required runtime dependency for intended WTFIM progression.

### Matcha Copper classification

- Standard Copper tools: **KEEP**, rebuilt as `wtfim:` items.
- Copper armor: **KEEP**, rebuilt as `wtfim:` items.
- Copper Shears: **KEEP**, rebuilt as a forged `wtfim:` item.
- Matcha Copper stats: **KEEP**, except mining gate.
- Direct shaped Copper gear crafting: **DROP**.
- One-Ingot Copper equipment recycling: **CHANGE**.
- Matcha Copper harvest gate: **CHANGE** to Overgeared progression.
- Copper Compass: **DEFER**.
- Copper Dolabra: **DEFER**.
- Copper Mattock: **DEFER**.
- Dormant Matcha Copper equipment loot: **DROP** from the base Copper acquisition design.
- Legacy CMD/carrier architecture: **DROP**.
- Oxidation variants/mechanics: **DEFER**.

### Consequences

- WTFIM owns final Copper identity, stats, assets, repair behavior, and progression teaching.
- Overgeared owns heating, blueprints, parts, plates, forging, assembly, and quality.
- The normal survival graph must expose only one final Copper equipment family.
- No easy fallback crafting path is added when Overgeared is present.
- Copper implementation must runtime-test quality propagation, EMI presentation, villager trades, mining gates, recycling, and duplicate-route suppression.
- Shakudo/Hepatizon remain out of scope for this slice.

See `docs/COPPER_AUDIT.md` for the full source audit and conflict analysis.


## ADR-004 — Suppress duplicate Overgeared Copper villager offers

**Status:** Accepted — 2026-10-06

### Context

Overgeared 1.21.1-1.6.19 adds both useful forged Copper intermediates and its obsolete finished Copper equipment to Weaponsmith, Toolsmith, and Armorer trade pools. WTFIM redirects standard manufacturing to canonical `wtfim:copper_*` outputs, so allowing villagers to continue selling finished `overgeared:copper_*` gear would preserve a second normal survival route and bypass WTFIM's canonical Copper progression.

Removing all Overgeared Copper trades is not acceptable because useful Copper blades and tool heads must remain available.

### Decision

WTFIM listens to NeoForge's `VillagerTradesEvent` at `LOWEST` priority so Overgeared has already populated and wrapped its trade factories.

For Weaponsmith, Toolsmith, and Armorer pools, WTFIM wraps the finalized `VillagerTrades.ItemListing` entries. At offer-generation time, the wrapper delegates to the original listing and suppresses the offer only when its result is one of the nine obsolete finished IDs:

- `overgeared:copper_sword`
- `overgeared:copper_axe`
- `overgeared:copper_pickaxe`
- `overgeared:copper_shovel`
- `overgeared:copper_hoe`
- `overgeared:copper_helmet`
- `overgeared:copper_chestplate`
- `overgeared:copper_leggings`
- `overgeared:copper_boots`

No reflection, mixin, or direct reference to Overgeared implementation classes is used.

Finished Overgeared Copper offers are **removed**, not replaced with WTFIM finished gear. Villagers must not become a bypass around Overgeared forging.

### Consequences

- Useful Overgeared Copper heads/blades/plates and unrelated trades remain intact.
- New villager offer generation should not expose finished `overgeared:copper_*` equipment.
- Existing villagers that already persisted obsolete offers are not migrated by this boundary.
- Creative-tab and EMI visibility of registered Overgeared items is a separate cleanup concern and does not by itself constitute a survival progression route.
- The filter must be runtime-tested against Weaponsmith, Toolsmith, and Armorer while confirming useful Copper intermediate trades still appear.

### Runtime validation — 2026-10-07

The corrected explicit LOWEST-priority listener was exercised in WTFIM-DEV. Diagnostics confirmed that WTFIM wrapped the Armorer, Toolsmith, and Weaponsmith trade pools and suppressed generated finished Copper offers in each relevant profession path, including `overgeared:copper_sword`, `overgeared:copper_axe`, `overgeared:copper_pickaxe`, `overgeared:copper_leggings`, and `overgeared:copper_boots`. No finished Overgeared Copper offer leaked during the focused pass, while useful Copper head trades had already been observed. ADR-004's implementation is therefore runtime-validated.
