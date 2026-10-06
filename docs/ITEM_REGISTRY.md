# WTFIM — Item Registry

## Foundation registry

| ID | Status | Purpose | Removal condition |
| --- | --- | --- | --- |
| `wtfim:test_item` | Temporary | Proves item registration, language, model, texture, `/give`, persistence, and recipe-viewer indexing | Remove when the first real Copper registrations provide equivalent smoke coverage and Copper validation passes |

## Copper — accepted registry plan

The Copper audit accepts these ten canonical final equipment IDs for implementation:

| ID | Status | Role |
| --- | --- | --- |
| `wtfim:copper_sword` | Accepted / not yet implemented | Standard Copper weapon |
| `wtfim:copper_axe` | Accepted / not yet implemented | Copper axe |
| `wtfim:copper_pickaxe` | Accepted / not yet implemented | Copper pickaxe |
| `wtfim:copper_shovel` | Accepted / not yet implemented | Copper shovel |
| `wtfim:copper_hoe` | Accepted / not yet implemented | Copper hoe |
| `wtfim:copper_shears` | Accepted / not yet implemented | Repairable Copper shears |
| `wtfim:copper_helmet` | Accepted / not yet implemented | Copper helmet |
| `wtfim:copper_chestplate` | Accepted / not yet implemented | Copper chestplate |
| `wtfim:copper_leggings` | Accepted / not yet implemented | Copper leggings |
| `wtfim:copper_boots` | Accepted / not yet implemented | Copper boots |

### Explicitly deferred Copper concepts

| Matcha reference ID | WTFIM status | Reason |
| --- | --- | --- |
| `matcha:copper_compass` | DEFER | Separate exploration utility; not required for equipment progression |
| `matcha:copper_dolabra` | DEFER | Real Matcha item but no normal v19 crafting path; audit with special tools |
| `matcha:copper_mattock` | DEFER | Real Matcha item but no normal v19 crafting path; audit with special tools |

No carrier/CMD IDs are introduced for Copper.

Overgeared's existing `overgeared:copper_*` finished items remain registered by Overgeared but are not canonical WTFIM progression items.
