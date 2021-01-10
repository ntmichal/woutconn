# Diet-tracker

Webapp for 
tracking diet


* SpringBoot
* REST, JPA
* Angular
* H2 Database
* [Maven](https://maven.apache.org/)
* Docker

API documentation

https://ntmichal.github.io/diet-tracker-api-swagger/

##### Clone source code from git
```
git clone https://github.com/ntmichal/Diet-tracker.git .
```

##### Build Docker image
```
docker build -t diet-tracker .
```
##### Run Docker Container
```
docker run -p 8080:8080 -it --rm diet-tracker:latest
