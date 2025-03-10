The architectural approach you took and why:
I have used Clean Architecture (dividing the app into data, domain, and UI layers) with MVVI. 
I know for a simple app, MVVM would have been sufficient since there are no actions or events going from the ViewModel to the UI, 
but I find MVVI makes data management and UI display easier. 
I did not add a use case in the domain model since the app only fetches a list, and adding it would have been unnecessary.

How to run your project:
I have attached a zip file and an APK. You can install the APK directly using ADB. 
After the app starts click on the "Show Me the Portfolio" button to display the portfolio. Click back to return to the Intro Screen.

3rd party libraries or copied code you may have used:
I have used standard libraries like Retrofit for networking and Koin for dependency injection.
I havent really copied the code directly from anywhere, but referred to some examples to improve the use of modifiers for UI enhancements.

Any other information that you would like us to know
I have not implemented any security measures such as obfuscation, sandboxing, or encryption. 
With more time, I could have improved the UI, such as adding a well-designed splash screen, more meaningful names and messages and visual elements. 