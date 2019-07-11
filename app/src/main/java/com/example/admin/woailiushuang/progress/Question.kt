package com.example.admin.woailiushuang.progress

data class Question (
        var id:Int?=null,
        var question:String? =null,
        var answer:String? = null,
        var list:List<String>? = null,
        var item1:String? = null,
        var item2:String? = null,
        var item3:String? = null,
        var item4:String? = null,
        var explains:String? = null,
        var url:String? = null
){

    override fun toString(): String {
        return "Question(id=$id, question=$question, answer=$answer, list=$list, item1=$item1, item2=$item2, item3=$item3, item4=$item4, explains=$explains, url=$url)"
    }
}

data class JSND(var reason:String?=null,
                var result:List<Question>?=null)