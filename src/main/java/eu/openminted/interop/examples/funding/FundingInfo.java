/*
 * Licensed to the Technische Universität Darmstadt under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The Technische Universität Darmstadt 
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.openminted.interop.examples.funding;

/**
 * OpenMinTeD Funding Info vocabulary.
 */
public class FundingInfo
{
    public static final String PREFIX_FI = "fi";
    
    public static final String NS_FI = "http://openminted.eu/ns/fi#";

    public static final String PROP_FUND = NS_FI + "fund";
    public static final String PROP_ACRONYM = NS_FI + "acronym";
    public static final String PROP_GRANT_ID = NS_FI + "grantId";
    public static final String PROP_CONFIDENCE = NS_FI + "confidence";
    public static final String PROP_EGI_RELATED = NS_FI + "egi-related";
    public static final String PROP_FUNDING_INFO = NS_FI + "funding-info";

    public static final String TYPE_FUNDING = NS_FI + "Funding";
    public static final String TYPE_RESULT = NS_FI + "Result";
}
