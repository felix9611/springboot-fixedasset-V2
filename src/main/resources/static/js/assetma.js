var AssetMa = (function () {
    var AssetMa = {};

    AssetMa.getPerName = function (datas, id) {
                                          datas = JSON.parse(datas);
                                          var PerName = "";

                                          $.each(datas, function(index, dict) {
                                              if (dict.id == ('' + id)) {
                                                  PerName = dict.realName;
                                                  return false;
                                              }
                                          });
                                          if (CoreUtil.isEmpty(PerName)) {
                                               PerName = "(不存在)";
                                               return PerName;
                                          }
                                          return PerName;
                                      }


})(AssetMa, window);