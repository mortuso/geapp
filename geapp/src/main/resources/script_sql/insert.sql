CREATE TABLE word (
   w_name VARCHAR (500) NOT NULL,
   meaning VARCHAR (500) NOT NULL,
   note VARCHAR (2000)
);

CREATE TABLE w_result (
   w_name VARCHAR (500) NOT NULL,
   attempts INTEGER,
   centered INTEGER,
   percent NUMERIC(5, 2)
);

ALTER TABLE word ADD PRIMARY KEY (w_name);
ALTER TABLE w_result ADD PRIMARY KEY (w_name);
