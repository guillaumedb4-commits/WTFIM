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

Copper is now fully designed at architecture level but not yet implemented.

### Canonical graph

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

Matcha-style direct shaped Copper equipment crafting is removed from the WTFIM design.

### Progression boundary

Copper's harvest gate follows Overgeared:

- blocks requiring Iron are incorrect for Copper;
- blocks requiring Diamond are incorrect for Copper;
- blocks requiring Overgeared Steel are incorrect for Copper.

This preserves Copper as an early tier without allowing Matcha's more permissive mining behavior to bypass Overgeared's intended progression.

### Quality

The standard five tools and four armor pieces are manufactured through Overgeared recipe types so forging quality/creator data can survive into canonical WTFIM outputs.

Copper Shears are the exception: forged through Overgeared infrastructure but intentionally not quality-bearing.

### Future branches

Shakudo and Hepatizon will later use the canonical WTFIM Copper equipment as their bases.

No Shakudo/Hepatizon recipes, items, tags, or compatibility stubs are implemented as part of the Copper audit/documentation commit.

See `COPPER_AUDIT.md` and ADR-003.
