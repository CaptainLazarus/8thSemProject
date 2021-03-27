#import matplotlib.image as img

#a = img.imread("C:/Users/Rohan/Desktop/pokeball.jpg")
#print(a.shape)

# Import the necessary libraries
from PIL import Image
from numpy import asarray


# load the image and convert into
# numpy array
img = Image.open('C:/Users/Rohan/Downloads/img1.jpg')
a = asarray(img)

# data
print("Array of image 1")
#print(numpydata)


# load the image and convert into 
# numpy array
image = Image.open('C:/Users/Rohan/Downloads/img2.jpg')
b = asarray(image)
print("Array of image 2")  
#print(type(numpydata2))

c = a-b
d = b-a
a_dash = ((a+b)-c)-d 

#print(fin_numpydata)
  
#  shape
#print(fin_numpydata.shape)
  
# Below is the way of creating Pillow 
# image from our numpyarray
pilImage = Image.fromarray(a_dash)
print(type(pilImage))
pilImage.show()
# Let us check  image details
print(pilImage.mode)
print(pilImage.size)

if(numpydata.all() == a_dash.all()):
	print("YES THEY ARE EQUAL!")