package com.bcwms;

public class BcwConstant {

    public static final String SEARCH_NONE_ID="-999999";//一个默认值,表示什么都查不到
    public static final String ATTACH_MIN_IMG="x300";//按高等比例压缩
    public static final int ATTACH_MIN_IMG_INT=300;

    //上传的文件夹里面的子文件夹
    public static final String FOLDER_IMG="img";
    public static final String FOLDER_CARD="card";
    public static final String FOLDER_VIDEO="video";
//    public static final String FOLDER_CAPTURE="capture";//截屏

    //全局通用统一的type,与实际的数据库实体关联
    public static final String TYPE_EVENT="event";//活动
    public static final String TYPE_EVENT_RESULT="event_result";//活动结果
    public static final String TYPE_EVENT_COMMENT="event_comment";//活动评论
    public static final String TYPE_CREW="crew";
    public static final String TYPE_CREW_TERM="crew_term";//团期
    public static final String TYPE_CREW_TERM_EXAM="crew_term_exam";//考团
    public static final String TYPE_STUDIO="studio";
    public static final String TYPE_STYLE="style";//舞种
    public static final String TYPE_CLOCK="clock";//打卡
    public static final String TYPE_USER="user";//用户

    public static final String STATUS_0_ING="0";//等待审核-通过小程序报名活动后的状态
    public static final String STATUS_1_NO="1";//审核未通过/未激活/停课-活动签到后的状态
    public static final String STATUS_2_YES="2";//审核通过/已激活-考团打分后,活动进入n强后的状态
    public static final String STATUS_3_REMOVE="3";//删除
    public static final String STATUS_4_END="4";//结束/已结算(已上链)

    //通知类型
    public static final String NOTICE_PUBLISH="publish";
    public static final String NOTICE_TASK="task";



    public static final String ROLE_CREW_ADMIN="ROLE_CREW_ADMIN";//crew的管理人员权限
    public static final String ROLE_STUDIO_ADMIN="ROLE_STUDIO_ADMIN";//studio的管理人员权限


    public static final String CONFIRM_0_NOT="0";//未确认
    public static final String CONFIRM_1_ED="1";//已经确认


}
