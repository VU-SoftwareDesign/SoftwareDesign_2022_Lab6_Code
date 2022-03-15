package softwaredesign.projectManager;


import java.util.HashSet;
import java.util.Set;

public class Status {
    //Enumeration of all the status --> DONE
    //Changes made to task as a result. Check there for relevant changes
    private final Progress currentStatus;

    private final Set<StatusObserver> observers = new HashSet<>();

    //To change status of Progress, you can only choose so from the enumeration below.
    enum Progress {
        READY {
            @Override
            public String toString() {
                return "Task ready to start";
            }
        },
        EXECUTING {
            @Override
            public String toString() {
                return "Task in Progress";
            }
        },
        FINISHED {
            @Override
            public String toString() {
                return "Task finished!";
            }
        }
    }

    public Status(Progress currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Status () {
        this.currentStatus = Progress.READY;
    }

    public void printStatus () {
        System.out.println(this.currentStatus);
    }

    public Status setStatus(Progress progress) {
        notifyObservers(progress);

        return new Status(progress);
    }

    public void addObserver(StatusObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Progress progress) {
        for (StatusObserver observer : observers) {
            observer.update(currentStatus, progress);
        }
    }
}
