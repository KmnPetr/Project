CREATE TABLE Person(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username varchar(30) UNIQUE NOT NULL,
    password varchar NOT NULL,
    role varchar(30) NOT NULL,
    created_at timestamp NOT NULL
);

DROP TABLE Person;

UPDATE Person SET role='ROLE_ADMIN' WHERE id=1;

-- /////////////////////////////////////////////

CREATE TABLE Comments(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    owner_id int NOT NULL REFERENCES Person(id) ON DELETE SET NULL,
    text varchar(3000) NOT NULL,
    count_likes int,
    count_dislikes int,
    created_at timestamp
);
DROP TABLE Comments;
-- ///////////////////////////////////////////////
CREATE TABLE Likes_Person_Comment(
    person_id int REFERENCES Person(id),
    comment_id int REFERENCES Comments(id),
    PRIMARY KEY (person_id,comment_id)
);
CREATE TABLE Dislikes_Person_Comment(
    person_id int REFERENCES Person(id),
    comment_id int REFERENCES Comments(id),
    PRIMARY KEY (person_id,comment_id)
);
DROP TABLE Likes_Person_Comment;
DROP TABLE Dislikes_Person_Comment;
-- /////////////////////////////////////////
CREATE TABLE Like_Actions_Person_Comment(
    person_id int REFERENCES Person(id),
    comment_id int REFERENCES Comments(id),
    PRIMARY KEY (person_id,comment_id),
    action varchar NOT NULL--в этом поле хранится значение "like" или "dislike"
);
DROP TABLE Like_Actions_Person_Comment;