package eu.openminted.interop.examples.funding;

import static java.util.Arrays.asList;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.SerialFormat;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.fit.util.FSUtil;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasIOUtils;

public class CreateFundingOutputXmi
{
    public static void main(String[] args)
        throws Exception
    {
        // Create empty type system to ensure uimaFIT does *not* pick up any TSD from the classpath
        TypeSystemDescription typeSystem = UIMAFramework.getResourceSpecifierFactory()
                .createTypeSystemDescription();

        // {"funding_info": [ {
        //   "fund": "FP7", 
        //   "acronym": "OPENAIREPLUS", 
        //   "grantid": "283595", 
        //   "confidence": 0.77, 
        //   "EGI-related": false 
        //  }, {
        //    "fund": "FP7", 
        //    "acronym": "OPTIQUE", 
        //    "grantid": "318338", 
        //    "confidence": 0.92, 
        //    "EGI-related": false
        //  }]}        
        
        TypeDescription fundingTypeDesc = typeSystem.addType(
                "eu.openminted.interop.example.funding.Funding", 
                "no description", 
                CAS.TYPE_NAME_ANNOTATION_BASE);
        fundingTypeDesc.addFeature("fund", "no description", CAS.TYPE_NAME_STRING);
        fundingTypeDesc.addFeature("acronym", "no description", CAS.TYPE_NAME_STRING);
        fundingTypeDesc.addFeature("grantid", "no description", CAS.TYPE_NAME_STRING);
        fundingTypeDesc.addFeature("confidence", "no description", CAS.TYPE_NAME_DOUBLE);
        fundingTypeDesc.addFeature("EGI_related", "no description", CAS.TYPE_NAME_BOOLEAN);
        
        TypeDescription resultTypeDesc = typeSystem.addType(
                "eu.openminted.interop.example.funding.Result", 
                "no description", 
                CAS.TYPE_NAME_ANNOTATION);
        resultTypeDesc.addFeature("funding_info", "no description", CAS.TYPE_NAME_FS_ARRAY,
                fundingTypeDesc.getName(), false);
        
        CAS document = JCasFactory.createJCas(typeSystem).getCas();
        document.setDocumentText("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" + 
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure\n" + 
                "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint funded by fp7 project OpenAIREplus\n" + 
                "occaecat cupidatat non proident,fp7 project with grant agreement number 318338 sunt in culpa qui officia deserunt mollit anim id est laborum.\n" + 
                "\n" + 
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In posuere felis nec tortor.\n" + 
                "Pellentesque faucibus. Ut accumsan ultricies elit. Maecenas at justo id velit placerat molestie. Donec dictum lectus non\n" + 
                "odio. Cras a ante vitae enim iaculis aliquam. Mauris nunc quam, venenatis nec, euismod sit amet, egestas placerat, est.\n" + 
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras id elit. Integer quis\n" + 
                "urna. Ut ante enim, dapibus malesuada, fringilla eu, condimentum quis, tellus. Aenean porttitor eros vel dolor. Donec\n" + 
                "convallis pede venenatis nibh. Duis quam. Nam eget lacus. Aliquam erat volutpat. Quisque dignissim congue leo.\n" + 
                "\n" + 
                "Mauris vel lacus vitae felis vestibulum volutpat. Etiam est nunc, venenatis in, tristique eu, imperdiet ac, nisl. Cum sociis\n" + 
                "natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. In iaculis facilisis massa. Etiam eu urna. Sed\n" + 
                "porta. Suspendisse quam leo, molestie sed, luctus quis, feugiat in, pede. Fusce tellus. Sed metus augue, convallis et,\n" + 
                "vehicula ut, pulvinar eu, ante. Integer orci tellus, tristique vitae, consequat nec, porta vel, lectus. Nulla sit amet diam.\n" + 
                "Duis non nunc. Nulla rhoncus dictum metus. Curabitur tristique mi condimentum orci. Phasellus pellentesque aliquam enim. Proin\n" + 
                "dui lectus, cursus eu, mattis laoreet, viverra sit amet, quam. Curabitur vel dolor ultrices ipsum dictum tristique. Praesent\n" + 
                "vitae lacus. Ut velit enim, vestibulum non, fermentum nec, hendrerit quis, leo. Pellentesque rutrum malesuada neque.\n" + 
                "\n" + 
                "References:\n" + 
                "Sieger, Rainer; (2012): PanGet - downloads multiple data sets from PANGAEA; PANGAEA - Data Publisher for Earth & Environmental Science.\n" + 
                "Grobe, Hannes; (2005): Description and user manual of the information system PANGAEA; PANGAEA - Data Publisher for Earth & Environmental Science. http://dx.doi.org/10.1594/PANGAEA.319947\n" + 
                "\n" + 
                "Appendix:\n" + 
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In posuere felis nec tortor.\n" + 
                "Pellentesque faucibus. Ut accumsan ultricies elit. Maecenas at justo id velit placerat molestie. Donec dictum lectus non\n" + 
                "odio. Cras a ante vitae enim iaculis aliquam. Mauris nunc quam, venenatis nec, euismod sit amet, egestas placerat, est.\n" + 
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras id elit. Integer quis\n" + 
                "urna. Ut ante enim, dapibus malesuada, fringilla eu, condimentum quis, tellus. Aenean porttitor eros vel dolor. Donec\n" + 
                "convallis pede venenatis nibh. Duis quam. Nam eget lacus. Aliquam erat volutpat. Quisque dignissim congue leo.");
        
        // {"funding_info": [ {
        //   "fund": "FP7", 
        //   "acronym": "OPENAIREPLUS", 
        //   "grantid": "283595", 
        //   "confidence": 0.77, 
        //   "EGI-related": false 
        //  }, {
        //    "fund": "FP7", 
        //    "acronym": "OPTIQUE", 
        //    "grantid": "318338", 
        //    "confidence": 0.92, 
        //    "EGI-related": false
        //  }]}

        Type fundingType = CasUtil.getType(document, fundingTypeDesc.getName());
        Type resultType = CasUtil.getType(document, resultTypeDesc.getName());

        FeatureStructure fundingFS1 = document.createFS(fundingType);
        FSUtil.setFeature(fundingFS1, "fund", "FP7");
        FSUtil.setFeature(fundingFS1, "acronym", "OPENAIREPLUS");
        FSUtil.setFeature(fundingFS1, "grantid", "283595");
        FSUtil.setFeature(fundingFS1, "confidence", 0.77);
        FSUtil.setFeature(fundingFS1, "EGI_related", false);
        document.addFsToIndexes(fundingFS1);
        
        FeatureStructure fundingFS2 = document.createFS(fundingType);
        FSUtil.setFeature(fundingFS2, "fund", "FP7");
        FSUtil.setFeature(fundingFS2, "acronym", "OPTIQUE");
        FSUtil.setFeature(fundingFS2, "grantid", "318338");
        FSUtil.setFeature(fundingFS2, "confidence", 0.92);
        FSUtil.setFeature(fundingFS2, "EGI_related", false);
        document.addFsToIndexes(fundingFS2);
                
        FeatureStructure resultFS = document.createFS(resultType);
        FSUtil.setFeature(resultFS, "funding_info", asList(fundingFS1, fundingFS2));
        document.addFsToIndexes(resultFS);
        
        try (OutputStream docOS = new FileOutputStream("target/funding_document.xmi")) {
            CasIOUtils.save(document, docOS, SerialFormat.XMI);
        }
        
        try (OutputStream tsOS = new FileOutputStream("target/funding_typesystem.xml")) {
            typeSystem.toXML(tsOS);
        }
    }
}
