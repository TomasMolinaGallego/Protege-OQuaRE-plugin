This repository contains code for the Protégé desktop plugin.

Prerequisites
To build and run the plugin, the following items must be installed:

Apache's Maven.

A Protégé distribution (5.0.0 or higher).  The Protégé 5.5.0 release is available from the main Protégé website.

Build

In the oquare-plugin directory:

 mvn clean package
 

On build completion, the "target" directory will contain a oquare.plugin{version}.jar file.

Copy the JAR file from the directory named "oquare-versions.jar" and copy to the "plugins" subdirectory of your Protege distribution.
Copy the JAR file from the target directory to the "plugins" subdirectory of your Protege distribution.

View the Plugin in Protégé

Launch your Protégé distribution.

Window > Views > Ontology views > Graphical representation of the OQuaRE scores
