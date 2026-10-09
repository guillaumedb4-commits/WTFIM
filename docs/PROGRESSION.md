# WTFIM — Progression

## Accepted high-level direction

```text
Copper
  -> Overgeared manufacturing
  -> canonical WTFIM Copper equipment
  -> Shakudo OR Hepatizon

Iron
  -> canonical WTFIM Steel
  -> Overgeared manufacturing
  -> Diamond via Overgeared Diamond Upgrade Smithing Template
  -> Electrum OR Adamant
```

Additional accepted rules:

- Matcha defines progression; Overgeared defines craftsmanship.
- Iron / Gold / Diamond manufacturing authority belongs to Overgeared.
- Shakudo and Hepatizon are real WTFIM alloys, not vanilla carrier IDs.
- Electrum is a real WTFIM alloy, not Heart of the Sea.
- Adamant uses vanilla Netherite IDs as the canonical identity.

## Copper vertical slice

Copper implementation is complete for the accepted vertical-slice scope and passed both WTFIM-DEV and real-modpack WTFIM-INTEGRATION validation on 2026-10-09.

The canonical ten-item Copper equipment family, Copper tool tier, Copper armor material, repairable Copper Shears, and Copper incorrect-block tag are now defined in source.

Standard manufacturing redirection is DEV-validated for the five standard tools and four armor pieces. Copper Shears use their own Overgeared forging path: two heated Copper Ingots, Stone tier, three hammering actions, no quenching/polishing, no blueprint. DEV runtime validation confirms the Shears and Copper Hammer Head patterns resolve independently and Shears receive no forging quality. The three-hammering balance target was accepted in gameplay testing on 2026-10-08. No direct shaped fallback recipe has been added.

### Canonical target graph

```text
Vanilla Copper acquisition
  -> Overgeared heating
  -> Overgeared blueprints + forged heads/blades/plates
  -> Overgeared assembly / armor forging
  -> wtfim:copper_* equipment
```

The standard canonical family is:

- Sword
- Axe
- Pickaxe
- Shovel
- Hoe
- Shears
- Helmet
- Chestplate
- Leggings
- Boots

Matcha-style direct shaped Copper equipment crafting remains excluded from the WTFIM design.

### Progression boundary

The Copper tier's incorrect-block tag is defined to include:

- `#minecraft:needs_iron_tool`;
- `#minecraft:needs_diamond_tool`;
- optional `#overgeared:needs_steel_tool`.

DEV runtime validation on 2026-10-07 confirmed the intended boundary: Iron Ore remains harvestable, Diamond Ore and Obsidian do not drop correctly, and Poor/Well/Expert/Perfect/Master forging quality never upgrades the Copper harvest tier. Exact Overgeared 1.6.19 audit shows its Steel-tool tag contains Obsidian and Crying Obsidian; these overlap the vanilla higher-tier gate, so the Overgeared tag's independent causal effect cannot be isolated in runtime, but its membership and WTFIM inclusion are statically confirmed.

### Recycling

Copper recycling follows the accepted Overgeared economy rather than Matcha's one-ingot return. Every canonical `wtfim:copper_*` equipment item recycles to one `overgeared:copper_nugget` through both smelting and blasting. The nine standard items reuse/override Overgeared's existing recycling recipe IDs; Copper Shears use equivalent WTFIM recipes. DEV runtime validation on 2026-10-07 confirmed all ten items work in both furnace types and no one-Ingot Matcha-style recycling path remains. A focused material-economy loop test on 2026-10-09 confirmed Copper Nugget conversions are not profitable, the cheapest canonical Copper equipment costs more material than the fixed one-nugget return, and a complete forge -> recycle cycle loses Copper rather than duplicating it.

### Quality

The standard manufacturing redirects retain Overgeared's custom `crafting_shapeless` and `forging` recipe types specifically so quality and creator data can reach canonical WTFIM outputs. DEV runtime validation on 2026-10-07 confirmed that tool quality propagates onto canonical WTFIM Copper tools, applies the expected quality-derived durability/mining/combat modifiers, and survives save/reload while retaining the `wtfim:` item identity. Armor quality is also DEV runtime-validated: quality and its modifiers persist after save/reload on canonical WTFIM Copper armor while preserving the `wtfim:` item identity. Creator transfer is also DEV runtime-validated: the `overgeared:creator` component persisted from forged Copper Pickaxe Head to final `wtfim:copper_pickaxe`. The runtime pass also confirmed Overgeared's expected preparation semantics: an unpolished head can lose quality on assembly, while a properly polished and cooled/quench-ready Well head remains Well on the final WTFIM tool.

Copper Shears are the exception: they are forged through Overgeared infrastructure but intentionally not quality-bearing. DEV runtime validation on 2026-10-07 confirmed no forging quality is attached.

### Progression teaching

The accepted Copper tutorial advancement is `wtfim:copper/first_forged_equipment`. It is parented to `overgeared:making_anvil` and completes when the player obtains any one of the ten canonical `wtfim:copper_*` equipment items. The trigger uses vanilla `minecraft:inventory_changed`; this deliberately teaches acquisition of canonical forged Copper without adding a custom WTFIM↔Overgeared trigger. The advancement title is `Forged in Copper`, it grants no gameplay reward, and it does not introduce Shakudo/Hepatizon. DEV runtime validation on 2026-10-08 confirmed the advancement loads correctly, both Copper Pickaxe and Copper Shears can independently complete it, and the accepted text renders correctly.

### Future branches

Shakudo and Hepatizon will later use the canonical WTFIM Copper equipment as their bases.

No Shakudo/Hepatizon recipes, items, tags, or compatibility stubs are implemented as part of Copper registration.

See `COPPER_AUDIT.md` and ADR-003.
