var assetMa = (function () {
    var assetMa = {};

    assetMa.isEmpty = function(obj){
            if(typeof obj == "undefined" || obj == null || obj == ""){
                return true;
            }else{
                return false;
            }
        }

    assetMa.getPlaceName = function(assetCode, assetList){
                                assetList = JSON.parse(assetList);
                                console.log(assetList);
                                var assetPlace = "";


                                $.each(assetList, function(index, dict) {
                                     if (dict.assetCode == ('' + assetCode)) {
                                         assetPlace = dict.place;
                                         return false;
                                     }
                                });

                                if (assetMa.isEmpty(assetPlace)) {
                                    return "(此資產不存在)";
                                 }
                                return assetPlace;
                         }
     assetMa.getAssetData = function(assetCode, assetList){
                                     assetList = JSON.parse(assetList);
                                     console.log(assetList);
                                     var assetData = "";


                                     $.each(assetList, function(index, dict) {
                                          if (dict.assetCode == ('' + assetCode)) {
                                              assetData = dict;
                                              return false;
                                          }
                                     });

                                     if (assetMa.isEmpty(assetPlace)) {
                                         return "(此資產不存在)";
                                      }
                                     return assetData;
                              }

        return assetMa;




})(assetMa, window);