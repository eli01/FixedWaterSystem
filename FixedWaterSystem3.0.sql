create database FixedWaterSystem
----------------订水系统数据库重建（在数据库已被创建的前提下）-------------------------
use FixedWaterSystem
go
--------------------（1）删除已有的数据库中的表格-------------------------------
drop table ProductShipping ---出货表
drop table orders--订单表
drop table Hydrotechnic--员工表
drop table client--客户表
drop table Buying--购进表
drop table Products--商品表
drop table users--用户表


drop table Supplier--供应商表


--------------------（2）创建新的数据库表-------------------------------
--1创建供应商表
create table Supplier(
SUID nvarchar(20) primary key,--供应商id
Sname nvarchar(30) not null,--供应商名称
Phone nvarchar(20) not null,--号码
Address nvarchar(50) not null,--地址
Postcode char(6) ,--邮政编码
QQ char(50) ,--QQ号
MSN char(50),
Remarks nvarchar(200) 
)
--2创建矿泉水分类表--
create table Products(--
PID nvarchar(20) primary key,--
Wname nchar(50) not null,--商品名称
Producttype nvarchar(50) ,--类型
SUID nvarchar(20) foreign key references Supplier(SUID),
Quantity int,
Description nvarchar(500) --描述
)
---3用户
create table users(
userid nvarchar(20) primary key,
username nvarchar(5),--用户名
userpasswd nvarchar(10),--用户密码
usergrade int,
usersex nchar(5),
userbg nvarchar(4),
userjob nvarchar(10),
userage int,--用户年龄
useradress nvarchar(20),--用户地址
useraser nvarchar(30),--用于设置的问题
useranswer nvarchar(30),--答案
userremind nvarchar(30),--密码提示
)
--4购进表
create table Buying(--
BID nvarchar(20) primary key,--进货号
SUID nvarchar(20) foreign key references Supplier(SUID),--供应商
PID nvarchar(20)  foreign key references Products(PID),--商品ID
BuyDate Smalldatetime,--购进日期 
PriceBuy smallmoney not null,--单价
Quantity nvarchar(10),
BSituation int not null,--是否付费（1为付费，-1为未付）          
Remarks nvarchar(200))--

--5创建客户表
create table client(
CID nvarchar(20) PRIMARY KEY,--1顾客id(卡号)
CName nvarchar(15) not null,--2客户名称
Phone nchar(30),--3联系电话
Adress nChar(30) not null,--4联系地址
ContactPerson nvarchar(8) ,--5联系人
Email nvarchar(20),--6电子邮箱
Birthday Smalldatetime,--7出生日期
JoinTime smallDatetime,--8加入时间
Grade nchar(5),--9客户级别
CType nchar(10),--10客户类型
Area nvarchar(20) ,--11片区名称
AreaType nchar(10),--12地区类型
ARegion nvarchar(10),--13关联区域
CarType nvarchar(10),--14送货类型
CFloor nvarchar(5),--15客户楼层
Effective nvarchar(5)not null,--16是否有效
Denominated nvarchar(10),--17结算单位
RecentServer nvarchar(20),--18最近服务
ProductModel nvarchar(10),--19产品型号
OftenPro nvarchar(10),--20常用产品名称
OftenQuantity int,--21常送数量
Remarks nChar(200))--22备注

--6员工表

create table Hydrotechnic(--
HID nvarchar(20) PRIMARY KEY,--送水工ID
Hname nvarchar(10) ,--名字
Phone nvarchar(20) ,--手机号
sex nchar(5),
job nvarchar(20),
pay smallmoney,
jointime smalldatetime,
education nvarchar(10),
address nvarchar(30),
Remarks nvarchar(200))--备注

