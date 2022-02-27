package com.app;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Article {

    public int id;
    public String title;
    public String body;
    public LocalDateTime regDate;
    public LocalDateTime updateDate;

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}