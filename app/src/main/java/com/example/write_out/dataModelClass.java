package com.example.write_out;

public class dataModelClass
{
    String userName,ArticleTitle,Category,DateOfPublication,ArticleBody;

    dataModelClass(){

    }

    public dataModelClass(String userName, String articleTitle, String category, String dateOfPublication) {
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

    public String getCategory() {
        return Category;
    }

    public String getDateOfPublication() {
        return DateOfPublication;
    }


}
