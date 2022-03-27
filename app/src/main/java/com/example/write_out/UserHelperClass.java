package com.example.write_out;

import java.util.Locale;

public class UserHelperClass {
    String userName,ArticleTitle,Category,DateOfPublication;

    public UserHelperClass(String userName, String articleTitle,String category, String dateOfPublication) {
        this.userName = userName;
        ArticleTitle = articleTitle;
        Category = category;
        DateOfPublication = dateOfPublication;

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
}
