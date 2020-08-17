package com.company;
import java.util.Scanner;
import java.util.stream.Stream;

public class Program {

    SetOfValues values = null;
    TypeEnum type = TypeEnum.ALL;
    FuncEnum selectedFunction = FuncEnum.Print;
    boolean isFunc = false;
    public Program(){}

    public void Start(){

        System.out.println("задайте массив,\nhelp - для справки");
        Scanner sc = new Scanner(System.in);
        String nextInput = null;

        while (!"exit".equals(nextInput)) {
            nextInput = sc.nextLine();
            this.type = TypeEnum.ALL;
            this.initList(nextInput);

            if(this.values == null)
                continue;
            this.isFunc = false;
            this.initFunc(nextInput);
            if(this.isFunc && this.type == null){
                System.out.println("\n укажите ключ");
                continue;
            }
            this.type = this.isFunc ? this.type : TypeEnum.ALL;
            switch (this.selectedFunction) {
                case Print -> {
                    if (this.type == TypeEnum.ALL)
                        values.list.forEach((key, val) -> System.out.println(key + "   :   " + val));
                    else
                        System.out.println(values.list.get(this.type));
                }
                case Help -> this.printHelp();
                case Exit -> System.out.println("good bye");
                case Clear-> {
                    values.clearList(this.type);
                    System.out.println(this.type == TypeEnum.ALL ? "набор очищен" : this.type + " очищен");
                }
                default -> this.printHelp();
            }
        }
    }

    private void initList(String input){

        var splitInput = input.split("init ");
        if(splitInput.length > 1){
            this.selectedFunction = FuncEnum.Print;
            this.values = new SetOfValues(splitInput[1]);
            return;
        }
        if(this.values == null) {
            System.out.println("набор не инициализирован \n init массив 1,2 3 (через пробел или запятую) \n для большей информации введите help");
        }
    }
    private void initFunc(String input){
        FuncEnum[] arr = {FuncEnum.Clear, FuncEnum.Print};
        FuncEnum selectedFunction = null;
        TypeEnum type = null;
        for (FuncEnum a: arr){
            var commandStack= input.toLowerCase().split(a.toString().toLowerCase()+ " ");
            if (commandStack.length == 2) {
                selectedFunction = a;
                type = this.getType(commandStack[1]);
                break;
            }
        }
        this.isFunc = selectedFunction != null;
        this.selectedFunction = selectedFunction == null ? FuncEnum.getType(input) : selectedFunction;
        this.type = type == null ? TypeEnum.getType(input) : type;
    }
    private TypeEnum getType(String str){
        var type = TypeEnum.getType(str);
        if (type == null) {
            System.out.print("Доступные ключи: ");
            Stream.of(TypeEnum.values()).filter(t-> !t.equals(TypeEnum.ALL)).forEach(t-> System.out.print(t+" "));
            return  null;
        }
        return type;
    }
    private void printHelp(){
        System.out.println("\n help: " +
                "\n #######################################################################"+
                "\n init array, где array - набор чисел (ввести через пробел или запятую);" +
                "\n print вывод всех массивов " +
                "\n print type, где type: x,s,m"+
                "\n exit для завершения программы"+
                "\n #######################################################################");
    }
}
