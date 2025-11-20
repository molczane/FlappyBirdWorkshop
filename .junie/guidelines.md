
Follow these concise rules to keep the development checklist effective and consistent across contributors.

1. Checklist Basics
   - Mark a task done by changing `[ ]` to `[x]` — do not remove completed tasks.
   - Keep the original phase order intact. If you need a new task, add it under the most relevant phase.
   - Use short, action‑oriented task titles. Add one brief follow‑up line if clarification is needed.

2. Linking Discipline
   - Every task must reference one Plan item and one or more Requirements using IDs:
     - Example: `(Refs: P5, R2, R6)` or with links: `(Refs: [P5](../docs/plan.md#p5), [R2](../docs/requirements.md#r2))`.
   - If you add or modify a task, ensure the references remain accurate.

3. Phases
   - Phases are: Setup & Navigation → Gameplay Core → Visuals & Assets → Platforms & Web Focus → Score & Session → Performance & QA → Polish.
   - Don’t rename phases. If a task spans phases, place it where work starts and mention cross‑phase impact in the description.

4. Consistency & Style
   - Keep formatting identical to existing tasks: enumerated list plus `[ ]` checkbox, followed by references.
   - Prefer relative links to `../docs/plan.md#p{n}` and `../docs/requirements.md#r{n}`.
   - Use American English spelling and present tense ("Implement", "Add").

5. Scope Control
   - Only add tasks that map to approved Requirements and Plan items. If you need new scope, first update `docs/requirements.md` and `docs/plan.md` in a separate change, then add tasks here.
   - Keep Web (Wasm JS) as the primary target when prioritizing or unblocking work.

6. Validation
   - After checking a task, verify the app still builds for the target you touched. For Web use: `./gradlew :composeApp:wasmJsBrowserDevelopmentRun`.
   - For Android/desktop/iOS runs, follow commands from the project README.

7. Reviews & Commits
   - Commit messages should reference the task numbers you changed (e.g., "Tasks: 6–9") and affected IDs (e.g., "P5, R2").
   - If you re-order tasks, keep numbering stable within a phase when possible; otherwise, renumber consistently.
