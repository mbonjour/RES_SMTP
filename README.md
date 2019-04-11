# Basic SMTP Prank
#### Author : Bonjour Mickael & Mettler Samuel

## Description
This application allows you to forge emails. Subjects, contents and targets are all available and free to edit for your own pleasure. The application will connect to a chosen SMTP server and send a certain amount of email depending on the choice of the user.

## Functionalities 
- The user is able to define a list of victims by editing the file _mailTarget.utf8_ located in the config folder. It is also possible to keep the default values we provided. The format is the following :  
```
address@email.com  
secondAddress@email.com
...
```
- The user is able to define how many groups of victims should be formed in a given campaign. The user is able to edit the number of groups in the file _config.properties_ located in the config folder. The default value is 5.
- The user is able to define a list of email messages. When a prank is played on a group of victims, one of the messages is selected. The mail is sent by one of the victim to all the others. Obviously he won't send an email to himself. The group are made of 3 persons at least. 


## How to use

There are differents way you can test our tool. In the description below we will use a Docker image to emulate the SMTP server. Docker is a tool to wrap an application from your environment and which contain all that is needed to run this application locally on your machine. This guarantees that the software will always run the same, regardless of its environment.  
Here's a guide you can follow :  
1. After you downloaded this project, open a terminal and go to the folder _util_. It should contains a Dockerfile and a Java archive (.jar)
2. run the following command from the _util_ folder :  
```
docker build -t mockserver .
docker run -d -p 8282:8282 -p 2525:2525 mockserver
```

Please be advised that the port 2525 matchs the SmtpPort in the file _config.properties_. It needs to be the same to work properly.  

3. The STMP server beeing up, you can now freely send emails with our application. Either launch it from your favorite IDE or by running the following command from the root fo the application :
```
cd target
java -jar RES_SMTP-1.0-SNAPSHOT.jar
```

## Implementation Detail

