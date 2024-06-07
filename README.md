### SparkDataLake

#### Description
DataLake is a project designed for ingesting, processing, and analyzing data using Scala and Apache Spark. The project facilitates the configuration and deployment of services in Kubernetes and Azure environments, providing the necessary scripts and configurations to handle data from various sources.

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

### License

This project is licensed under the MIT License.

