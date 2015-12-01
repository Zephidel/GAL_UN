import re

pythonKeywords = ["and", "as", "assert", "break", "class", "continue",
					"def", "del", "elif", "else", "except", "exec", "False",
					"finally", "for", "from", "global", "if", "import", "in",
					"is", "lambda", "None", "nonlocal", "not", "or", "pass",
					"print", "raise", "return", "True", "try", "while", "with",
					"yield"]


def loadFile(path):
	isCommentedBlock = False
	text = []
	
	aReader = open(path,"r")
	currentLine = 
	
	while currentLine:
		if '"""' in currentLine and not isCommentedBlock:
			currentLine = aReader.readline()
			isCommentedBlock = True
		
		if isCommentedBlock:
			if '"""' in currentLine:
				isCommented = False
			
		else:
			if currentLine[0] == '#':
				currentLine.replace("\n","")
				currentLine.replace("\t","")
				if len(currentLine) > 0:
					text.append(currentLine)
				
		currentLine = aReader.readline()
			
			
	aReader.close()
	return text
	


def analyzeFile(filepath):
	lastFound = []
	text = loadFile(filepath)
	
	if len(text) > 0:
		
		if len(lastFound) > 0:
			del lastFound[:]
		
		for string in text:
			words = string.split()
			idx = 0
			
			isLiteral = False
			temp = ""
			
			while idx < len(words):
				word = words[idx]
				
				if re.match("^\".*$",word):
					isLiteral = True
				
				if isLiteral:
					temp = temp + word + " "
					if re.match("^.*\"$",word):
						isLiteral = False
						lastFound.append(["Literal",temp,-1])
						
				else:
					added = clasify(word)

				if not added:
					if re.match("^([^\\dA-Za-z ]*[a-zA-Z]+([0-9]*[a-zA-Z]*[-|_]*)*[^\\dA-Za-z ]*)*$",word):
						pattern = re.compile("[^\\dA-Za-z ][a-zA-Z]+([0-9]*[a-zA-Z]*[-|_]*)*")
						matcher = pattern.matcher(word);
						if matcher.findall():
							aWord = matcher.group()
							foundTokens.append(["SpecialSymbol", aWord[0:1], -1])
							clasify(aWord[1:len(aWord)])
						else:
							pattern = re.compile("[a-zA-Z]+([0-9]*[a-zA-Z]*[-|_]*)*[^\\dA-Za-z ]")
							matcher = pattern.matcher(word);
							if matcher.findall():
								aWord = matcher.group()
								clasify(aWord[0:len(aWord)-1])
								foundTokens.append(["SpecialSymbol",aWord.substring(aWord.length() - 1,aWord.length()), -1));
							else:

								pattern = re.compile("[^\\dA-Za-z ][a-zA-Z]+([0-9]*[a-zA-Z]*[-|_]*)*[^\\dA-Za-z ]")
								matcher = pattern.matcher(word)
								if matcher.findall():
									aWord = matcher.group()
									foundTokens.append(["SpecialSymbol",aWord[0:1],-1])
									clasify(aWord[1:len(aWord)-1])
									foundTokens.append(["SpecialSymbol",aWord.substring(aWord.length() - 1,aWord.length()), -1));
								
				idx += 1
		
		for aToken in lastFound:
			print "<"+aToken[0]+", "+aToken[1]+", "+aToken[2]+">"



def clasify(word):
	value = false
	if re.match("^[a-zA-Z]+$",word):
		if word in pythonKeywords:
			foundTokens.append(["keyword",word,-1])
			value = true
		
		
		else:
			if not word in foundIdentifiers:
				foundIdentifiers.append(word)
			
			foundTokens.append(["Identifier",word,-1])
			value = true
		
	else:
		if re.match("^[0-9]+$",word):
			foundTokens.append(["Number",word,-1])
			value = true
		else:
			if re.match("^[a-zA-Z]+([a-zA-Z]*|[0-9]+|_|-)*$",word):
				if not word in foundIdentifiers:
					foundIdentifiers.append(word)
				
				foundTokens.append(["Identifier",word,0])
				value = true
			else:
				if re.match("^=$",word):
					foundTokens.append(["AsignationOp",word,-1])
					value = true

				if re.match("^(==|!=)$",word):
					foundTokens.append(["ComparisonOp",word,-1])
					value = true

				if re.match("^[+|-|*|**]$",word):
					foundTokens.append(["MathOp",word,-1])
					value = true

				if re.matches("^:$",word):
					foundTokens.append(["DefinitionOp",word,-1])
					value = true
				
			
		
	
	return value
	
	
