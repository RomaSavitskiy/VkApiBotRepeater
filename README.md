# VK API Repeater Bot

#### Requirements:
* [JDK 21](https://www.oracle.com/nl/java/technologies/downloads/#java21)
* [PostgreSQL](https://www.postgresql.org/download/)
* (Optional) [Ngrok](https://ngrok.com/download)

-------------

### How to Run the Application

#### 1. Clone the Repository
> `git clone git@github.com:RomaSavitskiy/VkApiBotRepeater.git`
>

#### 2. Configure VK API Properties
> 1. Open the `src/main/resources/vk.properties` file.
> 2. Specify the parameters obtained from the VK group settings.
>
> | Variable Name       | Description                      |
> |---------------------|----------------------------------|
> | vk.api.access-token | VK API access token              |
> | vk.api.v            | VK API usage version             |
> | vk.api.secret       | Callback API secret key          |
> | vk.api.confirmation | Callback API confirmation string |
>


#### 4. Set environment variables for PostgreSQL in resources/application.yml
> | Variable Name | Description                  |
> |---------------|------------------------------|
> | uri           | Uri your database            |
> | username      | Username of your DBMS server |
> | password      | зфыыцщкв of your DBMS server |
>

#### 5. Build the JAR
> On a Linux host: `./mvnw clean package`
>
> On a Windows host: `mvnw.cmd clean package`
>

#### 6. Run the Application
Execute the following command to run the application:
> `java -jar targer/Responder.jar`
> 
To enable external HTTPS on the local machine, Ngrok can be used.


