/*anjularjs对于post和get请求默认的Content-Type header 是application/json。
通过设置‘Content-Type’: undefined，这样浏览器会帮我们把Content-Type 设置为 multipart/form-data.
通过设置transformRequest: angular.identity，anjularjs transformRequest function将序列化我们的formdata object.
*/
app.service("uploudService",function ($http) {
    this.uploadFile=function () {
        //New class with file upload function in html 5
        var formData=new FormData();
        formData.append("file",file.files[0]);
        return $http({
            method:'post',
            url:"../upload.do",
            data:formData,
            headers:{'Content-Type':undefined},
            //Binary serialization of forms
            transformRequest:angular.identity
        });
    }
})