A backend code where you can demonstrate the feature of add, update number of quantity and removed products from the cart through api. 
You can use h2 db for in-memory behavior or whatever you are comfortable with. The code must be written in java using spring boot (version >= 2.x)
==================================================================
POST: (adding to cart)
---------------------------
url: localhost:8080/carts/items
body:

{
"userId": 29,
"productId": 5,
"qty": 1,
"price":500.5
}

Result:
[
    {
        "cartId": 1,
        "userId": 29,
        "productId": 2,
        "qty": 23,
        "price": 234.5
    },
    {
        "cartId": 2,
        "userId": 29,
        "productId": 5,
        "qty": 5,
        "price": 500.5
    }
]
=========================================================================================
DELETE: (delete product from cart)
--------------------------------------
url: localhost:8080/carts/removeProduct/29/6

result:
Product is deleted
=========================================================================================
GET:(get items from cart)
--------------------------
url: localhost:8080/carts/getCartsByUserId/29

result:
[
    {
        "cartId": 1,
        "userId": 29,
        "productId": 2,
        "qty": 23,
        "price": 234.5
    },
    {
        "cartId": 2,
        "userId": 29,
        "productId": 3,
        "qty": 3,
        "price": 50.6
    }
]
=========================================================================================
PUT:(update quantity)
------------------------
url:localhost:8080/carts/updateQtyForCart
body:
{
"userId": 29,
"productId": 2,
"qty": 1,
"price":50.6
}

Result:
[
    {
        "cartId": 1,
        "userId": 29,
        "productId": 2,
        "qty": 1,
        "price": 50.6
    }
============================================================================================

POST: (check out)
url: localhost:8080/order/updateorder
body:
{
"userId": 29,
"totalprice": 250.0,
"paymentType": "Creditcard"
}

result:
[
    {
        "orderId": 1,
        "userId": 29,
        "totalprice": 250.0,
        "paymentType": "Creditcard"
    }
]

======================================================
Tables

SELECT * FROM add_to_cart;
SELECT * FROM checkout_cart;
SELECT * FROM products;