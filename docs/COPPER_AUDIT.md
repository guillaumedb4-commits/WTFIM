# WTFIM — Copper Audit

## Status

**Accepted architecture audit — 2026-10-06**

This document records the static-source Copper audit that precedes implementation of the Copper vertical slice.

Evidence priority used:

1. Exact Matcha Backport 1.21.1 JAR
2. Exact Matcha v19 datapack
3. Exact Matcha resource pack
4. Exact Overgeared 1.21.1-1.6.19 JAR
5. Existing WTFIM static-audit documents
6. Current WTFIM GitHub source/docs

This is a static-source audit. Runtime behavior, final EMI presentation, trade-handler ordering, and quality-component behavior on WTFIM outputs must still be validated in WTFIM-DEV.

## Matcha Copper inventory

The audited backport contains 13 real `matcha:copper_*` IDs:

- `matcha:copper_sword`
- `matcha:copper_axe`
- `matcha:copper_pickaxe`
- `matcha:copper_shovel`
- `matcha:copper_hoe`
- `matcha:copper_shears`
- `matcha:copper_helmet`
- `matcha:copper_chestplate`
- `matcha:copper_leggings`
- `matcha:copper_boots`
- `matcha:copper_compass`
- `matcha:copper_dolabra`
- `matcha:copper_mattock`

The ten standard equipment pieces are the coherent core Copper tier. Copper Compass is a separate exploration utility. Copper Dolabra and Mattock are registered but have no normal v19 crafting path and are not required as later Shakudo/Hepatizon smithing bases.

## Matcha Copper stats

### Tool tier

- Durability: **350**
- Mining speed: **6.0**
- Tier attack bonus: **+2**
- Enchantability: **13**
- Repair ingredient: `minecraft:copper_ingot`
- Fire resistance: none

Effective combat attributes:

| Tool | Attack damage | Attack speed |
| --- | ---: | ---: |
| Sword | 5 | 1.6 |
| Axe | 9 | 0.8 |
| Pickaxe | 3 | 1.2 |
| Shovel | 3.5 | 1.0 |
| Hoe | 1 | 2.0 |

### Copper Shears

- Durability: **300**
- Standard shears behavior
- Copper Ingot repair
- No Copper-specific fire resistance or special combat behavior

### Armor

| Piece | Armor | Durability |
| --- | ---: | ---: |
| Helmet | 2 | 200 |
| Chestplate | 4 | 200 |
| Leggings | 3 | 200 |
| Boots | 1 | 200 |

Material-wide:

- Enchantability: **8**
- Toughness: **0**
- Knockback resistance: **0**
- Repair ingredient: `minecraft:copper_ingot`
- Equip sound: Iron
- Fire resistance: none

## Matcha acquisition and dependencies

The ten core Copper equipment items use ordinary shaped crafting directly from Copper Ingots. This is intentionally **not** preserved in WTFIM because it bypasses Overgeared craftsmanship.

Matcha also contains Copper equipment recycling recipes that return one full Copper Ingot. WTFIM will not preserve that economy unchanged.

Copper is the base equipment family for later Shakudo and Hepatizon smithing. Later Dolabra/Mattock transforms use the standard Copper Axe/Hoe as their bases, so Copper Dolabra/Mattock are not required to keep that later progression possible.

No meaningful Matcha advancement teaches the Copper equipment tier. The audited metal-progression tutorial coverage is incomplete.

Individual Copper equipment loot tables exist, including Dolabra/Mattock, but no current static datapack caller was found for those core equipment tables. Copper Compass has a separate referenced Stronghold-library loot path.

No normal Matcha villager acquisition path for the Copper equipment set was identified. Copper-equipment references in trade compatibility are associated with later alloy/recycling behavior rather than establishing the base Copper tier.

## Matcha mining gate

Matcha's Copper incorrect-block tag is comparatively permissive. It marks Obsidian/Crying Obsidian/Ender Chest plus Diamond and Gold ore families as incorrect.

This does not match Overgeared's intended early mining progression and will not be copied literally.

## Matcha visual identity

The original Matcha Copper equipment has distinct item/armor art and translations that are suitable as visual references.

WTFIM will use self-contained `wtfim:` assets. It will not import legacy carrier/CustomModelData architecture or global vanilla resource overrides.

Legacy oxidized/exposed/weathered Copper tool assets are not evidence of a complete current oxidation mechanic. Oxidation is deferred unless later designed explicitly.

