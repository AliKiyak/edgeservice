# Esports Edge service
The edge service connects to the Eureka server, and retrieves data from the micro-services using. Before we had implemented the Zuul Gateway, this service was our main point of contact with the micro-services. The controller that are a part of the Edge services make sure that we can GET, POST, PUT and DELETE data from our microservices. It also contains combined models, that we can use to return different sorts of relations

## Routes and methods
![Game_controller](https://i.postimg.cc/Z58PvjN9/image.png)
## Context
The Esports wiki allows you to find every bit of information about the games, teams, tournaments and players that are involved in Esports. You can look at them in detail, add, delete or even edit them. This way you can keep your own database about your favorite esports game / team.
## Installation

* Clone our project (git clone https://github.com/AliKiyak/edgeservice.git)

## Usage

Run the project and make sure that the ZuulGateway, Eureka Server and microservices are all running.

## Client

This is a school project made for the course Java Advanced Topipcs

## Authors and acknowledgment

* Ali Kiyak (AliKiyak)
* Bosz Srisan (BoszS)
* René Vanhoof (VanhoofR)
* Chris Tophe (Christophe195)
