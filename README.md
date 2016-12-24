# calculator
A little Android app where I use the Shunting Yard Algorithm along with Reverse Polish Notation to calculate all the things. 

Known bugs:
- Freezing when higher precedence operators come later in the evaluation.
- Not evaluating multiple digits, will only consider digit farthest right. Ex: 10 + 1 will result in 1, app only considers the   0 in 10.

Side note on the buttons: My intention for the visual aspect of the app is to have an image as a background and transparent buttons overlaying the image that will give the numbers their proper functions. For some reason the image I created to be the background is not loading so I replaced the transparent buttons with ugly gray numbers and symbols as placeholders.

NOTE: This is an Android Studio 2.2.3 project on Mac. Issues may occur if you attempt to use another version of Android Studio. You will probably need to change your SDK destination in the local.properties file.
