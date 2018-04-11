jaxws-maven m2e connector
=============================================

This m2e connector for the jaxws maven plugin is designed to handle the wsimport, wsgen, wsimport-test  and wsgen-test goals of the [jaxws-maven-plugin](https://jax-ws-commons.java.net/jaxws-maven-plugin/). 
The connector can also handle the wsimport and wsgen goals of the old codehaus [jaxws-maven-plugin](http://mojo.codehaus.org/jaxws-maven-plugin/). 

[![Build Status](https://buildhive.cloudbees.com/job/coderplus/job/m2e-connector-for-jaxws-maven-plugin/badge/icon)](https://buildhive.cloudbees.com/job/coderplus/job/m2e-connector-for-jaxws-maven-plugin/)

## FAQ ##

### How do I use it? ###

First off, note that this is currently Beta code.  It has been minimally tested, and all the usual early adopter
warnings apply.  That said if you're willing to help test the connector all you have to do is:

1. Drag the Install button into your eclipse workspace to install the connector from the Eclipse Market place
[![Install the Connector](http://marketplace.eclipse.org/sites/all/modules/custom/marketplace/images/installbutton.png)](http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=1965968)

or use the  [update site](https://coderplus.github.io/m2e-connector-for-jaxws-maven-plugin/)

2. Remove any [lifecycle mapping metadata](http://wiki.eclipse.org/M2E_plugin_execution_not_covered#ignore_plugin_goal)
you might have had in your POMs for the jaxws:wsimport,jaxws:wsgen,jaxws:wsimport-test or  jaxws:wsgen-test goals.







That's it!  The connector will run on full builds. It will be executed on incremental builds only if something interesting to the plugin has changed.

### How can I help the project? ###

Thanks for asking...

* If you're a  user of this plugin/connector
	* Test this out.  [File an issue](https://github.com/coderplus/m2e-connector-for-jaxws-maven-plugin/issues) if it doesn't
	work for you.  File an issue if you think it should do something more, or something different.
* If you're a Tycho/Eclipse Plugin/m2e  expert:
	* File an issue or submit a pull request if there is something that could be done better.
	* Contribute test cases.
* If you're a representative of the Eclipse Foundation or Apache Software Foundation or similar:
	* I'd be happy to consider donating this plugin&mdash;do get in touch.


## Thanks ##

Many thanks to the folks from the m2e-dev mailing list, especially [Igor Federenko](https://github.com/ifedorenko)
