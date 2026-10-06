# WTFIM — Item Registry

## Foundation registry

| ID | Status | Purpose | Removal condition |
| --- | --- | --- | --- |
| `wtfim:test_item` | Temporary | Proves item registration, language, model, texture, `/give`, persistence, and recipe-viewer indexing | Remove after the real Copper registrations pass runtime/resource validation and provide equivalent smoke coverage |

## Copper — canonical registry

The first Copper implementation commit defines these ten canonical final equipment IDs in source. DEV registration validation passed on 2026-10-06. Matcha-derived WTFIM-namespaced models, item textures, armor layers, English names, and standard vanilla equipment/enchantable tags are now present in source; runtime resource validation remains pending.

| ID | Status | Role |
| --- | --- | --- |
| `wtfim:copper_sword` | Registered / DEV presentation validated | Standard Copper weapon |
| `wtfim:copper_axe` | Registered / DEV presentation validated | Copper axe |
| `wtfim:copper_pickaxe` | Registered / DEV presentation validated | Copper pickaxe |
| `wtfim:copper_shovel` | Registered / DEV presentation validated | Copper shovel |
| `wtfim:copper_hoe` | Registered / DEV presentation validated | Copper hoe |
| `wtfim:copper_shears` | Registered / DEV presentation validated | Repairable Copper shears |
| `wtfim:copper_helmet` | Registered / DEV presentation validated | Copper helmet |
| `wtfim:copper_chestplate` | Registered / DEV presentation validated | Copper chestplate |
| `wtfim:copper_leggings` | Registered / DEV presentation validated | Copper leggings |
| `wtfim:copper_boots` | Registered / DEV presentation validated | Copper boots |

Supporting source now defines:

- a Copper `SimpleTier` with 350 durability, 6.0 mining speed, +2 tier attack bonus, enchantability 13, and Copper-Ingot repair;
- a registered Copper armor material with 2/4/3/1 protection, enchantability 8, zero toughness/knockback resistance, and Copper-Ingot repair;
- a Copper Shears subclass with 300 durability and explicit Copper-Ingot repair;
- the accepted Copper incorrect-block tag boundary.

Current Copper work still does not add acquisition/manufacturing recipes, Overgeared recipe redirects, trades, EMI hiding, or advancement teaching. Copper visual resources are self-contained under `assets/wtfim`; no legacy global `minecraft:` overrides are used.

### Explicitly deferred Copper concepts

| Matcha reference ID | WTFIM status | Reason |
| --- | --- | --- |
| `matcha:copper_compass` | DEFER | Separate exploration utility; not required for equipment progression |
| `matcha:copper_dolabra` | DEFER | Real Matcha item but no normal v19 crafting path; audit with special tools |
| `matcha:copper_mattock` | DEFER | Real Matcha item but no normal v19 crafting path; audit with special tools |

No carrier/CMD IDs are introduced for Copper.

Overgeared's existing `overgeared:copper_*` finished items remain registered by Overgeared but are not canonical WTFIM progression items.
