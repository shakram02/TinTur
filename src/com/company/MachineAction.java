package com.company;

public enum MachineAction {
    Left,
    Right,
    Accept,
    Reject;

    @Override
    public String toString() {
        switch (this) {

            case Right:
                return "R";
            case Left:
                return "L";
            case Accept:
                return "Y";
            case Reject:
                return "N";
        }

        throw new IllegalStateException();
    }

    public static MachineAction fromString(String action) {
        action = action.toUpperCase();

        switch (action) {
            case "R":
                return MachineAction.Right;
            case "L":
                return MachineAction.Left;
            case "Y":
                return MachineAction.Accept;
            case "N":
                return MachineAction.Reject;

            default:
                throw new IllegalArgumentException("Invalid state");
        }
    }
}
