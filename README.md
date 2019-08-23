# Inline Activity Result [![](https://jitpack.io/v/cregus/inline-activity-result.svg)](https://jitpack.io/#cregus/inline-activity-result)
Android library written in Kotlin to simplify starting activities for result.

## Setup
### Add the JitPack repository to your build file
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### Add library dependency to your project build file
```
dependencies {
  implementation 'com.github.cregus:inline-activity-result:1.0'
}
```

## Sample usage
`startActivityForResult` functions can be started from FragmentActivity or Fragment.

### Starting activity using intent.
```kotlin
startActivityForResult(intent) { result ->
    if (result.isOk()) {
        val resultIntent = result.intent
        // Do something with result intent.
    }
}
```

### Starting activity with extras and listening for only OK result code.
```kotlin
val extras = Bundle()
extras.putInt("ID", 1)
startActivityForResultOk<SampleActivity>(extras) { result ->
    val resultIntent = result.intent
    // Do something with result intent.
}
```