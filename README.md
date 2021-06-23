# Quotes - Android App

> Android application that displays the list of quotes retrieved from public API

## Architectural components included
- `ViewModel`

## Steps to Build the App

-   Clone the project from github
-   Open the project in Android Studio
-   Click the dropdown menu in the toolbar at the top (Open 'Edit Run/Debug configurations' dialog)
-   Select "Edit Configurations"
-   Click the "+" Button
-   Select "Gradle"
-   Choose your module as a Gradle project
-   In Tasks: enter assemble
-   Press Run

Your debug apk will be generated at `<Project Name>/app/build/outputs/apk/debug/`

## API Details

### End point URL
`https://type.fit/api/quotes`

### Sample Response
```json
{
    "text": "Genius is one percent inspiration and ninety-nine percent perspiration.",
    "author": "Thomas Edison"
}
```

