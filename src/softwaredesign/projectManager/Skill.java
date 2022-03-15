package softwaredesign.projectManager;

import java.util.HashMap;
import java.util.Map;

final public class Skill {
    private final String name;
    private static Map<String, Skill> instances;

    public static Skill getInstance(String name) {
        if (instances == null) {
            instances = new HashMap<>();
        }

        if (! instances.containsKey(name.toLowerCase())) {
            instances.put(name.toLowerCase(), new Skill(name));
        }

        return instances.get(name.toLowerCase());
    }

    private Skill(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Skill setName(String newSkill) {
        return new Skill(newSkill);
    }

    public int hashCode() {
        return this.name.toLowerCase().hashCode();
    }

    public boolean equals(Skill that) {
        return this.name.toLowerCase().equals(that.getName().toLowerCase());
    }
}