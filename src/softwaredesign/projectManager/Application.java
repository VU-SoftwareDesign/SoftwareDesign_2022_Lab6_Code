package softwaredesign.projectManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application implements Iterable<Project> {
    private final List<Project> projects;
    private static Application instance;

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    private Application() {
        projects = new ArrayList<>();
    }

    public void addProject(Project proj) {
        projects.add(proj);
    }

    public void removeProject(Project proj) {
        projects.remove(proj);
    }

    @Override
    public Iterator<Project> iterator() {
        return projects.iterator();
    }
}
