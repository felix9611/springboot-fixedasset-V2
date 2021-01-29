package com.company.project.common.exception.code;


/**
 * 错误码
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
public enum BaseResponseCode implements ResponseCodeInterface {
    /**
     * 错误码
     */
    SUCCESS(0, "操作成功"),
    SYSTEM_BUSY(500001, "系統繁忙，請稍候再試"),
    OPERATION_ERRO(500002, "操作失敗"),

    TOKEN_ERROR(401001, "登錄憑證已過期，請重新登錄 "),
    DATA_ERROR(401003, "傳入數據異常 "),
    NOT_ACCOUNT(401004, "該用戶不存在,請先註冊"),
    USER_LOCK(401005, "該用戶已被鎖定，請聯繫運營人員 "),
    PASSWORD_ERROR(401006, "用戶名或密碼錯誤"),
    METHODARGUMENTNOTVALIDEXCEPTION(401007, "方法參數校驗異常"),
    UNAUTHORIZED_ERROR(401008, "權鑑校驗不通過"),
    ROLE_PERMISSION_RELATION(401009, "該菜單權限存在子集關聯，不允許刪除"),
    OLD_PASSWORD_ERROR(401010, "舊密碼不正確"),
    NOT_PERMISSION_DELETED_DEPT(401011, "該組織機構下還關聯著用戶，不允許刪除"),
    OPERATION_MENU_PERMISSION_CATALOG_ERROR(401012, "操作後的菜單類型是目錄，所屬菜單必須為默認頂級菜單或者目錄"),
    OPERATION_MENU_PERMISSION_MENU_ERROR(401013, "操作後的菜單類型是菜單，所屬菜單必須為目錄類型"),
    OPERATION_MENU_PERMISSION_BTN_ERROR(401013, "操作後的菜單類型是按鈕，所屬菜單必須為菜單類型"),
    OPERATION_MENU_PERMISSION_URL_NOT_NULL(401015, "菜單權限的url不能為空"),
    OPERATION_MENU_PERMISSION_URL_PERMS_NULL(401016, "菜單權限的標識符不能為空"),
    ;

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误消息
     */
    private final String msg;

    BaseResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