--7订单
create table orders(
OID nvarchar(20) primary key ,--出售ID
CID nvarchar(20) foreign key references client(CID),--顾客id
HID nvarchar(20) foreign key references Hydrotechnic(HID),--送水工id
SDate SmallDateTime,--来电时间
Osituation int,--订单状态
CallNumber nvarchar(10),--来电号码
SendTime smalldatetime ,
Warehouse nvarchar(10),--出货仓库
OmonerySum money ,--金额
Oremarks nvarchar(100)--订单备注
)
-------8货物流水
create table ProductShipping(
SID nvarchar(20) primary key ,--货物流水号
OID nvarchar(20) foreign key references orders(OID), --订单流水号（业务单号）
PID nvarchar(20) foreign key references Products(PID),--商品id
Pname nvarchar(20),
specification nvarchar(10),--商品规格
unitPrice money ,--单价
quantity int,
biscount numeric(4,3),--折扣
monerySum money,--金额
Remarks nvarchar(200) ,--备注
)
--------------------（3）在表中创建初始化信息-------------------------------
insert into Supplier values('SUID2013390001','供应商1','0000','地址1','000000','000000001','','');
insert into Supplier values('SUID2013390002','供应商2','0001','地址2','000001','000000002','','');
insert into Supplier values('SUID2013390003','供应商3','0002','地址3','000002','000000003','','');
insert into Supplier values('SUID2013390004','供应商4','0003','地址4','000003','000000004','','');
insert into Supplier values('SUID2013390005','供应商5','0004','地址5','000004','000000005','','');
insert into Supplier values('SUID2013390006','供应商6','0005','地址6','000005','000000006','','');
insert into Supplier values('SUID2013390007','供应商7','0006','地址7','000006','000000007','','');
insert into Supplier values('SUID2013390008','供应商8','0007','地址8','000007','000000008','','');
insert into Supplier values('SUID2013390009','供应商9','0008','地址9','000008','000000009','','');

insert into Products values('P2013310001','哇哈哈','矿泉水','SUID2013390001','100','优质')
insert into Products values('P2013310002','农夫山泉','矿泉水','SUID2013390002','100','优质')
insert into Products values('P2013310003','康师傅','纯净水','SUID2013390003','100','优质')

insert into users values('u0001','老孙','truman','1','男','1','系统管理员','22','湖南湘潭大学','时代精神网站？','www.thezeitgeistmovement.com','.com中国官网')
insert into users values('u0002','neo','truman','2','男','2','经理','25','湖南长沙','维纳斯计划？','www.thevenusproject.com','金星计划网站')
insert into users values('u0003','小陈','truman','3','女','3','会计','25','湖南长沙','自由能源','凯史基金会','百度一下你就知道')
insert into users values('u0004','小李','truman','4','男','4','仓库管理员','35','湖南湘潭','自由能源','凯史基金会','百度一下你就知道')
insert into users values('u0005','小蒋','truman','5','男','5','接线员','19','湖南长沙','自由能源','凯史基金会','百度一下你就知道')



insert into Buying values('B2013310001','SUID2013390001','P2013310001',getDate(),'5.00','100','1','货物有损坏');
insert into Buying values('B2013310002','SUID2013390002','P2013310002',getDate(),'5.00','100','1','');
insert into Buying values('B2013310003','SUID2013390003','P2013310003',getDate(),'5.00','100','1','');

insert into client values('C201212210001','南苑一栋777','18207321605','南苑一栋777','孟同学','929020238@qq.com','',getDate(),'普通','学生宿舍','雨湖区','居民区','无','汽车','4','1','南苑一栋407寝室','7桶纯净水','10加仑','纯净水','7','This man case from earth.')
insert into client values('C201212210002','金翰林十栋999','18888888888','南苑一栋999','李同学','ebella365@gmail.cn','',getDate(),'普通','学生宿舍','雨湖区','居民区','无','汽车','4','1','金翰林十栋329','2桶纯净水','10加仑','纯净水','2','寝室常没人.')
insert into client values('C201212210000','初始化','00000000000','广东香山县','孙先生','ebella365@gmail.cn','',getDate(),'VIP','家庭','中山市','城区','无','汽车','4','1','香港西医书院','2桶纯净水','10加仑','纯净水','2','客户很忙.')

