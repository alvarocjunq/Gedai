/**
token:     description:             example:
#YYYY#     4-digit year             1999
#YY#       2-digit year             99
#MMMM#     full month name          February
#MMM#      3-letter month name      Feb
#MM#       2-digit month number     02
#M#        month number             2
#DDDD#     full weekday name        Wednesday
#DDD#      3-letter weekday name    Wed
#DD#       2-digit day number       09
#D#        day number               9
#th#       day ordinal suffix       nd
#hhhh#     2-digit 24-based hour    17
#hhh#      military/24-based hour   17
#hh#       2-digit hour             05
#h#        hour                     5
#mm#       2-digit minute           07
#m#        minute                   7
#ss#       2-digit second           09
#s#        second                   9
#ampm#     "am" or "pm"             pm
#AMPM#     "AM" or "PM"             PM
 */

function customFormat(dataParam, formatString){
  var data = new Date(dataParam);
  var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
	  YY = ((YYYY=data.getFullYear())+"").slice(-2);
	  MM = (M=data.getMonth()+1)<10?('0'+M):M;
	  MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
	  DD = (D=data.getDate())<10?('0'+D):D;
	  DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][data.getDay()]).substring(0,3);
	  th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
	  formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
	  h=(hhh=data.getHours());
	  if (h==0) h=24;
	  if (h>12) h-=12;
	  hh = h<10?('0'+h):h;
	  hhhh = h<10?('0'+hhh):hhh;
	  AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
	  mm=(m=data.getMinutes())<10?('0'+m):m;
	  ss=(s=data.getSeconds())<10?('0'+s):s;
  return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
};


function getDateDiff(a, b){
	var date1 = new Date(a);
	var date2 = new Date(b);
	var diferenca = Math.abs(date1 - date2); //diferença em milésimos e positivo
	var dia = 1000*60*60*24; // milésimos de segundo correspondente a um dia
	return Math.round(diferenca/dia); //valor total de dias arredondado 
}