package com.example.ngensdroid;

public class URLs {

    private static final String ROOT_URL = "http://192.168.1.100/api_ngens/Api.php?apicall=";

    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN= ROOT_URL + "login";
    public static final String UPLOAD_URL = ROOT_URL + "uploadorder";
    public static final String UPDATE_URL = ROOT_URL + "updateorder";
    public static final String TAKEORDER_URL = ROOT_URL + "takeorder";
    public static final String DELETEORDER_URL = ROOT_URL + "deleteorder";
    public static final String CANCELORDER_URL = ROOT_URL + "cancelorder";
    public static final String FINISHORDER_URL = ROOT_URL + "finishorder";


}