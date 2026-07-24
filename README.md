# cics-java-liberty-springboot-jpa
[![Build](https://github.com/cicsdev/cics-java-liberty-springboot-jpa/actions/workflows/build.yaml/badge.svg)](https://github.com/cicsdev/cics-java-liberty-springboot-jpa/actions/workflows/build.yaml)
[![License](https://img.shields.io/badge/License-EPL%202.0-green.svg)](https://www.eclipse.org/legal/epl-2.0/)

## Overview

This sample demonstrates a Spring Boot application that uses Spring Data JPA to access Db2 for z/OS data, deployed to a CICS Liberty JVM server. The application targets the employee sample table (`EMP`) supplied with Db2 for z/OS, and exposes REST endpoints to add, update, delete, and display employee records.

**Key Features:**
- **Spring Data JPA**: Uses Spring Boot's auto-configuration with Hibernate and Db2
- **JNDI DataSource lookup**: Connects to Db2 via a CICS type-2 JDBC connection configured in Liberty
- **REST API**: CRUD operations over the `EMP` table via simple HTTP GET endpoints
- **Jakarta EE 10**: Spring Boot 3.x with `jakarta.*` namespace and `servlet-6.0`
- **Multi-build support**: Gradle, Maven, and Eclipse (CICS Explorer SDK) deployment paths

## Table of Contents

1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Before You Start](#before-you-start)
4. [Reference](#reference)
5. [Downloading](#downloading)
6. [Check Dependencies](#check-dependencies)
7. [Building the Sample](#building-the-sample)
8. [Deploying to a CICS Liberty JVM server](#deploying-to-a-cics-liberty-jvm-server)
9. [Running the Sample](#running-the-sample)
10. [Troubleshooting](#troubleshooting)
11. [License](#license)
12. [Additional Resources](#additional-resources)
13. [Contributing](#contributing)

## Prerequisites

- CICS TS V6.1 or later (required for Spring Boot 3.x and Jakarta EE 10 support)
- A configured Liberty JVM server in CICS
- Java SE 17 or later on the workstation
- IBM Db2 V12 or later on z/OS with the CICS DB2CONN resource configured
- An Eclipse development environment on the workstation (optional)
- Either Gradle or Apache Maven on the workstation (optional if using Wrappers)

## Before You Start

Before building and deploying this sample, you must customize the following files with your environment-specific values.

### 1. Db2 Connection Configuration

**File:** Liberty `server.xml`

Configure the IBM Data Server Driver for JDBC and SQLJ library and a type-2 DataSource:

```xml
<library id="db2Type2Driver">
    <fileset dir="/usr/lpp/db2v12/jdbc/classes" includes="db2jcc4.jar db2jcc_license_cisuz.jar"/>
    <fileset dir="/usr/lpp/db2v12/jdbc/lib" includes="libdb2jcct2zos4_64.so"/>
</library>

<dataSource id="db2Type2" jndiName="jdbc/jpaDataSource" transactional="false"
            commitOrRollbackOnCleanup="commit" type="javax.sql.DataSource">
    <jdbcDriver libraryRef="db2Type2Driver"/>
    <properties.db2.jcc currentSchema="YOUR_SCHEMA" driverType="2"/>
    <connectionManager agedTimeout="0"/>
</dataSource>
```

Update the `fileset dir` paths to match your Db2 JDBC installation on z/OS, and set `currentSchema` to your Db2 schema name.

### 2. CICS Region Configuration

Before deploying, ensure your CICS region has:

- **DB2CONN resource** defined and installed in CICS
- **CICS SIT parameter** `DB2CONN=YES` (required for JDBC Type 2 connections)
- **JCL** — add these DD statements to your CICS region JCL to provide the Db2 load libraries:

```jcl
//         DD DSN=SYS2.DB2.V12.SDSNLOAD,DISP=SHR
//         DD DSN=SYS2.DB2.V12.SDSNLOD2,DISP=SHR
```

### 3. Liberty Server Features

**File:** Liberty `server.xml`

```xml
<featureManager>
    <!-- Required for Spring Boot 3.x (Jakarta EE 10) -->
    <feature>servlet-6.0</feature>
    <!-- Required for JDBC DataSource -->
    <feature>jdbc-4.3</feature>
</featureManager>
```

> **Note:** `servlet-6.0` requires CICS TS V6.1 or later. `cicsts:security-1.0` is auto-injected by CICS when region security is active — do not add it manually. For `jdbc-4.3`, ensure `type="javax.sql.DataSource"` is set on the `<dataSource>` element when using a type-2 driver.

A complete template `server.xml` is provided in [`etc/config/liberty/server.xml`](./etc/config/liberty/server.xml).

## Reference

More information about the development of this sample can be found in the blog [Spring Boot Java applications for CICS, Part 4: Spring JPA](https://developer.ibm.com/tutorials/spring-boot-java-applications-for-cics-part-4-spring-jpa/)

## Downloading

**If using Eclipse:** the simplest approach is to clone the repository using the Eclipse Git plugin (EGit) perspective.

**If using the command line:**
```shell
git clone https://github.com/cicsdev/cics-java-liberty-springboot-jpa
```
Alternatively, download the sample as a [ZIP](https://github.com/cicsdev/cics-java-liberty-springboot-jpa/archive/main.zip) and unzip onto the workstation.

**If importing into Eclipse:**
1. In the **Git Repositories** view, right-click the repository → **Import as Project** (imports the root project)
   *(if you cloned from the command line, use **File → Import → Existing Projects into Workspace** instead, browse to the cloned directory, select all projects, and skip to step 6)*
2. Switch to the **Java EE** perspective
3. In the **Project Explorer**, right-click the `cics-java-liberty-springboot-jpa-app` folder → **Import as Project**
4. Right-click the `cics-java-liberty-springboot-jpa-cicsbundle` folder → **Import as Project**
5. Right-click the `cics-java-liberty-springboot-jpa-cicsbundle-eclipse` folder → **Import as Project**
6. **Required:** Right-click the root project → **Gradle → Refresh Gradle Project** or **Maven → Update Project...** — this resolves Spring Boot and CICS dependencies into the project classpath. Without this step, the WTP export will produce an incomplete WAR missing `WEB-INF/lib`.

### Check dependencies

Before building this sample, you should verify the correct CICS TS bill of materials (BOM) version for your CICS release. The BOM specifies a consistent set of artifacts and their scopes. You can browse the published versions of the CICS BOM at [Maven Central](https://mvnrepository.com/artifact/com.ibm.cics/com.ibm.cics.ts.bom).

This sample uses Spring Data JPA and does not require JCICS directly. No BOM entry is required for the JPA sample.

## Building the Sample

You can build the sample using an IDE of your choice, or you can build it from the command line. For both approaches, using the supplied Gradle or Maven wrapper is the recommended way to get a consistent version of build tooling.

On the command line, you simply swap the Gradle or Maven command for the wrapper equivalent, `gradlew` or `mvnw` respectively.

For an IDE, taking Eclipse as an example, the plug-ins for Gradle *buildship* and Maven *m2e* will integrate with the "Run As..." capability, allowing you to specify whether you want to build the project with a Wrapper, or a specific version of your chosen build tool.

The required build-tasks are `clean build` for Gradle and `clean verify` for Maven. Once run, Gradle will generate a WAR file in the `cics-java-liberty-springboot-jpa-app/build/libs` directory, while Maven will generate it in the `cics-java-liberty-springboot-jpa-app/target` directory.

**Note:** When building a WAR file for deployment to Liberty it is good practice to exclude Tomcat from the final runtime artifact. We demonstrate this in the pom.xml with the *provided* scope, and in build.gradle with the *providedRuntime()* dependency.

### Gradle Wrapper (command line)

Run the following in a local command prompt:

On Linux or Mac:

```shell
./gradlew clean build
```

On Windows:

```shell
gradlew.bat clean build
```

This creates a WAR file inside the `cics-java-liberty-springboot-jpa-app/build/libs` directory.

> **Note:** In Eclipse, the `build` directory may be hidden by default. To view it: **Package Explorer → ⋮ → Filters and Customization → uncheck "Gradle build folder"**. For Maven, the `target` directory is visible by default.

### Maven Wrapper (command line)

Run the following in a local command prompt:

On Linux or Mac:

```shell
./mvnw clean verify
```

On Windows:

```shell
mvnw.cmd clean verify
```

This creates a WAR file inside the `cics-java-liberty-springboot-jpa-app/target` directory.

## Deploying to a CICS Liberty JVM server

Ensure your Liberty `server.xml` and CICS region are configured as described in [Before You Start](#before-you-start). A complete template `server.xml` is provided in [`etc/config/liberty/server.xml`](./etc/config/liberty/server.xml).

### Update application.properties

The `src/main/resources/application.properties` file contains:

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.DB2Dialect
spring.jpa.show-sql=true
spring.data.jpa.repositories.bootstrap-mode=default
```

- `spring.jpa.show-sql` is optional but useful to display the SQL generated by Hibernate.
- `spring.jpa.properties.hibernate.dialect` ensures Hibernate generates Db2-compatible SQL.
- `spring.data.jpa.repositories.bootstrap-mode=default` is required — do NOT use `deferred` or `lazy` as these attempt asynchronous database access on a non-CICS/Db2-enabled thread.

### CICS Bundle Plugin Deployment (Gradle/Maven)

This method uses the cics-bundle-gradle-plugin or cics-bundle-maven-plugin to automatically generate a CICS bundle.

**Configure your JVM server name:**

Gradle (`cics-java-liberty-springboot-jpa-cicsbundle/build.gradle`):
```gradle
cics.jvmserver = 'YOUR_JVMSERVER_NAME'  // e.g., 'DFHWLP'
```

Maven (`cics-java-liberty-springboot-jpa-cicsbundle/pom.xml`):
```xml
<cics.jvmserver>YOUR_JVMSERVER_NAME</cics.jvmserver>  <!-- e.g., DFHWLP -->
```

**Deploy the bundle:**

1. Upload the CICS bundle ZIP file to zFS:
   - Gradle: `cics-java-liberty-springboot-jpa-cicsbundle/build/distributions/cics-java-liberty-springboot-jpa-cicsbundle-1.0.0.zip`
   - Maven: `cics-java-liberty-springboot-jpa-cicsbundle/target/cics-java-liberty-springboot-jpa-cicsbundle-1.0.0.zip`

2. Unzip the bundle on zFS

3. Create a CICS BUNDLE resource definition:
   ```
   CEDA DEFINE BUNDLE(JPA) GROUP(MYGROUP) BUNDLEDIR(/path/to/bundle)
   ```

4. Install the bundle:
   ```
   CEDA INSTALL BUNDLE(JPA) GROUP(MYGROUP)
   ```

**Alternative:** Use the CICS deployment API via CMCI to deploy the bundle remotely.

---

### CICS Explorer SDK Deployment

This repository includes a pre-configured Eclipse CICS bundle project `cics-java-liberty-springboot-jpa-cicsbundle-eclipse` that can be used directly with CICS Explorer SDK.

1. Right-click the `cics-java-liberty-springboot-jpa-cicsbundle-eclipse` project → **Export Bundle Project to z/OS UNIX File System** and follow the wizard

> **Note**: The bundle project is pre-configured so that the Eclipse WTP export automatically packages the application WAR with all dependencies. This relies on the `-app` project being open in the same Eclipse workspace. If you have not yet imported the project, follow steps 3 and 6 of the [Importing into Eclipse](#downloading) instructions first.

---

### Direct Liberty Application Deployment

1. Manually upload the WAR file to zFS
2. Add an `<application>` element to the Liberty server.xml to define the web application with access to all authenticated users. For example:

```xml
<application id="cics-java-liberty-springboot-jpa"
    location="${server.config.dir}/springapps/cics-java-liberty-springboot-jpa.war"
    name="cics-java-liberty-springboot-jpa" type="war">
    <application-bnd>
        <security-role name="cicsAllAuthenticated">
            <special-subject type="ALL_AUTHENTICATED_USERS"/>
        </security-role>
    </application-bnd>
</application>
```

---

## Running the Sample

1. Ensure the web application started successfully in Liberty by checking for msg `CWWKT0016I` in the Liberty messages.log:
   ```
   CWWKT0016I: Web application available (default_host): http://myzos.mycompany.com:httpPort/cics-java-liberty-springboot-jpa
   ```

2. Visit the root URL from a browser:
   ```
   http://myzos.mycompany.com:httpPort/cics-java-liberty-springboot-jpa/
   ```
   The browser will prompt for basic authentication. Enter a valid userid and password for your Liberty JVM server.

3. **Available endpoints:**

   - `GET /allEmployees` — returns all rows from the `EMP` table
   - `GET /listEmployee/{empno}` — returns the employee with the given employee number
   - `GET /addEmployee/{firstName}/{lastName}` — adds a new employee record
   - `GET /updateEmployee/{empNo}/{newSalary}` — updates the salary for an employee
   - `GET /deleteEmployee/{empNo}` — deletes the employee with the given number

   Example:
   ```
   http://myzos.mycompany.com:httpPort/cics-java-liberty-springboot-jpa/allEmployees
   ```

## Troubleshooting

**Application fails to start — `CWWKZ0013E` or `SRVE0190E`**
- Verify `servlet-6.0` is enabled in your Liberty `server.xml`.
- Confirm CICS TS V6.1 or later is installed — earlier releases do not support Jakarta EE 10.

**`ClassNotFoundException` for `jakarta.persistence.*`**
- This sample uses `jakarta.*` namespace (Spring Boot 3.x / Jakarta EE 10). Ensure you are not running against a CICS TS release older than V6.1, which uses `javax.*`.

**`HibernateException` or DataSource lookup failure**
- Verify the `<dataSource>` JNDI name in `server.xml` is `jdbc/jpaDataSource`.
- Confirm a CICS DB2CONN resource is active and the region has access to Db2.
- Check `spring.jpa.properties.hibernate.dialect` is set correctly in `application.properties`.

**Empty result set / SQL errors**
- Confirm the Db2 schema name in `<properties.db2.jcc currentSchema="..."/>` matches your Db2 installation.
- Verify the `EMP` table exists in the configured schema.

## License

This project is licensed under [Eclipse Public License - v 2.0](LICENSE).

## Additional Resources

- [CICS TS Documentation](https://www.ibm.com/docs/en/cics-ts)
- [WebSphere Liberty Documentation](https://www.ibm.com/docs/en/was-liberty)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [Spring Boot Java applications for CICS, Part 4: Spring JPA](https://developer.ibm.com/tutorials/spring-boot-java-applications-for-cics-part-4-spring-jpa/)

## Contributing

This sample is maintained by IBM CICS development. We welcome bug reports and feature requests via GitHub Issues. Contributions are welcome and reviewed on a case-by-case basis — please read the [contributing guidelines](https://github.com/cicsdev/.github/blob/main/CONTRIBUTING.md) before opening a pull request. For CICS product questions, contact IBM Support.