## Overgeared Copper inventory

Overgeared 1.6.19 registers nine finished Copper equipment items:

- `overgeared:copper_sword`
- `overgeared:copper_axe`
- `overgeared:copper_pickaxe`
- `overgeared:copper_shovel`
- `overgeared:copper_hoe`
- `overgeared:copper_helmet`
- `overgeared:copper_chestplate`
- `overgeared:copper_leggings`
- `overgeared:copper_boots`

It also owns Copper manufacturing intermediates and stations, including heated Copper, Copper Nuggets, Copper Plates, forged heads/blades, smithing hammers, blueprints, and related forging recipes.

Overgeared has no Copper Shears.

## Overgeared Copper stats

### Tools

- Durability: **190**
- Mining speed: **5**
- Tier attack bonus: **+1**
- Enchantability: **12**
- Repair ingredient: Copper Ingot

Effective combat attributes:

| Tool | Attack damage | Attack speed |
| --- | ---: | ---: |
| Sword | 5 | 1.6 |
| Axe | 7 | 1.0 |
| Pickaxe | 3 | 1.2 |
| Shovel | 3.5 | 1.0 |
| Hoe | 1 | 2.5 |

### Armor

| Piece | Armor | Base durability |
| --- | ---: | ---: |
| Helmet | 1 | 110 |
| Chestplate | 4 | 160 |
| Leggings | 3 | 150 |
| Boots | 1 | 130 |

Material-wide:

- Enchantability: **15**
- Toughness: **0**
- Knockback resistance: **0**
- Repair ingredient: Copper Ingot

## Overgeared mining progression

Overgeared Copper treats the following as incorrect for drops:

- `#minecraft:needs_iron_tool`
- `#minecraft:needs_diamond_tool`
- `#overgeared:needs_steel_tool`

Overgeared separately makes Iron Ore, Deepslate Iron Ore, Raw Iron Block, and Iron Block part of its Copper-stage mining progression.

WTFIM will align the Copper harvest gate with Overgeared rather than preserve Matcha's more permissive gate.

## Overgeared manufacturing

The source-defined Copper flow is:

```text
Copper acquisition
-> heated Copper
-> blueprint + forging
-> forged head / blade / plate
-> assembly or armor forging
-> finished Copper equipment
```

Tool heads/blades are forged from heated Copper, then assembled with sticks using Overgeared's custom shapeless recipe type. Armor is forged from Copper Plates.

Overgeared's custom assembly logic copies its forging-quality and creator components from forged parts to finished equipment. Its forging recipes can assign quality to recipe outputs without requiring the output namespace to be `overgeared`.

Therefore redirected recipes can produce `wtfim:copper_*` while retaining Overgeared craftsmanship and quality infrastructure. Runtime validation remains required for the final modifier behavior.

## Conflict analysis

| Area | Conflict |
| --- | --- |
| Final item identity | Matcha/WTFIM and Overgeared otherwise produce separate Copper equipment families |
| Acquisition | Matcha direct shaped crafting bypasses Overgeared forging |
| Mining gate | Matcha Copper can harvest more than Overgeared intends |
| Tool stats | Matcha and Overgeared differ in durability, speed, tier bonus, enchantability, Axe/Hoe combat |
| Armor stats | Durability, enchantability, and helmet protection differ |
| Repair | Both use Copper Ingots; no conflict |
| Forging quality | Integration opportunity if WTFIM items are Overgeared recipe outputs |
| Recycling | Matcha returns one ingot; Overgeared uses a much lower nugget return |
| Villager economy | Overgeared can distribute its own finished Copper gear unless compatibility suppresses/redirects it |
| EMI | Duplicate final Copper families would confuse progression |
| Advancements | Matcha does not coherently teach Copper progression |
| Assets | Separate visual identities require one canonical player-facing family |

## Accepted KEEP / CHANGE / DROP

