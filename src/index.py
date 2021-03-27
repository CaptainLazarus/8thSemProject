# Import the necessary libraries
from PIL import Image
from numpy import asarray


# load the image and convert into
# numpy array
img = Image.open('../images/1.jpg')
a = asarray(img)


# load the image and convert into 
# numpy array
image = Image.open('../images/2.jpg')
b = asarray(image)

# Debugging
print(a.shape)
print(b.shape)
print(len(a))

c = []

for i in range(len(a)):
	for j in range(len(a[0])):
		for k in range(len(a[0][0])):
			if a[i][j][k] == b[i][j][k]:
				c[i][j][k] = a[i][j][k]
			else:
				c[i][j][k] = 0		
		c[i][j].append([])
	c[i].append([])
# Below is the way of creating Pillow 
# image from our numpyarray
pilImage = Image.fromarray(c)
pilImage = pilImage.resize((500,500))
pilImage.show()