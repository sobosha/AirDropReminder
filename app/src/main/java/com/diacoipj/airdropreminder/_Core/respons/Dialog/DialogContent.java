package com.diacoipj.airdropreminder._Core.respons.Dialog;

public class DialogContent {

    int id;
    String title , message  , roundIcon , image;
    DialogBtn ok  , cancel ;
    DialogStyle style ;
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public DialogBtn getOk() {
        return ok;
    }

    public DialogBtn getCancel() {
        return cancel;
    }

    public DialogStyle getStyle() {
        return style;
    }

    public String getRoundIcon() {
        return roundIcon;
    }

    public String getImage() {
        return image;
    }
}

