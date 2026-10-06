# WTFIM — Feature Matrix

The audited Matcha feature matrix remains the source reference for later KEEP / CHANGE / DROP work. A snapshot of the existing WTFIM Feature Matrix is retained at `docs/reference/WTFIM_Feature_Matrix.xlsx`; supporting static audits are retained under `docs/reference/audit/`.

| Feature | Decision | Status / notes |
| --- | --- | --- |
| Clean mod architecture | KEEP | Implemented and validated as Foundation `0.1.0-alpha`. |
| Temporary smoke-test item | KEEP TEMPORARILY | `wtfim:test_item`; remove when Copper implementation replaces the need for the smoke item. |
| Copper standard equipment family | KEEP / CHANGE | Accepted for the Copper slice as ten canonical `wtfim:copper_*` items. Matcha identity/stats are preserved; manufacturing is redesigned around Overgeared. |
| Copper direct shaped equipment recipes | DROP | Would bypass Overgeared craftsmanship. |
| Copper mining gate | CHANGE | Use Overgeared Copper progression boundaries rather than Matcha's permissive incorrect-block tag. |
| Copper recycling | CHANGE | Use Overgeared-aligned one-Copper-Nugget return rather than Matcha's one-Ingot return. |
| Copper forging quality | KEEP FROM OVERGEARED | Standard WTFIM Copper tools/armor must be produced through Overgeared recipe types and preserve quality/creator data where applicable. |
| Duplicate Overgeared final Copper gear | DROP FROM NORMAL PROGRESSION | Registry IDs remain, but recipes/trades/EMI must not expose a parallel normal survival family. |
| Copper Shears | KEEP / CHANGE | Canonical `wtfim:copper_shears`; forged through Overgeared infrastructure, no quality component. |
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

The Copper KEEP / CHANGE / DROP decisions are accepted. Implementation is not yet present in this documentation-only commit.
