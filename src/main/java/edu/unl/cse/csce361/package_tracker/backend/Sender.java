package edu.unl.cse.csce361.package_tracker.backend;

import java.util.HashSet;
import java.util.Set;

public class Sender {
    private int id;
    private Address address;
    private String name;
    private HashSet packageSet = new HashSet<Package>();

    public Sender () {
    }
}
