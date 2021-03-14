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

      assetMa.idGetAssetName = function(id, assetList){
                                      list = JSON.parse(assetList);
                                      console.log(list);
                                      var assetName = "";


                                      $.each(list, function(index, dict) {
                                           if (dict.id == ('' + id)) {
                                               assetName = dict.assetName;
                                               return false;
                                           }
                                      });

                                      return assetName;
                               }
      assetMa.idGetAssetCode = function(id, assetList){
            list = JSON.parse(assetList);
            console.log(assetList);
            var assetCode = "";


            $.each(list, function(index, dict) {
                 if (dict.id == ('' + id)) {
                     assetCode = dict.assetCode;
                     return false;
                  }
            });

            return assetCode;
       }

        return assetMa;




})(assetMa, window);