package com.bcwms.bean.crew;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceCrewTermUserDetail;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(isNativeSql = true,isUseMapModel = true,
sql=" select ctud.id,ctud.userId,ctuds.score,ctuds.foundationScore,ctuds.routineScore,ctuds.soloScore,ctuds.otherScore, " +
        " u.aka,u.trueName,u.avatarUrl " +
        " from TblDanceCrewTermUserDetail ctud " +
        " left join TblDanceCrewTermUserDetailScore ctuds on ctuds.crewTermUserDetailId=ctud.id and ctuds.teacherId=:teacherId " +
        " left join TblDanceUser u on u.userId=ctud.userId " +
        " where ctud.eventId=:eventId " +
        " and ctud.crewId=:crewId and ctud.crewUserType= "+TblDanceCrewTermUserDetail.CREWUSERTYPE_2_MEMBER)

public class SearchCrewTermUserScoreExaming {

    @PaginationField
    public Integer eventId;

    @PaginationField
    public Integer crewId;

    @PaginationField
    public Integer teacherId;

    @PaginationField(sql=" and (ctuds.foundationScore>=0 or routineScore>=0 or soloScore>=0 or otherScore>=0) ")
    public String scoreStatus;

    @PaginationField(sql=" and ((ctuds.foundationScore is null or ctuds.foundationScore='') " +
            " && (ctuds.routineScore is null or ctuds.routineScore='') " +
            " && (ctuds.soloScore is null or ctuds.soloScore='')" +
            " && (ctuds.otherScore is null or ctuds.otherScore=''))")
    public String notScoreStatus;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public Integer getTeacherId() {
        return BcwUserHolder.getDanceUser().getUserId();
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getScoreStatus() {
        return scoreStatus;
    }

    public void setScoreStatus(String scoreStatus) {
        this.scoreStatus = scoreStatus;
    }

    public String getNotScoreStatus() {
        return notScoreStatus;
    }

    public void setNotScoreStatus(String notScoreStatus) {
        this.notScoreStatus = notScoreStatus;
    }
}
