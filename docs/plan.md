# Implementation Plan

This plan is derived from the Requirements Document and is organized into logical groups. Each plan item has an ID (P#), priority, and explicit links to requirements (R#). The Web (Wasm JS) target is emphasized per the project goal.

Legend: R# → see Requirements at ./requirements.md#r{n}

---

## Group A — Foundation & Navigation

<a id="p1"></a>
#### P1 — Project Setup and App Entry Points (Priority: High)
- Summary: Ensure a clean Compose Multiplatform structure, define app states, and confirm entry points for all targets (Android, iOS, Desktop, Web Wasm JS).
- Key work:
  - Define navigation state machine: Start → Game → Score → Start.
  - Centralize common UI in `commonMain` with platform shims as needed.
  - Reuse README commands for builds and runs.
- Links: [R1](./requirements.md#r1), [R9](./requirements.md#r9)

<a id="p2"></a>
#### P2 — Start Screen (Priority: High)
- Summary: Implement Start screen with a prominent Play button, keyboard focus, and accessibility labels.
- Links: [R1](./requirements.md#r1), [R8](./requirements.md#r8)

<a id="p3"></a>
#### P3 — Score Screen & Session Scores (Priority: High)
- Summary: Implement Score screen that lists scores collected during the current app session and a Play Again button returning to Start.
- Links: [R4](./requirements.md#r4)

---

## Group B — Gameplay Core

<a id="p4"></a>
#### P4 — Render Surface & Coordinate System (Priority: High)
- Summary: Implement a Canvas-based playfield with a fixed logical coordinate space (portrait), auto-scaled to device size with letterboxing.
- Links: [R2](./requirements.md#r2), [R8](./requirements.md#r8)

<a id="p5"></a>
#### P5 — Bird Physics & Input (Priority: High)
- Summary: Gravity, velocity, flap impulse; cross-platform input (tap/click/Space).
- Links: [R2](./requirements.md#r2), [R6](./requirements.md#r6), [R7](./requirements.md#r7)

<a id="p6"></a>
#### P6 — Pipe(s), Scrolling & Collision (Priority: High)
- Summary: Spawn and scroll at least one pipe pair with a gap; AABB collision between bird and pipes/ground; end run on collision or out-of-bounds.
- Links: [R2](./requirements.md#r2), [R3](./requirements.md#r3)

<a id="p7"></a>
#### P7 — Scoring (Priority: High)
- Summary: Increment score when bird successfully passes a pipe gap; display in-run HUD.
- Links: [R3](./requirements.md#r3)

---

## Group C — Visuals & Assets

<a id="p8"></a>
#### P8 — Visual Fidelity & Scaling (Priority: Medium)
- Summary: Create IP-safe bird and pipe sprites that closely match the classic look; implement density-aware scaling for crisp rendering.
- Links: [R5](./requirements.md#r5), [R8](./requirements.md#r8)

<a id="p9"></a>
#### P9 — Frame Pacing & Performance (Priority: Medium)
- Summary: Use a game loop with time-step integration; aim for 60 FPS with graceful degradation to 30 FPS where necessary.
- Links: [R7](./requirements.md#r7)

---

## Group D — Platforms & Web Focus

<a id="p10"></a>
#### P10 — Platform Builds & Entrypoints (Priority: High)
- Summary: Ensure build/run on all targets using tasks from README: Android, Desktop, Web (Wasm JS), iOS. Provide unsupported-browser message for Web where needed.
- Links: [R9](./requirements.md#r9), [R10](./requirements.md#r10)

---

## Group E — Quality

<a id="p11"></a>
#### P11 — Minimal Tests & Smoke Checks (Priority: Medium)
- Summary: Add a lightweight unit test for physics step or utility math; ensure at least one target compiles by default in CI/local.
- Links: [R11](./requirements.md#r11)

---

## Coverage Matrix (Plan → Requirements)
- P1 → R1, R9
- P2 → R1, R8
- P3 → R4
- P4 → R2, R8
- P5 → R2, R6, R7
- P6 → R2, R3
- P7 → R3
- P8 → R5, R8
- P9 → R7
- P10 → R9, R10
- P11 → R11
