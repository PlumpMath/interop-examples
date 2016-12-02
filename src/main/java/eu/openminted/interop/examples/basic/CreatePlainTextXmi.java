package eu.openminted.interop.examples.basic;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.SerialFormat;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasIOUtils;

public class CreatePlainTextXmi
{
    public static void main(String[] args)
        throws Exception
    {
        // Create empty type system to ensure uimaFIT does *not* pick up any TSD from the classpath
        TypeSystemDescription typeSystem = UIMAFramework.getResourceSpecifierFactory()
                .createTypeSystemDescription();

        JCas document = JCasFactory.createJCas(typeSystem);
        document.setDocumentText("Hello world.");
        
        try (OutputStream docOS = new FileOutputStream("target/plaintext.xmi")) {
            CasIOUtils.save(document.getCas(), docOS, SerialFormat.XMI);
        }
        
        try (OutputStream tsOS = new FileOutputStream("target/typesystem.xml")) {
            typeSystem.toXML(tsOS);
        }
    }
}
