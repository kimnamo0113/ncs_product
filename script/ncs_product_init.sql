-- ncs_product
DROP SCHEMA IF EXISTS ncs_product;

-- ncs_product
CREATE SCHEMA ncs_product;

set foreign_key_checks=0;

drop table if exists ncs_product.product;
CREATE TABLE ncs_product.product (
  `product_code` char(4) NOT NULL,
  `product_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`product_code`)
);

drop table if exists ncs_product.sale;
CREATE TABLE ncs_product.sale (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` char(4) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `saleCnt` int(11) DEFAULT NULL,
  `marginrate` int(11) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `product_code` (`product_code`),
  CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`product_code`) REFERENCES `product` (`product_code`)
);

drop table if exists ncs_product.sale_detail;
CREATE TABLE ncs_product.sale_detail (
  `no` int(11) DEFAULT NULL,
  `sale_price` int(11) DEFAULT NULL,
  `addTax` int(11) DEFAULT NULL,
  `supply_price` int(11) DEFAULT NULL,
  `margin_price` int(11) DEFAULT NULL,
  KEY `no` (`no`),
  CONSTRAINT `sale_detail_ibfk_1` FOREIGN KEY (`no`) REFERENCES `sale` (`no`) ON DELETE CASCADE
);

set foreign_key_checks=1;