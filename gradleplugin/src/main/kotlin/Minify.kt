import org.gradle.api.artifacts.transform.InputArtifact
import org.gradle.api.artifacts.transform.TransformAction
import org.gradle.api.artifacts.transform.TransformOutputs
import org.gradle.api.artifacts.transform.TransformParameters
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


/**
 * Author: Tungbo Wang
 * Email: yongxiang.wang@ximalaya.com
 * Date: 2023/4/26
 * Description：
 */
abstract class Minify : TransformAction<Minify.Parameters> {
    interface Parameters : TransformParameters {
        @get:Input
        var keepClassesByArtifact: Map<String, Set<String>>

    }

    @get:PathSensitive(PathSensitivity.NAME_ONLY)
    @get:InputArtifact
    abstract val inputArtifact: Provider<FileSystemLocation>

    override
    fun transform(outputs: TransformOutputs) {
        val fileName = inputArtifact.get().asFile.name
        for (entry in parameters.keepClassesByArtifact) {
            if (fileName.startsWith(entry.key)) {
                val nameWithoutExtension = fileName.substring(0, fileName.length - 4)
                println("transform $nameWithoutExtension")
                minify(
                    inputArtifact.get().asFile,
                    entry.value,
                    outputs.file("${nameWithoutExtension}-min.jar")
                )
                return
            }
        }
        println("Nothing to minify - using ${fileName} unchanged")
        outputs.file(inputArtifact)
    }

    private fun minify(artifact: File, keepClasses: Set<String>, jarFile: File) {
        println("Minifying ${artifact.name}")
        // Implementation ... 自定义操作
        try {
            val inputStream = FileInputStream(artifact)
            val outputStream = FileOutputStream(jarFile)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            inputStream.close()
            outputStream.close()
            println("File copied successfully.")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        println("Minifying ${jarFile.length()}")
        println("Minifying $jarFile")
    }
}