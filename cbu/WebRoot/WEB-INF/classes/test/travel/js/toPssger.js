function toPssger(o){
	if(o && o.status && o.data.isExist){
		var ps=o.data.normal_passengers||[];
		var pa=pssger.getJSArray();
		for(var i=0;i<ps.length;i++){
			var t=ps[i]; 
			var p=pssger.getPss(t.passenger_id_no,t.passenger_name,t.mobile_no,t.email,t.code,t.sex_code);
			pa.push(p);
		}
		return pa;
	}
	return null;
}
toPssger(psa);