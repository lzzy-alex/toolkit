/* 
	Tested : sql server 2000
	Tags:	    year(), 	month(), 	group by, 		case when
*/

declare @s datetime
declare @e datetime
set @s = cast('2015-01-01' as datetime)
set @e = cast('2016-12-30' as datetime)

select		y '年度',
				sum(month1) '一月',    sum(month2) '二月',    sum(month3) '三月',    sum(month4) '四月',	
				sum(month5) '五月',    sum(month6) '六月',    sum(month7) '七月',    sum(month8) '八月',
				sum(month9) '九月',    sum(month10) '十月',  sum(month11) '十一',  sum(month12) '十二' 
from (
	select y,case when m=1   then c else 0 end 'month1',     case when m=2 then c else 0 end 'month2',      case when m=3 then c else 0 end 'month3',
		           case when m=4   then c else 0 end 'month4',     case when m=5 then c else 0 end 'month5',      case when m=6 then c else 0 end 'month6',
	               case when m=7   then c else 0 end 'month7',     case when m=8 then c else 0 end 'month8',      case when m=9 then c else 0 end 'month9',
	               case when m=10 then c else 0 end 'month10',  case when m=11 then c else 0 end 'month11',  case when m=12 then c else 0 end 'month12'
	from(
	    select  year(created) as 'y', month(created) as 'm', count(*) as 'c'
	    from tb where created >= @s and created <= @e group by year(created), month(created)
	) as t1
) as t2 group by y
