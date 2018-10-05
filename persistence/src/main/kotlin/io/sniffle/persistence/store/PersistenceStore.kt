package io.sniffle.persistence.store

import java.io.File
import java.io.RandomAccessFile

class PersistenceStore(private val path: String, private val name: String,
                       private val fileSizeInMB: Long, private val lockFile: File = createLockFile(path)) {

    private val randomAccessFile: RandomAccessFile

    val length: Long
        get() = randomAccessFile.length()
    private var endOfFilePointer: Long = 0

    init {
        randomAccessFile = loadOrCreateFile(path, name, fileSizeInMB)
        seekEndOfFilePointer(randomAccessFile)
    }

    private fun seekEndOfFilePointer(randomAccessFile: RandomAccessFile) {
        var terminatorLength = -1
        while (terminatorLength != 0) {
            terminatorLength = randomAccessFile.readInt()
            if (terminatorLength > 0) {
                endOfFilePointer = randomAccessFile.filePointer
                randomAccessFile.seek((endOfFilePointer + terminatorLength))
                endOfFilePointer = randomAccessFile.filePointer
            }
        }
    }

    fun read(offset: Long, length: Int): ByteArray =
        ByteArray(length).also {
            randomAccessFile.seek(offset)
            randomAccessFile.read(it, 0, length)
        }

    fun readInt(offset: Long): Int {
        randomAccessFile.seek(offset)
        return randomAccessFile.readInt()
    }

    fun write(value: ByteArray): Pair<Long, Int> {
        val endOfFileBeforeWrite = endOfFilePointer
        val valueLength = value.size
        randomAccessFile.seek(endOfFilePointer)
        randomAccessFile.writeInt(valueLength)
        randomAccessFile.write(value)
        endOfFilePointer = randomAccessFile.filePointer
        return Pair(endOfFileBeforeWrite, valueLength)
    }

    private fun loadOrCreateFile(path: String, name: String, sizeInMB: Long): RandomAccessFile {
        createPath(path)
        return createFile(path, name, sizeInMB)
    }

    private fun createFile(path: String, name: String, sizeInMB: Long): RandomAccessFile =
        if (!lockFileExists(path)) {
            createFileAndLockFile(path, name, sizeInMB)
        } else {
            fail("Persistence file for name: $name could not be created in path $path")
        }

    private fun lockFileExists(path: String): Boolean = File(path).listFiles { _, name -> name == ".lock" }.isNotEmpty()

    private fun fail(errorMessage: String): Nothing = throw IllegalArgumentException(errorMessage)

    private fun createFileAndLockFile(path: String, name: String, sizeInMB: Long): RandomAccessFile {
        lockFile.createNewFile()
        return preAllocate(File(path + File.separator + name), (sizeInMB * 1024000))
    }

    private fun preAllocate(file: File, maxSizeInBytes: Long): RandomAccessFile =
        RandomAccessFile(file, "rw").apply { setLength(maxSizeInBytes) }


    private fun createPath(path: String): Boolean =
        File(path).run {
            mkdirs()
        }

    fun close() {
        try {
            randomAccessFile.close()
        } finally {
            lockFile.delete()
        }
    }
}

private fun createLockFile(path: String) = File(path + File.separator + ".lock")