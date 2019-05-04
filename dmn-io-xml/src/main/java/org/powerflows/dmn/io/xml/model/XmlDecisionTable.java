/*
 * Copyright (c) 2018-present PowerFlows.org - all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.powerflows.dmn.io.xml.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "decisionTable", namespace = "http://www.omg.org/spec/DMN/20151101/dmn.xsd")
@Data
@NoArgsConstructor
public class XmlDecisionTable {

    @XmlAttribute
    private String hitPolicy;

    @XmlAttribute
    private String id;

    @XmlAnyAttribute
    private Map<QName, String> anyAttributes = new HashMap<>();

    @XmlElement(name = "input")
    private List<XmlInput> inputs = new ArrayList<>();

    @XmlElement(name = "output")
    private List<XmlOutput> outputs = new ArrayList<>();

    @XmlElement(name = "rule")
    private List<XmlRule> rules = new ArrayList<>();
}