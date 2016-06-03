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
package org.switchyard.quickstarts.jca.outbound;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

/**
 * OrderServiceBean.
 * 
 * @author <a href="mailto:tm.igarashi@gmail.com">Tomohisa Igarashi</a>
 *
 */
@Service(OrderService.class)
public class OrderServiceBean implements OrderService {

    @Inject
    @Reference("ShippingReference")
    private OrderService _shipping;
    @Inject
    @Reference("FillingStockReference")
    private OrderService _fillingStock;

    private List<String> _stock = Arrays.asList("BREAD", "BUTTER", "JAM", "EGG", "MILK");

    @Override
    public void process(String order) {
        if (_stock.contains(order)) {
            _shipping.process(order);
        } else {
            _fillingStock.process(order);
        }
    }

}