

insert into ncs_product.product(product_code,product_name) values
('A001','¾Æ¸Þ¸®Ä«³ë'),
('A002','Ä«ÇªÄ¡³ë'),
('A003','ÇìÀÌÁñ³Ó'),
('A004','¿¡½ºÇÁ·¹¼Ò'),
('B001','µþ±â½¦ÀÌÅ©'),
('B002','ÈÄ¸£Ã÷¿ÍÀÎ'),
('B003','ÆÏºù¼ö'),
('B004','¾ÆÀÌ½ºÃÊÄÚ');





insert into sale values
('1','A001','4500',150,10),
('2','A002','3800',140,15),
('3','B001','5200',250,12),
('4','B002','4300',110,11);



delete from product;
delete from sale;
delete from sale_detail;
select * from sale;
select * from sale_detail;


select * from sale s join sale_detail d using(no);


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



update sale set saleCnt=100 where no=1;
select * from sale;

select * from sale s join sale_detail d using(no);

update sale set saleCnt=150 where no=1;

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

delete from sale where no=4;

select * from sale s join sale_detail d using(no);
select * from sale;
select * from sale_detail;

insert into sale values
('4','B002','4300',110,11);

select * from sale s join sale_detail d using(no);


-- 6

DROP PROCEDURE IF EXISTS ncs_product.proc_saledetail_orderby_saleprice;

DELIMITER $$
$$
CREATE DEFINER=`user_ncs_product`@`localhost` PROCEDURE `ncs_product`.`proc_saledetail_orderby_saleprice`()
begin
	select
	(select count(*)+1 from sale_detail where sale_price > d.sale_price),product_code,product_name,price,saleCnt,d.sale_price,addTax,supply_price,marginrate,margin_price
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
	(select count(*)+1 from sale_detail where margin_price > d.margin_price),product_code,product_name,price,saleCnt,d.sale_price,addTax,supply_price,marginrate,margin_price
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
	select count(s.no) s.no,sum(s.price) price,sum(s.saleCnt) saleCnt, sum(addTax) addTax, sum(supply_price) supply_price, sum(sale_price) sale_price,sum(margin_price) margin_price
	from sale_detail d join sale s on s.no=d.no;
END$$
DELIMITER ;

select * from sale_detail d join sale s on s.no=d.no;
call proc_sum();
