create database FixedWaterSystem
----------------��ˮϵͳ���ݿ��ؽ��������ݿ��ѱ�������ǰ���£�-------------------------
use FixedWaterSystem
go
--------------------��1��ɾ�����е����ݿ��еı��-------------------------------
drop table ProductShipping ---������
drop table orders--������
drop table Hydrotechnic--Ա����
drop table client--�ͻ���
drop table Buying--������
drop table Products--��Ʒ��
drop table users--�û���


drop table Supplier--��Ӧ�̱�


--------------------��2�������µ����ݿ��-------------------------------
--1������Ӧ�̱�
create table Supplier(
SUID nvarchar(20) primary key,--��Ӧ��id
Sname nvarchar(30) not null,--��Ӧ������
Phone nvarchar(20) not null,--����
Address nvarchar(50) not null,--��ַ
Postcode char(6) ,--��������
QQ char(50) ,--QQ��
MSN char(50),
Remarks nvarchar(200) 
)
--2������Ȫˮ�����--
create table Products(--
PID nvarchar(20) primary key,--
Wname nchar(50) not null,--��Ʒ����
Producttype nvarchar(50) ,--����
SUID nvarchar(20) foreign key references Supplier(SUID),
Quantity int,
Description nvarchar(500) --����
)
---3�û�
create table users(
userid nvarchar(20) primary key,
username nvarchar(5),--�û���
userpasswd nvarchar(10),--�û�����
usergrade int,
usersex nchar(5),
userbg nvarchar(4),
userjob nvarchar(10),
userage int,--�û�����
useradress nvarchar(20),--�û���ַ
useraser nvarchar(30),--�������õ�����
useranswer nvarchar(30),--��
userremind nvarchar(30),--������ʾ
)
--4������
create table Buying(--
BID nvarchar(20) primary key,--������
SUID nvarchar(20) foreign key references Supplier(SUID),--��Ӧ��
PID nvarchar(20)  foreign key references Products(PID),--��ƷID
BuyDate Smalldatetime,--�������� 
PriceBuy smallmoney not null,--����
Quantity nvarchar(10),
BSituation int not null,--�Ƿ񸶷ѣ�1Ϊ���ѣ�-1Ϊδ����          
Remarks nvarchar(200))--

--5�����ͻ���
create table client(
CID nvarchar(20) PRIMARY KEY,--1�˿�id(����)
CName nvarchar(15) not null,--2�ͻ�����
Phone nchar(30),--3��ϵ�绰
Adress nChar(30) not null,--4��ϵ��ַ
ContactPerson nvarchar(8) ,--5��ϵ��
Email nvarchar(20),--6��������
Birthday Smalldatetime,--7��������
JoinTime smallDatetime,--8����ʱ��
Grade nchar(5),--9�ͻ�����
CType nchar(10),--10�ͻ�����
Area nvarchar(20) ,--11Ƭ������
AreaType nchar(10),--12��������
ARegion nvarchar(10),--13��������
CarType nvarchar(10),--14�ͻ�����
CFloor nvarchar(5),--15�ͻ�¥��
Effective nvarchar(5)not null,--16�Ƿ���Ч
Denominated nvarchar(10),--17���㵥λ
RecentServer nvarchar(20),--18�������
ProductModel nvarchar(10),--19��Ʒ�ͺ�
OftenPro nvarchar(10),--20���ò�Ʒ����
OftenQuantity int,--21��������
Remarks nChar(200))--22��ע

--6Ա����

create table Hydrotechnic(--
HID nvarchar(20) PRIMARY KEY,--��ˮ��ID
Hname nvarchar(10) ,--����
Phone nvarchar(20) ,--�ֻ���
sex nchar(5),
job nvarchar(20),
pay smallmoney,
jointime smalldatetime,
education nvarchar(10),
address nvarchar(30),
Remarks nvarchar(200))--��ע

