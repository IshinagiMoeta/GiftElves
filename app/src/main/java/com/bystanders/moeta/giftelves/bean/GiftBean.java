package com.bystanders.moeta.giftelves.bean;

import java.util.List;

/**
 * Created by Ishinagi_moeta on 2016/9/28.
 */
public class GiftBean {

    /**
     * ad : [{"id":251,"title":"把妹大作战独家国庆礼包","flag":1,"iconurl":"/allimgs/img_iad/_1475060682462.jpg","addtime":"2016-09-19","giftid":"1475053440","appName":"把妹大作战","appLogo":"/allimgs/img_iapp/201607/_1468919290136.jpg","appId":1460364739},{"id":249,"title":"绝世武神独家礼包（IOS）","flag":1,"iconurl":"/allimgs/img_iad/_1474623667919.jpg","addtime":"2016-09-17","giftid":"1474618069","appName":"绝世武神","appLogo":"/allimgs/img_iapp/201607/_1469611677571.jpg","appId":1444892111},{"id":246,"title":"天天炫舞中秋独家礼包","flag":1,"iconurl":"/allimgs/img_iad/_1473751476804.jpg","addtime":"2016-09-15","giftid":"1473749901","appName":"天天炫舞","appLogo":"/allimgs/img_iapp/201609/_1474340811708.png","appId":2496048},{"id":250,"title":"克鲁赛德战记国庆独家专题礼包","flag":1,"iconurl":"/allimgs/img_iad/_1474961155879.jpg","addtime":"2016-09-15","linkurl":"http://www.1688wan.com/libao/1474959513.html","giftid":"1474959513","appName":"克鲁赛德战记","appLogo":"/allimgs/img_iapp/201604/_1459853961237.png","appId":1435301325},{"id":243,"title":"航海王强者之路中秋媒体节日礼包","flag":1,"iconurl":"/allimgs/img_iad/_1473316667867.jpg","addtime":"2016-09-14","giftid":"1473316129","appName":"航海王强者之路","appLogo":"/allimgs/img_iapp/201511/_1448446144213.png","appId":1448446177}]
     * pageno : 182
     * list : [{"id":"1475027333","iconurl":"/uploads/allimg/131221/11-131221004415403.jpg","giftname":"独家礼包","number":19,"exchanges":1,"type":1,"gname":"王者之剑","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"2,","operators":"","flag":1},{"id":"1475029959","iconurl":"/allimgs/img_iapp/201609/_1474876231512.png","giftname":"iOS新手礼包","number":20,"exchanges":0,"type":1,"gname":"那兔之大国梦","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"2,","operators":"","flag":1},{"id":"1475036299","iconurl":"/allimgs/img_iapp/201609/_1474264951793.png","giftname":"十一国庆礼包","number":20,"exchanges":0,"type":1,"gname":"乱轰三国志","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"1,2,","operators":"","flag":1},{"id":"1475045414","iconurl":"/allimgs/img_iapp/201609/_1473736912113.png","giftname":"欢度国庆礼包","number":5,"exchanges":0,"type":1,"gname":"巴啦啦魔法变身3","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"1,","operators":"","flag":1},{"id":"1475045883","iconurl":"/allimgs/img_iapp/201604/_1460020330316.png","giftname":"国庆节礼包","number":20,"exchanges":0,"type":1,"gname":"小小三国","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"1,2,","operators":"","flag":1},{"id":"1475046373","iconurl":"/allimgs/img_iapp/201603/_1459331332138.jpg","giftname":"国庆礼包","number":10,"exchanges":0,"type":1,"gname":"号令三国","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"1,2,","operators":"","flag":1},{"id":"1475046932","iconurl":"/allimgs/img_iapp/201609/_1474964573614.png","giftname":"国庆礼包","number":21,"exchanges":0,"type":1,"gname":"我欲封天之至尊归来","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"1,2,","operators":"","flag":1},{"id":"1475047602","iconurl":"/allimgs/img_iapp/201508/_1440057674846.jpg","giftname":"国庆狂欢礼包","number":30,"exchanges":0,"type":1,"gname":"超能继承者","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"1,2,","operators":"","flag":1},{"id":"1475048765","iconurl":"/allimgs/img_iapp/201609/_1475048015078.png","giftname":"豪华礼包","number":50,"exchanges":0,"type":1,"gname":"三国情仇录","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"1,","operators":"","flag":1},{"id":"1475050931","iconurl":"/allimgs/img_iapp/201607/_1468925116148.png","giftname":"国庆节礼包","number":10,"exchanges":0,"type":1,"gname":"永恒星语","integral":0,"isintegral":0,"addtime":"2016-09-28","ptype":"1,","operators":"","flag":1}]
     */

    private int pageno;
    /**
     * id : 251
     * title : 把妹大作战独家国庆礼包
     * flag : 1
     * iconurl : /allimgs/img_iad/_1475060682462.jpg
     * addtime : 2016-09-19
     * giftid : 1475053440
     * appName : 把妹大作战
     * appLogo : /allimgs/img_iapp/201607/_1468919290136.jpg
     * appId : 1460364739
     */

    private List<AdBean> ad;
    /**
     * id : 1475027333
     * iconurl : /uploads/allimg/131221/11-131221004415403.jpg
     * giftname : 独家礼包
     * number : 19
     * exchanges : 1
     * type : 1
     * gname : 王者之剑
     * integral : 0
     * isintegral : 0
     * addtime : 2016-09-28
     * ptype : 2,
     * operators :
     * flag : 1
     */

    private List<ListBean> list;

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public List<AdBean> getAd() {
        return ad;
    }

    public void setAd(List<AdBean> ad) {
        this.ad = ad;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class AdBean {
        private int id;
        private String title;
        private int flag;
        private String iconurl;
        private String addtime;
        private String giftid;
        private String appName;
        private String appLogo;
        private int appId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getGiftid() {
            return giftid;
        }

        public void setGiftid(String giftid) {
            this.giftid = giftid;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppLogo() {
            return appLogo;
        }

        public void setAppLogo(String appLogo) {
            this.appLogo = appLogo;
        }

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }
    }

    public static class ListBean {
        private String id;
        private String iconurl;
        private String giftname;
        private int number;
        private int exchanges;
        private int type;
        private String gname;
        private int integral;
        private int isintegral;
        private String addtime;
        private String ptype;
        private String operators;
        private int flag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getGiftname() {
            return giftname;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getExchanges() {
            return exchanges;
        }

        public void setExchanges(int exchanges) {
            this.exchanges = exchanges;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIsintegral() {
            return isintegral;
        }

        public void setIsintegral(int isintegral) {
            this.isintegral = isintegral;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public String getOperators() {
            return operators;
        }

        public void setOperators(String operators) {
            this.operators = operators;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
