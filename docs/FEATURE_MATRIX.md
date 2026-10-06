# WTFIM — Feature Matrix

The audited Matcha feature matrix remains the source reference for later KEEP / CHANGE / DROP work. A snapshot of the existing WTFIM Feature Matrix is retained at `docs/reference/WTFIM_Feature_Matrix.xlsx`; supporting static audits are retained under `docs/reference/audit/`.

| Feature | Decision | Status / notes |
| --- | --- | --- |
| Clean mod architecture | KEEP | Implemented and validated as Foundation `0.1.0-alpha`. |
| Temporary smoke-test item | KEEP TEMPORARILY | `wtfim:test_item`; retain until the real Copper items pass runtime/resource validation. |
| Copper standard equipment family | KEEP / CHANGE | Ten canonical `wtfim:copper_*` items are registered and DEV presentation-validated. Standard Overgeared manufacturing redirects for five tools + four armor pieces are DEV-validated; Copper Shears forging is implemented in source pending runtime validation. Trade suppression, EMI cleanup, recycling, and progression teaching remain incomplete. |
| Copper direct shaped equipment recipes | DROP | Not implemented; would bypass Overgeared craftsmanship. |
| Copper mining gate | CHANGE | Copper tier now points at `wtfim:incorrect_for_copper_tool`, containing vanilla Iron/Diamond requirements plus optional `#overgeared:needs_steel_tool`. Runtime validation remains. |
| Copper recycling | CHANGE | Planned Overgeared-aligned one-Copper-Nugget return; not implemented yet. |
| Copper forging quality | KEEP FROM OVERGEARED | Standard recipes now retain Overgeared `crafting_shapeless` / `forging` serializers; runtime quality/creator propagation remains unvalidated. |
| Duplicate Overgeared final Copper gear | DROP FROM NORMAL PROGRESSION | Registry IDs remain. The nine standard final recipes are redirected and DEV-validated. Old Overgeared finished items are still visible in Creative/EMI; villager-trade suppression and visibility cleanup remain pending. |
| Copper Shears | KEEP / CHANGE | `wtfim:copper_shears` registered with 300 durability/Copper-Ingot repair. Dedicated two-heated-ingot, three-hammer Overgeared forging recipe implemented in source; runtime validation pending. |
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

Copper implementation is intentionally proceeding in small commits. Registration/presentation and standard manufacturing have been validated; Copper Shears forging is the current source boundary.
