/* 
	Tested : sql server 2000
*/

if (object_id('sp_foo', 'P') is not null)
    drop proc sp_foo
go

create proc sp_foo
	@id varchar(20)
as

begin
	
	declare @uname varchar(20)
	declare @tb varchar(40)
	declare @sql varchar(512)
	
	create table #tmp(
		id varchar(20),
		uname varchar(20)
	)

	declare cursor_1 cursor
	for select uname, ref_table from tb_user
	open cursor_1
	fetch next from cursor_1 into @uname, @tb

	while(@@fetch_status=0)
	begin
		set @sql = 'select app_id, vname from ' + @tb +' where fk_id=''' +@id+''''
		insert into #tmp(id, uname) exec(@sql)
		fetch next from cursor_1 into @uname, @tb
	end
	
	close cursor_1
	deallocate cursor_1

	select * from #tmp
	drop table #tmp
	
end


