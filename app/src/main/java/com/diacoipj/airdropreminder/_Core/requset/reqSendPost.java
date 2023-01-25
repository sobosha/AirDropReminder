package com.diacoipj.airdropreminder._Core.requset;

public class reqSendPost {
    String question,title;
    int catId;

    public reqSendPost(String question, String title, int catId) {
        this.question = question;
        this.title = title;
        this.catId = catId;
    }
}
