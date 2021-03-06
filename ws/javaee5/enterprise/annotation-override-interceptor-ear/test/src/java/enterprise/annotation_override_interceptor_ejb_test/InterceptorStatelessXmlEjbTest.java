/*
 	Copyright (c) 2019 Oracle and/or its affiliates. All rights reserved.
	
	This program and the accompanying materials are made available under the
	terms of the Eclipse Public License v. 2.0, which is available at
	http://www.eclipse.org/legal/epl-2.0.
	
	This Source Code may also be made available under the following Secondary
	Licenses when the conditions for such availability set forth in the
	Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
	version 2 with the GNU Classpath Exception, which is available at
	https://www.gnu.org/software/classpath/license.html.
	
	SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
*/
package enterprise.annotation_override_interceptor_ejb_test;

import java.util.List;

import org.junit.*;

import javax.naming.InitialContext;
import enterprise.annotation_override_interceptor_ejb.StatelessSession;
import enterprise.annotation_override_interceptor_ejb.BadArgumentException;

public class InterceptorStatelessXmlEjbTest {

    private static final String UPPER_LOWER_CASE_INTERCEPTOR_NAME_LIST =
	"NullChecker, ArgumentsChecker, StatelessSessionBean";

    private static final String IS_ODD_NUMBER_INTERCEPTOR_NAME_LIST =
	"NullChecker, StatelessSessionBean";

			
    @Test public void test1() {
        try {
            InitialContext ic = new InitialContext();
            String jndiName = "enterprise.annotation_override_interceptor_ejb.StatelessSession#"
                + "enterprise.annotation_override_interceptor_ejb.StatelessSession";

            StatelessSession sless = (StatelessSession) ic.lookup(jndiName);
            Assert.assertEquals("Failed to change case",
                sless.initUpperCase("duke"), "Duke");

	    List<String> interceptorNames = sless.getInterceptorNamesFor("initUpperCase");

	    String delimiter = "";
	    StringBuilder sbldr = new StringBuilder();
	    for (String interceptorName : interceptorNames) {
		sbldr.append(delimiter).append(interceptorName);
		delimiter = ", ";
	    }
	    Assert.assertEquals("Failed interceptor order for initUpperCase", 
		sbldr.toString(), UPPER_LOWER_CASE_INTERCEPTOR_NAME_LIST);
        } catch(Exception e) {
            e.printStackTrace();
            Assert.fail("encountered exception in InterceptorStatelessEjbTest:returnMessage");
        }
    }

    @Test public void test2() {
        try {
            InitialContext ic = new InitialContext();
            String jndiName = "enterprise.annotation_override_interceptor_ejb.StatelessSession#"
                + "enterprise.annotation_override_interceptor_ejb.StatelessSession";

            StatelessSession sless = (StatelessSession) ic.lookup(jndiName);
            Assert.assertEquals("Failed to change case",
                sless.initLowerCase("Duke"), "duke");

	    List<String> interceptorNames = sless.getInterceptorNamesFor("initLowerCase");

	    String delimiter = "";
	    StringBuilder sbldr = new StringBuilder();
	    for (String interceptorName : interceptorNames) {
		sbldr.append(delimiter).append(interceptorName);
		delimiter = ", ";
	    }
	    Assert.assertEquals("Failed interceptor order for initLowerCase", 
		sbldr.toString(), UPPER_LOWER_CASE_INTERCEPTOR_NAME_LIST);
        } catch(Exception e) {
            e.printStackTrace();
            Assert.fail("encountered exception in InterceptorStatelessEjbTest:returnMessage");
        }
    }

    @Test public void test3() {
        try {
            InitialContext ic = new InitialContext();
            String jndiName = "enterprise.annotation_override_interceptor_ejb.StatelessSession#"
                + "enterprise.annotation_override_interceptor_ejb.StatelessSession";

            StatelessSession sless = (StatelessSession) ic.lookup(jndiName);
            Assert.assertEquals("Failed to check isOddNumber",
                sless.isOddNumber(3), true);

	    List<String> interceptorNames = sless.getInterceptorNamesFor("isOddNumber");

	    String delimiter = "";
	    StringBuilder sbldr = new StringBuilder();
	    for (String interceptorName : interceptorNames) {
		sbldr.append(delimiter).append(interceptorName);
		delimiter = ", ";
	    }
	    Assert.assertEquals("Failed interceptor order for isOddNumber", 
		sbldr.toString(), IS_ODD_NUMBER_INTERCEPTOR_NAME_LIST);
        } catch(Exception e) {
            e.printStackTrace();
            Assert.fail("encountered exception in InterceptorStatelessEjbTest:returnMessage");
        }
    }

}
