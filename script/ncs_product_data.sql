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


insert into ncs_product.product(product_code,product_name) values
('A001','�Ƹ޸�ī��'),
('A002','īǪġ��'),
('A003','�������'),
('A004','����������'),
('B001','���⽦��ũ'),
('B002','�ĸ�������'),
('B003','�Ϻ���'),
('B004','���̽�����');


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
	(select count(*)+1 from sale where price > s.price),product_code,product_name,price,saleCnt,d.sale_price,addTax,supply_price,marginrate,margin_price
	from sale s join sale_detail d
	using(no)
	join product p using(product_code)
	order by price desc
	;

END$$
DELIMITER ;

call proc_saledetail_orderby_saleprice;
(select count(*)+1 from sale where price > s.price) as ����,product_code �ڵ�,product_name ��ǰ��,price ����,saleCnt �Ǹż���,d.sale_price �Ǹž�,addTax �ΰ�����,supply_price ���ް���,marginrate ������,margin_price
-- 7

DROP PROCEDURE IF EXISTS ncs_product.proc_saledetail_orderby_marginprice;

DELIMITER $$
$$
CREATE DEFINER=`user_ncs_product`@`localhost` PROCEDURE `ncs_product`.`proc_saledetail_orderby_marginprice`()
begin
	select
	(select count(*)+1 from sale_detail where margin_price > d.margin_price) as ����,product_code �ڵ�,product_name ��ǰ��,price ����,saleCnt �Ǹż���,d.sale_price �Ǹž�,addTax �ΰ�����,supply_price ���ް���,marginrate ������,margin_price ������
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
	select sum(sale_price) �Ǹűݾ�, sum(addTax) �ΰ�����, sum(supply_price) ���ް���, sum(margin_price) ������
	from sale_detail
	;

END$$
DELIMITER ;

select * from sale_detail;
call proc_sum;