--7����
create table orders(
OID nvarchar(20) primary key ,--����ID
CID nvarchar(20) foreign key references client(CID),--�˿�id
HID nvarchar(20) foreign key references Hydrotechnic(HID),--��ˮ��id
SDate SmallDateTime,--����ʱ��
Osituation int,--����״̬
CallNumber nvarchar(10),--�������
SendTime smalldatetime ,
Warehouse nvarchar(10),--�����ֿ�
OmonerySum money ,--���
Oremarks nvarchar(100)--������ע
)
-------8������ˮ
create table ProductShipping(
SID nvarchar(20) primary key ,--������ˮ��
OID nvarchar(20) foreign key references orders(OID), --������ˮ�ţ�ҵ�񵥺ţ�
PID nvarchar(20) foreign key references Products(PID),--��Ʒid
Pname nvarchar(20),
specification nvarchar(10),--��Ʒ���
unitPrice money ,--����
quantity int,
biscount numeric(4,3),--�ۿ�
monerySum money,--���
Remarks nvarchar(200) ,--��ע
)
--------------------��3���ڱ��д�����ʼ����Ϣ-------------------------------
insert into Supplier values('SUID2013390001','��Ӧ��1','0000','��ַ1','000000','000000001','','');
insert into Supplier values('SUID2013390002','��Ӧ��2','0001','��ַ2','000001','000000002','','');
insert into Supplier values('SUID2013390003','��Ӧ��3','0002','��ַ3','000002','000000003','','');
insert into Supplier values('SUID2013390004','��Ӧ��4','0003','��ַ4','000003','000000004','','');
insert into Supplier values('SUID2013390005','��Ӧ��5','0004','��ַ5','000004','000000005','','');
insert into Supplier values('SUID2013390006','��Ӧ��6','0005','��ַ6','000005','000000006','','');
insert into Supplier values('SUID2013390007','��Ӧ��7','0006','��ַ7','000006','000000007','','');
insert into Supplier values('SUID2013390008','��Ӧ��8','0007','��ַ8','000007','000000008','','');
insert into Supplier values('SUID2013390009','��Ӧ��9','0008','��ַ9','000008','000000009','','');

insert into Products values('P2013310001','�۹���','��Ȫˮ','SUID2013390001','100','����')
insert into Products values('P2013310002','ũ��ɽȪ','��Ȫˮ','SUID2013390002','100','����')
insert into Products values('P2013310003','��ʦ��','����ˮ','SUID2013390003','100','����')

insert into users values('u0001','����','truman','1','��','1','ϵͳ����Ա','22','������̶��ѧ','ʱ��������վ��','www.thezeitgeistmovement.com','.com�й�����')
insert into users values('u0002','neo','truman','2','��','2','����','25','���ϳ�ɳ','ά��˹�ƻ���','www.thevenusproject.com','���Ǽƻ���վ')
insert into users values('u0003','С��','truman','3','Ů','3','���','25','���ϳ�ɳ','������Դ','��ʷ�����','�ٶ�һ�����֪��')
insert into users values('u0004','С��','truman','4','��','4','�ֿ����Ա','35','������̶','������Դ','��ʷ�����','�ٶ�һ�����֪��')
insert into users values('u0005','С��','truman','5','��','5','����Ա','19','���ϳ�ɳ','������Դ','��ʷ�����','�ٶ�һ�����֪��')



insert into Buying values('B2013310001','SUID2013390001','P2013310001',getDate(),'5.00','100','1','��������');
insert into Buying values('B2013310002','SUID2013390002','P2013310002',getDate(),'5.00','100','1','');
insert into Buying values('B2013310003','SUID2013390003','P2013310003',getDate(),'5.00','100','1','');

insert into client values('C201212210001','��Էһ��777','18207321605','��Էһ��777','��ͬѧ','929020238@qq.com','',getDate(),'��ͨ','ѧ������','�����','������','��','����','4','1','��Էһ��407����','7Ͱ����ˮ','10����','����ˮ','7','This man case from earth.')
insert into client values('C201212210002','����ʮ��999','18888888888','��Էһ��999','��ͬѧ','ebella365@gmail.cn','',getDate(),'��ͨ','ѧ������','�����','������','��','����','4','1','����ʮ��329','2Ͱ����ˮ','10����','����ˮ','2','���ҳ�û��.')
insert into client values('C201212210000','��ʼ��','00000000000','�㶫��ɽ��','������','ebella365@gmail.cn','',getDate(),'VIP','��ͥ','��ɽ��','����','��','����','4','1','�����ҽ��Ժ','2Ͱ����ˮ','10����','����ˮ','2','�ͻ���æ.')

