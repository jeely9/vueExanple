function checkall(){
	$("#checkall").click(function(){
		var cklist = $(".ck")
		for(var i = 0; i< cklist.length; i++){
			cklist[i].checked=this.checked;
		}
	})
}

function remove(){
	$(".delete").click(function(){
		var row = this.parentNode.parentNode;
		var tip = window.confirm("您确定要删除吗？");
		if(tip){
			row.remove();
		}else{
			window.alert("未删除!")
		}
	})
}

function change(){
	$(".change").click(function(){
		 $('#myModal').modal({
			keyboard: true
		});
		var a= this.parentNode.parentNode;
		console.log(a);
		var beforeval = $("#temail").text();
			console.log(beforeval);
		$(".save").click(function(){
			var val = $("#email").val();
			console.log(val)
			$("#temail").text() = $("#email").val()
		})
	})
}

function table(){
	 //Model
	 
	 $.ajax({
		type:"get",
		url:"http://192.168.188.150:8080/TongjiUniversity/select_All.do",
		dataType:"JSONP",
		//async:false,
		//data: "",
		//jsonp:"callback",
		//jsonpCallback:"success_jsonpCallback",
		success:function(result){
			console.log(result);
			console.log(result.dept);
			console.log(result.dept[1]);
			
			
			
		},
		error: function(){
			alert("错误！");
		}
	});
        var data = {
            rows: [
            {  Name: '小王', Age: 18, School: '光明小学', Remark: '三好学生' },
            {  Name: '小刚', Age: 20, School: '复兴中学', Remark: '优秀班干部' },
            {  Name: '吉姆格林', Age: 19, School: '光明小学', Remark: '吉姆做了汽车公司经理' },
            {  Name: '李雷', Age: 25, School: '复兴中学', Remark: '不老实的家伙' },
            {  Name: '韩梅梅', Age: 22, School: '光明小学', Remark: '在一起' },
            ],
            rowtemplate: { Id: 0, Name: '', Age: '', School: '', Remark: '' }
        };
       
    //ViewModel
    var vue = new Vue({
        el: '#app',
        data: data,
        methods: {
            Save: function (event) {
                if (this.rowtemplate.Id == 0) {
                    //设置当前新增行的Id
                    this.rowtemplate.Id = this.rows.length + 1;
                    this.rows.push(this.rowtemplate);
                }
                
                //还原模板
                this.rowtemplate = { Id: 0, Name: '', Age: '', School: '', Remark: '' }
            },
            Delete: function (id) {
                //实际项目中参数操作肯定会涉及到id去后台删除，这里只是展示，先这么处理。
                for (var i=0;i<this.rows.length;i++){
                    if (this.rows[i].Id == id) {
                        this.rows.splice(i, 1);
                        break;
                    }
                }
            },
            Edit: function (row) {
                this.rowtemplate = row;
            }
        }
    });
}





$(function(){
	//checkall();    //全选
	//remove();      //删除整行
	//change();      //修改信息
	table();
})