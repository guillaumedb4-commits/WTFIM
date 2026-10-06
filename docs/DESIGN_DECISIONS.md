# WTFIM — Design Decisions

## ADR-001 — Clean WTFIM Foundation

**Status:** Accepted

### Decision

WTFIM is rebuilt as a clean NeoForge mod rather than extending the old Unified JAR or importing the old Matcha datapack/resource-pack architecture wholesale.

Foundation uses a conventional NeoForge structure with a single mod entrypoint, DeferredRegister-based registries, self-contained assets, explicit metadata, and permanent documentation.

### Reason

The previous combined architecture changed Java registrations, carrier migration, datapack replacements, assets, progression, and compatibility behavior at the same time. That made failures difficult to isolate and made the project harder to reason about.

### Consequences

- `0.1.0-alpha` contains no gameplay progression.
- `wtfim:test_item` is temporary and exists only to validate registry/resource/save behavior.
- Matcha systems are rebuilt one vertical slice at a time after Foundation passes validation.
- Old carrier IDs are not introduced as a default implementation strategy.
- No stable Foundation tag is created until runtime checks pass.

## Accepted project direction carried into later versions

These decisions are recorded here as context only; Foundation does not implement them:

- Matcha Copper will be the canonical Copper equipment family.
- Matcha Steel will be the canonical Steel equipment family.
- Iron, Gold, and Diamond manufacturing authority belongs to Overgeared.
- Shakudo and Hepatizon will be real WTFIM alloys branching from Copper.
- Electrum will be a real WTFIM alloy branching from Diamond.
- Vanilla Netherite IDs become Adamant.
- Overgeared's Diamond Upgrade Smithing Template is used for Steel -> Diamond.
- Matcha defines progression; Overgeared defines craftsmanship.

## ADR-002 — Foundation validation baseline

**Status:** Accepted — 2026-10-06

### Decision

WTFIM `0.1.0-alpha` Foundation is considered validated after passing the Gradle build, clean DEV runtime, save/reload, EMI indexing, Overgeared coexistence, and the real modpack integration boot/test.

### Evidence boundary

Known Overgeared 1.6.19 recipe/EMI log errors are treated as external baseline noise because they reproduce independently of WTFIM and are documented in `TEST_MATRIX.md` / `OVERGEARED_COMPAT.md`. WTFIM Foundation does not patch them.

### Consequence

`v0.1.0-alpha` is the baseline tag. Gameplay work starts after this point in vertical slices, beginning with Copper; Foundation-only behavior should remain reproducible from the tag.
