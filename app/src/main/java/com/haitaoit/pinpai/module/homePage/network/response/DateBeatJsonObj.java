package com.haitaoit.pinpai.module.homePage.network.response;

import java.util.List;

/**
 * Created by LZY on 2017/12/19.
 */

public class DateBeatJsonObj {

    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : [{"goods_id":45,"dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201513419020.jpg"},{"img_url":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201513488292.jpg"},{"img_url":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201513552389.jpg"}],"title":"男士黑色中长款连帽抽身舒适派克夹克大衣外套|64I40ABLK","addtime":"2017-12-20","source":"上海","type":1,"is_colllect":1,"user_name":"125***.com","avatar":"http://linb.hai-tao.net/upload/201712/15/17121514485856447712.jpg"},{"goods_id":44,"dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932198834.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712190932249801.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201442242330.png"}],"title":"乐町2017秋冬新款羽绒服（每个ID限购5件）","addtime":"2017-12-18","source":"上海","type":1,"is_colllect":1,"user_name":"125***.com","avatar":"http://linb.hai-tao.net/upload/201712/15/17121514485856447712.jpg"},{"goods_id":43,"dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712191351441246.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712191351488717.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712191351532984.png"}],"title":"①韩都衣舍韩版女装冬装新款羽绒服（每个ID限购5件）","addtime":"2017-12-18","source":"上海","type":2,"is_colllect":2,"user_name":"610***.com","avatar":"http://linb.hai-tao.net/images/userphoto.jpg"},{"goods_id":36,"dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712191512020445.png"}],"title":"ONLY毛领收腰修身羽绒服|117312502（每个ID限购5件）","addtime":"2017-12-18","source":"北京","type":1,"is_colllect":2,"user_name":"125***.com","avatar":"http://linb.hai-tao.net/upload/201712/15/17121514485856447712.jpg"},{"goods_id":22,"dyimglist":[{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712191512455592.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/19/thumb_1_201712191512513434.png"},{"img_url":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201445598297.jpg"}],"title":"欧时力花朵刺绣棒球款羽绒外套（每个ID限购5件）","addtime":"2017-12-18","source":"北京","type":1,"is_colllect":2,"user_name":"125***.com","avatar":"http://linb.hai-tao.net/upload/201712/15/17121514485856447712.jpg"}]
     */

    private int ErrCode;
    private String ErrMsg;
    private List<ResponseBean> Response;

    public int getErrCode() {
        return ErrCode;
    }

    public void setErrCode(int ErrCode) {
        this.ErrCode = ErrCode;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public List<ResponseBean> getResponse() {
        return Response;
    }

    public void setResponse(List<ResponseBean> Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        /**
         * goods_id : 45
         * dyimglist : [{"img_url":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201513419020.jpg"},{"img_url":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201513488292.jpg"},{"img_url":"http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201513552389.jpg"}]
         * title : 男士黑色中长款连帽抽身舒适派克夹克大衣外套|64I40ABLK
         * addtime : 2017-12-20
         * source : 上海
         * type : 1
         * is_colllect : 1
         * user_name : 125***.com
         * avatar : http://linb.hai-tao.net/upload/201712/15/17121514485856447712.jpg
         */

        private String goods_id;
        private String title;
        private String addtime;
        private String source;
        private String type;
        private String is_colllect;
        private String user_name;
        private String avatar;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        private String totalCount;
        private List<DyimglistBean> dyimglist;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_colllect() {
            return is_colllect;
        }

        public void setIs_colllect(String is_colllect) {
            this.is_colllect = is_colllect;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public List<DyimglistBean> getDyimglist() {
            return dyimglist;
        }

        public void setDyimglist(List<DyimglistBean> dyimglist) {
            this.dyimglist = dyimglist;
        }

        public static class DyimglistBean {
            /**
             * img_url : http://linb.hai-tao.net/upload/201712/20/thumb_1_201712201513419020.jpg
             */

            private String img_url;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}
