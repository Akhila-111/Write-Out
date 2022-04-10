package com.example.write_out;

import java.util.Locale;

public class UserHelperClass {
    String userName=" ",articleTitle=" ",category=" ",dateOfPublication=" ",articleBody="  ";

    public UserHelperClass() {

    }

   public UserHelperClass(String userName,String articleTitle,String category,String dateOfPublication,String articleBody) {
        this.userName =userName;
        this.articleTitle = articleTitle;
        this.category = category;
        this.dateOfPublication = dateOfPublication;
        this.articleBody = articleBody;
    }



    public String getUserName() {
        return userName;
    }

    public String getArticleTitle() { return articleTitle; }

    public String getCategory(){ return category;}

    public String getdateOfPublication() {
        return dateOfPublication;
    }

    public String getArticleBody() { return articleBody;}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }
}
