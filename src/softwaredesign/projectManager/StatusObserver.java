package softwaredesign.projectManager;

public interface StatusObserver {

    void update(Status.Progress oldProgress, Status.Progress newProgress);
}
