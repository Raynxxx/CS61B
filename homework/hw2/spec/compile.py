import markdown

f = open('hw2.md', 'r')
html = markdown.markdown(f.read())
of = open('hw2.html', 'w')
of.write(html)