package com.example.write_out;

import java.util.Locale;

public class UserHelperClass {
    String articleBody = " ", articleTitle = " ", category = " ", dateOfPublication = " ", userName = " ";

    public UserHelperClass() {

    }

    public UserHelperClass(String userName, String articleTitle, String category, String dateOfPublication, String articleBody) {
        this.articleBody = articleBody;
        this.articleTitle = articleTitle;
        this.category = category;
        this.dateOfPublication = dateOfPublication;
        this.userName = userName;

    }


    public String getUserName() {
        return userName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getCategory() {
        return category;
    }

    public String getdateOfPublication() {
        return dateOfPublication;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
