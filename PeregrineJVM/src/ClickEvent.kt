import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

class ClickEvent (val time : Long,
                  val url : String,
                  val groupName : String
                  ) {

    val isDue : Boolean
        get() = time < System.currentTimeMillis()

    val timeUntilDue : Long
        get() = time - System.currentTimeMillis()

    init{
        Thread{
            println("Event for the site $url for group $groupName will be due in $timeUntilDue millis")

            if(timeUntilDue > 0) {
                Thread.sleep(timeUntilDue)
            }

            handleEvent()
        }.start()
    }

    fun urlColumnJSONMap(path : Path){
        val file = File(path.toString())
        file.createNewFile()

        val writer = BufferedWriter(FileWriter(file))
        writer.write("{")
        writer.newLine()
        writer.write(""" "url" : "$url", """)
        writer.newLine()
        writer.write(""" "groupName" : "$groupName" """)
        writer.newLine()
        writer.write("}")

        writer.close()
        println("Map File '$path' created")
    }

    private fun handleEvent(){

        val courseNrRegex = Regex("courseNr")
        val match = courseNrRegex.find(url)
        val numberRange = match!!.range.endInclusive + 2 .. match.range.endInclusive + 7
        val courseNr = url.substring(numberRange)
        println(courseNr)

        val path = Paths.get("urlColumnMatches/match$courseNr.JSON")
        urlColumnJSONMap(path)

        Runtime.getRuntime().exec("$BROWSER_PATH $url")
        println("window '$url' started")
    }

    override fun toString() : String{
        val calendar = GregorianCalendar()
        calendar.timeInMillis = time

        return("""
            URL: $url
            groupName: $groupName
            this Event will be due in ${timeUntilDue / 60_000} minutes and ${(timeUntilDue % 60_000)/1_000} seconds 
        """.trimIndent())


    }
}