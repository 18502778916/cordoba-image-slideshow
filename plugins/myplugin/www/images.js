var exec=require('cordova/exec');
module.exports={
showImages:function(content){
exec(null,null,"showImages","showImages",[content]);
}
};