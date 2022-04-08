package com.example.write_out;

import java.util.Locale;

public class UserHelperClass {
    String userName=" ",ArticleTitle=" ",Category=" ",DateOfPublication=" ",ArticleBody="  ";

    public UserHelperClass() {

    }

   public UserHelperClass(String UserName, String ArticleTitle, String Category, String DateOfPublication,String ArticleBody) {
        this.userName =UserName;
        this.ArticleTitle = ArticleTitle;
        this.Category = Category;
        this.DateOfPublication = DateOfPublication;
        this.ArticleBody = ArticleBody;

    }


    public String getUserName() {
        return userName;
    }

    public String getArticleTitle() { return ArticleTitle; }

    public String getCategory(){ return Category;}

    public String getDateOfPublication() {
        return DateOfPublication;
    }

    public String getArticleBody() { return ArticleBody;}
}
