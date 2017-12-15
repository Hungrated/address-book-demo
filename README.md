# address-book-demo
A simple address book, based on JFrame.

## Usage

This demo needs database support.

```
CREATE DATABASE `address-book-demo`;
```

You need create a table first.

```
CREATE TABLE `phone_info` (
  name VARCHAR(10) NOT NULL,
  phone VARCHAR(32) NOT NULL
);
```

Then, you can run this demo and try its functions. Enjoy!
