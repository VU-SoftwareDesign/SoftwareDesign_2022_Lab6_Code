package softwaredesign.projectManager;

public class EstimatedTimeVisitor implements TaskVisitor {

    private int estimatedTime = 0;

    @Override
    public void visit(Task task) {
        this.estimatedTime += task.getEstimateTime();
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }
}
