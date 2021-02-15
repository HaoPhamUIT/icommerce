a. Brief explanation for the software development principles, patterns & practices being
applied:
	- I complete the project in 7 days, analyze and design 1 day, code 6 days (2 hours / day). A total of 14 hours.
	- Patterns & practices being: Factory pattern, OOP, Solid 
b.Brief explanation for the code folder structure and the key Java libraries and frameworks
being used:
 ![alt text](https://i.ibb.co/dJMgrp5/Capture.png)

Document

-certificate :

	phh.cer : keytool -import -file phh.cer -keystore cacerts or 
	windown: C:\Program Files\Java\jdk1.8.0_191\jre\lib\security>keytool -import -alias mlc -file D:\project\document\certificate\phh.cer -keystore cacerts
-jwt : use when work with commons-jwt
	private_key.der : generate jwt token
	public_key.der : validate or claims jwt token
