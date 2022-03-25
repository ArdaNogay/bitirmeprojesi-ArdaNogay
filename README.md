# Service that determines final prices

<!-- TABLE OF CONTENTS -->
<details><summary>CONTENT</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#short-description">Short Description</a></li>
        <li><a href="#main-story">Main Story</a></li>
        <li><a href="#extended-story">Extended Story</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#screenshots">Screenshots</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project
### Built With
* [Jason Web Token](https://jwt.io/)
* [Java 11](https://www.oracle.com/tr/java/technologies/javase/jdk11-archive-downloads.html)
* [IntelliJ Idea](https://www.jetbrains.com/idea)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Validation](https://spring.io/guides/gs/validating-form-input)
* [Developer Tools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
* [Postgre SQL](https://www.postgresql.org/)
* [Open API](https://springdoc.org)
* [Lombok](https://projectlombok.org)
* [Hateoas](https://en.wikipedia.org/wiki/HATEOAS)

About the project file;

![Spring   Config](https://user-images.githubusercontent.com/83350108/160043332-0da3a166-0d6c-4652-a208-04054527dcf3.PNG)


### Short Description
Project Subject: Writing the service that determines the final prices of the products in a market according to the sales prices, using the Spring Boot Framework and optionally writing the frontend.
Before you run and use this project, please read the readme file and main story by clicking below links.

### Main Story
<a href="https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-ArdaNogay/blob/main/bitirme.pdf" target="_blank">Click me to read main story.</a>

<!-- GETTING STARTED -->
## Getting Started
### Installation
1. Clone the repo
   ```sh
      git clone https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-ArdaNogay.git
   ```
2. Install JDK-11 and switch to it.
3. Import the project to your IDE.
4. Find the pom.xml file in the project files and select it to proceed.
This step will make the necessary project settings for you.

![ide](https://user-images.githubusercontent.com/83350108/160042982-ceffe9f4-0c94-47bc-94dc-35a257ca01f5.PNG)

5. Create a new database by using PgAdmin on PostgreSQL.
6. If you want, you can run the project on IDE too by applying required configurations. 
7. Open the swagger ui on your browser. Default link -> http://localhost:8080/swagger-ui.html
8. Send request to endpoints (The project is running on the 8080 port by defalt).

### Screenshots
---
![register](https://user-images.githubusercontent.com/83350108/160039926-3acce8cc-d4ae-4de5-943b-df1fb2cf5731.PNG)
- In order to use the services, you must first register with the system.
</br>

![login1](https://user-images.githubusercontent.com/83350108/160040716-0ebaff60-3daa-48af-95e1-760cde3cdcb3.PNG)
- Login to the system with the user you created.
</br>

![getyourtokenwithoutbearer](https://user-images.githubusercontent.com/83350108/160040863-eaa52abd-2965-4f0a-96b5-1161994cccc3.PNG)
- After logging into the system, do not forget to get tokens from the reply you receive in order to be able to use the service.
- ##### WARNING! When purchasing your token, you must buy it without the "Bearer" prefix.
</br>

![auth](https://user-images.githubusercontent.com/83350108/160041436-4e67494c-ee72-4d23-9f83-57b234ace9e9.PNG)
- Click the "Authorize" button on the top right of the Swagger page.
</br>

![auth Authorize](https://user-images.githubusercontent.com/83350108/160041467-2a77f1d5-a49b-4b7e-99f6-f3ae0ed686f4.PNG)
- In the window that opens, enter the token given to you and verify your permission for service uses.

![unlocked](https://user-images.githubusercontent.com/83350108/160041926-3e52c4cf-79ce-4b87-8029-31a9d6c8c92f.jpg)

You now have the necessary permission to use all services. All services are waiting for you to use.

<!-- CONTRIBUTING -->
## Contributing
Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request.

<!-- CONTACT -->
## Contact
Ceyhan Arda Nogay: https://www.linkedin.com/in/ardanogay

Project Link: https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-ArdaNogay
