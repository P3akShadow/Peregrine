import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer
import java.nio.file.Files
import java.nio.file.Path

class ClickEvent (val time : Long,
                  val url : String,
                  val groupName : String
                  ) {
    val isDue : Boolean
    get() = time < System.currentTimeMillis()

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
}