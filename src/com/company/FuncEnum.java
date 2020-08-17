package com.company;

public enum  FuncEnum {
    Print,
    Help,
    Exit,
    Clear;

    static FuncEnum getType(String str){
        return switch (str.toLowerCase()) {
            case "print" -> FuncEnum.Print;
            case "help" -> FuncEnum.Help;
            case "exit" -> FuncEnum.Exit;
            case "clear" -> FuncEnum.Clear;
            default -> FuncEnum.Print;
        };
    }
}
