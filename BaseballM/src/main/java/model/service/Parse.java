package model.service;


import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Parse {
    private String input;
    private String action;
    private String list;
    private int id;
    private String name;
    private String position;
    private String reason;

    public Parse(String input){
        this.input = input;
    }
    public void parsing(String input){
            setAction(input.split("\\?")[0]);
            setList(input.split("\\?")[1]);
    }
    public void parsingById(String list){
        setId(Integer.parseInt(list.split("=")[1]));
    }
    public void parsingByName(String list){
        setName(list.split("=")[1]);
    }
    public void parsingByIdAndName(String list){
        List<String> info = new ArrayList<>();
        for (int i = 0; i < list.split("&").length; i++) {
            info.add(list.split("&")[i].split("=")[1]);
        }
        setId(Integer.parseInt(info.get(0)));
        setName(info.get(1));
    }
    public void parsingByIdAndNameAndPosition(String list){
        List<String> info = new ArrayList<>();
        for (int i = 0; i < list.split("&").length; i++) {
            info.add(list.split("&")[i].split("=")[1]);
        }

        setId(Integer.parseInt(info.get(0)));
        setName(info.get(1));
        setPosition(info.get(2));
    }
    public void parsingByIdAndReason(String list){
        List<String> info = new ArrayList<>();
        for (int i = 0; i < list.split("&").length; i++) {
            info.add(list.split("&")[i].split("=")[1]);
        }
        setId(Integer.parseInt(info.get(0)));
        setReason(info.get(1));
    }

}
