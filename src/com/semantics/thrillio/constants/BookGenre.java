package com.semantics.thrillio.constants;

public enum BookGenre {
    
     ART("Art"),
     BIOGRAPHY("Biography"),
     CHILDREN("Children"),
     FICTION("Fiction"),
     HISTORY("History"),
     MYSTERY("Mystery"),
     PHILOSOPHY("Philosophy"),
     RELIGION("Religion"),
     Romance("Romance"),
     SELF_HELP("Self Help"),
     TECHNICAL("Technical");

     private BookGenre(String name){

         this.name = name;
     }

     private String name;

    public String getName() {
        return name;
    }
}
