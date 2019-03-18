# Python-Java-JMS
Kommunikation mellan Python och Java via JMS ActiveMQ Artemis standalone broker på docker.

Python producer skickar meddelande varje sekund som en java consumer läser.

## Tekniker
Artemis ActiveMQ broker, Thorntail, Python stomp.

## Så kör man
Man behöver köra en instans av Artemis ActiveMQ broker. En broker.xml finns med i projektet. Man ersätter alltså den som autogenereras när man installerar en instans av brokern med som finns i projektet. Javadelen körs enklast antingen genom **mvn clean install && mvn thorntail:run** eller **mvn clean install && java -jar target/python-java-jms-1.0-SNAPSHOT-thorntail.jar**
Pythondelen kan man antingen köra i en IDE eller med ett CLI-kommando. Bibliotek som behöver installeras i Python är: json, time, och stomp. OBS Python 3 används. 
