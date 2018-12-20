# CSC 335 Lab 6: Etch-A-Sketch

In this lab, we'll be using the ```Canvas``` control along with handling mouse movements to make a simplistic line drawing program. (That honestly isn't much like an actual Etch-A-Sketch at all.)

Here's the GUI:

![GUI Screenshot](layout.png?raw=true "Screenshot")

Our Scene will consist of a Canvas and a toolbar along the bottom. Use BorderPane for the topmost layout, and add to the bottom a flowpane. To add nodes to a FlowPane, do ```flowpane.getChildren().add(control);``` where ```flowpane``` is your flowpane instance and ```control``` is the control instance you wish to add.

The rightmost control is a ```ColorPicker``` control. Use that to set the current stroke color. It generates an ```ActionEvent``` when the user selects a new color. Default it to ```Color.BLACK```.

To handle the drawing, we will want to add an event handler to our canvas for ```MOUSE_DOWN``` events and ```MOUSE_DRAGGED``` events. The ```MOUSE_DOWN``` event indicates that the user has depressed the left mouse button and ```MOUSE_DRAGGED``` means that we have moved the mouse while holding the left mouse button down. These will both be necessary so that we can draw a stroke (line) between the previous point (where the initial click occurred in the case of the first ```MOUSE_DOWN``` event) and the current mouse position (obtained from the ```Event``` parameter to our mouse dragged handler). 

The clearscreen button should ```clearRect()``` on the whole canvas.

You should then be able to create a multicolor line drawing, as in:

![Drawing](masterpiece.png?raw=true "Drawing")
