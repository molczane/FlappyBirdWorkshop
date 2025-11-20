# Development Tasks Checklist

Use this checklist to track implementation progress. Each task is linked to a plan item (P#) and related requirement(s) (R#). Mark tasks as `[x]` when completed.

Key links: Plan → ./plan.md#p{n} | Requirements → ./requirements.md#r{n}

---

## Phase 1 — Setup & Navigation

1. [ ] Confirm project builds and runs on Web (Wasm JS) dev server using README command (`:composeApp:wasmJsBrowserDevelopmentRun`). (Refs: [P10](./plan.md#p10), [R9](./requirements.md#r9))
2. [x] Define `Screen` navigation state (e.g., sealed class: Start, Game, Score) in `commonMain`. (Refs: [P1](./plan.md#p1), [R1](./requirements.md#r1))
3. [x] Implement root `App()` state machine to switch between screens. (Refs: [P1](./plan.md#p1), [R1](./requirements.md#r1))
4. [x] Implement Start screen UI with centered Play button and keyboard focus/Enter/Space activation. (Refs: [P2](./plan.md#p2), [R1](./requirements.md#r1), [R8](./requirements.md#r8))
5. [x] Wire Play button to transition to Game screen. (Refs: [P2](./plan.md#p2), [R1](./requirements.md#r1))

---

## Phase 2 — Gameplay Core

6. [x] Create Canvas-based playfield with fixed logical size (portrait), auto-scaled with letterboxing. (Refs: [P4](./plan.md#p4), [R2](./requirements.md#r2), [R8](./requirements.md#r8))
7. [x] Implement bird physics model: position, velocity, gravity, flap impulse. (Refs: [P5](./plan.md#p5), [R2](./requirements.md#r2), [R6](./requirements.md#r6), [R7](./requirements.md#r7))
8. [x] Implement input handlers: tap/click and Space to flap; debounce as needed. (Refs: [P5](./plan.md#p5), [R6](./requirements.md#r6))
9. [x] Implement single pipe pair with vertical gap; horizontal scrolling across screen. (Refs: [P6](./plan.md#p6), [R2](./requirements.md#r2))
10. [x] Implement collision detection (AABB) for bird vs pipes and ground/off-screen. (Refs: [P6](./plan.md#p6), [R2](./requirements.md#r2))
11. [x] End run on collision/out-of-bounds and navigate to Score screen. (Refs: [P6](./plan.md#p6), [R2](./requirements.md#r2), [R4](./requirements.md#r4))
12. [x] Implement in-run score increment when bird passes a pipe gap; render HUD. (Refs: [P7](./plan.md#p7), [R3](./requirements.md#r3))

---

## Phase 3 — Visuals & Assets

13. [ ] Create IP-safe bird and pipe sprites approximating classic look (not copied). (Refs: [P8](./plan.md#p8), [R5](./requirements.md#r5))
14. [ ] Integrate sprites into render loop; implement density-aware scaling for crisp results. (Refs: [P8](./plan.md#p8), [R5](./requirements.md#r5), [R8](./requirements.md#r8))
15. [ ] Verify visuals across common device sizes and pixel densities. (Refs: [P8](./plan.md#p8), [R8](./requirements.md#r8))

---

## Phase 4 — Platforms & Web Focus

16. [ ] Android: assemble and run debug build per README. (Refs: [P10](./plan.md#p10), [R9](./requirements.md#r9))
17. [ ] Desktop (JVM): run app per README. (Refs: [P10](./plan.md#p10), [R9](./requirements.md#r9))
18. [ ] Web (Wasm JS): run dev server; test in modern browsers; add unsupported-browser message if Wasm not available. (Refs: [P10](./plan.md#p10), [R9](./requirements.md#r9), [R10](./requirements.md#r10))
19. [ ] iOS: open `iosApp` in Xcode or run via run configuration; verify start→game→score loop. (Refs: [P10](./plan.md#p10), [R9](./requirements.md#r9))

---

## Phase 5 — Score Screen & Session Management

20. [ ] Maintain in-memory list of run scores for current session; latest first. (Refs: [P3](./plan.md#p3), [R4](./requirements.md#r4))
21. [ ] Implement Score screen UI showing recent session scores and Play Again button to return to Start. (Refs: [P3](./plan.md#p3), [R4](./requirements.md#r4))
22. [ ] Ensure session scores reset on app restart (no persistence). (Refs: [P3](./plan.md#p3), [R4](./requirements.md#r4))

---

## Phase 6 — Performance & QA

23. [ ] Implement fixed/variable timestep integration for smooth motion; target 60 FPS, allow degrade to 30. (Refs: [P9](./plan.md#p9), [R7](./requirements.md#r7))
24. [ ] Add minimal unit test(s) for physics step or helper math in `commonTest`. (Refs: [P11](./plan.md#p11), [R11](./requirements.md#r11))
25. [ ] Ensure default Gradle task compiles at least one target; document run instructions in README if needed. (Refs: [P11](./plan.md#p11), [R11](./requirements.md#r11))

---

## Phase 7 — Polish

26. [ ] Accessibility pass: button labels, focus order, color contrast check. (Refs: [P2](./plan.md#p2), [R1](./requirements.md#r1))
27. [ ] Input extras: Escape handling on desktop/web (optional per R6). (Refs: [P5](./plan.md#p5), [R6](./requirements.md#r6))
28. [ ] Cross-device visual QA; adjust scaling constants as needed. (Refs: [P8](./plan.md#p8), [R8](./requirements.md#r8))
