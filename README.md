DB Details:

I have used 3 databases.
1) master database, which will have tenant details such as, tenant id, tenant db url, username etc
master_db_tenant
![image](https://github.com/shubham-mhatre/spring-boot-r2dbc-multi-tenant/assets/55918816/87f77519-509c-44bc-90cd-1c9b2fffe887)

2)tenant_1 db has product table which have 2 product details.
![image](https://github.com/shubham-mhatre/spring-boot-r2dbc-multi-tenant/assets/55918816/c65c137e-8271-4314-a88c-6f75a77f7acb)

3)tenant_2 db has product table which have 3 product details.
![image](https://github.com/shubham-mhatre/spring-boot-r2dbc-multi-tenant/assets/55918816/bae3bf29-8b80-419e-9934-f6ef1b38baa7)

API Endpoint:
I have created 1 endpoint which will return list of products.
when i pass tenant_1 as tenant_id in request header, tenant_1 database should get connected and should return product list which have 2 records.
![image](https://github.com/shubham-mhatre/spring-boot-r2dbc-multi-tenant/assets/55918816/6262c657-b46d-4600-b40d-3d384478a072)

when i pass tenant_2 as tenant_id in request header, tenant_2 database should get connected and should return product list which have 3 records.

![image](https://github.com/shubham-mhatre/spring-boot-r2dbc-multi-tenant/assets/55918816/5c93653e-158b-4967-958d-ab0de5312155)
