
INSERT INTO User(seq, userId, username, phone) VALUES ('1', 'blue', 'Leonardo Park', '010-3066-3571');

INSERT INTO UserOrder(seq, total) VALUES ('1', '100');

INSERT INTO UserOrderItem(seq, orderSeq, productName, price, amount) VALUES ('1', '1', '아이맥', '20', '1');
INSERT INTO UserOrderItem(seq, orderSeq, productName, price, amount) VALUES ('2', '1', '맥북', '20', '2');
INSERT INTO UserOrderItem(seq, orderSeq, productName, price, amount) VALUES ('3', '1', '맥북에어', '20', '1');
INSERT INTO UserOrderItem(seq, orderSeq, productName, price, amount) VALUES ('4', '1', '맥북프로', '20', '1');
