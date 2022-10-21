package model;

import java.util.List;

public record User(String name, List<Entry> entries) {}
