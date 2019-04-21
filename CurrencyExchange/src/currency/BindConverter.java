package currency;

public class BindConverter extends org.jdesktop.beansbinding.Converter<String, String>{
	
	@Override
	public String convertForward(String arg0) {
		String result = "";
		result = arg0.replaceAll("\\D+","");
		return result;
	}

	@Override
	public String convertReverse(String arg0) {
		return arg0;
	}

}
