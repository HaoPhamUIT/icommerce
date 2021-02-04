# icommerce

certificate :

	phh.cer : keytool -import -file mailinh.cer -keystore cacerts or 
	windown: C:\Program Files\Java\jdk1.8.0_191\jre\lib\security>keytool -import -alias mlc -file D:\project\document\certificate\mailinh.cer -keystore cacerts
jwt : use when work with commons-jwt
	private_key.der : generate jwt token
	public_key.der : validate or claims jwt token