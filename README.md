# home-store-backend
Real Estate Management API made with Spring Boot and MySQL

## Main features:
- **[Properties](#properties)**
- **[Ads](#ads)**
- **[Rentals](#rentals)**
- **[Sales](#sales)**
- **[Comments](#comments)**
- **[Forum](#forum)**

## Properties
| HTTP Verb | Request description    | Endpoint                         | Authorization    |
|-----------|------------------------|----------------------------------|------------------|
| GET       | get my properties      | /api/v1/properties               | USER             |
| GET       | get property by id     | /api/v1/properties/{id}          | USER             |
| POST      | save property          | /api/v1/properties               | USER             |
| PATCH     | update property        | /api/v1/properties/{id}          | USER             |
| DELETE    | delete property        | /api/v1/properties/{id}          | USER             |


## Ads
| HTTP Verb | Request description    | Endpoint                         | Authorization    |
|-----------|------------------------|----------------------------------|------------------|
| GET       | get all active ads     | /api/v1/ads                      | USER             |
| GET       | get ad by id           | /api/v1/ads/{id}                 | USER             |
| GET       | get ads by filters     | /api/v1/ads/filter               | USER             |
| GET       | get my ads             | /api/v1/ads/my-ads               | USER & REALTOR   |
| POST      | save ad                | /api/v1/ads                      | USER             |
| PATCH     | update ad              | /api/v1/ads/{id}                 | USER & REALTOR   |
| DELETE    | delete ad              | /api/v1/ads/{id}                 | USER             |


## Rentals
| HTTP Verb | Request description    | Endpoint                         | Authorization    |
|-----------|------------------------|----------------------------------|------------------|
| GET       | get my rentals         | /api/v1/rentals                  | REALTOR          |
| GET       | get rental by id       | /api/v1/rentals/{id}             | REALTOR          |
| POST      | save rental            | /api/v1/rentals                  | REALTOR          |


## Sales
| HTTP Verb | Request description    | Endpoint                         | Authorization    |
|-----------|------------------------|----------------------------------|------------------|
| GET       | get my sales           | /api/v1/sales                    | REALTOR          |
| GET       | get sale by id         | /api/v1/sales/{id}               | REALTOR          |
| POST      | save sales             | /api/v1/sales                    | REALTOR          |


## Comments
| HTTP Verb | Request description     | Endpoint                         | Authorization    |
|-----------|-------------------------|----------------------------------|------------------|
| POST      | post comment            | /api/v1/comment                  | USER & ADMIN     |
| DELETE    | delete comment          | /api/v1/comment/{id}             | USER & ADMIN     |
| PATCH     | edit comment            | /api/v1/comment/{id}             | USER & ADMIN     |

# Forum
| HTTP Verb | Request description     | Endpoint                         | Authorization    |
|-----------|-------------------------|----------------------------------|------------------|
| GET      | get all forums           | /api/v1/forums                   | permit all       |
| GET      | get forum by id          | /api/v1/comment                  | permit all       |