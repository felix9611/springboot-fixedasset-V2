/*工具类*/
var CoreUtil = (function () {
    var coreUtil = {};

    /*GET*/
    coreUtil.sendGet = function(url, params, ft){
        this.sendAJAX(url, params, ft, "GET")
    }

    /*POST*/
    coreUtil.sendPost = function(url, params, ft){
        this.sendAJAX(url, JSON.stringify(params), ft, "POST")
    }
    /*PUT*/
    coreUtil.sendPut = function(url, params, ft){
        this.sendAJAX(url, JSON.stringify(params), ft, "PUT")
    }
    /*DELETE*/
    coreUtil.sendDelete = function(url, params, ft){
        this.sendAJAX(url, JSON.stringify(params), ft, "DELETE")
    }


    /*ajax*/
    coreUtil.sendAJAX = function(url, params, ft, method){
        var loadIndex = top.layer.load(0, {shade: false});
        $.ajax({
            url: url,
            cache: false,
            async: true,
            data: params,
            type: method,
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            beforeSend: function(request) {
                request.setRequestHeader("authorization", CoreUtil.getData("access_token"));
            },
            success: function (res) {
                top.layer.close(loadIndex);
                if (res.code==0){
                    if(ft!=null&&ft!=undefined){
                        ft(res);
                    }
                }else if(res.code==401001){ //凭证过期重新登录
                    layer.msg("請重新登入", {time:2000}, function () {
                        top.window.location.href="/index/login"
                    })
                }else if(res.code==401008){ //凭证过期重新登录
                    layer.msg("抱歉！您沒有權限", {time:2000})
                } else {
                    layer.msg(res.msg);
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                top.layer.close(loadIndex);
                if(XMLHttpRequest.status==404){
                    top.window.location.href="/index/404";
                }else{
                    layer.msg("服務器好像出現問題！請稍後嘗試");
                }
            }
        })
    }


    /*存入本地缓存*/
    coreUtil.setData = function(key, value){
        layui.data('LocalData',{
            key :key,
            value: value
        })
    };
    /*从本地缓存拿数据*/
    coreUtil.getData = function(key){
        var localData = layui.data('LocalData');
        return localData[key];
    };

    //判断字符是否为空的方法
    coreUtil.isEmpty = function(obj){
        if(typeof obj == "undefined" || obj == null || obj == ""){
            return true;
        }else{
            return false;
        }
    }


    //字典数据回显
    coreUtil.selectDictLabel = function (datas, value) {
        datas = JSON.parse(datas);
        var label = "";
        $.each(datas, function(index, dict) {
            if (dict.value == ('' + value)) {
                label = dict.label;
                return false;
            }
        });
        //匹配不到，返回未知
        if (CoreUtil.isEmpty(label)) {
            return "未知";
        }
        return label;
    }

    coreUtil.selecTypetId = function (datas, id) {
            datas = JSON.parse(datas);
            var typeName = "";
            $.each(datas, function(index, dict) {
                if (dict.id == ('' + id)) {
                    typeName = dict.typeName;
                    return false;
                }
            });
            //匹配不到，返回未知
            if (CoreUtil.isEmpty(typeName)) {
                return "未知";
            }
            return typeName;
        }

        coreUtil.selecPlacetId = function (datas, id) {
                    datas = JSON.parse(datas);
                    var placeName = "";
                    $.each(datas, function(index, dict) {
                        if (dict.id == ('' + id)) {
                            placeName = dict.placeName;
                            return false;
                        }
                    });
                    //匹配不到，返回未知
                    if (CoreUtil.isEmpty(placeName)) {
                        return id;
                    }
                    return placeName;
                }


         coreUtil.selectDeptId = function (datas, id) {
                             datas = JSON.parse(datas);
                             var deptName = "";
                             $.each(datas, function(index, dict) {
                                 if (dict.id == ('' + id)) {
                                     deptName = dict.deptName;
                                     return false;
                                 }
                             });
                             //匹配不到，返回未知
                             if (CoreUtil.isEmpty(deptName)) {
                                 return "未知";
                             }
                             return deptName;
                         }

         coreUtil.selectAssetCode = function (datas, id) {
                                      datas = JSON.parse(datas);
                                      var assetCode = "";

                                      $.each(datas, function(index, dict) {
                                          if (dict.assetCode == ('' + id)) {
                                              assetCode = dict.assetCode;
                                              return false;
                                          }
                                      });
                                      if (CoreUtil.isEmpty(assetCode)) {
                                           assetCode = id + "(此資產不存在)";
                                           return assetCode;
                                      }
                                      return assetCode;
                                  }

        coreUtil.idGetAssetName = function(id, assetList){
                                              assetList = JSON.parse(assetList);
                                              console.log(assetList);
                                              var assetName = "";


                                              $.each(assetList, function(index, dict) {
                                                   if (dict.id == ('' + id)) {
                                                       assetName = dict.assetName;
                                                       return false;
                                                   }
                                              });

                                              return assetName;
                                       }
        coreUtil.idGetAssetCode = function(id, assetList){
                                                      assetList = JSON.parse(assetList);
                                                      console.log(assetList);
                                                      var assetCode = "";


                                                      $.each(assetList, function(index, dict) {
                                                           if (dict.id == ('' + id)) {
                                                               assetCode = dict.assetCode;
                                                               return false;
                                                           }
                                                      });

                                                      return assetCode;
                                               }

        coreUtil.selectAssetName = function (datas, id) {
                                              datas = JSON.parse(datas);
                                              var assetName = "";

                                              $.each(datas, function(index, dict) {
                                                  if (dict.assetCode == ('' + id)) {
                                                      assetName = dict.assetName;
                                                      return false;
                                                  }
                                              });
                                              if (CoreUtil.isEmpty(assetName)) {
                                                   return "(此資產不存在)";
                                              }
                                              return assetName;
                                          }

         coreUtil.selectStatus = function (status){

            if(status == "1"){
                return "不存在";
            }else if(status == "2"){
                return "存在";
            }else if(status == "3"){
                return "存在(殘缺)";
            }else if(status == "4"){
                                  return "要維修";
                              }else if(status == "5"){
                                  return "維修中";
                              }else{
                                  return "不明";
                              }
         }


    return coreUtil;
})(CoreUtil, window);
