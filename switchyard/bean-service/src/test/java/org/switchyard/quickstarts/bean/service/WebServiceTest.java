/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.switchyard.quickstarts.bean.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(
    mixins = { CDIMixIn.class, HTTPMixIn.class },
    config = SwitchYardTestCaseConfig.SWITCHYARD_XML,
    scanners = TransformSwitchYardScanner.class)
public class WebServiceTest {

    private HTTPMixIn httpMixIn;

    @BeforeDeploy
    public void setProperties() {
        System.setProperty("org.switchyard.component.http.standalone.port", "8081");
    }

    @Test
    public void invokeOrderWebService() throws Exception {
        // Use the HttpMixIn to invoke the SOAP binding endpoint with a SOAP input (from the test classpath)
        // and compare the SOAP response to a SOAP response resource (from the test classpath)...
        httpMixIn.setContentType("application/soap+xml");
        httpMixIn.postResourceAndTestXML("http://localhost:8081/quickstart-bean/OrderService", "/xml/soap-request.xml", "/xml/soap-response.xml");
    }
}
