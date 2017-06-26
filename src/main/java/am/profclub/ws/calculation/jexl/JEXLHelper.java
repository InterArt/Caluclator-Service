package am.profclub.ws.calculation.jexl;

/**
 * Created by admin on 6/23/17.
 */
public class JEXLHelper extends JEXLExpressionHelper{

	private static JEXLHelper __instance;

	private JEXLHelper(){
	}

	public static JEXLHelper getInstance(){
		if( __instance == null ){
			__instance = new JEXLHelper();
		}
		return __instance;
	}
}
