import org.yaml.snakeyaml.util.UriEncoder;

public class Main {
    public static void main(String[] args) throws Exception{
        ExcelOperation excelOperation = new ExcelOperation("./src/main/resources/companyinfo.xlsx", "sheet");
        excelOperation.getData(2180, 2, "SINAGLOBAL=3292573029888.404.1554124198297; _s_tentry=www.baidu.com; Apache=8361542517050.313.1601187504634; ULV=1601187504680:21:2:1:8361542517050.313.1601187504634:1601107499455; webim_unReadCount=%7B%22time%22%3A1601193508480%2C%22dm_pub_total%22%3A3%2C%22chat_group_client%22%3A0%2C%22chat_group_notice%22%3A0%2C%22allcountNum%22%3A63%2C%22msgbox%22%3A0%7D; secsys_id=0c7f0347d967283e38bf2f61073e99bd; login_sid_t=4cf9a9fb2556985e1d615376693b1c9a; cross_origin_proto=SSL; UOR=,,login.sina.com.cn; crossidccode=CODE-yf-1KmrGq-3QzpOG-DrWSBIxFmyv8MXwf13ed8; ALF=1632729930; SSOLoginState=1601193930; SCF=AnQ3EJPxaOHvz7PtdUuyWQYmv50GjAqL_kkVlS6exG9tHLM3sPv3i7ebtqgb3qiss1tZ6Zdb59ScMngYZncGszQ.; SUB=_2A25ydDebDeRhGedG7lIW-CbIyziIHXVRAC5TrDV8PUNbmtANLU37kW9NUV9eKlKtzELXncewEoSaQWc0JemYVOjg; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhFoO4_bdkJVeroEd0RxRo-5JpX5KzhUgL.Fo2RSK5N1hnXehB2dJLoI0qLxKqL1-BLBK-LxKBLBonL12BLxK-L12qL122LxKML1-2L1hBLxKML1hnLBo2LxKqL1h2L12zt; SUHB=04WvTdmqE_Adf1; wvr=6");
    }
}
