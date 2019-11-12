#Heeey Inovia! - BackendTest README
#This was developed and executed on Ubuntu!

mvn spring-boot:run


#REST API Calls

#Say hi and check if the api is running. /sayhi
curl http://localhost:8080/sayhi

#Fill the database / your wish list for Santa! /makeawish
curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{"presentName": "Employment", "cost": "1337", "store": "Inovia"}' "http://localhost:8080/makeawish"
curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{"presentName": "Spiderman", "cost": "1111", "store": "Hobex"}' "http://localhost:8080/makeawish"
curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data '{"presentName": "Warm clothes", "cost": "369", "store": "Stadium"}' "http://localhost:8080/makeawish"

#Check your cool wish list so santa wont miss anything! /wishlist
curl http://localhost:8080/wishlist

#Was the most important wish on the wish list registered? /wish/{presentName}
curl http://localhost:8080/wish/Employment