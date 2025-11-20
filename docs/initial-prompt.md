# Spec-Driven Development Prompt

Transform the provided high-level requirements into a complete set of project planning artifacts for spec-driven development.

## Instructions:

You must produce **four files** inside the `docs/` and `.junie/` directories:

- `docs/requirements.md`
- `docs/plan.md`
- `docs/tasks.md`
- `.junie/guidelines.md`

Follow the methodology below step by step:
  
---  

### Step 1: Create `docs/requirements.md`

- Title: **Requirements Document**
- Introduction: Summarize the application purpose and key functionality.
- Requirements section:

    - Use sequential numbering (1, 2, 3, …).
    - Each requirement must include:  
      - **User Story** in the format:

            > As a user, I want [goal] so that [benefit/reason]  

          - **Acceptance Criteria** in the format:  

            > WHEN [condition] THEN the system SHALL [expected behavior]  

- Guidelines:

    - Focus on user goals and benefits.
    - Make acceptance criteria specific, testable, and precise.
    - Cover normal flows, edge cases, error handling, persistence, and UI/UX.
    - Group related requirements logically.

---  

### Step 2: Create `docs/plan.md`

- Analyze `docs/requirements.md`.
- Develop a **detailed implementation plan**:
    - Link each plan item explicitly to the corresponding requirements.
    - Assign priorities (e.g., High, Medium, Low).
    - Group related plan items logically.
- Ensure comprehensive coverage of all requirements.

---  

### Step 3: Create `docs/tasks.md`

- Based on the implementation plan in `docs/plan.md`, produce a **detailed enumerated technical task list**:

    - Each task must have a placeholder `[ ]` to mark completion.
    - Link each task both to:
        - the development plan item in `docs/plan.md`
            - the related requirement(s) in `docs/requirements.md`
- Group tasks into **development phases**.
- Organize phases logically (e.g., Setup → Core Features → Advanced Features → Testing & QA).

---  

### Step 4: Update `.junie/guidelines.md`

- Add **concise technical instructions** on how to work with the `docs/tasks.md` checklist.
- Instructions should include:
    - Mark tasks as `[x]` when completed.
    - Keep phases intact but add new tasks if needed.
    - Ensure every new or modified task stays linked to a requirement and plan item.
    - Keep formatting consistent with the existing style.

---  

## Input:~~~~

My goal is to create a flappy bird app with Compose Multiplatform Technology. My goal is to be able to run it on iOS, Android, Desktop and Web (wasmJs), with the main focus on running it on the Web (wasmJs) target, because I want to show it to my friends. App is intended to be fun. The first screen of the app should be a play button. Then there should be a game screen with a bird and a pipe. And finally, there should be a score screen with the scores in the current user session and play again button, which takes it to the first screen. Please take care of the visual aspects of the app; to be specific, I want bird and pipe to look like in the original flappy bird game. You are already provided project structure. Investigate it and act accordingly to it. Some important info about the project can be found in the `README.md` file.

## Output:

1. `docs/requirements.md` – structured requirements document
2. `docs/plan.md` – implementation plan with priorities and links
3. `docs/tasks.md` – detailed enumerated task list grouped into phases
4. `.junie/guidelines.md` – updated concise instructions for working with the task list