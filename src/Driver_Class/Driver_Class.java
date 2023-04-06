
package Driver_Class;

import java.io.IOException;

import Test_Class.GET_TC1;
import Test_Class.PATCH_TC1;
import Test_Class.POST_TC1;
import Test_Class.PUT_TC1;

public class Driver_Class {

	public static void main(String[] args) throws IOException {
		//TODO Auto-generated method stub
		POST_TC1.orchestrator();
		PUT_TC1.orchestrator();
		PATCH_TC1.orchestrator();
		//GET_TC1.orchestrator();
	}

}
