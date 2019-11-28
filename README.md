# Esports Edge service
The edge service connects to the Eureka server, and retrieves data from the micro-services using. Before we had implemented the Zuul Gateway, this service was our main point of contact with the micro-services. The controller that are a part of the Edge services make sure that we can GET, POST, PUT and DELETE data from our microservices. It also contains combined models, that we can use to return different sorts of relations
## Context
The Esports wiki allows you to find every bit of information about the games, teams, tournaments and players that are involved in Espors. You can look at them in detail, add, delete or even edit them. This way you can keep your own database about your favorite esports game / team.
