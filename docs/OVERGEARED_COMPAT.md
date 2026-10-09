# WTFIM — Overgeared Compatibility

## Foundation target

Primary compatibility target: **Overgeared 1.21.1-1.6.19**.

Foundation has no compile-time dependency on Overgeared and declares no hard runtime dependency. This is intentional for `0.1.0-alpha`: Foundation contains no shared recipes, tags, registries, attributes, quality integration, or progression hooks.

## Responsibility boundary

- WTFIM / Matcha defines **what** materials, tiers, alloys, and progression exist.
- Overgeared defines **how** standard equipment is physically manufactured and forged.

## Foundation runtime baseline — 2026-10-06

The clean DEV stack used Minecraft 1.21.1, NeoForge 21.1.251, WTFIM 0.1.0-alpha, Overgeared 1.21.1-1.6.19 and EMI 1.1.24. WTFIM initialized, its resources loaded, the test item survived save/reload, and EMI indexed it.

Known log errors are baseline Overgeared 1.6.19 behavior rather than WTFIM compatibility regressions:

- Overgeared ships nine `minecraft:diamond_*` placeholder recipe resources using `minecraft:air`; Minecraft rejects those recipe files.
- Overgeared's EMI plugin emits an unregistered `overgeared:flint_knapping` explanation-category error while EMI otherwise completes reload.
- A missing `overgeared:smithing` block tag warning and an optional JEI-class warning are also present.

These remain external baseline noise unless the Copper implementation demonstrably changes them.

## Copper compatibility contract — accepted

The Copper audit confirms that Matcha and Overgeared otherwise create separate complete Copper final-equipment families.

WTFIM resolves this by making `wtfim:copper_*` the only canonical normal-progression final family while preserving Overgeared as the manufacturing authority.

### WTFIM owns

- final Copper item IDs;
- Matcha-derived base stats;
- repair behavior;
- assets/translations;
- advancement/progression teaching;
- later progression references.

### Overgeared owns

- heating;
- blueprints;
- Copper heads/blades;
- Copper Plates;
- forging/hammering;
- standard tool assembly;
- forging quality and creator metadata;
- Copper-stage mining gate.

### Recipe integration

**Implemented and DEV runtime-validated.** WTFIM overrides the existing Overgeared recipe IDs `overgeared:copper_sword`, `copper_axe`, `copper_pickaxe`, `copper_shovel`, `copper_hoe`, `copper_helmet`, `copper_chestplate`, `copper_leggings`, and `copper_boots`. The original inputs, patterns, hammering counts, categories, tiers, and recipe serializers are preserved; only the final result IDs change.

Standard tools redirect Overgeared final Copper assembly to:

```text
wtfim:copper_sword
wtfim:copper_axe
wtfim:copper_pickaxe
wtfim:copper_shovel
wtfim:copper_hoe
```

The custom Overgeared shapeless recipe type must be retained so forging-quality and creator data can transfer from the forged part.

Copper armor forging retains Overgeared's exact plate costs/patterns/hammering values while returning:

```text
wtfim:copper_helmet
wtfim:copper_chestplate
wtfim:copper_leggings
wtfim:copper_boots
```

Copper Shears have no native Overgeared equivalent. WTFIM implements the accepted design as a dedicated `wtfim:copper_shears` forging recipe: two `overgeared:heated_copper_ingot`, Stone-tier forging, three hammering actions, no blueprint, no quenching, no polishing, and explicit `has_quality: false`. Runtime testing exposed that the original diagonal matched Overgeared's `copper_hammer_head` recipe exactly. The Shears pattern is therefore the opposite diagonal (`" #"`, `"# "`), matching Overgeared's vanilla Iron Shears visual convention while remaining distinct because Overgeared's forging matcher does not mirror patterns. Polymorph may coexist in the modpack but is not required to disambiguate this canonical path.

**DEV runtime-validated — 2026-10-07.** The operator confirmed both the Copper Hammer Head and Copper Shears can be forged independently with their respective diagonal patterns, and the resulting Copper Shears have no forging quality. This closes the collision/no-quality behavior checks; the separate subjective balance check for whether three hammering actions feels right remains open.

