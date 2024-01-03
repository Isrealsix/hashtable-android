import android.content.Context
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

class HashSerialization {
    @Throws(IOException::class)
    fun saveToFile(filename: String?, hash: IHash, objectBuilder: ObjectBuilder) {
//        try {
//            BufferedWriter(FileWriter(filename)).use { writer ->
//                writer.write("<hashMap>\n")
//                hash.forEach(object : ActionStarter {
//                    override fun toDo(value: Any?) {
//                        writer.write(objectBuilder.toString(value) + "\n")
//                    }
//                })
//                writer.write("</hashMap>")
//            }
//        } catch (e: IOException) {
//            throw RuntimeException(e)
//        }

//        val file:String = "malam.txt"
//        val data:String = "my top 10 secret"
//        val fileOutputStream: FileOutputStream
//        try {
//            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
//            fileOutputStream.write(data.toByteArray())
//        }catch (e: Exception){
//            e.printStackTrace()
//        }
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    fun loadFromFile(filename: String?, hash: IHash): IHash {
        try {
            BufferedReader(FileReader(filename)).use { br ->
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    println(line)
                }
                return hash
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}