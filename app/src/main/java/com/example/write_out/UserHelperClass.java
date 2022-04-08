package com.example.write_out;

import java.util.Locale;

public class UserHelperClass {
    String UserName=" ",ArticleTitle=" ",Category=" ",dateOfPublication=" ",Articlebody="  ";

    public UserHelperClass() {

    }

   public UserHelperClass(String userName,String ArticleTitle,String Category,String DateOfPublication,String Articlebody) {
        this.UserName =userName;
        this.ArticleTitle = ArticleTitle;
        this.Category = Category;
        this.dateOfPublication = DateOfPublication;
        this.Articlebody = Articlebody;


    }



    public String getUserName() {
        return UserName;
    }

    public String getArticleTitle() { return ArticleTitle; }

    public String getCategory(){ return Category;}

    public String getDateOfPub() {
        return dateOfPublication;
    }

    public String getArticleBody() { return Articlebody;}
}