### Duplicate final Overgeared items

These remain registered by Overgeared:

- `overgeared:copper_sword`
- `overgeared:copper_axe`
- `overgeared:copper_pickaxe`
- `overgeared:copper_shovel`
- `overgeared:copper_hoe`
- `overgeared:copper_helmet`
- `overgeared:copper_chestplate`
- `overgeared:copper_leggings`
- `overgeared:copper_boots`

WTFIM must not attempt to unregister them.

Instead, the Copper compatibility layer must ensure they do not remain a second normal survival family:

1. redirect/replace final recipes;
2. prevent ordinary smithing-profession trades from leaking the duplicate finished set;
3. retain useful forged parts/plates when appropriate;
4. hide obsolete finished Copper outputs from EMI where feasible;
5. leave registry IDs available for compatibility.

A surviving ordinary recipe/trade path to the duplicate finished family is a Copper-slice failure.

### Villager trade suppression

**Implemented and DEV runtime-validated — 2026-10-07.** WTFIM explicitly registers a LOWEST-priority `VillagerTradesEvent` listener with `NeoForge.EVENT_BUS.addListener(...)` after Overgeared's normal trade population. The filter delegates to every smithing-profession trade factory and returns `null` only when the generated result is one of the nine obsolete finished `overgeared:copper_*` items.

This intentionally preserves useful Copper sword blades/tool heads and unrelated Overgeared trades. It uses only vanilla/NeoForge trade interfaces: no reflection, mixin, or direct compile-time dependency on Overgeared classes. Existing villagers with already-saved obsolete offers are outside this boundary.

The first DEV attempt using annotation scanning via `EVENT_BUS.register(Class)` did not suppress a fresh Toolsmith's finished Copper Axe offer, while legitimate head trades remained present. Registration was therefore made explicit with `addListener`.

The corrected build was then focused-runtime-tested in WTFIM-DEV. The listener wrapped Armorer (28 listings), Toolsmith (42), and Weaponsmith (17), and fresh offer generation logged suppression of `overgeared:copper_sword`, `overgeared:copper_axe`, `overgeared:copper_pickaxe`, `overgeared:copper_leggings`, and `overgeared:copper_boots`. No finished Overgeared Copper offer leaked during the focused pass. This validates the ADR-004 mechanism across all three smithing professions; the static nine-ID block set still defines the complete suppression boundary. Temporary INFO diagnostics remain in place for now and are not part of this documentation-only closeout.

DEV validation confirmed the standard recipe paths now end in `wtfim:copper_*`. A focused DEV audit on 2026-10-08 then checked all nine obsolete finished `overgeared:copper_*` entries in EMI: none exposed an active production recipe. The useful Copper Sword Blade, Axe/Pickaxe/Shovel/Hoe Heads, and Copper Plate retained their intended Overgeared routes. `/datapack list enabled` showed only vanilla built-in data and NeoForge `mod_data`, with no external user datapack capable of restoring a duplicate finished route. The old finished items remain registered. Recipe-viewer cleanup is implemented through the standard common item tag `c:hidden_from_recipe_viewers`, containing exactly the nine obsolete finished `overgeared:copper_*` IDs. This avoids a direct EMI API dependency while preserving registry compatibility. DEV runtime validation on 2026-10-08 confirmed all nine obsolete finished items disappear from EMI, while the Copper Sword Blade, four Copper tool heads, Copper Plate, and canonical `wtfim:copper_*` items remain visible. Creative-tab visibility is intentionally unchanged by this boundary.

### Mining gate

WTFIM Copper must treat these as incorrect:

- `#minecraft:needs_iron_tool`
- `#minecraft:needs_diamond_tool`
- `#overgeared:needs_steel_tool`

This deliberately follows Overgeared's progression rather than Matcha's more permissive Copper harvest rules.

