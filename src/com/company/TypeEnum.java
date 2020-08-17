package com.company;

public enum TypeEnum {

    X, S, M, ALL;

    static TypeEnum getType(String str){
        return switch (str.toLowerCase()) {
            case "x" -> TypeEnum.X;
            case "s" -> TypeEnum.S;
            case "m" -> TypeEnum.M;
            default -> null;
        };
    }
}