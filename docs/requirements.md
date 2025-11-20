# Requirements Document

## Introduction
This document specifies the requirements for a simple, fun Flappy Bird–style game implemented with Kotlin Compose Multiplatform. The application must run on iOS, Android, Desktop (JVM), and Web (Wasm JS), with primary emphasis on the Web (Wasm JS) target so it can be easily shared and played in a browser. The app has three screens: a Start screen with a Play button, a Game screen with a bird and a pipe (obstacle), and a Score screen that shows scores from the current user session with a Play Again button that returns to the Start screen. Visuals should closely resemble the original Flappy Bird’s bird and pipe while remaining IP‑safe (original‑looking but not copied assets).

---

## Requirements

<a id="r1"></a>
1. [R1] Start Screen — Play Button
   - User Story:
     > As a user, I want to see a simple Start screen with a Play button so that I can quickly begin the game.
   - Acceptance Criteria:
     > WHEN the app launches THEN the system SHALL display a Start screen with a prominent Play button centered and accessible.
     
     > WHEN the user taps/clicks/presses Enter or Space with the Play button focused THEN the system SHALL navigate to the Game screen.

<a id="r2"></a>
2. [R2] Game Screen — Bird and Pipe Visible
   - User Story:
     > As a user, I want to play a Flappy Bird–style game with a bird and a pipe so that gameplay feels familiar and fun.
   - Acceptance Criteria:
     > WHEN the Game screen loads THEN the system SHALL render a bird sprite and at least one pipe obstacle on screen.

     > WHEN the user provides input (tap/click/Space) THEN the system SHALL cause the bird to flap (gain upward velocity) against gravity.

     > WHEN the bird hits the ground, goes off-screen vertically, or collides with a pipe THEN the system SHALL end the run and navigate to the Score screen.

<a id="r3"></a>
3. [R3] Scoring — In‑Run Score
   - User Story:
     > As a user, I want a score that increases as I progress so that I can measure my performance.
   - Acceptance Criteria:
     > WHEN the bird successfully passes a pipe gap THEN the system SHALL increment the score by 1 and display the current score during the run.

<a id="r4"></a>
4. [R4] Score Screen — Session Scores and Play Again
   - User Story:
     > As a user, I want to see my recent scores and quickly try again so that I can improve and have fun.
   - Acceptance Criteria:
     > WHEN a run ends THEN the system SHALL navigate to a Score screen listing the scores from the current app session (latest first), including the most recent score.

     > WHEN the user activates the Play Again button THEN the system SHALL return to the Start screen.

     > WHEN the app is closed/reloaded THEN the system SHALL reset the session scores (no persistence across app restarts).

<a id="r5"></a>
5. [R5] Visual Fidelity — Bird and Pipe Appearance
   - User Story:
     > As a user, I want the bird and pipe to look like the originals so that the game feels authentic.
   - Acceptance Criteria:
     > WHEN the Game screen renders THEN the system SHALL draw a bird and pipe with colors, proportions, and style closely matching the classic Flappy Bird look, without using copyrighted art assets verbatim.

     > WHEN running on devices with different resolutions and densities THEN the system SHALL render crisp, properly scaled sprites without distortion.

<a id="r6"></a>
6. [R6] Controls — Cross‑Platform Input
   - User Story:
     > As a user, I want intuitive controls on each platform so that gameplay is consistent and responsive.
   - Acceptance Criteria:
     > WHEN on mobile (iOS/Android) THEN tapping the screen SHALL trigger a flap.

     > WHEN on desktop/web THEN clicking/tapping the canvas or pressing Space SHALL trigger a flap; Escape SHALL pause/return to Start (optional), or be ignored if pause is not implemented.

<a id="r7"></a>
7. [R7] Performance and Frame Rate
   - User Story:
     > As a user, I want smooth gameplay so that the game feels responsive and enjoyable.
   - Acceptance Criteria:
     > WHEN the game runs on supported devices/browsers THEN the system SHALL target 60 FPS for rendering; on constrained environments it MAY gracefully degrade to 30 FPS without stutter.

<a id="r8"></a>
8. [R8] Layout, Safe Areas, and Aspect
   - User Story:
     > As a user, I want the game to fit my screen properly so that nothing is cropped or awkward.
   - Acceptance Criteria:
     > WHEN the game runs on devices with various sizes/aspects THEN the system SHALL maintain a consistent playfield aspect (portrait) with letterboxing or scaling as needed, respecting safe areas (e.g., iOS notches) and avoiding UI overlap.

<a id="r9"></a>
9. [R9] Multiplatform Targets — Build & Run
   - User Story:
     > As a user, I want the game available on iOS, Android, Desktop, and Web so that I can share and play it anywhere.
   - Acceptance Criteria:
     > WHEN building for Android THEN the app SHALL assemble and run via Gradle as described in README.

     > WHEN building for Desktop (JVM) THEN the app SHALL run via Gradle as described in README.

     > WHEN building for Web (Wasm JS preferred) THEN the app SHALL run locally via the Wasm development task as described in README.

     > WHEN building for iOS THEN the app SHALL build and run via the provided Xcode/Gradle flows as described in README.

<a id="r10"></a>
10. [R10] Error Handling and Unsupported Environments
    - User Story:
      > As a user, I want clear messages if my environment is unsupported so that I understand what to do.
    - Acceptance Criteria:
      > WHEN running in a browser that cannot execute the Wasm build THEN the system SHALL show a friendly message with a suggestion to use a modern browser.

<a id="r11"></a>
11. [R11] Minimal Testing & QA
    - User Story:
      > As a developer, I want basic tests or verifications so that regressions are minimized.
    - Acceptance Criteria:
      > WHEN CI/local verification runs THEN the project SHALL compile for at least one target by default and include a minimal unit test or smoke check for core logic (e.g., physics step), where feasible.
