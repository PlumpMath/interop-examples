package eu.openminted.interop.examples.funding;

import static org.apache.jena.datatypes.xsd.XSDDatatype.*;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;

public class CreateFundingOutputOA
{
    public static void main(String[] args)
        throws Exception
    {
        OntModel m = ModelFactory.createOntologyModel();
        m.setNsPrefix(WebAnnotation.PREFIX_OA, WebAnnotation.NS_OA);
        
        // Set up query instances
        final Resource tAnnotation = m.createResource(WebAnnotation.TYPE_ANNOTATION);
        final Property pHasBody = m.createProperty(WebAnnotation.PROP_HAS_BODY);
        final Property pHasTarget = m.createProperty(WebAnnotation.PROP_HAS_TARGET);

        final Resource tResult = m.createResource(FundingInfo.TYPE_RESULT);
        final Property pFundingInfo = m.createProperty(FundingInfo.PROP_FUNDING_INFO);

        final Resource tFunding = m.createResource(FundingInfo.TYPE_FUNDING);
        final Property pFund = m.createProperty(FundingInfo.PROP_FUND);
        final Property pAcronym = m.createProperty(FundingInfo.PROP_ACRONYM);
        final Property pGrantId = m.createProperty(FundingInfo.PROP_GRANT_ID);
        final Property pConfidence = m.createProperty(FundingInfo.PROP_CONFIDENCE);
        final Property pEgiRelated = m.createProperty(FundingInfo.PROP_EGI_RELATED);
        
        Individual fundingFS1 = m.createIndividual("funding1Uri", tFunding);
        fundingFS1.addLiteral(pFund, m.createTypedLiteral("FP7", XSDstring));
        fundingFS1.addLiteral(pAcronym, m.createTypedLiteral("OPENAIREPLUS", XSDstring));
        fundingFS1.addLiteral(pGrantId, m.createTypedLiteral("283595", XSDstring));
        fundingFS1.addLiteral(pConfidence, m.createTypedLiteral(0.77, XSDdouble));
        fundingFS1.addLiteral(pEgiRelated, m.createTypedLiteral(false, XSDboolean));

        Individual fundingFS2 = m.createIndividual("funding2Uri", tFunding);
        fundingFS2.addLiteral(pFund, m.createTypedLiteral("FP7", XSDstring));
        fundingFS2.addLiteral(pAcronym, m.createTypedLiteral("OPTIQUE", XSDstring));
        fundingFS2.addLiteral(pGrantId, m.createTypedLiteral("318338", XSDstring));
        fundingFS2.addLiteral(pConfidence, m.createTypedLiteral(0.92, XSDdouble));
        fundingFS2.addLiteral(pEgiRelated, m.createTypedLiteral(false, XSDboolean));

        Individual fundingInfo = m.createIndividual("result1Uri", tResult);
        fundingInfo.addProperty(pFundingInfo, fundingFS1);
        fundingInfo.addProperty(pFundingInfo, fundingFS2);

        Individual annotation = m.createIndividual("resultUri1", tAnnotation);
        annotation.addProperty(pHasBody, fundingInfo);
        annotation.addLiteral(pHasTarget, 
                m.createTypedLiteral("originalDocumentUri", XSDstring));
        
        try (OutputStream docOS = new FileOutputStream("target/funding_webannotation.jsonld")) {
            // RDFDataMgr.write(docOS, m, RDFLanguages.JSONLD);
            m.write(docOS, "JSON-LD");
        }
        catch (Exception e) {
            throw new AnalysisEngineProcessException(e);
        }
    }
}
