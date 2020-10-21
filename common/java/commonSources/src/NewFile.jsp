<%@page language="java" contentType="text/html; charset=utf-8"%>

<%@include file="/common/jsp/header.jsp"%>

<%--

Program Name  : tm_day_090_m01.jsp

Description   : 근태/근무신청 2018.변경분

Author        : 최현수

History       : 2018-12-21

                2020-10-19 박고은 수정

--%>

<%

  String S_C_CD      = StringUtil.nvl(request.getParameter("S_C_CD"), ehrbean.getCCD());

  String S_APPL_TYPE = StringUtil.nvl(request.getParameter("S_APPL_TYPE"));

%>

<html>

<head>

<title>:::</title>

<%@include file="/common/jsp/commonResource2.jsp"%>

<%@include file="/common/jsp/sys_appl.jsp"%>

<link href="/common/css/tam4.css" rel=stylesheet type="text/css">

<script language="javascript">
                                // 환경변수 QUOTA_TIME_YN

                                var quotaTimeYn = "N"; // 시간단위연차사용여부(환경변수)

                                var vmMaster; //신청서 Vue app

                                var isPopup = _isPopup;

                                if (isPopup) HCG.sizeDialog(900, 600);

                                function LoadPage()

                                {

                                    // 신청서에서 사용할 Vue app 정의

                                    initVueApp();



                                    //신청서 공통 뷰앱 세팅

                                    vmMaster.v_applInit(vmMaster);



                                    Vue.set(vmMaster.enable, "STA_YMD", false);

                                    Vue.set(vmMaster.enable, "END_YMD", false);



                                    <%--신규와 임시저장에서만 취소할 신청건 선택--%>

                                    if (Apply.formValue.S_APPL_MODE == 'APPL')

                                    {

                                        if (Apply.v_getApplStatus() <= Apply.TempSave)

                                        {

                                            //Vue.set(vmMaster.visivle,'popup01',true);

                                        }

                                    }



                                }



                                function initVueApp()

                                {

                                    var pageVueParam = {

                                        el: "#vmMaster"

                                        ,
                                        data:

                                        {

                                            dsClass: "<com:otp value='biz.tam.tm_day.Tm_day_090_m01'/>"

                                            ,
                                            dsMethod: ""

                                            ,
                                            condition:

                                            {

                                                S_APPL_ID: "<%=StringUtil.nvl(request.getParameter("
                                                S_APPL_ID "),"
                                                ")%>"

                                                ,
                                                S_STD_YMD: "<%=TO_YMD%>"

                                                ,
                                                S_TOYMD: "<%=TO_YMD%>"

                                            }

                                            ,
                                            apply:

                                            {

                                                S_APPR_PATH_ID: ""

                                            }

                                            ,
                                            formValue:

                                            {

                                                S_STA_YMD: ""

                                                ,
                                                S_END_YMD: ""

                                                ,
                                                S_ATTEND_CLASS: ""

                                                ,
                                                S_NOTE: ""

                                                ,
                                                S_DAYS_DUTIE_DAY: ""
                                                <%-- 달력일수 --%>

                                                ,
                                                S_WORK_DUTIE_DAY: ""
                                                <%-- 사용일수 --%>

                                                ,
                                                S_QUOTA_TIME_VAL: quotaTimeYn

                                                    <%-- 다중건인 데이터 값 문자열로 묶기 --%>

                                                ,
                                                S_ATTEND_YMDS: ""

                                                ,
                                                S_ALL_DAY_YNS: ""

                                                ,
                                                S_USE_HOURS: ""

                                                ,
                                                S_USE_MINS: ""

                                                ,
                                                S_STA_YMDS: ""

                                                ,
                                                S_STA_HMS: ""

                                                ,
                                                S_END_YMDS: ""

                                                ,
                                                S_END_HMS: ""

                                                ,
                                                STA_YMD_CLASS: ""

                                                ,
                                                END_YMD_CLASS: ""

                                                ,
                                                STAT_PER_CD: ""

                                                ,
                                                STAT_PER_NM: ""

                                                    <%-- 취소신청 데이터 formValue--%>

                                                ,
                                                S_DELETE_APPL_ID: ""

                                                ,
                                                S_APPL_INFO: ""

                                                ,
                                                S_DEL_NOTE: ""

                                            }

                                            ,
                                            arrAttendRule: <%=hmPreparedData.get("attendRule")%> ["default"]
                                            <%-- 근태기준데이터 --%>

                                            ,
                                            selAttendRule: {

                                                D_CNT_TYPE: ""

                                                ,
                                                USE_CNT: ""

                                                ,
                                                WORK_YN: ""

                                                ,

                                            }
                                            <%-- 선택된 근태/근무의 근태기준 --%>

                                            ,
                                            arrAttendYmd: []
                                            <%-- 신청기간 리스트 (attendYmd) --%>

                                            ,
                                            arrGraphData: []
                                            <%-- 근로시간현황 그래프 데이터 ( F_TM_GET_GRAPH_HTML) --%>

                                            ,
                                            combo:

                                            {

                                                S_ATTEND_CLASS: <%=hmPreparedData.get("cbAttend")%>

                                                ,
                                                S_YMD_CLASS: <%=hmPreparedData.get("TM102")%>

                                                ,
                                                S_APPL_CLASS: <%=hmPreparedData.get("TM500")%>

                                            }

                                            ,
                                            visible:

                                            {

                                                graphAreaDiv: false

                                                //,popup01 : false

                                            }

                                            ,
                                            resultSet:

                                            {

                                                applData: {}

                                            }

                                            ,
                                            mask:

                                            {

                                            }

                                            ,
                                            enable:

                                            {

                                                STA_YMD: true

                                                ,
                                                END_YMD: true

                                            }

                                            ,
                                            labelDayCnt: ""
                                            <%-- 신청기간 세부정보 문구 ( #spanDay) --%>

                                        }

                                        ,
                                        methods:

                                        {

                                            v_doSearch: function(applStatus, isReapply)

                                            {

                                                search01(applStatus, isReapply);

                                            }

                                            ,
                                            v_doApplyBefore: function()

                                            {

                                                return this.v_doSaveBefore();

                                            }

                                            ,
                                            v_doApprBefore: function()

                                            {

                                                var validYn = vmMaster.v_prevCheckPeriodAppr();

                                                if (!validYn)

                                                {

                                                    return false;

                                                }

                                                return true;

                                            }

                                            ,
                                            v_doSaveBefore: function()

                                            {

                                                //저장전 필수값 체크

                                                if (!HCG.checkForm("f1"))

                                                {

                                                    return false;

                                                }



                                                if (!vmMaster.v_checkAttendYmd())

                                                {

                                                    return false;

                                                }



                                                if (vmMaster.formValue.S_WORK_DUTIE_DAY == "" || Number(vmMaster.formValue.S_WORK_DUTIE_DAY) == 0)

                                                {

                                                    alert(HCG.ajaxMsg("MSG_ALERT_DAY_003")); //근태 적용일수가 0일 입니다. 신청기간을 확인해주세요.

                                                    return false;

                                                }



                                                vmMaster.v_makeSaveParam();



                                                var validYn = vmMaster.v_prevCheckPeriod();

                                                if (!validYn)

                                                {

                                                    return false;

                                                }



                                                //vmMaster.v_makeSaveParam();

                                                return true;

                                            }

                                            <%-- 휴가사용기간을 가지고 실제 휴가사용 일자를 가져와서 테이블 tr 로 구성. --%>

                                            ,
                                            v_searchAttendYmd: function()

                                            {

                                                Vue.set(vmMaster, "arrAttendYmd", []);



                                                Vue.set(vmMaster, "dsMethod", "<com:otp value='searchAttendYmd'/>");

                                                Vue.set(vmMaster.condition, "S_STA_YMD", vmMaster.formValue.S_STA_YMD);

                                                Vue.set(vmMaster.condition, "S_END_YMD", vmMaster.formValue.S_END_YMD);

                                                Vue.set(vmMaster.condition, "S_ATTEND_CLASS", vmMaster.formValue.S_ATTEND_CLASS);

                                                Vue.set(vmMaster.condition, "S_TARGET_EMP_ID", vmMaster.formValue.S_TARGET_EMP_ID);

                                                HCG.ajaxSearch(vmMaster, function(response)

                                                    {

                                                        if (!HCG.chkResponse(response)) return;

                                                        if (HCG.isArray(response.Data))

                                                        {

                                                            vmMaster.v_createAttend(response.Data, false, true);

                                                        } else

                                                        {

                                                            vmMaster.v_createAttend([]);

                                                        }

                                                    });

                                            }

                                            <%-- v_searchAttendYmd end --%>

                                            <%-- 휴가 사용기간에 대한 화면 표시 --%>

                                            ,
                                            v_displayPeriod: function()

                                            {

                                                Vue.set(vmMaster.formValue, "S_DAYS_DUTIE_DAY", "");

                                                Vue.set(vmMaster.formValue, "S_WORK_DUTIE_DAY", "");

                                                Vue.set(vmMaster, "labelDayCnt", "");



                                                Vue.set(vmMaster, "dsMethod", "<com:otp value='prevGetDCnt'/>");

                                                Vue.set(vmMaster.condition, "S_TARGET_EMP_ID", vmMaster.formValue.S_TARGET_EMP_ID);

                                                Vue.set(vmMaster.condition, "S_STA_YMD", vmMaster.formValue.S_STA_YMD);

                                                Vue.set(vmMaster.condition, "S_END_YMD", vmMaster.formValue.S_END_YMD);

                                                Vue.set(vmMaster.condition, "S_CD_D_CNT_TYPE", vmMaster.selAttendRule.D_CNT_TYPE);

                                                Vue.set(vmMaster.condition, "S_CD_USE_CNT", vmMaster.selAttendRule.USE_CNT);



                                                <%-- 달력상 휴가일수 와 실제휴가 일수를 가져온다. --%>

                                                HCG.ajaxSearch(vmMaster, function(response)

                                                    {

                                                        if (!HCG.chkResponse(response)) return;

                                                        if (HCG.isArray(response.Data))

                                                        {

                                                            Vue.set(vmMaster.formValue, "S_DAYS_DUTIE_DAY", response.Data[0].T_D_CNT);

                                                            Vue.set(vmMaster.formValue, "S_WORK_DUTIE_DAY", response.Data[0].W_D_CNT);

                                                            Vue.set(vmMaster, "labelDayCnt", "[ <B>신청일수</B> : " + response.Data[0].T_D_CNT + " 일 / <B>적용일수</B> : " + response.Data[0].W_D_CNT + " 일 ]");

                                                        }

                                                    });

                                            }

                                            <%-- v_displayPeriod end --%>



                                            ,
                                            v_makeSaveParam: function()

                                            {

                                                var arrAttendYmd = [],
                                                    arrAllDayYn = [],
                                                    arrUseHour = []

                                                , arrUseMin = [], arrStaYmd = [], arrStaHm = [], arrEndYmd = [], arrEndHm = [];



                                                $.each(vmMaster.arrAttendYmd, function(i, attend) {

                                                    arrAttendYmd.push(attend.ATTEND_YMD);

                                                    arrAllDayYn.push(attend.ALL_DAY_YN || "N");

                                                    arrUseHour.push(attend.USE_HOUR || "-");

                                                    arrUseMin.push(attend.USE_MIN || "-");

                                                    arrStaYmd.push(attend.STA_YMD_CLASS);

                                                    arrStaHm.push(attend.STA_HM || "-");

                                                    arrEndYmd.push(attend.END_YMD_CLASS);

                                                    arrEndHm.push(attend.END_HM || "-");

                                                });



                                                Vue.set(vmMaster.formValue, "S_ATTEND_YMDS", HCG.joinStr(arrAttendYmd, ","));

                                                Vue.set(vmMaster.formValue, "S_ALL_DAY_YNS", HCG.joinStr(arrAllDayYn, ","));

                                                Vue.set(vmMaster.formValue, "S_USE_HOURS", HCG.joinStr(arrUseHour, ","));

                                                Vue.set(vmMaster.formValue, "S_USE_MINS", HCG.joinStr(arrUseMin, ","));

                                                Vue.set(vmMaster.formValue, "S_STA_HMS", HCG.joinStr(arrStaHm, ","));

                                                Vue.set(vmMaster.formValue, "S_END_HMS", HCG.joinStr(arrEndHm, ","));

                                                Vue.set(vmMaster.formValue, "S_STA_YMDS", HCG.joinStr(arrStaYmd, ","));

                                                Vue.set(vmMaster.formValue, "S_END_YMDS", HCG.joinStr(arrEndYmd, ","));



                                            }

                                            <%-- v_makeSaveParam end --%>



                                            <%-- 휴가 사용기간에 대한 검증 체크 --%>

                                            ,
                                            v_prevCheckPeriod: function()

                                            {

                                                var validYn = false;

                                                var S_CHK_MSG = "";



                                                Vue.set(vmMaster.formValue, "S_DAYS_DUTIE_DAY", "");

                                                Vue.set(vmMaster.formValue, "S_WORK_DUTIE_DAY", "");

                                                Vue.set(vmMaster, "labelDayCnt", "");



                                                Vue.set(vmMaster, "dsMethod", "<com:otp value='prevCheckPeriod'/>");

                                                Vue.set(vmMaster.condition, "S_TARGET_EMP_ID", vmMaster.formValue.S_TARGET_EMP_ID);

                                                Vue.set(vmMaster.condition, "S_STA_YMD", vmMaster.formValue.S_STA_YMD);

                                                Vue.set(vmMaster.condition, "S_END_YMD", vmMaster.formValue.S_END_YMD);

                                                Vue.set(vmMaster.condition, "S_ATTEND_CLASS", vmMaster.formValue.S_ATTEND_CLASS);

                                                Vue.set(vmMaster.condition, "S_APPL_TYPE", Apply.formValue.S_APPL_TYPE);



                                                Vue.set(vmMaster.condition, "S_ATTEND_YMDS", vmMaster.formValue.S_ATTEND_YMDS);

                                                Vue.set(vmMaster.condition, "S_ALL_DAY_YNS", vmMaster.formValue.S_ALL_DAY_YNS);

                                                Vue.set(vmMaster.condition, "S_USE_HOURS", vmMaster.formValue.S_USE_HOURS);

                                                Vue.set(vmMaster.condition, "S_USE_MINS", vmMaster.formValue.S_USE_MINS);

                                                Vue.set(vmMaster.condition, "S_STA_HMS", vmMaster.formValue.S_STA_HMS);

                                                Vue.set(vmMaster.condition, "S_END_HMS", vmMaster.formValue.S_END_HMS);

                                                Vue.set(vmMaster.condition, "S_STA_YMDS", vmMaster.formValue.S_STA_YMDS);

                                                Vue.set(vmMaster.condition, "S_END_YMDS", vmMaster.formValue.S_END_YMDS);



                                                <%-- 휴가사용 가능여부 체크 --%>

                                                HCG.ajaxSyncRequestJson(this.dsClass, this.dsMethod, this.condition, function(response)

                                                    {

                                                        if (!HCG.chkResponse(response)) return;

                                                        if (HCG.isArray(response.Etc))

                                                        {

                                                            if (!!response.Etc[0].CHK_MSG)

                                                            {

                                                                S_CHK_MSG = response.Etc[0].CHK_MSG;

                                                            }



                                                            if (S_CHK_MSG == "OK")

                                                            {

                                                                vmMaster.v_displayPeriod();

                                                            } else

                                                            {

                                                                vmMaster.v_initYmd();

                                                            }

                                                        }

                                                    });





                                                if (S_CHK_MSG == "OK")

                                                {

                                                    validYn = true;

                                                } else

                                                {

                                                    Vue.set(vmMaster, "labelDayCnt", "[<B>신청기간확인</B> : " + S_CHK_MSG + " ]");

                                                    alert(S_CHK_MSG);

                                                    validYn = false;

                                                }

                                                return validYn;

                                            }

                                            ,
                                            v_prevCheckPeriodAppr: function()

                                            {



                                                var S_CHK_MSG = "";

                                                var rtnVal = true;



                                                Vue.set(vmMaster, "dsMethod", "<com:otp value='prevCheckPeriod'/>");

                                                Vue.set(vmMaster.condition, "S_TARGET_EMP_ID", vmMaster.formValue.S_TARGET_EMP_ID);

                                                Vue.set(vmMaster.condition, "S_STA_YMD", vmMaster.formValue.S_STA_YMD);

                                                Vue.set(vmMaster.condition, "S_END_YMD", vmMaster.formValue.S_END_YMD);

                                                Vue.set(vmMaster.condition, "S_ATTEND_CLASS", vmMaster.formValue.S_ATTEND_CLASS);

                                                Vue.set(vmMaster.condition, "S_APPL_TYPE", Apply.formValue.S_APPL_TYPE);



                                                Vue.set(vmMaster.condition, "S_ATTEND_YMDS", vmMaster.formValue.S_ATTEND_YMDS);

                                                Vue.set(vmMaster.condition, "S_ALL_DAY_YNS", vmMaster.formValue.S_ALL_DAY_YNS);

                                                Vue.set(vmMaster.condition, "S_USE_HOURS", vmMaster.formValue.S_USE_HOURS);

                                                Vue.set(vmMaster.condition, "S_USE_MINS", vmMaster.formValue.S_USE_MINS);

                                                Vue.set(vmMaster.condition, "S_STA_HMS", vmMaster.formValue.S_STA_HMS);

                                                Vue.set(vmMaster.condition, "S_END_HMS", vmMaster.formValue.S_END_HMS);

                                                Vue.set(vmMaster.condition, "S_STA_YMDS", vmMaster.formValue.S_STA_YMDS);

                                                Vue.set(vmMaster.condition, "S_END_YMDS", vmMaster.formValue.S_END_YMDS);

                                                Vue.set(vmMaster.condition, "S_APPR_YN", "Y");





                                                <%-- 휴가사용 가능여부 체크 --%>

                                                HCG.ajaxSyncRequestJson(this.dsClass, this.dsMethod, this.condition, function(response)

                                                    {

                                                        if (!HCG.chkResponse(response)) return;

                                                        if (HCG.isArray(response.Etc))

                                                        {

                                                            if (!!response.Etc[0].CHK_MSG)

                                                            {

                                                                S_CHK_MSG = response.Etc[0].CHK_MSG;

                                                            }



                                                            if (S_CHK_MSG == "OK")

                                                            {

                                                                rtnVal = true;

                                                            } else

                                                            {

                                                                alert(S_CHK_MSG);

                                                                rtnVal = false;

                                                            }

                                                        }

                                                    });



                                                return rtnVal;



                                            }

                                            <%-- prevCheckPeriod end --%>



                                            <%--

       입력한 시작/종료 시각 출퇴근입력 범위내인지 확인

       type : sta / end

       attend : 선택한 일자 객체

     --%>

                                            ,
                                            v_checkInOutTerm: function(type, attend)

                                            {

                                                var attendYmd = attend.ATTEND_YMD;



                                                var ymdClass, stdTime;

                                                if (type == "sta")

                                                {

                                                    ymdClass = attend.STA_YMD_CLASS;

                                                    stdTime = attend.STA_HM;

                                                } else if (type == "end")

                                                {

                                                    ymdClass = attend.END_YMD_CLASS;

                                                    stdTime = attend.END_HM;

                                                }



                                                var stdYmd = ('2' == ymdClass) ? attendYmd : HCG.addYmd(attendYmd, "D", 1);



                                                Vue.set(vmMaster, "dsMethod", "<com:otp value='checkInOutTerm'/>");

                                                Vue.set(vmMaster.condition, "S_TARGET_EMP_ID", vmMaster.formValue.S_TARGET_EMP_ID);

                                                Vue.set(vmMaster.condition, "S_ATTEND_YMD", attendYmd);

                                                Vue.set(vmMaster.condition, "S_STD_YMD", stdYmd);

                                                Vue.set(vmMaster.condition, "S_STD_TIME", stdTime);



                                                HCG.ajaxSearch(vmMaster, function(response)

                                                    {

                                                        if (!HCG.chkResponse(response)) return;



                                                        var etcData = {};

                                                        if (HCG.isArray(response.Etc))

                                                        {

                                                            var etcData = response.Etc[0];

                                                        }



                                                        if (etcData.CONFIRM != undefined && etcData.CONFIRM == "1")

                                                        {

                                                            vmMaster.v_calcTime01(attend);

                                                        } else

                                                        {

                                                            if (stdTime.length == 4)

                                                            {

                                                                alert(HCG.ajaxMsg("MSG_ALERT_DAY_006")); //신청가능한 시간 범위가 아닙니다.

                                                                if (type == "sta")

                                                                {

                                                                    Vue.set(attend, "STA_HM", "");

                                                                } else if (type == "end")

                                                                {

                                                                    Vue.set(attend, "END_HM", "");

                                                                }

                                                            }

                                                        }

                                                    });

                                            }

                                            <%-- checkInOutTerm end --%>



                                            <%-- 

       시작,종료시각으로 자동 시간 계산

        - attend : 선택한 일자 객체 

     --%>

                                            ,
                                            v_calcTime01: function(attend)

                                            {

                                                if (!HCG.checkForm("f1")) return;



                                                var sel_ymd = attend.ATTEND_YMD;

                                                var sta_time = attend.STA_HM;

                                                var end_time = attend.END_HM;

                                                var $use_hour = attend.USE_HOUR;

                                                var $use_min = attend.USE_MIN;



                                                var sta_ymd_class = attend.STA_YMD_CLASS;

                                                var end_ymd_class = attend.END_YMD_CLASS;

                                                var sta_ymd = ('2' == sta_ymd_class) ? sel_ymd : HCG.addYmd(sel_ymd, "D", 1);

                                                var end_ymd = ('2' == end_ymd_class) ? sel_ymd : HCG.addYmd(sel_ymd, "D", 1);



                                                if (!sta_time || !end_time) return;

                                                if (sta_ymd + sta_time > end_ymd + end_time)

                                                {

                                                    alert(HCG.ajaxMsg("MSG_ALERT_DAY_007")); //시작시각이 종료시각보다 클 수 없습니다.

                                                    Vue.set(attend, "END_HM", "");

                                                    return;

                                                }



                                                Vue.set(vmMaster, "dsMethod", "<com:otp value='calcTime01'/>");

                                                Vue.set(vmMaster.condition, "S_TARGET_EMP_ID", vmMaster.formValue.S_TARGET_EMP_ID);

                                                Vue.set(vmMaster.condition, "S_STD_YMD", sel_ymd);

                                                Vue.set(vmMaster.condition, "S_STA_YMD", sta_ymd);

                                                Vue.set(vmMaster.condition, "S_END_YMD", end_ymd);

                                                Vue.set(vmMaster.condition, "S_STA_TIME", sta_time);

                                                Vue.set(vmMaster.condition, "S_END_TIME", end_time);

                                                HCG.ajaxSearch(vmMaster, function(response)

                                                    {

                                                        if (!HCG.chkResponse(response)) return;

                                                        if (HCG.isArray(response.Data))

                                                        {

                                                            Vue.set(attend, "USE_HOUR", response.Data[0].USE_HOUR);

                                                            Vue.set(attend, "USE_MIN", response.Data[0].USE_MIN);

                                                        } else

                                                        {

                                                            Vue.set(attend, "USE_HOUR", "");

                                                            Vue.set(attend, "USE_MIN", "");

                                                        }

                                                    });

                                            }

                                            <%-- v_calcTime01 //--%>



                                            <%-- 

       근로현황 그래프 생성 

        - DB Function에서 HTML 가져와서 집어넣는 기존방식에서, 데이터만 조회하여 화면에서 구성하도록 변경.

     --%>

                                            ,
                                            v_getGraph: function()

                                            {

                                                Vue.set(vmMaster.visible, "graphAreaDiv", true);



                                                Vue.set(vmMaster, "dsMethod", "<com:otp value='getGraphData'/>");

                                                Vue.set(vmMaster.condition, "S_EMP_ID", vmMaster.formValue.S_TARGET_EMP_ID);

                                                Vue.set(vmMaster.condition, "S_STA_YMD", vmMaster.formValue.S_STA_YMD);

                                                Vue.set(vmMaster.condition, "S_END_YMD", vmMaster.formValue.S_END_YMD);

                                                HCG.ajaxSyncRequestJson("<com:otp value='biz.tam.Tam_common'/>", this.dsMethod, this.condition, function(response)

                                                    {

                                                        if (!HCG.chkResponse(response)) return;

                                                        if (HCG.isArray(response.Data))

                                                        {

                                                            Vue.set(vmMaster, "arrGraphData", response.Data);

                                                        } else

                                                        {

                                                            Vue.set(vmMaster, "arrGraphData", []);

                                                        }

                                                    });

                                            }

                                            <%-- v_getGraph //--%>



                                            <%-- 근태 코드에 따른 사용기준 가져오기 --%>

                                            ,
                                            v_getRule: function()

                                            {

                                                Vue.set(vmMaster.visible, "graphAreaDiv", false);



                                                //연차 차감순서 체크



                                                Vue.set(vmMaster, "dsMethod", "<com:otp value='search_ddct_order'/>");

                                                Vue.set(vmMaster.condition, "S_TARGET_EMP_ID", vmMaster.formValue.S_TARGET_EMP_ID);

                                                Vue.set(vmMaster.condition, "S_ATTEND_CLASS", vmMaster.formValue.S_ATTEND_CLASS);

                                                HCG.ajaxSearch(vmMaster, function(response)

                                                    {

                                                        if (!HCG.chkResponse(response)) return;

                                                        if (HCG.isArray(response.Data))

                                                        {

                                                            if (response.Data[0].APPLY_ATTEND_CD_NM)

                                                            {

                                                                alert(response.Data[0].APPLY_ATTEND_CD_NM + "을(를) 먼저 소진 해 주시기 바랍니다.");

                                                                Vue.set(vmMaster.formValue, "S_ATTEND_CLASS", false);

                                                                vmMaster.v_initYmd();

                                                                Vue.set(vmMaster.enable, "STA_YMD", false);

                                                                Vue.set(vmMaster.enable, "END_YMD", false);

                                                                return;

                                                            }

                                                        }



                                                    });



                                                <%-- 선택근태코드가 없다면.. 시작/종료일 disable 및 초기화 --%>

                                                if (vmMaster.formValue.S_ATTEND_CLASS == null || vmMaster.formValue.S_ATTEND_CLASS == "")

                                                {

                                                    vmMaster.v_initYmd();

                                                    Vue.set(vmMaster.enable, "STA_YMD", false);

                                                    Vue.set(vmMaster.enable, "END_YMD", false);

                                                    return;

                                                } else

                                                {

                                                    Vue.set(vmMaster.enable, "STA_YMD", true);

                                                    Vue.set(vmMaster.enable, "END_YMD", true);

                                                }



                                                var selRule = {};

                                                $.each(vmMaster.arrAttendRule, function(i, obj) {

                                                    if (vmMaster.formValue.S_ATTEND_CLASS == obj.CD)

                                                    {

                                                        selRule = obj;

                                                        return false;

                                                    }

                                                });



                                                Vue.set(vmMaster, "selAttendRule", selRule);



                                                alert(vmMaster.selAttendRule.selRule);

                                            }

                                            <%-- v_getRule end --%>



                                            <%-- 시작/종료일 변경시 이벤트 --%>

                                            ,
                                            v_checkAttendCd: function()

                                            {

                                                if (vmMaster.formValue.S_ATTEND_CLASS == "")

                                                {

                                                    alert(HCG.ajaxMsg("MSG_ALERT_DAY_001")); //신청하고자 하는 '근태/휴가'를 먼저 선택하여 주십시오.

                                                    return;

                                                } else

                                                {

                                                    var staYmd = vmMaster.formValue.S_STA_YMD;

                                                    var endYmd = vmMaster.formValue.S_END_YMD;



                                                    if (!!staYmd && !!endYmd)

                                                    {

                                                        if (staYmd > endYmd)

                                                        {

                                                            alert(HCG.ajaxMsg("MSG_ALERT_STA_END"));

                                                            Vue.set(vmMaster.formValue, "END_YMD", "");

                                                            return;

                                                        }



                                                        <%-- 근무인경우 --%>

                                                        if (vmMaster.selAttendRule.WORK_YN == "Y")

                                                        {

                                                            vmMaster.v_searchAttendYmd();

                                                        } else

                                                        {

                                                            Vue.set(vmMaster, "arrAttendYmd", []);

                                                        }



                                                        vmMaster.v_displayPeriod();

                                                    }

                                                    vmMaster.v_getGraph();

                                                }

                                            }

                                            <%-- checkAttendCd end --%>



                                            <%--

       JaDE의 createAttedTr

       휴가사용기간 동안의 실제 휴가사용 일자를 테이블 tr 로 구성.

       disableYn: true: 요소의 disable, false: enable

       newYn: true: 값설정안함 disable, false: 저장 값으로 설정

     --%>

                                            ,
                                            v_createAttend: function(rowData, disableYn, newYn)

                                            {

                                                var arrAttendYmd = [];

                                                <%-- 휴가기간 데이터 추가 --%>

                                                $.each(rowData, function(i, obj)

                                                    {

                                                        arrAttendYmd.push

                                                            ({

                                                            <%-- 기준데이터 --%>

                                                            ATTEND_YMD: obj.ATTEND_YMD

                                                            ,
                                                            WORK_TIME_STD_DAY: obj.WORK_TIME_STD_DAY

                                                            ,
                                                            ATTEND_STA_HM: obj.STA_HM <%-- 근무유형의 시작시간 --%>

                                                            ,
                                                            ATTEND_END_HM: obj.END_HM <%-- 근무유형의 종료시간 --%>

                                                                <%-- 임시저장, 신청 후 면(false) xs 데이터를 화면에 셋팅 --%>

                                                            ,
                                                            ALL_DAY_YN: !newYn ? obj.ALL_DAY_YN : "N"

                                                            ,
                                                            STA_YMD_CLASS: !newYn ? obj.STA_YMD_CLASS : "2"

                                                            ,
                                                            STA_HM: !newYn ? obj.STA_HM : ""

                                                            ,
                                                            END_YMD_CLASS: !newYn ? obj.END_YMD_CLASS : "2"

                                                            ,
                                                            END_HM: !newYn ? obj.END_HM : ""

                                                            ,
                                                            USE_HOUR: !newYn ? obj.USE_HOUR : ""

                                                            ,
                                                            USE_MIN: !newYn ? obj.USE_MIN : ""

                                                            ,
                                                            NEW_YN: newYn

                                                                <%--

             disableYn 이 true  면 최초 입력 또는 임시저장시 이벤트 부여

             disableYn 이 false 면 신청 후 이벤트 부여안하고 disable 처리

           --%>

                                                            ,
                                                            disableYn: disableYn

                                                                <%-- 값 입력 활성화여부 --%>

                                                            ,
                                                            insertEnable: true

                                                                <%--

             종일 체크박스 이벤트 함수

              - attend : 선택한 신청일 객체

           --%>

                                                            ,
                                                            changeAllDayYn: function(attend)

                                                            {

                                                                if (attend.disableYn) return;

                                                                if (attend.ALL_DAY_YN == "Y")

                                                                {

                                                                    Vue.set(attend, "USE_HOUR", attend.WORK_TIME_STD_DAY);

                                                                    Vue.set(attend, "STA_HM", attend.ATTEND_STA_HM);

                                                                    Vue.set(attend, "END_HM", attend.ATTEND_END_HM);

                                                                    Vue.set(attend, "USE_MIN", "00");

                                                                    Vue.set(attend, "STA_YMD_CLASS", "2");

                                                                    Vue.set(attend, "END_YMD_CLASS", "2");

                                                                    Vue.set(attend, "insertEnable", false);

                                                                    // Vue.set(attend, "disableYn", true);

                                                                } else

                                                                {

                                                                    //if(this.name != "F_USE_HOUR" && this.name != "F_USE_MIN")

                                                                    Vue.set(attend, "USE_HOUR", "");

                                                                    Vue.set(attend, "STA_HM", "");

                                                                    Vue.set(attend, "END_HM", "");

                                                                    Vue.set(attend, "USE_MIN", "");

                                                                    Vue.set(attend, "STA_YMD_CLASS", "2");

                                                                    Vue.set(attend, "END_YMD_CLASS", "2");

                                                                    Vue.set(attend, "insertEnable", true);

                                                                    // Vue.set(attend, "disableYn", false);

                                                                }

                                                            }

                                                            <%--

             시작/종료시간 이벤트 함수

              - attend : 선택한 신청일 객체

           --%>

                                                            ,
                                                            changeHm: function(type, attend)

                                                            {

                                                                if (attend.disableYn) return;



                                                                Vue.set(attend, "USE_HOUR", "");

                                                                Vue.set(attend, "USE_MIN", "");



                                                                vmMaster.v_checkInOutTerm(type, attend);

                                                            }

                                                        });

                                                    });



                                                Vue.set(vmMaster, "arrAttendYmd", arrAttendYmd);

                                            }

                                            <%-- v_createAttend end // --%>



                                            <%-- 근태/근무 선택시 --%>

                                            ,
                                            v_changeTmCd: function()

                                            {

                                                //        vmMaster.v_initYmd();

                                                vmMaster.v_getRule();

                                                if (Apply.v_getApplStatus() != Apply.Complete)

                                                {

                                                    vmMaster.v_getGraph();

                                                }



                                            }

                                            <%-- 휴가기간 초기화 --%>

                                            ,
                                            v_initYmd: function()

                                            {

                                                Vue.set(vmMaster.formValue, "STA_YMD", "");

                                                Vue.set(vmMaster.formValue, "END_YMD", "");

                                                Vue.set(vmMaster, "labelDayCnt", "");

                                                Vue.set(vmMaster, "arrAttendYmd", []);

                                            }

                                            ,
                                            v_getDateString: function(sDate)

                                            {

                                                if (sDate.length == 8)

                                                {

                                                    return sDate.substring(0, 4) + "년 " + sDate.substring(4, 6) + "월 " + sDate.substring(6, 8) + "일";

                                                } else

                                                {

                                                    return "";

                                                }

                                            }

                                            ,
                                            v_getTimeString: function(hour, minute)

                                            {

                                                var rv = "";



                                                if (hour != undefined && hour != "")

                                                {

                                                    rv += (hour + "시간");

                                                }

                                                if (minute != undefined && minute != "")

                                                {

                                                    rv += (" " + minute + "분");

                                                }



                                                return rv;

                                            }

                                            <%-- 휴가일 설정에 대한 validation check --%>

                                            ,
                                            v_checkAttendYmd: function()

                                            {

                                                var valid = true;

                                                var errMsg = "";



                                                $.each(vmMaster.arrAttendYmd, function(i, attend)

                                                    {

                                                        var ymd = "";



                                                        var checked = attend.ALL_DAY_YN;

                                                        var $attendYmd = attend.ATTEND_YMD;

                                                        var $useHour = attend.USE_HOUR;

                                                        var $useMin = attend.USE_MIN;

                                                        var $staHm = attend.STA_HM;

                                                        var $endHm = attend.END_HM;



                                                        if (!$attendYmd)

                                                        {

                                                            valid = false;

                                                            errMsg = HCG.ajaxMsg("MSG_ALERT_DAY_010");

                                                            return false;

                                                        }



                                                        ymd = vmMaster.v_getDateString($attendYmd) || $attendYmd;

                                                        if (!$useHour)

                                                        {

                                                            valid = false;

                                                            errMsg = ymd + HCG.ajaxMsg("MSG_ALERT_DAY_058");



                                                            return false;

                                                        }



                                                        if (!checked)

                                                        {

                                                            if (!$staHm)

                                                            {

                                                                valid = false;

                                                                errMsg = ymd + HCG.ajaxMsg("MSG_ALERT_DAY_074");

                                                                return false;

                                                            }



                                                            if (!$endHm)

                                                            {

                                                                valid = false;

                                                                errMsg = ymd + HCG.ajaxMsg("MSG_ALERT_DAY_075");

                                                                return false;

                                                            }

                                                        }



                                                        //휴가사용 가능여부 체크

                                                        HCG.ajaxSyncRequestXS("<com:otp value='biz.tam.Tam_common'/>", "<com:otp value='checkAttendYmd'/>", {
                                                                S_EMP_ID: vmMaster.formValue.S_TARGET_EMP_ID,
                                                                S_ATTEND_CD: vmMaster.formValue.S_ATTEND_CLASS,
                                                                S_YMD: $attendYmd,
                                                                S_APPL_HOUR: $useHour,
                                                                S_APPL_MIN: $useMin
                                                            }, function(xs)

                                                            {

                                                                if (xs.RowCount() > 0)

                                                                {

                                                                    if (xs.GetCellValue(0, "CHK_MSG") != "OK")

                                                                    {

                                                                        valid = false;

                                                                        errMsg = xs.GetCellValue(0, "CHK_MSG");

                                                                        return false;

                                                                    }

                                                                }

                                                            });

                                                    });



                                                if (!valid)

                                                {

                                                    alert(errMsg);

                                                }

                                                return valid;

                                            }

                                            <%-- v_checkAttendYmd end // --%>

                                            <%-- 그래프제목 --%>

                                            ,
                                            v_getGraphHeader: function(type, dataObj)

                                            {

                                                var rv = "";

                                                switch (type)

                                                {

                                                    case "law":

                                                        {

                                                            rv = "법정근로 (" + dataObj.FSTR_TOT_WORK_VAL + "/" + dataObj.FSTR_LAW_WORK_TIME + ")";

                                                        }

                                                        break;

                                                    case "over":

                                                        {

                                                            rv = "연장 (" + dataObj.FSTR_TOT_OVER_VAL + "/" + dataObj.FSTR_OVER_WORK_TIME + ")";

                                                        }

                                                        break;

                                                }

                                                return rv;

                                            }

                                            <%--

       그래프 배경색 구하기 (skin.css 배경색깔, xxxBg)

        - code : /TM409(근로상태)

     --%>

                                            ,
                                            v_getGraphRgb: function(code)

                                            {

                                                var rv = "";

                                                switch (code)

                                                {

                                                    case "blue":

                                                        {

                                                            rv = "#3091ea";

                                                        }

                                                        break;

                                                        <%-- 양호 --%>

                                                    case "green":

                                                    case "0010":

                                                        {

                                                            rv = "#67b930";

                                                        }

                                                        break;

                                                        <%-- 주의 --%>

                                                    case "orange":

                                                    case "0020":

                                                        {

                                                            rv = "#ff8a00";

                                                        }

                                                        break;

                                                        <%-- 경고 --%>

                                                    case "red":

                                                    case "0030":

                                                        {

                                                            rv = "#ed1b3c";

                                                        }

                                                        break;

                                                        <%-- 금지 --%>

                                                    case "black":

                                                    case "0040":

                                                        {

                                                            rv = "#000";

                                                        }

                                                        break;

                                                }

                                                return rv;

                                            }

                                            ,
                                            v_OnEmpInfo01: function()

                                            {

                                                if (this.formValue.S_TARGET_EMP_ID)

                                                {

                                                    if (this.formValue.S_BEF_TARGET_EMP_ID != this.formValue.S_TARGET_EMP_ID)

                                                    {

                                                        Vue.set(this.formValue, 'S_ATTEND_CLASS', "");

                                                        Vue.set(this.formValue, 'S_STA_YMD', "");

                                                        Vue.set(this.formValue, 'S_END_YMD', "");

                                                        Vue.set(this.formValue, 'S_NOTE', "");

                                                        Vue.set(this.formValue, 'S_CD_Q_YN', "");

                                                        Vue.set(this.formValue, 'S_CD_Q_CD', "");

                                                        Vue.set(this.formValue, 'S_CD_D_CNT_TYPE', "");

                                                        Vue.set(this.formValue, 'S_CD_USE_CNT', "");

                                                        Vue.set(this.formValue, 'S_DAYS_DUTIE_DAY', "");

                                                        Vue.set(this.formValue, 'S_WORK_DUTIE_DAY', "");

                                                        Vue.set(this, 'labelDayCnt', ""); // $("#spanDay").html("");



                                                        Vue.set(vmMaster, "arrGraphData", []);



                                                    }

                                                    Apply.v_changeTRG();

                                                }

                                            }

                                            <%-- 취소할 근태/휴가내역 페이지 --%>

                                            ,
                                            v_popup_01: function()

                                            {

                                                var tit = this.v_getLabel('tm_day_017_001', '취소할 근태/휴가내역');

                                                var S_APPL_TYPE = '<%=StringUtil.nvl(request.getParameter("S_APPL_TYPE"))%>';

                                                HCG.ModalUtil.open({
                                                        title: tit,
                                                        url: "/tam/tm_day/tm_day_070_p01.jsp",
                                                        param: {
                                                            S_EMP_ID: this.formValue.S_TARGET_EMP_ID,
                                                            S_APPL_TYPE: S_APPL_TYPE
                                                        }
                                                    }, "", function(rv)

                                                    {

                                                        if (rv != null)

                                                        {

                                                            var grid = rv;

                                                            for (var r = 0; r < grid.RowCount; r++)

                                                            {

                                                                Vue.set(vmMaster.formValue, 'S_APPL_INFO', HCG.grid_GetCellValue(grid, r, "APPL_INFO"))

                                                                Vue.set(vmMaster.formValue, 'S_DELETE_APPL_ID', HCG.grid_GetCellValue(grid, r, "APPL_ID"))

                                                                break;

                                                            }

                                                        }

                                                    });

                                            }

                                            ,
                                            v_getId: function(index)

                                            {

                                                return "ALL_DAY_YN_" + index;

                                            }

                                        }

                                    };

                                    vmMaster = HCG.initApplVue(pageVueParam); //신청서는 initVue가 아닌 initApplVue를 호출해야 한다.

                                }





                                function search01(applStatus, isReapply)

                                {

                                    <%-- 신청내역 조회 --%>

                                    vmMaster.dsMethod = "<com:otp value='search01'/>";

                                    vmMaster.condition.S_APPL_ID = vmMaster.formValue.S_APPL_ID;

                                    vmMaster.condition.S_QUOTA_TIME_VAL = vmMaster.formValue.S_QUOTA_TIME_VAL;

                                    //Vue.set(vmMaster.condition,"S_QUOTA_TIME_VAL", vmMaster.formValue.S_QUOTA_TIME_VAL);



                                    HCG.ajaxSearch(vmMaster, function(response)

                                        {

                                            if (HCG.isArray(response.Data))

                                            {

                                                Vue.set(vmMaster, "formValue", HCG.extendForm(vmMaster.formValue, response.Data[0]));



                                                <%--승인 프로세스 있을경우 근무시간 수기입력--%>

                                                if (Apply.formValue.S_APPL_MODE == "ADMIN")

                                                {

                                                    //Vue.set(vmMaster.visivle,'popup01',false);

                                                }



                                                <%-- 선택한 근태코드의 근태기준 --%>

                                                vmMaster.v_getRule();



                                                if (vmMaster.selAttendRule.WORK_YN == "Y" && Apply.v_getApplStatus() == Apply.TempSave)

                                                {

                                                    vmMaster.v_createAttend(response.Data, false, false);

                                                } else

                                                {

                                                    vmMaster.v_createAttend(response.Data, true, false);

                                                }



                                                if (Apply.v_getApplStatus() != Apply.Complete)

                                                {

                                                    vmMaster.v_getGraph();

                                                }



                                                vmMaster.v_displayPeriod();

                                            } else

                                            {

                                                alert(HCG.ajaxMsg("MSG_ALERT_0509")); //신청 데이터가 존재하지 않습니다.

                                                return;

                                            }

                                        });

                                }
                            </script>

</head>
<body class="overflow-hidden">
  <div class="h-flex-layout h-flex-col h-app-form" v-cloak>
    <form name="f1" onsubmit="return false;"
      class="h-flex-col h-flex-form">
      <div class="h-flex-col">
        <!--// 결재선 -->
        <div>
          <%@include file="/sys/sy_appl/sys_appl_line.jsp"%>
          <!-- 결재선 -->
          <%@include file="/sys/sy_appl/sys_appl_line_detail.jsp"%>
          <!-- 결재선상세 -->
        </div>
        <!-- 결재선 // -->
        <!--// 본문  -->
        <div class="h-hidden-zone">
          <!-- //신청폼 -->
          <div class="container-fluid h-form"
            v-if="formValue.S_APPL_MODE == 'APPL' && formValue.S_APPL_STAT_CD <= TempSave">
            <div class="row">
              <div class="col-md-6 col-lg-6">
                <p class="h-point">{{ v_getLabel("trg_emp","대상자") }}
                </p>
                <p class="h-form-input">
                  <jsp:include page="/sys/sy_com/sy_com_181_c01.jsp"
                    flush="true">
                    <jsp:param name="S_MODE01" value="0020" />
                    <jsp:param name="S_REQUIRED" value="Y" />
                    <jsp:param name="S_SIZE" value="10" />
                    <jsp:param name="S_PREFIX" value="S_TARGET" />
                    <jsp:param name="S_FUNCTION"
                      value="vmMaster.v_OnEmpInfo01" />
                    <jsp:param name="S_V_MODEL_EMP_ID"
                      value="formValue.S_TARGET_EMP_ID" />
                    <jsp:param name="S_V_MODEL_EMP_NM"
                      value="formValue.S_TARGET_EMP_NM" />
                  </jsp:include>
                </p>
              </div>
              <div class="col-md-6 col-lg-6">
                <p class="h-point">{{
                  v_getLabel('appl_gubun','신청구분') }}</p>
                <p class="h-form-input">
                  <h-select name="S_APPL_CLASS"
                    v-model="formValue.S_APPL_CLASS"
                    :combo="combo.S_APPL_CLASS" key_field="Y"
                    col-cd="CD" col-cd-nm="CD_NM" ref="S_APPL_CLASS"
                    null-option="S"
                    :korname="v_getLabel('appl_gubun','신청구분')"></h-select>
                </p>
              </div>
            </div>
            <div class="h-dotted"></div>
            <div class="row" v-show="formValue.S_APPL_CLASS == '10' ">
              <div class="col-md-6 col-lg-4">
                <p class="h-point">{{ v_getLabel('attend','근태') }} /
                  {{ v_getLabel('work','근무') }}</p>
                <p class="h-form-input">
                  <h-select name="S_ATTEND_CLASS"
                    v-model="formValue.S_ATTEND_CLASS"
                    :combo="combo.S_ATTEND_CLASS" :key_field="Y"
                    col-cd="CD" col-cd-nm="CD_NM" ref="S_ATTEND_CLASS"
                    @change="v_changeTmCd"
                    :korname="v_getLabel('attend','근태') + '/' + v_getLabel('work','근무')"></h-select>
                </p>
              </div>
              <div class="col-md-6 col-lg-4">
                <p class="h-point">{{
                  v_getLabel('appl_period','신청기간') }}</p>
                <p class="h-form-input h-calendar2">
                  <h-formatted-input name="S_STA_YMD"
                    v-model="formValue.S_STA_YMD" :key_field="Y"
                    data-format="dfDateYmd"
                    :korname="v_getLabel('sta_ymd_01','기간시작일')"
                    @change="v_checkAttendCd"
                    :disabled="!enable.STA_YMD"></h-formatted-input>
                  <h-formatted-input name="S_END_YMD"
                    v-model="formValue.S_END_YMD" :key_field="Y"
                    data-format="dfDateYmd"
                    :korname="v_getLabel('end_ymd_01','기간종료일')"
                    @change="v_checkAttendCd"
                    :disabled="!enable.END_YMD"></h-formatted-input>
                </p>
              </div>
              <div class="col-md-12 col-lg-12">
                <p>{{ v_getLabel('appl_info2','신청정보') }}</p>
                <p
                  class="h-form-input pd-l10 pd-t5 color-main01 font-bold">
                  <span class="h-input-info" v-html="labelDayCnt"></span>
                </p>
              </div>
              <div class="col-md-12 col-lg-6"
                v-if="arrAttendYmd.length > 0 && selAttendRule.WORK_YN == 'Y'">
                <div class="row" v-for="(item, index) in arrAttendYmd">
                  <div class="col-4">
                    <p>{{ v_getLabel('appl_ymd','신청일') }}</p>
                    <p class="h-form-input pd-l10">
                      <span class="h-form-txt">{{v_formatValue(item.ATTEND_YMD,'dfDateYmd')}}
                      </span>s <input type="checkbox"
                        class="h-checkbox-style01" :id="v_getId(index)"
                        v-model="item.ALL_DAY_YN" true-value="Y"
                        false-value="N" :disabled="item.disableYn"
                        @change="item.changeAllDayYn(item)" /> <label
                        :for="v_getId(index)">{{v_getLabel('all_day_01','종일')}}</label>
                      <span class="ml-3">{{v_getTimeString(item.USE_HOUR,
                        item.USE_MIN)}}</span>
                    </p>
                  </div>
                  <div class="col-4">
                    <p>{{v_getLabel("sta","시작")}} :</p>
                    <p class="h-form-many">
                      <span class="h-form-input mr-10"> <h-select
                          v-model="item.STA_YMD_CLASS"
                          :combo="combo.S_YMD_CLASS" col-cd="CD"
                          col-cd-nm="CD_NM"
                          @change="item.changeHm('sta', item)"
                          :disabled="item.disableYn || !item.insertEnable">
                        </h-select>
                      </span> <span class="h-form-input"> <h-formatted-input
                          v-model="item.STA_HM" data-format="dfTimeHm"
                          :disabled="item.disableYn || !item.insertEnable"
                          @change="item.changeHm('sta', item)">
                        </h-formatted-input>
                      </span>
                    </p>
                  </div>
                  <div class="col-4">
                    <p>{{v_getLabel("end","종료")}}</p>
                    <p class="h-form-many">
                      <span class="h-form-input mr-10"> <h-select
                          v-model="item.END_YMD_CLASS"
                          :combo="combo.S_YMD_CLASS" col-cd="CD"
                          col-cd-nm="CD_NM"
                          @change="item.changeHm('end', item)"
                          :disabled="item.disableYn || !item.insertEnable">
                        </h-select>
                      </span> <span class="h-form-input"> <h-formatted-input
                          v-model="item.END_HM" data-format="dfTimeHm"
                          :disabled="item.disableYn || !item.insertEnable"
                          @change="item.changeHm('end', item)">
                        </h-formatted-input>
                      </span>
                    </p>
                  </div>
                  <div class="col-md-12 col-lg-6">
                    <p class="h-point">{{
                      v_getLabel('appl_rsn','신청사유') }}</p>
                    <p class="h-form-input">
                      <input type="text" name="S_NOTE"
                        v-model="formValue.S_NOTE"
                        :korname="v_getLabel('appl_rsn','신청사유')" />
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row" v-show="formValue.S_APPL_CLASS == '20' ">
              <div class="col-md-12 col-lg-12">
                <p class="h-point">
                  <com:label key='wig_appl' def='신청내역' />
                </p>
                <p class="h-form-input">
                  <input type="text" class="h-in-text-style01"
                    name="S_APPL_INFO" v-model="formValue.S_APPL_INFO"
                    disabled /> <a href="javascript:"
                    class="h-input-area-button search"
                    @click="v_popup_01()"></a>
                </p>
              </div>
              <div class="col-md-12 col-lg-12">
                <p class="h-point">
                  <com:label key='cancel_rsn' def='취소사유' />
                </p>
                <p class="h-form-input">
                  <input type="text" class="h-in-text-style01"
                    name="S_DEL_NOTE" v-model="formValue.S_DEL_NOTE"
                    :key_field="Y"
                    korname="<com:label key='cancel_rsn' def='취소사유' />"
                    maxbl="300" />
                </p>
              </div>
            </div>
            <div class="h-dotted"></div>
            <div class="row"
              v-show="visible.graphAreaDiv && arrGraphData.length > 0">
              <div class="col-md-12 col-lg-6">
                <!-- 
              ( {{item.STA_YMD.substring(4,6)}}.{{item.STA_YMD.substring(6,8)}}~{{item.END_YMD.substring(4,6)}}.{{item.END_YMD.substring(6,8)}} ) 
               -->
                <p>{{ v_getLabel('work_time_stat','근로시간현황') }}</p>
                <p>
                <ul>
                  <li v-for="(item, index) in arrGraphData">
                    <div class="h-work-state">
                      <div class="h-normalTime">
                        <strong>{{item.STA_YMD.substring(4,6)}}.{{item.STA_YMD.substring(6,8)}}~{{item.END_YMD.substring(4,6)}}.{{item.END_YMD.substring(6,8)}}</strong>
                        <span class="workState ml10"
                          :style="{'background-color' : v_getGraphRgb(item.STAT_PER_CD)}">
                          {{item.STAT_PER_NM}} </span>
                      </div>
                    </div>
                  </li>
                </ul>
                </p>
              </div>
            </div>
          </div>
          <!-- 신청폼// -->

          <!--// 결재시 -->
          <div class="h-app-form-result"
            v-if="formValue.S_APPL_MODE == 'APPR' || formValue.S_APPL_MODE == 'ADMIN' || formValue.S_APPL_STAT_CD > TempSave">
            <div class="h-app-form-result-tit">
              <span class="point">{{ formValue.S_APPL_TYPE_NM }}</span>
              내역은 다음과 같습니다.
            </div>
            <div class="container-fluid h-form">
              <div class="row">
                <div class="col-md-6 col-lg-4">
                  <p>{{ v_getLabel("trg_emp","대상자") }}</p>
                  <p>
                    <span class="h-tit">{{formValue.S_TARGET_EMP_NM}}</span>
                  </p>
                </div>
                <div class="col-md-6 col-lg-4">
                  <p>{{ v_getLabel("appl_gbn","신청구분") }}</p>
                  <p>
                    <span class="h-tit">{{v_getCodeNm(combo.S_APPL_CLASS,
                      formValue.S_APPL_CLASS) }}</span>
                  </p>
                </div>
              </div>
              <div class="h-dotted"></div>
              <div class="row" v-show="formValue.S_APPL_CLASS == '10'">
                <div class="col-md-6 col-lg-4">
                  <p>{{ v_getLabel('attend','근태') }} / {{
                    v_getLabel('work','근무') }}</p>
                  <p>
                    <span class="h-tit">{{
                      v_getCodeNm(combo.S_ATTEND_CLASS,
                      formValue.S_ATTEND_CLASS) }}</span>
                  </p>
                </div>
                <div class="col-md-6 col-lg-4">
                  <p>{{ v_getLabel('appl_period','신청기간') }}</p>
                  <p>
                    <span class="h-tit">
                      {{v_formatValue(formValue.S_STA_YMD,"dfDateYmd")}}
                      ~
                      {{v_formatValue(formValue.S_END_YMD,"dfDateYmd")}}</span>
                  </p>
                </div>
                <div class="col-md-6 col-lg-4">
                  <p>{{ v_getLabel('appl_period','신청기간') }}</p>
                  <p class="h-input-info" v-html="labelDayCnt"></p>
                </div>
                <div class="col-md-12 col-lg-6"
                  v-if="arrAttendYmd.length > 0 && selAttendRule.WORK_YN == 'Y'">
                  <p>
                    <span class="h-tit">{{
                      v_getLabel('appl_ymd','신청일') }}</span>
                  </p>
                  <ul>
                    <li v-for="(item, index) in arrAttendYmd">
                      <p>
                        <span class="h-tit">{{v_formatValue(item.ATTEND_YMD,'dfDateYmd')}}
                        </span> <input type="checkbox"
                          class="h-checkbox-style01"
                          :id="v_getId(index)" v-model="item.ALL_DAY_YN"
                          true-value="Y" false-value="N"
                          :disabled="item.disableYn"
                          @change="item.changeAllDayYn(item)" /> <label
                          :for="v_getId(index)">{{v_getLabel('all_day_01','종일')}}</label>
                        <span class="ml-3">{{v_getTimeString(item.USE_HOUR,
                          item.USE_MIN)}}</span> <label class="ml-3">{{v_getLabel('sta','시작')}}
                          : </label> <span class="h-tit">{{
                          v_getCodeNm(combo.S_YMD_CLASS,
                          item.STA_YMD_CLASS) }}(
                          {{v_formatValue(item.STA_HM,"dfTimeHm")}} ) ~
                          {{ v_getCodeNm(combo.S_YMD_CLASS,
                          item.END_YMD_CLASS) }}(
                          {{v_formatValue(item.END_HM,"dfTimeHm")}} ) </span>
                      </p>
                    </li>
                  </ul>
                  </p>
                </div>
                <div class="col-md-12 col-lg-6">
                  <p>{{ v_getLabel('appl_rsn','신청사유') }}</p>
                  <p>
                    <span class="h-tit">{{formValue.S_NOTE}}</span>
                  </p>
                </div>
              </div>
              <div class="row" v-show="formValue.S_APPL_CLASS == '20' ">
                <div class="col-md-6 col-lg-4">
                  <p>
                    <com:label key='wig_appl' def='신청내역' />
                  </p>
                  <p>
                    <span class="h-tit">{{formValue.S_APPL_INFO}}</span>
                  </p>
                </div>
                <div class="col-md-12 col-lg-6">
                  <p>
                    <com:label key='cancel_rsn' def='취소사유' />
                  </p>
                  <p>
                    <span class="h-tit">{{formValue.S_NOTE}}</span>
                  </p>
                </div>
              </div>
              <div class="h-dotted"></div>
              <div class="row"
                v-show="visible.graphAreaDiv && arrGraphData.length > 0">
                <div class="col-md-12 col-lg-6">
                  <!-- 
                ( {{item.STA_YMD.substring(4,6)}}.{{item.STA_YMD.substring(6,8)}}~{{item.END_YMD.substring(4,6)}}.{{item.END_YMD.substring(6,8)}} ) 
                 -->
                  <p>{{ v_getLabel('work_time_stat','근로시간현황') }}</p>
                  <p>
                  <ul class="w-100">
                    <!--                <li v-for="(item, index) in arrGraphData" class="p-2 mb-3" style="border:1px solid #c7d1d8;"> -->
                    <li v-for="(item, index) in arrGraphData"
                      class="p-2 mb-3">
                      <div class="mt-1 mb-2">
                        <span style="font-weight: bold;">{{item.STA_YMD.substring(4,6)}}.{{item.STA_YMD.substring(6,8)}}~{{item.END_YMD.substring(4,6)}}.{{item.END_YMD.substring(6,8)}}</span>
                        <span class="workState2"
                          :style="{'background-color' : v_getGraphRgb(item.STAT_PER_CD)}">
                          {{item.STAT_PER_NM}} </span>
                      </div>
                    </li>
                  </ul>
                  </p>
                </div>
              </div>
            </div>
          </div>
          <!-- 결재시 //-->


          <!--// 파일 -->

          <%@include file="/sys/sy_appl/sys_appl_file.jsp"%>

          <!-- 파일 //-->

          <!--// 히스토리 -->

          <%@include file="/sys/sy_appl/sys_appl_history.jsp"%>

          <!-- 히스토리 //-->

        </div>

        <!-- 본문 //-->

      </div>



      <!--// 버튼 -->

      <%@include file="/sys/sy_appl/sys_appl_button.jsp"%>

      <!-- 버튼 //-->

    </form>

  </div>

  <!-- 신청서 공통 start -->

  <div class="h-hidden-zone">

    <textarea><%=hmPreparedData.get("xmlApplTypeInfo")%></textarea>

  </div>

  <!-- end -->

</body>

</html>