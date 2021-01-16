**POSTGRESQL**
```
helm install postgresql -f chart/postgresql/values.yaml bitnami/postgresql
kubectl run postgresql-client-add --rm --tty -i --namespace myapp --image docker.io/bitnami/postgresql:11.10.0-debian-10-r52 --env="POSTGRESQL_PASSWORD=postgres" --env="PGPASSWORD=postgres" --command -- psql --host postgresql -U postgres -d postgres -p 5432
    create table if not exists order_details (id varchar(250) primary key, client_id varchar(250), amount bigint, ins_time timestamp default now());
```

**APPLICATIONS:**
```
helm install order-service chart/order-service/
```
