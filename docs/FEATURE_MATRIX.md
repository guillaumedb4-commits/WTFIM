# WTFIM — Feature Matrix

The audited Matcha feature matrix remains the source reference for later KEEP / CHANGE / DROP work. A snapshot of the existing WTFIM Feature Matrix is retained at `docs/reference/WTFIM_Feature_Matrix.xlsx`; supporting static audits are retained under `docs/reference/audit/`.

| Feature | Decision | Status / notes |
| --- | --- | --- |
| Clean mod architecture | KEEP | Implemented and validated as Foundation `0.1.0-alpha`. |
| Temporary smoke-test item | DROP AFTER FOUNDATION | `wtfim:test_item` retired after the real Copper family passed registration, resource, persistence, EMI, manufacturing, and quality smoke coverage. Removal was DEV runtime-validated on 2026-10-08 with no Copper regression. |
| Copper standard equipment family | KEEP / CHANGE | Ten canonical `wtfim:copper_*` items are registered and DEV presentation-validated. Standard Overgeared manufacturing redirects for five tools + four armor pieces and dedicated Copper Shears forging are DEV runtime-validated. Base armor protection 2/4/3/1, zero toughness/knockback resistance, and absence of fire resistance are DEV runtime-validated. Tool mining speed 6.0, tool enchantability 13, and armor enchantability 8 are confirmed in the built JAR bytecode. Finished-Copper villager trade suppression, Copper recycling, and EMI cleanup are DEV runtime-validated; progression teaching remains incomplete. |
| Copper direct shaped equipment recipes | DROP | Not implemented; would bypass Overgeared craftsmanship. |
| Copper mining gate | CHANGE | `wtfim:incorrect_for_copper_tool` includes vanilla Iron/Diamond requirements plus optional `#overgeared:needs_steel_tool`. DEV runtime validation confirms Iron Ore remains harvestable while Diamond Ore and Obsidian do not drop correctly, and Poor/Well/Expert/Perfect/Master qualities do not bypass the gate. |
| Copper recycling | CHANGE | Overgeared-aligned one-Copper-Nugget recycling is DEV runtime-validated for all ten canonical Copper items in both furnace and blast furnace; broader economy-loop duplication audit remains. |
| Copper forging quality | KEEP FROM OVERGEARED | Tool and armor quality propagation/modifiers on canonical WTFIM Copper equipment are DEV runtime-validated, including persistence after save/reload. Creator transfer and expected polishing/quenching preservation behavior are also DEV runtime-validated. |
| Duplicate Overgeared final Copper gear | DROP FROM NORMAL PROGRESSION | Registry IDs remain. Standard final recipes and smithing-profession trades are suppressed/redirected as designed. A focused DEV audit on 2026-10-08 found no active production recipe for any of the nine finished `overgeared:copper_*` items, while useful Copper blade/head/plate routes remained available; no external DEV datapack was enabled to restore a duplicate route. Recipe-viewer hiding through `c:hidden_from_recipe_viewers` is DEV runtime-validated: the nine obsolete finished IDs are absent from EMI while useful intermediates and canonical WTFIM Copper remain visible. Creative visibility is intentionally unchanged. |
| Copper Shears | KEEP / CHANGE | `wtfim:copper_shears` registered with 300 durability/Copper-Ingot repair. Dedicated two-heated-ingot Overgeared forging path is DEV runtime-validated: the Shears and Copper Hammer Head recipes resolve independently, Copper Shears receive no forging quality, and the three-hammering balance target was accepted in gameplay testing on 2026-10-08. |
| Copper Compass | DEFER | Valid Matcha exploration utility, but outside the core equipment slice. |
| Copper Dolabra | DEFER | Registered in Matcha but no normal v19 acquisition; audit separately with special tools. |
| Copper Mattock | DEFER | Registered in Matcha but no normal v19 acquisition; audit separately with special tools. |
| Copper oxidation variants/mechanic | DEFER | Asset residue is not sufficient evidence of a complete current mechanic. |
| Shakudo / Hepatizon | DEFER | Later slices; Copper only establishes their future canonical base equipment IDs. |
| Steel / Diamond / advanced alloys | DEFER | Existing accepted direction remains documented in `DESIGN_DECISIONS.md` and `PROGRESSION.md`; no implementation in Copper. |
| Legacy carrier migration | DEFER | Not needed for Copper. |
| Matcha datapack/resource-pack total conversion | DROP AS ARCHITECTURE | Original files remain references only; features are rebuilt explicitly. |

## Copper decision source

See:

- `docs/COPPER_AUDIT.md`
- `docs/DESIGN_DECISIONS.md` ADR-003
- `docs/OVERGEARED_COMPAT.md`

Copper implementation is intentionally proceeding in small commits. Registration/presentation, standard manufacturing, Copper Shears forging behavior, and finished-Copper villager offer suppression are DEV runtime-validated.
