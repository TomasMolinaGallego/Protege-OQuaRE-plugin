FOR NO DEVELOPERS:

This repository contains the plugin and the OQuaRE jars necessary for the execution in Protégé.  Click the files "oquare-versions.jar" and "oquare.plugin-1.0.jar" and click downlowad. Once downloaded just copy both jar in the "plugins" directory of your Protégé Desktop and to open the plugin, inside Protégé, in "Window" -> "Views" -> "Ontology Views" -> "OQuaRE metrics and score"

FOR DEVELOPERS:

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

![view](https://user-images.githubusercontent.com/74840157/182941671-9d277a88-75b8-46a3-aa18-dac5994e2432.png)


Some images of the plugin 

![cpt](https://user-images.githubusercontent.com/74840157/182941556-e721a9e2-9a94-4d70-b0b3-aa9770599ffe.png)

![ejemploCompleto](https://user-images.githubusercontent.com/74840157/182941961-b0273714-139e-484b-876f-4bdfbf16f1ec.png)

![graphicMetrics](https://user-images.githubusercontent.com/74840157/182942289-986ad839-cebe-4942-b62f-2a2c8cd3b1d6.png)

![graphicMetrics2](https://user-images.githubusercontent.com/74840157/182941572-f081d9b3-2c24-405a-8313-36432e903c4d.png)

![graphicMetrics3](https://user-images.githubusercontent.com/74840157/182941588-165025c8-e1e6-4d40-9f9d-b14fa3e89547.png)