insert into Hydrotechnic values('H2013310001','小孟','18207321605','男','送水工','3000','','高中','湘潭大学','本科');
insert into Hydrotechnic values('H2013310002','老孙','18207321607','男','系统管理员','7000','','研究生','湘潭大学','本科');
insert into Hydrotechnic values('H2013310003','neo','18207321608','男','经理','7000',getDate(),'本科','湘潭大学','本科');
insert into Hydrotechnic values('H2013310004','小陈','18207321609','女','会计','3000',getDate(),'高中','湘潭大学','本科');
insert into Hydrotechnic values('H2013310005','小李','18207321601','男','仓库管理员','3000',getDate(),'高中','湘潭大学','本科');
insert into Hydrotechnic values('H2013310006','小蒋','18207321602','男','接线员','3000',getDate(),'高中','湘潭大学','本科');
insert into Hydrotechnic values('H2013310007','大刘','18207321644','男','送水工','3000',getDate(),'高中','北区','本科');
insert into Hydrotechnic values('H2013310008','猴子','18207321655','男','送水工','3000',getDate(),'高中','东区','本科');
insert into Hydrotechnic values('H2013310009','孙俪','18207321444','女','会计','3000',getDate(),'高中','湘潭大学','数学专业，专业很强');

insert into orders values('O2013351757420001','C201212210000','H2013310001',getDate(),'0','22222222',getDate(),'2','200','客户抱怨迟到')
insert into ProductShipping values('S201212210000001','O2013351757420001','P2013310001','哇哈哈','10.0','9.00','0','1.000','0.00','货源充足');
insert into ProductShipping values('S201212210000002','O2013351757420001','P2013310002','农夫山泉','10.0','9.00','0','1.000','0.00','货源充足');
insert into ProductShipping values('S201212210000003','O2013351757420001','P2013310003','康师傅','10.0','9.00','0','1.000','0.00','货源充足');


insert into orders values('O2013351757420002','C201212210001','H2013310001',getDate(),'0','22222222',getDate(),'2','200','客户抱怨迟到')
insert into ProductShipping values('S201212210000004','O2013351757420002','P2013310001','哇哈哈','10.0','9.00','7','1.000','63.00','货源充足');
insert into ProductShipping values('S201212210000005','O2013351757420002','P2013310002','农夫山泉','10.0','9.00','1','1.000','9.00','货源充足');
insert into ProductShipping values('S201212210000006','O2013351757420002','P2013310003','康师傅','10.0','9.00','2','1.000','18.00','货源充足');

insert into orders values('O2013351757420003','C201212210002','H2013310001',getDate(),'0','22222222',getDate(),'2','200','客户抱怨迟到')
insert into ProductShipping values('S201212210000007','O2013351757420003','P2013310001','哇哈哈','10.0','9.00','7','1.000','63.00','货源充足');
insert into ProductShipping values('S201212210000008','O2013351757420003','P2013310002','农夫山泉','10.0','9.00','1','1.000','9.00','货源充足');
insert into ProductShipping values('S201212210000009','O2013351757420003','P2013310003','康师傅','10.0','9.00','2','1.000','18.00','货源充足');

insert into orders values('O2013351757420004','C201212210001','H2013310001',getDate(),'0','22222222',getDate(),'2','200','客户抱怨迟到')
insert into ProductShipping values('S2012122100000010','O2013351757420004','P2013310001','哇哈哈','10.0','9.00','0','1.000','0.00','货源充足');
insert into ProductShipping values('S2012122100000011','O2013351757420004','P2013310002','农夫山泉','10.0','9.00','1','1.000','9.00','货源充足');
insert into ProductShipping values('S2012122100000012','O2013351757420004','P2013310003','康师傅','10.0','9.00','2','1.000','18.00','货源充足');
---------------------------------------------------
select * from ProductShipping
select * from orders
select * from users
select * from client
delete from ProductShipping where	OID='O2013351757420004'
delete from orders where OID='O2013351757420004'

select OID 业务单号,CID 客户卡号,SendTime 送货时间,OmonerySum 金额,Warehouse 仓库 from orders where CallNumber='18207321605' order by SendTime desc