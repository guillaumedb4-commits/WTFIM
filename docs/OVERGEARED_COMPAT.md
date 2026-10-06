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

DEV validation confirmed the standard recipe paths now end in `wtfim:copper_*`. The old Overgeared finished Copper items are still visible in Overgeared's Creative tab and EMI index because Overgeared explicitly registers/displays them. Visibility alone is not a progression route. Creative/EMI hiding remains a separate compatibility-cleanup task; villager trades remain the more important survival-route blocker.

### Mining gate

WTFIM Copper must treat these as incorrect:

- `#minecraft:needs_iron_tool`
- `#minecraft:needs_diamond_tool`
- `#overgeared:needs_steel_tool`

This deliberately follows Overgeared's progression rather than Matcha's more permissive Copper harvest rules.

### Recycling

Canonical WTFIM Copper equipment should recycle through the Overgeared Copper economy to one `overgeared:copper_nugget`.

### Dependency transition

Foundation remains historically correct as an Overgeared-optional smoke-test mod.

Starting with the Copper manufacturing integration, Overgeared **1.21.1-1.6.19+ is declared as a required BOTH-side dependency**, ordered before WTFIM, because canonical Copper acquisition depends on its recipe types, stations, intermediates, and quality system.

Do not add a direct crafting fallback solely to keep WTFIM standalone.

Overgeared Universal is not part of the Copper integration.

### Runtime validation required

The nine standard Copper recipe redirects and required dependency metadata are now DEV runtime-validated. Copper is still not complete until the remaining checks are finished:

- WTFIM outputs appear in Overgeared manufacturing;
- quality and creator data survive standard tool assembly;
- quality modifiers behave correctly on WTFIM item classes;
- armor quality behaves correctly;
- duplicate Overgeared final recipes are absent;
- duplicate finished Copper trades are absent;
- EMI presents one coherent final Copper family;
- mining gates match the accepted progression;
- recycling returns the accepted amount;
- no new WTFIM/Overgeared errors appear beyond Foundation baseline.

See `COPPER_AUDIT.md`.


## Current manufacturing boundary

The first manufacturing commit stopped at the nine standard Matcha/Overgeared-overlap pieces. Copper Shears forging is now implemented as a separate boundary. Villager trade replacement, Creative/EMI hiding, recycling redirects, and advancements remain separate so failures can be isolated.
