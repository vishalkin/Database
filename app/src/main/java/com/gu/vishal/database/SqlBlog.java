package com.gu.vishal.database;

/**
 * Created by vishal on 08/10/2016.
 */
public class SqlBlog {

    private  SqlDbHandler helper;

    String _author, _post;
    int _id;

    public SqlBlog() {

    }

    public SqlBlog(String _author, String _post, int _id) {
        this._author = _author;
        this._post = _post;
        this._id = _id;
    }

    public SqlBlog(String _post, String _author) {
        this._author = _author;
        this._post = _post;
    }

    public int get_id() {
        return this._id;
    }

    public String get_author() {
        return this._author;
    }

    public String get_post() {
        return this._post;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_author(String _author) {
        this._author = _author;
    }

    public void set_post(String _post) {
        this._post = _post;
    }
}


