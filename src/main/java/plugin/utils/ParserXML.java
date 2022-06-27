package plugin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import plugin.metrics.MetricsOquare;
import plugin.metrics.OquareMetrics;
import plugin.metrics.OquareModelMetrics;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParserXML {

	private static ParserXML instancia = null;
	private static OquareMetrics oquareMetrics = null;
	private static DocumentBuilderFactory dbFactory;
	private static DocumentBuilder dBuilder;
	private static Document doc;
	private static Logger log = LoggerFactory.getLogger(ParserXML.class);

	private ParserXML() {

	}

	public static ParserXML getInstancia() {
		if (instancia == null) {
			instancia = new ParserXML();
		}
		return instancia;
	}

	public static OquareMetrics getOquareMetrics(String file) {

		oquareMetrics = new OquareMetrics();

		dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc = dBuilder.parse(new File(file));
		} catch (SAXException e) {
			log.error("ERROR: Error parsing OQuaRE document");
			return null;
		} catch (IOException e) {
			log.error("ERROR: Error parsing OQuaRE document");
			return null;
		}
		oquareMetrics.setMetricsOquareScaled(ParserXML.getInstancia().getMetricsOquareScaled(file));
		oquareMetrics.setOquareModelMetrics(ParserXML.getInstancia().getOquareModelMetrics(file));
		oquareMetrics.setMetricsOquare(ParserXML.getInstancia().getMetricsOquare(file));

		return oquareMetrics;
	}

	private MetricsOquare getMetricsOquareScaled(String file) {

		// oquareMetricsScaled
		MetricsOquare metricsOquareScaled = new MetricsOquare();
		NodeList listOquareMetricsScaled = doc.getElementsByTagName("oquareMetricsScaled");
		for (int i = 0; i < listOquareMetricsScaled.getLength(); i++) {
			Node node = listOquareMetricsScaled.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Map<String, Double> mapaOquareMetricsScaled = new HashMap<String, Double>();
				mapaOquareMetricsScaled.put("ANOnto", getXMLValue(element, "ANOnto"));
				mapaOquareMetricsScaled.put("AROnto", getXMLValue(element, "AROnto"));
				mapaOquareMetricsScaled.put("CBOOnto", getXMLValue(element, "CBOOnto"));
				mapaOquareMetricsScaled.put("CBOnto2", getXMLValue(element, "CBOnto2"));
				mapaOquareMetricsScaled.put("CROnto", getXMLValue(element, "CROnto"));
				mapaOquareMetricsScaled.put("DITOnto", getXMLValue(element, "DITOnto"));
				mapaOquareMetricsScaled.put("INROnto", getXMLValue(element, "INROnto"));
				mapaOquareMetricsScaled.put("LCOMOnto", getXMLValue(element, "LCOMOnto"));
				mapaOquareMetricsScaled.put("NACOnto", getXMLValue(element, "NACOnto"));
				mapaOquareMetricsScaled.put("NOCOnto", getXMLValue(element, "NOCOnto"));
				mapaOquareMetricsScaled.put("NOMOnto", getXMLValue(element, "NOMOnto"));
				mapaOquareMetricsScaled.put("POnto", getXMLValue(element, "POnto"));
				mapaOquareMetricsScaled.put("PROnto", getXMLValue(element, "PROnto"));
				mapaOquareMetricsScaled.put("RFCOnto", getXMLValue(element, "RFCOnto"));
				mapaOquareMetricsScaled.put("RROnto", getXMLValue(element, "RROnto"));
				mapaOquareMetricsScaled.put("TMOnto", getXMLValue(element, "TMOnto"));
				mapaOquareMetricsScaled.put("TMOnto2", getXMLValue(element, "TMOnto2"));
				mapaOquareMetricsScaled.put("WMCOnto", getXMLValue(element, "WMCOnto"));
				mapaOquareMetricsScaled.put("WMCOnto2", getXMLValue(element, "WMCOnto2"));
				metricsOquareScaled.setMetricas(mapaOquareMetricsScaled);
				return metricsOquareScaled;
			}
		}
		return metricsOquareScaled;
	}

	private MetricsOquare getMetricsOquare(String file) {

		MetricsOquare metricsOquare = new MetricsOquare();
		NodeList listOquareMetrics = doc.getElementsByTagName("oquareMetrics");
		for (int i = 0; i < listOquareMetrics.getLength(); i++) {
			Node node = listOquareMetrics.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Map<String, Double> mapaOquareMetrics = new HashMap<String, Double>();
				mapaOquareMetrics.put("ANOnto", getXMLValue(element, "ANOnto"));
				mapaOquareMetrics.put("AROnto", getXMLValue(element, "AROnto"));
				mapaOquareMetrics.put("CBOOnto", getXMLValue(element, "CBOOnto"));
				mapaOquareMetrics.put("CBOnto2", getXMLValue(element, "CBOnto2"));
				mapaOquareMetrics.put("CROnto", getXMLValue(element, "CROnto"));
				mapaOquareMetrics.put("DITOnto", getXMLValue(element, "DITOnto"));
				mapaOquareMetrics.put("INROnto", getXMLValue(element, "INROnto"));
				mapaOquareMetrics.put("LCOMOnto", getXMLValue(element, "LCOMOnto"));
				mapaOquareMetrics.put("NACOnto", getXMLValue(element, "NACOnto"));
				mapaOquareMetrics.put("NOCOnto", getXMLValue(element, "NOCOnto"));
				mapaOquareMetrics.put("NOMOnto", getXMLValue(element, "NOMOnto"));
				mapaOquareMetrics.put("POnto", getXMLValue(element, "POnto"));
				mapaOquareMetrics.put("PROnto", getXMLValue(element, "PROnto"));
				mapaOquareMetrics.put("RFCOnto", getXMLValue(element, "RFCOnto"));
				mapaOquareMetrics.put("RROnto", getXMLValue(element, "RROnto"));
				mapaOquareMetrics.put("TMOnto", getXMLValue(element, "TMOnto"));
				mapaOquareMetrics.put("TMOnto2", getXMLValue(element, "TMOnto2"));
				mapaOquareMetrics.put("WMCOnto", getXMLValue(element, "WMCOnto"));
				mapaOquareMetrics.put("WMCOnto2", getXMLValue(element, "WMCOnto2"));
				metricsOquare.setMetricas(mapaOquareMetrics);
				return metricsOquare;
			}
		}
		return metricsOquare;
	}

	private OquareModelMetrics getOquareModelMetrics(String file) {
		OquareModelMetrics oquareModelMetrics = new OquareModelMetrics();

		NodeList listOquareModelMetrics = doc.getElementsByTagName("oquareModel");
		for (int i = 0; i < listOquareModelMetrics.getLength(); i++) {
			Node node = listOquareModelMetrics.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				Element e = null;

				Map<String, Double> mapOquareValue = new HashMap<String, Double>();
				mapOquareValue.put("OquareValue", getXMLAttributeValue(element, "oquareValue"));
				oquareModelMetrics.setMapOquareModel(mapOquareValue);

				Map<String, Double> mapOquareModelCompatibility = new HashMap<String, Double>();
				e = (Element) element.getElementsByTagName("oquareModelCompatibility").item(0);
				mapOquareModelCompatibility.put("Compatibility", getXMLAttributeValue(e, "compatibility"));
				mapOquareModelCompatibility.put("Replaceability", getXMLValue(e, "replaceability"));
				oquareModelMetrics.setMapOquareModelCompatibility(mapOquareModelCompatibility);

				Map<String, Double> mapOquareModelFunctionalAdequacy = new HashMap<String, Double>();
				e = (Element) element.getElementsByTagName("oquareModelFunctionalAdequacy").item(0);
				mapOquareModelFunctionalAdequacy.put("FunctionalAdequacy",
						getXMLAttributeValue(e, "functionalAdequacy"));
				mapOquareModelFunctionalAdequacy.put("ConsistentSearchAndQuery",
						getXMLValue(e, "consistentSearchAndQuery"));
				mapOquareModelFunctionalAdequacy.put("GuidanceAndDecisionTrees",
						getXMLValue(e, "guidanceAndDecisionTrees"));
				mapOquareModelFunctionalAdequacy.put("IndexingAndLinking", getXMLValue(e, "indexingAndLinking"));
				mapOquareModelFunctionalAdequacy.put("Infering", getXMLValue(e, "infering"));
				mapOquareModelFunctionalAdequacy.put("KnowledgeAcquisition", getXMLValue(e, "knowledgeAcquisition"));
				mapOquareModelFunctionalAdequacy.put("KnowledgeReuse", getXMLValue(e, "knowledgeReuse"));
				mapOquareModelFunctionalAdequacy.put("ReferenceOntology", getXMLValue(e, "referenceOntology"));
				mapOquareModelFunctionalAdequacy.put("ResultsRepresentation", getXMLValue(e, "resultsRepresentation"));
				mapOquareModelFunctionalAdequacy.put("SchemaAndValueReconciliation",
						getXMLValue(e, "schemaAndValueReconciliation"));
				mapOquareModelFunctionalAdequacy.put("TextAnalysis", getXMLValue(e, "textAnalysis"));
				oquareModelMetrics.setMapOquareModelFunctionalAdequacy(mapOquareModelFunctionalAdequacy);

				Map<String, Double> mapOquareModelMaintainability = new HashMap<String, Double>();
				e = (Element) element.getElementsByTagName("oquareModelMaintainability").item(0);
				mapOquareModelMaintainability.put("Maintainability", getXMLAttributeValue(e, "maintainability"));
				mapOquareModelMaintainability.put("Analysability", getXMLValue(e, "analysability"));
				mapOquareModelMaintainability.put("Changeability", getXMLValue(e, "changeability"));
				mapOquareModelMaintainability.put("ModificationStability", getXMLValue(e, "modificationStability"));
				mapOquareModelMaintainability.put("Modularity", getXMLValue(e, "modularity"));
				mapOquareModelMaintainability.put("Reusability", getXMLValue(e, "reusability"));
				mapOquareModelMaintainability.put("Testeability", getXMLValue(e, "testeability"));
				oquareModelMetrics.setMapOquareModelMaintainability(mapOquareModelMaintainability);

				Map<String, Double> mapOquareModelOperability = new HashMap<String, Double>();
				e = (Element) element.getElementsByTagName("oquareModelOperability").item(0);
				mapOquareModelOperability.put("Operability", getXMLAttributeValue(e, "operability"));
				mapOquareModelOperability.put("Lerneability", getXMLValue(e, "lerneability"));
				oquareModelMetrics.setMapOquareModelOperability(mapOquareModelOperability);

				Map<String, Double> mapOquareModelReliability = new HashMap<String, Double>();
				e = (Element) element.getElementsByTagName("oquareModelReliability").item(0);
				mapOquareModelReliability.put("Reliability", getXMLAttributeValue(e, "reliability"));
				mapOquareModelReliability.put("Availability", getXMLValue(e, "availability"));
				mapOquareModelReliability.put("Recoverability", getXMLValue(e, "recoverability"));
				oquareModelMetrics.setMapOquareModelReliability(mapOquareModelReliability);

				Map<String, Double> mapOquareModelStructural = new HashMap<String, Double>();
				e = (Element) element.getElementsByTagName("oquareModelStructural").item(0);
				mapOquareModelStructural.put("Structural", getXMLAttributeValue(e, "structural"));
				mapOquareModelStructural.put("Cohesion", getXMLValue(e, "cohesion"));
				mapOquareModelStructural.put("Consistency", getXMLValue(e, "consistency"));
				mapOquareModelStructural.put("FormalRelationSupport", getXMLValue(e, "formalRelationSupport"));
				mapOquareModelStructural.put("Formalisation", getXMLValue(e, "formalisation"));
				mapOquareModelStructural.put("Redundancy", getXMLValue(e, "redundancy"));
				mapOquareModelStructural.put("Tagledness", getXMLValue(e, "tagledness"));
				oquareModelMetrics.setMapOquareModelStructural(mapOquareModelStructural);

				Map<String, Double> mapOquareModelTransferability = new HashMap<String, Double>();
				e = (Element) element.getElementsByTagName("oquareModelTransferability").item(0);
				mapOquareModelTransferability.put("Transferability", getXMLAttributeValue(e, "transferability"));
				mapOquareModelTransferability.put("Adaptability", getXMLValue(e, "adaptability"));
				oquareModelMetrics.setMapOquareModelTransferability(mapOquareModelTransferability);

				return oquareModelMetrics;

			}
		}
		return oquareModelMetrics;
	}

	private double getXMLValue(Element element, String tag) {
		return Double.parseDouble(element.getElementsByTagName(tag).item(0).getTextContent());
	}

	private double getXMLAttributeValue(Element element, String tag) {
		return Double.parseDouble(element.getAttribute(tag));
	}
}
