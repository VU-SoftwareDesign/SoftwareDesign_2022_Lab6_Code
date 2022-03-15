package softwaredesign.projectManager;

import java.util.List;

public class EmployeeFactory {

    public static enum EmployeeType {
        Internal,
        External,
        Manager
    }

    public static Employee getEmployee(EmployeeType type, String name, Double maxWorkHours, List<Skill> skills) {
        switch (type) {
            case Internal:
                if (maxWorkHours != null && skills != null) {
                    return new InternalEmployee(name, maxWorkHours, skills);
                }
                else {
                    return new InternalEmployee(name);
                }
            case External:
                if (maxWorkHours != null && skills != null) {
                    return new ExternalEmployee(name, maxWorkHours, skills);
                }
                else {
                    return new InternalEmployee(name);
                }
            case Manager:
                if (maxWorkHours != null && skills != null) {
                    return new Manager(name, maxWorkHours, skills);
                }
                else {
                    return new Manager(name);
                }
        }

        throw new RuntimeException("Could not resolve requested class. No constructors match.");
    }

    public static Employee getEmployee(EmployeeType type, String name) {
        switch (type) {
            case Internal:
                return new InternalEmployee(name);
            case External:
                return new ExternalEmployee(name);
            case Manager:
                return new Manager(name);
        }

        throw new RuntimeException("Could not resolve requested class. No constructors match.");
    }

}
