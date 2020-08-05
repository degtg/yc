package thread.d0730;

public class Cookie {

	private String name;
	private String value;
	private int maxAge=-1;
	public Cookie(String name, String value) {
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		String s="Set-Cookie: %s=%s;\n";
		s=String.format(s,name, value);
		if(maxAge>-1) {
			s+="Max-Age="+maxAge+";";
		}
		
		s+="\n";
		System.out.println("s="+s);
		return s;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		System.out.println("======李四的代码======");
		this.maxAge=maxAge;
	}
	
}
