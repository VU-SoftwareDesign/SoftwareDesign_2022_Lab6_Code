package softwaredesign.projectManager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MainStage extends Stage {

    private TabPane tabPane = new JFXTabPane();
    private Map<Tab, Project> tabs = new HashMap<>();

    public MainStage() {
        setTitle("Project manager");

        populateTabPane();

        GridPane pane = new GridPane();

        MenuBar menuBar = createMenuBar();

        pane.add(menuBar,0, 0);
        pane.add(tabPane, 0, 1);

        GridPane.setHgrow(menuBar, Priority.ALWAYS);
        GridPane.setHgrow(tabPane, Priority.ALWAYS);

        Scene scene = new Scene(pane, 600, 400);

        setScene(scene);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem newProject = new MenuItem("New project");
        newProject.setOnAction(event -> newProjectClick());

        MenuItem addTaskList = new MenuItem("Add task list");
        addTaskList.setOnAction(event -> addTaskListClick());

        fileMenu.getItems().add(newProject);
        fileMenu.getItems().add(addTaskList);

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    private void populateTabPane() {
        tabPane.getTabs().clear();
        tabs.clear();

        for (Project project : Application.getInstance()) {
            Tab tab = new Tab(project.getName());

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(10, 10, 10, 10));

            for (TaskList taskList : project.getTaskLists()) {
                VBox vBox = new VBox();

                Label taskListName = new Label(taskList.getName());
                taskListName.setFont(Font.font(16));

                vBox.getChildren().add(taskListName);

                for (Task task : taskList) {
                    VBox taskVBox = new VBox();

                    Label taskName = new Label(task.getTaskName());
                    taskName.setFont(Font.font(14));
                    Label progress = new Label(task.getStatus().getStatus());

                    taskVBox.getChildren().add(taskName);
                    taskVBox.getChildren().add(progress);

                    taskVBox.setPadding(new Insets(10, 10, 10, 10));

                    vBox.getChildren().add(taskVBox);
                }

                Button button = new JFXButton("Add task");
                button.setOnAction(event -> addTaskClick(taskList));

                vBox.getChildren().add(button);

                hBox.getChildren().add(vBox);
            }

            tab.setContent(hBox);

            tabPane.getTabs().add(tab);

            tabs.put(tab, project);
        }
    }

    private void newProjectClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input name");
        dialog.setHeaderText(null);

        Optional<String> name = dialog.showAndWait();

        if (!name.isPresent()) {
            return;
        }

        Application.getInstance().addProject(new Project(name.get(), new ArrayList<>(), new ArrayList<>()));

        populateTabPane();
    }

    private void addTaskListClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input name");
        dialog.setHeaderText(null);

        Optional<String> name = dialog.showAndWait();

        if (!name.isPresent()) {
            return;
        }

        Project project = tabs.get(tabPane.getSelectionModel().getSelectedItem());
        Project newProject = project.addTaskList(new TaskList(name.get()));

        Application.getInstance().removeProject(project);
        Application.getInstance().addProject(newProject);

        populateTabPane();
    }

    private void addTaskClick(TaskList taskList) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input name");
        dialog.setHeaderText(null);

        Optional<String> name = dialog.showAndWait();

        if (!name.isPresent()) {
            return;
        }

        Task task = new Task(name.get(), 0, new ArrayList<>(), new Status(Status.Progress.EXECUTING), new ArrayList<>());
        TaskList newTaskList = taskList.addTask(task);

        Project project = tabs.get(tabPane.getSelectionModel().getSelectedItem());
        Project newProject = project.replaceTaskList(taskList, newTaskList);

        Application.getInstance().removeProject(project);
        Application.getInstance().addProject(newProject);

        populateTabPane();
    }
}
