package softwaredesign.projectManager;

import java.util.List;

public class InternalEmployee extends Employee{

    public InternalEmployee(String name, double maxWorkHours, List<Skill> skills) {
        super(EmployeeFactory.EmployeeType.Internal, name, maxWorkHours, skills);
    }

    public InternalEmployee(EmployeeFactory.EmployeeType type, String name, double maxWorkHours, List<Skill> skills) {
        super(type, name, maxWorkHours, skills);
    }

    public InternalEmployee(String name) {
        super(EmployeeFactory.EmployeeType.Internal, name);
    }

    public InternalEmployee(EmployeeFactory.EmployeeType type, String name) {
        super(type, name);
    }

    @Override
    protected Employee copyAdditionalFields(Employee old) {
        return old;
    }
}
