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

grant all privileges 
on ncs_product.* 
to 'user_ncs_product'@'localhost'
identified by 'rootroot';


grant all privileges 
on ncs_product.* 
to 'user_ncs_product'@'%'
identified by 'rootroot';

use ncs_product;
select * from product;
select * from sale;
select * from sale_detail;



-- 3.
delimiter $$
drop trigger if exists tri_sale_after_insert_detail$$
create trigger tri_sale_after_insert_detail
after insert on sale
for each row
	begin
		insert into sale_detail(no,sale_price,addTax,supply_price,margin_price) values
		(new.no,
		new.price*new.saleCnt,
		ceiling(sale_price/11),
		sale_price-addTax,
		round(supply_price*new.marginRate/100)
		);
	end $$
delimiter ;


/**/

-- 4

delimiter $$
drop trigger if exists tri_sale_after_update_detail$$
create trigger tri_sale_after_update_detail
after update on sale
for each row
	begin
		update sale_detail set
		no=new.no,
		sale_price=new.price*new.saleCnt,
		addTax=ceiling(sale_price/11),
		supply_price=sale_price-addTax,
		margin_price=round(supply_price*new.marginRate/100)
		where no=old.no;
	end $$
delimiter ;



-- 5

delimiter $$
drop trigger if exists tri_sale_after_delete_detail$$
create trigger tri_sale_after_delete_detail
after delete on sale
for each row
	begin
		delete from sale_detail
		where no=old.no;
	end $$
delimiter ;


-- 6

DROP PROCEDURE IF EXISTS ncs_product.proc_saledetail_orderby_saleprice;

DELIMITER $$
$$
CREATE DEFINER=`user_ncs_product`@`localhost` PROCEDURE `ncs_product`.`proc_saledetail_orderby_saleprice`()
begin
	select
	(select count(*)+1 from sale_detail where sale_price > d.sale_price) no,product_code,product_name,price,saleCnt,d.sale_price,addTax,supply_price,marginrate,margin_price
	from sale s join sale_detail d
	using(no)
	join product p using(product_code)
	order by sale_price desc
	;

END$$
DELIMITER ;

call proc_saledetail_orderby_saleprice;



-- 7

DROP PROCEDURE IF EXISTS ncs_product.proc_saledetail_orderby_marginprice;

DELIMITER $$
$$
CREATE DEFINER=`user_ncs_product`@`localhost` PROCEDURE `ncs_product`.`proc_saledetail_orderby_marginprice`()
begin
	select
	(select count(*)+1 from sale_detail where margin_price > d.margin_price) no,product_code,product_name,price,saleCnt,d.sale_price,addTax,supply_price,marginrate,margin_price
	from sale s join sale_detail d
	using(no)
	join product p using(product_code)
	order by margin_price desc
	;

END$$
DELIMITER ;

call proc_saledetail_orderby_marginprice;


-- 8

DROP PROCEDURE IF EXISTS ncs_product.proc_sum;

DELIMITER $$
$$
CREATE DEFINER=`user_ncs_product`@`localhost` PROCEDURE `ncs_product`.`proc_sum`()
begin
	select sum(sale_price) sale_price, sum(addTax) addTax, sum(supply_price) supply_price, sum(margin_price) margin_price
	from sale_detail d join sale s on s.no=d.no
	;

END$$
DELIMITER ;

select * from sale_detail d join sale s on s.no=d.no;
call proc_sum;