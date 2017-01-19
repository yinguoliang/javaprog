package com.elva;



class PPP{
    private String value ;
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
public class Hello {
    public void deal(PPP p) throws Exception{
        p = new PPP();
        p.setValue("HHHHHH");
        throw new Exception();
    }
	public void sayHello(){
	    final PPP p = new PPP();
	    try{
	        deal(p);
	    }catch(Exception ex){
	        
	    }
	    System.out.println(p.getValue());
	}
	public static void main(String[] args) {
	    
	    new Hello().sayHello();
	}

}
