CREATE TABLE User (
  seq       INT,
  userId    VARCHAR(10),
  username  VARCHAR(50),
  phone     VARCHAR(30)
);

CREATE TABLE UserOrder (
  seq       INT,
  total     INT
);

CREATE TABLE UserOrderItem (
  seq       INT,
  orderSeq  INT,
  productName VARCHAR(50),
  price     INT,
  amount    INT
);

