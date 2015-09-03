f = open('hw2.md', 'r')
for line in f:
	for c in line:
		if (ord(c) > 127):
			print line
