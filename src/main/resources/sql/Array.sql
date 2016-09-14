-- SQL Server


-- 求数组长度
-- select dbo.arr_len('a_b_c', '_')
-- drop function arr_len
create function arr_len 
(
	@str varchar(1024),
	@split varchar(10)
) returns int
as
begin

declare @location int
declare @start int
declare @length int

set @str = ltrim(rtrim(@str))
set @location = charindex(@split,@str)
set @length = 1

while @location <> 0
begin
	set @start = @location+1
	set @location = charindex(@split,@str,@start)
	set @length = @length+1
end

return @length
end

-- 取数组某位置的值
-- select dbo.arr_val('a_b_c','_',2)
-- drop function arr_val
create function arr_val(
	@str varchar(1024),
	@separator varchar(10),
	@pos int
) returns varchar(1024)
as
begin

declare @location int
declare @start int
declare @next int
declare @seed int

set @str = ltrim(rtrim(@str))
set @start = 1
set @next = 1
set @seed = len(@separator)

set @location = charindex(@separator,@str)
while @location <> 0 and @pos > @next
begin
	set @start = @location+@seed
	set @location = charindex(@separator,@str,@start)
	set @next = @next + 1
end
if @location =0 select @location =len(@str)+1
return substring(@str,@start,@location-@start)
end