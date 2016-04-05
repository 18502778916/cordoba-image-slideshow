cordova.define("org.apache.cordova.images.ImagePlugin",function(require,exports,module){
var exec=require('cordova/exec');
module.exports={
showImages:function(content){
exec(null,null,"WebImages","showImages",[content]);
}
};
});