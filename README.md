# Overview
This repository is a future home for Konveyor's static code analysis Rulesets.  It is intended to contain future rules to aid static code analysis as well as issues shared by subject matter experts to aid creation of richer Rulesets.
* Intended for [Rules](https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md) compatible with [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp)

## Please contribute knowledge
The Konveyor project is working to consolidate learnings from modernization engagements into codified rules to help future users.  We are actively asking anyone willing to share to open an issue and describe a problem they encountered bringing a legacy application to Kubernetes.  
* We are interested to learn from you, anything you find interesting to share is worthy of opening an issue to help the community.
* Examples to show a few highlights of areas to consider:
  * .Net framework to .Net
  * Legacy Java to Kubernetes
    * Traditional Application Server (Java EE/Jakarta EE) to Quarkus or Spring Boot
    * Netflix OSS to Kubernetes
    * Distributed Cache setups in VM to running on Kubernetes
    * Service discovery (Consul/Eureka/Zookeeper) to Kubernetes
    * Session Management
    * Configuration Management patterns in K8s
    * HA (native clustering) to Kubernetes
    * Coupling with APIs from other container platforms to Kubernetes
  * And anything else you feel is important (NodeJS, Python, Go, etc...)
### What is an example of the kind of information that would help for a Rule?
* Let's say you recently updated a legacy Java application that was using a distributed cache and you brought it to Kubernetes but noticed that the new environment caused the discovery mechanism to break, so after bringing it to Kubernetes you needed to update to a different library for discovery of other endpoints for the cache. 
    * The above would be the exact kind of information we are asking you to open an issue about.  Include as much or as little information as you have time.  If you can supply more information of library names, versions, error messages, or even samples of source code that would be excellent...yet even if time does not permit and the issue is simpler and just a few sentences describing the problem that would be of great use to the community.  We will investigate deeper and look to incorporate into future Rulesets.
## User Group
For those interested, we have a [user group focused on improving the migration experience](https://github.com/konveyor/community/tree/main/ug-migration-experience) which is helping to share information on problems and solutions from their modernization engagements.  
* Please consider joining the email group: konveyor-ug-mig-exp@googlegroups.com
  * Join URL:  https://groups.google.com/g/konveyor-ug-mig-exp


## Background Info
* Konveyor began as a project delivering Java code analysis via [Windup](https://github.com/windup/windup) with a large collection of Java related [Rulesets](https://github.com/windup/windup-rulesets)
* The Konveyor community is working to expand analysis support to multiple languages with the first deliverable targeting a [Summer 2023 Release](https://github.com/orgs/konveyor/projects/59) with analysis leveraging a new approach based on reusing [Language Servers](https://microsoft.github.io/language-server-protocol/implementors/servers/) that implement the [Language Server Protocol](https://microsoft.github.io/language-server-protocol/)
* Konveyor's new Analysis engine is located at: [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp) 
  * As of ~Summer 2023 it is planned to support:
    * [Java](https://github.com/konveyor/analyzer-lsp/tree/main/provider/java) via [eclipse/eclipse.jdt.ls](https://github.com/eclipse/eclipse.jdt.ls) with a custom LSP extension: [konveyor/java-analyzer-bundle](https://github.com/konveyor/java-analyzer-bundle)
    * [Go](https://github.com/konveyor/analyzer-lsp/tree/main/provider/golang) via [golang/tools/gopls](https://github.com/golang/tools/tree/master/gopls)
  * Post Summer 2023
    * .Net: A C# analyzer is next priority with language support via [OmniSharp/csharp-language-server-protocol](https://github.com/OmniSharp/csharp-language-server-protocol)
 * Spring 2023:
   * Until the [Summer 2023 Release](https://github.com/orgs/konveyor/projects/59) is available, Konveyor will continue to rely on [Windup](https://github.com/windup/windup) for Java analysis 
     * Rules written with the Windup syntax will remain compatible with the new [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp) via conversion tool being developed at [fabianvf/windup-rulesets-yaml/blob/master/main.go](https://github.com/fabianvf/windup-rulesets-yaml/blob/master/main.go)


## Code of Conduct
Refer to Konveyor's Code of Conduct [here](https://github.com/konveyor/community/blob/main/CODE_OF_CONDUCT.md).
