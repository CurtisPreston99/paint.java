# PDJ file format

this is the file format for paint.java 

this file format supports layers and layer properties 

how it works

## file format

### file layout
*.PDJ
|-- layers.xml
|-- 0.png
|-- 1.png
|-- ...

### layers.xml
this is what paint.java parses when loading 
PDJ is the root element 
width and height describes the dimensions of the image 
layers is the element containing all of the layers 

layer is the element for each layer there is 1 per layer 
name is the name of each layer 
visible is a boolean 1 or 0 for if the layer is enabled 
opacity is a int between 0-100 for the  opacity of the layer
example file 

    |<PDJ>
    +  +  <width>640</width>
       |  <height>480</height>
       |  <layers>
       +  +	<layer>
          +  + 	<name>layer 0</name>
           	 |	<opacity>100</opacity>
           	 |	<visable>1</visable>
          +	 +	<file>0.png</file>
          |	</layer>
          |	<layer>
          +  +	<name>layer 1</name>
             |	<opacity>100</opacity>
           	 |	<visable>1</visable>
          +  +  <file>1.png</file>
          |</layer>
          |<layer>
          +  +	<name>layer 2</name>
             |	<opacity>100</opacity>
             |	<visable>1</visable>
          +  +	<file>2.png</file>
       +  +	</layer>
    +  +</layers>
    |</PDJ>

the parser parses this file making a list of layers before making it into an image
