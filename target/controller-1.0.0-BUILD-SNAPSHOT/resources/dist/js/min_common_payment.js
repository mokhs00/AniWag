var mwaiting = mwaiting || {};
jQuery(document).ready(function() {
    function t() {
        first && e()
    }
    function e() {
        $(".center_layer").css("display", ""),
        $(".btn_pay").click(function() {
            $(".center_layer").css("display", "none")
        })
    }
    function n() {
        $(".center_layer").css("display", ""),
        $(".btn_pay").click(function() {
            window.open(appUrl, "_top"),
            $(".center_layer").css("display", "none")
        })
    }
    function i() {
        return 1 == (window.location != window.parent.location)
    }
    var a = navigator.userAgent.toLowerCase();
    "app" == type && (document.location = appUrl),
    "mWeb" == type && (!function() {
        "IOS" == device ? -1 != a.indexOf("safari") && -1 == a.indexOf("crios") && -1 == a.indexOf("fxios") && !0 === i() ? n() : t() : -1 != a.indexOf("chrome") ? t() : window.location = appUrl
    }(),
    
    
    $(".link_warning").click(function(t) {
        "IOS" == device && -1 != a.indexOf("safari") && -1 == a.indexOf("crios") && -1 == a.indexOf("fxios") && !0 === i() && (t.preventDefault(),
        		window.open(appUrl, "_top"))})),
    mwaiting.defaultMsg = {
        paymentStopConfirmMsg: "카카오페이 결제를 중단하시겠습니까?",
        paymentCancelConfirmMsg: "결제를 취소하시겠습니까?",
        paymentWaitingMsg: "아직 결제가 완료되지 않았습니다.",
        paymentExpiredMsg: "유효시간 초과로 결제할 수 없습니다.<br/>가맹점에서 다시 결제를 요청해주세요.",
        paymentCancelMsg: "결제를 취소하셨습니다.",
        paymentCompletedMsg: "종료된 요청입니다.",
        paymentAuthFailMsg: "결제 인증이 실패하였습니다.<br/>결제를 다시 진행해주세요.",
        paymentFailMsg: "결제가 실패하였습니다.<br/>다시 진행해주세요."
    },
    mwaiting.IssuedSubscriptionOrderMsg = {
        paymentStopConfirmMsg: "결제수단 등록을 중단하시겠습니까?",
        paymentCancelConfirmMsg: "결제수단 등록을 취소하시겠습니까?",
        paymentWaitingMsg: "아직 등록이 완료되지 않았습니다.",
        paymentExpiredMsg: "유효시간 초과로 등록할 수 없습니다.<br/>가맹점에서 다시 등록을 요청해주세요.",
        paymentCancelMsg: "결제수단 등록을 취소하셨습니다.",
        paymentCompletedMsg: "종료된 요청입니다.",
        paymentAuthFailMsg: "결제수단 인증이 실패하였습니다.<br/>등록을 다시 진행해주세요.",
        paymentFailMsg: "결제수단 등록이 실패하였습니다.<br/>다시 진행해주세요."
    },
    mwaiting.messages = mwaiting.defaultMsg,
    isIssuedSubscriptionOrder && (mwaiting.messages = mwaiting.IssuedSubscriptionOrderMsg);
    var o = !1;
    mwaiting.commonAction = {
        blockUserEvents: function() {
            $(".btn_complete").attr("disabled", "disabled"),
            $(".link_cancle, .link_close").click(function(t) {
                t.preventDefault()
            })
        }
    },
    mwaiting.stateManager = function() {
        var t = {
            _isProcessing: !1,
            _isSuccess: !1,
            setTime: null,
            clickedButton: !1,
            xhr: null,
            config: {
                checkPaymentInterval: 3e3
            },
            init: function() {
                return setTimeout($.proxy(this._paymentPolling, t), 5e3),
                this
            },
            _paymentPolling: function() {
                this.setTimer = setTimeout($.proxy(this.checkPollingAjax, t), this.config.checkPaymentInterval)
            },
            checkPollingAjax: function() {
                function t() {
                    e.clickedButton && (e.clickedButton = !1,
                    teslaBaseCommon.payAlert(mwaiting.messages.paymentWaitingMsg),
                    $(".btn_complete").removeAttr("disabled", "disabled"))
                }
                var e = this;
                if (e._isProcessing)
                    return void t();
                e._isSuccess || (e._isProcessing = !0,
                e.xhr = $.ajax({
                    url: "/v1/" + hash + "/check",
                    timeout: 3e3,
                    type: "GET",
                    error: function(t, e, n) {},
                    complete: function() {
                        setTimeout(function() {
                            e._isProcessing = !1
                        }, 300)
                    },
                    success: function(n) {
                        n.expired ? (clearTimeout(e.setTimer),
                        mwaiting.commonAction.blockUserEvents(),
                        o && ($("body").removeClass("layer_on"),
                        $("#payConfirmDiv").addClass("hide")),
                        teslaBaseCommon.payAlert(mwaiting.messages.paymentExpiredMsg, function() {
                            return teslaCommonPayment.redirectToUrlWithMethod(isFailPost, failUrl, "#approvalPost")
                        })) : "success" === n.status_result ? (clearTimeout(e.setTimer),
                        mwaiting.commonAction.blockUserEvents(),
                        teslaCommonPayment.showSpin(".dimmed_layer"),
                        !e._isSuccess && n.approval_url && (e._isSuccess = !0,
                        teslaCommonPayment.redirectToUrlWithMethod(isApprovalPost, n.approval_url, "#approvalPost"))) : "cancel" === n.status_result ? (mwaiting.commonAction.blockUserEvents(),
                        teslaBaseCommon.payAlert(mwaiting.messages.paymentCancelMsg, function() {
                            return teslaCommonPayment.redirectToUrlWithMethod(isCancelPost, cancelUrl, "#approvalPost")
                        })) : "completed" === n.status_result ? (mwaiting.commonAction.blockUserEvents(),
                        teslaBaseCommon.payAlert(mwaiting.messages.paymentCompletedMsg, function() {
                            return teslaCommonPayment.redirectToUrlWithMethod(isFailPost, failUrl, "#approvalPost")
                        })) : "failPassword" === n.status_result ? (mwaiting.commonAction.blockUserEvents(),
                        teslaCommonPayment.redirectToUrlWithMethod(isFailPost, failUrl, "#approvalPost")) : "failSecondAuth" === n.status_result ? (mwaiting.commonAction.blockUserEvents(),
                        teslaBaseCommon.payAlert(mwaiting.messages.paymentAuthFailMsg, function() {
                            return teslaCommonPayment.redirectToUrlWithMethod(isFailPost, failUrl, "#approvalPost")
                        })) : "fail" === n.status_result ? (mwaiting.commonAction.blockUserEvents(),
                        teslaBaseCommon.payAlert(mwaiting.messages.paymentFailMsg, function() {
                            return teslaCommonPayment.redirectToUrlWithMethod(isFailPost, failUrl, "#approvalPost")
                        })) : "unsuccessfulRedirect" === n.status_result ? (mwaiting.commonAction.blockUserEvents(),
                        n.alert_message ? teslaBaseCommon.payAlert(n.alert_message, function() {
                            return teslaCommonPayment.redirectToUrlWithMethod(n.post, n.redirect_url, "#approvalPost")
                        }) : teslaCommonPayment.redirectToUrlWithMethod(n.post, n.redirect_url, "#approvalPost")) : (t(),
                        e._paymentPolling())
                    }
                }))
            }
        };
        return t.init(),
        {
            abortAjax: function() {
                clearTimeout(t.setTimer),
                null !== t.xhr && (t.xhr.abort(),
                t.xhr = null)
            },
            clickButton: function() {
                t.clickedButton = !0
            },
            checkPollingAjax: $.proxy(t.checkPollingAjax, t)
        }
    }(),
    function() {
        $(".btn_complete").click(function(t) {
            t.preventDefault(),
            $(t.target).attr("disabled", "disabled"),
            mwaiting.stateManager.abortAjax(),
            mwaiting.stateManager.clickButton(),
            mwaiting.stateManager.checkPollingAjax()
        }),
        $(".link_close").click(function() {
            o = !0,
            teslaBaseCommon.payConfirm(mwaiting.messages.paymentStopConfirmMsg, function() {
                return teslaCommonPayment.paymentStop(hash, isCancelPost, mwaiting.commonAction.blockUserEvents, "#actionPost")
            }, function() {
                o = !1
            })
        }),
        $(".link_cancle").click(function() {
            o = !0,
            teslaBaseCommon.payConfirm(mwaiting.messages.paymentCancelConfirmMsg, function() {
                return teslaCommonPayment.paymentStop(hash, isCancelPost, mwaiting.commonAction.blockUserEvents, "#actionPost")
            }, function() {
                o = !1
            })
        })
    }()
});
