package softwaredesign.projectManager;

import java.util.ArrayList;
import java.util.List;

public class Manager extends InternalEmployee{
    private final List<Employee> subordinates;

    public Manager(String name, double maxWorkHours, List<Skill> skills, List<Employee> subordinates) {
        super(EmployeeFactory.EmployeeType.Manager, name, maxWorkHours, skills);
        this.subordinates = new ArrayList<>(subordinates);
    }

    public Manager(String name, double maxWorkHours, List<Skill> skills) {
        super(EmployeeFactory.EmployeeType.Manager, name, maxWorkHours, skills);
        this.subordinates = new ArrayList<>();
    }

    public Manager(String name) {
        super(EmployeeFactory.EmployeeType.Manager, name);
        this.subordinates = new ArrayList<>();
    }

    public List<Employee> getSubordinates() {
        return this.subordinates;
    }

    public Manager setSubordinates(List<Employee> subordinates) {
        return new Manager(super.getName(), super.getHours(), super.getSkills(), new ArrayList<>(subordinates));
    }

    @Override
    protected Employee copyAdditionalFields(Employee old) {
        Manager oldManager = (Manager) old;
        return setSubordinates(oldManager.getSubordinates());
    }

    @Override
    public String toString() {
        return "Manager [subordinates=" + subordinates + ", details=" + super.toString() + "]";
    }
}
