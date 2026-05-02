package com.quanta.api.network;

public enum NodeType {
    MACHINE("machine"),
    SWITCH("switch"),
    COMPUTER("computer"),
    MAINFRAME("mainframe"),
    SUPERCOMPUTER("supercomputer"),
    TERMINAL("terminal"),
    STORAGE("storage");

    private final String name;

    NodeType(String name) { this.name = name; }
    public String getName() { return name; }
}
