CREATE TABLE Person(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username varchar(30) UNIQUE NOT NULL,
    password varchar NOT NULL,
    role varchar(30) NOT NULL,
    created_at timestamp NOT NULL
);


-- /////////////////////////////////////////////

CREATE TABLE Comments(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    owner_id int NOT NULL REFERENCES Person(id) ON DELETE SET NULL,
    text varchar(3000) NOT NULL,
    created_at DATE
);
-- /////////////////////////////////////////
CREATE TABLE Like_Actions_Person_Comment(
    person_id int REFERENCES Person(id),
    comment_id int REFERENCES Comments(id) ON DELETE CASCADE,
    PRIMARY KEY (person_id,comment_id),
    type varchar NOT NULL--в этом поле хранится значение "like" или "dislike"
);
-- ///////////////////////////////////////
CREATE TABLE Visit(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    session varchar,
    username varchar,
    value varchar,
    time timestamp
);