| Feature | Decision |
| --- | --- |
| Copper as a full early equipment tier | KEEP |
| Sword/Axe/Pickaxe/Shovel/Hoe | KEEP as WTFIM items |
| Four armor pieces | KEEP as WTFIM items |
| Copper Shears | KEEP as WTFIM item |
| Matcha core base stats | KEEP, except mining gate |
| Matcha armor stats | KEEP |
| Copper-Ingot repair | KEEP |
| Matcha direct equipment crafting | DROP |
| Matcha one-ingot recycling | CHANGE to Overgeared-aligned economy |
| Matcha mining incorrect tag | CHANGE to Overgeared progression gate |
| Overgeared heating/forging/assembly | KEEP |
| Overgeared forging quality | KEEP for canonical standard WTFIM Copper equipment |
| Copper Compass | DEFER to exploration/utility work |
| Copper Dolabra | DEFER pending special-tool audit |
| Copper Mattock | DEFER pending special-tool audit |
| Dormant Matcha Copper gear loot tables | DROP from the base Copper acquisition design |
| Matcha Copper visual identity | KEEP as visual reference |
| Legacy CMD/carrier behavior | DROP |
| Oxidation mechanic | DEFER |

## Accepted WTFIM architecture

Canonical Copper equipment IDs:

```text
wtfim:copper_sword
wtfim:copper_axe
wtfim:copper_pickaxe
wtfim:copper_shovel
wtfim:copper_hoe
wtfim:copper_shears
wtfim:copper_helmet
wtfim:copper_chestplate
wtfim:copper_leggings
wtfim:copper_boots
```

WTFIM owns:

- canonical final Copper item identities;
- Matcha-derived base stats;
- repair behavior;
- WTFIM models/textures/translations;
- Copper progression teaching;
- later use of these items as progression bases.

Overgeared owns:

- heating;
- blueprints;
- forged parts;
- Copper Plates;
- hammering/forging;
- final equipment manufacture;
- forging quality and creator metadata;
- Copper-stage mining progression boundaries.

Manufacturing target:

```text
Vanilla Copper acquisition
-> Overgeared heating
-> Overgeared forged heads / blades / plates
-> Overgeared assembly / armor forging
-> canonical wtfim:copper_* equipment
```

The five standard tools should remain Overgeared custom-assembly outputs so quality/creator data can transfer from forged parts. The four armor pieces should be direct Overgeared forging outputs using the existing plate costs and forging patterns but returning WTFIM items.

Copper Shears have no exact Overgeared precedent. Accepted design: forge two heated Copper Ingots into `wtfim:copper_shears`, using a small Copper-stage forging operation and no forging-quality component. The initial implementation target is three hammering actions; this is a WTFIM design decision rather than recovered Matcha/Overgeared behavior and must be gameplay-tested.

The Copper mining incorrect-block policy should cover:

- `#minecraft:needs_iron_tool`
- `#minecraft:needs_diamond_tool`
- `#overgeared:needs_steel_tool`

Canonical WTFIM Copper equipment should recycle through the Overgeared Copper economy to one `overgeared:copper_nugget`, not Matcha's one-Ingot return.

## Duplicate Overgeared final-item policy

Overgeared's nine finished Copper IDs remain registered because WTFIM must not attempt registry surgery against another mod.

They are not part of normal WTFIM survival progression.

Compatibility work must:

1. redirect/replace final Overgeared Copper recipes to `wtfim:copper_*`;
2. prevent smithing-profession trades from reintroducing `overgeared:copper_*` as normal final gear;
3. preserve useful Copper heads/blades/plates where appropriate;
4. hide obsolete finished Overgeared Copper gear from EMI where feasible;
5. leave the original registry IDs intact for compatibility.

Failure to suppress a normal survival route to the duplicate Overgeared finished set is a Copper-slice blocker.

## Dependency decision

Starting with the Copper implementation version, Overgeared 1.21.1-1.6.19+ is a required runtime dependency for the intended WTFIM progression.

WTFIM will not add direct fallback crafting merely to remain standalone. A standalone compatibility mode, if ever desired, must be designed separately.

Overgeared Universal is not required for Copper.

## EMI / teaching target

EMI should communicate:

```text
Copper
-> heated Copper
-> forged parts / plates
-> assembly / forging
-> WTFIM Copper equipment
```

It should not advertise direct Matcha-style crafting or the duplicate Overgeared finished Copper family as normal progression.

WTFIM will add a small Copper progression advancement teaching that Copper equipment is forged rather than directly crafted. Shakudo/Hepatizon are not introduced by the Copper advancement.

## Implementation boundary

This audit does **not** authorize implementation of Shakudo, Hepatizon, Steel, Diamond, Electrum, Adamant, Estus, mobs, Sack, or later progression systems.

Copper implementation starts only after this accepted documentation commit.
