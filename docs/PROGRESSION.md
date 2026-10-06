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

Copper implementation has begun.

The canonical ten-item Copper equipment family, Copper tool tier, Copper armor material, repairable Copper Shears, and Copper incorrect-block tag are now defined in source.

Standard manufacturing redirection is DEV-validated for the five standard tools and four armor pieces. Copper Shears now have their own source implementation using Overgeared forging: two heated Copper Ingots, Stone tier, three hammering actions, no quenching/polishing, no blueprint. Runtime Shears-forging validation is pending. No direct shaped fallback recipe has been added.

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

Runtime verification is still required before this is considered validated progression behavior.

### Quality

The standard manufacturing redirects retain Overgeared's custom `crafting_shapeless` and `forging` recipe types specifically so quality and creator data can reach canonical WTFIM outputs. Runtime propagation is not yet marked validated.

Copper Shears are the planned exception: forged through Overgeared infrastructure but intentionally not quality-bearing. Their dedicated forging recipe is now implemented in source; runtime must confirm no quality component is attached.

### Future branches

Shakudo and Hepatizon will later use the canonical WTFIM Copper equipment as their bases.

No Shakudo/Hepatizon recipes, items, tags, or compatibility stubs are implemented as part of Copper registration.

See `COPPER_AUDIT.md` and ADR-003.