**DEV runtime-validated — 2026-10-07.** A canonical Copper Pickaxe correctly harvested Iron Ore, did not correctly harvest Diamond Ore, and did not correctly harvest Obsidian. The same harvest boundary held for Poor, Well, Expert, Perfect, and Master quality Copper Pickaxes, confirming that forging quality does not raise the harvest tier.

Exact Overgeared 1.6.19 source audit shows `#overgeared:needs_steel_tool` contains `minecraft:obsidian` and `minecraft:crying_obsidian`. WTFIM statically includes that tag in `wtfim:incorrect_for_copper_tool`. Because both members also overlap Minecraft's higher vanilla harvest gate, runtime testing confirms the effective behavior on a real member but cannot independently attribute the failure to the Overgeared tag alone.

### Recycling

Canonical WTFIM Copper equipment recycles through the Overgeared Copper economy to one `overgeared:copper_nugget`.

**Implemented and DEV runtime-validated — 2026-10-07.** For the nine standard Copper tools/armor pieces, WTFIM overrides Overgeared's existing smelting and blasting recipe IDs and changes only the ingredient from `overgeared:copper_*` to the canonical `wtfim:copper_*` item. The Overgeared result, XP, and cooking times are preserved exactly: one `overgeared:copper_nugget`, 0.1 XP, 200-tick smelting / 100-tick blasting.

Because Overgeared has no Copper Shears, WTFIM adds matching `wtfim:` smelting and blasting recipes for `wtfim:copper_shears` with the same one-nugget result, XP, and timings. Matcha's one-full-ingot recycling is not reproduced.

The operator validated all ten canonical Copper items in both furnace types: each path returned exactly one `overgeared:copper_nugget`, Copper Shears worked in furnace and blast furnace, and no full-Copper-Ingot recycling path appeared. Focused economy-loop validation on 2026-10-09 then confirmed Copper Nugget conversions expose no profitable shortcut, the cheapest canonical Copper equipment costs more Copper than the fixed one-nugget recycle return, and a full forge -> recycle cycle is materially lossy. The Copper recycling duplication check is therefore closed.

### Dependency transition

Foundation remains historically correct as an Overgeared-optional smoke-test mod.

Starting with the Copper manufacturing integration, Overgeared **1.21.1-1.6.19+ is declared as a required BOTH-side dependency**, ordered before WTFIM, because canonical Copper acquisition depends on its recipe types, stations, intermediates, and quality system.

Do not add a direct crafting fallback solely to keep WTFIM standalone.

Overgeared Universal is not part of the Copper integration.

### Runtime validation required

The nine standard Copper recipe redirects and required dependency metadata are now DEV runtime-validated. Copper is still not complete until the remaining checks are finished:

- WTFIM outputs appear in Overgeared manufacturing;
- tool quality data survives standard assembly and save/reload — DEV runtime-validated 2026-10-07;
- tool quality modifiers behave correctly on canonical WTFIM Copper tools — DEV runtime-validated 2026-10-07;
- creator transfer through standard Copper tool assembly is DEV runtime-validated 2026-10-07; the same `overgeared:creator` value persisted from forged head to final `wtfim:copper_pickaxe`, and a properly polished/cooled Well head remained Well on the final tool;
- armor quality behaves correctly on canonical WTFIM Copper armor and survives save/reload — DEV runtime-validated 2026-10-07;
- duplicate Overgeared final recipes are absent;
- duplicate finished Copper trades are absent — DEV runtime-validated 2026-10-07;
- EMI presents one coherent final Copper family;
- mining gates match the accepted progression — DEV runtime-validated 2026-10-07;
- recycling returns the accepted amount — DEV runtime-validated 2026-10-07;
- no new WTFIM/Overgeared errors appear beyond Foundation baseline.

See `COPPER_AUDIT.md`.


## Current manufacturing boundary

The first manufacturing commit stopped at the nine standard Matcha/Overgeared-overlap pieces. Copper Shears forging behavior is DEV runtime-validated as its own boundary. Finished-Copper villager trade suppression is DEV runtime-validated under ADR-004. Creative/EMI hiding and advancements remain separate so failures can be isolated. Copper recycling recipes are DEV runtime-validated as their own boundary.
