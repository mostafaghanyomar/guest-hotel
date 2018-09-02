**Secure Service Demo (Hotel/Guest Example)**

This is a demonstration of how to create a secured micro service Using spring boot and spring security.
The API is documented as well thanks to swagger.

For the demonstration purpose I've made a simple example of guests registered in a hotel and would like to do a random reservation.

I've tried to make the service as much simple and clear as possible in order to be a base of any business model you'd like to apply security upon, also if there's anything unclear or any questions I'd like to help,
I'm available to be contacted via email, I'll try to answer as soon as possible, 
but it depends on my time and availability :)

@Mail:mostafa.ghany.omar@gmail.com

Now The Important Notes:
- To Login There are 2 users "guest" and "staff" both have the same password:
POST 
http://localhost:8081/my-hotel/auth/login
{"username":"guest","password":"passmyclass"}

- To View The Services Documentation in swagger:
http://localhost:8081/my-hotel/swagger-ui.html

- Only Users with the role STAFF can access the rooms data:
localhost:8081/my-hotel/api/hotel/rooms
- And Users with the role GUEST can access the reservation endpoint:
localhost:8081/my-hotel/api/book/room

You have to use the token in the header of all the secured services which are under "/api", you also can change this path in the properties file.