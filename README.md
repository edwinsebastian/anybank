# Getting Started

## Create transaction
### POST /v1/transaction
#### request
```
{
    "accountIban": "2f919434-ed72-434a-bc57-30341a18ee66",
    "date": "2019-07-16T16:55:42.000Z",
    "amount": -97.01,
    "fee": -0.44,
    "description": "Restaurant payment"
}
```
>reference: if it is not sent it is generated automatically
>
>fee: if it is not sent by default it is 0

#### response
```
{
    "reference": "12b97411-6252-4387-b6d8-2899e4f318a6",
    "accountIban": "2f919434-ed72-434a-bc57-30341a18ee66",
    "date": "2019-07-16T16:55:42.000+00:00",
    "amount": -97.01,
    "fee": -0.44,
    "description": "Restaurant payment"
}
```
## Search transactions
### GET 
>/v1/transaction
> 
>all transactions

#### response
```
[
    {
        "id": "9ce51750-9f1b-4e6e-a6f2-bb62a4483e16",
        "amount": 93.16,
        "fee": 6.50,
        "date": "2021-12-26T11:37:19.000+00:00",
        "description": "Spoon - Soup, Plastic"
    },
    {
        "id": "a64be17b-8a56-4825-a3e3-26d9a4568dc6",
        "amount": 90.54,
        "fee": 7.66,
        "date": "2021-12-20T11:31:03.000+00:00",
        "description": "Cleaner - Comet"
    },
    {
        "id": "a95aafb7-2de0-4019-85bf-f2cbbe4e13d9",
        "amount": 34.82,
        "fee": 9.23,
        "date": "2022-01-07T00:32:17.000+00:00",
        "description": "Bag - Clear 7 Lb"
    },
    {
        "id": "ca3a430b-d663-483d-a873-e339a4f070a7",
        "amount": 13.84,
        "fee": 2.51,
        "date": "2021-02-22T11:01:16.000+00:00",
        "description": "Bread - Frozen Basket Variety"
    }
]
```

### GET 
>/v1/transaction/12b97411-6252-4387-b6d8-2899e4f318a6
>
>single transaction

#### response
```
{
    "id": "12b97411-6252-4387-b6d8-2899e4f318a6",
    "amount": -97.01,
    "fee": -0.44,
    "date": "2019-07-16T16:55:42.000+00:00",
    "description": "Restaurant payment"
}
```

### GET transactions by iban 
>/v1/transaction/iban/2f919434-ed72-434a-bc57-30341a18ee66?sortBy=amount&order=asc

>#### Optionals request params
> 
>sortBy: By default is date
> 
>order: By default is desc
#### response
```
[
    {
        "id": "12b97411-6252-4387-b6d8-2899e4f318a6",
        "amount": -97.01,
        "fee": -0.44,
        "date": "2019-07-16T16:55:42.000+00:00",
        "description": "Restaurant payment"
    },
    {
        "id": "ca3a430b-d663-483d-a873-e339a4f070a7",
        "amount": 13.84,
        "fee": 2.51,
        "date": "2021-02-22T11:01:16.000+00:00",
        "description": "Bread - Frozen Basket Variety"
    },
    {
        "id": "a95aafb7-2de0-4019-85bf-f2cbbe4e13d9",
        "amount": 34.82,
        "fee": 9.23,
        "date": "2022-01-07T00:32:17.000+00:00",
        "description": "Bag - Clear 7 Lb"
    },
    {
        "id": "a64be17b-8a56-4825-a3e3-26d9a4568dc6",
        "amount": 90.54,
        "fee": 7.66,
        "date": "2021-12-20T11:31:03.000+00:00",
        "description": "Cleaner - Comet"
    },
    {
        "id": "9ce51750-9f1b-4e6e-a6f2-bb62a4483e16",
        "amount": 93.16,
        "fee": 6.50,
        "date": "2021-12-26T11:37:19.000+00:00",
        "description": "Spoon - Soup, Plastic"
    }
]
```

### GET transactions by iban (Alternative)
>/v1/account/2f919434-ed72-434a-bc57-30341a18ee66
#### response
```
{
    "id": "2f919434-ed72-434a-bc57-30341a18ee66",
    "balance": 197.45,
    "transactions": [
        {
            "id": "9ce51750-9f1b-4e6e-a6f2-bb62a4483e16",
            "amount": 93.16,
            "fee": 6.50,
            "date": "2021-12-26T11:37:19.000+00:00",
            "description": "Spoon - Soup, Plastic"
        },
        {
            "id": "a64be17b-8a56-4825-a3e3-26d9a4568dc6",
            "amount": 90.54,
            "fee": 7.66,
            "date": "2021-12-20T11:31:03.000+00:00",
            "description": "Cleaner - Comet"
        },
        {
            "id": "a95aafb7-2de0-4019-85bf-f2cbbe4e13d9",
            "amount": 34.82,
            "fee": 9.23,
            "date": "2022-01-07T00:32:17.000+00:00",
            "description": "Bag - Clear 7 Lb"
        },
        {
            "id": "ca3a430b-d663-483d-a873-e339a4f070a7",
            "amount": 13.84,
            "fee": 2.51,
            "date": "2021-02-22T11:01:16.000+00:00",
            "description": "Bread - Frozen Basket Variety"
        }
    ]
}
```
# Transaction status
### POST /v1/transaction/status
#### request
```
{
    "reference": "9ce51750-9f1b-4e6e-a6f2-bb62a4483e16",
    "channel": "INTERNAL"
}
```
>channel: if it is not sent by default is CLIENT

#### response
```
{
    "reference": "9ce51750-9f1b-4e6e-a6f2-bb62a4483e16",
    "status": "SETTLED",
    "amount": 93.16,
    "fee": 6.50
}
```
