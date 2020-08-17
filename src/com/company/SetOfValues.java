package com.company;

import java.util.*;

public class SetOfValues {

    public boolean hasOthers = false;
    public Map<TypeEnum, List<Integer>> list = new HashMap<>();

    SetOfValues(String input)
    {
        this.initList();
        String[] strArray = input.split(",|\\s+");
        if(strArray.length < 1)
            return;

        List<Integer> intList = new ArrayList<>();

        for (String s : strArray) {
            Integer nextNumber;
            try {
                nextNumber = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                continue;
            }
            if (intList.contains(nextNumber))
                continue;

            intList.add(nextNumber);
            List<TypeEnum> typeEnumList = new ArrayList<>();
            if (nextNumber % 21 == 0)
                typeEnumList = Arrays.asList(TypeEnum.X, TypeEnum.S, TypeEnum.M);
            else if (nextNumber % 7 == 0)
                typeEnumList.add(TypeEnum.X);
            else if (nextNumber % 3 == 0)
                typeEnumList.add(TypeEnum.S);
            else {
                this.hasOthers = true;
                continue;
            }
            for (TypeEnum t : typeEnumList) {
                this.setToList(t, nextNumber);
            }
        }
    }

    public void clearList(TypeEnum key)
    {
        if(key == TypeEnum.ALL)
            this.initList();
        else
            this.setForKey(key, new ArrayList<>());
    }

    private void initList(){
        for (TypeEnum t : TypeEnum.values()) {
            if (t == TypeEnum.ALL)
                break;
            this.setForKey(t, new ArrayList<>());
        }
    }

    private void setToList(TypeEnum key, Integer value)
    {
        var item = this.list.get(key);
        item.add(value);
        this.setForKey(key, item);
    }

    private void setForKey(TypeEnum key, List<Integer> value)
    {
        this.list.put(key, value);
    }

}