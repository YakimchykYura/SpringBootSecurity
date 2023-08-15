CREATE table Note (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title varchar,
    content varchar
)