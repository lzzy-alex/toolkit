-- SQL SERVER
declare @start_date varchar(20), @end_date varchar(20)
set @start_date = '2015-01-01'
set @end_date = '2016-12-30'

select y 'year', sum(month1) 'month1', sum(month2) 'month2',sum(month3) 'month3', sum(month4) 'month4',	
			sum(month5) 'month5', sum(month6) 'month6',sum(month7) 'month7', sum(month8) 'month8',
			sum(month9) 'month9', sum(month10) 'month10',sum(month11) 'month11', sum(month12) 'month12' 
from (
	select y, case when m=1 then c else 0 end 'month1', case when m=2 then c else 0 end 'month2', case when m=3 then c else 0 end 'month3',
		case when m=4 then c else 0 end 'month4', case when m=5 then c else 0 end 'month5', case when m=6 then c else 0 end 'month6',
	    case when m=7 then c else 0 end 'month7', case when m=8 then c else 0 end 'month8', case when m=9 then c else 0 end 'month9',
	    case when m=10 then c else 0 end 'month10', case when m=11 then c else 0 end 'month11', case when m=12 then c else 0 end 'month12'
	from(
	    select  year(cast(sys_submit_date as datetime)) as 'y', month(cast(sys_submit_date as datetime)) as 'm', count(*) as 'c'
	    from jet_project_jqhrzxsqs_data where cast(sys_submit_date as datetime) >= cast(@start_date as datetime) 
	    and cast(sys_submit_date as datetime) <= cast(@end_date as datetime)
	    group by year(cast(sys_submit_date as datetime)), month(cast(sys_submit_date as datetime))
	) as t1
) as t2 group by y
