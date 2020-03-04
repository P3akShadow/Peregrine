import java.nio.file.Paths
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.pow

val COMMANDS = HashMap<String, String>()
val COMMAND_STRINGS = arrayOf("commands", "newEvent", "quit", "currentEvents")
val CIN = Scanner(System.`in`)
var QUIT = false

val EVENTS = ClickEventList()
val BROWSER_PATH = """C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"""

fun main(args: Array<String>) {
    fillCommands()
    println("Welcome to Peregrine")
    println("For a list of commands write 'commands'")

    while (!QUIT) {
        println("waiting for commands")
        menu(CIN.next())
    }
}

fun menu(command : String){
    when(command){
        COMMAND_STRINGS[0] -> printCommands()
        COMMAND_STRINGS[1] -> newEvent()
        COMMAND_STRINGS[2] -> QUIT = true
        COMMAND_STRINGS[3] -> currentEvents()
    }
}

fun fillCommands(){
    COMMANDS[COMMAND_STRINGS[0]] = "Prints all possible commands"
    COMMANDS[COMMAND_STRINGS[1]] = "Adds an Event to the EventList"
    COMMANDS[COMMAND_STRINGS[2]] = "terminates the program"
}

fun printCommands(){
    for(key in COMMANDS)
        println("${key.key} : ${key.value}")
}

fun newEvent(){

    println("When should I click?")
    var time = CIN.nextLong()

    if(time == 1.toLong()){
        time = System.currentTimeMillis()
        time -= System.currentTimeMillis() % 3_600_000             //gets the previous "full" hour
        time += 3_600_010                                          //10 extra millis to ensure being too early
    }else if(time < 60) {                                        //clicks in time seconds (cant be 1 or > 2)
        time = System.currentTimeMillis() + time * 1_000
    }


    println("Which url is the Button at?")
    val url = CIN.next()

    println("What is the group's Name?")
    val groupName = CIN.next()

    EVENTS.add(ClickEvent(time, url, groupName))
}

fun currentEvents(){
    println("Number of events: ${EVENTS.size}")

    for(event in EVENTS) {
        println("****************************************")
        println(event)
        println("****************************************")
    }

}
