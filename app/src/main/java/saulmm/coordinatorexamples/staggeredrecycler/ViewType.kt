package saulmm.coordinatorexamples.staggeredrecycler

enum  class  ViewType(var nameVal:String ="",val id:Int = 1) {

    BIG("BIG",1),
    SMALL("SMALL",2),
    EMPTY("EMPTY",3)

}

fun getNameVal(  id:Int = -1):String{

   val t =  ViewType.values()
    for(item in ViewType.values()){
        if(item.id == id )
            return item.nameVal
    }
    return "Not Found"
}