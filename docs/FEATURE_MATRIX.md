# WTFIM — Feature Matrix

The audited Matcha feature matrix remains the source reference for later KEEP / CHANGE / DROP work. Foundation intentionally implements no Matcha gameplay feature. A snapshot of the existing WTFIM Feature Matrix is retained at `docs/reference/WTFIM_Feature_Matrix.xlsx`; supporting static audits are retained under `docs/reference/audit/`.

| Feature | 0.1.0-alpha decision | Notes |
| --- | --- | --- |
| Clean mod architecture | KEEP | Implemented as Foundation. |
| Temporary smoke-test item | KEEP TEMPORARILY | `wtfim:test_item`; delete after Foundation validation. |
| Copper gameplay | DEFER | First real content slice; not implemented here. |
| Steel / Diamond / advanced alloys | DEFER | Existing accepted direction is documented in `DESIGN_DECISIONS.md` and `PROGRESSION.md`; no code yet. |
| Legacy carrier migration | DEFER | Not part of Foundation. |
| Matcha datapack/resource-pack total conversion | DROP AS ARCHITECTURE | Original files remain references only; future features are rebuilt explicitly. |
