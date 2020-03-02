import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date
import org.w3c.xhr.XMLHttpRequest
import kotlin.math.pow

class UrlColumnMap(val url : String, val groupName : String)

fun main(args: Array<String>) {
    println("hello - im here to sign you into a tiss course")

    if(document.URL == "https://tiss.tuwien.ac.at/education/course/courseRegistration.xhtml" || document.URL == "https://tiss.tuwien.ac.at/education/course/groupList.xhtml"){
        println("clicking")

        val buttons = document.getElementsByTagName("input")
        println(buttons.length)

        val button = buttons[0] as HTMLInputElement
        button.click()


    }

    val url = document.URL

    val courseNrRegex = Regex("courseNr")
    val match = courseNrRegex.find(url)
    val numberRange = match!!.range.endInclusive + 2 .. match.range.endInclusive + 7
    val courseNr = url.substring(numberRange)
    println(courseNr)

    val path = "chrome-extension://mmojljechnociddmllimabfekkidbpgh/PeregrineJVM/urlColumnMatches/match$courseNr.JSON"
    clickAtPos(path)
}

fun clickAtPos(path : String){
    var fileInput : String
    val xhr =  XMLHttpRequest()
    xhr.onreadystatechange = {
        if (xhr.readyState == 4.toShort() && xhr.status == 200.toShort()){
            fileInput = xhr.responseText

            val urlColumnMap = JSON.parse<UrlColumnMap>(fileInput)
            println("searching for: ${urlColumnMap.groupName}")

            val groupWrappers = document.getElementsByClassName("groupWrapper")


            for(i in 0 until groupWrappers.length){
                var currentGroupName : String? = groupWrappers[i]
                        ?.children?.get(0)
                        ?.children?.get(0)
                        ?.textContent                //gets the name displayed on the Website

                var currentGroupNameTrimmed = false
                while(! currentGroupNameTrimmed){
                    if(currentGroupName?.get(currentGroupName.length - 1)!! < 48.toChar() || currentGroupName?.get(currentGroupName.length - 1)!! > 91.toChar()){
                        currentGroupName = currentGroupName.substring(0, currentGroupName.length - 1)
                    } else{
                        currentGroupNameTrimmed = true
                    }
                }

                if (currentGroupName != null) {
                    println(currentGroupName.length)
                }

                println(currentGroupName)

                if(currentGroupName.equals(urlColumnMap.groupName)) {

                    val lines = groupWrappers[i]
                            ?.children?.get(1)
                            ?.children?.get(0)
                            ?.children?.get(0)

                    println(lines?.tagName)

                    val button = lines?.children?.get(lines.children.length - 1)
                            ?.children?.get(0) as HTMLInputElement         //gets the button to click

                    println(button.id)

                    button.click()
                }
            }

        }
    }
    xhr.open("GET", path)
    xhr.send()
}
