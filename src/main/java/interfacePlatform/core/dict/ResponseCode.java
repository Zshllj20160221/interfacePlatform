package interfacePlatform.core.dict;

public enum ResponseCode {

    success(10000, "操作成功!"), error(20000, "系统错误!"), fail(30000, "操作失败!"), unknown_account(10001, "账户不存在!"), freeze_account(10002,
            "账户已被冻结!"), lock_account(10003, "账户已被锁定!"), login_error(10004, "账户名或者密码错误!"), unauthorized(10005, "你没有此操作权限!"), code_error(10006,
                    "验证码错误!"), code_success(10007, "验证码正确!"), idcard_exist(10008, "身份证号码已存在"), user_exist(10009, "用户已存在"), app_unknow_account(10010,
                            "账户不存在或未登录,请重新进行登录!"), image_code_error(10011, "图形验证码错误!"), token_not_exist(10012, "请求未携带token！"), invalidtoken(20002,
                                    "token错误、无法解密"), session_unvaildate(20003, "客户端会话失效,请刷新页面重新登录!"), idcard_not_exist(20004,
                                            "身份证号码不能为空"), member_not_exist(20005, "会员不存在"), code_reach_limit(20006,
                                                    "您的手机号码今日已不能接收验证码"), approveProcess_not_exist(30001, "没有设置此用户对应的审批流程"), pay_pass_error(40004,
                                                            "支付密码错误"), access_param_invaild(30002, "请求参数错误"), access_request_fail(429,
                                                                    "请求数太多"), params_loss(40005, "参数丢失"), balance_lack(40006, "余额不足");

    private int code;

    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}