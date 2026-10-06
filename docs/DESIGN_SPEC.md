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

## Copper vertical slice — accepted design

Copper is the first gameplay vertical slice after Foundation.

### Responsibility boundary

WTFIM defines **what Copper equipment is**:

- canonical final IDs;
- Matcha-derived base stats;
- repair behavior;
- models/textures/translations;
- progression teaching;
- later progression references.

Overgeared defines **how standard Copper equipment is manufactured**:

- heating;
- blueprints;
- forged heads/blades;
- Copper Plates;
- forging;
- tool assembly;
- forging quality and creator metadata.

### Canonical Copper family

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

Copper Compass, Copper Dolabra, and Copper Mattock are not part of this initial equipment slice.

### Base tool tier

- durability: 350
- mining speed: 6.0
- tier attack bonus: +2
- enchantability: 13
- repair: `minecraft:copper_ingot`

Combat targets:

| Tool | Damage | Speed |
| --- | ---: | ---: |
| Sword | 5 | 1.6 |
| Axe | 9 | 0.8 |
| Pickaxe | 3 | 1.2 |
| Shovel | 3.5 | 1.0 |
| Hoe | 1 | 2.0 |

Copper Shears target 300 durability and Copper-Ingot repair.

### Armor target

| Piece | Armor | Durability |
| --- | ---: | ---: |
| Helmet | 2 | 200 |
| Chestplate | 4 | 200 |
| Leggings | 3 | 200 |
| Boots | 1 | 200 |

Armor material:

- enchantability: 8
- toughness: 0
- knockback resistance: 0
- repair: `minecraft:copper_ingot`

### Mining gate

Copper harvest gating follows Overgeared progression and must treat these as incorrect for Copper:

- `#minecraft:needs_iron_tool`
- `#minecraft:needs_diamond_tool`
- `#overgeared:needs_steel_tool`

### Manufacturing target

```text
Vanilla Copper acquisition
-> Overgeared heating
-> forged Copper head/blade/plate
-> Overgeared assembly / armor forging
-> wtfim:copper_* final equipment
```

No Matcha-style direct shaped Copper-equipment recipes are allowed.

Standard tools should retain Overgeared's custom assembly recipe type so quality and creator data can transfer from forged parts. Armor should use redirected Overgeared forging recipes.

Copper Shears are forged from two heated Copper Ingots. Initial design target: three hammering actions, no forging quality.

### Duplicate-family rule

Overgeared's finished Copper IDs remain registered but must not form a second normal survival path. Final recipes, smithing-profession trade leakage, and EMI presentation must be controlled accordingly.

### Recycling

Canonical WTFIM Copper equipment recycles to one `overgeared:copper_nugget`.

### Dependency

From the Copper implementation version onward, Overgeared 1.21.1-1.6.19+ is required for intended progression. No easy fallback crafting path is added solely for standalone operation.

See `DESIGN_DECISIONS.md` ADR-003 and `COPPER_AUDIT.md`.
