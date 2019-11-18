package com.qian.wesmile.api.js;

import com.qian.wesmile.annotation.ParamName;
import com.qian.wesmile.annotation.RelativePath;
import com.qian.wesmile.model.result.JsTicketResult;

public interface JsTicketApi {
    @RelativePath("/cgi-bin/ticket/getticket")
    JsTicketResult getticket(@ParamName("type") String type);
}
