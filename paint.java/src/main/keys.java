package main;

import java.util.HashMap;

public class keys {
	static keys KEYS=new keys();
	HashMap<Integer,Boolean> keyCheck = new HashMap<>();
	
	keys(){
		for(Integer i=0;i<300;i++) {
			setkey(i, false);
		}
	}
	
	public Boolean getkey(Integer s) {
		Boolean c=keyCheck.get(s);
		if(c==null) {
			setkey(s,false);
			
		}
		return keyCheck.get(s);
	}
	public Boolean keyCombo(Integer[] s) {
		
		for(Integer i : s) {
			Boolean b=getkey(i);
			if(b==false) {
				return false;
			}
		}
		return true;
	}
	
	public void setkey(Integer s,Boolean b) {
		keyCheck.put(s, b);
	}
	
	public  static keys getKeys() {
		return KEYS;
	}
}
