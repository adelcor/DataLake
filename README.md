### SparkDataLake

#### Description
SparkDataLake is a project designed for ingesting, processing, and analyzing data using Scala and Apache Spark. The project facilitates the configuration and deployment of services in Kubernetes and Azure environments, providing the necessary scripts and configurations to handle data from various sources.

#### Main Features

1. **Data Ingestion**:
   - Reading datasets from CSV files.
   - Uses case classes (`CaseClass`) to define the structure of data from different domains such as meteorites, salmon, and tortillas.

2. **Configuration**:
   - Configuration of dataset paths and Spark parameters in configuration files (`DatasetConfig`, `SparkConfig`).
   - Configuration of connection properties for databases and other services (`ConnectionPropertiesSetter`).

3. **Data Processing**:
   - Data processing using algorithms defined in `Handler` and `Process`.
   - Use of custom encoders to transform and manipulate data (`CustomEncoders`).

4. **Spark Session**:
   - Configuration and building of the Spark session (`SparkSessionBuilder`), which is crucial for running Spark jobs.

5. **Deployment**:
   - Scripts and configurations to deploy services in Kubernetes (`deployment`).
   - PowerShell scripts to automate deployment and management tasks (`deploy-kubernetes.ps1`, `port-forward.ps1`).

#### Project Structure
DataLake
- .gitignore
- pom.xml
- .idea/
  - .gitignore
  - encodings.xml
  - misc.xml
  - scala_compiler.xml
  - uiDesigner.xml
  - vcs.xml
  - codeStyles/
    - Project.xml
    - codeStyleConfig.xml
- .metals/
  - metals.log
  - metals.mv.db
- deployment/
  - UploadCSV.ps1
  - azurefunction-deployment.yaml
  - azurefunction-service.yaml
  - azurite-deployment.yaml
  - azurite-service.yaml
  - cosmosdb_emulator_deployment.yaml
  - cosmosdb_emulator_service.yaml
  - deploy-kubernetes.ps1
  - port-forward.ps1
  - postgres-deployment.yaml
  - postgres-service.yaml
  - stop-port-forward.ps1
- src/
  - main/
    - resources/
      - application.properties
    - scala/
      - org/example/
        - App.scala
        - CaseClass/
          - MeteoritosCaseClass.scala
          - SalmonesCaseClass.scala
          - TortillaCaseClass.scala
        - Config/
          - DatasetConfig.scala
          - SparkConfig.scala
          - readConfig.scala
        - Connection/
          - ConnectionPropertiesSetter.scala
        - Constants/
          - TSMConst.scala
        - DataHandler/
          - Handler.scala
        - Encoders/
          - CustomEncoders.scala
        - ProcessData/
          - Process.scala
        - Readers/
          - DatasetReader.scala
        - SessionBuilder/
          - SparkSessionBuilder.scala
        - TSMmaps/
          - ColumnMaps.scala
          - ColumnTypes.scala
  - test/
    - scala/
      - samples/
        - junit.scala
        - scalatest.scala
        - specs.scala


### Usage

1. **Initial Setup**:
   - Install Java, Scala, and Maven.
   - Configure the necessary properties in `application.properties`.

2. **Build the Project**:
   - Run `mvn clean install` to build the project.

3. **Deploy in Kubernetes**:
   - Use the scripts in the `deployment/` directory to deploy the services in Kubernetes.

4. **Run the Application**:
   - Run the main application with `scala src/main/scala/org/example/App.scala`.

### Contributions

Contributions are welcome. Please create a pull request to propose changes.


### SparkDataLake

#### Descripción
SparkDataLake es un proyecto diseñado para la ingesta, procesamiento y análisis de datos utilizando Scala y Apache Spark. El proyecto facilita la configuración y el despliegue de servicios en entornos Kubernetes y Azure, proporcionando los scripts y configuraciones necesarios para manejar datos de varias fuentes.

#### Funcionalidades Principales

1. **Ingesta de Datos**:
   - Lectura de datasets desde archivos CSV.
   - Utiliza case classes (`CaseClass`) para definir la estructura de datos de diferentes dominios como meteoritos, salmones y tortillas.

2. **Configuración**:
   - Configuración de rutas de datasets y parámetros de Spark en archivos de configuración (`DatasetConfig`, `SparkConfig`).
   - Configuración de propiedades de conexión para bases de datos y otros servicios (`ConnectionPropertiesSetter`).

3. **Procesamiento de Datos**:
   - Procesamiento de datos utilizando algoritmos definidos en `Handler` y `Process`.
   - Uso de encoders personalizados para transformar y manipular datos (`CustomEncoders`).

4. **Sesión de Spark**:
   - Configuración y construcción de la sesión de Spark (`SparkSessionBuilder`), que es crucial para ejecutar trabajos de Spark.

5. **Despliegue**:
   - Scripts y configuraciones para desplegar servicios en Kubernetes (`deployment`).
   - Scripts de PowerShell para automatizar tareas de despliegue y gestión (`deploy-kubernetes.ps1`, `port-forward.ps1`).

#### Estructura del Proyecto
DataLake
- .gitignore
- pom.xml
- .idea/
  - .gitignore
  - encodings.xml
  - misc.xml
  - scala_compiler.xml
  - uiDesigner.xml
  - vcs.xml
  - codeStyles/
    - Project.xml
    - codeStyleConfig.xml
- .metals/
  - metals.log
  - metals.mv.db
- deployment/
  - UploadCSV.ps1
  - azurefunction-deployment.yaml
  - azurefunction-service.yaml
  - azurite-deployment.yaml
  - azurite-service.yaml
  - cosmosdb_emulator_deployment.yaml
  - cosmosdb_emulator_service.yaml
  - deploy-kubernetes.ps1
  - port-forward.ps1
  - postgres-deployment.yaml
  - postgres-service.yaml
  - stop-port-forward.ps1
- src/
  - main/
    - resources/
      - application.properties
    - scala/
      - org/example/
        - App.scala
        - CaseClass/
          - MeteoritosCaseClass.scala
          - SalmonesCaseClass.scala
          - TortillaCaseClass.scala
        - Config/
          - DatasetConfig.scala
          - SparkConfig.scala
          - readConfig.scala
        - Connection/
          - ConnectionPropertiesSetter.scala
        - Constants/
          - TSMConst.scala
        - DataHandler/
          - Handler.scala
        - Encoders/
          - CustomEncoders.scala
        - ProcessData/
          - Process.scala
        - Readers/
          - DatasetReader.scala
        - SessionBuilder/
          - SparkSessionBuilder.scala
        - TSMmaps/
          - ColumnMaps.scala
          - ColumnTypes.scala
  - test/
    - scala/
      - samples/
        - junit.scala
        - scalatest.scala
        - specs.scala


### Uso

1. **Configuración Inicial**:
   - Instalar Java, Scala y Maven.
   - Configurar las propiedades necesarias en `application.properties`.

2. **Compilar el Proyecto**:
   - Ejecuta `mvn clean install` para compilar el proyecto.

3. **Desplegar en Kubernetes**:
   - Usa los scripts en el directorio `deployment/` para desplegar los servicios en Kubernetes.

4. **Ejecutar la Aplicación**:
   - Ejecuta la aplicación principal con `scala src/main/scala/org/example/App.scala`.

### Contribuciones

Las contribuciones son bienvenidas. Por favor, crea un "pull request" para proponer cambios.
