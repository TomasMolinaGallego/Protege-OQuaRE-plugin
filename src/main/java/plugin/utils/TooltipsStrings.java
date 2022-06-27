package plugin.utils;

import java.util.HashMap;
import java.util.Map;

public class TooltipsStrings {

	public static final Map<String, String> TOOLTIPS_MAP;
	static {
		TOOLTIPS_MAP = new HashMap<>();
		TOOLTIPS_MAP.put("ANOnto",
				"Annotation Richness. Mean number of annotations per class.");
		TOOLTIPS_MAP.put("AROnto",
				"Attribute Richness. Mean number of attributes per class.");
		TOOLTIPS_MAP.put("CBOOnto",
				"Coupling Between Objects. Number of related classes.");
		TOOLTIPS_MAP.put("CBOnto2",
				"Coupling Between object 2. Number of classes related.");
		TOOLTIPS_MAP.put("CROnto",
				"Class Richness. Mean number of instances per class.");
		TOOLTIPS_MAP.put("DITOnto",
				"Depth of Subsumption hierarchy. Length of the largest path from Thing to a leaf class");
		TOOLTIPS_MAP.put("INROnto",
				"Relationships per Class. Mean number of relationships per class.");
		TOOLTIPS_MAP.put("LCOMOnto",
				"Lack of Cohesion in Methods. The semantic and conceptual relatedness of classes can be used to measure the separation of responsibilities and independence of components of ontologies.");
		TOOLTIPS_MAP.put("NACOnto",
				"Number of Ancestor Classes. Mean number of ancestor classes per leaf class.");
		TOOLTIPS_MAP.put("NOCOnto",
				"Number Of Children. Number of the direct subclasses divided by the number of classes minus the number of leaf classes");
		TOOLTIPS_MAP.put("NOMOnto",
				"Number Of properties. Number of properties per class. Mean number of object and data property usages per class");
		TOOLTIPS_MAP.put("POnto",
				"Direct Parent Average. Mean number of direct parents per class.");
		TOOLTIPS_MAP.put("RFCOnto",
				"Response For a Class. Number of properties that can be directly accessed from the class.");
		TOOLTIPS_MAP.put("RROnto",
				"Properties Richness. Number of properties defined in the ontology divided by the number of relationships and properties");
		TOOLTIPS_MAP.put("TMOnto",
				"Tangledness. Mean number of parents per class.");
		TOOLTIPS_MAP.put("TMOnto2",
				"Tangledness 2. Mean number of direct ancestor of classes with more than 1 direct ancestor.");
		TOOLTIPS_MAP.put("WMCOnto",
				"Weighted Method Count. Mean number of properties and relationships per class.");
		TOOLTIPS_MAP.put("WMCOnto2",
				"Weighted Method Count 2. Mean number of path from Thing to a leaf class per leaf class.");
	}

}
