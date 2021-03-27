# Import the necessary libraries
from PIL import Image
from numpy import asarray
import numpy as np


# Print iterations progress
def printProgressBar (iteration, total, prefix = '', suffix = '', decimals = 1, length = 100, fill = '█', printEnd = "\r"):
    """
    Call in a loop to create terminal progress bar
    @params:
        iteration   - Required  : current iteration (Int)
        total       - Required  : total iterations (Int)
        prefix      - Optional  : prefix string (Str)
        suffix      - Optional  : suffix string (Str)
        decimals    - Optional  : positive number of decimals in percent complete (Int)
        length      - Optional  : character length of bar (Int)
        fill        - Optional  : bar fill character (Str)
        printEnd    - Optional  : end character (e.g. "\r", "\r\n") (Str)
    """
    percent = ("{0:." + str(decimals) + "f}").format(100 * (iteration / float(total)))
    filledLength = int(length * iteration // total)
    bar = fill * filledLength + '-' * (length - filledLength)
    print(f'\r{prefix} |{bar}| {percent}% {suffix}', end = printEnd)
    # Print New Line on Complete
    if iteration == total: 
        print()

# load the image and convert into
# numpy array
img = Image.open('../images/1.jpg')
a = asarray(img)


# load the image and convert into 
# numpy array
image = Image.open('../images/1.jpg')
b = asarray(image)

# # Debugging
# print(a.shape)
# print(b.shape)
# print(len(a))

c = [[[0]*a.shape[2]]*a.shape[1]]*a.shape[0]
c = np.array(c)
# print(c.shape)

for i in range(len(a)):
	printProgressBar(i , len(a)-1)
	for j in range(len(a[0])):
		temp = []
		for k in range(len(a[0][0])):
			if a[i][j][k] == b[i][j][k]:
				temp.append(a[i][j][k])
			else:
				temp.append(0)		
		c[i][j] = (np.array(temp).astype(np.uint8))

# print(c.shape)

# Below is the way of creating Pillow 
# image from our numpyarray
pilImage = Image.fromarray(c)
pilImage = pilImage.resize((500,500))
pilImage.show()