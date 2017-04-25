package bean;

import java.util.List;

/**
 * Created by CaesarFeng on 2017/4/15.
 */

public class HomeNewHeader {

    /**
     * status : 0
     * msg : OK
     * data : [{"bannerid":"1660","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/04/14/58f0ee5d9f1d8.jpg","description":"","userid":"927555","addtime":"1492184669","uptime":"1492184669","orderid":"6","cateid":"0","count_click":"0","status":"1","start_time":"1492185600","end_time":"1492271940","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"http:\\/\\/www.vmovier.com\\/fan243?inner_app=1\",\"app_show_type\":null}","extra_data":{"app_banner_type":"2","app_banner_param":"http://www.vmovier.com/fan243?inner_app=1","app_show_type":"","is_album":"0"}},{"bannerid":"1644","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/04/06/58e627b6da93a.jpg","description":"","userid":"551913","addtime":"1492166081","uptime":"1492166081","orderid":"4","cateid":"0","count_click":"607","status":"1","start_time":"1491478440","end_time":"0","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"https:\\/\\/kdt.im\\/QLJqvr\",\"app_show_type\":\"all\"}","extra_data":{"app_banner_type":"1","app_banner_param":"https://kdt.im/QLJqvr","app_show_type":"all"}},{"bannerid":"1658","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/04/13/58eee7abb4e1f.jpg","description":"","userid":"927555","addtime":"1492051883","uptime":"1492051883","orderid":"3","cateid":"0","count_click":"0","status":"1","start_time":"1492051883","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"51526\",\"app_show_type\":null}","extra_data":{"app_banner_type":"2","app_banner_param":"51526","app_show_type":"","is_album":"0"}},{"bannerid":"1639","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/04/05/58e441930a717.jpg","description":"","userid":"927555","addtime":"1491354003","uptime":"1491354003","orderid":"2","cateid":"0","count_click":"4717","status":"1","start_time":"1491354003","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"51481\",\"app_show_type\":null}","extra_data":{"app_banner_type":"2","app_banner_param":"51481","app_show_type":"","is_album":"0"}},{"bannerid":"1600","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2017/03/17/58cbd2501e6f3.jpg","description":"","userid":"962838","addtime":"1489851828","uptime":"1489851828","orderid":"1","cateid":"0","count_click":"4253","status":"1","start_time":"1489752600","end_time":"0","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"http:\\/\\/www.xinpianchang.com\\/activity\\/independence\\/ts-magicfood\",\"app_show_type\":\"all\"}","extra_data":{"app_banner_type":"1","app_banner_param":"http://www.xinpianchang.com/activity/independence/ts-magicfood","app_show_type":"all"}}]
     */

    private String status;
    private String msg;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bannerid : 1660
         * type : 7
         * object_id : 0
         * title :
         * url : /
         * image : http://cs.vmoiver.com/Uploads/Banner/2017/04/14/58f0ee5d9f1d8.jpg
         * description :
         * userid : 927555
         * addtime : 1492184669
         * uptime : 1492184669
         * orderid : 6
         * cateid : 0
         * count_click : 0
         * status : 1
         * start_time : 1492185600
         * end_time : 1492271940
         * extra : {"app_banner_type":"2","app_banner_param":"http:\/\/www.vmovier.com\/fan243?inner_app=1","app_show_type":null}
         * extra_data : {"app_banner_type":"2","app_banner_param":"http://www.vmovier.com/fan243?inner_app=1","app_show_type":"","is_album":"0"}
         */

        private String bannerid;
        private String type;
        private String object_id;
        private String title;
        private String url;
        private String image;
        private String description;
        private String userid;
        private String addtime;
        private String uptime;
        private String orderid;
        private String cateid;
        private String count_click;
        private String status;
        private String start_time;
        private String end_time;
        private String extra;
        private ExtraDataBean extra_data;

        public String getBannerid() {
            return bannerid;
        }

        public void setBannerid(String bannerid) {
            this.bannerid = bannerid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getObject_id() {
            return object_id;
        }

        public void setObject_id(String object_id) {
            this.object_id = object_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getUptime() {
            return uptime;
        }

        public void setUptime(String uptime) {
            this.uptime = uptime;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getCateid() {
            return cateid;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public String getCount_click() {
            return count_click;
        }

        public void setCount_click(String count_click) {
            this.count_click = count_click;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public ExtraDataBean getExtra_data() {
            return extra_data;
        }

        public void setExtra_data(ExtraDataBean extra_data) {
            this.extra_data = extra_data;
        }

        public static class ExtraDataBean {
            /**
             * app_banner_type : 2
             * app_banner_param : http://www.vmovier.com/fan243?inner_app=1
             * app_show_type :
             * is_album : 0
             */

            private String app_banner_type;
            private String app_banner_param;
            private String app_show_type;
            private String is_album;

            public String getApp_banner_type() {
                return app_banner_type;
            }

            public void setApp_banner_type(String app_banner_type) {
                this.app_banner_type = app_banner_type;
            }

            public String getApp_banner_param() {
                return app_banner_param;
            }

            public void setApp_banner_param(String app_banner_param) {
                this.app_banner_param = app_banner_param;
            }

            public String getApp_show_type() {
                return app_show_type;
            }

            public void setApp_show_type(String app_show_type) {
                this.app_show_type = app_show_type;
            }

            public String getIs_album() {
                return is_album;
            }

            public void setIs_album(String is_album) {
                this.is_album = is_album;
            }
        }
    }
}
