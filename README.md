# SWE_Projekt

<img src="https://user-images.githubusercontent.com/72318314/123131995-8642e280-d44e-11eb-8959-a0ed110a537b.png">

## Requirements / Prerequisites
- [x] [Java Runtime Environment](https://www.java.com/de/download/) (>= Version 1.8)
- [x] [SWT APP](https://github.com/inf19150/SWE_Projekt/releases/latest/) (invitation to repo must be accepted)

## Run Application
- [x] Make sure, that within the same directory of `SWT.jar`, there exists a folder called `Output_Modules/` and `Aggregation_Modules/` each containing jar-modules (e.g. `AGGREGATE_Ger_Satellite.jar`). Optionally the input file called `data.json` can be placed in this directory. **A zip-file representing this required folder-architecture is provided in the moodle-filing.**
<img src="https://user-images.githubusercontent.com/72318314/123115316-4e34a300-d440-11eb-8d9e-55dc749ff0d0.png">

- [x] Run the application by choosing one command
* `java -jar SWT.jar` **(if the data.json is not placed in the directory of the SWT.jar, you'll be greeted with a file-dialog)**

* `java -jar SWT.jar [PATH_TO_data.json]`
  
Then you should see a GUI like this.<br>
<img src="https://user-images.githubusercontent.com/72318314/123114895-f1d18380-d43f-11eb-9b74-46a369607394.png" width="400">

Description of the modules are shown by hovering over the combobox.
<img src="https://user-images.githubusercontent.com/72318314/123143875-53531b80-d45b-11eb-8732-17f80046c8d4.png">

## [Task](https://github.com/inf19150/SWE_Projekt/files/6598660/Aufgabe.pdf)
* Make a suitable draft for the program as well as the configurable modules.<br>
The draft is visible in the attached class diagram. A lot of design principles and design patterns are implemented in this draft, which are listed [here](https://github.com/inf19150/SWE_Projekt/blob/main/README.md#implemented-design-patterns-design-principles).
* Create a program to connect the described module types.<br>
This requirement is satisfied by the [ModuleLoader Class](https://inf19150.github.io/SWE_Projekt/doc/controller/ModuleLoader.html). At runtime, jar modules can be copied in either `Output_Modules/` or `Aggregation_Modules/` and can be loaded dynamically into the program by clicking the `Reload Modules` button.<br>(Deleting modules is complicated, because the files are in use while the program is running.)
* Create at least two aggregation modules.<br>

|Class|Description|
|--|--|
|[AGGREGATE_Ger_Satellite](https://inf19150.github.io/SWE_Projekt/doc/model/aggregations/AGGREGATE_Ger_Satellite.html)|Aggregates all Satellites that have german channels implements IAggregate.|
|[AGGREGATE_Radio_Channels](https://inf19150.github.io/SWE_Projekt/doc/model/aggregations/AGGREGATE_Radio_Channels.html)|Aggregates all Satellites with radio channels implements IAggregate.|
|[AGGREGATE_Satellite_Channels_HD](https://inf19150.github.io/SWE_Projekt/doc/model/aggregations/AGGREGATE_Satellite_Channels_HD.html)|Aggregates all Satellites with HD channels implements IAggregate.|
|[AGGREGATE_Satellite_Eng_Channel](https://inf19150.github.io/SWE_Projekt/doc/model/aggregations/AGGREGATE_Satellite_Eng_Channel.html)|Aggregates all Satellites with english channels implements IAggregate.|
|[AGGREGATE_Satellite_Ger_Channel](https://inf19150.github.io/SWE_Projekt/doc/model/aggregations/AGGREGATE_Satellite_Ger_Channel.html)|Aggregates all Satellites with german channels implements IAggregate.|
|[AGGREGATE_Satellite_Transponder_Count_Channels](https://inf19150.github.io/SWE_Projekt/doc/model/aggregations/AGGREGATE_Satellite_Transponder_Count_Channels.html)|Aggregates all Satellites with their respective count of radio and TV channels implements IAggregate.|

* Create at least two output modules screen.<br>

|Class|Description|
|--|--|
|[JSONFileWriter](https://inf19150.github.io/SWE_Projekt/doc/view/output/JSONFileWriter.html)|JSONFileWriter class implements IOutput.|
|[SimpleFileWriter](https://inf19150.github.io/SWE_Projekt/doc/view/output/SimpleFileWriter.html)|SimpleFileWriter class implements IOutput.|
|[TextBoxWriter](https://inf19150.github.io/SWE_Projekt/doc/view/output/TextBoxWriter.html)|TextBoxWriter class inherits JFrame implements IOutput.|

## Software-Design
### Interface Aggregation-Modules
 
[IAggregate Interface](https://inf19150.github.io/SWE_Projekt/doc/model/aggregations/IAggregate.html)

    public interface IAggregate extends IDescriptor {

     /**
      * Creates composite structure to store the aggregate.
      * 
      * @param satellitesList list of all Satellite objects
      * @return CompositeContainer Root composite container
      */
     public CompositeContainer aggregate(ArrayList<Satellite> satellitesList);
    }
 
 This interface needs to be implemented in any further aggregation-module, written by any developer.
 The Input of any aggregation is a list, containing all satellite data, including their transponders and channels.
 Any aggregation-module must return a [compositum-instance](https://inf19150.github.io/SWE_Projekt/doc/model/container/CompositeContainer.html) (can be hierarchically indefinitely deep) containing the results of the particular aggregation.
 
<hr>

### Interface Output-Modules
 
[IOutput Interface](https://inf19150.github.io/SWE_Projekt/doc/view/output/IOutput.html)

    public interface IOutput extends IDescriptor {

     /**
      * Outputs the contents of the composite structure
      * 
      * @param container Root composite container
      */
     public void output(CompositeContainer container);

     /**
      * Resets the Output module object to its initial state.
      * 
      */
     public void reset();
    }
 
This interface needs to be implemented in any further output-module, written by any developer. Input of any output is a head-container of a [compositum-structure](https://inf19150.github.io/SWE_Projekt/doc/model/container/CompositeContainer.html), containing all aggregated key-value-data. Furthermore, the method `void reset()` needs to be implemented to make sure, the state of any output module can be set to its initial state afterwards.

### Implemented design-patterns, Design-Principles

- [x] MVC-architecture
- [x] Singleton in [GUI](https://inf19150.github.io/SWE_Projekt/doc/view/GUI.html) and [Controller](https://inf19150.github.io/SWE_Projekt/doc/controller/Controller.html)
- [x] Class-Adapter involving [AFileWriter](https://inf19150.github.io/SWE_Projekt/doc/view/output/AFileWriter.html) as Adaptee, [FileWriterWrapper](https://inf19150.github.io/SWE_Projekt/doc/view/output/FileWriterWrapper.html) as Target and [SimpleFileWriter](https://inf19150.github.io/SWE_Projekt/doc/view/output/SimpleFileWriter.html) as Adapter. 
- [x] Compositum involving [CompositeContainer](https://inf19150.github.io/SWE_Projekt/doc/model/container/CompositeContainer.html)
- [x] Interface segregation principle
- [x] Focused on high cohesion and low coupling
- [x] Template metaprogramming implemented in [ModuleLoader](https://inf19150.github.io/SWE_Projekt/doc/controller/ModuleLoader.html)
- [x] Modified factory implemented in [ModuleLoader](https://inf19150.github.io/SWE_Projekt/doc/controller/ModuleLoader.html)

<hr>

## How to write an additional aggregation/output module

1. Create a project for your module and include [SWT.jar](https://github.com/inf19150/SWE_Projekt/releases/latest/) as external dependency.
<img src="https://user-images.githubusercontent.com/72318314/123127143-4b3eb000-d44a-11eb-9b0f-186d18bdf211.png" width="500">

2. Create a class within the default package implementing [IAggregate](https://inf19150.github.io/SWE_Projekt/doc/model/aggregations/IAggregate.html) or [IOutput](https://inf19150.github.io/SWE_Projekt/doc/view/output/IOutput.html) as interface.
3. Export the module as jar-file, where the name of your jar file e.g. `MyModule.jar` must match the class (e.g. `MyModule.java`) implementing the interface
<img src="https://user-images.githubusercontent.com/72318314/123128515-7f66a080-d44b-11eb-8155-1d2c4aca8bcb.png">

### Template for writing an aggregation-module
    import java.util.ArrayList;

    import model.Satellite;
    import model.aggregations.IAggregate;
    import model.container.CompositeContainer;

    public class AGGREGATE_Template_Module implements IAggregate {

      private static final String NAME = "Decent Name of Class";
      private static final String DESCRIPTION = "<HTML><pre width=220px>"
          + "- Description: A\n"
          + "	- of: i\n"
          + "	- implemented: ii\n"
          + "	- Module: iii\n"
          + "</pre></HTML>";

      @Override
      public String getName() {
        return NAME;
      }

      @Override
      public String getDescription() {
        return DESCRIPTION;
      }

      @Override
      public CompositeContainer aggregate(ArrayList<Satellite> satellites) {
        // TODO Auto-generated method stub
        return null;
      }

    }


### Template for writing an output-module
    import model.container.CompositeContainer;
    import view.output.IOutput;

    public class OUTPUT_Template_Module implements IOutput {

      private static final String NAME = "Decent Name of Class";
      private static final String DESCRIPTION = "<HTML><pre width=300px>Description of implemented module.</pre></HTML>";

      @Override
      public String getDescription() {
        return DESCRIPTION;
      }

      @Override
      public String getName() {
        return NAME;
      }

      @Override
      public void output(CompositeContainer root) {
        // TODO Auto-generated method stub

      }

      @Override
      public void reset() {
        // TODO Auto-generated method stub
      }

    }

## Appenxix
### UML-Class-Diagram
![UML_Classes](https://user-images.githubusercontent.com/72318314/123143049-64e7f380-d45a-11eb-89eb-32cad2e9158c.png)

### Code-Coverage
<img src="https://user-images.githubusercontent.com/72318314/123143003-56014100-d45a-11eb-81c7-2fb23cdcf335.png" width="600">

### [Javadoc](https://inf19150.github.io/SWE_Projekt/doc/)

## References
- [Java Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html)
- [JUnit-Tests](https://junit.org/junit5/docs/current/user-guide/)
- [Gson](https://github.com/google/gson)
