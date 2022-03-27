package com.example.write_out;

public class WritingHelperClass {
    String ArticleBody ;
    String UserName;

    public WritingHelperClass(String articleBody,String userName) {
        ArticleBody = articleBody;
        UserName = userName;
    }

    public String getArticleBody() {
        return ArticleBody;
    }

    public String getUserName() {
        return UserName;
    }
}
