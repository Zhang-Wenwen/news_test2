package com.twtstudio.zhangwenwen;

import java.util.List;

/**
 * Created by Administrator on 2017/11/10.
 */

public class ListBean {

    /**
     * error_code : -1
     * message :
     * data : [{"index":"54192","subject":"携手素拓谊 共话两岸情","pic":"http://news.twt.edu.cn/public/news/wyynews/420_2017_10_15_00_10_20_cover_336.jpg","visitcount":"301","comments":0,"summary":"10月14日下午1时30分，时维九月，序数三秋。落叶飘零，天空中烟云笼罩，然而天津大学素..."},{"index":"54170","subject":"兴学之路：迎新交响合唱音乐会","pic":"http://news.twt.edu.cn/public/news/wyynews/420_2017_09_30_10_14_03_cover_326.jpg","visitcount":"332","comments":0,"summary":"\u201c自强首在储才，储才必先兴学。\u201d兴学之路，可以从音乐之美开始。9月29日晚7时，天津..."},{"index":"54116","subject":"\u201c贴心服务\u201d：校园网升级改造工作进行中","pic":"https://open.twtstudio.com/imgcache/3c537cbd4b2669dc0d75a75d76d72bca.jpg","visitcount":"402","comments":0,"summary":"\u201c夏日炎炎，最美好的事情莫过于空调、WiFi和西瓜。\u201d然而不少同学表示，在天津大学的..."},{"index":"53569","subject":"第九届方寸·流年\u201c触碰·触动\u201d主题摄影大赛开始作品征集","pic":"","visitcount":"168","comments":0,"summary":"本次摄影大赛由天津大学天外天新闻中心发起并主办，同时也将作为天津大学首届网络文化..."},{"index":"53471","subject":"国旗下的守护：全民国家安全教育日升旗仪式","pic":"/post/img/uploadFiles/2017_04_15_11_07_41_27.jpg","visitcount":"116","comments":0,"summary":"4月15日上午七时，全民国家安全教育日升旗仪式在天津大学双校区顺利举办。近200名国旗护..."},{"index":"53384","subject":"北洋园校区社团春季纳新活动成功举办","pic":"https://open.twtstudio.com/imgcache/dcc7890ee10f55482009eae331fcc572.jpg","visitcount":"174","comments":0,"summary":"春风十里只为你，百团翘首以盼卿。3月11日，北洋园结束一假期的寂静，人头攒动，热闹非..."},{"index":"52701","subject":"【新媒体时代，他们这样过大学生活】头条学院新媒体训练营招募天津大学宣讲会","pic":"http://news.twt.edu.cn/public/news/wyynews/420_2016_10_31_19_12_28_cover_281.jpg","visitcount":"106","comments":0,"summary":"你是否厌倦网上无趣的段子和有毒的鸡汤？你是否害怕未来的工作埋没你段子手的天赋？那..."},{"index":"51747","subject":"【诗人之恋】触动心灵的诗人音符","pic":"","visitcount":"137","comments":0,"summary":""},{"index":"51525","subject":"天津大学成功举办第七次CSP软件能力认证","pic":"https://open.twtstudio.com/imgcache/1e86f6c0ab7c3c06a1d768314b4b1c38.jpg","visitcount":"150","comments":0,"summary":"　　2016年4月10日，第七次CCF CSP软件能力认证在天津大学北洋园校区如期举行。本次认证，..."},{"index":"51509","subject":"【天大海棠季】青春北洋园 畅听天地间","pic":"https://open.twtstudio.com/imgcache/5d0fc6c6b74bdc92eaee55577df46d4a.jpg","visitcount":"77","comments":0,"summary":"4月9日上午9时，天津大学海棠节如期开幕，这也是北洋园校区第一次举办海棠季活动。社团..."},{"index":"50674","subject":"三院歌手争霸赛：歌唱青春 执着梦想","pic":"","visitcount":"149","comments":0,"summary":""},{"index":"50135","subject":"【2015军训】新的一天从晨训开始","pic":"https://open.twtstudio.com/imgcache/756dcf1f1c15e7716b4725eb85ae5283.jpg","visitcount":"76","comments":0,"summary":"&nbsp; &nbsp; &nbsp; &nbsp;天刚蒙蒙亮，宿舍走廊中便已发出了窸窸窣窣的声响。起床，洗漱，换..."}]
     */

    public int error_code;
    public String message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * index : 54192
         * subject : 携手素拓谊 共话两岸情
         * pic : http://news.twt.edu.cn/public/news/wyynews/420_2017_10_15_00_10_20_cover_336.jpg
         * visitcount : 301
         * comments : 0
         * summary : 10月14日下午1时30分，时维九月，序数三秋。落叶飘零，天空中烟云笼罩，然而天津大学素...
         */

        public String index;
        public String subject;
        public String pic;
        public String visitcount;
        public int comments;
        public String summary;
    }
}
