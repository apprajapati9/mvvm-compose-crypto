Notes:

- KAPT is added as plugin but not used. Using KSP for hilt.

These are dependencies on version with Kotlin and Hilt so if encountered such issues, 
check for supported version of Kotlin with Hilt compatibility.

Update: For now, you can use the newest version.

    Kotlin:1.9.0 - Hilt:2.48
    Kotlin:2.0.0 - Hilt:2.51.1

https://stackoverflow.com/questions/67744002/hilt-unsupported-metadata-version-in-kotlin

- This project contains : Keybindings.txt for my custom key shortcuts for Android studio IDE.


- Abstracted Hilt DI is used on GetCoinUseCase and GetCoinDetail use case where we don't have to
  use @Inject constructor 
  - a nice article is written here : 
    - https://proandroiddev.com/dagger-and-inject-on-constructors-do-or-dont-9d97e7c93f84
  
- A useful nice article written to setup hilt di ->  https://medium.com/@deepakkathayat87/setting-up-hilt-dependency-injection-in-android-using-libs-versions-toml-and-kotlin-dsl-37acbcb1f483