package jackdaw.media

import java.io.File
import java.nio.file.Path
import java.nio.file.Files

import scutil.lang._
import scutil.implicits._
import scutil.log._

import jackdaw.util.Checked

object Symlink extends Decoder with Logging {
	def name	= "Symlink"
	
	def convertToWav(input:File, output:File, preferredFrameRate:Int, preferredChannelCount:Int):Checked[Unit] =
			for {
				_	<- recognizeFile(input)
				_ 	<-
					try {
						// TODO should ensure the wav file is compatible
						DEBUG(so"decoding with ${name}")
						val orig:Path	= input.toPath
						val link:Path	= output.toPath
						Files deleteIfExists		link
						Files createSymbolicLink	(link, orig)
						Win(())
					}
					catch { case e:Exception =>
						ERROR(so"${name} failed", e)
						Checked fail1 so"${name} failed: ${e.getMessage}"
					}
			}
			yield ()
			
	private val recognizeFile:File=>Checked[Unit]	=
			MediaUtil requireFileSuffixIn (".wav")
}
