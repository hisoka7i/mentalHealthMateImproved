This was project for my post graduation diploma
I am trying to implement some new things into it.
1. JWT token(Done)
2. AI chatbox(Done, just replace my API key with your own, api key"
3. Different system design concepts
   3a. Rate limiter
   3b. Cache from DB
4. Convert it into Event Driven(Implement kafka)
5. Apache kafka implementation
   5.0.1: Implementing simple producer and consumer.(Completed, front_end implementation is missing but backend is completed, It is event driven.)
    I want landing on home a page as a event which which afterwards will tell if user logged in or not.
   5a. User activity tracker(Done). (Mood analysis can also be implemented)
   5b. Chatbot responses.
   5c. Notifications.
6. Implement design patterns.
   6a. Singleton pattern: Authentication service and configuration manager.
   6b. Observer pattern: For user login or activity miss.
   6c. Factory pattern: For chatbox response.
   6d. Repository pattern: To abstract database interactions
7. Security
   7a. API Security(rate limiter, SQL injection, HTTPS)
   7b. End to end Encryption(use OpenSSL --> Encrypt client message and de crypt them at server.)
   7c. Secure Authentication using OAuth 2.0
