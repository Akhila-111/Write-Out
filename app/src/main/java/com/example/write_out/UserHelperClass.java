package com.example.write_out;

import java.util.Locale;

public class UserHelperClass {
    String userName,ArticleTitle,Category,DateOfPublication,ArticleBody;

    public UserHelperClass() {

    }

    public UserHelperClass(String userName, String articleTitle, String category, String dateOfPublication,String body) {
        this.userName = userName;
        this.ArticleTitle = articleTitle;
        this.Category = category;
        this.DateOfPublication = dateOfPublication;
        this.ArticleBody = body;

    }


    public String getUserName() {
        return userName;
    }

    public String getArticleTitle() {
        return ArticleTitle;
    }

    public String getCategory(){ return Category;}

    public String getDateOfPublication() {
        return DateOfPublication;
    }

    public String getBody() { return ArticleBody;}
}