insert into Hydrotechnic values('H2013310001','С��','18207321605','��','��ˮ��','3000','','����','��̶��ѧ','����');
insert into Hydrotechnic values('H2013310002','����','18207321607','��','ϵͳ����Ա','7000','','�о���','��̶��ѧ','����');
insert into Hydrotechnic values('H2013310003','neo','18207321608','��','����','7000',getDate(),'����','��̶��ѧ','����');
insert into Hydrotechnic values('H2013310004','С��','18207321609','Ů','���','3000',getDate(),'����','��̶��ѧ','����');
insert into Hydrotechnic values('H2013310005','С��','18207321601','��','�ֿ����Ա','3000',getDate(),'����','��̶��ѧ','����');
insert into Hydrotechnic values('H2013310006','С��','18207321602','��','����Ա','3000',getDate(),'����','��̶��ѧ','����');
insert into Hydrotechnic values('H2013310007','����','18207321644','��','��ˮ��','3000',getDate(),'����','����','����');
insert into Hydrotechnic values('H2013310008','����','18207321655','��','��ˮ��','3000',getDate(),'����','����','����');
insert into Hydrotechnic values('H2013310009','��ٳ','18207321444','Ů','���','3000',getDate(),'����','��̶��ѧ','��ѧרҵ��רҵ��ǿ');

insert into orders values('O2013351757420001','C201212210000','H2013310001',getDate(),'0','22222222',getDate(),'2','200','�ͻ���Թ�ٵ�')
insert into ProductShipping values('S201212210000001','O2013351757420001','P2013310001','�۹���','10.0','9.00','0','1.000','0.00','��Դ����');
insert into ProductShipping values('S201212210000002','O2013351757420001','P2013310002','ũ��ɽȪ','10.0','9.00','0','1.000','0.00','��Դ����');
insert into ProductShipping values('S201212210000003','O2013351757420001','P2013310003','��ʦ��','10.0','9.00','0','1.000','0.00','��Դ����');


insert into orders values('O2013351757420002','C201212210001','H2013310001',getDate(),'0','22222222',getDate(),'2','200','�ͻ���Թ�ٵ�')
insert into ProductShipping values('S201212210000004','O2013351757420002','P2013310001','�۹���','10.0','9.00','7','1.000','63.00','��Դ����');
insert into ProductShipping values('S201212210000005','O2013351757420002','P2013310002','ũ��ɽȪ','10.0','9.00','1','1.000','9.00','��Դ����');
insert into ProductShipping values('S201212210000006','O2013351757420002','P2013310003','��ʦ��','10.0','9.00','2','1.000','18.00','��Դ����');

insert into orders values('O2013351757420003','C201212210002','H2013310001',getDate(),'0','22222222',getDate(),'2','200','�ͻ���Թ�ٵ�')
insert into ProductShipping values('S201212210000007','O2013351757420003','P2013310001','�۹���','10.0','9.00','7','1.000','63.00','��Դ����');
insert into ProductShipping values('S201212210000008','O2013351757420003','P2013310002','ũ��ɽȪ','10.0','9.00','1','1.000','9.00','��Դ����');
insert into ProductShipping values('S201212210000009','O2013351757420003','P2013310003','��ʦ��','10.0','9.00','2','1.000','18.00','��Դ����');

insert into orders values('O2013351757420004','C201212210001','H2013310001',getDate(),'0','22222222',getDate(),'2','200','�ͻ���Թ�ٵ�')
insert into ProductShipping values('S2012122100000010','O2013351757420004','P2013310001','�۹���','10.0','9.00','0','1.000','0.00','��Դ����');
insert into ProductShipping values('S2012122100000011','O2013351757420004','P2013310002','ũ��ɽȪ','10.0','9.00','1','1.000','9.00','��Դ����');
insert into ProductShipping values('S2012122100000012','O2013351757420004','P2013310003','��ʦ��','10.0','9.00','2','1.000','18.00','��Դ����');
---------------------------------------------------
select * from ProductShipping
select * from orders
select * from users
select * from client
delete from ProductShipping where	OID='O2013351757420004'
delete from orders where OID='O2013351757420004'

select OID ҵ�񵥺�,CID �ͻ�����,SendTime �ͻ�ʱ��,OmonerySum ���,Warehouse �ֿ� from orders where CallNumber='18207321605' order by SendTime desc