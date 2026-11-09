# EduKatalogApp

EduKatalogApp is a simple Android application developed in Kotlin that serves as a course catalog. It allows users to log in, view a list of educational courses, and see detailed information about a selected course.

## Features

*   **User Login**: A simple login screen to access the application.
*   **Course Listing**: Displays a list of available courses using a `RecyclerView`.
*   **Course Details**: View detailed information about a specific course, including its title, department, and description.
*   **Dummy Data**: The app is pre-populated with a set of dummy courses (Mobile Programming, Web Programming, Database, Data Structures, Computer Networks).

## Tech Stack

*   **Language**: Kotlin
*   **Framework**: Android SDK
*   **UI Components**: `RecyclerView`, `Intent` for navigation, XML-based layouts.

## Project Structure

*   `app/src/main/java/com/example/edukatalogapp/`: Contains the Kotlin source files.
    *   `MainActivity.kt`: Main dashboard displaying the list of courses.
    *   `LoginActivity.kt`: Login screen activity.
    *   `DetailActivity.kt`: Course detail activity.
    *   `Course.kt`: Data model for a course.
    *   `CourseAdapter.kt`: RecyclerView adapter for displaying course items.
*   `app/src/main/res/layout/`: Contains the XML layout files for the activities and list items.

## Getting Started

### Prerequisites

*   Android Studio
*   Android SDK

### Cloning the Repository

To clone this repository, use the following command in your terminal:

```bash
git clone https://github.com/dhnnnn/EduKatalogApp.git
cd EduKatalogApp
```

### Building and Running

You can build and run the application using Android Studio, or via the command line using Gradle.

#### Using Android Studio (Recommended)
1.  Open Android Studio.
2.  Select **File > Open** and choose the `EduKatalogApp` directory.
3.  Wait for the project to sync with Gradle.
4.  Run the app on your emulator or connected physical device by clicking the **Run** button.

#### Using Command Line (Gradle)

**On Linux or macOS:**

```bash
./gradlew assembleDebug
```

**On Windows:**

```cmd
gradlew.bat assembleDebug
```

After building, you can find the APK file in the `app/build/outputs/apk/debug/` directory.
