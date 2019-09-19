package com.haitaoit.pinpai.module.loginPage.network.response;

/**
 * Created by LZY on 2017/12/13.
 */

public class GetH5Obj {
    /**
     * ErrCode : 0
     * ErrMsg : 成功
     * Response : {"content":"<div style=\"width:100%;\"><p style=\"text-indent:2em;\">\r\n\t&emsp;12日晚间的朋友圈，被一则窦靖童\u201c剃度\u201d的视频刷爆了，视频中，窦靖童安静的坐在椅子上，负责剃发的还是宗萨蒋扬钦哲仁波切，结尾处还隐约露出羞涩笑容。\r\n<\/p>\r\n<p style=\"text-indent:2em;\">\r\n\t<p style=\"margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;\">\r\n\t\t&emsp;&emsp;结果就在大家都以为窦靖童名正言顺响应\u201c第一批90后已经出家\u201d的时代号召时，窦靖童工作人员通过媒体发出正式回应：\u201c纯粹是剃光让头发重新长，真不是出家，误会了！\u201d\r\n\t<\/p>\r\n\t<p style=\"margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;\">\r\n\t\t&emsp;&emsp;这则热传的视频背后，当然有两个关键词，窦靖童+90后。窦靖童一直很红，90后最近特别红，从《第一批90后已经开始油腻了》、《第一批90后已经离婚了》到《第一批90后的胃已经垮了》《第一批90后已经秃了》，最近流行的是，《第一批90后已经出家了》，于是窦靖童一个剃头的举动很容易被理解为成为90后出家代言人。\r\n\t<\/p>\r\n\t<p style=\"margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;\">\r\n\t\t&emsp;&emsp;其实这么多10万+背后，无非是一句话：90后已经完掉了，垮掉了。\r\n\t<\/p>\r\n\t<p style=\"margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;\">\r\n\t\t&emsp;&emsp;说起来，一直特立独行的窦靖童似乎也某种程度上成为了90后代言人，虽然大众看不到窦靖童油腻、离婚、胃跨掉，看到她出个家也是对90后丧掉的某种坐实。\r\n\t<\/p>\r\n\t<p style=\"margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;\">\r\n\t\t&emsp;&emsp;可是抱歉啊，窦靖童和90后，都不是你们想的那个样子。\r\n\t<\/p>\r\n\t<p style=\"margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;\">\r\n\t\t&emsp;&emsp;<strong>窦靖童剃度？成90后出家代言人？90后已辟谣！<\/strong>\r\n\t<\/p>\r\n\t<p style=\"margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;\">\r\n\t\t&emsp;&emsp;窦靖童也好一阵没有出现在新闻中了，或者说，这是最近窦靖童唯一跟自己有关的爆款新闻了吧。\r\n\t<\/p>\r\n\t<p style=\"margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;\">\r\n\t\t&emsp;&emsp;之前大家说起窦靖童，多半还是她的父母：王菲和窦唯。\r\n\t<\/p>\r\n<\/p><\/div>"}
     */

    private int ErrCode;
    private String ErrMsg;
    private ResponseBean Response;

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

    public ResponseBean getResponse() {
        return Response;
    }

    public void setResponse(ResponseBean Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        /**
         * content : <div style="width:100%;"><p style="text-indent:2em;">
         * &emsp;12日晚间的朋友圈，被一则窦靖童“剃度”的视频刷爆了，视频中，窦靖童安静的坐在椅子上，负责剃发的还是宗萨蒋扬钦哲仁波切，结尾处还隐约露出羞涩笑容。
         * </p>
         * <p style="text-indent:2em;">
         * <p style="margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;">
         * &emsp;&emsp;结果就在大家都以为窦靖童名正言顺响应“第一批90后已经出家”的时代号召时，窦靖童工作人员通过媒体发出正式回应：“纯粹是剃光让头发重新长，真不是出家，误会了！”
         * </p>
         * <p style="margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;">
         * &emsp;&emsp;这则热传的视频背后，当然有两个关键词，窦靖童+90后。窦靖童一直很红，90后最近特别红，从《第一批90后已经开始油腻了》、《第一批90后已经离婚了》到《第一批90后的胃已经垮了》《第一批90后已经秃了》，最近流行的是，《第一批90后已经出家了》，于是窦靖童一个剃头的举动很容易被理解为成为90后出家代言人。
         * </p>
         * <p style="margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;">
         * &emsp;&emsp;其实这么多10万+背后，无非是一句话：90后已经完掉了，垮掉了。
         * </p>
         * <p style="margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;">
         * &emsp;&emsp;说起来，一直特立独行的窦靖童似乎也某种程度上成为了90后代言人，虽然大众看不到窦靖童油腻、离婚、胃跨掉，看到她出个家也是对90后丧掉的某种坐实。
         * </p>
         * <p style="margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;">
         * &emsp;&emsp;可是抱歉啊，窦靖童和90后，都不是你们想的那个样子。
         * </p>
         * <p style="margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;">
         * &emsp;&emsp;<strong>窦靖童剃度？成90后出家代言人？90后已辟谣！</strong>
         * </p>
         * <p style="margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;">
         * &emsp;&emsp;窦靖童也好一阵没有出现在新闻中了，或者说，这是最近窦靖童唯一跟自己有关的爆款新闻了吧。
         * </p>
         * <p style="margin-top:15px;margin-bottom:15px;padding:0px;border:0px;list-style:none;font-size:14px;line-height:22px;color:#333333;font-family:'Microsoft YaHei', 微软雅黑, SimSun, 宋体;white-space:normal;background-color:#FFFFFF;">
         * &emsp;&emsp;之前大家说起窦靖童，多半还是她的父母：王菲和窦唯。
         * </p>
         * </p></div>
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
